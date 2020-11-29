package com.example.fcmnotification;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

    @POST("index.php")
    Call<GetNotificationResponse>getNotification();

    @FormUrlEncoded
    @POST("Login.php")
    Call<NotificationModel>Notification(
            @Field("mobile") String mobile,
            @Field("otp") String otp,
            @Field("token") String token
    );
}
