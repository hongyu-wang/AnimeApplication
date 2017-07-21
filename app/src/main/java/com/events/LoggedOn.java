package com.events;

/**
 *
 * Created by hongy on 7/21/2017.
 */

public class LoggedOn implements EventMarker{
    public String code;

    public LoggedOn(String code){
        this.code = code;
    }
}
