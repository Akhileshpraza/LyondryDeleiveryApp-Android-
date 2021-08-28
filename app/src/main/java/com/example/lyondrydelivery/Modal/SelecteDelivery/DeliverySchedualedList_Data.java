package com.example.lyondrydelivery.Modal.SelecteDelivery;

import android.util.Log;

import com.example.lyondrydelivery.Adapter.DeliveryCompleteListShowAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliverySchedualedList_Data {
    @SerializedName("DeliveryRequestId")
    @Expose
    private Integer deliveryRequestId;
    @SerializedName("DeliveryRequestServiceType")
    @Expose
    private String deliveryRequestServiceType;
    @SerializedName("DeliveryRequestServiceName")
    @Expose
    private String deliveryRequestServiceName;
    @SerializedName("DeliveryRequestStoreName")
    @Expose
    private String deliveryRequestStoreName;
    @SerializedName("DeliveryRequestAddress")
    @Expose
    private String deliveryRequestAddress;
    @SerializedName("DeliveryRequestDate")
    @Expose
    private String deliveryRequestDate;
    @SerializedName("DeliveryRequestDueDate")
    @Expose
    private String deliveryRequestDueDate;
    @SerializedName("DeliveryRequestTime")
    @Expose
    private String deliveryRequestTime;
    @SerializedName("DeliveryRequestStatus")
    @Expose
    private String deliveryRequestStatus;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("CustomerId")
    @Expose
    private Integer customerId;
    @SerializedName("DeliveryOTP")
    @Expose
    private String deliveryOTP;
    @SerializedName("DeliveryRequestTotalAmount")
    @Expose
    private Integer deliveryRequestTotalAmount;
    @SerializedName("DeliveryRequestTotalTax")
    @Expose
    private float deliveryRequestTotalTax;
    @SerializedName("DeliveryRequestTotalCharge")
    @Expose
    private Integer deliveryRequestTotalCharge;
    @SerializedName("DeliveryRequestTotalDiscount")
    @Expose
    private Integer deliveryRequestTotalDiscount;
    @SerializedName("DeliveryRequestTotalRoundOff")
    @Expose
    private float deliveryRequestTotalRoundOff;
    @SerializedName("DeliveryRequestNetAmount")
    @Expose
    private Integer deliveryRequestNetAmount;


    public DeliverySchedualedList_Data(int pickuId,
                                       String address,
                                       String customerName,
                                       int customerId,
                                       String pickupRequestDate,
                                       String deliveryOTP,
                                       Integer deliveryRequestTotalAmount,
                                       float deliveryRequestTotalTax,
                                       Integer deliveryRequestTotalCharge,
                                       Integer deliveryRequestTotalDiscount,
                                       float deliveryRequestTotalRoundOff,
                                       Integer deliveryRequestNetAmount) {
        this.deliveryRequestId =pickuId;
        this.deliveryRequestAddress =address;
        this.customerName = customerName;
        this.customerId = customerId;
        this.deliveryRequestDate = pickupRequestDate;
        this.deliveryOTP =deliveryOTP;
        this.deliveryRequestTotalAmount =deliveryRequestTotalAmount;
        this.deliveryRequestTotalTax = deliveryRequestTotalTax;
        this.deliveryRequestTotalCharge =deliveryRequestTotalCharge;
        this.deliveryRequestTotalDiscount =deliveryRequestTotalDiscount;
        this.deliveryRequestTotalRoundOff =deliveryRequestTotalRoundOff;
        this.deliveryRequestNetAmount = deliveryRequestNetAmount;
    }



    public DeliverySchedualedList_Data(Integer DeliveryRequestId,
                                       String DeliveryRequestServiceType,
                                       String DeliveryRequestServiceName,
                                       String DeliveryRequestStoreName,
                                       String DeliveryRequestAddress,
                                       String DeliveryRequestDate,
                                       String DeliveryRequestDueDate,
                                       String DeliveryRequestTime,
                                       String DeliveryRequestStatus,
                                       String DeliveryLocation,
                                       String DeliveryCustomerName,
                                       Integer DeliveryCustomerId,
                                       String DeliveryOtp,
                                       Integer deliveryRequestTotalAmount,
                                       float deliveryRequestTotalTax,
                                       Integer deliveryRequestTotalCharge,
                                       Integer deliveryRequestTotalDiscount,
                                       float deliveryRequestTotalRoundOff,
                                       Integer deliveryRequestNetAmount) {


        this.deliveryRequestId =DeliveryRequestId;
        this.deliveryRequestServiceType = DeliveryRequestServiceType;
        this.deliveryRequestServiceName =DeliveryRequestServiceName;
        this.deliveryRequestStoreName =DeliveryRequestStoreName;
        this.deliveryRequestAddress = DeliveryRequestAddress;
        this.deliveryRequestDate = DeliveryRequestDate;
        this.deliveryRequestDueDate = DeliveryRequestDueDate;
        this.deliveryRequestTime = DeliveryRequestTime;
        this.deliveryRequestStatus = DeliveryRequestStatus;
        this.location =DeliveryLocation;
        this.customerName =DeliveryCustomerName;
        this.customerId = DeliveryCustomerId;
        this.deliveryOTP = DeliveryOtp;
        this.deliveryRequestTotalAmount =deliveryRequestTotalAmount;
        this.deliveryRequestTotalTax = deliveryRequestTotalTax;
        this.deliveryRequestTotalCharge =deliveryRequestTotalCharge;
        this.deliveryRequestTotalDiscount =deliveryRequestTotalDiscount;
        this.deliveryRequestTotalRoundOff =deliveryRequestTotalRoundOff;
        this.deliveryRequestNetAmount = deliveryRequestNetAmount;
    }

//    public DeliverySchedualedList_Data(int pickuId, String address, String customerName, int customerId, String pickupRequestDate) {
//
//        this.deliveryRequestId =pickuId;
//        this.deliveryRequestAddress =address;
//        this.customerName = customerName;
//        this.customerId = customerId;
//        this.deliveryRequestDate = pickupRequestDate;
//
//    }




    public Integer getDeliveryRequestId() {
        return deliveryRequestId;
    }

    public void setDeliveryRequestId(Integer deliveryRequestId) {
        this.deliveryRequestId = deliveryRequestId;
    }

    public String getDeliveryRequestServiceType() {
        return deliveryRequestServiceType;
    }

    public void setDeliveryRequestServiceType(String deliveryRequestServiceType) {
        this.deliveryRequestServiceType = deliveryRequestServiceType;
    }

    public String getDeliveryRequestServiceName() {
        return deliveryRequestServiceName;
    }

    public void setDeliveryRequestServiceName(String deliveryRequestServiceName) {
        this.deliveryRequestServiceName = deliveryRequestServiceName;
    }

    public String getDeliveryRequestStoreName() {
        return deliveryRequestStoreName;
    }

    public void setDeliveryRequestStoreName(String deliveryRequestStoreName) {
        this.deliveryRequestStoreName = deliveryRequestStoreName;
    }

    public String getDeliveryRequestAddress() {
        return deliveryRequestAddress;
    }

    public void setDeliveryRequestAddress(String deliveryRequestAddress) {
        this.deliveryRequestAddress = deliveryRequestAddress;
    }

    public String getDeliveryRequestDate() {
        return deliveryRequestDate;
    }

    public void setDeliveryRequestDate(String deliveryRequestDate) {
        this.deliveryRequestDate = deliveryRequestDate;
    }

    public String getDeliveryRequestDueDate() {
        return deliveryRequestDueDate;
    }

    public void setDeliveryRequestDueDate(String deliveryRequestDueDate) {
        this.deliveryRequestDueDate = deliveryRequestDueDate;
    }

    public String getDeliveryRequestTime() {
        return deliveryRequestTime;
    }

    public void setDeliveryRequestTime(String deliveryRequestTime) {
        this.deliveryRequestTime = deliveryRequestTime;
    }

    public String getDeliveryRequestStatus() {
        return deliveryRequestStatus;
    }

    public void setDeliveryRequestStatus(String deliveryRequestStatus) {
        this.deliveryRequestStatus = deliveryRequestStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getDeliveryOTP() {
        return deliveryOTP;
    }

    public void setDeliveryOTP(String deliveryOTP) {
        this.deliveryOTP = deliveryOTP;
    }

    public Integer getDeliveryRequestTotalAmount() {
        return deliveryRequestTotalAmount;
    }

    public void setDeliveryRequestTotalAmount(Integer deliveryRequestTotalAmount) {
        this.deliveryRequestTotalAmount = deliveryRequestTotalAmount;
    }

    public float getDeliveryRequestTotalTax() {
        return deliveryRequestTotalTax;
    }

    public void setDeliveryRequestTotalTax(float deliveryRequestTotalTax) {
        this.deliveryRequestTotalTax = deliveryRequestTotalTax;
    }

    public Integer getDeliveryRequestTotalCharge() {
        return deliveryRequestTotalCharge;
    }

    public void setDeliveryRequestTotalCharge(Integer deliveryRequestTotalCharge) {
        this.deliveryRequestTotalCharge = deliveryRequestTotalCharge;
    }

    public Integer getDeliveryRequestTotalDiscount() {
        return deliveryRequestTotalDiscount;
    }

    public void setDeliveryRequestTotalDiscount(Integer deliveryRequestTotalDiscount) {
        this.deliveryRequestTotalDiscount = deliveryRequestTotalDiscount;
    }

    public float getDeliveryRequestTotalRoundOff() {
        return deliveryRequestTotalRoundOff;
    }

    public void setDeliveryRequestTotalRoundOff(float deliveryRequestTotalRoundOff) {
        this.deliveryRequestTotalRoundOff = deliveryRequestTotalRoundOff;
    }

    public Integer getDeliveryRequestNetAmount() {
        return deliveryRequestNetAmount;
    }

    public void setDeliveryRequestNetAmount(Integer deliveryRequestNetAmount) {
        this.deliveryRequestNetAmount = deliveryRequestNetAmount;
    }


}
