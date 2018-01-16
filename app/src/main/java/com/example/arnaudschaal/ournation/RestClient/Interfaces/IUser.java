package com.example.arnaudschaal.ournation.RestClient.Interfaces;

import com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by arnaud.schaal on 07-01-18.
 */

public interface IUser {

    @POST("api/User/GetUserInfo/ById/{id}")
    Call<UserInfoResponse> getUserInfoById(@Path("id") int id);

    @POST("api/User/GetUserInfo/ByUsername/{username}")
    Call<UserInfoResponse> getUserInfoByUsername(@Path("username}") String username);
}
