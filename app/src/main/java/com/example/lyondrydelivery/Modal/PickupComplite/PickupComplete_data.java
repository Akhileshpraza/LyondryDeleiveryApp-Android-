package com.example.lyondrydelivery.Modal.PickupComplite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PickupComplete_data {

    @SerializedName("OrderDetailsOrderCode")
    @Expose
    private String orderDetailsOrderCode;
    @SerializedName("OrderDetailsItemCode")
    @Expose
    private String orderDetailsItemCode;
    @SerializedName("OrderDetailsItemName")
    @Expose
    private String orderDetailsItemName;
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
    private Integer orderDetailsPrice;
    @SerializedName("OrderDetailsDiscPercentage")
    @Expose
    private Integer orderDetailsDiscPercentage;
    @SerializedName("OrderDetailsTaxPercentage")
    @Expose
    private Integer orderDetailsTaxPercentage;
    @SerializedName("OrderDetailsTotalPrice")
    @Expose
    private Double orderDetailsTotalPrice;
//    @SerializedName("OrderDetailsDiscount")
//    @Expose
//    private Integer orderDetailsDiscount;
//    @SerializedName("OrderDetailsTax")
//    @Expose
//    private Integer orderDetailsTax;
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

    public String getOrderDetailsItemName() {
        return orderDetailsItemName;
    }

    public void setOrderDetailsItemName(String orderDetailsItemName) {
        this.orderDetailsItemName = orderDetailsItemName;
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

    public Integer getOrderDetailsPrice() {
        return orderDetailsPrice;
    }

    public void setOrderDetailsPrice(Integer orderDetailsPrice) {
        this.orderDetailsPrice = orderDetailsPrice;
    }

    public Integer getOrderDetailsDiscPercentage() {
        return orderDetailsDiscPercentage;
    }

    public void setOrderDetailsDiscPercentage(Integer orderDetailsDiscPercentage) {
        this.orderDetailsDiscPercentage = orderDetailsDiscPercentage;
    }

    public Integer getOrderDetailsTaxPercentage() {
        return orderDetailsTaxPercentage;
    }

    public void setOrderDetailsTaxPercentage(Integer orderDetailsTaxPercentage) {
        this.orderDetailsTaxPercentage = orderDetailsTaxPercentage;
    }

    public Double getOrderDetailsTotalPrice() {
        return orderDetailsTotalPrice;
    }

    public void setOrderDetailsTotalPrice(Double orderDetailsTotalPrice) {
        this.orderDetailsTotalPrice = orderDetailsTotalPrice;
    }

//    public Integer getOrderDetailsDiscount() {
//        return orderDetailsDiscount;
//    }
//
//    public void setOrderDetailsDiscount(Integer orderDetailsDiscount) {
//        this.orderDetailsDiscount = orderDetailsDiscount;
//    }
//
//    public Integer getOrderDetailsTax() {
//        return orderDetailsTax;
//    }
//
//    public void setOrderDetailsTax(Integer orderDetailsTax) {
//        this.orderDetailsTax = orderDetailsTax;
//    }

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
