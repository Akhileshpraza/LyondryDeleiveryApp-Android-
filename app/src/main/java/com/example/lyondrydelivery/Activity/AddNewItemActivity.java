package com.example.lyondrydelivery.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyondrydelivery.Adapter.HomeAdapter;
import com.example.lyondrydelivery.Adapter.ItemAdapter;
import com.example.lyondrydelivery.Adapter.SearchAdapter;
import com.example.lyondrydelivery.Class.DecimalDigitsInputFilter;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Class.SharedPreference;
import com.example.lyondrydelivery.Fragments.CameraFragment;
import com.example.lyondrydelivery.Handler.RecyclerTouchListener;
import com.example.lyondrydelivery.Modal.ItemModal;
import com.example.lyondrydelivery.Modal.SelectItem.SelectItem_responce_modal;
import com.example.lyondrydelivery.Modal.SelectItem.SelecteItem_data;
import com.example.lyondrydelivery.Modal.SelectItem.SelecteItem_data_temp;
import com.example.lyondrydelivery.Modal.SelectOrderSpecialInstr.OrderSpecialInstr_data;
import com.example.lyondrydelivery.Modal.SelectOrderSpecialInstr.SelectOrderSpecialInstr_respoce_modal;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_data;
import com.example.lyondrydelivery.Modal.Services.Services_data;
import com.example.lyondrydelivery.Modal.Services.Services_responce_modal;
import com.example.lyondrydelivery.R;
import com.example.lyondrydelivery.RetrofiltClient.RetrofitClient;
import com.example.lyondrydelivery.SQLiteDB.SqliteDatabase;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewItemActivity extends AppCompatActivity {

    TextView BtnCancel, SerialNo,UintPrice,GroupItem,Amount;
    EditText  Quantity, Discription;
    Button BtnAdd,TotalAmount;
    RecyclerView recyclerView;
    EditText searchView;
    String gItem, amount, discription, quantity,unitprice;
    SelectItem_responce_modal selectItem_responce_modal;
    List<SelecteItem_data> selecteItem_data;

    List<SelecteItem_data_temp> selecteItem_data_temp=new ArrayList<>();
    SearchAdapter adapter;
    String SelecteType,ItemCode,ItemSelectType,ItemPrices;
    String TotalAmounts=null;
    int ItemQuantity=0;
    int serviceId=0;
    int ItemService;
    double ItemPrice;
    boolean recycler_bool=false;

    List<OrderSpecialInstr_data> orderSpecialInstr_data;
    String OrderSplInstruction,Select_OrderSplInstruction,OrderSplInstructionName,OrderSplCodeId;
    Spinner SP_OrderSplInstruction;
    int OrderSplId,OrderSplIndId;
    RadioGroup radioGroup;
    RadioButton Folded,Hanger;
    String PackType,Pickupid;

    SharedPreference sharedPreference;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);
        GroupItem = findViewById(R.id.et_groupItem);
        UintPrice = findViewById(R.id.et_unitPrice);
        Quantity = findViewById(R.id.et_qty);
        Amount = findViewById(R.id.et_rate);
        Discription = findViewById(R.id.et_discription);
        BtnAdd = findViewById(R.id.btn_add);
        BtnCancel = findViewById(R.id.btn_cancle1);
        //TotalAmount = findViewById(R.id.totalAmount);
        SP_OrderSplInstruction = findViewById(R.id.sp_OrderSplInstruction);
        radioGroup = findViewById(R.id.rg_floded);
        Folded =findViewById(R.id.rb_folded);
        Hanger = findViewById(R.id.rb_hanger);
        SerialNo = findViewById(R.id.snumber);
        searchView = findViewById(R.id.sv_searchView);
        //searchView.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        recyclerView = findViewById(R.id.recycleView_search);
        recyclerView.setVisibility(View.GONE);

        loadFragment(new CameraFragment(), false);
        //amount validation
        Amount.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(6, 2)});

        SelecteType =getIntent().getExtras().getString("selectType");
        serviceId = getIntent().getIntExtra("serviceId",0);
        Pickupid = getIntent().getStringExtra("pickupid");



        Log.i("SelecteType","**********SelecteType****************"+SelecteType);
        Log.i("Pickupid","**********Pickupid0909****************"+Pickupid);
        Log.e("serviceId","**********serviceIdserviceId****************"+serviceId);
        Log.i("SelecteType","**********SelecteTypeSelecteType****************"+SelecteType);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {

                LinearLayout ll_main;
                ll_main=view.findViewById(R.id.ll_main);
                ll_main.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recycler_bool=true;
                        recyclerView.setVisibility(View.GONE);
                        searchView.setText(selecteItem_data_temp.get(position).getItemName());
                        GroupItem.setText(selecteItem_data_temp.get(position).getItemName());
                        ItemPrice= selecteItem_data_temp.get(position).getItemPrice();

                        UintPrice.setText(String.valueOf(selecteItem_data_temp.get(position).getItemPrice()));
                        ItemCode = selecteItem_data_temp.get(position).getItemCode();
                        ItemService = selecteItem_data_temp.get(position).getItemService();
                        ItemSelectType = selecteItem_data_temp.get(position).getItemServiceType();
                        Log.e("GroupItemss", "" + selecteItem_data_temp.get(position).getItemName());

                        Log.i("ItemServiceType","**********ItemPrice*******"+ItemPrice);

                    }
                });

            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(searchView.getText().toString().equals(""))
                {
                    recyclerView.setVisibility(View.GONE);
                }
                else {
                    if(recycler_bool==true)
                    {
                        recycler_bool=false;
                        recyclerView.setVisibility(View.GONE);
                    }
                    else {

                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
                    adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        PackType =Folded.getText().toString();
        Log.i("PackType","********PackType**********"+PackType);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId==R.id.rb_folded){
                    PackType =Folded.getText().toString();
                }
                else{
                    PackType =Hanger.getText().toString();
                }
            }
        });

        SelectItem();
        OrderSpecialInstr();
       // totalAmountBtn();
        cancleBtn();
        Add();

        Quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (Quantity.getText() != null && Quantity.getText().toString().length() > 0) {
                    ItemQuantity = Integer.parseInt(Quantity.getText().toString());
                    ItemPrices = UintPrice.getText().toString().trim();
                    TotalAmounts = String.valueOf(Integer.valueOf(ItemQuantity) * ItemPrice);
                    Amount.setText(TotalAmounts);
                }
                else{
                    Amount.setText(String.valueOf("0.00"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });

    }

    private void totalAmountBtn(){
//        Quantity.setOnKeyListener(new View.OnKeyListener(){
//
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                Log.e("KeyCode","********Key Code**********"+keyCode);
//                if (event.getAction() == KeyEvent.ACTION_MULTIPLE) {
//                    if ((keyCode >= 7 && keyCode <= 16) || keyCode == 67) {
//                        if (Quantity.getText() != null && Quantity.getText().toString().trim().length() > 0) {
//                            ItemQuantity = Integer.parseInt(Quantity.getText().toString().trim());
//                            ItemPrices = UintPrice.getText().toString().trim();
//                            TotalAmounts = String.valueOf(Integer.valueOf(ItemQuantity) * ItemPrice);
//                            Amount.setText(TotalAmounts);
//                            return true;
//                        }
//                    }
//                }
//                return false;

            //}
        //});


    }
    private void cancleBtn(){
        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewItemActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void OrderSpecialInstr() {

        String OrderSplCode= String.valueOf(0);
        Call<SelectOrderSpecialInstr_respoce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .OrderSpecialInstr(OrderSplCode);

        call.enqueue(new Callback<SelectOrderSpecialInstr_respoce_modal>() {
            @Override
            public void onResponse(@NotNull Call<SelectOrderSpecialInstr_respoce_modal> call, Response<SelectOrderSpecialInstr_respoce_modal> response) {

                if (response.isSuccessful()) {

                    SelectOrderSpecialInstr_respoce_modal selectOrderSpecialInstr_respoce_modal = response.body();

                    if (selectOrderSpecialInstr_respoce_modal.getSuccess()) {
                        orderSpecialInstr_data = selectOrderSpecialInstr_respoce_modal.getOrderSpecialInstrList();
                        OrderSpecialInstr_data orderSpecialInstrData;
                        String[] SpecialInstrName = new String[orderSpecialInstr_data.size() ];
                        String[] id1 = new String[orderSpecialInstr_data.size() ];
                        for (int i=0; i<orderSpecialInstr_data.size(); i++)
                        {
                            orderSpecialInstrData = (OrderSpecialInstr_data) orderSpecialInstr_data.get(i);
                            OrderSplInstruction = orderSpecialInstrData.getOrderSplInstruction();
                            SpecialInstrName[i]=OrderSplInstruction;

                            OrderSplIndId = orderSpecialInstrData.getOrderSplId();
                            id1[i]= String.valueOf(OrderSplIndId);
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, SpecialInstrName);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        SP_OrderSplInstruction.setAdapter(dataAdapter);
                        SP_OrderSplInstruction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                OrderSplId = orderSpecialInstr_data.get(position).getOrderSplId();
                                OrderSplCodeId = orderSpecialInstr_data.get(position).getOrderSplCode();
                                OrderSplInstructionName =orderSpecialInstr_data.get(position).getOrderSplInstruction();
                                Select_OrderSplInstruction = String.valueOf(SP_OrderSplInstruction.getSelectedItemPosition());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    } else {
                        Toast.makeText(getApplicationContext(), selectOrderSpecialInstr_respoce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SelectOrderSpecialInstr_respoce_modal> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //*************************************Add new Items*********************************
    private void Add() {
        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gItem = GroupItem.getText().toString();
                quantity = Quantity.getText().toString();
                amount = Amount.getText().toString();
                unitprice = UintPrice.getText().toString();
                discription = Discription.getText().toString();

                if (gItem.isEmpty()) {
                    GroupItem.setError("GroupItem can't be blank");
                    GroupItem.requestFocus();
                    return;
                }
                else if (quantity.isEmpty()) {
                    Quantity.setError("Quantity can't be blank");
                    Quantity.requestFocus();
                    return;

                }
                else if (quantity.startsWith("0")) {
                    Quantity.setError("Quantity can't be Zero");
                    Quantity.requestFocus();
                    return;
                }

                else if (unitprice.isEmpty()){
                    UintPrice.setError("UnitPrice can't be blank");
                    return;
                }
//                else if (discription.isEmpty()) {
//                    Discription.setError("Discription can't be blank");
//                    Discription.requestFocus();
//                    return;
//                }

                else if (amount.isEmpty()) {
                    Amount.setError("Amount can't be blank");
                    Amount.requestFocus();
                    return;
                }
                else if (amount.startsWith("0")) {
                    Amount.setError("Amount can't be Zero");
                    Amount.requestFocus();
                    return;
                }


                else {

                    SqliteDatabase databaseHelperClass = new SqliteDatabase(AddNewItemActivity.this);
                    ItemModal itemModal = new ItemModal(gItem, quantity, amount,unitprice,ItemCode,String.valueOf(ItemService),ItemSelectType,OrderSplCodeId,PackType,"Hello", discription,Pickupid);
                    Intent i = new Intent(getApplicationContext(), PickupDeliveryActivity.class);
                    i.putExtra("ServiceId2",serviceId);

                    startActivity(i);
                    databaseHelperClass.addItems(itemModal);
                    Toast.makeText(AddNewItemActivity.this, "Add New Items Successfully", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    //*************************************Add new Items*********************************

    //********************************** Camera Image Picker*********Pick Image Clicked Items***********************

    public void loadFragment(Fragment fragment, Boolean bool) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        if (bool)
            transaction.addToBackStack(null);
        transaction.commit();
    }
    //********************************** Camera Image Picke*********Pick Image Clicked Items***********************


    private void SelectItem() {

        int num = 0;
        Call<SelectItem_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .SelectItem(num);

        call.enqueue(new Callback<SelectItem_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<SelectItem_responce_modal> call, Response<SelectItem_responce_modal> response) {

                if (response.isSuccessful()) {
                    selectItem_responce_modal =response.body();

                    if (selectItem_responce_modal.getSuccess()) {
                        selecteItem_data=selectItem_responce_modal.getItemList();
                        for (int i=0;i<selecteItem_data.size();i++) {
                            //Log.e("SelectItems", "" + selecteItem_data.get(i).getItemName());
                            //Log.e("getItemServiceType", "" + selecteItem_data.get(i).getItemServiceType());
                        }
                        for (int i = 0; i < selecteItem_data.size(); i++)
                        {
                            if(serviceId==(selecteItem_data.get(i).getItemService()))
                            {
                                int id=selecteItem_data.get(i).getItemService();
                                String tye=selecteItem_data.get(i).getItemServiceType();
                                Log.v("Detaikssss","***:"+SelecteType+" Type:"+tye);
                                if(SelecteType.equals("Ragular"))
                                {
                                    SelecteType="Regular";
                                }

                                if(SelecteType.equals(selecteItem_data.get(i).getItemServiceType()))
                                {
                                    Log.v("itemlistss","***Items:"+selecteItem_data.get(i).getItemName());
                                selecteItem_data_temp.add(new SelecteItem_data_temp(selecteItem_data.get(i).getItemName(),selecteItem_data.get(i).getItemServiceType(),selecteItem_data.get(i).getItemPrice(),selecteItem_data.get(i).getItemCode(),selecteItem_data.get(i).getItemService()));
                                }
                            }
                        }
                        Log.v("itemlistss","***"+selecteItem_data_temp.size());
                        adapter = new SearchAdapter(selecteItem_data_temp, getApplicationContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                    else {
                        Toast.makeText(AddNewItemActivity.this, selectItem_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddNewItemActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SelectItem_responce_modal> call, Throwable t) {

                Toast.makeText(AddNewItemActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

