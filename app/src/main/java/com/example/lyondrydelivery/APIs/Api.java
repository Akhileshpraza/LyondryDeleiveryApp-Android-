package com.example.lyondrydelivery.APIs;

import com.example.lyondrydelivery.Modal.InsertOrder.InsertOrder_responce_modal;
import com.example.lyondrydelivery.Modal.InsertOrder.MainInsertOrderList;
import com.example.lyondrydelivery.Modal.InviceMaster.InvoiceMaster_resonce_modal;
import com.example.lyondrydelivery.Modal.InviceMaster.MainInviceMaster_reponce_modal;
import com.example.lyondrydelivery.Modal.Login_responce_modal;
import com.example.lyondrydelivery.Modal.PickupComplite.PickupComplite_responce_modal;
import com.example.lyondrydelivery.Modal.SelectItem.SelectItem_responce_modal;
import com.example.lyondrydelivery.Modal.SelectOrderSpecialInstr.SelectOrderSpecialInstr_respoce_modal;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_responce_modal;
import com.example.lyondrydelivery.Modal.SelecteDelivery.SelectDeliveryScheduled_responce_modal;
import com.example.lyondrydelivery.Modal.Services.Services_responce_modal;
import com.example.lyondrydelivery.Modal.UpdateDeliveryStatus.UpdateDeliveryStatus_responce_modal;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("UserLoginApi/Login")
    Call<Login_responce_modal> login(
            @Field("UserName") String UserName,
            @Field("UserPassword") String UserPassword,
            @Field("UserStoreId") int UserStoreId
    );

    @GET("PickupDeliveryApi/SelectPickupDelivery")
    Call<SelectPickupDelivery_responce_modal> SelectPickupDelivery(
            @Header("Token") String token,
            @Header("UserName") String UserName);


    @POST("OrderApi/InsertOrder")
    Call<InsertOrder_responce_modal> InsertOrder(
            @Header("Token") String Token,
            @Header("UserName") String UserName,
            @Body MainInsertOrderList mainInsertOrderList);

    @GET("ServiceApi/SelectService")
    Call<Services_responce_modal> SelectService(
            @Query("ServiceId") Long ServiceId);

    @GET("ItemMasterApi/SelectItem")
    Call<SelectItem_responce_modal> SelectItem(
            @Query("ItemId") int ItemId);

    @GET("OrderApi/SelectOrderSpecialInstr")
    Call<SelectOrderSpecialInstr_respoce_modal> OrderSpecialInstr(
            @Query("OrderSplCode") String OrderSplCode);

    @GET("DeliveryScheduleApi/SelectDeliveryScheduled")
    Call<SelectDeliveryScheduled_responce_modal> SelectDeliveryScheduale(
            @Header("Token") String token,
            @Header("UserName") String UserName,
            @Header("PickupRequestId") int PickupRequestId);

    @GET("InvoiceApi/SelectInvoice")
    Call<MainInviceMaster_reponce_modal> InviceMaster(
            @Header("Token") String token,
            @Header("UserName") String UserName);

    @POST("DeliveryScheduleApi/UpdateDeliveredStatus")
    Call<UpdateDeliveryStatus_responce_modal> UpdateDeliveredStatus(
            @Header("Token") String Token,
            @Header("UserName") String UserName,
            @Header("PickupSchedulePickupRequestId") Integer PickupSchedulePickupRequestId,
            @Header("InvoiceModeOfPayment") String InvoiceModeOfPayment,
            @Header("InvoicePaymentDetails") String InvoicePaymentDetails,
            @Header("InvoiceRemarks") String InvoiceRemarks
            );


    @GET("PickupCompletedApi/SelectPickupCompleted")
    Call<PickupComplite_responce_modal> PickupComplete(
            @Header("App") String app,
            @Header("Token") String token,
            @Header("UserName") String UserName,
            @Header("PickupRequestId") int PickupRequestId);
}



