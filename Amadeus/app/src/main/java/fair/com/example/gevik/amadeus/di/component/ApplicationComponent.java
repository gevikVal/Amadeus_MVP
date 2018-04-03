package fair.com.example.gevik.amadeus.di.component;

import dagger.Component;
import fair.com.example.gevik.amadeus.network.CarsHubApplication;
import fair.com.example.gevik.amadeus.di.module.AndroidModule;
import fair.com.example.gevik.amadeus.di.module.ApplicationModule;
import fair.com.example.gevik.amadeus.di.module.CarListModule;
import fair.com.example.gevik.amadeus.di.module.CarModule;
import fair.com.example.gevik.amadeus.di.module.NetworkModule;
import fair.com.example.gevik.amadeus.scope.ApplicationScope;

/**
 * Created by gevik on 2/26/2018.
 */

@ApplicationScope
@Component(modules = {ApplicationModule.class, AndroidModule.class, NetworkModule.class} )
public interface ApplicationComponent {
    // Setup injection targets
    void inject(CarsHubApplication target);

    CarComponent createSubComponent(CarModule carModule);
    CarListComponent createCarListComponent(CarListModule carListModule);

}




