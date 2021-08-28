package com.example.lyondrydelivery.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login_data {
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("UserPassword")
    @Expose
    private String userPassword;
    @SerializedName("UserStoreId")
    @Expose
    private Integer userStoreId;
    @SerializedName("UserID")
    @Expose
    private Integer userID;

    public Login_data(String userName, String userPassword,Integer userStoreId, Integer userID) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userStoreId = userStoreId;
        this.userID = userID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getUserStoreId() {
        return userStoreId;
    }

    public void setUserStoreId(Integer userStoreId) {
        this.userStoreId = userStoreId;
    }
}
