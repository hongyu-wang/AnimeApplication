package com.webservices.model.seriesEndpoints;

import com.webservices.endpointBuilder.QueryString;
import com.webservices.model.Model;
import com.webservices.model.ModelFactory;

import static com.android.volley.Request.Method.GET;

/**
 *
 * Created by hongy on 7/19/2017.
 */

public class GenreModel implements Model {
    public static QueryString getQueryString(){
        QueryString queryString = new QueryString("genre_list", GET);
        queryString.add("access_token", ModelFactory.getCurrentClient().getAccessToken());
        return queryString;
    }

    private int id;
    private String genre;
}
