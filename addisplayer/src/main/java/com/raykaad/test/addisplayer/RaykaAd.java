package com.raykaad.test.addisplayer;


import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.raykaad.test.addisplayer.model.AdsDoneRequest;
import com.raykaad.test.addisplayer.model.BaseRequest;
import com.raykaad.test.addisplayer.model.BaseResponse;
import com.raykaad.test.addisplayer.http_url_connection.RequestCallBack;
import com.raykaad.test.addisplayer.http_url_connection.RequestHelper;
import com.raykaad.test.addisplayer.model.GetAdsDownloadUrlResponse;
import com.raykaad.test.addisplayer.model.GetAllInstalledAdsResponse;
import com.raykaad.test.addisplayer.rayka_enum.RequestMethodEnum;
import com.raykaad.test.addisplayer.rayka_enum.UrlEnum;

import org.json.JSONException;

import java.io.IOException;

public class RaykaAd {

    private static RaykaAd instance;
    private Context context;
    private String googleAdId;

    public static RaykaAd getInstance() {

        if (instance == null)
            instance = new RaykaAd();

        return instance;
    }

    public static void init(Context context) {
        getInstance().context = context;
        getInstance().googleAdId = "";
        getInstance().setGoogleAdsId();
    }

    public void getAdsDownloadURL(final CallBack.GetAdsDownloadURL callBack) {
        RequestHelper.getINSTANCE().SendAsyncRequest(
                RequestMethodEnum.GET,
                UrlEnum.GET_ADS_DOWNLOAD_URL.getUrl()+"/"+googleAdId+"/"+getPackageName(),
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

    public void getAllInstalledAds(final CallBack.GetAllInstalledAds callBack) {
        RequestHelper.getINSTANCE().SendAsyncRequest(
                RequestMethodEnum.GET,
                UrlEnum.GET_ALL_INSTALLED_ADS.getUrl()+"/"+googleAdId+"/"+getPackageName(),
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

    public void adsDone(String adsID, final CallBack.AdsDone callBack) {
        RequestHelper.getINSTANCE().SendAsyncRequest(
                RequestMethodEnum.PUT,
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

    public void setGoogleAdsId() {
        if (googleAdId.matches(""))
            new GoogleAdsIdAsyncTask().execute();
    }

    public String getGoogleAdsId() {
        setGoogleAdsId();
        return googleAdId;
    }

    public String getPackageName() {
        return context.getPackageName();
    }

    private class GoogleAdsIdAsyncTask extends AsyncTask<BaseRequest, String, String> {
        @Override
        protected String doInBackground(BaseRequest... baseRequests) {
            AdvertisingIdClient.Info idInfo = null;
            try {
                idInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
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
            googleAdId = advertId;
        }

    }

}
