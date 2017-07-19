package com.main;


import com.webservices.model.ModelFactory;
import com.webservices.model.seriesEndpoints.BasicSeriesModel;

import java.util.concurrent.ExecutionException;

/**
 *
 * Created by hongy on 7/5/2017.
         */
public class Main {

    public static void main (String args []) throws ExecutionException, InterruptedException {

        ModelFactory.initTest();
        ModelFactory.getModel(BasicSeriesModel.class);
    }

}
