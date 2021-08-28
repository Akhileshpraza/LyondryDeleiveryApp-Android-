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

import com.example.lyondrydelivery.Activity.PickupCompliteListItomShowActivity;
import com.example.lyondrydelivery.Activity.PickupDeliveryActivity;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_data;
import com.example.lyondrydelivery.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PickupCompleteMainAdapter extends RecyclerView.Adapter<PickupCompleteMainAdapter.ViewHolder> {
    List<SelectPickupDelivery_data> selectPickupDeliveryData;
    Context context;
    SelectPickupDelivery_data selectPickupDelivery_data;
    SharedPrefManager sharedPrefManager;

    public PickupCompleteMainAdapter(List<SelectPickupDelivery_data> selectPickupDeliveryData, Context context) {
        this.selectPickupDeliveryData = selectPickupDeliveryData;
        this.context = context;
    }

    public void filterList(ArrayList<SelectPickupDelivery_data > filteredList) {
        selectPickupDeliveryData = new ArrayList<>();
        selectPickupDeliveryData.addAll(filteredList);
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_item_layout, parent, false);

        return new PickupCompleteMainAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        selectPickupDelivery_data = selectPickupDeliveryData.get(position);
        holder.DDate.setText(selectPickupDelivery_data.getPickupRequestDate());
        String pickupid = String.valueOf(selectPickupDelivery_data.getPickupRequestId());
        Log.i("pickupid","*********pickupidpo***************"+pickupid);
        holder.Pickup_id.setText(pickupid);
        holder.Name.setText(selectPickupDelivery_data.getCustomerName());
        holder.Location.setText(selectPickupDelivery_data.getLocation());
        holder.Time.setText(selectPickupDelivery_data.getPickupRequestTime());
        String PickupId = String.valueOf(selectPickupDelivery_data.getPickupRequestId());

        holder.Home_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), PickupCompliteListItomShowActivity.class);
                intent.putExtra("PickupId",PickupId);
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
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            DDate = itemView.findViewById(R.id.tv_date);
            Pickup_id = itemView.findViewById(R.id.tv_pickup_id);
            Name = itemView.findViewById(R.id.tv_name);
            Location =  itemView.findViewById(R.id.tv_location);
            Time = itemView.findViewById(R.id.tv_time);
            context = itemView.getContext();
            Home_cardview =  itemView.findViewById(R.id.home_cardview);
            sharedPrefManager = new SharedPrefManager(itemView.getContext());
        }
    }
}
