package com.example.doome.text_baseddungeoncrawler;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Shake extends AppCompatActivity {

    private SensorManager smh;

    private float acelValue;
    private float acelLastValue;
    private float shake;
    private boolean shaken = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakeo_de_boyo);

        smh = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        smh.registerListener(sensorListener, smh.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        acelValue = SensorManager.GRAVITY_EARTH;
        acelLastValue = SensorManager.GRAVITY_EARTH;
        shake = .00f;
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (shaken) return;

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            acelLastValue = acelValue;
            acelValue = (float) Math.sqrt(x*x +y*y + z*z);
            float delta = acelValue - acelLastValue;
            shake = shake * .9f + delta;

            if (shake > 3) {
                shaken = true;
                new CountDownTimer(2000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }

                    @Override
                    public void onFinish() {
                        shaken = false;
                    }
                };
                finish();
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
