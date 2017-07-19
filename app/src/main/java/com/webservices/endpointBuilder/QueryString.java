package com.webservices.endpointBuilder;

import android.app.VoiceInteractor;

import com.android.volley.Request;

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

    private int requestType;


    public QueryString(String urlSection, int req) {
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

    public int getRequestType() {
        return requestType;
    }

    public String toString() {
        return query;
    }



}
