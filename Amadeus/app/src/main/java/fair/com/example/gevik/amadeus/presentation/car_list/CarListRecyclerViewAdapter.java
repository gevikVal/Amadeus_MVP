package fair.com.example.gevik.amadeus.presentation.car_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fair.com.example.gevik.amadeus.R;
import fair.com.example.gevik.amadeus.presentation.car_list.CarListFragment.OnListFragmentInteractionListener;
import fair.com.example.gevik.amadeus.model.CarItem;

import java.util.ArrayList;
import java.util.List;


public class CarListRecyclerViewAdapter extends RecyclerView.Adapter<CarListRecyclerViewAdapter.ViewHolder> {
    List<CarItem> carItemList = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;

    public CarListRecyclerViewAdapter(OnListFragmentInteractionListener listener) {

        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.providerNameTV.setText(carItemList.get(position).getProviderName());
        holder.vehicleTypeTV.setText(carItemList.get(position).getType());
        holder.priceTV.setText(carItemList.get(position).getPrice() + "  " + carItemList.get(position).getCurrency());
        holder.distanceTV.setText("Distance :" + String.format("%.2f", carItemList.get(position).getDistance()));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(carItemList.get(holder.getAdapterPosition()));

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return carItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView providerNameTV;
        TextView vehicleTypeTV;
        TextView priceTV;
        TextView distanceTV;
        TextView carIdTV;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            providerNameTV = view.findViewById(R.id.provider_name);
            vehicleTypeTV = view.findViewById(R.id.vehicle_type);
            priceTV = view.findViewById(R.id.price);
            distanceTV = view.findViewById(R.id.distance);
            carIdTV = view.findViewById(R.id.car_id);
        }

        @Override
        public String toString() {
            return super.toString() + " '";
        }

    }

    public void swapData(List<CarItem> list) {
        carItemList.clear();
        if (list != null) {
            carItemList.addAll(list);
        }
        notifyDataSetChanged();
    }
}
