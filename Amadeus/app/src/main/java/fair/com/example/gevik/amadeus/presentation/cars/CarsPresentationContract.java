package fair.com.example.gevik.amadeus.presentation.cars;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import fair.com.example.gevik.amadeus.realm_models.Car;

/**
 * Created by gevik on 2/26/2018.
 */

public interface CarsPresentationContract {
    interface View {

    }

    interface Presenter {

        void onLoadCars(double latitude, double longitude, int radius, String pickUp, String dropOff);

        void onCarsClick(Car car);

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause();

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume();

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void onCreate();

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void onDestroy();

    }
}
