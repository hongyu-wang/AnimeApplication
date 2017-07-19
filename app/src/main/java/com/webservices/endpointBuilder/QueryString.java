package com.webservices.endpointBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 3rd party QueryString class
 *
 * <a href=http://www.java2s.com/Tutorial/Java/0320__Network/BuildquerystringforURL.htm>
 *      Class source
 *     </a>
 *
 * Created by hongy on 7/9/2017.
 */

public class QueryString {
    private String query;

    private RequestType requestType;


    public QueryString(String urlSection, RequestType req) {
        query = urlSection + "?";
        this.requestType = req;

    }

    public void add(String name, String value) {
        if(!query.substring(query.length() -1).equals("?"))
        query += "&";

        encode(name, value);
    }

    private void encode(String name, String value) {
        try {
            query += URLEncoder.encode(name, "UTF-8");
            query += "=";
            query += URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Broken VM does not support UTF-8");
        }
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String toString() {
        return query;
    }



}
