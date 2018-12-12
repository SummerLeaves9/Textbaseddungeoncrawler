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
    private float acelLast;
    private float shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shakeo_de_boyo);

        smh = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        smh.registerListener(sensorListener, smh.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        acelValue = SensorManager.GRAVITY_EARTH;
        acelLast = SensorManager.GRAVITY_EARTH;
        shake = 0.00f;
    }

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            acelLast = acelValue;
            acelValue = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = acelValue - acelLast;
            shake = shake * .9f + delta;

            if(shake > 10) {
                if (Gameplay.isReadyToAttack) {
                    int dealtDamage = Gameplay.thisPlayer.determineHit(Gameplay.thisRoom.numberOne);
                    if (dealtDamage != 0) {
                        Gameplay.consoleOutput = Gameplay.displayHit + dealtDamage + ". ";
                        if (Gameplay.thisRoom.numberOne.liveHP <= 0) {
                            Gameplay.thisPlayer.myPoints += Gameplay.thisRoom.numberOne.pointValue;
                            Gameplay.victoryMessage = "You won! " + Gameplay.thisRoom.numberOne.name +
                                    " dropped " + Gameplay.thisRoom.numberOne.pointValue + " points. Now you have " +
                                    Gameplay.thisPlayer.myPoints + " points!";
                            Gameplay.consoleOutput += Gameplay.victoryMessage;
                            Gameplay.isBattling = false;
                            Gameplay.enemiesDefeatedCounter++;
                            if (Gameplay.thisRoom.disSearchable) {
                                Gameplay.consoleOutput += Gameplay.canBeSearched;
                            }
                            Gameplay.displayInfo = "Hp:" + Gameplay.thisPlayer.liveHP + "/" + Gameplay.thisPlayer.hp + " Points: " + Gameplay.thisPlayer.myPoints;
                            Gameplay.turnAdvantage = true;
                        }
                    } else {
                        Gameplay.consoleOutput = Gameplay.displayMiss;
                        Gameplay.turnAdvantage = false;
                    }
                    Gameplay.isReadyToAttack = false;
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
