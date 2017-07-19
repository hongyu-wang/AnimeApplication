package com.webservices.model;

import com.singletons.CredentialSingleton;
import com.webservices.endpointBuilder.QueryString;
import com.webservices.endpointBuilder.RequestType;

import java.io.Serializable;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public class ClientCredModel implements Serializable{
    public static QueryString getQueryString(){
        QueryString queryString = new QueryString("auth/access_token", RequestType.POST);
        queryString.add("grant_type", "client_credentials");
        queryString.add("client_id", CredentialSingleton.get().getClientID());
        queryString.add("client_secret", CredentialSingleton.get().getClientSecret());
        return queryString;
    }


    public String getAccessToken() {
        return accessToken;
    }

    private String accessToken;

    private String tokenType;

    private int expires;

    private int expiresIn;



}
