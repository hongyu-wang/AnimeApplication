package com.singletons;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Update this to include images if necessary.
 *
 * Created by hongy on 7/19/2017.
 */
public class RequestQueueSingleton {

    private static RequestQueue myInstance;

    public static synchronized RequestQueue get(Context context){
        if (myInstance == null) myInstance = Volley.newRequestQueue(context.getApplicationContext());
        return myInstance;
    }

}
