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
                    int strengthTry = Integer.valueOf(enterStatsBar.getText().toString());
                    if (strengthTry <= Player.statMax && strengthTry >= Player.statMin) {
                        statPlayerStrength = strengthTry;
                        enterStatsBar.setHint("Accuracy");
                    } else {
                        whichStat--;
                        enterStatsBar.setHint("That value was not between 0 and 10. Please try again.");
                    }
                } else if (whichStat == 1) {
                    int accuracyTry = Integer.valueOf(enterStatsBar.getText().toString());
                    if (accuracyTry <= Player.statMax && accuracyTry >= Player.statMin) {
                        statPlayerAccuracy = accuracyTry;
                        enterStatsBar.setHint("Defense");
                    } else {
                        whichStat--;
                        enterStatsBar.setHint("That value was not between 0 and 10. Please try again.");
                    }
                } else if (whichStat == 2) {
                    int defenseTry = Integer.valueOf(enterStatsBar.getText().toString());
                    if (defenseTry <= Player.statMax && defenseTry >= Player.statMin) {
                        statPlayerDefense = defenseTry;
                        enterStatsBar.setHint("Agility");
                    } else {
                        whichStat--;
                        enterStatsBar.setHint("That value was not between 0 and 10. Please try again.");
                    }
                } else if (whichStat == 3) {
                    int agilityTry = Integer.valueOf(enterStatsBar.getText().toString());
                    if (agilityTry <= Player.statMax && agilityTry >= Player.statMin) {
                        statPlayerAgility = agilityTry;
                        enterStatsBar.setHint("Intelligence");
                    } else {
                        whichStat--;
                        enterStatsBar.setHint("That value was not between 0 and 10. Please try again.");
                    }
                } else if (whichStat == 4) {
                    int intelligenceTry = Integer.valueOf(enterStatsBar.getText().toString());
                    if (intelligenceTry <= Player.statMax && intelligenceTry >= Player.statMin) {
                        statPlayerIntelligence = intelligenceTry;
                        enterStatsBar.setHint("And finally, Luck");
                    } else {
                        whichStat--;
                        enterStatsBar.setHint("That value was not between 0 and 10. Please try again.");
                    }
                } else if (whichStat == 5) {
                    int luckTry = Integer.valueOf(enterStatsBar.getText().toString());
                    if (luckTry <= Player.statMax && luckTry >= Player.statMin) {
                        statPlayerLuck = luckTry;
                    } else {
                        whichStat--;
                        enterStatsBar.setHint("That value was not between 0 and 10. Please try again.");
                    }
                    if (statPlayerAccuracy + statPlayerAgility + statPlayerDefense + statPlayerIntelligence + statPlayerLuck + statPlayerStrength == Player.statPoints) {
                        startActivity(new Intent(StatSelection.this, EnterNames.class));
                    } else {
                        whichStat = -1;
                        enterStatsBar.setHint("Your stats did not add up to the right number. Please enter stats which add up to 30.");
                    }
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
