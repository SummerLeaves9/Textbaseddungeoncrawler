package com.example.doome.text_baseddungeoncrawler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinishedGame extends AppCompatActivity {

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
        score.setText("Final score: " + EnterNames.thisPlayer.myPoints);
        playerName1.setText("Your name: " + EnterNames.thisPlayer.name);
        playerWeaponName1.setText("Your weapon: " + EnterNames.thisPlayer.weaponName);
        displayStrength.setText("Your Strength: " + EnterNames.thisPlayer.strengthValue);
        displayAccuracy.setText("Your Accuracy: " + EnterNames.thisPlayer.accuracyValue);
        displayDefense.setText("Your Defense: " + EnterNames.thisPlayer.defenseValue);
        displayAgility.setText("Your Agility: " + EnterNames.thisPlayer.agilityValue);
        displayIntelligence.setText("Your Intelligence: " + EnterNames.thisPlayer.intelligenceValue );
        displayLuck.setText("Your Luck: " + EnterNames.thisPlayer.luckValue);
        if (DifficultySelection.selectedDifficulty == DifficultySelection.easy) {
            difficultyChosen.setText("Chosen Difficulty: Easy");
        } else if (DifficultySelection.selectedDifficulty == DifficultySelection.normal) {
            difficultyChosen.setText("Chosen Difficulty: Normal");
        } else if (DifficultySelection.selectedDifficulty == DifficultySelection.hard) {
            difficultyChosen.setText("Chosen Difficulty: Hard");
        } else {
            difficultyChosen.setText("Chosen Difficulty: Uber");
        }
    }
}
