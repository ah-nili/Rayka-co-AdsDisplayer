package com.raykaad.test.addisplayer;


import android.os.AsyncTask;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.raykaad.test.addisplayer.model.AdsDoneRequest;
import com.raykaad.test.addisplayer.model.BaseRequest;
import com.raykaad.test.addisplayer.model.BaseResponse;
import com.raykaad.test.addisplayer.http_url_connection.RequestCallBack;
import com.raykaad.test.addisplayer.http_url_connection.RequestHelper;
import com.raykaad.test.addisplayer.model.GetAdsDownloadUrlRequest;
import com.raykaad.test.addisplayer.model.GetAdsDownloadUrlResponse;
import com.raykaad.test.addisplayer.model.GetAllInstalledAdsResponse;
import com.raykaad.test.addisplayer.rayka_enum.RequestMethodEnum;
import com.raykaad.test.addisplayer.rayka_enum.UrlEnum;

import org.json.JSONException;

import java.io.IOException;

public class RaykaAd {

    private static RaykaAd INSTANCE;

    public RaykaAd getINSTANS() {

        if (INSTANCE == null)
            INSTANCE = new RaykaAd();

        return INSTANCE;
    }


    public static void getAdsDownloadURL(final CallBack.GetAdsDownloadURL callBack) {
        getAdsDownloadURL("", "", callBack);
    }

    public static void getAdsDownloadURL(String googleAdId, String packageName, final CallBack.GetAdsDownloadURL callBack) {
        RequestHelper.getINSTANCE().SendAsyncRequest(
                RequestMethodEnum.POST,
                UrlEnum.GET_ADS_DOWNLOAD_URL.getUrl(),
                new GetAdsDownloadUrlRequest(googleAdId, packageName).getJsonObject(),
                new RequestCallBack() {
                    @Override
                    public void Success(BaseResponse baseResponse) {
                        GetAdsDownloadUrlResponse res = null;
                        try {
                            res = new GetAdsDownloadUrlResponse(baseResponse.getData());
                            callBack.onResponse(res.getAdsData());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callBack.onFailure();
                        }
                    }

                    @Override
                    public void Error() {
                        callBack.onFailure();
                    }
                }
        );
    }


    public static void getAllInstalledAds(final CallBack.GetAllInstalledAds callBack) {
        getAllInstalledAds("", "", callBack);
    }

    public static void getAllInstalledAds(String googleAdId, String packageName, final CallBack.GetAllInstalledAds callBack) {
        RequestHelper.getINSTANCE().SendAsyncRequest(
                RequestMethodEnum.POST,
                UrlEnum.GET_ALL_INSTALLED_ADS.getUrl(),
                new GetAdsDownloadUrlRequest(googleAdId, packageName).getJsonObject(),
                new RequestCallBack() {
                    @Override
                    public void Success(BaseResponse baseResponse) {
                        GetAllInstalledAdsResponse res = null;
                        try {
                            res = new GetAllInstalledAdsResponse(baseResponse.getData());
                            callBack.onResponse(res.getAdsDatas());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callBack.onFailure();
                        }
                    }

                    @Override
                    public void Error() {
                        callBack.onFailure();
                    }
                }
        );
    }


    public static void adsDone(final CallBack.AdsDone callBack) {
        adsDone("", "", callBack);
    }

    public static void adsDone(String googleAdId, String adsID, final CallBack.AdsDone callBack) {
        RequestHelper.getINSTANCE().SendAsyncRequest(
                RequestMethodEnum.POST,
                UrlEnum.ADS_DONE.getUrl(),
                new AdsDoneRequest(googleAdId, adsID).getJsonObject(),
                new RequestCallBack() {
                    @Override
                    public void Success(BaseResponse baseResponse) {
                        callBack.onResponse();
                    }

                    @Override
                    public void Error() {
                        callBack.onFailure();
                    }
                }
        );
    }


    public static void setGoogleAdsId() {
        if (MyPreferencesManager.getInstance().getGooglrAdsId().matches(""))
            new GoogleAdsIdAsyncTask().execute();
    }

    public static String getGoogleAdsId() {
        setGoogleAdsId();
        return MyPreferencesManager.getInstance().getGooglrAdsId();
    }

    public static String getPackageName() {
        return LibApp.getContext().getPackageName();
    }


    private static class GoogleAdsIdAsyncTask extends AsyncTask<BaseRequest, String, String> {
        @Override
        protected String doInBackground(BaseRequest... baseRequests) {
            AdvertisingIdClient.Info idInfo = null;
            try {
                idInfo = AdvertisingIdClient.getAdvertisingIdInfo(LibApp.getContext());
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String advertId = null;
            try {
                advertId = idInfo.getId();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            return advertId;
        }

        @Override
        protected void onPostExecute(String advertId) {
            MyPreferencesManager.getInstance().setGoogleAdsId(advertId);
        }

    }


}
