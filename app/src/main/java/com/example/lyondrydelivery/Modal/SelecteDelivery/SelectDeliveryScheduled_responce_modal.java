package com.example.lyondrydelivery.Modal.SelecteDelivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectDeliveryScheduled_responce_modal {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("DeliveryList")
    @Expose
    private List<DeliverySchedualedList_Data> deliveryList = null;
    @SerializedName("DeliveryDetailsList")
    @Expose
    private List<DeliveryDetailsList_data> deliveryDetailsList = null;
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

    public List<DeliverySchedualedList_Data> getDeliveryList() {
        return deliveryList;
    }

    public void setDeliveryList(List<DeliverySchedualedList_Data> deliveryList) {
        this.deliveryList = deliveryList;
    }
    public List<DeliveryDetailsList_data> getDeliveryDetailsList() {
        return deliveryDetailsList;
    }

    public void setDeliveryDetailsList(List<DeliveryDetailsList_data> deliveryDetailsList) {
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
