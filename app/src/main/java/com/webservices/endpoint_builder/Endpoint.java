package com.webservices.endpoint_builder;

import com.webservices.model.ModelSource;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public class Endpoint implements ModelSource{

    private static final String BASE_ENDPOINT = "https://anilist.co/api/";





    /**
     * URL implementation of ModelSource
     *
     * @return json representation of the Anilist system
     */
    @Override
    public String getJson() {
        return null;
    }
}
