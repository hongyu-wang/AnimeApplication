package com.webservices.model;

import com.singletons.ApiDataSingleton;
import com.webservices.api_auth.ApiData;
import com.webservices.endpoint_builder.QueryString;

import java.io.Serializable;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public class ClientCredModel implements Serializable{
    public static QueryString getQueryString(){
        QueryString queryString = new QueryString("auth/access_token");
        queryString.add("grant_type", "client_credentials");
        queryString.add("client_id", ApiDataSingleton.get().getClientID());
        queryString.add("client_secret", ApiDataSingleton.get().getClientSecret());
        return queryString;
    }



    private String access_token;

    private String token_type;

    private int expires;

    private int expires_in;

}
