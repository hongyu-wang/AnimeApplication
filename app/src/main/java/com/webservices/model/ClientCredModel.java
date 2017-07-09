package com.webservices.model;

import java.io.Serializable;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public class ClientCredModel implements Serializable{

    private String access_token;

    private String token_type;

    private int expires;

    private int expires_in;

}
