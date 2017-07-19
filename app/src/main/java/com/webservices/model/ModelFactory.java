package com.webservices.model;


import com.google.gson.reflect.TypeToken;
import com.singletons.GsonSingleton;
import com.webservices.endpointBuilder.Endpoint;
import com.webservices.endpointBuilder.QueryString;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public final class ModelFactory {

    public static ClientCredModel getCurrentClient() {
        return currentClient;
    }

    //This is the current client for the model
    private static ClientCredModel currentClient;


    private static <T extends Model> Endpoint getEndpoint(Class<T> className, String ... args){
        Endpoint m = null;
        Method method;
        try {

            method = className.getMethod("getQueryString", String [].class);
            m = Endpoint.endpointFactory((QueryString) method.invoke(null, new Object[]{args}));

        } catch (NoSuchMethodException e) {
            try {
                method = className.getMethod("getQueryString");
                m = Endpoint.endpointFactory((QueryString) method.invoke(null));

            } catch (Exception e1) {
                e1.printStackTrace();
                System.exit(-1);
            }
        } catch (Exception e1){
            e1.printStackTrace();
            System.exit(-1);

        }

        return m;
    }

    public static <T extends Model> T [] getModelList(Class<T> className, String ... args){
        String json = getEndpoint(className, args).getJson();



        return (T [])GsonSingleton.get().fromJson(json , Array.newInstance(className, 0).getClass());

    }

    public static <T extends Model> T getModel(Class<T> className, String ... args) {
        return GsonSingleton.get().fromJson(getEndpoint(className, args).getJson(), className);
    }


    public static void initTest(){
        currentClient = getModel(ClientCredModel.class);
    }

    public static void initAndroid() throws ExecutionException, InterruptedException {
        /*
        AsyncTask<Class<ClientCredModel>, Void, ClientCredModel> XD = new AsyncTask<Class<ClientCredModel>, Void, ClientCredModel>() {
            @Override
            protected ClientCredModel doInBackground(Class<ClientCredModel>... classes) {
                return ModelFactory.getModel(classes[0]);
            }
            protected void onPostExecute(ClientCredModel result) {
                if (result != null) {
                    ModelFactory.setCurrentClient(result);
                }
            }
        };*/
        RetrieveModelTask<ClientCredModel> xd = new RetrieveModelTask<>();
        currentClient = xd.execute(ClientCredModel.class).get();
    }

}
