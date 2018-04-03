package fair.com.example.gevik.amadeus.presentation.cars;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import fair.com.example.gevik.amadeus.di.Repositories.car.CarDataSourceContract;
import fair.com.example.gevik.amadeus.di.Repositories.car.CarRepository;
import fair.com.example.gevik.amadeus.network.MessageEvent;
import fair.com.example.gevik.amadeus.realm_models.Car;
import fair.com.example.gevik.amadeus.realm_models.Result;
import fair.com.example.gevik.amadeus.realm_models.ResultResponse;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by gevik on 2/26/2018.
 */

public class CarsPresenter implements CarsPresentationContract.Presenter, LifecycleObserver {
    private CarsPresentationContract.View carsView;
    private CarDataSourceContract.Repository carRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public CarsPresenter(CarsPresentationContract.View carsView, CarDataSourceContract.Repository carRepository) {
        this.carsView = carsView;
        this.carRepository = carRepository;
        if (this.carsView instanceof LifecycleOwner) {
            ((LifecycleOwner) carsView).getLifecycle().addObserver(this);
        }
    }

    @Override
    public void onLoadCars(double latitude, double longitude, int radius, String pickUp, String dropOff) {
        Disposable disposable = carRepository.getCars(latitude, longitude, radius, pickUp, dropOff)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResultResponse>() {
                    @Override
                    public void onSuccess(ResultResponse moviesDomainModel) {
                        EventBus.getDefault().post(new MessageEvent(200, "success"));
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        EventBus.getDefault().post(new MessageEvent(500, "Error"));
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onCarsClick(Car car) {

    }
    //lifecycle observer
    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {

    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {

    }

    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

}
