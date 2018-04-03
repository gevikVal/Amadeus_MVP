package fair.com.example.gevik.amadeus.presentation.car_list;

import java.util.List;

import fair.com.example.gevik.amadeus.db.DbRepository;
import fair.com.example.gevik.amadeus.model.CarItem;

/**
 * Created by gevik on 3/6/2018.
 */

public class CarListModel implements CarsListPresentationContract.Model {
    DbRepository dbRepositoryCarList;

    public CarListModel(DbRepository dbRepositoryCarList) {
        this.dbRepositoryCarList = dbRepositoryCarList;
    }


    @Override
    public List<CarItem> getCarList(String sort) {
        List<CarItem> carItemList = dbRepositoryCarList.queryAll(sort);
        return carItemList;
    }
}
