package com.example.lyondrydelivery.Modal;

import android.content.Intent;
import android.util.Log;
import android.widget.EditText;

public class ItemModal {

     int id;
     int SerialNo;
     String Items;
     String Qty;
     String Rate;
     String color;
     String brand;
     String unitprice;
     String discription;
     String image;

    String ODOrderCode;
    String ODItemCode;

    String ODServiceId;
    String ODServiceType;
    Double ODPrice;
    String ODRemarks;
    String ODRemarks2;
    String ODSpecialInstruction;
    String ODDeliveryPackType;
    String PickupId;

    public ItemModal(int id, int serialNo, String items, String qty, String rate, String unitprice, String itemscode, String itemserviceid, String itemservicetype, String itemspecialinstruction, String itemspacktype, String itemremakes2, String discription, String pickupid) {

        this.id = id;
        this.SerialNo = serialNo;
        this.Items = items;
        this.Qty = qty;
        this.Rate = rate;
        this.unitprice = unitprice;
        this.ODItemCode = itemscode;
        this.ODServiceId = itemserviceid;
        this.ODServiceType = itemservicetype;
        this.ODSpecialInstruction = itemspecialinstruction;
        this.ODDeliveryPackType = itemspacktype;
        this.ODRemarks2 = itemremakes2;
        this.discription = discription;
        this.PickupId = pickupid;

        Log.i("ODItemCode","*******ODItemCode*********"+ODItemCode);


    }

    public ItemModal(String items, String qty, String rate, String unitprice, String itemscode, String itemserviceid, String itemservicetype, String itemspecialinstruction, String itemspacktype, String itemremakes2, String discription, String pickupid) {

        this.id = id;
        this.Items = items;
        this.Qty = qty;
        this.Rate = rate;
        this.unitprice = unitprice;
        this.ODItemCode = itemscode;
        this.ODServiceId = itemserviceid;
        this.ODServiceType = itemservicetype;
        this.ODSpecialInstruction = itemspecialinstruction;
        this.ODDeliveryPackType = itemspacktype;
        this.ODRemarks2 = itemremakes2;
        this.discription = discription;
        this.PickupId = pickupid;

        Log.i("ODItemCode","*******ODItemCode*********"+ODItemCode);


    }

    public ItemModal(int id,int serialNo, String gitems, String quantity, String amount, String unitprice, String itemscode, String itemserviceid, String itemservicetype, String itemspecialinstruction, String itemspacktype) {

        Log.i("id","*******id*********"+id);
        Log.i("gitems",""+gitems);
        Log.i("quantity",""+quantity);
        Log.i("amount",""+amount);
        Log.i("discription",""+discription);

        this.id = id;
        this.SerialNo = serialNo;
        this.Items = gitems;
        this.Qty = quantity;
        this.Rate = amount;
        this.unitprice = unitprice;
        this.ODItemCode = itemscode;
        this.ODServiceId = itemserviceid;
        this.ODServiceType= itemservicetype;
        this.ODSpecialInstruction = itemspecialinstruction;
        this.ODDeliveryPackType = itemspacktype;


    }

    public ItemModal(int sno, String itemName, String itemQuntity, String amount) {
        this.SerialNo = sno;
        this.Items = itemName;
        this.Qty = itemQuntity;
        this.Rate = amount;
    }

//    public ItemModal(int id,  String itemName, String itemQuntity, String amount) {
//        this.id = id;
//        this.Items = itemName;
//        this.Qty = itemQuntity;
//        this.Rate = amount;
//    }

    public String getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(int serialNo) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getODOrderCode() {
        return ODOrderCode;
    }

    public void setODOrderCode(String ODOrderCode) {
        this.ODOrderCode = ODOrderCode;
    }

    public String getODItemCode() {
        return ODItemCode;
    }

    public void setODItemCode(String ODItemCode) {
        this.ODItemCode = ODItemCode;
    }


    public String getODServiceId() {
        return ODServiceId;
    }

    public void setODServiceId(String ODServiceId) {
        this.ODServiceId = ODServiceId;
    }

    public String getODServiceType() {
        return ODServiceType;
    }

    public void setODServiceType(String ODServiceType) {
        this.ODServiceType = ODServiceType;
    }

    public Double getODPrice() {
        return ODPrice;
    }

    public void setODPrice(Double ODPrice) {
        this.ODPrice = ODPrice;
    }

    public String getODRemarks() {
        return ODRemarks;
    }

    public void setODRemarks(String ODRemarks) {
        this.ODRemarks = ODRemarks;
    }

    public String getODRemarks2() {
        return ODRemarks2;
    }

    public void setODRemarks2(String ODRemarks2) {
        this.ODRemarks2 = ODRemarks2;
    }

    public String getODSpecialInstruction() {
        return ODSpecialInstruction;
    }

    public void setODSpecialInstruction(String ODSpecialInstruction) {
        this.ODSpecialInstruction = ODSpecialInstruction;
    }

    public String getODDeliveryPackType() {
        return ODDeliveryPackType;
    }

    public void setODDeliveryPackType(String ODDeliveryPackType) {
        this.ODDeliveryPackType = ODDeliveryPackType;
    }

    public String getPickupId() {
        return PickupId;
    }

    public void setPickupId(String pickupId) {
        PickupId = pickupId;
    }
}
