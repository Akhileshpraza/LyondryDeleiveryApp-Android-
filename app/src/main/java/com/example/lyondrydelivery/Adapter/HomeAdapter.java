package com.example.lyondrydelivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lyondrydelivery.Activity.PickupDeliveryActivity;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Modal.HomeModal;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_data;
import com.example.lyondrydelivery.R;

import java.util.ArrayList;
import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    List<SelectPickupDelivery_data> selectPickupDeliveryData;
    Context context;
    SelectPickupDelivery_data selectPickupDelivery_data;
    SharedPrefManager sharedPrefManager;

    public HomeAdapter(List<SelectPickupDelivery_data> selectPickupDeliveryData, Context context) {
        this.selectPickupDeliveryData = selectPickupDeliveryData;
        this.context = context;
    }


    public void filterList(ArrayList<SelectPickupDelivery_data > filteredList) {
        selectPickupDeliveryData = new ArrayList<>();
        selectPickupDeliveryData.addAll(filteredList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_item_layout, parent, false);

        return new HomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        selectPickupDelivery_data = selectPickupDeliveryData.get(position);
        holder.DDate.setText(selectPickupDelivery_data.getPickupRequestDate());
        String pickupid = String.valueOf(selectPickupDelivery_data.getPickupRequestId());
        Log.i("pickupid","*********pickupidpo***************"+pickupid);
        holder.Pickup_id.setText(pickupid);
        holder.Name.setText(selectPickupDelivery_data.getCustomerName());
        holder.Location.setText(selectPickupDelivery_data.getLocation());
        holder.Time.setText(selectPickupDelivery_data.getPickupRequestTime());

        holder.Home_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), PickupDeliveryActivity.class);

                sharedPrefManager.saveSelectPickupDetails(selectPickupDeliveryData.get(position).getPickupRequestId(),
                        selectPickupDeliveryData.get(position).getPickupRequestAddress(),
                        selectPickupDeliveryData.get(position).getCustomerId(),
                        selectPickupDeliveryData.get(position).getCustomerName(),
                        selectPickupDeliveryData.get(position).getPickupRequestDate(),
                        selectPickupDeliveryData.get(position).getPickupRequestServiceType(),
                        selectPickupDeliveryData.get(position).getPickupRequestServiceName(),
                        selectPickupDeliveryData.get(position).getPickupRequestStoreName(),
                        selectPickupDeliveryData.get(position).getPickupRequestTime(),
                        selectPickupDeliveryData.get(position).getPickupRequestStatus(),
                        selectPickupDeliveryData.get(position).getLocation(),
                        selectPickupDeliveryData.get(position).getPickupRequestDueDate());


                v.getContext().startActivity(intent);
            }
        });

    }
 
    @Override
    public int getItemCount() {
        return selectPickupDeliveryData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView DDate,Pickup_id,Name,Location,Time;
        CardView Home_cardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            DDate = itemView.findViewById(R.id.tv_date);
            Pickup_id = itemView.findViewById(R.id.tv_pickup_id);
            Name = itemView.findViewById(R.id.tv_name);
            Location =  itemView.findViewById(R.id.tv_location);
            Time = itemView.findViewById(R.id.tv_time);
            context = itemView.getContext();
            Home_cardview =  itemView.findViewById(R.id.home_cardview);
            sharedPrefManager = new SharedPrefManager(itemView.getContext());
//            Home_cardview.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //Toast.makeText(context,"PICKUP ID",Toast.LENGTH_SHORT).show();
//
//                }
//            });
        }
    }
}
