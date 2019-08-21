package com.raykaad.test.addisplayer;


import com.raykaad.test.addisplayer.object_customer.AdsData;
import com.raykaad.test.addisplayer.object_customer.AllInstalledAds;

import java.util.List;

public interface CallBack {

    interface GetAdsDownloadURL {
        void onResponse(AdsData response);

        void onFailure();
    }


    interface GetAllInstalledAds {
        void onResponse(List<AdsData> response);

        void onFailure();
    }


    interface AdsDone {
        void onResponse();

        void onFailure();
    }


}
