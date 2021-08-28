package com.example.lyondrydelivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lyondrydelivery.Activity.DeliveryDetailsActivity;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Modal.HomeModal;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_data;
import com.example.lyondrydelivery.Modal.SelecteDelivery.DeliveryDetailsList_data;
import com.example.lyondrydelivery.Modal.SelecteDelivery.DeliverySchedualedList_Data;
import com.example.lyondrydelivery.R;

import java.util.ArrayList;
import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder> {

    List<DeliverySchedualedList_Data> deliverySchedualedListData;
    List<DeliveryDetailsList_data>deliveryDetailsList_data;
    Context context;
    SharedPrefManager sharedPrefManager;
    DeliverySchedualedList_Data deliverySchedualedListData1;

    public DeliveryAdapter(List<DeliverySchedualedList_Data> selectPickupDeliveryData, Context context) {
        this.deliverySchedualedListData = selectPickupDeliveryData;
        this.context = context;
    }



    public void filterList(ArrayList<DeliverySchedualedList_Data> filteredList) {
        deliverySchedualedListData = new ArrayList<>();
        deliverySchedualedListData.addAll(filteredList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_item_fragments_layout, parent, false);

        return new DeliveryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        deliverySchedualedListData1 = deliverySchedualedListData.get(position);
        holder.DDate.setText(deliverySchedualedListData1.getDeliveryRequestDate());
        String pickupid = String.valueOf(deliverySchedualedListData1.getDeliveryRequestId());
        holder.Pickup_id.setText(pickupid);
        holder.Name.setText(deliverySchedualedListData1.getCustomerName());
        holder.Location.setText(deliverySchedualedListData1.getLocation());
        holder.Time.setText(deliverySchedualedListData1.getDeliveryRequestTime());
        String otp = deliverySchedualedListData1.getDeliveryOTP();
        Log.i("getOtp","**********getOtp**********"+otp);
        holder.Home_cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"PICKUP ID",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DeliveryDetailsActivity.class);

//                sharedPrefManager.saveItems(deliveryDetailsList_data.get(position).getOrderDetailsItemCode(),
//                        String.valueOf(deliveryDetailsList_data.get(position).getOrderDetailsPrice()),
//                        String.valueOf(deliveryDetailsList_data.get(position).getOrderDetailsTotalNo()));

                sharedPrefManager.saveSelectDeliveryDetails(
                        String.valueOf(deliverySchedualedListData.get(position).getDeliveryRequestId()),
                        deliverySchedualedListData.get(position).getDeliveryRequestAddress(),
                        deliverySchedualedListData.get(position).getCustomerName(),
                        String.valueOf(deliverySchedualedListData.get(position).getCustomerId()),
                        deliverySchedualedListData.get(position).getDeliveryRequestDate(),
                        deliverySchedualedListData.get(position).getDeliveryOTP(),
                        String.valueOf(deliverySchedualedListData.get(position).getDeliveryRequestTotalAmount()),
                        String.valueOf( deliverySchedualedListData.get(position).getDeliveryRequestTotalTax()),
                        String.valueOf(deliverySchedualedListData.get(position).getDeliveryRequestTotalCharge()),
                        String.valueOf(deliverySchedualedListData.get(position).getDeliveryRequestTotalDiscount()),
                        String.valueOf(deliverySchedualedListData.get(position).getDeliveryRequestTotalRoundOff()),
                        String.valueOf(deliverySchedualedListData.get(position).getDeliveryRequestNetAmount()));

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return deliverySchedualedListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView DDate,Pickup_id,Name,Location,Time,Arrow_right;
        CardView Home_cardview;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            DDate = itemView.findViewById(R.id.tv_date1);
            Pickup_id = itemView.findViewById(R.id.tv_pickup_id1);
            Name = itemView.findViewById(R.id.tv_name1);
            Location =  itemView.findViewById(R.id.tv_location1);
            Time = itemView.findViewById(R.id.tv_time1);
            Arrow_right=itemView.findViewById(R.id.tv_right_arrow1);
            context = itemView.getContext();
            Home_cardview =  itemView.findViewById(R.id.home_cardview1);
            sharedPrefManager = new SharedPrefManager(itemView.getContext());

        }
    }
}
