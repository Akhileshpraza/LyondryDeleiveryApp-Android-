package com.example.lyondrydelivery.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectPickupDelivery_data {

    @SerializedName("PickupRequestId")
    @Expose
    private Integer pickupRequestId;
    @SerializedName("PickupRequestServiceType")
    @Expose
    private String pickupRequestServiceType;
    @SerializedName("PickupRequestServiceName")
    @Expose
    private String pickupRequestServiceName;
    @SerializedName("PickupRequestStoreName")
    @Expose
    private String pickupRequestStoreName;
    @SerializedName("PickupRequestAddress")
    @Expose
    private String pickupRequestAddress;
    @SerializedName("PickupRequestDate")
    @Expose
    private String pickupRequestDate;
    @SerializedName("PickupRequestTime")
    @Expose
    private String pickupRequestTime;
    @SerializedName("PickupRequestStatus")
    @Expose
    private String pickupRequestStatus;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("CustomerId")
    @Expose
    private Integer CustomerId;
    @SerializedName("PickupRequestDueDate")
    @Expose
    private String pickupRequestDueDate;


    public SelectPickupDelivery_data(int pickuId, String pickupRequestAddress, int customerId, String customerName, String pickupRequestDate, String pickupRequestServiceType, String pickupRequestServiceName, String pickupRequestStoreName, String pickupRequestTime, String pickupRequestStatus, String locations,String duaDate) {
        this.pickupRequestId =pickuId;
        this.pickupRequestAddress =pickupRequestAddress;
        this.CustomerId = customerId;
        this.customerName = customerName;
        this.pickupRequestDate = pickupRequestDate;
        this.pickupRequestServiceType = pickupRequestServiceType;
        this.pickupRequestServiceName = pickupRequestServiceName;
        this.pickupRequestStoreName = pickupRequestStoreName;
        this.pickupRequestTime = pickupRequestTime;
        this.pickupRequestStatus = pickupRequestStatus;
        this.location = locations;
        this.pickupRequestDueDate =duaDate;

    }

    public SelectPickupDelivery_data(int pickupRequestId, String pickupRequestServiceType, String pickupRequestServiceName, String pickupRequestStoreName, String pickupRequestAddress, String pickupRequestDate, String pickupRequestTime, String pickupRequestStatus, String location,String customerName, int customerId,String duaDate) {
        this.pickupRequestId =pickupRequestId;
        this.pickupRequestServiceType = pickupRequestServiceType;
        this.pickupRequestServiceName = pickupRequestServiceName;
        this.pickupRequestStoreName = pickupRequestStoreName;
        this.pickupRequestAddress =pickupRequestAddress;
        this.pickupRequestDate = pickupRequestDate;
        this.pickupRequestTime = pickupRequestTime;
        this.pickupRequestStatus = pickupRequestStatus;
        this.location = location;
        this.customerName = customerName;
        this.CustomerId = customerId;
        this.pickupRequestDueDate =duaDate;

    }

    public SelectPickupDelivery_data(int pickupRequestId, String pickupRequestAddress, String pickupRequestTime, String location, String customerName) {
        this.pickupRequestId =pickupRequestId;
        this.pickupRequestAddress =pickupRequestAddress;
        this.customerName = customerName;
        this.pickupRequestTime = pickupRequestTime;
        this.location = location;
    }

    public SelectPickupDelivery_data(int pickupid) {
        this.pickupRequestId =pickupid;
    }


    public Integer getPickupRequestId() {
        return pickupRequestId;
    }

    public void setPickupRequestId(Integer pickupRequestId) {
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

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    @Override
    public String toString() {
        return location;
    }

    public String getPickupRequestDueDate() {
        return pickupRequestDueDate;
    }

    public void setPickupRequestDueDate(String pickupRequestDueDate) {
        this.pickupRequestDueDate = pickupRequestDueDate;
    }
}
