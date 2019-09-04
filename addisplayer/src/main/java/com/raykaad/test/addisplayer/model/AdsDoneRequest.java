package com.raykaad.test.addisplayer.model;

import org.json.JSONException;
import org.json.JSONObject;

public class AdsDoneRequest {

    private String googleAdID;
    private String adsID;

    public AdsDoneRequest(String googleAdID, String adsID) {
        this.googleAdID = googleAdID;
        this.adsID = adsID;
    }

    public String getGoogleAdID() {
        return googleAdID;
    }

    public void setGoogleAdID(String googleAdID) {
        this.googleAdID = googleAdID;
    }

    public String getAdsID() {
        return adsID;
    }

    public void setAdsID(String adsID) {
        this.adsID = adsID;
    }

    public JSONObject getJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("googleAdID", getGoogleAdID());
            jsonObject.put("adsID", getAdsID());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }
}

