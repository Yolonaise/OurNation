package com.example.arnaudschaal.ournation.RestClient.Models.RequestModels;

import com.example.arnaudschaal.ournation.RestClient.Models.Interface.IJSONMessage;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by arnaud.schaal on 11-01-18.
 */

public class AddEventRequest implements IJSONMessage {
    @SerializedName("creatorId")
    @Expose
    public int CreatorId;
    @SerializedName("startDate")
    @Expose
    public String StartDate;
    @SerializedName("endDate")
    @Expose
    public String EndDate;
    @SerializedName("titre")
    @Expose
    public String Titre;
    @SerializedName("description")
    @Expose
    public String Description;

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

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

    public static AddEventRequest createInstance(int creatorId, String startDate, String endDate){
        AddEventRequest res = new AddEventRequest();

        res.setCreatorId(creatorId);
        res.setStartDate(startDate);
        res.setEndDate(endDate);

        return res;
    }
}
