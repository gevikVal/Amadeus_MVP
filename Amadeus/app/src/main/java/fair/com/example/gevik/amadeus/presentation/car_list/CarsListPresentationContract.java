package fair.com.example.gevik.amadeus.presentation.car_list;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import java.util.List;

import fair.com.example.gevik.amadeus.presentation.BasePresenter;
import fair.com.example.gevik.amadeus.model.CarItem;
import fair.com.example.gevik.amadeus.realm_models.Car;

/**
 * Created by gevik on 3/4/2018.
 */

public interface CarsListPresentationContract {
    interface View {
        void showCarList(List<CarItem> carItemList);
    }

    interface Presenter extends BasePresenter<View> {

        void loadCarList(String companyQuery, String distanceQuery, String priceQuery);

        void onCarsClick(Car car);

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause();

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume();

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void onCreate();

    }

    interface Model {
        public List<CarItem> getCarList(String sort);
    }

}
