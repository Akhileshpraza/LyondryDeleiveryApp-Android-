package com.example.lyondrydelivery.Modal;

public class DeliveryDetailsItemModal {
    Integer id;
    private String SerialNo;
    private String Items;
    private String Qty;
    private String Rate;

    public DeliveryDetailsItemModal(String items, String qty, String rate) {
        Items = items;
        Qty = qty;
        Rate = rate;
    }

    public DeliveryDetailsItemModal(Integer id, String items, String qty, String rate) {
        this.id = id;
        Items = items;
        Qty = qty;
        Rate = rate;
    }

    public String getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(String serialNo) {
        SerialNo = serialNo;
    }

    public String getItems() {
        return Items;
    }

    public void setItems(String items) {
        Items = items;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }


}
