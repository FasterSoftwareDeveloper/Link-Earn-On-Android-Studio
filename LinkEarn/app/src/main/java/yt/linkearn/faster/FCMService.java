package yt.linkearn.faster;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class FCMService extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "fcm_channel";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (!remoteMessage.getData().isEmpty()) {
            String title = remoteMessage.getData().get("title");
            String body = remoteMessage.getData().get("body");
            String imageUrl = remoteMessage.getData().get("image");
            String extraData = remoteMessage.getData().get("extraData");
            String ContextActivity = remoteMessage.getData().get("ContextActivity");

            if (isValid(title) && isValid(body)) {
                showNotification(title, body, imageUrl, extraData, ContextActivity);
            } else {
                if (!isValid(title)) {
                    Log.e("FCMService", "Error: Title is missing or empty");
                }
                if (!isValid(body)) {
                    Log.e("FCMService", "Error: Body is missing or empty");
                }
            }
        } else {
            Log.e("FCMService", "Error: Received empty data payload");
        }
    }

    private boolean isValid(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private void showNotification(String title, String body, String imageUrl, String extraData, String ContextActivity) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("message", body);
        intent.putExtra("image", imageUrl);
        intent.putExtra("extraData", extraData);
        intent.putExtra("ContextActivity", ContextActivity);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "FCM Channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo) // You can customize this based on app requirements
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(pendingIntent);

        // Using ExecutorService instead of AsyncTask
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new DownloadImageRunnable(builder, imageUrl));
    }

    private class DownloadImageRunnable implements Runnable {

        private final NotificationCompat.Builder builder;
        private final String imageUrl;

        public DownloadImageRunnable(NotificationCompat.Builder builder, String imageUrl) {
            this.builder = builder;
            this.imageUrl = imageUrl;
        }

        @Override
        public void run() {
            Bitmap bitmap = downloadImage(imageUrl);
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (bitmap != null) {
                builder.setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap)
                        .bigLargeIcon((Bitmap) null));  // Explicitly cast to Bitmap
            } else {
                Log.w("DownloadImageRunnable", "Image download failed, showing notification without image.");
            }

            if (notificationManager != null) {
                notificationManager.notify((int) System.currentTimeMillis(), builder.build());
            } else {
                Log.e("DownloadImageRunnable", "Error: NotificationManager is null");
            }
        }

        private Bitmap downloadImage(String imageUrl) {
            if (imageUrl == null || imageUrl.trim().isEmpty()) {
                Log.w("DownloadImageRunnable", "Warning: No image URL provided");
                return null;
            }

            try {
                InputStream in = new URL(imageUrl).openStream();
                return BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("DownloadImageRunnable", "Error downloading image from URL: " + imageUrl, e);
                return null;
            }
        }
    }
}