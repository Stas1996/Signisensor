package com.example.signisensore;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

public class ActivityRecognizedService extends IntentService {

    public ActivityRecognizedService (){
        super("ActivityRecognizedService");
    }
        public ActivityRecognizedService (String name){
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (ActivityRecognitionResult.hasResult(intent)){
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleSitectedActivity(result.getProbableActivities());
        }
    }

    private void handleSitectedActivity(List<DetectedActivity> probableActivities) {
        for (DetectedActivity activity : probableActivities){
            switch(activity.getType()){
                case DetectedActivity.IN_VEHICLE:{
                    Log.d("stas", "handle Activity: IN_VEHICLE: " + activity.getConfidence() + "%");
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    Log.d("stas", "handle Activity: ON_BICYCLE: " + activity.getConfidence() + "%");
                    break;
                }
                case DetectedActivity.ON_FOOT: {
                    Log.d("stas", "handle Activity: ON_FOOT: " + activity.getConfidence() + "%");
                    break;
                }
                case DetectedActivity.RUNNING: {
                    Log.d("stas", "handle Activity: RUNNING: " + activity.getConfidence() + "%");
                    break;
                }
                case DetectedActivity.STILL: {
                    Log.d("stas", "handle Activity: STILL: " + activity.getConfidence() + "%");
                    break;
                }
                case DetectedActivity.WALKING: {
                    Log.d("stas", "handle Activity: WALKING: " + activity.getConfidence() + "%");
                    break;
                }
                case DetectedActivity.TILTING: {
                    Log.d("stas", "handle Activity: TILTING: " + activity.getConfidence() + "%");
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    Log.d("stas", "handle Activity: UNKNOWN: " + activity.getConfidence() + "%");
                    break;
                }
            }
        }
    }
}
