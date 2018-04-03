package fair.com.example.gevik.amadeus.presentation.cars;

import android.Manifest;
import android.app.ActivityOptions;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.victor.loading.newton.NewtonCradleLoading;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fair.com.example.gevik.amadeus.network.CarsHubApplication;
import fair.com.example.gevik.amadeus.R;
import fair.com.example.gevik.amadeus.di.component.CarComponent;
import fair.com.example.gevik.amadeus.di.database.dao.util.ResultViewModel;
import fair.com.example.gevik.amadeus.di.module.CarModule;
import fair.com.example.gevik.amadeus.network.MessageEvent;
import fair.com.example.gevik.amadeus.presentation.car_list.CarListActivity;
import fair.com.example.gevik.amadeus.realm_models.ResultResponse;
import me.nlmartian.silkcal.DatePickerController;
import me.nlmartian.silkcal.DayPickerView;
import me.nlmartian.silkcal.SimpleMonthAdapter;

public class MainActivity extends AppCompatActivity implements CarsPresentationContract.View, PlaceSelectionListener, DatePickerController {

    CarComponent carComponent;
    DayPickerView calendarView;
    double latitude;
    double longitude;
    @Inject
    CarsPresentationContract.Presenter carsPresenter;
    private ResultViewModel resultViewModel;
    @BindView(R.id.search_btn)
    Button searchBtn;
    @BindView(R.id.calendar_view_drop_off)
    DayPickerView calendarViewDropOff;
    @BindView(R.id.pick_up_date_layout)
    LinearLayout pickUpDateLayout;
    @BindView(R.id.drop_off_date_layout)
    LinearLayout dropOffDateLayout;
    @BindView(R.id.drop_off_date)
    TextView dropOffTV;
    @BindView(R.id.pick_up_date)
    TextView pickUpTV;
    private Unbinder unbinder;
    String calenderTrack;
    private NewtonCradleLoading newtonCradleLoading;
    public final static String PICK_UP = "PICK_UP";
    public final static String DROP_OFF = "DROP_OFF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newtonCradleLoading = (NewtonCradleLoading) findViewById(R.id.newton_cradle_loading);
        unbinder = ButterKnife.bind(this, this);
        initCalender();
        calendarView = (DayPickerView) findViewById(R.id.calendar_view);
        calendarView.scrollToToday();

        calendarView.setController(this);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        searchBtn.setBackgroundResource(R.drawable.btn_press);
        searchBtn.startAnimation(pulse);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (latitude != 0 && longitude != 0) {

                    newtonCradleLoading.start();
                    newtonCradleLoading.setVisibility(View.VISIBLE);
                    carsPresenter.onLoadCars(latitude, longitude, 30, pickUpTV.getText().toString(), dropOffTV.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Please choose a location", Toast.LENGTH_LONG).show();
                }
            }
        });
        dropOffDateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.setVisibility(View.VISIBLE);
                calenderTrack = DROP_OFF;
            }
        });

        pickUpDateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.setVisibility(View.VISIBLE);
                calenderTrack = PICK_UP;
            }
        });

        createCarComponent().inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        resultViewModel.getResult().observe(this, new Observer<ResultResponse>() {    //just in case we want our app to use live data
            @Override
            public void onChanged(@Nullable ResultResponse response) {

            }
        });

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    private void initCalender() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        pickUpTV.setText(dateFormat.format(date));
        Calendar pickUp = Calendar.getInstance();
        Calendar dropOff = Calendar.getInstance();
        try {
            pickUp.setTime(dateFormat.parse(pickUpTV.getText().toString()));
            dropOff.setTime(dateFormat.parse(pickUpTV.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dropOff.add(Calendar.DATE, 6);
        pickUp.add(Calendar.DATE, 3);
        String pickUpTime = dateFormat.format(pickUp.getTime());
        String dropOffTime = dateFormat.format(dropOff.getTime());
        dropOffTV.setText(dropOffTime);
        pickUpTV.setText(pickUpTime);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getResponseNumber()) {
            case 200:
                newtonCradleLoading.stop();
                newtonCradleLoading.setVisibility(View.GONE);
                Bundle translateBundle =
                        ActivityOptions.makeCustomAnimation(MainActivity.this,
                                R.anim.slide_in_left, R.anim.slide_out_left).toBundle();
                Intent intent = new Intent(this, CarListActivity.class);
                startActivity(intent, translateBundle);
                break;
            case 400:
                showToast(event.getMessage());
                newtonCradleLoading.stop();
                newtonCradleLoading.setVisibility(View.GONE);
                break;
            case 500:
                showToast(event.getMessage());
                newtonCradleLoading.stop();
                newtonCradleLoading.setVisibility(View.GONE);
                break;
        }

    }

    private void showToast(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    CarComponent createCarComponent() {
        carComponent = ((CarsHubApplication) this.getApplication())
                .getApplicationComponent()
                .createSubComponent(new CarModule(this));
        return carComponent;

    }

    @Override
    public void onPlaceSelected(Place place) {
        LatLng lat = place.getLatLng();
        latitude = lat.latitude;
        longitude = lat.longitude;
    }

    @Override
    public void onError(Status status) {
        Toast.makeText(this, "the location is incorrect", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getMaxYear() {
        return 0;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {
        month = month + 1;
        String calenderDateOld = "" + year + "-" + month + "-" + day;
        Date date = null;
        String formattedString = null;
        try {
            date = new SimpleDateFormat("yyyy-M-d").parse(calenderDateOld);
            formattedString = new SimpleDateFormat("yyyy-MM-dd").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (calenderTrack.contains(PICK_UP)) {
            pickUpTV.setText(formattedString);
            calendarView.setVisibility(View.GONE);
        } else {
            dropOffTV.setText(formattedString);
            calendarView.setVisibility(View.GONE);
        }
        calenderTrack = "";
    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {

    }

    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

}
