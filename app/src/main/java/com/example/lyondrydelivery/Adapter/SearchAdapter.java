package com.example.lyondrydelivery.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lyondrydelivery.Activity.AddNewItemActivity;
import com.example.lyondrydelivery.Modal.SelectItem.SelecteItem_data;
import com.example.lyondrydelivery.Modal.SelectItem.SelecteItem_data_temp;
import com.example.lyondrydelivery.R;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements Filterable {

    List<SelecteItem_data_temp> selecteItemData;
    List<SelecteItem_data_temp>backup;
    Context context;

    public SearchAdapter(List<SelecteItem_data_temp> selecteItemData, Context context) {
        this.selecteItemData = selecteItemData;
        this.context = context;
        this.backup = (selecteItemData);
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchlist_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        SelecteItem_data_temp date = selecteItemData.get(position);
        String name = date.getItemName();
        holder.Itemname.setText(date.getItemName());
        double Amonut = date.getItemPrice();
        String unitprice = Double.toString(Amonut);
        holder.UintPrice.setText(unitprice);
        holder.ItemCode.setText(date.getItemCode());
        Log.e("GroupItemss", "" + holder.Itemname);
        //Log.e("SelectItemsServiceType", "" + holder.SelectType);

        //int service  =date.getItemService();
        //ser = Double.toString(service);
        //holder.SelectService.setText(ser);
        holder.SelectType.setText(date.getItemServiceType());
        //Toast.makeText(context, ""+name, Toast.LENGTH_SHORT).show();

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(v.getContext(), ""+name, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(v.getContext(), AddNewItemActivity.class);
//                intent.putExtra("name",selecteItemData.get(position).getItemName());
//                intent.putExtra("unitprice",selecteItemData.get(position).getItemPrice());
//                v.getContext().startActivity(intent);
//               // holder.cardView.setVisibility(View.GONE);
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return selecteItemData.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<SelecteItem_data_temp> filterdata = new ArrayList<>();
            if (constraint.toString().isEmpty()){
                filterdata.addAll(backup);
                Log.v("filterdatshow","***"+backup.size());
            }
            else {
                for (SelecteItem_data_temp data : backup){
                    Log.v("filterdatshow","***1"+backup.size());
                    if (data.getItemName().toString().toLowerCase().contains(constraint.toString().toLowerCase()))
                        filterdata.add(data);
                }
            }

            FilterResults results =new FilterResults();
            results.values =filterdata;
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            selecteItemData.clear();
            selecteItemData.addAll((ArrayList<SelecteItem_data_temp>)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Itemname,UintPrice,ItemCode,SelectType;
        CardView cardView;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            Itemname = itemView.findViewById(R.id.itemname);
            UintPrice = itemView.findViewById(R.id.unitprice);
            ItemCode =itemView.findViewById(R.id.itemcode);
            SelectType = itemView.findViewById(R.id.selectType);
            cardView = itemView.findViewById(R.id.cartlist);
        }
    }
}
