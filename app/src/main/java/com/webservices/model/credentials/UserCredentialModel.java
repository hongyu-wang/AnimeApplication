package com.webservices.model.credentials;

import com.myapplication.MainActivity;
import com.singletons.CredentialSingleton;
import com.webservices.endpointBuilder.QueryString;
import com.webservices.model.Model;
import com.webservices.model.ModelFactory;

import static com.android.volley.Request.Method.POST;

/**
 * Model for user credentials
 *
 * Created by hongy on 7/21/2017.
 */

public class UserCredentialModel implements Model {

    public static QueryString getQueryString(String ... args){
        QueryString qs = new QueryString("auth/access_token", POST);
        qs.add("client_id", CredentialSingleton.get().getClientID());
        qs.add("client_secret", CredentialSingleton.get().getClientSecret());
        qs.add("code", args[0]);
        qs.add("redirect_uri", MainActivity.CALLBACK_URI);
        qs.add("grant_type", "authorization_code");
        return qs;
    }


    private String access_token;
    private String token_type;
    private int expires;
    private int expires_in;
    private String refresh_token;

    public String getAccess_token() {
        return access_token;
    }
}
