package com.appostamento;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.json.JSONArray;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import me.everything.providers.android.calendar.Calendar;
import me.everything.providers.android.calendar.CalendarProvider;
import me.everything.providers.android.calendar.Event;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    List<CalendarEvent> calendarEventList = new ArrayList<>();
    AsyncHttpClient client = new AsyncHttpClient();

    private final String HOST_URL = "www.google.com";


    @AfterViews
    public void initComponents(){


        scheduleNotification(2000, "Ciao chicco");

        sendData();



    }


    public static final int REQUEST_CODE_NOTIFY = 1;

    public void scheduleNotification(long delayTimeMs, String messaggio) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long currentTimeMs = SystemClock.elapsedRealtime();

        Intent intent =  new Intent(NotifyHandlerReceiver.ACTION);
        intent.putExtra(NotifyHandlerReceiver.EXTRA_TEXT,messaggio);

        PendingIntent pendingNotifyIntent = PendingIntent.getBroadcast(
                this,
                REQUEST_CODE_NOTIFY,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, currentTimeMs + delayTimeMs, pendingNotifyIntent);
    }




    @Background
    public void sendData() {

        CalendarProvider provider = new CalendarProvider(this);
        List<Calendar> calendars = provider.getCalendars().getList();

        for(Calendar calendar : calendars){
            List<Event> calendars2 = provider.getEvents(calendar.id).getList();
            for(Event e : calendars2){
                CalendarEvent calendarEvent = new CalendarEvent(e.dTStart,e.dTend);
                if(calendarEvent.isValid())calendarEventList.add(calendarEvent);
            }
        }

        try {
            JSONArray jsonArray = new JSONArray();

            for (CalendarEvent ce : calendarEventList) {
                jsonArray.put(ce.toJson());
            }

            StringEntity entity = new StringEntity(jsonArray.toString());
            new SyncHttpClient().post(this, HOST_URL, entity, "application/json",
                    new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            throwable.printStackTrace();
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {
                            System.out.println(responseString);
                        }
                    });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
