package fair.com.example.gevik.amadeus.presentation.cars;

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

    }
}
