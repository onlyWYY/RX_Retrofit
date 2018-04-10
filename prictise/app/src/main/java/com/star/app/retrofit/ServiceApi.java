package com.star.app.retrofit;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by suxing on 2018/4/9.
 */

public interface ServiceApi {

    @FormUrlEncoded
    @POST("index.php?r=config")
    Observable<String> config(
            @Field("verson") String verson,
            @Field("os") String os);

    @POST("index.php?r=user/refresh-login")
    Observable<String> refresh(
            @Query("token") String token);
}
