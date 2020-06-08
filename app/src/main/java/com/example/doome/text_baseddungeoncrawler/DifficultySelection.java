package com.example.doome.text_baseddungeoncrawler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DifficultySelection extends AppCompatActivity {

    public static final byte easyStatPoints = 40;

    public static final double easyNegativeSecretChance = .1;

    public static final double easyStrongEnemyChance = .15;

    public static final byte normalStatPoints = 35;

    public static final double normalNegativeSecretChance = .23;

    public static final double normalStrongEnemyChance = .23;

    public static final byte hardAndUberStatPoints = 30;

    public static final double hardNegativeSecretChance = .3;

    public static final double hardStrongEnemyChance = .35;

    public static final double uberNegativeSecretChance = .35;

    public static final double uberStrongEnemyChance = .65;

    public static double finalNegativeSecretChance;

    public static byte statPointCount;

    public static final char easy = 'E';

    public static final char normal = 'N';

    public static final char hard = 'H';

    public static final char uber = 'U';

    public static char selectedDifficulty;

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
                Enemy.strongChance = easyStrongEnemyChance;
                startActivity(new Intent(DifficultySelection.this, StatSelection.class));
            }
        });
        progressButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                statPointCount = normalStatPoints;
                selectedDifficulty = normal;
                finalNegativeSecretChance = normalNegativeSecretChance;
                Enemy.strongChance = normalStrongEnemyChance;
                startActivity(new Intent(DifficultySelection.this, StatSelection.class));
            }
        });
        progressButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v3) {
                statPointCount = hardAndUberStatPoints;
                selectedDifficulty = hard;
                finalNegativeSecretChance = hardNegativeSecretChance;
                Enemy.strongChance = hardStrongEnemyChance;
                startActivity(new Intent(DifficultySelection.this, StatSelection.class));
            }
        });
        progressButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v4) {
                statPointCount = hardAndUberStatPoints;
                selectedDifficulty = uber;
                finalNegativeSecretChance = uberNegativeSecretChance;
                Enemy.strongChance = uberStrongEnemyChance;
                startActivity(new Intent(DifficultySelection.this, StatSelection.class));
            }
        });
    }
    public static char getSelectedDifficulty() {
        return selectedDifficulty;
    }

    public static byte getStatPointCount() {
        return statPointCount;
    }
    public static double getFinalNegativeSecretChance() { return finalNegativeSecretChance; }
}
