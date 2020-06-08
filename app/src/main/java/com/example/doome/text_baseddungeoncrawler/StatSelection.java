package com.example.doome.text_baseddungeoncrawler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

public class StatSelection extends AppCompatActivity {


    public static byte whichStat = 0;
    public static byte thisStatPoints;
    public static byte thisLiveStatPoints;
    TextView StatSelect;
    TextView StatIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_selection);
        configureNextButton();
        StatSelect = findViewById(R.id.StatSelect);
        StatIndicator = findViewById(R.id.StatIndicator);
        StatIndicator.setText("Strength");
        thisLiveStatPoints = DifficultySelection.getStatPointCount();
        thisStatPoints = DifficultySelection.getStatPointCount();
        StatSelect.setText("Select stats. Points remaining: " + String.valueOf(thisStatPoints));
    }
    private void configureNextButton() {
        Button goBackButton = findViewById(R.id.StatBackButton);
        Button zero = findViewById(R.id.EnterZero);
        Button one = findViewById(R.id.EnterOne);
        Button two = findViewById(R.id.EnterTwo);
        Button three = findViewById(R.id.EnterThree);
        Button four = findViewById(R.id.EnterFour);
        Button five = findViewById(R.id.EnterFive);
        Button six = findViewById(R.id.EnterSix);
        Button seven = findViewById(R.id.EnterSeven);
        Button eight = findViewById(R.id.EnterEight);
        Button nine = findViewById(R.id.EnterNine);
        Button ten = findViewById(R.id.EnterTen);

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whichStat = 0;
                EnterNames.playerStrength = 0;
                EnterNames.playerAccuracy = 0;
                EnterNames.playerDefense = 0;
                EnterNames.playerAgility = 0;
                EnterNames.playerIntelligence = 0;
                EnterNames.playerMagic = 0;
                EnterNames.playerLuck = 0;
                startActivity(new Intent(StatSelection.this, DifficultySelection.class));
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 0);
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 1);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 2);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 3);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 4);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 5);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 6);
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 7);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 8);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 9);
            }
        });
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 10);
            }
        });
    }
    public void setStatIndicator(boolean valid) {
        if (valid == true) {
            if (whichStat == 0) {
                StatIndicator.setText("Accuracy");
            } else if (whichStat == 1) {
                StatIndicator.setText("Defense");
            } else if (whichStat == 2) {
                StatIndicator.setText("Agility");
            } else if (whichStat == 3) {
                StatIndicator.setText("Intelligence");
            } else if (whichStat == 4) {
                StatIndicator.setText("Magic");
            } else if (whichStat == 5) {
                StatIndicator.setText("Luck");
            }
        }
        else {
            StatSelect.setText("You don't have enough points to set this stat so high." +
                    " Points remaining: " + String.valueOf(thisLiveStatPoints));
        }
    }
    public void setPlayerStat(byte value) {
        if (whichStat == 0) {EnterNames.playerStrength = value;}
        else if (whichStat == 1) {EnterNames.playerAccuracy = value;}
        else if (whichStat == 2) {EnterNames.playerDefense = value;}
        else if (whichStat == 3) {EnterNames.playerAgility = value;}
        else if (whichStat == 4) {EnterNames.playerIntelligence = value;}
        else if (whichStat == 5) {
            EnterNames.playerMagic = value;
            //SpellSelection.magicStat = value;
        }
        else {
            EnterNames.playerLuck = value;
            startActivity(new Intent(StatSelection.this, EnterNames.class));
        }
        if (thisLiveStatPoints - value < 0) {
            setStatIndicator(false);
        }
        else {
            setStatIndicator(true);
            thisLiveStatPoints -= value;
            StatSelect.setText("Select stats. Points remaining: " + String.valueOf(thisLiveStatPoints));
            whichStat++;
        }
    }
}
