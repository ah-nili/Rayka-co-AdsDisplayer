package com.raykaad.test.addisplayer.model;

import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

public class AdsData {


    private String id;
    private String url;
    private String title;
    private String description;


    public AdsData() {
        this.id = "";
        this.url = "";
        this.title = "";
        this.description = "";
    }

    public AdsData(String adId, String url, String title, String description) {
        this.id = adId;
        this.url = url;
        this.title = title;
        this.description = description;
    }

    public AdsData(@Nullable Object data) throws JSONException {
        if (data!=null) {
            JSONObject jsonObject = (JSONObject) data;
            this.id = jsonObject.getString("id");
            this.url = jsonObject.getString("url");
            this.title = jsonObject.getString("title");
            this.description = jsonObject.getString("description");
        }else
            new AdsData();
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
