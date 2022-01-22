package com.example.signisensore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionClient;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

public GoogleApiClient mApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    mApiClient = new GoogleApiClient.Builder(MainActivity.this)
            .addApi(ActivityRecognition.API)
            .addConnectionCallbacks(MainActivity.this)
            .addOnConnectionFailedListener(MainActivity.this)
            .build();
    mApiClient.connect();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Intent intent = new Intent(MainActivity.this,ActivityRecognizedService.class);
        PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        ActivityRecognitionClient activityRecognitionClient = new ActivityRecognitionClient(mApiClient.getContext());
        activityRecognitionClient.requestActivityUpdates(3000,pendingIntent);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}