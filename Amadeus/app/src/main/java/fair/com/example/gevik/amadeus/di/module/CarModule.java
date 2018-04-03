package fair.com.example.gevik.amadeus.di.module;

import dagger.Module;
import dagger.Provides;
import fair.com.example.gevik.amadeus.di.Repositories.car.CarDataSourceContract;
import fair.com.example.gevik.amadeus.di.Repositories.car.CarRemoteDataSource;
import fair.com.example.gevik.amadeus.di.Repositories.car.CarRepository;
import fair.com.example.gevik.amadeus.network.CarHubService;
import fair.com.example.gevik.amadeus.network.NetworkState;
import fair.com.example.gevik.amadeus.presentation.cars.CarsPresentationContract;
import fair.com.example.gevik.amadeus.presentation.cars.CarsPresenter;
import retrofit2.Retrofit;

/**
 * Created by gevik on 2/26/2018.
 */
@Module
public class CarModule {

    private CarsPresentationContract.View carsView;

    public CarModule(CarsPresentationContract.View carsView) {
        this.carsView = carsView;
    }

    @Provides

    public CarDataSourceContract.RemoteDataSource provideCarRemoteDataSource(CarHubService carHubService, Retrofit retrofit, NetworkState networkState) {
        return new CarRemoteDataSource(carHubService, retrofit, networkState);
    }

    @Provides

    public CarDataSourceContract.Repository provideCarRepository(CarDataSourceContract.RemoteDataSource carRemoteDataSource) {
        return new CarRepository(carRemoteDataSource);
    }

    @Provides

    public CarsPresentationContract.Presenter provideCarsPresentation(CarRepository carRepository) {
        return new CarsPresenter(carsView, carRepository);
    }
}
