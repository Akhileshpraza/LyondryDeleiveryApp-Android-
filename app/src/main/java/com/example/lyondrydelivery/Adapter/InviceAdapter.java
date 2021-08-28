package com.example.lyondrydelivery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lyondrydelivery.Modal.InviceMaster.GridTran_data_invice;
import com.example.lyondrydelivery.Modal.InviceMaster.InviceModal;
import com.example.lyondrydelivery.R;

import java.util.List;

public class InviceAdapter extends RecyclerView.Adapter<InviceAdapter.ViewHolder> {

    List<GridTran_data_invice> gridTranDataInvices;
    Context context;

    public InviceAdapter(List<GridTran_data_invice> gridTran_data_invices, Context context) {
        this.gridTranDataInvices = gridTran_data_invices;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invice_item_layout_recycleview, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GridTran_data_invice gridTranDataInvice = gridTranDataInvices.get(position);

        holder.serviceName.setText(gridTranDataInvice.getInvoiceTranDescription());
        holder.hsnSac.setText(gridTranDataInvice.getInvoiceTranHSNSAC());
        holder.uom.setText(gridTranDataInvice.getInvoiceTranUOM());
        holder.quantity.setText(gridTranDataInvice.getInvoiceTranItemNos());
        holder.price.setText(String.valueOf(gridTranDataInvice.getInvoiceTranItemUnitPrice()));
        holder.taxable.setText(String.valueOf(gridTranDataInvice.getInvoiceTranAmount()));
    }

    @Override
    public int getItemCount() {
        return gridTranDataInvices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName,hsnSac,uom,quantity,price,taxable;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.seviceName);
            hsnSac = itemView.findViewById(R.id.hsnandsac);
            uom = itemView.findViewById(R.id.uom);
            quantity =itemView.findViewById(R.id.quantity);
            price =itemView.findViewById(R.id.price);
            taxable =itemView.findViewById(R.id.taxablevalue);

        }
    }
}
