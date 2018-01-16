package com.example.arnaudschaal.ournation.RestClient.Models.RequestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arnaud.schaal on 07-01-18.
 */

public class UserInfoRequest {
    @SerializedName("username")
    @Expose
    private String Username;
    @SerializedName("email")
    @Expose
    private String Email;
    @SerializedName("id")
    @Expose
    private int Id;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public static UserInfoRequest createInstance(String username, String email, int id){
        UserInfoRequest res = new UserInfoRequest();

        res.Username = username;
        res.Email = email;
        res.Id = id;

        return res;
    }
}
