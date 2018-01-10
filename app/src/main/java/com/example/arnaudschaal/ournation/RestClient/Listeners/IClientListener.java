package com.example.arnaudschaal.ournation.RestClient.Listeners;

import com.example.arnaudschaal.ournation.RestClient.Models.Interface.IJSONMessage;

/**
 * Created by arnaud.schaal on 05-01-18.
 */

public interface IClientListener {

    void onResponseEnd(IJSONMessage response, String method);

    void onResponseErrorEnd(Object response, String method);
}
