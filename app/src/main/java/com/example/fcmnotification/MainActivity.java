package com.example.fcmnotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
TextView text_show;
final static String Channel_name="lathesh";
final public static String Channel_id="lathesh";
final static String Channel_descvription="lathesh";
Button btm_subscribe,btm_unsubscribe;
String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_show = findViewById(R.id.text_show);
        btm_subscribe = findViewById(R.id.btm_subscribe);
        btm_unsubscribe = findViewById(R.id.btm_unsubscribe);

       /* to get the data from notificaation

       if (getIntent()!=null && getIntent().hasExtra("one")){

            for (String key:getIntent().getExtras().keySet()){
                text_show.append("key "+key+" "+"Data "+getIntent().getExtras().getString(key)+"\n");

            }
        }*/
// to generate the firebase token
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()){
                             token=task.getResult().getToken();
                            text_show.setText(token);
                            Log.d("token",token);
                        }else{
                            text_show.setText("failed to generate");
                        }
                    }
                });
//    }

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel(Channel_id,Channel_name, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(Channel_descvription);

            NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        btm_subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseMessaging.getInstance().unsubscribeFromTopic("demo-topic")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(MainActivity.this, "topic subscribed", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this, "failed to subscribe", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
 
        btm_unsubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                FirebaseMessaging.getInstance().unsubscribeFromTopic("demo-topic")
//                                  .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                      @Override
//                                      public void onComplete(@NonNull Task<Void> task) {
//                                          if (task.isSuccessful()){
//                                              Toast.makeText(MainActivity.this, "Unsubscvribed", Toast.LENGTH_SHORT).show();
//                                          }else{
//                                              Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
//                                          }
//                                      }
//                                  });
//
//                API api= BaseClient.getRetrofit().create(API.class);
//
//                Call<GetNotificationResponse> call=api.getNotification();
//
//                call.enqueue(new Callback<GetNotificationResponse>() {
//                    @Override
//                    public void onResponse(Call<GetNotificationResponse> call, Response<GetNotificationResponse> response) {
//                        if (response.isSuccessful()){
//                            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<GetNotificationResponse> call, Throwable t) {
//                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });

                API api=BaseClient.getRetrofit().create(API.class);

                Call<NotificationModel> call=api.Notification("123456789","638350",token);

                call.enqueue(new Callback<NotificationModel>() {
                    @Override
                    public void onResponse(Call<NotificationModel> call, Response<NotificationModel> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<NotificationModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }

}