package com.example.lyondrydelivery.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectPickupDelivery_responce_modal {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("PickupRequestList")
    @Expose
    private List<SelectPickupDelivery_data> pickupRequestList = null;
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

    public List<SelectPickupDelivery_data> getPickupRequestList() {
        return pickupRequestList;
    }

    public void setPickupRequestList(List<SelectPickupDelivery_data> pickupRequestList) {
        this.pickupRequestList = pickupRequestList;
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
