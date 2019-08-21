package com.raykaad.test.addisplayer.model;

import android.support.annotation.Nullable;

import com.raykaad.test.addisplayer.rayka_enum.RequestMethodEnum;

import org.json.JSONObject;

public class BaseRequest {


    private RequestMethodEnum requestMethodEnum;
    private String url;
    private JSONObject data;

    public BaseRequest(RequestMethodEnum requestMethodEnum, String url, @Nullable JSONObject data) {
        this.requestMethodEnum = requestMethodEnum;
        this.url = url;
        this.data = data;
    }

    public BaseRequest(RequestMethodEnum requestMethodEnum, String url) {
        this.requestMethodEnum = requestMethodEnum;
        this.url = url;
    }

    public RequestMethodEnum getRequestMethodEnum() {
        return requestMethodEnum;
    }

    public void setRequestMethodEnum(RequestMethodEnum requestMethodEnum) {
        this.requestMethodEnum = requestMethodEnum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public boolean isRequestMethodGet() {
        return getRequestMethodEnum().equals(RequestMethodEnum.GET);
    }

    public boolean isRequestMethodPost() {
        return getRequestMethodEnum().equals(RequestMethodEnum.POST);
    }
}
