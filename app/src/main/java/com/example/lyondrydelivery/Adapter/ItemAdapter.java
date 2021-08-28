package com.example.lyondrydelivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lyondrydelivery.Activity.AddNewItemActivity;
import com.example.lyondrydelivery.Activity.EditeAddNewItemsActivity;
import com.example.lyondrydelivery.Activity.PickupDeliveryActivity;
import com.example.lyondrydelivery.Fragments.CameraFragment;
import com.example.lyondrydelivery.Modal.ItemModal;
import com.example.lyondrydelivery.R;
import com.example.lyondrydelivery.SQLiteDB.SqliteDatabase;

import java.io.Serializable;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    List<ItemModal> itemModalList ;
    Context context;
   // ListSize listSize;
    SqliteDatabase databaseHelperClass;

    public ItemAdapter(List<ItemModal> itemModalList, Context context) {
        this.itemModalList = itemModalList;
        this.context = context;
        databaseHelperClass = new SqliteDatabase(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModal itemModal = itemModalList.get(position);

        holder.Id.setText(String.valueOf(itemModal.getId()));
        holder.SerialNumber.setText(String.valueOf(itemModal.getSerialNo()));
        holder.Items.setText(itemModal.getItems());
        String Quantity = itemModal.getQty();
        holder.QTY.setText(itemModal.getQty());
        holder.RATE.setText("Rs." + itemModal.getRate());
        String pickuid = itemModal.getPickupId();
        Log.i("pickuid","**********pickuidnewnew**********"+itemModal.getId());

        //****************************** sqlite Delete Items*********************************
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteItems(itemModal.getId());
                databaseHelperClass.deleteItems(itemModal.getSerialNo());

            System.out.println("*********Delete****************"+itemModal.getId());
                itemModalList.remove(position);
                Intent intent = new Intent(v.getContext(),PickupDeliveryActivity.class);
                v.getContext().startActivity(intent);
                notifyDataSetChanged();
            }
        });
        //****************************** sqlite Delete Items*********************************

        //*****************************************sqlite Edite Items or Update************************

        holder.Edite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               SqliteDatabase database = new SqliteDatabase(context);
               
                String id = String.valueOf(database.getPickupIdList().get(position).getId());
                int sno = database.getPickupIdList().get(position).getSerialNo();
                String ItemName = database.getPickupIdList().get(position).getItems();
                String ItemAmount = database.getPickupIdList().get(position).getRate();
                String ItemQuantity = database.getPickupIdList().get(position).getQty();
                String ItemUnitPrice =database.getPickupIdList().get(position).getUnitprice();
                String ItemCode = database.getPickupIdList().get(position).getODItemCode();
                String ItemServiceId = database.getPickupIdList().get(position).getODServiceId();
                String ItemSeviceType = database.getPickupIdList().get(position).getODServiceType();
                Log.i("ItemSeviceType","*********ItemSeviceType*************"+ItemSeviceType);
                String ItemSpecialInstruction =database.getPickupIdList().get(position).getODSpecialInstruction();
                String ItemPackType = database.getPickupIdList().get(position).getODDeliveryPackType();
                String ItemRemark = database.getPickupIdList().get(position).getODRemarks2();
                String ItemDiscription = database.getPickupIdList().get(position).getDiscription();

                Intent i = new Intent(v.getContext(),CameraFragment.class);
                i.putExtra("ID",id);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startService(i);

                Intent intent = new Intent(v.getContext(), EditeAddNewItemsActivity.class);
                intent.putExtra("ID",id);
                intent.putExtra("ItemName",ItemName);
                intent.putExtra("ItemAmount",ItemAmount);
                intent.putExtra("ItemQuantity",ItemQuantity);
                intent.putExtra("ItemUnitPrice",ItemUnitPrice);
                intent.putExtra("ItemCode",ItemCode);
                intent.putExtra("ItemServiceId",ItemServiceId);
                intent.putExtra("ItemSeviceType",ItemSeviceType);
                intent.putExtra("ItemSpecialInstruction",ItemSpecialInstruction);
                intent.putExtra("ItemPackType",ItemPackType);
                intent.putExtra("ItemRemark",ItemRemark);
                intent.putExtra("ItemDiscription",ItemDiscription);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemModalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView Id,SerialNumber, Items, QTY, RATE;

        ImageView Edite, Delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Id = itemView.findViewById(R.id.Uid);
            SerialNumber = itemView.findViewById(R.id.serialno);
            Items = itemView.findViewById(R.id.items);
            QTY = itemView.findViewById(R.id.qty);
            RATE = itemView.findViewById(R.id.rate);
            Edite = itemView.findViewById(R.id.edit1);
            Delete = itemView.findViewById(R.id.delete1);
        }
    }
}

