package com.depromeet.hackthon7th.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.view.WindowManager;
import androidx.core.app.NotificationCompat;
import com.depromeet.hackthon7th.R;
import com.depromeet.hackthon7th.main.ToDoListMainActivity;

public class PushNotificationManager {

  private static final int REQ_CODE = 100;
  private Context context;

  public PushNotificationManager(Context context) {
    this.context = context;
  }

  public void sendPush(String title, String body, String tag) {
    PendingIntent pendingIntent = createPendingIntent();
    NotificationManager notificationManager = (NotificationManager) context
        .getSystemService(Context.NOTIFICATION_SERVICE);

    // 노티 계속 생성 Auto increment 방식으로 id를 증가시키면서 노티를 발생시킬 것
    if (notificationManager != null) {
      notificationManager.notify((int) System.currentTimeMillis(),
          createNotificationBuilder(title, body, pendingIntent).build());
      wakeUpDevice(tag);
    }
  }

  private PendingIntent createPendingIntent() {
    Intent intent = new Intent(context, ToDoListMainActivity.class);

    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    return PendingIntent.getActivity(context, REQ_CODE, intent, PendingIntent.FLAG_ONE_SHOT);
  }

  private NotificationCompat.Builder createNotificationBuilder(String title, String body,
      PendingIntent pendingIntent) {

    Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();

    style.setBigContentTitle(title);
    style.bigText(body);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // SDK version 26 이상인경우에는 알림 채널 생성
      NotificationChannel channelMessage = new NotificationChannel("CHANNEL_NOTIFICATION",
          "앱 이름", NotificationManager.IMPORTANCE_DEFAULT);

      NotificationManager notificationManager = (NotificationManager) context
          .getSystemService(Context.NOTIFICATION_SERVICE);
      channelMessage.setDescription("구질구질한 알림");
      channelMessage.enableLights(true);
      channelMessage.enableVibration(true);
      channelMessage.setVibrationPattern(new long[]{100, 200, 100, 200});
      channelMessage.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
      if (notificationManager != null) {
        notificationManager.createNotificationChannel(channelMessage);
      }
    }

    return new NotificationCompat.Builder(context, "CHANNEL_NOTIFICATION")
        .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
        .setPriority(NotificationCompat.PRIORITY_MAX)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setLargeIcon(
            BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round))
        .setContentTitle(title)
        .setContentText(body)
        .setStyle(style)
        .setColor(context.getColor(R.color.colorAccent))
        .setAutoCancel(true)
        .setSound(defaultSoundUri)
        .setContentIntent(pendingIntent);
  }

  private void wakeUpDevice(String tag) {
    PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

    if (powerManager != null) {
      powerManager.newWakeLock(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, tag).acquire(2000);
    }
  }
}
