package com.example.arnaudschaal.ournation.RestClient.Models.RequestModels;

import com.example.arnaudschaal.ournation.RestClient.Models.Interface.IJSONMessage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arnaud.schaal on 03-01-18.
 */

public class RegistrationRequest implements IJSONMessage {
    @SerializedName("username")
    @Expose
    private String Username;
    @SerializedName("email")
    @Expose
    private String Email;
    @SerializedName("password")
    @Expose
    private String Password;

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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = Password;
    }

    public static RegistrationRequest createInstance(String username, String email, String password){
        RegistrationRequest res = new RegistrationRequest();

        res.Username = username;
        res.Email = email;
        res.Password = password;

        return res;
    }
}
