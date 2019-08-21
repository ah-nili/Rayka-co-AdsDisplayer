package com.raykaad.test.addisplayer.model;

import org.json.JSONException;
import org.json.JSONObject;

public class GetAllInstalledAdsRequest {


    private String googleAdID;
    private String packageName;

    public GetAllInstalledAdsRequest(String googleAdID, String packageName) {
        this.googleAdID = googleAdID;
        this.packageName = packageName;
    }

    public String getGoogleAdID() {
        return googleAdID;
    }

    public void setGoogleAdID(String googleAdID) {
        this.googleAdID = googleAdID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public JSONObject getJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("googleAdID", getGoogleAdID());
            jsonObject.put("packageName", getPackageName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }
}

