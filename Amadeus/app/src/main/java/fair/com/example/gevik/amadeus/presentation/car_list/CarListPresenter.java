package fair.com.example.gevik.amadeus.presentation.car_list;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import java.util.List;

import fair.com.example.gevik.amadeus.model.CarItem;
import fair.com.example.gevik.amadeus.realm_models.Car;
import fair.com.example.gevik.amadeus.util.Utility;

/**
 * Created by gevik on 3/4/2018.
 */

public class CarListPresenter implements CarsListPresentationContract.Presenter, LifecycleObserver {
    CarsListPresentationContract.View view;
    CarsListPresentationContract.Model model;
    Utility utility;

    public CarListPresenter(CarsListPresentationContract.Model model, Utility utility) {
        this.model = model;
        this.utility = utility;
    }


    @Override
    public void loadCarList(String companyQuery, String distanceQuery, String priceQuery) {

    }

    @Override
    public void onCarsClick(Car car) {

    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {

    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        String sort = utility.getPreferredCompanySort();
        List<CarItem> carItemList = model.getCarList(sort);
        view.showCarList(carItemList);
    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {

    }

    @Override
    public void setView(CarsListPresentationContract.View view) {
        this.view = view;
        if (view instanceof LifecycleOwner) {
            ((LifecycleOwner) view).getLifecycle().addObserver(this);
        }
    }

    @Override
    public void dropView() {
        view = null;
    }


}
