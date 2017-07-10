package com.singletons;

import com.webservices.api_auth.ApiData;
import com.webservices.api_auth.ApiImplementation;

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
