package com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels;

import com.example.arnaudschaal.ournation.RestClient.Models.Interface.IJSONMessage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arnaud.schaal on 03-01-18.
 */

public class RegistrationResponse extends BasicResponse implements IJSONMessage {


    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("token")
    @Expose
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}