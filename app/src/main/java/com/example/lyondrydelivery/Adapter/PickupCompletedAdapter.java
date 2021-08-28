package com.example.lyondrydelivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lyondrydelivery.Activity.PickupCompliteListItomShowActivity;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Modal.PickupItemModal;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_data;
import com.example.lyondrydelivery.Modal.SelecteDelivery.DeliverySchedualedList_Data;
import com.example.lyondrydelivery.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PickupCompletedAdapter extends RecyclerView.Adapter<PickupCompletedAdapter.ViewHolder> {
   // List<DeliverySchedualedList_Data> deliverySchedualedListData;
    List<PickupItemModal> deliverySchedualedListData;
    Context context;
    SharedPrefManager sharedPrefManager;
    PickupItemModal deliverySchedualedListData1;

    public PickupCompletedAdapter(List<PickupItemModal> selectPickupDeliveryData, Context context) {
        this.deliverySchedualedListData = selectPickupDeliveryData;
        this.context = context;
    }


//    public void filterList1(ArrayList<DeliverySchedualedList_Data > filteredListlocation) {
//        deliverySchedualedListData = new ArrayList<>();
//        deliverySchedualedListData.addAll(filteredListlocation);
//        notifyDataSetChanged();
//    }


    @NonNull
    @NotNull
    @Override
    public PickupCompletedAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_item_layout, parent, false);

        return new PickupCompletedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PickupCompletedAdapter.ViewHolder holder, int position) {

        deliverySchedualedListData1 = deliverySchedualedListData.get(position);
        holder.DDate.setText(deliverySchedualedListData1.getPickupRequestDate());
        String pickupid = String.valueOf(deliverySchedualedListData1.getPickupRequestId());
        holder.Pickup_id.setText(pickupid);
        holder.Name.setText(deliverySchedualedListData1.getCustomerName());
        holder.Location.setText(deliverySchedualedListData1.getLocation());
        holder.Time.setText(deliverySchedualedListData1.getPickupRequestTime());
        holder.Home_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(v.getContext(), PickupCompliteListItomShowActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return deliverySchedualedListData.size();
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

        }
    }
}
