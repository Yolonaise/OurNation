package com.example.arnaudschaal.ournation.RestClient.Interfaces;

import com.example.arnaudschaal.ournation.BuildConfig;
import com.example.arnaudschaal.ournation.RestClient.Models.RequestModels.RegistrationRequest;
import com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels.RegistrationResponse;
import com.example.arnaudschaal.ournation.Session.Context;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by arnaud.schaal on 03-01-18.
 */

public interface IRegistration {

    @POST("api/Registration/CreateAccount")
    Call<RegistrationResponse> createAccount(@Body RegistrationRequest request);

    @POST("api/Registration/Login")
    Call<RegistrationResponse> login(@Body RegistrationRequest request);

    @GET("api/Registration/TestConnection")
    Call<RegistrationResponse> getStatus();
}
