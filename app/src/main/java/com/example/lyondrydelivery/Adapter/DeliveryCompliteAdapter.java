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

import com.example.lyondrydelivery.Activity.DeliveyCompleteListItemShowActivity;
import com.example.lyondrydelivery.Activity.PickupCompliteListItomShowActivity;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Modal.DeliveryRequestComplited_Modal;
import com.example.lyondrydelivery.Modal.PickupItemModal;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_data;
import com.example.lyondrydelivery.Modal.SelecteDelivery.DeliverySchedualedList_Data;
import com.example.lyondrydelivery.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DeliveryCompliteAdapter extends RecyclerView.Adapter<DeliveryCompliteAdapter.ViewHolder> {

    List<DeliverySchedualedList_Data> deliveryRequestComplitedModals;
    Context context;
    SharedPrefManager sharedPrefManager;
    DeliverySchedualedList_Data deliveryRequestComplited_modal;

//    List<PickupItemModal> deliverySchedualedListData;
//    Context context;
//    SharedPrefManager sharedPrefManager;
//    PickupItemModal deliverySchedualedListData1;

//    public DeliveryCompliteAdapter(List<PickupItemModal> selectPickupDeliveryData, Context context) {
//        this.deliverySchedualedListData = selectPickupDeliveryData;
//        this.context = context;
//    }


    public DeliveryCompliteAdapter(List<DeliverySchedualedList_Data> deliveryRequestComplitedModals, Context context) {
        this.deliveryRequestComplitedModals = deliveryRequestComplitedModals;
        this.context = context;
    }

    public void filterList(ArrayList<DeliverySchedualedList_Data> filteredList) {
        deliveryRequestComplitedModals = new ArrayList<>();
        deliveryRequestComplitedModals.addAll(filteredList);
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_item_layout, parent, false);

        return new DeliveryCompliteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        deliveryRequestComplited_modal = deliveryRequestComplitedModals.get(position);
        holder.DDate.setText(deliveryRequestComplited_modal.getDeliveryRequestDate());
        String pickupid = String.valueOf(deliveryRequestComplited_modal.getDeliveryRequestId());
        holder.Pickup_id.setText(pickupid);
        holder.Name.setText(deliveryRequestComplited_modal.getCustomerName());
        holder.Location.setText(deliveryRequestComplited_modal.getLocation());
        holder.Time.setText(deliveryRequestComplited_modal.getDeliveryRequestTime());
        holder.Home_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(v.getContext(), DeliveyCompleteListItemShowActivity.class);
                intent.putExtra("PickupId",pickupid);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return deliveryRequestComplitedModals.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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
