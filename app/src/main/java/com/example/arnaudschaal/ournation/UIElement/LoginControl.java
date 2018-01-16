package com.example.arnaudschaal.ournation.UIElement;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arnaudschaal.ournation.R;
import com.example.arnaudschaal.ournation.RestClient.Clients.RegistrationClient;
import com.example.arnaudschaal.ournation.RestClient.Listeners.IClientListener;
import com.example.arnaudschaal.ournation.RestClient.Models.Interface.IJSONMessage;
import com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels.RegistrationResponse;
import com.transitionseverywhere.Recolor;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by arnaud.schaal on 05-01-18.
 */

public class LoginControl extends LinearLayout{

    public enum Menu{
        Login,
        SignUp
    }

    //region container
    private ViewGroup mainContainer;
    private ViewGroup loginContainer;
    private ViewGroup signupContainer;
    private ViewGroup buttonContainer;
    //region login
    private EditText loginEditText;
    private EditText passwordEditText;
    private Button loginButton;
    //region signup
    private EditText signupUsernameEditText;
    private EditText signupEmailEditText;
    private EditText signupPasswordEditText;
    private Button signUpButton;

    private TextView loading;
    private Menu currentMenu;
    private Runnable loginEndAction;
    private boolean isRunning;

    public LoginControl(Context context) {
        super(context);

        init(context);
    }

    public LoginControl(Context context, AttributeSet attrs){
        super(context, attrs);

        init(context);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);

        displayMenu(currentMenu, true);
    }

    public boolean hasOpenMenu(){
        return signupContainer.getVisibility() == VISIBLE ||  loginContainer.getVisibility() == VISIBLE;
    }

    public void setLoginEndAction(Runnable action){
        this.loginEndAction = action;
    }

    private void init(Context context){
        LayoutInflater i = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        i.inflate(R.layout.control_login, this);
        isRunning = false;

        mainContainer = (ViewGroup) findViewById(R.id.control_login_main_container);
        loginContainer = (ViewGroup) findViewById(R.id.control_login_container);
        signupContainer = (ViewGroup) findViewById(R.id.control_signup_container);
        buttonContainer = (ViewGroup) findViewById(R.id.control_login_button_container);

        loginEditText = (EditText) findViewById(R.id.control_login_login_editText);
        passwordEditText = (EditText) findViewById(R.id.control_login_password_editText);
        loginButton = (Button) findViewById(R.id.control_login_button);

        signupUsernameEditText = (EditText) findViewById(R.id.control_signup_username_editText);
        signupEmailEditText = (EditText) findViewById(R.id.control_signup_email_editText);
        signupPasswordEditText = (EditText) findViewById(R.id.control_signup_password_editText);
        signUpButton = (Button) findViewById(R.id.control_signup_button);

        loading = (TextView) findViewById(R.id.control_login_loading);
        loading.setVisibility(GONE);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRunning)
                    return;

                switch (currentMenu){
                    case SignUp:
                        displayMenu(Menu.Login, true);
                        break;
                    case Login:
                        processLogin();
                        break;
                }
            }
        });

        signUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isRunning)
                    return;

                switch (currentMenu){
                    case SignUp:
                        processSignup();
                        break;
                    case Login:
                        displayMenu(Menu.SignUp, true);
                        break;
                }
            }
        });

        currentMenu = Menu.Login;
    }


    public void displayMenu(Menu menu, boolean animate){
        if(menu == currentMenu && hasOpenMenu())
            return;

        if(animate)
            TransitionManager.beginDelayedTransition(mainContainer, new TransitionSet()
                    .addTransition(new Slide(Gravity.BOTTOM)));

        switch (menu){
            case Login:
                signupContainer.setVisibility(GONE);
                loginContainer.setVisibility(VISIBLE);

                if(buttonContainer.getVisibility() == VISIBLE){
                    if(animate)
                        TransitionManager.beginDelayedTransition(buttonContainer, new Recolor());
                }
                else{
                    if(animate)
                        TransitionManager.beginDelayedTransition(buttonContainer, new TransitionSet()
                            .addTransition(new Slide(Gravity.BOTTOM)));

                    buttonContainer.setVisibility(VISIBLE);
                }
                loginButton.setBackgroundDrawable(
                        new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
                signUpButton.setBackgroundDrawable(
                        new ColorDrawable(getResources().getColor(R.color.colorSecondaryDark)));
                currentMenu = Menu.Login;
                break;
            case SignUp:
                loginContainer.setVisibility(GONE);
                signupContainer.setVisibility(VISIBLE);

                if(buttonContainer.getVisibility() == VISIBLE){
                    if(animate)
                        TransitionManager.beginDelayedTransition(buttonContainer, new Recolor());
                }
                else{
                    if(animate)
                        TransitionManager.beginDelayedTransition(buttonContainer, new TransitionSet()
                                .addTransition(new Slide(Gravity.BOTTOM)));

                    buttonContainer.setVisibility(VISIBLE);
                }
                loginButton.setBackgroundDrawable(
                        new ColorDrawable(getResources().getColor(R.color.colorSecondaryDark)));
                signUpButton.setBackgroundDrawable(
                        new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));

                currentMenu = Menu.SignUp;
                break;
        }
    }

    private void processLogin(){
        setButtonClickable(false);
        String username = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(username.isEmpty()){
            displayToast("Username is empty");
            return;
        }

        if(password.isEmpty()){
            displayToast("Password is empty");
            return;
        }

        RegistrationClient client = new RegistrationClient();
        client.setClientListener(new IClientListener() {
            @Override
            public void onResponseEnd(IJSONMessage response, String method) {
                if(response == null)
                    displayToast("We have met an error, try again later.");
                if(response instanceof RegistrationResponse){
                    RegistrationResponse loginResponse = (RegistrationResponse)response;
                    switch (loginResponse.getStatus()){
                        case "200":
                            com.example.arnaudschaal.ournation.Session.Context.getInstance().saveConnection(getContext(), loginResponse.getId(), loginResponse.getToken());
                            if(loginEndAction != null)
                                loginEndAction.run();
                            break;
                        default:
                            displayToast(loginResponse.getMessage());
                            break;
                    }
                }
                show(true);
            }

            @Override
            public void onResponseErrorEnd(Object response, String method) {
                show(true);
                displayToast("Server error, try again later.");
            }
        });

        hide(true);
        client.login(username, null, password);
    }

    private void processSignup(){
        setButtonClickable(false);

        String username = signupUsernameEditText.getText().toString();
        String password = signupPasswordEditText.getText().toString();
        String email = signupEmailEditText.getText().toString();

        if(username.isEmpty()){
            displayToast("Username is empty");
            return;
        }

        if(password.isEmpty()){
            displayToast("Password is empty");
            return;
        }

        if(email.isEmpty()){
            displayToast("Email is empty");
            return;
        }

        RegistrationClient client = new RegistrationClient();
        client.setClientListener(new IClientListener() {
            @Override
            public void onResponseEnd(IJSONMessage response, String method) {
                if(response == null)
                    displayToast("We have met an error, try again later.");
                if(response instanceof RegistrationResponse){
                    RegistrationResponse loginResponse = (RegistrationResponse)response;
                    switch (loginResponse.getStatus()){
                        case "200":
                            com.example.arnaudschaal.ournation.Session.Context.getInstance().saveConnection(getContext(), loginResponse.getId(), loginResponse.getToken());
                            if(loginEndAction != null)
                                loginEndAction.run();
                            break;
                        default:
                            displayToast(loginResponse.getMessage());
                            break;
                    }
                }
                show(true);
            }

            @Override
            public void onResponseErrorEnd(Object response, String method) {
                show(true);
                displayToast("We have met an error, try again later.");
            }
        });

        hide(true);
        client.createAccount(username, email, password);
    }

    private void displayToast(final String text){
        post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), text, LENGTH_SHORT)
                        .show();
            }
        });
    }

    public void hide(final boolean animate){
        isRunning = true;
        post(new Runnable() {
            @Override
            public void run() {
                if(animate)
                    TransitionManager.beginDelayedTransition(mainContainer, new TransitionSet()
                            .addTransition(new Slide(Gravity.BOTTOM)));

                if(loginContainer.getVisibility() == VISIBLE)
                    loginContainer.setVisibility(GONE);

                if(signupContainer.getVisibility() == VISIBLE)
                    signupContainer.setVisibility(GONE);

                buttonContainer.setVisibility(GONE);
                loading.setVisibility(VISIBLE);
            }
        });
    }

    public void show(final boolean animate){
        isRunning = false;
        post(new Runnable() {
            @Override
            public void run() {
                loading.setVisibility(GONE);
                displayMenu(currentMenu, animate);
                setButtonClickable(true);

            }
        });
    }

    private void setButtonClickable(boolean clickable){
        loginButton.setClickable(clickable);
        signUpButton.setClickable(clickable);
    }
}
