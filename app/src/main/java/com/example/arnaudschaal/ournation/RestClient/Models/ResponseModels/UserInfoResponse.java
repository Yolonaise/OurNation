package com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels;

import com.example.arnaudschaal.ournation.RestClient.Models.Interface.IJSONMessage;
import com.example.arnaudschaal.ournation.RestClient.Models.RequestModels.UserInfoRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arnaud.schaal on 07-01-18.
 */

public class UserInfoResponse implements IJSONMessage {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id")
    @Expose
    private int id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
