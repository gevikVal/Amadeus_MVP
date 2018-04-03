package fair.com.example.gevik.amadeus.db;

import java.util.ArrayList;
import java.util.List;

import fair.com.example.gevik.amadeus.helper.EFMessageConstant;
import fair.com.example.gevik.amadeus.model.CarItem;
import fair.com.example.gevik.amadeus.realm_models.CarResult;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by gevik on 3/6/2018.
 */

public class CarRealmRepository implements DbRepository<CarItem> {
    @Override
    public void addOrUpdate(CarItem item) {

    }

    @Override
    public void addOrUpdate(Iterable<CarItem> items) {

    }

    @Override
    public void remove(String itemId) {

    }

    @Override
    public void removeAll() {
        Realm realm = Realm.getDefaultInstance();
        realm.deleteAll();
    }

    @Override
    public List<CarItem> queryAll() {
        return null;
    }

    @Override
    public List<CarItem> queryAll(String sort) {
        Realm realm = Realm.getDefaultInstance();
        List<CarItem> carItemList = new ArrayList<>();
        String field;
        Sort valueSortType;
        if (sort.contains("ASC")) {
            valueSortType = Sort.ASCENDING;
        } else {
            valueSortType = Sort.DESCENDING;
        }

        if (sort.equalsIgnoreCase("Distance ASC")) {
            field = "distance";
        } else if (sort.equalsIgnoreCase("Price ASC")) {
            field = "price";
        } else {
            field = "companyName";
        }

        RealmResults<CarResult> carList = realm.where(CarResult.class).findAllSorted(field, valueSortType);

        if (carList != null) {
            for (CarResult carResult : carList) {
                CarItem car = new CarItem();
                car.setAddress(carResult.getAddress());
                car.setAriconditioner(carResult.isAriconditioner());
                car.setCurrency(carResult.getCurrency());
                car.setAddress(carResult.getAddress());
                car.setDistance(carResult.getDistance());
                car.setLatitude(carResult.getLatitude());
                car.setLongitude(carResult.getLongitude());
                car.setFuel(carResult.getFuel());
                car.setPrice(carResult.getPrice());
                car.setProviderName(carResult.getProviderName());
                car.setTransmission(carResult.getTransmission());
                car.setType(carResult.getType());
                carItemList.add(car);
            }

        }
        return carItemList;
    }

    @Override
    public CarItem query(String itemId) {
        return null;
    }
}
