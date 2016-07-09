package com.appostamento;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

public class NotifyHandlerReceiver extends BroadcastReceiver {

    public static final String ACTION = "me.pepyakin.defferednotify.action.NOTIFY";
    public static final String EXTRA_TEXT = "ET_KEY";

    public void onReceive(Context context, Intent intent) {

        Notification.Action actionAccept =
                new Notification.Action.Builder(R.drawable.ic_done_white_24dp,"Confermo",
                        PendingIntent.getBroadcast(context,0,new Intent(),0)).build();

        Notification.Action actionRefuse =
                new Notification.Action.Builder(R.drawable.ic_delete_white_24dp, "Annulla",
                        PendingIntent.getBroadcast(context,0,new Intent(),0))
                        .build();

        if (false && ACTION.equals(intent.getAction())) {
            Notification.Builder builder = new Notification.Builder(context)
                    .setTicker("Notifica")

                    .setSmallIcon(android.R.drawable.ic_menu_my_calendar)

                    .addAction(actionRefuse)
                    .addAction(actionAccept)
                    .setPriority(Notification.PRIORITY_MAX)

                    .setContentTitle(context.getString(R.string.app_name))
                    .setContentText(intent.getStringExtra(EXTRA_TEXT))
                    .setAutoCancel(true)
                    .setContentIntent(PendingIntent.getActivity(context, 0,
                            new Intent(context, MainActivity_.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), 0));

            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify("interstitial_tag", 1, builder.build());
        }else{
            Notification.Builder builder = new Notification.Builder(context)
                    .setTicker("Notifica")
                    .setSmallIcon(android.R.drawable.ic_menu_my_calendar)
//                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), android.R.drawable.ic_menu_my_calendar))

                    .setPriority(Notification.PRIORITY_MAX)
                    .setStyle(new Notification.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.generate)))
                    .setAutoCancel(true)
                    .setContentTitle(context.getString(R.string.app_name))
                    .setContentText("QR Code bigliettino")
                    .setContentIntent(PendingIntent.getActivity(context, 0,
                            new Intent(context, QRCodeActivity_.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), 0));

            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify("interstitial_tag", 1, builder.build());
        }
    }
}