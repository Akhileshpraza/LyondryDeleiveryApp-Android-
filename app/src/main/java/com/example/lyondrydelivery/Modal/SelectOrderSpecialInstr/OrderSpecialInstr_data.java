package com.example.lyondrydelivery.Modal.SelectOrderSpecialInstr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderSpecialInstr_data {

    @SerializedName("OrderSplId")
    @Expose
    private Integer orderSplId;
    @SerializedName("OrderSplCode")
    @Expose
    private String orderSplCode;
    @SerializedName("OrderSplInstruction")
    @Expose
    private String orderSplInstruction;

    public Integer getOrderSplId() {
        return orderSplId;
    }

    public void setOrderSplId(Integer orderSplId) {
        this.orderSplId = orderSplId;
    }

    public String getOrderSplCode() {
        return orderSplCode;
    }

    public void setOrderSplCode(String orderSplCode) {
        this.orderSplCode = orderSplCode;
    }

    public String getOrderSplInstruction() {
        return orderSplInstruction;
    }

    public void setOrderSplInstruction(String orderSplInstruction) {
        this.orderSplInstruction = orderSplInstruction;
    }
}
