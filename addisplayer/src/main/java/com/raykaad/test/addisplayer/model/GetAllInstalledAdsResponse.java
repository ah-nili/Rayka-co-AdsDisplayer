package com.raykaad.test.addisplayer.model;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetAllInstalledAdsResponse {


    private List<AdsData> data;

    public GetAllInstalledAdsResponse(@Nullable Object object) throws JSONException {
        this.data = new ArrayList<>();
        if (data != null) {
            JSONArray jsonArray = (JSONArray) object;
            for (int i = 0; i < jsonArray.length(); i++) {
                AdsData adsData = new AdsData();
                JSONObject jsonobject = (JSONObject) jsonArray.get(i);
                adsData.setId(jsonobject.optString("id"));
                adsData.setTitle(jsonobject.optString("title"));
                adsData.setDescription(jsonobject.optString("description"));
                adsData.setUrl(jsonobject.optString("url"));
                data.add(adsData);
            }
        }
    }

    public List<AdsData> getData() {
        return data;
    }

    public void setData(List<AdsData> data) {
        this.data = data;
    }
}