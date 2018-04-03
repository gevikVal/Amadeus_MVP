package fair.com.example.gevik.amadeus.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import fair.com.example.gevik.amadeus.db.CarRealmRepository;
import fair.com.example.gevik.amadeus.presentation.car_list.CarListModel;
import fair.com.example.gevik.amadeus.presentation.car_list.CarListPresenter;
import fair.com.example.gevik.amadeus.presentation.car_list.CarsListPresentationContract;
import fair.com.example.gevik.amadeus.util.Utility;

/**
 * Created by gevik on 3/6/2018.
 */
@Module
public class CarListModule {
    @Provides
    CarsListPresentationContract.Presenter provideCarListPresenter(CarsListPresentationContract.Model model, Context context){
        return new CarListPresenter(model,new Utility(context));
    }

    @Provides
    CarsListPresentationContract.Model provideCarListModel(){
        return new CarListModel(new CarRealmRepository());
    }

}
