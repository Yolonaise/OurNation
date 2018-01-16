package com.example.arnaudschaal.ournation.RestClient.Models.RequestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arnaud.schaal on 11-01-18.
 */

public class GetEventsRequest{
    @SerializedName("creatorId")
    @Expose
    public int CreatorId;
    @SerializedName("startDate")
    @Expose
    public String StartDate;
    @SerializedName("endDate")
    @Expose
    public String EndDate;
    @SerializedName("number")
    @Expose
    public int Number;

    public int getCreatorId() {
        return CreatorId;
    }

    public void setCreatorId(int creatorId) {
        CreatorId = creatorId;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public static GetEventsRequest createInstance(int creatorId, String startDate, String endDate, int nb){
        GetEventsRequest res = new GetEventsRequest();

        res.setCreatorId(creatorId);
        res.setStartDate(startDate);
        res.setEndDate(endDate);
        res.setNumber(nb);

        return res;
    }
}
