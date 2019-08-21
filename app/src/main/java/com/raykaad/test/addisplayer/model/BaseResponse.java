package com.raykaad.test.addisplayer.model;

import org.json.JSONException;
import org.json.JSONObject;

public class BaseResponse {
    private boolean hasError;
    private String message;
    private  Object data;

    public BaseResponse(JSONObject jsonObject) {
        try {
            hasError = (boolean) jsonObject.get("hasError");
            message = (String) jsonObject.get("message");
            data = (Object) jsonObject.get("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
