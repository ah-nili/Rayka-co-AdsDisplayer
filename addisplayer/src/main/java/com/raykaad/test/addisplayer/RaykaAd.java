package com.raykaad.test.addisplayer;


import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.raykaad.test.addisplayer.model.AdsData;
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
        isSyncGoogleAdsId(new SyncCallback() {
            @Override
            public void synced() {
                RequestHelper.getINSTANCE().SendAsyncRequest(
                        RequestMethodEnum.GET,
                        UrlEnum.GET_ADS_DOWNLOAD_URL.getUrl() + "/" + getGoogleAdsId() + "/" + getPackageName(),
                        new RequestCallBack() {
                            @Override
                            public void Success(BaseResponse baseResponse) {
                                AdsData res = null;
                                try {
                                    res = new AdsData(baseResponse.getData());
                                    callBack.onResponse(res);
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
        });

    }

    public void getAllInstalledAds(final CallBack.GetAllInstalledAds callBack) {
        isSyncGoogleAdsId(new SyncCallback() {
            @Override
            public void synced() {
                RequestHelper.getINSTANCE().SendAsyncRequest(
                        RequestMethodEnum.GET,
                        UrlEnum.GET_ALL_INSTALLED_ADS.getUrl() + "/" + getGoogleAdsId() + "/" + getPackageName(),
                        new RequestCallBack() {
                            @Override
                            public void Success(BaseResponse baseResponse) {
                                GetAllInstalledAdsResponse res = null;
                                try {
                                    res = new GetAllInstalledAdsResponse(baseResponse.getData());
                                    callBack.onResponse(res.getData());
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
        });

    }

    public void adsDone(final String adsID, final CallBack.AdsDone callBack) {
        isSyncGoogleAdsId(new SyncCallback() {
            @Override
            public void synced() {
                RequestHelper.getINSTANCE().SendAsyncRequest(
                        RequestMethodEnum.PUT,
                        UrlEnum.ADS_DONE.getUrl(),
                        new AdsDoneRequest(getGoogleAdsId(), adsID).getJsonObject(),
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
        });
    }


    public void setGoogleAdsId() {
        if (googleAdId.matches(""))
            new GoogleAdsIdAsyncTask().execute();
        else
            syncCallback.synced();
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
            syncCallback.synced();
        }

    }


    private SyncCallback syncCallback;

    public interface SyncCallback {
        void synced();
    }


    private void isSyncGoogleAdsId(SyncCallback callback) {
        syncCallback = callback;
        setGoogleAdsId();
    }
}
