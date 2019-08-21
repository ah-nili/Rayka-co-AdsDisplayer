package com.raykaad.test.addisplayer.object_customer;


import java.util.List;

public class AllInstalledAds {

    public AllInstalledAds(List<Data> data) {
        this.data = data;
    }

    private List<Data> data;

    private class Data {
        private String adId;
        private String url;
        private String title;
        private String description;
    }



}