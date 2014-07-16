package com.example.activityRec;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

/**
 * Created with IntelliJ IDEA.
 * User: AlexChe
 * Date: 05.06.14
 * Time: 0:01
 * To change this template use File | Settings | File Templates.
 */
public class ActivityRecognitionService extends IntentService {

    private String TAG = "appLogs";
    public ActivityRecognitionService() {
        super("My Activity Recognition Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(ActivityRecognitionResult.hasResult(intent)){
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            Log.i(TAG, getType(result.getMostProbableActivity().getType()) + "t" + result.getMostProbableActivity().getConfidence());
            Intent i = new Intent("com.alexche.recognitionservice.ACTIVITY_RECOGNITION_DATA");
            i.putExtra("Activity", getType(result.getMostProbableActivity().getType()) );
            i.putExtra("Confidence", result.getMostProbableActivity().getConfidence());
            sendBroadcast(i);
        }
    }

    private String getType(int type){
        if(type == DetectedActivity.UNKNOWN)
            return "Unknown";
        else if(type == DetectedActivity.IN_VEHICLE)
            return "In Vehicle";
        else if(type == DetectedActivity.ON_BICYCLE)
            return "On Bicycle";
        else if(type == DetectedActivity.ON_FOOT)
            return "On Foot";
        else if(type == DetectedActivity.STILL)
            return "Still";
        else if(type == DetectedActivity.TILTING)
            return "Tilting";
        else
            return "";
    }

}
