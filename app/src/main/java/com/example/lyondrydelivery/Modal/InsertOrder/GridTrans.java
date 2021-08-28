package com.example.lyondrydelivery.Modal.InsertOrder;

public class GridTrans {
    String OrderDetailsOrderCode;
    String OrderDetailsItemCode;
    int OrderDetailsTotalNo;
    int OrderDetailsServiceId;
    String OrderDetailsServiceType;
    Double OrderDetailsPrice;
    String OrderDetailsRemarks;
    String OrderDetailsRemarks2;
    String OrderDetailsSpecialInstruction;
    String OrderDetailsDeliveryPackType;



    public GridTrans(String orderDetailsOrderCode, String orderDetailsItemCode, int orderDetailsTotalNo, int orderDetailsServiceId, String orderDetailsServiceType, double orderDetailsPrice, String orderDetailsRemarks, String orderDetailsRemarks2, String orderDetailsSpecialInstruction, String orderDetailsDeliveryPackType) {
        OrderDetailsOrderCode = orderDetailsOrderCode;
        OrderDetailsItemCode = orderDetailsItemCode;
        OrderDetailsTotalNo = orderDetailsTotalNo;
        OrderDetailsServiceId = orderDetailsServiceId;
        OrderDetailsServiceType = orderDetailsServiceType;
        OrderDetailsPrice = orderDetailsPrice;
        OrderDetailsRemarks = orderDetailsRemarks;
        OrderDetailsRemarks2 = orderDetailsRemarks2;
        OrderDetailsSpecialInstruction = orderDetailsSpecialInstruction;
        OrderDetailsDeliveryPackType = orderDetailsDeliveryPackType;
    }

//    public GridTrans(int id, int serialNo, String orderDetailsOrderCode, String orderDetailsItemCode, int orderDetailsTotalNo, int orderDetailsServiceId, String orderDetailsServiceType, Double orderDetailsPrice, String orderDetailsRemarks, String orderDetailsRemarks2, String orderDetailsSpecialInstruction, String orderDetailsDeliveryPackType) {
//        this.id = id;
//        this.SerialNo = serialNo;
//        this.OrderDetailsOrderCode = orderDetailsOrderCode;
//        this.OrderDetailsItemCode = orderDetailsItemCode;
//        this.OrderDetailsTotalNo = orderDetailsTotalNo;
//        this.OrderDetailsServiceId = orderDetailsServiceId;
//        this.OrderDetailsServiceType = orderDetailsServiceType;
//        this.OrderDetailsPrice = orderDetailsPrice;
//        this.OrderDetailsRemarks = orderDetailsRemarks;
//        this.OrderDetailsRemarks2 = orderDetailsRemarks2;
//        this.OrderDetailsSpecialInstruction = orderDetailsSpecialInstruction;
//        this.OrderDetailsDeliveryPackType = orderDetailsDeliveryPackType;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getSerialNo() {
//        return SerialNo;
//    }

//    public void setSerialNo(int serialNo) {
//        SerialNo = serialNo;
//    }

    public String getOrderDetailsOrderCode() {
        return OrderDetailsOrderCode;
    }

    public void setOrderDetailsOrderCode(String orderDetailsOrderCode) {
        OrderDetailsOrderCode = orderDetailsOrderCode;
    }

    public String getOrderDetailsItemCode() {
        return OrderDetailsItemCode;
    }

    public void setOrderDetailsItemCode(String orderDetailsItemCode) {
        OrderDetailsItemCode = orderDetailsItemCode;
    }

    public int getOrderDetailsTotalNo() {
        return OrderDetailsTotalNo;
    }

    public void setOrderDetailsTotalNo(int orderDetailsTotalNo) {
        OrderDetailsTotalNo = orderDetailsTotalNo;
    }

    public int getOrderDetailsServiceId() {
        return OrderDetailsServiceId;
    }

    public void setOrderDetailsServiceId(int orderDetailsServiceId) {
        OrderDetailsServiceId = orderDetailsServiceId;
    }

    public String getOrderDetailsServiceType() {
        return OrderDetailsServiceType;
    }

    public void setOrderDetailsServiceType(String orderDetailsServiceType) {
        OrderDetailsServiceType = orderDetailsServiceType;
    }

    public Double getOrderDetailsPrice() {
        return OrderDetailsPrice;
    }

    public void setOrderDetailsPrice(Double orderDetailsPrice) {
        OrderDetailsPrice = orderDetailsPrice;
    }

    public String getOrderDetailsRemarks() {
        return OrderDetailsRemarks;
    }

    public void setOrderDetailsRemarks(String orderDetailsRemarks) {
        OrderDetailsRemarks = orderDetailsRemarks;
    }

    public String getOrderDetailsRemarks2() {
        return OrderDetailsRemarks2;
    }

    public void setOrderDetailsRemarks2(String orderDetailsRemarks2) {
        OrderDetailsRemarks2 = orderDetailsRemarks2;
    }

    public String getOrderDetailsSpecialInstruction() {
        return OrderDetailsSpecialInstruction;
    }

    public void setOrderDetailsSpecialInstruction(String orderDetailsSpecialInstruction) {
        OrderDetailsSpecialInstruction = orderDetailsSpecialInstruction;
    }

    public String getOrderDetailsDeliveryPackType() {
        return OrderDetailsDeliveryPackType;
    }

    public void setOrderDetailsDeliveryPackType(String orderDetailsDeliveryPackType) {
        OrderDetailsDeliveryPackType = orderDetailsDeliveryPackType;
    }
}
