package fair.com.example.gevik.amadeus.network;

import fair.com.example.gevik.amadeus.realm_models.ResultResponse;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gevik on 2/25/2018.
 */

public interface CarHubService {

    String BASE_URL = "https://api.sandbox.amadeus.com/v1.2/cars/";

    @GET("search-circle")
    Single<ResultResponse> getCarReuslts(@Query("apikey") String apikey,
                                         @Query("latitude") double latitude,
                                         @Query("longitude") double longitude,
                                         @Query("radius") int radius,
                                         @Query("pick_up") String pick_up,
                                         @Query("drop_off") String drop_off);

}
