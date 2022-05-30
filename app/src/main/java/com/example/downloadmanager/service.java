package com.example.downloadmanager;

import static com.example.downloadmanager.NotificationApp.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class service extends Service {

    private static final int BUFFER_SIZE = 4096;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("test log","sevice is creat");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String getDataLinkDownload = intent.getStringExtra("key_link_download");
        downloadFile(getDataLinkDownload);
        sendNotification(getDataLinkDownload);
        return START_NOT_STICKY;
    }

    private void sendNotification(String getDataLinkDownload) {
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews notificationLayoutExpanded  = new RemoteViews(getPackageName(), R.layout.item_download_layout);
        notificationLayoutExpanded.setTextViewText(R.id.tv_link_download,getDataLinkDownload);

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayoutExpanded)
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

    public void downloadFile(String strdownload) {


        try {

            //create url and connect
            URL url = new URL(strdownload);
            URLConnection connection = url.openConnection();
            connection.connect();

            // this will be useful so that you can show a typical 0-100% progress bar
            int fileLength = connection.getContentLength();

            // download the file
            InputStream input = new BufferedInputStream(connection.getInputStream());

            String path = "/sdcard/BarcodeScanner-debug.apk" ;
            OutputStream output = new FileOutputStream(path);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
