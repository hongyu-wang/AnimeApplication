package com.singletons;

import com.google.gson.Gson;

/**
 *
 * Created by hongy on 7/9/2017.
 */
public class GsonSingleton {
    private static Gson gson;

    public static Gson get(){
        if (gson == null) gson = new Gson();

        return gson;
    }


}
