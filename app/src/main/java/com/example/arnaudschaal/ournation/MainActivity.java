package com.example.arnaudschaal.ournation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.arnaudschaal.ournation.Activities.DashBoardActivity;
import com.example.arnaudschaal.ournation.RestClient.Clients.RegistrationClient;
import com.example.arnaudschaal.ournation.RestClient.Listeners.IClientListener;
import com.example.arnaudschaal.ournation.RestClient.Models.Interface.IJSONMessage;
import com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels.RegistrationResponse;
import com.example.arnaudschaal.ournation.Session.Context;
import com.example.arnaudschaal.ournation.UIElement.LoginControl;
import com.transitionseverywhere.TransitionManager;

public class MainActivity extends AppCompatActivity {
    private LoginControl loginControl;
    private ViewGroup mainContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        initUserControl();
        initContext();
    }

    private void initUserControl(){
        mainContainer = (ViewGroup) findViewById(R.id.main_activity_main_container);
        loginControl = (LoginControl) findViewById(R.id.main_activity_login_control);

        loginControl.setLoginEndAction(new Runnable() {
            @Override
            public void run() {
                redirectToMainPage();
            }
        });
    }

    private void initContext(){
        Context.getInstance().initToken(this);

        RegistrationClient client = new RegistrationClient();
        client.setClientListener(new IClientListener() {
            @Override
            public void onResponseEnd(IJSONMessage response, String method) {
                if(response instanceof RegistrationResponse){
                    RegistrationResponse registrationResponse = (RegistrationResponse) response;
                    switch (registrationResponse.getStatus()){
                        case "200":
                            redirectToMainPage();
                            break;
                        default:
                            displayConnection();
                            break;
                    }
                }
            }

            @Override
            public void onResponseErrorEnd(Object error, String method) {
                displayConnection();
            }
        });

        client.getStatus();
    }

    private void displayConnection(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TransitionManager.beginDelayedTransition(mainContainer);
                loginControl.setVisibility(View.VISIBLE);
            }
        });
    }

    private void redirectToMainPage(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), DashBoardActivity.class);
                startActivity(intent);
            }
        });
    }
}
