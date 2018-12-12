package com.example.doome.text_baseddungeoncrawler;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShakeoDeBoyo extends AppCompatActivity {

    private SensorManager smh;

    private float acelValue;
    private float acelLastValue;
    private float shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        smh = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        smh.registerListener(sensorListener, smh.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        acelValue = SensorManager.GRAVITY_EARTH;
        acelLastValue = SensorManager.GRAVITY_EARTH;
        shake = .00f;
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            acelLastValue = acelValue;
            acelValue = (float) Math.sqrt(x*x +y*y + z*z);
            float delta = acelValue - acelLastValue;
            shake = shake * .9f + delta;

            if (shake > 12) {
                if (Gameplay.isReadyToAttack) {
                    Gameplay.attack();
                    Gameplay.isReadyToAttack = false;
                }
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
