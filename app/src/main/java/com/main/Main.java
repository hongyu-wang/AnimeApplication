package com.main;

import com.webservices.api_auth.ApiData;
import com.webservices.api_auth.ApiImplementation;

/**
 *
 * Created by hongy on 7/5/2017.
 */

public class Main {

    public static void main (String args []){
        ApiData data = new ApiImplementation();
        //AMHmaSPrkHpAq6H8VxAUGZB5kZyc4TDoJaEbmQir
        System.out.println("https://anilist.co/api/auth/access_token?grant_type=client_credentials&"
                +"client_id" + data.getClientID() + "client_secret" + data.getClientSecret());
    }

}
