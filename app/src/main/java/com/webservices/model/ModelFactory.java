package com.webservices.model;


import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.events.ModelFactoryInitialized;
import com.singletons.GsonSingleton;
import com.singletons.RequestQueueSingleton;
import com.webservices.endpointBuilder.QueryString;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This class is the be all end all of all Model classes
 *
 * This class is simple, you can retrieve any model using a template argument.
 * Created by hongy on 7/9/2017.
 */
public class ModelFactory {
    private static final String TAG = "ModelFactory";
    private final static String BASE_ENDPOINT = "https://anilist.co/api/";

    //This is the inbuilt hidden modelFactory instance required for subscription to Eventbus
    private static ModelFactory modelFactory;

    //This is the current client model.
    private static ClientCredModel currentClient;
    public static ClientCredModel getCurrentClient() {
        return currentClient;
    }

    /**
     * This class is the initializer of ModelFactory. This should be called once on app start
     * Subsequent calls will do nothing
     *
     * @param context MainActivity
     */
    public static void init(Context context) {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
            ModelFactory.requestModel(context, ClientCredModel.class);
        }
    }

    /**
     * This function is called when clientcredmodel is called.
     * @param model The model that exists
     */
    @Subscribe
    public void onEvent(ClientCredModel model){
        Log.d(TAG, "Received client cred model");
        currentClient = model;
        EventBus.getDefault().post(new ModelFactoryInitialized());
    }

    //Private constructor as no instance of this (other than the private static instance) should exist
    private ModelFactory(){
        EventBus.getDefault().register(this);
    }

    //This is an inner helper function that performs reflection in order to invoke getQueryString
    private static <T extends Model> QueryString getEndpoint(Class<T> className, String ... args){
        QueryString m = null;
        Method method;
        try {
            //Attempts to get a method with parameters. If this exists
            method = className.getMethod("getQueryString", String [].class);

            //We invoke it and pass the parameters.
            m = (QueryString) method.invoke(null, new Object[]{args});
            //if it doesn't
        } catch (NoSuchMethodException e) {
            try {
                //try to get a function that requires no parameters
                method = className.getMethod("getQueryString");

                //if so, we invoke it
                m = (QueryString) method.invoke(null);
            //This means that no such query string exists
            } catch (NoSuchMethodException e1) {
                Log.e(TAG, "The model you wrote did not implement getQueryString.\n" +
                        "Please see the Model interface for more information.");
                System.exit(-1);
            } catch (Exception e1){
                e.printStackTrace();
                System.exit(-1);
            }
        } catch (Exception e1){
            e1.printStackTrace();
            System.exit(-1);
        }

        return m;
    }

    /**
     * This function should be called when you require a model that comes in the form of a JSON list
     * This function will invoke the getQueryString function and then create an event callback loop.
     *
     * Make sure that your context is subscribed to event bus before calling this method
     *
     * @param context the activity or fragment that requested the model
     * @param className Class name of the model
     * @param args Arguments the url requires
     * @param <T> the class of the model
     */
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


    /**
     * This function should be called when you require a model that comes in the form of a JSON
     * This function will invoke the getQueryString function and then create an event callback loop.
     *
     * Make sure that your context is subscribed to event bus before calling this method
     *
     * @param context the activity or fragment that requested the model
     * @param className Class name of the model
     * @param args Arguments the url requires
     * @param <T> the class of the model
     */
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


}
