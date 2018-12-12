package com.example.doome.text_baseddungeoncrawler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StatSelection extends AppCompatActivity {

    EditText enterStatsBar;
    int whichStat = 0;
    public static boolean statsAreRandomized = false;
    public static int statPlayerStrength;
    public static int statPlayerAccuracy;
    public static int statPlayerDefense;
    public static int statPlayerAgility;
    public static int statPlayerIntelligence;
    public static int statPlayerLuck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_selection);
        configureNextButton();
        enterStatsBar = (EditText) findViewById(R.id.enterStatsBar);
    }
    private void configureNextButton() {
        Button progressButton1 = (Button) findViewById(R.id.StatBackButton);
        progressButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StatSelection.this, HowToPlayTwo.class));
            }
        });
        Button progressButton2 = (Button) findViewById(R.id.StatStatEnterButton);
        progressButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (whichStat == 0) {
                    statPlayerStrength = Integer.valueOf(enterStatsBar.getText().toString());
                    enterStatsBar.setHint("Accuracy");
                } else if (whichStat == 1) {
                    statPlayerAccuracy = Integer.valueOf(enterStatsBar.getText().toString());
                    enterStatsBar.setHint("Agility");
                } else if (whichStat == 2) {
                    statPlayerDefense = Integer.valueOf(enterStatsBar.getText().toString());
                    enterStatsBar.setHint("Defense");
                } else if (whichStat == 3) {
                    statPlayerAgility = Integer.valueOf(enterStatsBar.getText().toString());
                    enterStatsBar.setHint("Intelligence");
                } else if (whichStat == 4) {
                    statPlayerIntelligence = Integer.valueOf(enterStatsBar.getText().toString());
                    enterStatsBar.setHint("And finally, Luck");
                } else if (whichStat == 5) {
                    statPlayerLuck = Integer.valueOf(enterStatsBar.getText().toString());
                    startActivity(new Intent(StatSelection.this, EnterNames.class));
                }
                enterStatsBar.setText("");
                whichStat++;
            }
        });
        Button progressButton3 = (Button) findViewById(R.id.StatStatRandomizer);
        progressButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statsAreRandomized = true;
                startActivity(new Intent(StatSelection.this, EnterNames.class));
            }
        });
    }
}
