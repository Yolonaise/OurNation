package com.example.arnaudschaal.ournation.RestClient.Interfaces;

import com.example.arnaudschaal.ournation.RestClient.Models.RequestModels.AddEventRequest;
import com.example.arnaudschaal.ournation.RestClient.Models.RequestModels.GetEventsRequest;
import com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels.BasicResponse;
import com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels.GetEventsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by arnaud.schaal on 11-01-18.
 */

public interface IEvents {
    @POST("api/Events/Add")
    Call<BasicResponse> addEvent(@Body AddEventRequest request);

    @POST("api/Events/GetByUserID")
    Call<GetEventsResponse> getEvents(@Body GetEventsRequest request);
}
