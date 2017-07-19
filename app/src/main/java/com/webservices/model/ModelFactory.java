package com.webservices.model;

import com.singletons.GsonSingleton;
import com.webservices.endpointBuilder.Endpoint;
import com.webservices.endpointBuilder.QueryString;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

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


    public static void init() throws ExecutionException, InterruptedException {
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
