package com.example.lyondrydelivery.Modal.UpdateDeliveryStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateDeliveryStatus_responce_modal {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("PickupRequestId")
    @Expose
    private Integer pickupRequestId;
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

    public Integer getPickupRequestId() {
        return pickupRequestId;
    }

    public void setPickupRequestId(Integer pickupRequestId) {
        this.pickupRequestId = pickupRequestId;
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
