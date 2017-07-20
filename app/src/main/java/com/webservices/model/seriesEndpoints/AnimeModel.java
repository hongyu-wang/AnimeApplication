package com.webservices.model.seriesEndpoints;

import com.webservices.endpointBuilder.QueryString;
import com.webservices.model.ModelFactory;

import static com.android.volley.Request.Method.GET;
import java.util.Map;

/**
 * Created by Han Yu on 2017-07-19.
 */

public class AnimeModel extends BasicSeriesModel{

    public static QueryString getQueryString(String... args){
        QueryString queryString = new QueryString("anime/search/" + args[0], GET);
        queryString.add("access_token", ModelFactory.getCurrentClient().getAccessToken());
        return queryString;
    }

    private int totalEpisodes;
    private int duration;
    private String airingStatus;
    private String youtubeId;
    private String hashtag;
    private String source;
    private Map<String, Integer> airingStats;

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAiringStatus() {
        return airingStatus;
    }

    public void setAiringStatus(String airingStatus) {
        this.airingStatus = airingStatus;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Map<String, Integer> getAiringStats() {
        return airingStats;
    }

    public void setAiringStats(Map<String, Integer> airingStats) {
        this.airingStats = airingStats;
    }


}
