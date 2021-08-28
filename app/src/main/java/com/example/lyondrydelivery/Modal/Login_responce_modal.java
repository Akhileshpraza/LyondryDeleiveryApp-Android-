package com.example.lyondrydelivery.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login_responce_modal {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Login_data data;
    @SerializedName("Error")
    @Expose
    private Boolean error;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("HttpResponseHeader")
    @Expose
    private String httpResponseHeader;

    public Login_responce_modal(String httpResponseHeader) {
        this.httpResponseHeader =httpResponseHeader;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Login_data getData() {
        return data;
    }

    public void setData(Login_data data) {
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

    public String getHttpResponseHeader() {
        return httpResponseHeader;
    }

    public void setHttpResponseHeader(String httpResponseHeader) {
        this.httpResponseHeader = httpResponseHeader;
    }
}
