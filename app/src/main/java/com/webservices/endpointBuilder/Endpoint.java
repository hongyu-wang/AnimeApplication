package com.webservices.endpointBuilder;

import com.webservices.model.ModelSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;

/**
 *
 * Created by hongy on 7/9/2017.
 */

public class Endpoint implements ModelSource{
    private static final String BASE_ENDPOINT = "https://anilist.co/api/";

    /**
     *
     * @param queryString arguments that go in the query string
     * @return the Endpoint Object to get the specific JSON
     */
    public static Endpoint endpointFactory(

            QueryString queryString
    ){
        return new Endpoint(BASE_ENDPOINT + queryString, queryString.getRequestType());
    }

    private RequestType type;

    private URL url;

    private Endpoint(String url, RequestType type){
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.type = type;
    }

    /**
     * This essentially is a hacky way of dealing with certificates
     *
     * Might expose apps to https attacks.
     *
     * Fix later
     *
     */
    private void fix(){
        //todo: fix later
        /*
        *  fix for
        *    Exception in thread "main" javax.net.ssl.SSLHandshakeException:
        *       sun.security.validator.ValidatorException:
        *           PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException:
        *               unable to find valid certification path to requested target
        */
        TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

                }

                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {  }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {  }

            }
        };

        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
        return true;
        }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        /*
        * end of the fix
        */
    }


    /**
     * URL implementation of ModelSource
     *
     * @return json representation of the Anilist system
     */
    @Override
    public String getJson() {
        fix();
        HttpsURLConnection connection;
        String json = "";

        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod(type.toString());
            connection.setDoOutput(true);



            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );

            String res;
            while ((res = br.readLine()) != null){
                json += res;
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
