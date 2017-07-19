package com.webservices.model;

import android.os.AsyncTask;

import com.singletons.GsonSingleton;
import com.webservices.endpointBuilder.Endpoint;
import com.webservices.endpointBuilder.QueryString;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
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

    public static <T> T getModel(Class<T> className, String ... args) {
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


        return GsonSingleton.get().fromJson(m.getJson(), className);



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
