package com.example.lyondrydelivery.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyondrydelivery.Adapter.ItemAdapter;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Class.SharedPreference;
import com.example.lyondrydelivery.Fragments.PickupPendingFragment;
import com.example.lyondrydelivery.Modal.InsertOrder.GridTrans;
import com.example.lyondrydelivery.Modal.InsertOrder.InsertOrder_data;
import com.example.lyondrydelivery.Modal.InsertOrder.InsertOrder_responce_modal;
import com.example.lyondrydelivery.Modal.InsertOrder.MainInsertOrderList;
import com.example.lyondrydelivery.Modal.ItemModal;
import com.example.lyondrydelivery.Modal.PickupItemModal;
import com.example.lyondrydelivery.Modal.SelectItem.SelecteItem_data_temp;
import com.example.lyondrydelivery.Modal.Services.Services_data;
import com.example.lyondrydelivery.Modal.Services.Services_responce_modal;
import com.example.lyondrydelivery.R;
import com.example.lyondrydelivery.RetrofiltClient.RetrofitClient;
import com.example.lyondrydelivery.SQLiteDB.SqliteDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickupDeliveryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemAdapter adapter;
    Button Btn_Update;
    TextView AddNewItem, DueDate,TotalQuantity,TotalAmount;
    Spinner Service;
    RadioGroup radioGroup;
    RadioButton Regular,Express;
    TextView ConformationId,BtnCancle;
    TextView PickupId,Date,CustomerAddress;
    SharedPrefManager sharedPrefManager;
    String token, UserName;
    int storeId;
    List<Services_data> services_data;
    String select_service;
    String SeviceName;
    String dicriptions;
    int serviceId ,userId;
    String selectedType="";
    String address,date,pickupid,CustomerId;
    GridTrans[] gridTransList = new GridTrans[1];
    MainInsertOrderList mainInsertOrderList;
    SqliteDatabase database;
    List<ItemModal> itemModalList=new ArrayList<>();
    List<ItemModal> itemModalpickuplistList=new ArrayList<>();
    InsertOrder_responce_modal insertOrder_responce_modal;
    InsertOrder_data insertOrder_data;
    String PickupRequestServiceType,PickupRequestServiceName;
    SharedPreferences sp2;
    Boolean checkItems = false;
    Services_data servicesData;

    int itemService;
    Double TAMOUNT=0.0;
    Double amountss;
    int TotalQuantitys =0;
    int qty;
    String ItemName,ItemQuntity,ItemServiceId,ItemServiceType,ItemUnitPrice,ItemOrderSplCodeId,ItemPackType,Itemcode,ItemDicriptions;
    ItemModal itemModal;
    String OrderCode,Status;
    int  SelecteServiceId2;
    String InsertOrderDate;
    int selectedServiceId;
    String pickupRequestStoreName,pickupRequestAddress,pickupRequestDate,pickupRequestTime,pickupRequestStatus,locations,customerName,Customerid,dueDate;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickuo_delivery);
        recyclerView = findViewById(R.id.item_recycleView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        PickupId =findViewById(R.id.pickupid);
        Date = findViewById(R.id.date);
        DueDate = (TextView) findViewById(R.id.due_date);
        CustomerAddress = findViewById(R.id.tv_defalte_address);
        Service = findViewById(R.id.sp_services);
        radioGroup = findViewById(R.id.radioGroup);
        Regular = findViewById(R.id.radioRegular);
        Express = findViewById(R.id.radioExpress);
        Btn_Update = findViewById(R.id.btnupdate);
        TotalQuantity = findViewById(R.id.total_qty);
        TotalAmount = findViewById(R.id.total_amount);
        ConformationId = findViewById(R.id.tv_confirmation);
        AddNewItem = findViewById(R.id.tv_addnew_address);
        BtnCancle = findViewById(R.id.btn_cancle1);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        UserName = sharedPrefManager.getLoginUser().getUserName();
        storeId = sharedPrefManager.getLoginUser().getUserStoreId();
        userId = sharedPrefManager.getLoginUser().getUserID();
        CustomerId = String.valueOf(sharedPrefManager.getSelectPickupDetails().getCustomerId());
        address= sharedPrefManager.getSelectPickupDetails().getPickupRequestAddress();
        date= sharedPrefManager.getSelectPickupDetails().getPickupRequestDate();

        String startDateString = date;
        String[] dateString= date.split("/");
        String day= dateString[0];
        String month= dateString[1];
        String year = dateString[2];
        InsertOrderDate = year+"/"+month+"/"+day;
        Log.i("newDatenew","****************"+InsertOrderDate);

        pickupid= String.valueOf(sharedPrefManager.getSelectPickupDetails().getPickupRequestId());
        Log.i("pickupid","**********pickupidid**********"+pickupid);

        PickupRequestServiceType = sharedPrefManager.getSelectPickupDetails().getPickupRequestServiceType();
        PickupRequestServiceName = sharedPrefManager.getSelectPickupDetails().getPickupRequestServiceName();
        pickupRequestStoreName = sharedPrefManager.getSelectPickupDetails().getPickupRequestStoreName();
        pickupRequestAddress = sharedPrefManager.getSelectPickupDetails().getPickupRequestAddress();
        pickupRequestDate = sharedPrefManager.getSelectPickupDetails().getPickupRequestDate();
        pickupRequestTime = sharedPrefManager.getSelectPickupDetails().getPickupRequestTime();
       // pickupRequestStatus = sharedPrefManager.getSelectPickupDetails().getPickupRequestStatus();
        locations = sharedPrefManager.getSelectPickupDetails().getLocation();
        customerName =sharedPrefManager.getSelectPickupDetails().getCustomerName();
        Customerid = String.valueOf(sharedPrefManager.getSelectPickupDetails().getCustomerId());
        dueDate = sharedPrefManager.getSelectPickupDetails().getPickupRequestDueDate();
        Log.i("dueDate","**********dueDate**********"+dueDate);
        SelecteServiceId2 = getIntent().getIntExtra("ServiceId2",0);
        Log.e("SelecteServiceId2",""+SelecteServiceId2);


        PickupId.setText(pickupid);
        Date.setText(date);
        CustomerAddress.setText(address);
        ConformationId.setText(pickupid);
        DueDate.setText(dueDate);

        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(false);

            if (PickupRequestServiceType.equals("Express")) {
                radioGroup.check(R.id.radioExpress);
                radioGroup.getChildAt(1).setEnabled(true);

            } else {
                radioGroup.check(R.id.radioRegular);
                radioGroup.getChildAt(0).setEnabled(true);
            }
        }

        ServicesApi();
        setRecyclerView();
        AddINewItem();
        BtnCancle();
        BtnUpdate();
//        selectedType =Regular.getText().toString();
//           radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//                if (checkedId==R.id.radioRegular){
//                    selectedType =Regular.getText().toString();
//
//                    Log.i("selectedType1","*******selectedType***********"+selectedType);
//                }
//                else{
//                    selectedType =Express.getText().toString();
//                    Log.i("selectedType2","*******selectedType***********"+selectedType);
//                }
//            }
//        });

    }
    private void BtnUpdate(){
        Btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (itemModalList.isEmpty())
                {
                    Btn_Update.setEnabled(false);
                    Toast.makeText(PickupDeliveryActivity.this, "No Itesm So Plz Add New Items", Toast.LENGTH_SHORT).show();

                }
                else {
                    SqliteDatabase databaseHelperClass = new SqliteDatabase(PickupDeliveryActivity.this);
                    PickupItemModal pickupItemModal = new PickupItemModal(pickupid, PickupRequestServiceType, PickupRequestServiceName,pickupRequestStoreName,pickupRequestAddress,pickupRequestDate,pickupRequestTime,pickupRequestStatus,locations,customerName, Customerid);
                    databaseHelperClass.addPickupitem(pickupItemModal);
                    InsertOrder();

                }

            }
        });

    }
    private void BtnCancle(){
        BtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickupDeliveryActivity.this,MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
    private void AddINewItem() {
        AddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickupDeliveryActivity.this, AddNewItemActivity.class);
                intent.putExtra("selectType",PickupRequestServiceType);
               // intent.putExtra("serviceId",serviceId);
                Log.e("Passing Value", ""+selectedServiceId);

                intent.putExtra("serviceId",selectedServiceId);
                intent.putExtra("pickupid",pickupid);

                startActivity(intent);
            }
        });
    }
    private void ServicesApi() {

        Long num = Long.valueOf(0);
        Call<Services_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .SelectService(num);

        call.enqueue(new Callback<Services_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<Services_responce_modal> call, Response<Services_responce_modal> response) {

                if (response.isSuccessful()) {

                    Services_responce_modal services_responce_modal = response.body();

                    if (services_responce_modal.getSuccess()) {
                        services_data = services_responce_modal.getServiceList();
                        //String[] name1 = new String[services_data.size() ];
                        if (SelecteServiceId2==0) {
                            for (Services_data d : services_data) {
                                if (PickupRequestServiceName.equals(d.getServiceName())) {
                                    SelecteServiceId2 = d.getServiceId();
                                }
                            }
                        }

                        ArrayList<String> Servicename1 = new ArrayList<>();
                        ArrayList<String> ServiceId = new ArrayList<>();
                      //  String[] ServiceId = new String[services_data.size() ];
                        // ****************************************************

                        for (int i=0; i<services_data.size(); i++)
                        {
                            servicesData = (Services_data) services_data.get(i);
                            Log.e("inside for"+i, ""+servicesData.getServiceId());
                            if(SelecteServiceId2 == servicesData.getServiceId()){
                                Servicename1.add(""+servicesData.getServiceName());
                                ServiceId.add(String.valueOf(servicesData.getServiceId()));
                            }
                        }

                       /* Log.e("Arrayname", ""+name1[0]);
                        Log.e("ArrayID", ""+ServiceId[0]);*/

                        if (Servicename1.size()>0){
                        for (int i=1; i<services_data.size(); i++)
                        {
                            servicesData = (Services_data) services_data.get(i);
                            SeviceName = servicesData.getServiceName();
                            serviceId = servicesData.getServiceId();

                            if(SelecteServiceId2 != serviceId){
                                Servicename1.add(SeviceName);
                                ServiceId.add(String.valueOf(serviceId));
                            }
                        }}else {
                            for (int i=0; i<services_data.size(); i++)
                            {
                                servicesData = (Services_data) services_data.get(i);
                                SeviceName = servicesData.getServiceName();
                                serviceId = servicesData.getServiceId();
                                Servicename1.add(SeviceName);
                                ServiceId.add(String.valueOf(serviceId));
                            }

                        }

                        // ****************************************************

//                        //***********************removedDuplicates*******************
//                        Set<String> hashSet = new LinkedHashSet(Arrays.asList(Servicename1));
//                        ArrayList<String> removedDuplicates = new ArrayList(hashSet);
//                        //removedDuplicates.add(PickupRequestServiceName);
////                        if(removedDuplicates.contains(PickupRequestServiceName)){
////                            Log.i("Duplicate Item","Item Name : "+PickupRequestServiceName);
////                        }else{
////                            removedDuplicates.add(PickupRequestServiceName);
////                        }
//                        for(int i=0; i<removedDuplicates.size(); i++){
//                            Log.i("dup","******************"+removedDuplicates.size());
//                            removedDuplicates.remove(PickupRequestServiceName);
//                            removedDuplicates.add(PickupRequestServiceName);
//                        }
//                        Collections.reverse(removedDuplicates);
//                        //***********************removedDuplicates*******************

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, Servicename1);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Service.setAdapter(dataAdapter);
                        Service.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                if (SelecteServiceId2==0) {
                                    selectedServiceId = services_data.get(position).getServiceId();
                                    String serviceName = services_data.get(position).getServiceName();
                                    Log.e("ServiceNameandID", "**********" + selectedServiceId + serviceName);
                                }else {
                                    selectedServiceId = SelecteServiceId2;
                                }

                                //Toast.makeText(PickupDeliveryActivity.this, "id"+services_data.get(position).getServiceId(), Toast.LENGTH_SHORT).show();

                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    } else {
                        Toast.makeText(getApplicationContext(), services_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Services_responce_modal> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void InsertOrder(){

        gridTransList = new GridTrans[itemModalpickuplistList.size()];

        Log.i("itemModalpickuplistList",""+itemModalpickuplistList.size());

        for (int i=0; i<itemModalpickuplistList.size(); i++ ){
            Log.i("itemModalpickuplistList","******"+itemModalpickuplistList.size());

            itemModal = (ItemModal) itemModalpickuplistList.get(i);
            ItemName = itemModal.getItems();
            ItemQuntity = itemModal.getQty();
            ItemServiceId = itemModal.getODServiceId();
            ItemServiceType = itemModal.getODServiceType();
            ItemUnitPrice = itemModal.getUnitprice();
            ItemOrderSplCodeId = itemModal.getODSpecialInstruction();
            ItemPackType = itemModal.getODDeliveryPackType();
            Log.i("ItemPackType","******"+ItemServiceType);

            Itemcode = itemModal.getODItemCode();
            ItemDicriptions = itemModal.getDiscription();
            Log.i("ItemDicriptions","******"+ItemDicriptions);
            gridTransList[i] = new GridTrans("",Itemcode,Integer.parseInt(ItemQuntity),Integer.parseInt(ItemServiceId),ItemServiceType,Double.parseDouble(ItemUnitPrice),ItemDicriptions,"",ItemOrderSplCodeId,ItemPackType);
        }

        mainInsertOrderList = new MainInsertOrderList(InsertOrderDate,"",storeId,userId,"Pickup",Integer.parseInt(pickupid),Integer.parseInt(CustomerId),Integer.valueOf(SelecteServiceId2),PickupRequestServiceType,TotalQuantitys, gridTransList);
        mainInsertOrderList.setGridTrans(gridTransList);

        Call<InsertOrder_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .InsertOrder(token, UserName,mainInsertOrderList);

        call.enqueue(new Callback<InsertOrder_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<InsertOrder_responce_modal> call, Response<InsertOrder_responce_modal> response) {

                if (response.isSuccessful()) {

                    insertOrder_responce_modal = response.body();
                    insertOrder_data = insertOrder_responce_modal.getData();
                    boolean Success = insertOrder_responce_modal.getSuccess();
                    Log.i("Success", "*************Success************" + Success);

                    if (insertOrder_responce_modal.getSuccess()) {
                        OrderCode = insertOrder_data.getOrderCode();
                        Status =insertOrder_data.getStatus();

                        Intent intent = new Intent(PickupDeliveryActivity.this, PickupActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        Log.i("OrderCode","**********OrderCode***********"+OrderCode);
                        Log.i("Status","**********Status***********"+Status);
                        ConformationId.setText(OrderCode);
                        
                        sharedPrefManager.saveInserOrder(OrderCode,Status);

                        Toast.makeText(PickupDeliveryActivity.this, "Insert Order Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PickupDeliveryActivity.this, insertOrder_responce_modal.getErrorMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PickupDeliveryActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertOrder_responce_modal> call, Throwable t) {

                Toast.makeText(PickupDeliveryActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.order_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.notification:
                Toast.makeText(getApplicationContext(), "Notification", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.more_vertical:
                Toast.makeText(getApplicationContext(), "More_vertical", Toast.LENGTH_SHORT).show();
                return true;
//            case R.id.invite:
//                Toast.makeText(getApplicationContext(), "Invite", Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

         database = new SqliteDatabase(this);

        itemModalList =database.getPickupIdList();
        if (itemModalList.size()==0){
            Toast.makeText(this, "list is empty", Toast.LENGTH_SHORT).show();
        }
        else {
            int SearialNo=0;
            for (int i = 0; i < itemModalList.size(); i++) {
                if (pickupid.equals(itemModalList.get(i).getPickupId())){
                    checkItems=true;
                    Btn_Update.setEnabled(true);
                    ItemModal itemModal = (ItemModal) itemModalList.get(i);
                    dicriptions = itemModal.getDiscription();
                    amountss = Double.parseDouble(itemModal.getRate());
                    Log.i("amountss","*************"+amountss);
                    qty = Integer.parseInt(itemModal.getQty());
                    TotalQuantitys = TotalQuantitys + qty;
                    int Sno = itemModal.getSerialNo();
                    int id = itemModal.getId();
                    ItemServiceType = itemModal.getODServiceType();
                    ItemName = itemModal.getItems();
                    ItemQuntity = itemModal.getQty();
                    ItemServiceId = itemModal.getODServiceId();
                    ItemServiceType = itemModal.getODServiceType();
                    ItemUnitPrice = itemModal.getUnitprice();
                    ItemOrderSplCodeId = itemModal.getODSpecialInstruction();
                    ItemPackType = itemModal.getODDeliveryPackType();
                    Itemcode = itemModal.getODItemCode();
                    ItemDicriptions = itemModal.getDiscription();

                    TAMOUNT = TAMOUNT + amountss;
                    TotalAmount.setText("Rs " + TAMOUNT);
                    TotalQuantity.setText(String.valueOf(TotalQuantitys));
                    itemModalpickuplistList.add(new ItemModal(id,Sno,ItemName,ItemQuntity,String.valueOf(amountss),ItemUnitPrice,Itemcode,ItemServiceId,ItemServiceType,ItemOrderSplCodeId,ItemPackType));
                    adapter = new ItemAdapter(itemModalpickuplistList,PickupDeliveryActivity.this);
                    recyclerView.setAdapter(adapter);
                }


            }
            if (checkItems==false){
                Btn_Update.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Please Add new Items",Toast.LENGTH_LONG).show();
            }
        }

    }

}
