package com.raykaad.test.addisplayer.model;

import com.raykaad.test.addisplayer.object_customer.AdsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetAllInstalledAdsResponse {

    private String adId;
    private String url;
    private String title;
    private String description;

    public GetAllInstalledAdsResponse(Object data) throws JSONException {
        JSONArray jsonArray = (JSONArray) data;
        JSONObject jsonObject = (JSONObject) data;
        this.adId = jsonObject.getString("id");
        this.url = jsonObject.getString("url");
        this.title = jsonObject.getString("title");
        this.description = jsonObject.getString("description");

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

    public List<AdsData> getAdsDatas(){
        return new ArrayList<>();
    }

}