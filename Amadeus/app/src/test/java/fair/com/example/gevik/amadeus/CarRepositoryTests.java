package fair.com.example.gevik.amadeus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fair.com.example.gevik.amadeus.di.Repositories.car.CarDataSourceContract;
import fair.com.example.gevik.amadeus.di.Repositories.car.CarRepository;
import fair.com.example.gevik.amadeus.presentation.cars.CarsPresenter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by gevik on 3/11/2018.
 */

public class CarRepositoryTests {
    @Mock
    CarDataSourceContract.RemoteDataSource carRemoteDataSource;
    @Mock
    CarRepository carRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        carRepository = new CarRepository(carRemoteDataSource);
    }

    @Test
    public void onLoadCars(){
        carRepository.getCars(0,0,0,null,null);
        verify(carRemoteDataSource, times(1)).getCars(0,0,0,null,null);
    }
}
