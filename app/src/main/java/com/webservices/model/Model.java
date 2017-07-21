package com.webservices.model;


import java.io.Serializable;

/**
 * Marker interface that marks this class as a model.
 *
 * Every model class MUST implement:
 *
 * public static QueryString getQueryString()
 *
 * OR
 *
 * public static QueryString getQueryString(String ... args).
 *
 * This is done through reflection
 *
 * Created by hongy on 7/19/2017.
 */

public interface Model extends Serializable{


}
