package com.raykaad.test.addisplayer.http_url_connection;

import android.os.AsyncTask;

import com.raykaad.test.addisplayer.model.BaseRequest;
import com.raykaad.test.addisplayer.model.BaseResponse;
import com.raykaad.test.addisplayer.rayka_enum.RequestMethodEnum;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestHelper {

    RequestCallBack requestCallback;

    private static RequestHelper INSTANCE;

    public static RequestHelper getINSTANCE() {

        if (INSTANCE == null)
            INSTANCE = new RequestHelper();

        return INSTANCE;
    }

    private class RequestAsyncTask extends AsyncTask<BaseRequest, String, String> {

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(BaseRequest... requests) {
            try {
                BaseRequest request = requests[0];

                switch (request.getRequestMethodEnum()) {
                    case GET:
                        return RequestHandler.sendGet(request.getUrl());
                    case PUT:
                        return RequestHandler.sendPost(request.getUrl(), request.getData());
                    case POST:
                        return RequestHandler.sendPut(request.getUrl(), request.getData());
                    default:
                        return "";
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                BaseResponse baseResponse;
                baseResponse = new BaseResponse(new JSONObject(result));
                if (baseResponse.isHasError())
                    requestCallback.Error();
                else
                    requestCallback.Success(baseResponse);
            } catch (JSONException e) {
                e.printStackTrace();
                requestCallback.Error();
            }
        }


    }

    public void SendAsyncRequest(RequestMethodEnum requestMethodEnum, String url, JSONObject jsonObject, RequestCallBack requestCallback) {
        this.requestCallback = requestCallback;
        new RequestAsyncTask().execute(new BaseRequest(requestMethodEnum, url, jsonObject));
    }

    public void SendAsyncRequest(RequestMethodEnum requestMethodEnum, String url, RequestCallBack requestCallback) {
        this.requestCallback = requestCallback;
        new RequestAsyncTask().execute(new BaseRequest(requestMethodEnum, url));
    }

}
