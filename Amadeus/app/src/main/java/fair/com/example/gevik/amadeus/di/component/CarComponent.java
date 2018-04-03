package fair.com.example.gevik.amadeus.di.component;

import dagger.Subcomponent;
import fair.com.example.gevik.amadeus.presentation.cars.MainActivity;
import fair.com.example.gevik.amadeus.di.module.CarModule;
import fair.com.example.gevik.amadeus.scope.PresentationScope;

/**
 * Created by gevik on 2/26/2018.
 */
@PresentationScope
@Subcomponent(modules = {CarModule.class})
public interface CarComponent {
    void inject(MainActivity target);
}
