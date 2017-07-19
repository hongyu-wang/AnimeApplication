package com.webservices.endpointBuilder;

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
