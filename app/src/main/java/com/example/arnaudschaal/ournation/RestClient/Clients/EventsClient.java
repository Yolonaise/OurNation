package com.example.arnaudschaal.ournation.RestClient.Clients;

import com.example.arnaudschaal.ournation.RestClient.APIClient;
import com.example.arnaudschaal.ournation.RestClient.Interfaces.IEvents;
import com.example.arnaudschaal.ournation.RestClient.Models.RequestModels.AddEventRequest;
import com.example.arnaudschaal.ournation.RestClient.Models.RequestModels.GetEventsRequest;
import com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels.BasicResponse;
import com.example.arnaudschaal.ournation.RestClient.Models.ResponseModels.GetEventsResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by arnaud.schaal on 11-01-18.
 */

public class EventsClient extends APIClient {
    private IEvents clientInterface;

    public EventsClient() {
        super();
        clientInterface = super.retrofit.create(IEvents.class);
    }

    public void addEvent(final int creatorId, final String startDate, final String endDate) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Call<BasicResponse> call = clientInterface.addEvent(
                        AddEventRequest.createInstance(creatorId, startDate, endDate)
                    );
                    Response<BasicResponse> response = call.execute();

                    dispatchListener(response, "createAccount");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getEvents(final int creatorId, final String startDate, final String endDate, final int nb) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Call<GetEventsResponse> call = clientInterface.getEvents(
                            GetEventsRequest.createInstance(creatorId, startDate, endDate, nb)
                    );
                    Response<GetEventsResponse> response = call.execute();

                    dispatchListener(response, "createAccount");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
