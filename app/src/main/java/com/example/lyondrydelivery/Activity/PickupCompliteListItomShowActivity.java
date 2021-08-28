package com.example.lyondrydelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lyondrydelivery.Adapter.DeliveryCompleteListShowAdapter;
import com.example.lyondrydelivery.Adapter.ItemAdapter;
import com.example.lyondrydelivery.Adapter.PickupCompliteListShowAdapter;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Modal.ItemModal;
import com.example.lyondrydelivery.Modal.PickupComplite.PickupComplete_data;
import com.example.lyondrydelivery.Modal.PickupComplite.PickupComplite_responce_modal;
import com.example.lyondrydelivery.Modal.SelecteDelivery.DeliveryDetailsList_data;
import com.example.lyondrydelivery.Modal.SelecteDelivery.SelectDeliveryScheduled_responce_modal;
import com.example.lyondrydelivery.R;
import com.example.lyondrydelivery.RetrofiltClient.RetrofitClient;
import com.example.lyondrydelivery.SQLiteDB.SqliteDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickupCompliteListItomShowActivity extends AppCompatActivity {
    List<ItemModal> itemModalList=new ArrayList<>();
    List<ItemModal> itemModalList_temp=new ArrayList<>();
    List<PickupComplete_data> pickupComplete_data=new ArrayList<>();
    List<PickupComplete_data> pickupComplete_data_temp=new ArrayList<>();

    ItemModal itemModal;
    PickupCompliteListShowAdapter adapter;
    RecyclerView recyclerView;
    SqliteDatabase database;
    String pickupid,ItemName,ItemQuntity,ItemServiceId,ItemServiceType,ItemUnitPrice,ItemOrderSplCodeId,ItemPackType,Itemcode,ItemDicriptions;
    Double amountss;
    Boolean checkItems = false;
    SharedPrefManager sharedPrefManager;
    String token,UserName;
    String PickupId;
    String app = "D";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_complite_list_itom_show);
        recyclerView= findViewById(R.id.complite_item_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        pickupid = getIntent().getStringExtra("PickupId");
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        UserName = sharedPrefManager.getLoginUser().getUserName();

        Log.e("token","**********token**********"+token);
        Log.e("UserName","**********UserName**********"+UserName);
        Log.e("PickupId","**********pickupididakhi**********"+pickupid);
        //database = new SqliteDatabase(this);
        //itemModalList =database.getPickupIdList();
        PickupCompleteFragment();

        //adapter = new PickupCompliteListShowAdapter(itemModalList,getApplicationContext());
        //recyclerView.setAdapter(adapter);

//        if (itemModalList.size()==0){
//            Toast.makeText(this, "list is empty", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            int SearialNo=0;
//            for (int i = 0; i < itemModalList.size(); i++) {
//
//                if (pickupid.equals(itemModalList.get(i).getPickupId())){
//
//                    itemModal = (ItemModal) itemModalList.get(i);
//                    amountss = Double.parseDouble(itemModal.getRate());
//                    Log.i("amountss","*************"+amountss);
//                    int Sno = itemModal.getSerialNo();
//                    int id = itemModal.getId();
//                    ItemServiceType = itemModal.getODServiceType();
//                    ItemName = itemModal.getItems();
//                    ItemQuntity = itemModal.getQty();
//                    ItemServiceId = itemModal.getODServiceId();
//                    ItemServiceType = itemModal.getODServiceType();
//                    ItemUnitPrice = itemModal.getUnitprice();
//                    ItemOrderSplCodeId = itemModal.getODSpecialInstruction();
//                    ItemPackType = itemModal.getODDeliveryPackType();
//                    Itemcode = itemModal.getODItemCode();
//                    ItemDicriptions = itemModal.getDiscription();
//
//                    itemModalList_temp.add(new ItemModal(++SearialNo,ItemName,ItemQuntity,String.valueOf(amountss)));
//                    adapter = new PickupCompliteListShowAdapter(itemModalList_temp,getApplicationContext());
//                    recyclerView.setAdapter(adapter);
//                }
//
//
//            }
////            if (checkItems==false){
////                //Toast.makeText(getApplicationContext(),"Please Add new Items",Toast.LENGTH_LONG).show();
////            }
//        }


    }

    private void PickupCompleteFragment() {

        Call<PickupComplite_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .PickupComplete(app,token, UserName, Integer.parseInt(pickupid));

        call.enqueue(new Callback<PickupComplite_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<PickupComplite_responce_modal> call, Response<PickupComplite_responce_modal> response) {

                if (response.isSuccessful()) {
                    PickupComplite_responce_modal pickupComplite_responce_modal = response.body();

                    if (pickupComplite_responce_modal.getSuccess()) {

                        pickupComplete_data = pickupComplite_responce_modal.getDeliveryDetailsList();
                        adapter = new PickupCompliteListShowAdapter(pickupComplete_data, getApplicationContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(getApplicationContext(), pickupComplite_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PickupComplite_responce_modal> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}