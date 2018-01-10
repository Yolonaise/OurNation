package com.example.arnaudschaal.ournation.Session;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by arnaud.schaal on 05-01-18.
 */

public class Context {
    public static String KEY = "SESSION";

    //region Singletion
    private static Context instance;

    public static Context getInstance(){
        if(instance == null)
            instance = new Context();

        return instance;
    }

    private Context(){
        token = "notoken";
    }
    //endregion

    private String token;
    private String id;

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void saveConnection(android.content.Context context, String id, String token){
        SharedPreferences.Editor editor = context
                .getSharedPreferences(KEY, Activity.MODE_PRIVATE).edit();
        editor.putString("token", token);
        editor.putString("userid", id);
        editor.commit();
    }

    public void initToken(android.content.Context context){
        SharedPreferences savedSession = context.getSharedPreferences(KEY,
                Activity.MODE_PRIVATE);
        token = savedSession.getString("token", "noToken");
    }

}
