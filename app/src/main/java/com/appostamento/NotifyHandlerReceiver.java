package com.appostamento;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotifyHandlerReceiver extends BroadcastReceiver {

    public static final String ACTION = "me.pepyakin.defferednotify.action.NOTIFY";
    public static final String EXTRA_TEXT = "ET_KEY";

    public void onReceive(Context context, Intent intent) {


        Notification.Action actionAccept =
                new Notification.Action.Builder(R.drawable.ic_done_white_24dp,"OK",null).build();

        Notification.Action actionRefuse =
                new Notification.Action.Builder(R.drawable.ic_delete_white_24dp, "Cancella",null)
                        .build();

        if (ACTION.equals(intent.getAction())) {
            Notification.Builder builder = new Notification.Builder(context)
                    .setTicker("Notifica")
                    .setSmallIcon(android.R.drawable.stat_notify_chat)

                    .addAction(actionRefuse)
                    .addAction(actionAccept)


                    .setContentTitle(context.getString(R.string.app_name))
                    .setContentText(intent.getStringExtra(EXTRA_TEXT))
                    .setAutoCancel(true)
                    .setContentIntent(PendingIntent.getActivity(context, 0,
                            new Intent(context, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), 0));

            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify("interstitial_tag", 1, builder.build());
        }
    }
}