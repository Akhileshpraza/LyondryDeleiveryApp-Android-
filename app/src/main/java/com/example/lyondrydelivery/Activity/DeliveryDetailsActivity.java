package com.example.lyondrydelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyondrydelivery.Adapter.DeliveryAdapter;
import com.example.lyondrydelivery.Adapter.DeliveryDetailsAdapter;
import com.example.lyondrydelivery.Adapter.ItemAdapter;
import com.example.lyondrydelivery.Adapter.PickupCompliteListShowAdapter;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Modal.DeliveryDetailsItemModal;
import com.example.lyondrydelivery.Modal.InviceMaster.GridTran_data_invice;
import com.example.lyondrydelivery.Modal.InviceMaster.InvoiceMaster_resonce_modal;
import com.example.lyondrydelivery.Modal.InviceMaster.MainInviceMaster_reponce_modal;
import com.example.lyondrydelivery.Modal.ItemModal;
import com.example.lyondrydelivery.Modal.PickupComplite.PickupComplete_data;
import com.example.lyondrydelivery.Modal.PickupComplite.PickupComplite_responce_modal;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_data;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_responce_modal;
import com.example.lyondrydelivery.Modal.UpdateDeliveryStatus.UpdateDeliveryStatus_responce_modal;
import com.example.lyondrydelivery.R;
import com.example.lyondrydelivery.RetrofiltClient.RetrofitClient;
import com.example.lyondrydelivery.SQLiteDB.SqliteDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryDetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DeliveryDetailsAdapter adapter;
    EditText DeliveryOtp,Remarks;
    List<DeliveryDetailsItemModal>deliveryDetailsItemModalList;
    List<ItemModal> itemModalList=new ArrayList<>();
    List<ItemModal> itemModalpickuplistList=new ArrayList<>();
    SqliteDatabase database;
    Button BTN_Next,BtnVerifyDeliveryOtp,BtnDeliver;
    TextView PickupId,PickupDate,PickupAddress,BtnCancle;
    TextView TotalRate,GST,DeliveryCharge,NetAmount,AmountPaid,BalancePayable,RoundOff,Discount;
    RadioGroup radioGroup;
    RadioButton Cashpayment,DebitCard;
    SharedPrefManager sharedPrefManager;
    String token, UserName;
    String address,date,pickupid,deliveryOtp;
    InvoiceMaster_resonce_modal invoiceMaster_resonce_modals;
    GridTran_data_invice gridTran_data_invice1;
    List<GridTran_data_invice> gridTran_data_invice;

    String pickupRequestStoreName,pickupRequestAddress,pickupRequestDate,pickupRequestTime,pickupRequestStatus,locations,customerName,Customerid;
    String ItemName,ItemQuntity,ItemServiceId,ItemServiceType,ItemUnitPrice,ItemOrderSplCodeId,ItemPackType,Itemcode,ItemDicriptions;
    String DeliveryPickupId,DeliveryAddress,DeliveryCustomerName,DeliveryDate,DeliveryOTP,DeliveryRequestTotalAmount,DeliveryRequestTotalTax,DeliveryRequestTotalCharge,DeliveryRequestTotalDiscount,DeliveryRequestTotalRoundOff,DeliveryRequestNetAmount;
    Double amountss;
    Boolean checkItems = false;
    SharedPreferences sharedPreferences;
    List<PickupComplete_data> pickupComplete_data=new ArrayList<>();
    String app = "D";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        //back arrow enable in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setTitle("Delivery Details");
        BtnDeliver = findViewById(R.id.btndeliver);
        BtnCancle = findViewById(R.id.btncancle);
        recyclerView = findViewById(R.id.items_recycleView);
        PickupId = findViewById(R.id.pickupid);
        PickupDate = findViewById(R.id.pickupdate);
        PickupAddress = findViewById(R.id.pickupaddress);
        DeliveryOtp = findViewById(R.id.delivery_otp);
        radioGroup = findViewById(R.id.rg_payment);
        Cashpayment = findViewById(R.id.rb_cash);
        //DebitCard = findViewById(R.id.rb_creditcard);
        TotalRate = findViewById(R.id.tv_total_rate);
        GST = findViewById(R.id.tv_gst);
        DeliveryCharge = findViewById(R.id.tv_delivery_charge);
        NetAmount =findViewById(R.id.tv_net_amount);
        AmountPaid = findViewById(R.id.tv_amount_paid);
        BalancePayable = findViewById(R.id.tv_balance_payable);
        RoundOff = findViewById(R.id.tv_roundof);
        Discount = findViewById(R.id.tv_discount);
        Remarks = findViewById(R.id.et_remarks);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        DeliveryPickupId=  sharedPrefManager.getPref("PickuId",getApplicationContext());
        DeliveryAddress= sharedPrefManager.getPref("Address",getApplicationContext());
        DeliveryCustomerName= sharedPrefManager.getPref("CustomerName",getApplicationContext());
        DeliveryDate= sharedPrefManager.getPref("PickupRequestDate",getApplicationContext());
        DeliveryOTP= sharedPrefManager.getPref("DeliveryOTP",getApplicationContext());
        DeliveryRequestTotalAmount= sharedPrefManager.getPref("DeliveryRequestTotalAmount",getApplicationContext());
        DeliveryRequestTotalTax= sharedPrefManager.getPref("DeliveryRequestTotalTax",getApplicationContext());
        DeliveryRequestTotalCharge= sharedPrefManager.getPref("DeliveryRequestTotalCharge",getApplicationContext());
        DeliveryRequestTotalDiscount= sharedPrefManager.getPref("DeliveryRequestTotalDiscount",getApplicationContext());
        Log.e("DeliveryOTP","**************"+DeliveryOTP);
        DeliveryRequestTotalRoundOff= sharedPrefManager.getPref("DeliveryRequestTotalRoundOff",getApplicationContext());
        DeliveryRequestNetAmount= sharedPrefManager.getPref("DeliveryRequestNetAmount",getApplicationContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        UserName = sharedPrefManager.getLoginUser().getUserName();

        PickupId.setText(DeliveryPickupId);
        PickupDate.setText(DeliveryDate);
        PickupAddress.setText(DeliveryAddress);
        TotalRate.setText(String.valueOf(DeliveryRequestTotalAmount));
        GST.setText(String.valueOf(DeliveryRequestTotalTax));
        DeliveryCharge.setText(String.valueOf(DeliveryRequestTotalCharge));
        NetAmount.setText(String.valueOf(DeliveryRequestNetAmount));
        RoundOff.setText(String.valueOf(DeliveryRequestTotalRoundOff));
        Discount.setText(String.valueOf(DeliveryRequestTotalDiscount));

        //setRecyclerView();
        BtnCancle();
        BtnDelivery();
        //InviceMaster();
        DeliveryOtpVerify();
       // ItemDetials();
        PickupCompleteItems();
    }

    private void DeliveryOtpVerify() {

        DeliveryOtp.setOnKeyListener(new View.OnKeyListener(){

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP)) {
                    String otp = DeliveryOtp.getText().toString();
                    if (otp.length()==6){

                        if (otp.equals(DeliveryOTP)){
                            Toast.makeText(DeliveryDetailsActivity.this, "OTP Verify", Toast.LENGTH_SHORT).show();
                            BtnDeliver.setEnabled(true);
                        }
                        else {
                            Toast.makeText(DeliveryDetailsActivity.this, "OTP not Verify", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
                        //Toast.makeText(DeliveryDetailsActivity.this, "OTP max 6 digits", Toast.LENGTH_SHORT).show();
                    }

                    return true;
                }
                return false;
            }
        });

    }

    private void BtnCancle(){
        BtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveryDetailsActivity.this,DeliveryActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
    private void BtnDelivery(){
        BtnDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),GroupItem,Toast.LENGTH_SHORT).show();
                UpdateDeliveredStatus();
            }
        });
    }

    private void PickupCompleteItems() {

        Call<PickupComplite_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .PickupComplete( app,token, UserName, Integer.parseInt(DeliveryPickupId));

        call.enqueue(new Callback<PickupComplite_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<PickupComplite_responce_modal> call, Response<PickupComplite_responce_modal> response) {

                if (response.isSuccessful()) {
                    PickupComplite_responce_modal pickupComplite_responce_modal = response.body();

                    if (pickupComplite_responce_modal.getSuccess()) {

                        pickupComplete_data = pickupComplite_responce_modal.getDeliveryDetailsList();
                        adapter = new DeliveryDetailsAdapter(pickupComplete_data, getApplicationContext());
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
//    public void setRecyclerView() {
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//        database = new SqliteDatabase(this);
//        itemModalList =database.getPickupIdList();
//        if (itemModalList.size()==0){
//            Toast.makeText(this, "list is empty", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            for (int i = 0; i < itemModalList.size(); i++) {
//                if (DeliveryPickupId.equals(itemModalList.get(i).getPickupId())){
//                    checkItems=true;
//                    ItemModal itemModal = (ItemModal) itemModalList.get(i);
//                    amountss = Double.parseDouble(itemModal.getRate());
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
//                    int Sno = itemModal.getSerialNo();
//                    int id = itemModal.getId();
//
//
//                    Log.i("PICKUPID", "********PICKUPID**********" + itemModal.getPickupId());
//                    itemModalpickuplistList.add(new ItemModal(id,Sno,ItemName,ItemQuntity,String.valueOf(amountss),ItemUnitPrice,Itemcode,ItemServiceId,ItemServiceType,ItemOrderSplCodeId,ItemDicriptions));
//                    adapter = new DeliveryDetailsAdapter(itemModalpickuplistList,DeliveryDetailsActivity.this);
//                    recyclerView.setAdapter(adapter);
//                }
//
//
//            }
//            if (checkItems==false){
//                Toast.makeText(getApplicationContext(),"Please Add new Items",Toast.LENGTH_LONG).show();
//            }
//        }
//
//
//
////        for (int i=0; i<itemModalList.size(); i++){
////            ItemModal itemModal = (ItemModal) itemModalList.get(i);
////            double amountss = Double.parseDouble(itemModal.getRate());
////            Log.i("amountss","*********amountss**********"+amountss);
////        }
////
////        if (itemModalList.size() > 0){
////            adapter = new DeliveryDetailsAdapter(itemModalList,DeliveryDetailsActivity.this);
////            recyclerView.setAdapter(adapter);
////        }else {
////           // Toast.makeText(this, "There is no items plz add new Items", Toast.LENGTH_SHORT).show();
////        }
//    }

    private void InviceMaster() {
        Call<MainInviceMaster_reponce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .InviceMaster(token, UserName);

        call.enqueue(new Callback<MainInviceMaster_reponce_modal>() {
            @Override
            public void onResponse(@NotNull Call<MainInviceMaster_reponce_modal> call, Response<MainInviceMaster_reponce_modal> response) {

                if (response.isSuccessful()) {
                    MainInviceMaster_reponce_modal  mainInviceMaster_reponce_modal = response.body();

                    if (mainInviceMaster_reponce_modal.getSuccess()) {
                        invoiceMaster_resonce_modals = mainInviceMaster_reponce_modal.getInvoiceMaster();
                       String InvoiceNo = invoiceMaster_resonce_modals.getInvoiceNo();
                       String InvoiceDateString =invoiceMaster_resonce_modals.getInvoiceDateString();
                       int InvoiceCustomerId =invoiceMaster_resonce_modals.getInvoiceCustomerId();
                       String InvoiceCustomerCode =invoiceMaster_resonce_modals.getInvoiceCustomerCode();
                       String InvoiceCustomerGstNo =invoiceMaster_resonce_modals.getInvoiceCustomerGstNo();
                       int InvoiceRequestId =invoiceMaster_resonce_modals.getInvoiceRequestId();
                       String InvoiceOrderNo =invoiceMaster_resonce_modals.getInvoiceOrderNo();
                       Double InvoiceTotalAmount =invoiceMaster_resonce_modals.getInvoiceTotalAmount();
                       Double InvoiceDiscountPercentage =invoiceMaster_resonce_modals.getInvoiceDiscountPercentage();
                       Double InvoiceDiscountAmount =invoiceMaster_resonce_modals.getInvoiceDiscountAmount();
                       Double InvoiceTaxPercentage =invoiceMaster_resonce_modals.getInvoiceTaxPercentage();
                       Double InvoiceCgst =invoiceMaster_resonce_modals.getInvoiceCgst();
                       Double InvoiceSgst =invoiceMaster_resonce_modals.getInvoiceSgst();
                       Double InvoiceIgst =invoiceMaster_resonce_modals.getInvoiceIgst();
                       Double InvoiceTotalTax =invoiceMaster_resonce_modals.getInvoiceTotalTax();
                       Double InvoiceRoundOff =invoiceMaster_resonce_modals.getInvoiceRoundOff();
                       Double InvoiceNetAmount =invoiceMaster_resonce_modals.getInvoiceNetAmount();
                       String InvoicePickupBy =invoiceMaster_resonce_modals.getInvoicePickupBy();

                       gridTran_data_invice =  invoiceMaster_resonce_modals.getGridTrans();

                       for (int i =0; i<gridTran_data_invice.size(); i++){
                           gridTran_data_invice1 = (GridTran_data_invice)gridTran_data_invice.get(i);
                           String InvoiceTranInvoiceNo = gridTran_data_invice1.getInvoiceTranInvoiceNo();
                           int InvoiceTranSlNo = gridTran_data_invice1.getInvoiceTranSlNo();
                           String InvoiceTranItemCode = gridTran_data_invice1.getInvoiceTranItemCode();
                           Double InvoiceTranItemUnitPrice = gridTran_data_invice1.getInvoiceTranItemUnitPrice();
                           int InvoiceTranItemUomId = gridTran_data_invice1.getInvoiceTranItemUomId();
                           int InvoiceTranItemNos = gridTran_data_invice1.getInvoiceTranItemNos();
                           Double InvoiceTranAmount = gridTran_data_invice1.getInvoiceTranAmount();
                           Double InvoiceTranDiscPercentage = gridTran_data_invice1.getInvoiceTranDiscPercentage();
                           Double InvoiceTranDiscount = gridTran_data_invice1.getInvoiceTranDiscount() ;
                           Double InvoiceTranTaxPercentage = gridTran_data_invice1.getInvoiceTranTaxPercentage();
                           Double InvoiceTranCgst = gridTran_data_invice1.getInvoiceTranCgst();
                           Double InvoiceTranSgst = gridTran_data_invice1.getInvoiceTranSgst();
                           Double InvoiceTranIgst = gridTran_data_invice1.getInvoiceTranIgst();
                           Double InvoiceTranTotalTax = gridTran_data_invice1.getInvoiceTranTotalTax();
                           Double InvoiceTranNetAmount = gridTran_data_invice1.getInvoiceTranNetAmount();

                       }

                    }




                     else {
                        Toast.makeText(getApplicationContext(), mainInviceMaster_reponce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MainInviceMaster_reponce_modal> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void UpdateDeliveredStatus() {
        String remakes = Remarks.getText().toString();


        Call<UpdateDeliveryStatus_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .UpdateDeliveredStatus(token, UserName, Integer.valueOf(DeliveryPickupId),"COD","COD",remakes);

        call.enqueue(new Callback<UpdateDeliveryStatus_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<UpdateDeliveryStatus_responce_modal> call, Response<UpdateDeliveryStatus_responce_modal> response) {

                if (response.isSuccessful()) {
                    UpdateDeliveryStatus_responce_modal updateDeliveryStatus_responce_modal = response.body();

                    Integer PickupRequestId = updateDeliveryStatus_responce_modal.getPickupRequestId();
                    Log.i("getPickupRequestId", "*************************" + PickupRequestId);
                    String ErrorMassage = updateDeliveryStatus_responce_modal.getErrorMessage();
                    if (ErrorMassage.isEmpty()) {
                        Intent intent = new Intent(DeliveryDetailsActivity.this, DeliveryActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(DeliveryDetailsActivity.this, ""+ErrorMassage, Toast.LENGTH_SHORT).show();
                    }
                }

                   else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateDeliveryStatus_responce_modal> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}