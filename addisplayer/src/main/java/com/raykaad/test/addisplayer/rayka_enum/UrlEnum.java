package com.raykaad.test.addisplayer.rayka_enum;

public enum UrlEnum {

    BASE("http://localhost:8585/ads/"),
    GET_ALL_INSTALLED_ADS("getAllInstalledAds"),
    ADS_DONE("adsDone"),
    GET_ADS_DOWNLOAD_URL("getAdsDownloadURL");


    private String url;

    UrlEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return BASE.url + url;
    }
}
