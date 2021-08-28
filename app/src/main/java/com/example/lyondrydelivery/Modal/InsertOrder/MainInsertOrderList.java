package com.example.lyondrydelivery.Modal.InsertOrder;

import com.example.lyondrydelivery.Modal.ItemModal;

public class MainInsertOrderList {
    String OrderDateString;
    String OrderCode;
    int OrderStoreId;
    int OrderPickupBy;
    String OrderType;
    int OrderPickupId;
    int OrderCustomerId;
    int OrderServiceId;
    String OrderServiceType;
    int OrderTotelItems;
    GridTrans[] gridTrans;


    public MainInsertOrderList(String orderDateString, String orderCode, int orderStoreId, int orderPickupBy, String orderType, int orderPickupId, int orderCustomerId, int orderServiceId, String orderServiceType, int orderTotelItems, GridTrans[] gridTrans) {
        OrderDateString = orderDateString;
        OrderCode = orderCode;
        OrderStoreId = orderStoreId;
        OrderPickupBy = orderPickupBy;
        OrderType = orderType;
        OrderPickupId = orderPickupId;
        OrderCustomerId = orderCustomerId;
        OrderServiceId = orderServiceId;
        OrderServiceType = orderServiceType;
        OrderTotelItems = orderTotelItems;
        this.gridTrans = gridTrans;
    }

    public String getOrderDate() {
        return OrderDateString;
    }

    public void setOrderDate(String orderDate) {
        OrderDateString = orderDate;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public int getOrderStoreId() {
        return OrderStoreId;
    }

    public void setOrderStoreId(int orderStoreId) {
        OrderStoreId = orderStoreId;
    }

    public int getOrderPickupBy() {
        return OrderPickupBy;
    }

    public void setOrderPickupBy(int orderPickupBy) {
        OrderPickupBy = orderPickupBy;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
    }

    public int getOrderPickupId() {
        return OrderPickupId;
    }

    public void setOrderPickupId(int orderPickupId) {
        OrderPickupId = orderPickupId;
    }

    public int getOrderCustomerId() {
        return OrderCustomerId;
    }

    public void setOrderCustomerId(int orderCustomerId) {
        OrderCustomerId = orderCustomerId;
    }

    public int getOrderServiceId() {
        return OrderServiceId;
    }

    public void setOrderServiceId(int orderServiceId) {
        OrderServiceId = orderServiceId;
    }

    public String getOrderServiceType() {
        return OrderServiceType;
    }

    public void setOrderServiceType(String orderServiceType) {
        OrderServiceType = orderServiceType;
    }

    public int getOrderTotelItems() {
        return OrderTotelItems;
    }

    public void setOrderTotelItems(int orderTotelItems) {
        OrderTotelItems = orderTotelItems;
    }

    public GridTrans[] getGridTrans() {
        return gridTrans;
    }

    public void setGridTrans(GridTrans[] gridTrans) {
        this.gridTrans = gridTrans;
    }
}
