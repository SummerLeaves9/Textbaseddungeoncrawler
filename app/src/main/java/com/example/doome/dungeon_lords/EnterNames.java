package com.example.doome.dungeon_lords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EnterNames extends AppCompatActivity {

    EditText NameEnterName;
    EditText NameEnterWeaponName;
    /**
     * The int that stores the player's strength stat before the player is initialized.
     */
    public static byte playerStrength;
    /**
     * The int that stores the player's accuracy stat before the player is initialized.
     */
    public static byte playerAccuracy;
    /**
     * The int that stores the player's defense stat before the player is initialized.
     */
    public static byte playerDefense;
    /**
     * The int that stores the player's agility stat before the player is initialized.
     */
    public static byte playerAgility;
    /**
     * The int that stores the player's intelligence stat before the player is initialized.
     */
    public static byte playerIntelligence;
    /**
     * The int that store the player's magic stat before the player is initialized.
     */
    public static byte playerMagic;
    /**
     * The int that stores the player's luck stat before the player is initialized.
     */
    public static byte playerLuck;
    /**
     * The String that stores the name the player enters.
     */
    public static String playerName;
    /**
     * The String that stores the weapon name the player enters.
     */
    public static String playerWeaponName;
    /**
     * The player.
     */
    public static Player thisPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_names);
        configureNextButton();
        NameEnterName = (EditText) findViewById(R.id.NameEnterName);
        NameEnterWeaponName = (EditText) findViewById(R.id.NameEnterWeaponName);
    }
    private void configureNextButton() {
        Button progressButton = (Button) findViewById(R.id.NameSubmitButton);
        Button backToStatsButton = (Button) findViewById(R.id.enterNamesBackButton);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerName = NameEnterName.getText().toString();
                playerWeaponName = NameEnterWeaponName.getText().toString();
                thisPlayer = new Player(playerStrength, playerAccuracy,
                        playerDefense, playerAgility, playerIntelligence, playerMagic,
                        playerLuck, playerName, playerWeaponName);
                StatSelection.statsChosen = true;
                startActivity(new Intent(EnterNames.this, Gameplay.class));
            }
        });
        backToStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatSelection.whichStat = 0;
                StatSelection.thisLiveStatPoints = StatSelection.thisStatPoints;
                //StatSelection.StatSelect.setText("Select stats. Points remaining: " + String.valueOf(StatSelection.thisStatPoints));
                //StatSelection.StatIndicator.setText("Strength");
                playerStrength = 0;
                playerAccuracy = 0;
                playerDefense = 0;
                playerAgility = 0;
                playerIntelligence = 0;
                playerMagic = 0;
                playerLuck = 0;
                startActivity(new Intent(EnterNames.this, StatSelection.class));
            }
        });
    }

    @Override
    public void onBackPressed() {}
}
