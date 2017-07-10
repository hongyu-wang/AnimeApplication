package com.webservices.endpoint_builder;

/**
 * Request Type
 *
 * Created by hongy on 7/9/2017.
 */

public enum RequestType {
    GET("GET"),
    POST("POST");

    private String type;

    @Override
    public String toString() {
        return type;
    }

    RequestType(String type) {
        this.type = type;
    }
}
