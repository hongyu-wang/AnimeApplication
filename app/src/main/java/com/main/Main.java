package com.main;

import android.util.JsonReader;

import com.google.gson.Gson;
import com.singletons.GsonSingleton;
import com.webservices.api_auth.ApiData;
import com.webservices.api_auth.ApiImplementation;
import com.webservices.endpoint_builder.Endpoint;
import com.webservices.endpoint_builder.QueryString;
import com.webservices.model.ClientCredModel;
import com.webservices.model.ModelSource;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.webservices.endpoint_builder.RequestType.POST;

/**
 *
 * Created by hongy on 7/5/2017.
 */
public class Main {

    public static void main (String args []){
        //Source of the json file
        ModelSource modelSource = Endpoint.endpointFactory(ClientCredModel.getQueryString(), POST);

        //String representation of the json
        String json = modelSource.getJson();

        //Deserialized version of the json
        ClientCredModel model = GsonSingleton.get().fromJson(json, ClientCredModel.class);

        System.out.println();
    }

}
