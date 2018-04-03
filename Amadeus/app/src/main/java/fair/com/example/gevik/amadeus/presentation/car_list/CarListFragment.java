package fair.com.example.gevik.amadeus.presentation.car_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fair.com.example.gevik.amadeus.R;
import fair.com.example.gevik.amadeus.model.CarItem;


public class CarListFragment extends Fragment {
    CarListRecyclerViewAdapter adapter;
    @BindView(R.id.recyclerView_carList)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    public final static String TAG = "CarListFragment.tag";
    private OnListFragmentInteractionListener mListener;

    public CarListFragment() {
    }

    public static CarListFragment newInstance() {
        return new CarListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_response_list, container, false);
        unbinder = unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CarListRecyclerViewAdapter(mListener);
        recyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(CarItem car);
    }

    public void udpateAdapter(List<CarItem> carItemList) {
        adapter.swapData(carItemList);
    }
}
