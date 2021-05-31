package com.devnags.pingme.network;

import com.devnags.pingme.adapters.model.PhoneNumber;
import com.devnags.pingme.adapters.model.PhoneOtp;
import com.devnags.pingme.adapters.model.Status;
import com.devnags.pingme.adapters.model.Token;
import com.devnags.pingme.db.entity.MainModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({
            "Content-Type: application/json",
            "Cookie: __cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720"
    })
    @POST("/V1/users/phone_number_login")
    Call<Status> verifyPhone(@Body PhoneNumber number);


    @Headers({
            "Content-Type: application/json",
            "Cookie: __cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720"
    })
    @POST("/V1/users/verify_otp")
    Call<Token> verifyOtp(@Body PhoneOtp numberOtp);


    @Headers({
            "Cookie: __cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720"
    })
    @GET("/V1/users/test_profile_list")
    Call<MainModel> getProfileList(@Header("Authorization") String token);



}
