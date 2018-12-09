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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_selection);
        configureNextButton();
        enterStatsBar = (EditText) findViewById(R.id.enterStatsBar);
    }
    private void configureNextButton() {
        Button progressButton = (Button) findViewById(R.id.StatNextButton);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StatSelection.this, Gameplay.class));
            }
        });
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
                    Dungeon.playerStrength = Integer.valueOf(enterStatsBar.getText().toString());
                }
                if (whichStat == 1) {
                    Dungeon.playerAccuracy = Integer.valueOf(enterStatsBar.getText().toString());
                }
                if (whichStat == 2) {
                    Dungeon.playerDefense = Integer.valueOf(enterStatsBar.getText().toString());
                }
                if (whichStat == 3) {
                    Dungeon.playerAgility = Integer.valueOf(enterStatsBar.getText().toString());
                }
                if (whichStat == 4) {
                    Dungeon.playerIntelligence = Integer.valueOf(enterStatsBar.getText().toString());
                }
                if (whichStat == 5) {
                    Dungeon.playerLuck = Integer.valueOf(enterStatsBar.getText().toString());
                    startActivity(new Intent(StatSelection.this, Gameplay.class));
                }
                enterStatsBar.setText("");
                whichStat++;
            }
        });
        Button progressButton3 = (Button) findViewById(R.id.StatStatRandomizer);
        progressButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dungeon.isRandomized = true;
                startActivity(new Intent(StatSelection.this, Gameplay.class));
            }
        });
    }
}
