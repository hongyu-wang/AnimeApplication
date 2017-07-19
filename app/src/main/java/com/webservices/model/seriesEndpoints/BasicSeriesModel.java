package com.webservices.model.seriesEndpoints;

import com.webservices.endpointBuilder.QueryString;
import com.webservices.model.Model;
import com.webservices.model.ModelFactory;

import java.util.List;
import java.util.Map;

import static com.android.volley.Request.Method.GET;

/**
 *
 * Created by Han Yu on 2017-07-09.
 */

public class BasicSeriesModel implements Model {




    public static QueryString getQueryString(String... args){
        QueryString queryString = new QueryString("anime/search/" + args[0], GET);
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

    public int getId() {
        return id;
    }

    public String getSeriesType() {
        return seriesType;
    }

    public String getTitleRomaji() {
        return titleRomaji;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public String getTitleJapanese() {
        return titleJapanese;
    }

    public String getType() {
        return type;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getStartDateFuzzy() {
        return startDateFuzzy;
    }

    public int getEndDateFuzzy() {
        return endDateFuzzy;
    }

    public int getSeason() {
        return season;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public List<String> getGenres() {
        return genres;
    }

    public boolean isAdult() {
        return adult;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public int getPopularity() {
        return popularity;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public String getImageUrlSml() {
        return imageUrlSml;
    }

    public String getImageUrlMed() {
        return imageUrlMed;
    }

    public String getImageUrlLge() {
        return imageUrlLge;
    }

    public String getImageUrlBanner() {
        return imageUrlBanner;
    }

    public int getUpdatedAt() {
        return updatedAt;
    }

    public Map<String, Integer> getScoreDistribution() {
        return scoreDistribution;
    }

    public Map<String, Integer> getListStats() {
        return listStats;
    }

}
