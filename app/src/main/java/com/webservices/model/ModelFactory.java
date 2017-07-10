package com.webservices.model;

import com.singletons.GsonSingleton;
import com.webservices.endpoint_builder.Endpoint;
import com.webservices.endpoint_builder.QueryString;
import com.webservices.endpoint_builder.RequestType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public final class ModelFactory {


    //This is the current client for the model
    private static ClientCredModel currentClient;

    public static <T> T getModel(Class<T> className) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method;

        method = className.getMethod("getQueryString");


        ModelSource m = Endpoint.endpointFactory((QueryString) method.invoke(null));
        return GsonSingleton.get().fromJson(m.getJson(), className);
    }

    public static <T> T getGetModel(Class<T> className){
        ModelSource m = Endpoint.endpointFactory(ClientCredModel.getQueryString());
        return GsonSingleton.get().fromJson(m.getJson(), className);
    }

    public static void init(){


    }

}
