package com.main;

import android.util.JsonReader;

import com.google.gson.Gson;
import com.singletons.GsonSingleton;
import com.webservices.api_auth.ApiData;
import com.webservices.api_auth.ApiImplementation;
import com.webservices.endpoint_builder.Endpoint;
import com.webservices.endpoint_builder.QueryString;
import com.webservices.model.ClientCredModel;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.webservices.endpoint_builder.RequestType.POST;

/**
 *
 * Created by hongy on 7/5/2017.
 */
public class Main {

    public static void main (String args []){
        Endpoint endpoint = Endpoint.endpointFactory(ClientCredModel.getQueryString(), POST);
        String arg = endpoint.getJson();

        ClientCredModel model = GsonSingleton.get().fromJson(arg, ClientCredModel.class);
        System.out.println();
    }

}
