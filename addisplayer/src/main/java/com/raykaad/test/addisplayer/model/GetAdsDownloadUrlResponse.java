package com.raykaad.test.addisplayer.model;

import com.raykaad.test.addisplayer.object_customer.AdsData;

import org.json.JSONException;
import org.json.JSONObject;

public class GetAdsDownloadUrlResponse {

    private String adId;
    private String url;
    private String title;
    private String description;

    public GetAdsDownloadUrlResponse(Object data) throws JSONException {
        JSONObject jsonObject = (JSONObject) data;
        this.adId = jsonObject.getString("adId");

        this.url = jsonObject.getString("url");
        this.title = jsonObject.getString("title");
        this.description = jsonObject.getString("description");

    }

    public AdsData getAdsData(){
        return new AdsData(
                getAdId(),
                getUrl(),
                getTitle(),
                getDescription()
        );
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
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