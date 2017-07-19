package com.webservices.model;

import android.os.AsyncTask;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public class RetrieveModelTask <T> extends AsyncTask<Class<T>, Void, T> {

    @SafeVarargs
    @Override
    protected final T doInBackground(Class<T>... params) {

        return ModelFactory.getModel(params[0]);
    }

}
