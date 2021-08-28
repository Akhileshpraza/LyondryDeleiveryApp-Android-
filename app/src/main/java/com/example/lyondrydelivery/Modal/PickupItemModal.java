package com.example.lyondrydelivery.Modal;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PickupItemModal {

    int id;
    int sno;
    String pickupRequestId;
    String pickupRequestServiceType;
    String pickupRequestServiceName;
    String pickupRequestStoreName;
    String pickupRequestAddress;
    String pickupRequestDate;
    String pickupRequestTime;
    String pickupRequestStatus;
    String location;
    String customerName;
    String CustomerId;

    public PickupItemModal(int id ,int son,String pickupRequestId, String pickupRequestServiceType, String pickupRequestServiceName, String pickupRequestStoreName, String pickupRequestAddress, String pickupRequestDate, String pickupRequestTime, String pickupRequestStatus, String locations, String customerName, String customerId) {

        this.id = id;
        this.sno = son;
        this.pickupRequestId = pickupRequestId;
        this.pickupRequestServiceType = pickupRequestServiceType;
        this.pickupRequestServiceName = pickupRequestServiceName;
        this.pickupRequestStoreName =pickupRequestStoreName;
        this.pickupRequestAddress = pickupRequestAddress;
        this.pickupRequestDate = pickupRequestDate;
        this.pickupRequestTime = pickupRequestTime;
        this.pickupRequestStatus = pickupRequestStatus;
        this.location = locations;
        this.customerName = customerName;
        this.CustomerId = customerId;
    }

    public PickupItemModal(String pickupRequestId, String pickupRequestServiceType, String pickupRequestServiceName, String pickupRequestStoreName, String pickupRequestAddress, String pickupRequestDate, String pickupRequestTime, String pickupRequestStatus, String locations, String customerName, String customerId) {

        this.pickupRequestId = pickupRequestId;
        this.pickupRequestServiceType = pickupRequestServiceType;
        this.pickupRequestServiceName = pickupRequestServiceName;
        this.pickupRequestStoreName =pickupRequestStoreName;
        this.pickupRequestAddress = pickupRequestAddress;
        this.pickupRequestDate = pickupRequestDate;
        this.pickupRequestTime = pickupRequestTime;
        this.pickupRequestStatus = pickupRequestStatus;
        this.location = locations;
        this.customerName = customerName;
        this.CustomerId = customerId;
        Log.i("pickupRequestIdqw",""+pickupRequestId);
    }



    public String getPickupRequestId() {
        return pickupRequestId;
    }

    public void setPickupRequestId(String pickupRequestId) {
        this.pickupRequestId = pickupRequestId;
    }

    public String getPickupRequestServiceType() {
        return pickupRequestServiceType;
    }

    public void setPickupRequestServiceType(String pickupRequestServiceType) {
        this.pickupRequestServiceType = pickupRequestServiceType;
    }

    public String getPickupRequestServiceName() {
        return pickupRequestServiceName;
    }

    public void setPickupRequestServiceName(String pickupRequestServiceName) {
        this.pickupRequestServiceName = pickupRequestServiceName;
    }

    public String getPickupRequestStoreName() {
        return pickupRequestStoreName;
    }

    public void setPickupRequestStoreName(String pickupRequestStoreName) {
        this.pickupRequestStoreName = pickupRequestStoreName;
    }

    public String getPickupRequestAddress() {
        return pickupRequestAddress;
    }

    public void setPickupRequestAddress(String pickupRequestAddress) {
        this.pickupRequestAddress = pickupRequestAddress;
    }

    public String getPickupRequestDate() {
        return pickupRequestDate;
    }

    public void setPickupRequestDate(String pickupRequestDate) {
        this.pickupRequestDate = pickupRequestDate;
    }

    public String getPickupRequestTime() {
        return pickupRequestTime;
    }

    public void setPickupRequestTime(String pickupRequestTime) {
        this.pickupRequestTime = pickupRequestTime;
    }

    public String getPickupRequestStatus() {
        return pickupRequestStatus;
    }

    public void setPickupRequestStatus(String pickupRequestStatus) {
        this.pickupRequestStatus = pickupRequestStatus;
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

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }
}
