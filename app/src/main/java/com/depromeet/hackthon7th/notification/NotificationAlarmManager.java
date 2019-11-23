package com.depromeet.hackthon7th.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.depromeet.hackthon7th.database.Task;

public class NotificationAlarmManager {

  private Context context;
  private AlarmManager alarmManager;


  public NotificationAlarmManager(Context context) {
    this.context = context;
    alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
  }

  // 등록
  public void register(Task task) {
    // TODO 반복 있는지 없는지 체크
    Intent intent = NotificationBroadcastReceiver.getCallingIntent(context);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
        task.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, task.getDeadLine().getTime(), pendingIntent);
  }

  // 취소
  public void cancel(Task task) {
    Intent intent = NotificationBroadcastReceiver.getCallingIntent(context);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
        task.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

    if (pendingIntent != null) {
      alarmManager.cancel(pendingIntent);
      pendingIntent.cancel();
    }

  }
}
