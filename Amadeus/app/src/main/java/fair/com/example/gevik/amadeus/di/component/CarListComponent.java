package fair.com.example.gevik.amadeus.di.component;

import dagger.Subcomponent;
import fair.com.example.gevik.amadeus.presentation.car_list.CarListActivity;
import fair.com.example.gevik.amadeus.di.module.CarListModule;
import fair.com.example.gevik.amadeus.scope.PresentationScope;

/**
 * Created by gevik on 3/6/2018.
 */
@PresentationScope
@Subcomponent(modules = {CarListModule.class})
public interface CarListComponent {
    void inject(CarListActivity target);
}
