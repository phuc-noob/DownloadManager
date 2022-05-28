package com.example.downloadmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

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
                sendOnChannel1(  );
            }
        });

        //
        //this.notificationManagerCompat = NotificationManagerCompat.from(this);
    }


    private void sendOnChannel1()  {

        // Create NotificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // NotificationTargetActivity is the activity opened when user click notification.
//                Intent intent = new Intent(NotificationActivity.this, NotificationTargetActivity.class);
//                Intent intentArr[] = {intent};
//                PendingIntent pendingIntent = PendingIntent.getActivities(NotificationActivity.this, 0, intentArr, 0);
        // Create a new Notification instance.
        Notification notification = new Notification();
        // Set small icon.
        notification.icon = R.drawable.ic__downloading_24;
        // Set large icon.
        BitmapDrawable bitmapDrawable = (BitmapDrawable)getDrawable(R.drawable.ic__downloading_24);
        Bitmap largeIconBitmap = bitmapDrawable.getBitmap();
        notification.largeIcon = largeIconBitmap;
        // Set flags.
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        // Set send time.
        notification.when = System.currentTimeMillis();
        // Create and set notification content view.
        RemoteViews customRemoteViews = new RemoteViews(getPackageName(), R.layout.item_download_layout);
        notification.contentView = customRemoteViews;
        // Set notification intent.
        //notification.contentIntent = pendingIntent;
        notificationManager.notify(NOTIFICATION_ID_CUSTOM_VIEW, notification);
    }

}
