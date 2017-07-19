package com.webservices.model;


import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.singletons.GsonSingleton;
import com.singletons.RequestQueueSingleton;
import com.webservices.endpointBuilder.QueryString;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public final class ModelFactory {
    private static final String TAG = "ModelFactory";


    //This is the current client for the model
    private static ClientCredModel currentClient;
    public static ClientCredModel getCurrentClient() {
        return currentClient;
    }

    private final static String BASE_ENDPOINT = "https://anilist.co/api/";

    private static <T extends Model> QueryString getEndpoint(Class<T> className, String ... args){
        QueryString m = null;
        Method method;
        try {

            method = className.getMethod("getQueryString", String [].class);
            m = (QueryString) method.invoke(null, new Object[]{args});

        } catch (NoSuchMethodException e) {
            try {
                method = className.getMethod("getQueryString");
                m = (QueryString) method.invoke(null);

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


    public static <T extends Model> void requestModelList(Context context, final Class<T> className, String ... args){
        QueryString url = getEndpoint(className, args);
        StringRequest request = new StringRequest(
                url.getRequestType(),
                BASE_ENDPOINT + url.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        EventBus.getDefault().post(
                                GsonSingleton.get().fromJson(response ,
                                        Array.newInstance(className, 0).getClass()));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString());
                    }
                }
        );
        RequestQueueSingleton.get(context).add(request);

    }

    public static <T extends Model> void requestModel(Context context, final Class<T> className, String ... args) {
        QueryString url = getEndpoint(className, args);
        StringRequest request = new StringRequest(
                url.getRequestType(),
                BASE_ENDPOINT + url.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        EventBus.getDefault().post(GsonSingleton.get().fromJson(response, className));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString());
                    }
                }
        );
        RequestQueueSingleton.get(context).add(request);
    }

    public static void setCurrentClient(ClientCredModel currentClient) {
        ModelFactory.currentClient = currentClient;
    }
}
