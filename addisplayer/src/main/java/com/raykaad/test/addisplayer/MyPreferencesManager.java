package com.raykaad.test.addisplayer;

import android.content.SharedPreferences;

/**
 * Created by Nili on 12/17/2015.
 */


public class MyPreferencesManager {

    private static MyPreferencesManager INSTANCE;
    private static final String PREF_NAME = "lib-v1";

    private SharedPreferences.Editor getEditor() {
        return getPref().edit();
    }

    private SharedPreferences getPref() {
        return LibApp.getContext().getSharedPreferences(PREF_NAME, 0);
    }

    public static MyPreferencesManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new MyPreferencesManager();
        return INSTANCE;
    }

    MyPreferencesManager() {
    }


    public void setGoogleAdsId(String id) {
        getEditor().putString("GOOGLE_ADS_ID", id).commit();
    }

    public String getGooglrAdsId() {
        return getPref().getString("GOOGLE_ADS_ID", "");
    }


}
