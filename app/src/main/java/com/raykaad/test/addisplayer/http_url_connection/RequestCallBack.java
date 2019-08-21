package com.raykaad.test.addisplayer.http_url_connection;

import com.raykaad.test.addisplayer.model.BaseResponse;

public interface RequestCallBack {

    void Success(BaseResponse baseResponse);

    void Error();
}
