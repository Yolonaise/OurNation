package com.example.arnaudschaal.ournation.RestClient.Models.RequestModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by arnaud.schaal on 11-01-18.
 */

public class GetEventsRequest{
    @SerializedName("creatorId")
    @Expose
    public int CreatorId;
    @SerializedName("startDate")
    @Expose
    public Date StartDate;
    @SerializedName("endDate")
    @Expose
    public Date EndDate;
    @SerializedName("number")
    @Expose
    public int Number;

    public int getCreatorId() {
        return CreatorId;
    }

    public void setCreatorId(int creatorId) {
        CreatorId = creatorId;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public static GetEventsRequest createInstance(int creatorId, Date startDate, Date endDate, int nb){
        GetEventsRequest res = new GetEventsRequest();

        res.setCreatorId(creatorId);
        res.setStartDate(startDate);
        res.setEndDate(endDate);
        res.setNumber(nb);

        return res;
    }
}
