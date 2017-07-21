package com.webservices.model.credentials;

import com.singletons.CredentialSingleton;
import com.webservices.endpointBuilder.QueryString;
import com.webservices.model.Model;

import java.io.Serializable;

import static com.android.volley.Request.Method.POST;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public class ClientCredModel implements Model {
    public static QueryString getQueryString(){
        QueryString queryString = new QueryString("auth/access_token", POST);
        queryString.add("grant_type", "client_credentials");
        queryString.add("client_id", CredentialSingleton.get().getClientID());
        queryString.add("client_secret", CredentialSingleton.get().getClientSecret());
        return queryString;
    }



    private String accessToken;



    private String tokenType;

    private int expires;

    private int expiresIn;

    public String refreshToken;


    public String getAccessToken() {
        return accessToken;
    }


}
