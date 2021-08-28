package com.example.lyondrydelivery.Modal.InsertOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertOrder_responce_modal {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private InsertOrder_data data;
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

    public InsertOrder_data getData() {
        return data;
    }

    public void setData(InsertOrder_data data) {
        this.data = data;
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
