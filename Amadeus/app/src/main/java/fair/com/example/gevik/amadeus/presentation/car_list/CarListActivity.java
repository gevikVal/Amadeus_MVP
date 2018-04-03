package fair.com.example.gevik.amadeus.presentation.car_list;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fair.com.example.gevik.amadeus.presentation.BaseActivity;
import fair.com.example.gevik.amadeus.network.CarsHubApplication;
import fair.com.example.gevik.amadeus.R;
import fair.com.example.gevik.amadeus.di.component.CarListComponent;
import fair.com.example.gevik.amadeus.di.database.dao.util.ResultViewModel;
import fair.com.example.gevik.amadeus.di.module.CarListModule;
import fair.com.example.gevik.amadeus.model.CarItem;
import fair.com.example.gevik.amadeus.realm_models.ResultResponse;
import fair.com.example.gevik.amadeus.util.ActivityUtils;
import fair.com.example.gevik.amadeus.util.SettingsActivity;

public class CarListActivity extends BaseActivity implements CarsListPresentationContract.View, CarListFragment.OnListFragmentInteractionListener {
    CarListComponent carListComponent;
    private Unbinder unbinder;
    @BindView(R.id.filterBTN)
    Button sortBTN;
    private ResultViewModel resultViewModel;
    @Inject
    CarsListPresentationContract.Presenter carListPresenter;
    CarListFragment carListFragment;
    int width;
    int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        unbinder = ButterKnife.bind(this, this);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;
        height = displaymetrics.heightPixels;
        createCarListComponent().inject(this);
        carListPresenter.setView(this);
        sortBTN.setBackgroundResource(R.drawable.btn_press);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        sortBTN.setBackgroundResource(R.drawable.btn_press);
        sortBTN.startAnimation(pulse);

        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        resultViewModel.getResult().observe(this, new Observer<ResultResponse>() {
            @Override
            public void onChanged(@Nullable ResultResponse response) {

            }
        });
        sortBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        carListFragment = (CarListFragment) getSupportFragmentManager()
                .findFragmentByTag(CarListFragment.TAG);
        if (carListFragment == null) {
            carListFragment = CarListFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), carListFragment, R.id.main_content, CarListFragment.TAG, false);
        }

    }

    CarListComponent createCarListComponent() {
        carListComponent = ((CarsHubApplication) this.getApplication())
                .getApplicationComponent()
                .createCarListComponent(new CarListModule());
        return carListComponent;
    }

    @Override
    public void showCarList(List<CarItem> carItemList) {
        carListFragment.udpateAdapter(carItemList);
    }


    @Override
    public void onListFragmentInteraction(final CarItem car) {
        LinearLayout viewGroup = (LinearLayout) this.findViewById(R.id.popup);
        String airConditioner = "";
        LayoutInflater layoutInflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_view, viewGroup);
        TextView companyNameTV = layout.findViewById(R.id.companyNameTV);
        TextView addressTV = layout.findViewById(R.id.adressTV);
        TextView transmissionTV = layout.findViewById(R.id.transmissionTV);
        TextView airConditionerTV = layout.findViewById(R.id.airConditonTV);
        TextView priceTV = layout.findViewById(R.id.priceTV);
        Button navigateBTN = layout.findViewById(R.id.navigateBTN);
        companyNameTV.setText(car.getProviderName());
        addressTV.setText(car.getAddress());
        transmissionTV.setText("Transmission : " + car.getTransmission());
        airConditioner = car.isAriconditioner() ? "yes" : "no";
        airConditionerTV.setText("Air Condition : " + airConditioner);
        priceTV.setText(car.getPrice() + "" + car.getCurrency());
        PopupWindow popup = new PopupWindow(this);
        popup.setContentView(layout);
        popup.setWidth((int) (width));
        popup.setHeight((int) (.41 * height));
        popup.setFocusable(true);
        popup.setAnimationStyle(R.style.DialogAnimation);
        popup.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
        navigateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNavigation(car);
            }
        });

    }

    @Override
    public void onBackPressed() {

        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
        } else {
            if (getFragmentManager().getBackStackEntryCount() > 1) {
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }


    public void startNavigation(CarItem car) {
        String uri = "http://maps.google.com/maps?daddr=" + car.getLatitude() + "," + car.getLongitude();
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse(uri));
        startActivity(intent);
    }
}

