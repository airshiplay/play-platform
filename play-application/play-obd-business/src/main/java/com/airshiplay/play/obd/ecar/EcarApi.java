package com.airshiplay.play.obd.ecar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.Map;

/**
 * Created by lig on 17/1/23.
 */
public interface EcarApi {

    @POST("AjaxService?method=CallWsWs")
    @Headers({
            "Accept:application/json, text/javascript, */*; q=0.01",
            "Content-Type:application/x-www-form-urlencoded; charset=UTF-8"
    })
    @FormUrlEncoded
    public Call<Map<String,Object>> getBrand(@Field("data") String body);

    //<img src="http://bu.wedrive.com.cn/upload/carlogo/fengtian.png">

    @GET("upload/carlogo/{fileId}")
    @Headers({"Content-Type: image/jpeg"})
    Call<ResponseBody> getFile(@Path("fileId") String fileId);
}
