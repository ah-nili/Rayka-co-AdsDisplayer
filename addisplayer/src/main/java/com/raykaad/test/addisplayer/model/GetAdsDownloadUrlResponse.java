package com.raykaad.test.addisplayer.model;

import org.json.JSONException;
import org.json.JSONObject;

public class GetAdsDownloadUrlResponse {

    private String id;
    private String url;
    private String title;
    private String description;

    public GetAdsDownloadUrlResponse(Object data) throws JSONException {
        JSONObject jsonObject = (JSONObject) data;
        this.id = jsonObject.getString("id");//*
        this.url = jsonObject.getString("url");
        this.title = jsonObject.getString("title");
        this.description = jsonObject.getString("description");

    }

    public AdsData getAdsData() {
        return new AdsData(
                getId(),
                getUrl(),
                getTitle(),
                getDescription()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}