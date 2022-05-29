package com.example.downloadmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManagerCompat;


    private EditText editTextLinkDownload;

    private Button btnDownload;

    private int NOTIFICATION_ID_CUSTOM_VIEW = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.editTextLinkDownload = (EditText) this.findViewById(R.id.et_link_download);

        this.btnDownload = (Button) this.findViewById(R.id.btnDownload);


        this.btnDownload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                clickStartSevice();
                sendOnChannel1();
            }
        });


    }

    private void clickStartSevice() {
        Intent intent = new Intent(this,service.class);
        intent.putExtra("key_link_download",editTextLinkDownload.getText().toString().trim());
        startService(intent);
    }


    private void sendOnChannel1() {
        editTextLinkDownload =findViewById(R.id.et_link_download);
        // Get the layouts to use in the custom notification
        RemoteViews notificationLayoutExpanded  = new RemoteViews(getPackageName(), R.layout.item_download_layout);
        notificationLayoutExpanded.setTextViewText(R.id.tv_link_download,editTextLinkDownload.getText().toString());

        // Apply the layouts to the notification
        Notification customNotification = new NotificationCompat.Builder(this, "channel1")
                .setSmallIcon(R.drawable.ic__downloading_24)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayoutExpanded)
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,customNotification);
    }
}
