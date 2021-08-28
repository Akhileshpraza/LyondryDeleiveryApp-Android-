package com.example.lyondrydelivery.Modal.SelectItem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelecteItem_data_temp {

    String itemName;
    String itemServiceType;
    Double itemPrice;
    String itemCode;
    Integer itemId;
    Integer itemService;


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemServiceType() {
        return itemServiceType;
    }

    public void setItemServiceType(String itemServiceType) {
        this.itemServiceType = itemServiceType;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemService() {
        return itemService;
    }

    public void setItemService(Integer itemService) {
        this.itemService = itemService;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }


    public SelecteItem_data_temp(String itemName, String itemServiceType, Double itemPrice, String itemCode, Integer itemService) {
        this.itemName = itemName;
        this.itemServiceType = itemServiceType;
        this.itemPrice = itemPrice;
        this.itemCode = itemCode;
        this.itemService = itemService;
    }
}
