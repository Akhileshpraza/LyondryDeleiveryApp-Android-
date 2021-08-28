package com.example.lyondrydelivery.Modal;

public class DeliveryRequestComplited_Modal {

    int id;
    int son;
    String DeliveryRequestId;
    String DeliveryRequestServiceType;
    String DeliveryRequestServiceName;
    String DeliveryRequestStoreName;
    String DeliveryRequestAddress;
    String DeliveryRequestDate;
    String DeliveryRequestDueDate;
    String DeliveryRequestTime;
    String DeliveryRequestStatus;
    String DeliveryLocation;
    String DeliveryCustomerName;
    String DeliveryCustomerId;

    public DeliveryRequestComplited_Modal(int id,int son, String deliveryRequestId, String deliveryRequestServiceType, String deliveryRequestServiceName, String deliveryRequestStoreName, String deliveryRequestAddress, String deliveryRequestDate, String deliveryRequestDueDate, String deliveryRequestTime, String deliveryRequestStatus, String deliveryLocation, String deliveryCustomerName, String deliveryCustomerId) {
        this.id = id;
        DeliveryRequestId = deliveryRequestId;
        DeliveryRequestServiceType = deliveryRequestServiceType;
        DeliveryRequestServiceName = deliveryRequestServiceName;
        DeliveryRequestStoreName = deliveryRequestStoreName;
        DeliveryRequestAddress = deliveryRequestAddress;
        DeliveryRequestDate = deliveryRequestDate;
        DeliveryRequestDueDate = deliveryRequestDueDate;
        DeliveryRequestTime = deliveryRequestTime;
        DeliveryRequestStatus = deliveryRequestStatus;
        DeliveryLocation = deliveryLocation;
        DeliveryCustomerName = deliveryCustomerName;
        DeliveryCustomerId = deliveryCustomerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeliveryRequestId() {
        return DeliveryRequestId;
    }

    public void setDeliveryRequestId(String deliveryRequestId) {
        DeliveryRequestId = deliveryRequestId;
    }

    public String getDeliveryRequestServiceType() {
        return DeliveryRequestServiceType;
    }

    public void setDeliveryRequestServiceType(String deliveryRequestServiceType) {
        DeliveryRequestServiceType = deliveryRequestServiceType;
    }

    public String getDeliveryRequestServiceName() {
        return DeliveryRequestServiceName;
    }

    public void setDeliveryRequestServiceName(String deliveryRequestServiceName) {
        DeliveryRequestServiceName = deliveryRequestServiceName;
    }

    public String getDeliveryRequestStoreName() {
        return DeliveryRequestStoreName;
    }

    public void setDeliveryRequestStoreName(String deliveryRequestStoreName) {
        DeliveryRequestStoreName = deliveryRequestStoreName;
    }

    public String getDeliveryRequestAddress() {
        return DeliveryRequestAddress;
    }

    public void setDeliveryRequestAddress(String deliveryRequestAddress) {
        DeliveryRequestAddress = deliveryRequestAddress;
    }

    public String getDeliveryRequestDate() {
        return DeliveryRequestDate;
    }

    public void setDeliveryRequestDate(String deliveryRequestDate) {
        DeliveryRequestDate = deliveryRequestDate;
    }

    public String getDeliveryRequestDueDate() {
        return DeliveryRequestDueDate;
    }

    public void setDeliveryRequestDueDate(String deliveryRequestDueDate) {
        DeliveryRequestDueDate = deliveryRequestDueDate;
    }

    public String getDeliveryRequestTime() {
        return DeliveryRequestTime;
    }

    public void setDeliveryRequestTime(String deliveryRequestTime) {
        DeliveryRequestTime = deliveryRequestTime;
    }

    public String getDeliveryRequestStatus() {
        return DeliveryRequestStatus;
    }

    public void setDeliveryRequestStatus(String deliveryRequestStatus) {
        DeliveryRequestStatus = deliveryRequestStatus;
    }

    public String getDeliveryLocation() {
        return DeliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        DeliveryLocation = deliveryLocation;
    }

    public String getDeliveryCustomerName() {
        return DeliveryCustomerName;
    }

    public void setDeliveryCustomerName(String deliveryCustomerName) {
        DeliveryCustomerName = deliveryCustomerName;
    }

    public String getDeliveryCustomerId() {
        return DeliveryCustomerId;
    }

    public void setDeliveryCustomerId(String deliveryCustomerId) {
        DeliveryCustomerId = deliveryCustomerId;
    }
}
