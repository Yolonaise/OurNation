package com.example.arnaudschaal.ournation.RestClient.Clients;

import com.example.arnaudschaal.ournation.RestClient.APIClient;
import com.example.arnaudschaal.ournation.RestClient.Interfaces.IUser;
import com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels.UserInfoResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by arnaud.schaal on 07-01-18.
 */

public class UserClient extends APIClient {
    private IUser clientInterface;

    public UserClient(){
        super();
        clientInterface = super.retrofit.create(IUser.class);
    }

    public void getUserInfoByID(final String id){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Call<UserInfoResponse> call = clientInterface.getUserInfoById(id);
                    Response<UserInfoResponse> response = call.execute();

                    dispatchListener(response, "getUserInfo");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getUserInfoByUsername(final String username){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Call<UserInfoResponse> call = clientInterface.getUserInfoByUsername(username);
                    Response<UserInfoResponse> response = call.execute();

                    dispatchListener(response, "getUserInfo");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
