package com.depromeet.hackthon7th.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationBroadcastReceiver extends BroadcastReceiver {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, NotificationBroadcastReceiver.class);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null) {
            if (!intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
                new PushNotificationManager(context).sendPush("구질구질한 알림", "이거 진짜 다한거죠...?", "태그");
            }
        }
    }
}
