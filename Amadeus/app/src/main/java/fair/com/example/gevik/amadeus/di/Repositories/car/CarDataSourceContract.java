package fair.com.example.gevik.amadeus.di.Repositories.car;

import fair.com.example.gevik.amadeus.realm_models.Result;
import fair.com.example.gevik.amadeus.realm_models.ResultResponse;
import io.reactivex.Single;

/**
 * Created by gevik on 2/26/2018.
 */

public interface CarDataSourceContract {

    interface Repository {
        Single<ResultResponse> getCars(double latitude, double longitude, int radius, String pickUp, String dropOff);
    }

    interface RemoteDataSource {
        Single<ResultResponse> getCars(double latitude, double longitude, int radius, String pickUp, String dropOff);
    }

}
