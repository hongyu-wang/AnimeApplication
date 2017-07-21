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
        // TODO change this back to "anime/search/"
        // gson was throwing error, i think the model didnt fit the response (search returns list
        // where as this returns a single series model (?)
        QueryString queryString = new QueryString("anime/" + args[0], GET);
        queryString.add("access_token", ModelFactory.getCurrentClient().getAccessToken());
        return queryString;
    }


    protected int id;
    protected String seriesType;
    protected String titleRomaji;
    protected String titleEnglish;
    protected String titleJapanese;
    protected String type;
    protected String startDate;
    protected String endDate;
    protected int startDateFuzzy;
    protected int endDateFuzzy;
    protected int season;
    protected String description;
    protected List<String> synonyms;
    protected List<String> genres;
    protected boolean adult;
    protected double averageScore;
    protected int popularity;
    protected boolean favourite;
    protected String imageUrlSml;
    protected String imageUrlMed;
    protected String imageUrlLge;
    protected String imageUrlBanner;
    protected int updatedAt;
    protected Map<String, Integer> scoreDistribution;
    protected Map<String, Integer> listStats;

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
