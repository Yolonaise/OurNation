package com.example.arnaudschaal.ournation.RestClient.Clients;

import com.example.arnaudschaal.ournation.RestClient.APIClient;
import com.example.arnaudschaal.ournation.RestClient.Interfaces.IRegistration;
import com.example.arnaudschaal.ournation.RestClient.Models.RequestModels.RegistrationRequest;
import com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels.RegistrationResponse;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by arnaud.schaal on 05-01-18.
 */

public class RegistrationClient extends APIClient {
    private IRegistration clientInterface;

    public RegistrationClient(){
        super();
        clientInterface = super.retrofit.create(IRegistration.class);
    }

    public void createAccount(final String username, final String email, final String password) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Call<RegistrationResponse> call = clientInterface.createAccount(
                            RegistrationRequest.createInstance(username, email, password)
                    );
                    Response<RegistrationResponse> response = call.execute();

                    dispatchListener(response, "createAccount");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void login(final String username, final String email, final String password){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Call<RegistrationResponse> call = clientInterface.login(
                            RegistrationRequest.createInstance(username, email, password)
                    );
                    Response<RegistrationResponse> response = call.execute();

                    dispatchListener(response, "login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getStatus(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Call<RegistrationResponse> call = clientInterface.getStatus();
                    Response<RegistrationResponse> response = call.execute();

                    dispatchListener(response, "getStatus");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
