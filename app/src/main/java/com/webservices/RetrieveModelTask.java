package com.webservices;

import android.os.AsyncTask;

import com.webservices.model.ClientCredModel;
import com.webservices.model.ModelFactory;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public class RetrieveModelTask extends AsyncTask<String, Void, ClientCredModel> {
    @Override
    protected ClientCredModel doInBackground(String... params) {
        return ModelFactory.getModel(ClientCredModel.class);
    }
}
