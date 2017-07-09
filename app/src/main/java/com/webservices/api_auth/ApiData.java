package com.webservices.api_auth;

/**
 *
 * Created by hongy on 7/5/2017.
 */

public abstract class ApiData {

    public static final String BASE_URL = "https://anilist.co/api/auth/authorize?";

    public abstract String getClientID();
    public abstract String getClientSecret();



    public String getUrl(){
        return BASE_URL
                + "grant_type=authorization_pin"
                + "&client_id=darknal43-y98jh"
                + "&response_type=pin"
                ;
    }
}
