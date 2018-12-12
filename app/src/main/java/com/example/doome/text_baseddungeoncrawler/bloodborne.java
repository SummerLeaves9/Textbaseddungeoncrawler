package com.example.doome.text_baseddungeoncrawler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class bloodborne extends AppCompatActivity {

    TextView score;
    TextView playerName1;
    TextView playerWeaponName1;
    TextView displayStrength;
    TextView displayAccuracy;
    TextView displayDefense;
    TextView displayAgility;
    TextView displayIntelligence;
    TextView displayLuck;
    TextView difficultyChosen;
    TextView enemiesDefeated;
    TextView secretsFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_game);
        score = (TextView) findViewById(R.id.score);
        playerName1 = (TextView) findViewById(R.id.playerName1);
        playerWeaponName1 = (TextView) findViewById(R.id.playerWeaponName1);
        displayStrength = (TextView) findViewById(R.id.displayStrength);
        displayAccuracy = (TextView) findViewById(R.id.displayAccuracy);
        displayDefense = (TextView) findViewById(R.id.displayDefense);
        displayAgility = (TextView) findViewById(R.id.displayAgility);
        displayIntelligence = (TextView) findViewById(R.id.displayIntelligence);
        displayLuck = (TextView) findViewById(R.id.displayIntelligence);
        difficultyChosen = (TextView) findViewById(R.id.difficultyChosen);
        enemiesDefeated = (TextView) findViewById(R.id.enemiesDefeated);
        secretsFound = (TextView) findViewById(R.id.secretsFound);
        score.setText("Final score: " + Gameplay.thisPlayer.myPoints);
        playerName1.setText("Your name: " + Gameplay.thisPlayer.name);
        playerWeaponName1.setText("Your weapon: " + Gameplay.thisPlayer.weaponName);
        displayStrength.setText("Your Strength: " + Gameplay.thisPlayer.strengthValue);
        displayAccuracy.setText("Your Accuracy: " + Gameplay.thisPlayer.accuracyValue);
        displayDefense.setText("Your Defense: " + Gameplay.thisPlayer.defenseValue);
        displayAgility.setText("Your Agility: " + Gameplay.thisPlayer.agilityValue);
        displayIntelligence.setText("Your Intelligence: " + Gameplay.thisPlayer.intelligenceValue);
        displayLuck.setText("Your Luck: " + Gameplay.thisPlayer.luckValue);
        if (Gameplay.roomCount == 15) {
            difficultyChosen.setText("Chosen Difficulty: Easy (15 rooms)");
        } else if (Gameplay.roomCount == 30) {
            difficultyChosen.setText("Chosen Difficulty: Normal (30 rooms)");
        } else if (Gameplay.roomCount == 45) {
            difficultyChosen.setText("Chosen Difficulty: Hard (45 rooms)");
        }
        enemiesDefeated.setText("You defeated " + Gameplay.enemiesDefeatedCounter + " enemies.");
        secretsFound.setText("You found " + Gameplay.secretsFoundCounter + " secrets.");
    }
}
