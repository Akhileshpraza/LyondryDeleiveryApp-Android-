package com.example.lyondrydelivery.Class;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.lyondrydelivery.Modal.InsertOrder.InsertOrder_data;
import com.example.lyondrydelivery.Modal.Login_data;
import com.example.lyondrydelivery.Modal.Login_responce_modal;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_data;
import com.example.lyondrydelivery.Modal.SelecteDelivery.DeliverySchedualedList_Data;


import static android.content.Context.MODE_PRIVATE;

public class SharedPrefManager {

    private static String SHARED_PREF_NAME = "lyondry_delivery";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context;
    }


    public void saveUserLogin(Login_data loginData) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("UserName", loginData.getUserName());
        editor.putString("UserPassword", loginData.getUserPassword());
        editor.putInt("UserStoreId",loginData.getUserStoreId());
        editor.putInt("UserId",loginData.getUserID());
        editor.putBoolean("logged", true);
        editor.apply();
    }

    public Login_data getLoginUser() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return new Login_data(sharedPreferences.getString("UserName", null),
                sharedPreferences.getString("UserPassword", null),
                sharedPreferences.getInt("UserStoreId",0),
                sharedPreferences.getInt("UserId",0));
    }


    public void saveToken(Login_responce_modal login_responce_modal) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("HttpResponseHeader", login_responce_modal.getHttpResponseHeader());
        editor.apply();
    }

    public Login_responce_modal getTokenOtp() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return new Login_responce_modal(sharedPreferences.getString("HttpResponseHeader", null));
    }

    public void saveSelectPickupDetails(int PickuId, String Address, Integer CustomerId
            , String CustomerName, String PickupRequestDate, String PickupRequestServiceType, String PickupRequestServiceName,
                                        String pickupRequestStoreName, String pickupRequestTime, String pickupRequestStatus, String locations,String DueDate) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt("PickuId", PickuId);
        editor.putString("Address", Address);
        editor.putInt("CustomerId",CustomerId);
        editor.putString("CustomerName",CustomerName);
        editor.putString("PickupRequestDate",PickupRequestDate);
        editor.putString("PickupRequestServiceType",PickupRequestServiceType);
        editor.putString("PickupRequestServiceName",PickupRequestServiceName);
        editor.putString("PickupRequestStoreName",pickupRequestStoreName);
        editor.putString("PickupRequestTime",pickupRequestTime);
        editor.putString("PickupRequestStatus",pickupRequestStatus);
        editor.putString("Location",locations);
        editor.putString("DueDate",DueDate);
        editor.apply();
    }


//    public void saveSelectPickupDetails(SelectPickupDelivery_data selectPickupDeliveryData) {
//        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//        editor.putInt("PickuId", selectPickupDeliveryData.getPickupRequestId());
//        editor.putString("Address", selectPickupDeliveryData.getPickupRequestAddress());
//        editor.putString("CustomerId",selectPickupDeliveryData.getCustomerId());
//        editor.putString("CustomerName",selectPickupDeliveryData.getCustomerName());
//        editor.putString("PickupRequestDate",selectPickupDeliveryData.getPickupRequestDate());
//        editor.apply();
//    }
    public SelectPickupDelivery_data getSelectPickupDetails() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return new SelectPickupDelivery_data(
                sharedPreferences.getInt("PickuId", 0),
                sharedPreferences.getString("Address", null),
                sharedPreferences.getInt("CustomerId",0),
                sharedPreferences.getString("CustomerName",null),
                sharedPreferences.getString("PickupRequestDate",null),
                sharedPreferences.getString("PickupRequestServiceType",null),
                sharedPreferences.getString("PickupRequestServiceName",null),
                sharedPreferences.getString("PickupRequestStoreName",null),
                sharedPreferences.getString("PickupRequestTime",null),
                sharedPreferences.getString("PickupRequestStatus",null),
                sharedPreferences.getString("Location",null),
                sharedPreferences.getString("DueDate",null));

    }


    public void saveSelectDeliveryDetails(String PickuId,
                                          String Address,
                                          String CustomerName,
                                          String CustomerId,
                                          String PickupRequestDate,
                                          String DeliveryOTP,
                                          String DeliveryRequestTotalAmount,
                                          String DeliveryRequestTotalTax,
                                          String DeliveryRequestTotalCharge,
                                          String DeliveryRequestTotalDiscount,
                                          String DeliveryRequestTotalRoundOff,
                                          String DeliveryRequestNetAmount) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        sharedPreferences =PreferenceManager.getDefaultSharedPreferences(context);
//        editor = sharedPreferences.edit();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PickuId", PickuId);
        editor.putString("Address",Address);
        editor.putString("CustomerName",CustomerName);
        editor.putString("CustomerId",CustomerId);
        editor.putString("PickupRequestDate",PickupRequestDate);
        editor.putString("DeliveryOTP",DeliveryOTP);
        editor.putString("DeliveryRequestTotalAmount",String.valueOf(DeliveryRequestTotalAmount));
        editor.putString("DeliveryRequestTotalTax",String.valueOf(DeliveryRequestTotalTax));
        editor.putString("DeliveryRequestTotalCharge",String.valueOf(DeliveryRequestTotalCharge));
        editor.putString("DeliveryRequestTotalDiscount",String.valueOf(DeliveryRequestTotalDiscount));
        editor.putString("DeliveryRequestTotalRoundOff",String.valueOf(DeliveryRequestTotalRoundOff));
        editor.putString("DeliveryRequestNetAmount",String.valueOf(DeliveryRequestNetAmount));
//        editor.apply();
        editor.commit();
    }

    public  String getPref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public void saveItems(String orderDetailsItemCode, String orderDetailsPrice, String orderDetailsTotalNo) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        sharedPreferences =PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Itemcode", orderDetailsItemCode);
        editor.putString("price", orderDetailsPrice);
        editor.putString("quantity", orderDetailsTotalNo);
        editor.commit();
    }

    public  String getItems(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

//    public DeliverySchedualedList_Data getSelectDeliveryDetails() {
//        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//        return new DeliverySchedualedList_Data(
//                sharedPreferences.getInt("PickuId",0),
//                sharedPreferences.getString("Address",""),
//                sharedPreferences.getString("CustomerName",""),
//                sharedPreferences.getInt("CustomerId",0),
//                sharedPreferences.getString("PickupRequestDate",null),
//                sharedPreferences.getString("DeliveryOTP",null),
//                sharedPreferences.getInt("DeliveryRequestTotalAmount",0),
//                sharedPreferences.getFloat("DeliveryRequestTotalTax",0),
//                sharedPreferences.getInt("DeliveryRequestTotalCharge",0),
//                sharedPreferences.getInt("DeliveryRequestTotalDiscount",0),
//                sharedPreferences.getFloat("DeliveryRequestTotalRoundOff",0),
//                sharedPreferences.getInt("DeliveryRequestNetAmount",0));
//    }

    public void saveInserOrder(String Status,String OrderCode) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("Status", Status);
        editor.putString("OrderCode",OrderCode);
        editor.apply();
    }

    public InsertOrder_data getInsertOrder() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return new InsertOrder_data(sharedPreferences.getString("Status", null),
                sharedPreferences.getString("OrderCode",null));

    }


    public void removeData(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.remove(SHARED_PREF_NAME);
        editor.apply();
    }


    public boolean isLoggedIn() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged", false);

    }


}
