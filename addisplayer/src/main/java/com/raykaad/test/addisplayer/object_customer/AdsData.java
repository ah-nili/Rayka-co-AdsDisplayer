package com.raykaad.test.addisplayer.object_customer;


public class AdsData {

    private String adId;
    private String url;
    private String title;
    private String description;



    public AdsData() {
    }

    public AdsData(String adId, String url, String title, String description) {
        this.adId = adId;
        this.url = url;
        this.title = title;
        this.description = description;
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
