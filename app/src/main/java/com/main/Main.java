package com.main;

import com.webservices.ApiData;
import com.webservices.ApiImplementation;

/**
 *
 * Created by hongy on 7/5/2017.
 */

public class Main {

    public static void main (String args []){
        ApiData data = new ApiImplementation();
        System.out.println(data.getUrl());
    }

}
