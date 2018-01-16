package com.example.arnaudschaal.ournation.Session;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.arnaudschaal.ournation.RestClient.Models.Objects.Event;

import java.util.ArrayList;

/**
 * Created by arnaud.schaal on 05-01-18.
 */

public class Context {

    //region Singletion
    private static Context instance;

    public static Context getInstance(){
        if(instance == null)
            instance = new Context();

        return instance;
    }

    private Context(){
        token = "notoken";
        id = -1;
        events = new ArrayList<>();
    }
    //endregion

    private String token;
    private int id;
    private ArrayList<Event> events;

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public ArrayList<Event> getEvents(){
        return events;
    }

    public void setEvents(ArrayList events){
        this.events = events;
    }

    public void addEvent(Event e){
        if(e != null && events != null)
            events.add(e);
    }

    public void addEvents(ArrayList<Event> events){
        if(events != null && this.events != null)
            events.addAll(events);
    }

    public void saveConnection(android.content.Context context, int id, String token){
        SharedPreferences saved_values = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=saved_values.edit();
        editor.putInt("userId", id);
        editor.putString("token", token);

        editor.commit();

        setId(id);
        setToken(token);
    }

    public void initConnection(android.content.Context context){
        SharedPreferences savedSession = PreferenceManager.getDefaultSharedPreferences(context);

        setId(savedSession.getInt("userId", -1));
        setToken(savedSession.getString("token", "noToken"));
    }

}
