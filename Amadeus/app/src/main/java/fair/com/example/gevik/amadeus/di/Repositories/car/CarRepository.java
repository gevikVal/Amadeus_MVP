package fair.com.example.gevik.amadeus.di.Repositories.car;

import android.util.Log;

import javax.inject.Inject;

import fair.com.example.gevik.amadeus.realm_models.Result;
import fair.com.example.gevik.amadeus.realm_models.ResultResponse;
import io.reactivex.Single;

/**
 * Created by gevik on 2/26/2018.
 */

public class CarRepository implements CarDataSourceContract.Repository {
    private CarDataSourceContract.RemoteDataSource  carRemoteDataSource;
    @Inject
    public CarRepository(CarDataSourceContract.RemoteDataSource carRemoteDataSource) {
        this.carRemoteDataSource = carRemoteDataSource;
    }

    @Override
    public Single<ResultResponse> getCars(double latitude, double longitude, int radius, String pickUp, String dropOff) {
        return carRemoteDataSource.getCars(latitude,longitude,radius,pickUp,dropOff);
    }
}
