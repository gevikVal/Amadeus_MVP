package fair.com.example.gevik.amadeus.di.Repositories.car;

import com.google.android.gms.maps.model.LatLng;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import javax.inject.Inject;


import fair.com.example.gevik.amadeus.network.NetworkState;
import fair.com.example.gevik.amadeus.realm_models.CarResult;
import fair.com.example.gevik.amadeus.network.MessageEvent;
import fair.com.example.gevik.amadeus.realm_models.Car;
import fair.com.example.gevik.amadeus.realm_models.Location;
import fair.com.example.gevik.amadeus.realm_models.Result;
import fair.com.example.gevik.amadeus.realm_models.ResultResponse;
import fair.com.example.gevik.amadeus.network.CarHubService;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by gevik on 2/26/2018.
 */

public class CarRemoteDataSource implements CarDataSourceContract.RemoteDataSource {
    private CarHubService carHubService;
    Retrofit retrofit;
    NetworkState networkState;

    @Inject
    public CarRemoteDataSource(CarHubService carHubService, Retrofit retrofit, NetworkState networkState) {
        this.carHubService = carHubService;
        this.retrofit = retrofit;
        this.networkState = networkState;
    }

    @Override
    public Single<ResultResponse> getCars(final double latitude, final double longitude, int radius, String pickUp, String dropOff) {
        Single<ResultResponse> remote = carHubService.getCarReuslts("CE3jgSBvt6jnOQhOCGw6vrKl2DepsGWp", latitude, longitude,
                radius, pickUp, dropOff).doOnSuccess(ResultResponse->saveDB(ResultResponse,1,1));
        return remote;

    }

    private void saveDB(final ResultResponse resultResponse, final double latitude, final double longitude) {
        try (final Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.deleteAll();
                }
            });
        }
        if (resultResponse == null) {
            return;
        }
        final double meter = 1609.344;
        LatLng currentLocationLatLng = new LatLng(latitude, longitude);
        final android.location.Location cuurent = new android.location.Location("point A");
        cuurent.setLatitude(currentLocationLatLng.latitude);
        cuurent.setLongitude(currentLocationLatLng.longitude);
        try (final Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(resultResponse);
                }
            });

        }
        try (final Realm realm = Realm.getDefaultInstance()) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Location> realmList = realm.where(Location.class).findAll();
                    for (Location location : realmList) {

                        double latitude = 0;
                        double longitude = 0;
                        android.location.Location carLocation = new android.location.Location("point B");
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        if (latitude != 0 && longitude != 0) {
                            LatLng carLatLang = new LatLng(location.getLatitude(), location.getLongitude());
                            carLocation.setLatitude(carLatLang.latitude);
                            carLocation.setLongitude(carLatLang.longitude);
                            double distance = cuurent.distanceTo(carLocation);

                            location.setDistance(distance / meter);
                        } else {
                            continue;
                        }

                    }
                }
            });
        }

        try (final Realm realm = Realm.getDefaultInstance()) {
            RealmList<Car> carRealmList;
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    ResultResponse resultResponses = realm.where(ResultResponse.class).findFirst();
                    RealmList<Result> realmList = resultResponses.getResulList();
                    for (Result result : realmList) {
                        RealmList<fair.com.example.gevik.amadeus.realm_models.Car> carList = result.getCars();
                        for (fair.com.example.gevik.amadeus.realm_models.Car realmCar : carList) {
                            CarResult car = new CarResult();
                            car.setProviderName(result.getProvider().getCompanyName());
                            car.setLatitude(result.getLocation().getLatitude());
                            car.setLongitude(result.getLocation().getLongitude());
                            car.setAddress(result.getAddress().getLine1() + " " + result.getAddress().getCity() + " " + result.getAddress().getCountry());
                            car.setDistance(result.getLocation().getDistance());
                            car.setTransmission(realmCar.getVehicleInfo().getTransmission());
                            car.setFuel(realmCar.getVehicleInfo().getFuel());
                            car.setAriconditioner(realmCar.getVehicleInfo().getAirConditioning());
                            car.setPrice(realmCar.getRates().get(0).getPrice().getAmount());
                            car.setType(realmCar.getVehicleInfo().getAcrissCode());
                            car.setCurrency(realmCar.getRates().get(0).getPrice().getCurrency());
                            realm.copyToRealmOrUpdate(car);
                        }
                    }
                }
            });
        }


    }
}
