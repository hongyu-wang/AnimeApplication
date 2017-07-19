package com.webservices.model;

import com.singletons.GsonSingleton;
import com.webservices.endpointBuilder.QueryString;
import com.webservices.endpointBuilder.Endpoint;

import java.lang.reflect.Method;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public final class ModelFactory {



    //This is the current client for the model
    private static ClientCredModel currentClient;

    public static <T> T getModel(Class<T> className) {


        try {
            Method method;

            method = className.getMethod("getQueryString");


            Endpoint m = Endpoint.endpointFactory((QueryString) method.invoke(null));
            return GsonSingleton.get().fromJson(m.getJson(), className);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public static void init(){
        currentClient = ModelFactory.getModel(ClientCredModel.class);
    }

}
