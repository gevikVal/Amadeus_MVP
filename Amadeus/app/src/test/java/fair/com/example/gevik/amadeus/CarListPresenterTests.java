package fair.com.example.gevik.amadeus;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fair.com.example.gevik.amadeus.di.Repositories.car.CarDataSourceContract;
import fair.com.example.gevik.amadeus.presentation.cars.CarsPresentationContract;
import fair.com.example.gevik.amadeus.presentation.cars.CarsPresenter;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by gevik on 3/11/2018.
 */

public class CarListPresenterTests {
    @Mock
    CarsPresentationContract.View carsView;
    @Mock
    CarDataSourceContract.Repository carRepository;
    @Mock
    CarsPresenter presenter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        presenter  = new CarsPresenter(carsView,carRepository);
    }
    @Test
    public void onLoadCars(){
        presenter.onLoadCars(0,0,0,null,null);
        verify(carRepository, times(1)).getCars(0,0,0,null,null);
    }
}
