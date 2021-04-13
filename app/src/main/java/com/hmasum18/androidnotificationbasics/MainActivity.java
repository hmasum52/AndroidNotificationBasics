package com.hmasum18.androidnotificationbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.hmasum18.androidnotificationbasics.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity->";

    private ActivityMainBinding mVB;

    //to show notification
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mVB = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mVB.getRoot());

        //get the notification manager compat from context
        notificationManagerCompat = NotificationManagerCompat.from(this);

        mVB.channel1BTN.setOnClickListener(v -> {
            //now create a notification to show it
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,App.CHANNEL_1_ID);
            builder.setSmallIcon(R.drawable.ic_baseline_1k_24);
            builder.setContentTitle(mVB.titleEDT.getText());
            builder.setContentText(mVB.textEDT.getText());
            if(!mVB.bigTextEDT.getText().equals(""))
                builder.setContentText(mVB.bigTextEDT.getText());
            builder.setPriority(NotificationCompat.PRIORITY_HIGH);

            // make notification clickable
            Intent activityIntent = new Intent(this, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0 , activityIntent, 0);
            builder.setContentIntent(contentIntent);
            builder.setAutoCancel(true);
            builder.setOnlyAlertOnce(true);
            builder.setColor(Color.GREEN);

            //if we want push multiple notification by this channel
            // we have to give a different id every time
            Log.d(TAG,"Sending notification to channel-1");
            notificationManagerCompat.notify(1,builder.build());
        });

        mVB.channel2BTN.setOnClickListener(v -> {
            //now create a notification to show it
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,App.CHANNEL_2_ID);
            builder.setSmallIcon(R.drawable.ic_baseline_2k_24);
            builder.setContentTitle(mVB.titleEDT.getText());
            builder.setContentText(mVB.textEDT.getText());
            if(!mVB.bigTextEDT.getText().equals(""))
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(mVB.bigTextEDT.getText()));
            builder.setPriority(NotificationCompat.PRIORITY_LOW);

            // make notification clickable
            Intent activityIntent = new Intent(this, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0 , activityIntent, 0);
            builder.setContentIntent(contentIntent);
            builder.setAutoCancel(true);
            builder.setColor(Color.GREEN);

            //if we want push multiple notification by this channel
            // we have to give a different id every time
            Log.d(TAG,"Sending notification to channel-2");
            notificationManagerCompat.notify(2,builder.build());
        });
    }
}