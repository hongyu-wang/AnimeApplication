package com.webservices.model;

import com.singletons.GsonSingleton;
import com.webservices.endpoint_builder.Endpoint;
import com.webservices.endpoint_builder.RequestType;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public final class ModelFactory {


    //This is the current client for the model
    private static ClientCredModel currentClient;

    public static <T> T getPostModel(Class<T> className){
        ModelSource m = Endpoint.endpointFactory(ClientCredModel.getQueryString(), RequestType.POST);
        return GsonSingleton.get().fromJson(m.getJson(), className);
    }

    public static <T> T getGetModel(Class<T> className){
        ModelSource m = Endpoint.endpointFactory(ClientCredModel.getQueryString(), RequestType.GET);
        return GsonSingleton.get().fromJson(m.getJson(), className);
    }

    public static void init(){


    }

}
