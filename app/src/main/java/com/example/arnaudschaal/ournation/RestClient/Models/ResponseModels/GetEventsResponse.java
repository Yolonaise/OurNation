package com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels;

import com.example.arnaudschaal.ournation.RestClient.Models.Interface.IJSONMessage;
import com.example.arnaudschaal.ournation.RestClient.Models.Objects.Event;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by arnaud.schaal on 11-01-18.
 */

public class GetEventsResponse extends BasicResponse implements IJSONMessage {
    @SerializedName("events")
    @Expose
    public ArrayList<Event> Events;
}
