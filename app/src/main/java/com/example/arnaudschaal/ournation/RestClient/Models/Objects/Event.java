package com.example.arnaudschaal.ournation.RestClient.Models.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by arnaud.schaal on 11-01-18.
 */

public class Event {
    @SerializedName("id")
    @Expose
    public int Id;

    @SerializedName("creator_id")
    @Expose
    public int creator_id;

    @SerializedName("created_date")
    @Expose
    public Date CreatedDate;

    @SerializedName("start_date")
    @Expose
    public Date StartDate;

    @SerializedName("end_date")
    @Expose
    public Date EnDdate;

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

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEnDdate() {
        return EnDdate;
    }

    public void setEnDdate(Date enDdate) {
        EnDdate = enDdate;
    }
}
