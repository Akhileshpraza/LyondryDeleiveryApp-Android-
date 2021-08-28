package com.example.lyondrydelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lyondrydelivery.Class.DecimalDigitsInputFilter;
import com.example.lyondrydelivery.Fragments.EditFragment;
import com.example.lyondrydelivery.Modal.ItemModal;
import com.example.lyondrydelivery.Modal.SelectOrderSpecialInstr.OrderSpecialInstr_data;
import com.example.lyondrydelivery.Modal.SelectOrderSpecialInstr.SelectOrderSpecialInstr_respoce_modal;
import com.example.lyondrydelivery.R;
import com.example.lyondrydelivery.RetrofiltClient.RetrofitClient;
import com.example.lyondrydelivery.SQLiteDB.SqliteDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditeAddNewItemsActivity extends AppCompatActivity {

    TextView BtnCancel, SerialNo,UnitPrice,GroupItem,Amount;
    EditText Quantity, Discription, ColorName, BrandName;
    Button BtnAdd, Btnupdate,TotalAmount;
    String gItem, amount, discription, quantity,ItemCode,ItemServiceId,ItemSeviceType,ItemSpecialInstruction,ItemPackType,ItemRemark;

    String groupItems,quantitys,amounts,colornames,brandnames,discriptions ,unitprices;
    String id;
    SqliteDatabase sqliteDatabase;
    Spinner SP_OrderSplInstruction;
    int OrderSplId,OrderSplIndId;
    RadioGroup radioGroup;
    RadioButton Folded,Hanger;
    List<OrderSpecialInstr_data> orderSpecialInstr_data;
    String OrderSplInstruction,Select_OrderSplInstruction,OrderSplInstructionName,OrderSplCodeId;
    String ItemPrices,TotalAmounts;
    int ItemQuantity =0;
    double ItemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_add_new_items);

        GroupItem =(TextView) findViewById(R.id.et_groupItem1);
        Quantity = (EditText)findViewById(R.id.et_qty1);
        Amount = (TextView) findViewById(R.id.et_rate1);
        Discription =(EditText) findViewById(R.id.et_discription1);
        Btnupdate = findViewById(R.id.btn_Update2);
        BtnCancel = findViewById(R.id.btn_cancle1);
        UnitPrice = findViewById(R.id.et_unitPrice);
        SP_OrderSplInstruction = findViewById(R.id.sp_OrderSplInstruction1);
        radioGroup = findViewById(R.id.rg_floded1);
        Folded =findViewById(R.id.rb_folded1);
        Hanger = findViewById(R.id.rb_hanger1);
        //TotalAmount = findViewById(R.id.totalAmount1);
        loadFragment2(new EditFragment(), false);

        sqliteDatabase = new SqliteDatabase(this);

        id = getIntent().getExtras().getString("ID");

        gItem =getIntent().getExtras().getString("ItemName");
        amount = getIntent().getExtras().getString("ItemAmount");
        quantity = getIntent().getExtras().getString("ItemQuantity");
        ItemPrices = getIntent().getExtras().getString("ItemUnitPrice");
        discription =getIntent().getExtras().getString("ItemDiscription");
        ItemCode = getIntent().getExtras().getString("ItemCode");
        ItemServiceId = getIntent().getExtras().getString("ItemServiceId");
        ItemSeviceType = getIntent().getExtras().getString("ItemSeviceType");
        ItemSpecialInstruction = getIntent().getExtras().getString("ItemSpecialInstruction");
        ItemPackType = getIntent().getExtras().getString("ItemPackType");
        ItemRemark = getIntent().getExtras().getString("ItemRemark");
        Log.i("ID","**********get Data******************"+id);
        Log.i("GROUPITEM",""+gItem);
        Log.i("AMOUNT",""+amount);
        Log.i("QUANTITY",""+quantity);
        Log.i("ItemPrices",""+ItemPrices);
        Log.i("DISCRIPTION",""+discription);
        Log.i("ItemCode",""+ItemCode);
        Log.i("ItemServiceId",""+ItemServiceId);
        Log.i("ItemSeviceType",""+ItemSeviceType);
        Log.i("ItemSpecialInstruction",""+ItemSpecialInstruction);
        Log.i("ItemPackType",""+ItemPackType);
        Log.i("ItemRemark",""+ItemRemark);

        GroupItem.setText(gItem);
        Amount.setText(amount);
        Quantity.setText(quantity);
        UnitPrice.setText(ItemPrices);
        Discription.setText(discription);

        Amount.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(6, 2)});

        ItemPackType =Folded.getText().toString();
        Log.i("PackType","********PackType**********"+ItemPackType);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId==R.id.rb_folded){
                    ItemPackType =Folded.getText().toString();
                }
                else{
                    ItemPackType =Hanger.getText().toString();
                }
            }
        });


        updateItem();
        OrderSpecialInstr();
        totalAmountBtn();

        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditeAddNewItemsActivity.this, PickupDeliveryActivity.class);
                startActivity(intent);
            }
        });

    }

    private void totalAmountBtn(){
        Quantity.setOnKeyListener(new View.OnKeyListener(){

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.i("KeyCode","********Key Code**********"+keyCode);
                if (event.getAction() == KeyEvent.ACTION_UP) {
                    if ((keyCode >= 7 && keyCode <= 16) || keyCode == 67) {
                        if (Quantity.getText() != null && Quantity.getText().toString().trim().length() > 0) {
                            ItemQuantity = Integer.parseInt(Quantity.getText().toString().trim());
                            ItemPrice = Double.parseDouble(UnitPrice.getText().toString().trim());
                            TotalAmounts = String.valueOf(Integer.valueOf(ItemQuantity) * ItemPrice);
                            Amount.setText(TotalAmounts);
                            return true;
                        }
                    }
                }
                return false;

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

    //*********************************************Update Items******************************
    public void updateItem() {

        Btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupItems = GroupItem.getText().toString().trim();
                quantitys = Quantity.getText().toString().trim();
                amounts = Amount.getText().toString().trim();
                unitprices = UnitPrice.getText().toString().trim();
                discriptions = Discription.getText().toString().trim();

                Log.i("ID","**********GetDAtaAll******************"+id);
                Log.i("GROUPITEM",""+groupItems);
                Log.i("AMOUNT",""+amounts);
                Log.i("QUANTITY",""+quantitys);
//                Log.i("COLOR",""+colornames);
//                Log.i("BRAND",""+brandnames);
                Log.i("DISCRIPTION",""+discriptions);

                if (groupItems.isEmpty()) {
                    GroupItem.setError("GroupItem can't be blank");
                    GroupItem.requestFocus();
                    return;
                }
                else if (quantitys.isEmpty()) {
                    Quantity.setError("Quantity can't be blank");
                    Quantity.requestFocus();
                    return;

                }
                else if (quantitys.startsWith("0")) {
                    Quantity.setError("Quantity can't be Zero");
                    Quantity.requestFocus();
                    return;
                }
                else if (amounts.isEmpty()) {
                    Amount.setError("Amount can't be blank");
                    Amount.requestFocus();
                    return;
                }
                else if (amounts.startsWith("0")) {
                    Amount.setError("Amount can't be Zero");
                    Amount.requestFocus();
                    return;
                }
                else if (unitprices.isEmpty()){
                    UnitPrice.setError("UnitPrice can't be blank");
                    return;
                }
//                else if (discriptions.isEmpty()) {
//                    Discription.setError("Discription can't be blank");
//                    Discription.requestFocus();
//                    return;
//                }

                boolean dbupdate = sqliteDatabase.updateItem(String.valueOf(id), groupItems,quantitys,amounts,unitprices,ItemCode,ItemServiceId,"",ItemSpecialInstruction,ItemPackType,"",discriptions);
                if (dbupdate == true){
                    Intent i = new Intent(getApplicationContext(), PickupDeliveryActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Items update Successfuly ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Items Can not update ", Toast.LENGTH_SHORT).show();
                }

            }
        });



//        ItemModal itemModal = itemModalList.get(Integer.parseInt(id));
//        if (GroupItem !=null) {
//            SqliteDatabase sqliteDatabase = new SqliteDatabase(EditeAddNewItemsActivity.this);
//            sqliteDatabase.updateItems(new ItemModal(itemModal.getId(), gItem, quantity, amount, colorname, brandname, discription));
//            Intent i = new Intent(getApplicationContext(), PickupDeliveryActivity.class);
//            startActivity(i);
//            Toast.makeText(getApplicationContext(), "Items update Successfuly ", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getApplicationContext(), "Items Can not update ", Toast.LENGTH_SHORT).show();
//        }

    }
    //*********************************************Update Items******************************

    //********************************** Camera Image Picker*********Pick Image Clicked Items***********************

    public void loadFragment2(Fragment fragment, Boolean bool) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout1, fragment);
        if (bool)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    //********************************** Camera Image Picke*********Pick Image Clicked Items***********************
}

