package com.example.lyondrydelivery.Modal.SelecteDelivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryDetailsList_data {
    @SerializedName("OrderDetailsId")
    @Expose
    private Integer orderDetailsId;
    @SerializedName("OrderDetailsOrderCode")
    @Expose
    private String orderDetailsOrderCode;
    @SerializedName("OrderDetailsItemCode")
    @Expose
    private String orderDetailsItemCode;
    @SerializedName("OrderDetailsTotalNo")
    @Expose
    private Integer orderDetailsTotalNo;
    @SerializedName("OrderDetailsServiceId")
    @Expose
    private Integer orderDetailsServiceId;
    @SerializedName("OrderDetailsServiceType")
    @Expose
    private String orderDetailsServiceType;
    @SerializedName("OrderDetailsPrice")
    @Expose
    private Float orderDetailsPrice;
    @SerializedName("OrderDetailsTaxPercentage")
    @Expose
    private Float orderDetailsTaxPercentage;
    @SerializedName("OrderDetailsTotalPrice")
    @Expose
    private Float orderDetailsTotalPrice;
    @SerializedName("OrderDetailsTax")
    @Expose
    private Float orderDetailsTax;
    @SerializedName("OrderDetailsRemarks")
    @Expose
    private String orderDetailsRemarks;
    @SerializedName("OrderDetailsRemarks2")
    @Expose
    private String orderDetailsRemarks2;
    @SerializedName("OrderDetailsSpecialInstruction")
    @Expose
    private String orderDetailsSpecialInstruction;
    @SerializedName("OrderDetailsDeliveryPackType")
    @Expose
    private String orderDetailsDeliveryPackType;

    public Integer getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Integer orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public String getOrderDetailsOrderCode() {
        return orderDetailsOrderCode;
    }

    public void setOrderDetailsOrderCode(String orderDetailsOrderCode) {
        this.orderDetailsOrderCode = orderDetailsOrderCode;
    }

    public String getOrderDetailsItemCode() {
        return orderDetailsItemCode;
    }

    public void setOrderDetailsItemCode(String orderDetailsItemCode) {
        this.orderDetailsItemCode = orderDetailsItemCode;
    }

    public Integer getOrderDetailsTotalNo() {
        return orderDetailsTotalNo;
    }

    public void setOrderDetailsTotalNo(Integer orderDetailsTotalNo) {
        this.orderDetailsTotalNo = orderDetailsTotalNo;
    }

    public Integer getOrderDetailsServiceId() {
        return orderDetailsServiceId;
    }

    public void setOrderDetailsServiceId(Integer orderDetailsServiceId) {
        this.orderDetailsServiceId = orderDetailsServiceId;
    }

    public String getOrderDetailsServiceType() {
        return orderDetailsServiceType;
    }

    public void setOrderDetailsServiceType(String orderDetailsServiceType) {
        this.orderDetailsServiceType = orderDetailsServiceType;
    }

    public Float getOrderDetailsPrice() {
        return orderDetailsPrice;
    }

    public void setOrderDetailsPrice(Float orderDetailsPrice) {
        this.orderDetailsPrice = orderDetailsPrice;
    }

    public Float getOrderDetailsTaxPercentage() {
        return orderDetailsTaxPercentage;
    }

    public void setOrderDetailsTaxPercentage(Float orderDetailsTaxPercentage) {
        this.orderDetailsTaxPercentage = orderDetailsTaxPercentage;
    }

    public Float getOrderDetailsTotalPrice() {
        return orderDetailsTotalPrice;
    }

    public void setOrderDetailsTotalPrice(Float orderDetailsTotalPrice) {
        this.orderDetailsTotalPrice = orderDetailsTotalPrice;
    }

    public Float getOrderDetailsTax() {
        return orderDetailsTax;
    }

    public void setOrderDetailsTax(Float orderDetailsTax) {
        this.orderDetailsTax = orderDetailsTax;
    }

    public String getOrderDetailsRemarks() {
        return orderDetailsRemarks;
    }

    public void setOrderDetailsRemarks(String orderDetailsRemarks) {
        this.orderDetailsRemarks = orderDetailsRemarks;
    }

    public String getOrderDetailsRemarks2() {
        return orderDetailsRemarks2;
    }

    public void setOrderDetailsRemarks2(String orderDetailsRemarks2) {
        this.orderDetailsRemarks2 = orderDetailsRemarks2;
    }

    public String getOrderDetailsSpecialInstruction() {
        return orderDetailsSpecialInstruction;
    }

    public void setOrderDetailsSpecialInstruction(String orderDetailsSpecialInstruction) {
        this.orderDetailsSpecialInstruction = orderDetailsSpecialInstruction;
    }

    public String getOrderDetailsDeliveryPackType() {
        return orderDetailsDeliveryPackType;
    }

    public void setOrderDetailsDeliveryPackType(String orderDetailsDeliveryPackType) {
        this.orderDetailsDeliveryPackType = orderDetailsDeliveryPackType;
    }

}
