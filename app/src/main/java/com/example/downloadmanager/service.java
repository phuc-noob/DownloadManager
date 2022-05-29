package com.example.downloadmanager;

import static com.example.downloadmanager.NotificationApp.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class service extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("test log","sevice is creat");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String getDataLinkDownload = intent.getStringExtra("key_link_download");

        sendNotification(getDataLinkDownload);
        return START_NOT_STICKY;
    }

    private void sendNotification(String getDataLinkDownload) {
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle("Sevice demo ")
                .setContentText(getDataLinkDownload)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic__downloading_24)
                .build();
        startForeground(1,notification);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("sevice","sevice is destroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
