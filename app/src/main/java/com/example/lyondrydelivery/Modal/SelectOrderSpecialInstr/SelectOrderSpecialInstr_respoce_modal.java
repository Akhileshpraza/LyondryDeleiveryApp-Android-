package com.example.lyondrydelivery.Modal.SelectOrderSpecialInstr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectOrderSpecialInstr_respoce_modal {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("OrderSpecialInstrList")
    @Expose
    private List<OrderSpecialInstr_data> orderSpecialInstrList = null;
    @SerializedName("Error")
    @Expose
    private Boolean error;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<OrderSpecialInstr_data> getOrderSpecialInstrList() {
        return orderSpecialInstrList;
    }

    public void setOrderSpecialInstrList(List<OrderSpecialInstr_data> orderSpecialInstrList) {
        this.orderSpecialInstrList = orderSpecialInstrList;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
