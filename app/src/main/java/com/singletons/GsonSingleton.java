package com.singletons;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * Created by hongy on 7/9/2017.
 */
public class GsonSingleton {
    private static Gson gson;

    public synchronized static Gson get(){
        if (gson == null) gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.
                        LOWER_CASE_WITH_UNDERSCORES).create();

        return gson;
    }


}
