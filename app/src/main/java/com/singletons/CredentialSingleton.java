package com.singletons;

import com.webservices.apiAuth.ApiData;
import com.webservices.apiAuth.ApiImplementation;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public class CredentialSingleton {
    private static ApiData instance;

    public static ApiData get(){
        if (instance == null) instance = new ApiImplementation();

        return instance;
    }



}
