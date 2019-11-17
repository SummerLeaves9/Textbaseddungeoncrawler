package com.example.doome.text_baseddungeoncrawler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DifficultySelection extends AppCompatActivity {

    public static final byte easyStatPoints = 40;

    public static final double easyNegativeSecretChance = .1;

    public static final byte normalStatPoints = 35;

    public static final double normalNegativeSecretChance = .23;

    public static final byte hardAndUberStatPoints = 30;

    public static final double hardNegativeSecretChance = .3;

    public static double finalNegativeSecretChance;

    public static byte statPointCount;

    public static final String easy = "Easy";

    public static final String normal = "Normal";

    public static final String hard = "Hard";

    public static final String uber = "Uber";

    public static String selectedDifficulty;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_selection);
        configureNextButton();
    }
    private void configureNextButton() {
        Button progressButton1 = (Button) findViewById(R.id.EasyButton);
        Button progressButton2 = (Button) findViewById(R.id.NormalButton);
        Button progressButton3 = (Button) findViewById(R.id.HardButton);
        Button progressButton4 = (Button) findViewById(R.id.UberButton);
        progressButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                statPointCount = easyStatPoints;
                selectedDifficulty = easy;
                finalNegativeSecretChance = easyNegativeSecretChance;
                startActivity(new Intent(DifficultySelection.this, StatSelection.class));
            }
        });
        progressButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                statPointCount = normalStatPoints;
                selectedDifficulty = normal;
                finalNegativeSecretChance = normalNegativeSecretChance;
                startActivity(new Intent(DifficultySelection.this, StatSelection.class));
            }
        });
        progressButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                statPointCount = hardAndUberStatPoints;
                selectedDifficulty = hard;
                finalNegativeSecretChance = hardNegativeSecretChance;
                startActivity(new Intent(DifficultySelection.this, StatSelection.class));
            }
        });
        progressButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v4) {
                statPointCount = hardAndUberStatPoints;
                selectedDifficulty = uber;
                startActivity(new Intent(DifficultySelection.this, StatSelection.class));
            }
        });
    }
    public static String getSelectedDifficulty() {
        return selectedDifficulty;
    }
    public static byte getStatPointCount() {
        return statPointCount;
    }
}
