package com.example.lyondrydelivery.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lyondrydelivery.Modal.DeliveryDetailsItemModal;
import com.example.lyondrydelivery.Modal.ItemModal;
import com.example.lyondrydelivery.Modal.PickupComplite.PickupComplete_data;
import com.example.lyondrydelivery.R;
import com.example.lyondrydelivery.SQLiteDB.SqliteDatabase;

import java.util.List;

public class DeliveryDetailsAdapter extends RecyclerView.Adapter<DeliveryDetailsAdapter.ViewHolder> {

    List<PickupComplete_data>deliveryDetailsItemModalList;
    Context context;
    SqliteDatabase databaseHelperClass;
    int SearialNo=0;

    public DeliveryDetailsAdapter(List<PickupComplete_data> deliveryDetailsItemModalList, Context context) {
        this.deliveryDetailsItemModalList = deliveryDetailsItemModalList;
        this.context = context;
        databaseHelperClass = new SqliteDatabase(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_details_recycleview_item_layout, parent, false);

        return new DeliveryDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PickupComplete_data deliveryDetailsItemModal = deliveryDetailsItemModalList.get(position);
        holder.Serialno.setText(String.valueOf(++SearialNo));
        holder.Items.setText(deliveryDetailsItemModal.getOrderDetailsItemName());
        holder.QTY.setText(String.valueOf(deliveryDetailsItemModal.getOrderDetailsTotalNo()));
        holder.Rate.setText(String.valueOf(deliveryDetailsItemModal.getOrderDetailsPrice()));
        //String amou= deliveryDetailsItemModal.getRate();
        //Log.i("Raterate","*****************"+amou);

    }

    @Override
    public int getItemCount() {
        return deliveryDetailsItemModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Serialno,Items,QTY,Rate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Serialno = itemView.findViewById(R.id.serialno);
            Items =itemView.findViewById(R.id.items);
            QTY =itemView.findViewById(R.id.qty);
            Rate = itemView.findViewById(R.id.rate);

        }
    }
}
