package com.example.doome.dungeon_lords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverScreen extends AppCompatActivity {

    final String restartEncounter = "Restart Encounter";

    final String restartDungeon = "Restart Dungeon";

    TextView playerName1;
    TextView playerWeaponName1;
    TextView displayStrength;
    TextView displayAccuracy;
    TextView displayDefense;
    TextView displayAgility;
    TextView displayIntelligence;
    TextView displayMagic;
    TextView displayLuck;
    TextView enemiesDefeated;
    TextView secretsFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodborne);
        playerName1 = (TextView) findViewById(R.id.playerName1);
        playerWeaponName1 = (TextView) findViewById(R.id.playerWeaponName1);
        displayStrength = (TextView) findViewById(R.id.displayStrength);
        displayAccuracy = (TextView) findViewById(R.id.displayAccuracy);
        displayDefense = (TextView) findViewById(R.id.displayDefense);
        displayAgility = (TextView) findViewById(R.id.displayAgility);
        displayIntelligence = (TextView) findViewById(R.id.displayIntelligence);
        displayMagic = (TextView) findViewById(R.id.displayMagic);
        displayLuck = (TextView) findViewById(R.id.displayLuck);
        enemiesDefeated = (TextView) findViewById(R.id.enemiesDefeated);
        secretsFound = (TextView) findViewById(R.id.secretsFound);
        playerName1.setText("Your name: " + EnterNames.thisPlayer.name);
        playerWeaponName1.setText("Your weapon: " + EnterNames.thisPlayer.weaponName);
        displayStrength.setText("Your Strength: " + EnterNames.thisPlayer.strengthValue);
        displayAccuracy.setText("Your Accuracy: " + EnterNames.thisPlayer.accuracyValue);
        displayDefense.setText("Your Defense: " + EnterNames.thisPlayer.defenseValue);
        displayAgility.setText("Your Agility: " + EnterNames.thisPlayer.agilityValue);
        displayIntelligence.setText("Your Intelligence: " + EnterNames.thisPlayer.intelligenceValue);
        displayMagic.setText("Your Magic: " + EnterNames.thisPlayer.magicValue);
        displayLuck.setText("Your Luck: " + EnterNames.thisPlayer.luckValue);
        if (Gameplay.roomCount == 1) {
            enemiesDefeated.setVisibility(View.GONE);
            secretsFound.setVisibility(View.GONE);
        } else {
            enemiesDefeated.setVisibility(View.VISIBLE);
            secretsFound.setVisibility(View.VISIBLE);
        }
        enemiesDefeated.setText("You defeated " + Gameplay.enemiesDefeatedCounter + " enemies.");
        secretsFound.setText("You found " + Gameplay.secretsFoundCounter + " secrets.");
        configureNextButton();
    }

    private void configureNextButton() {
        Button playAgain = findViewById(R.id.gameOverPlayAgain);
        Button playAgainSameStats = findViewById(R.id.gameOverPlayAgainSameStats);
        if (Gameplay.roomCount == 1) {
            playAgainSameStats.setText(restartEncounter);
        } else {
            playAgainSameStats.setText(restartDungeon);
        }
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //restart game
                Gameplay.thisDungeon = Gameplay.firstDungeon;
                Exploration.mapInitiated = false;
                Gameplay.sphericonCharge = 0;
                Gameplay.hasSphericon = false;
                Gameplay.hasBlocked = false;
                Gameplay.usedLuckyMarksman = false;
                Gameplay.attackTurnAdvantage = true;
                Gameplay.magicTurnAdvantage = true;
                Gameplay.liveRoomCount = 0;
                Gameplay.spellNum = -1;
                Exploration.knownDungeons = new byte[20][0];
                for (byte j = 0; j < Exploration.hasCleared.length; j++) {
                    Exploration.hasCleared[j] = false;
                }
                Exploration.visitedHouses = new byte[5][0];
                Exploration.visitedTowns = new byte[10][0];
                Exploration.godIsALie = false;
                Exploration.stepsTraversed = 0;
                Outside.firstDungeon = true;
                StatSelection.statsChosen = false;
                StatSelection.whichStat = 0;
                StatSelection.thisLiveStatPoints = StatSelection.thisStatPoints;
                startActivity(new Intent(GameOverScreen.this, TitleScreen.class));
            }
        });
        playAgainSameStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //restart dungeon
                Gameplay.sphericonCharge = 0;
                Gameplay.enemyEffectCounter = -1;
                Gameplay.myEffectCounter = -1;
                Gameplay.hasSphericon = false;
                Gameplay.hasBlocked = false;
                Gameplay.usedLuckyMarksman = false;
                Gameplay.attackTurnAdvantage = true;
                Gameplay.magicTurnAdvantage = true;
                Gameplay.liveRoomCount = 0;
                Gameplay.spellNum = -1;
                EnterNames.thisPlayer.strengthValue = Gameplay.tempPlayerValues[2];
                EnterNames.thisPlayer.accuracyValue = Gameplay.tempPlayerValues[3];
                EnterNames.thisPlayer.defenseValue = Gameplay.tempPlayerValues[4];
                EnterNames.thisPlayer.agilityValue = Gameplay.tempPlayerValues[5];
                EnterNames.thisPlayer.intelligenceValue = Gameplay.tempPlayerValues[6];
                EnterNames.thisPlayer.magicValue = Gameplay.tempPlayerValues[7];
                EnterNames.thisPlayer.luckValue = Gameplay.tempPlayerValues[8];
                for (byte i = 0; i < 7; i++) {
                    EnterNames.thisPlayer.myItems[i] = Gameplay.tempPlayerItems[i];
                }

                EnterNames.thisPlayer.myGold = Gameplay.tempGold;

                for (byte i = 0; i < 7; i++) {
                    EnterNames.thisPlayer.spells[i] = Gameplay.tempPlayerSpells[i];
                }
                EnterNames.thisPlayer.setAllStats();
                EnterNames.thisPlayer.liveHP = Gameplay.tempPlayerValues[0];
                EnterNames.thisPlayer.liveMP = Gameplay.tempPlayerValues[1];
                startActivity(new Intent(GameOverScreen.this, Gameplay.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {}
}
