package com.example.lyondrydelivery.Modal.InsertOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertOrder_data {
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("OrderCode")
    @Expose
    private String orderCode;

    public InsertOrder_data(String status, String orderCode) {
        this.status =status;
        this.orderCode = orderCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }


}
