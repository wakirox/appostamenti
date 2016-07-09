package com.appostamento;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Marius Gabriel Magadan on 09/07/2016.
 */
public class CalendarEvent {


    long startTimestamp;
    long endTimestamp;

    public CalendarEvent(long startTimestamp, long endTimestamp) {
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
    }

    public boolean isValid(){
        return startTimestamp>=0 && endTimestamp>=0;
    }

//    public void setEndTimestamp(long endTimestamp) {
//        this.endTimestamp = endTimestamp;
//    }
//
//    public void setStartTimestamp(long startTimestamp) {
//        this.startTimestamp = startTimestamp;
//    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("startTimestamp", startTimestamp);
            if(endTimestamp!=0)jsonObject.put("endTimestamp", endTimestamp);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }
}
