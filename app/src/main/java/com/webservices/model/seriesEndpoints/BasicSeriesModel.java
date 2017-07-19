package com.webservices.model.seriesEndpoints;

import com.webservices.endpointBuilder.QueryString;
import com.webservices.model.ModelFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static com.webservices.endpointBuilder.RequestType.GET;

/**
 *
 * Created by Han Yu on 2017-07-09.
 */

public class BasicSeriesModel implements Serializable{
    public static QueryString getQueryString(String... args){
        QueryString queryString = new QueryString("anime/" + args[0], GET);
        queryString.add("access_token", ModelFactory.getCurrentClient().getAccessToken());
        return queryString;
    }
    private int id;
    private String seriesType;
    private String titleRomaji;
    private String titleEnglish;
    private String titleJapanese;
    private String type;
    private String startDate;
    private String endDate;
    private int startDateFuzzy;
    private int endDateFuzzy;
    private int season;
    private String description;
    private List<String> synonyms;
    private List<String> genres;
    private boolean adult;
    private double averageScore;
    private int popularity;
    private boolean favourite;
    private String imageUrlSml;
    private String imageUrlMed;
    private String imageUrlLge;
    private String imageUrlBanner;
    private int updatedAt;
    private Map<String, Integer> scoreDistribution;
    private Map<String, Integer> listStats;

}
