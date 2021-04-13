package com.hmasum18.androidnotificationbasics;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

/**
 * create a one time notification channel here
 * instead of creating again and again when we need them
 *
 * We create it when the application start. So we extends the Application class
 * This App class wraps our whole application with all it components and services
 */
public class App extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    /**
     * this method will be called before we start any of our application activity
     * when we start our app
     */
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    public void createNotificationChannels(){
        //channel is not available in lower sdk version
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //create channel 1
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel-1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is a test channel name channel-1");

            //create channel-2
            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel-2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is a test channel name channel-2");

            //get the system's notification service
            NotificationManager manager = super.getSystemService(NotificationManager.class);
            //and then create our channel to system notification manager
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }

}
