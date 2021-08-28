package com.example.lyondrydelivery.Modal.PickupComplite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PickupComplite_responce_modal {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("DeliveryDetailsList")
    @Expose
    private List<PickupComplete_data> deliveryDetailsList = null;
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

    public List<PickupComplete_data> getDeliveryDetailsList() {
        return deliveryDetailsList;
    }

    public void setDeliveryDetailsList(List<PickupComplete_data> deliveryDetailsList) {
        this.deliveryDetailsList = deliveryDetailsList;
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
