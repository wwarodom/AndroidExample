package com.example.staff.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnNotification;
    private Button btnCancel;

    // Sets an ID for the notification
    int mNotificationId = 001;
    String channel_name = "my_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNotification = (Button) findViewById(R.id.btnNotification);
        btnCancel = (Button) findViewById(R.id.btnCancel);
    }

    protected void sendNotification(View v) {
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel mChannel;
        final String CHANNEL_ID ="0";
        final String CHANNEL_NAME = "my_channel";
        String Description = "This is my channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            mNotifyMgr.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this,CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .setContentTitle("My notification")
                        .setContentText("Open Google website!");

        Intent resultIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.google.com/"));

        //  Define notification action
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,   // context
                        0,      // requestCode (defined PendingIntent id)
                        resultIntent,
                        // if a previous PendingIntent already exists,
                        // then the current one will update it with the latest intent
                        PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent); //set click behavior

        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());

    }

    protected void cancelNotification(View v) {
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
        nMgr.cancel(mNotificationId);
    }

}
