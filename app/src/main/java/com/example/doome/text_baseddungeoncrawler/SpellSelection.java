package com.example.doome.text_baseddungeoncrawler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

public class SpellSelection extends AppCompatActivity {

    //public static byte magicStat;

    static TextView spellOneDesc;
    static TextView spellTwoDesc;
    static TextView spellThreeDesc;
    static TextView spellFourDesc;
    static TextView spellFiveDesc;
    static TextView spellSixDesc;
    static TextView spellSevenDesc;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_selection);
        spellOneDesc = (TextView) findViewById(R.id.spellOneDesc);
        spellTwoDesc = (TextView) findViewById(R.id.spellTwoDesc);
        spellThreeDesc = (TextView) findViewById(R.id.spellThreeDesc);
        spellFourDesc = (TextView) findViewById(R.id.spellFourDesc);
        spellFiveDesc = (TextView) findViewById(R.id.spellFiveDesc);
        spellSixDesc = (TextView) findViewById(R.id.spellSixDesc);
        spellSevenDesc = (TextView) findViewById(R.id.spellSevenDesc);
        configureNextButton();
        if (EnterNames.playerMagic >= 0) {
            SpellSelection.spellOneDesc.setText(Player.allSpells[0][1]);
        } if (EnterNames.playerMagic > 3) {
            SpellSelection.spellTwoDesc.setText("Fireball: MA. Does more damage than your regular attack, but slightly less accurate");
        } if (EnterNames.playerMagic > 5) {
            SpellSelection.spellThreeDesc.setText("Blinding Light: M. 80 percent chance to reduce enemy accuracy this turn");
        } if (EnterNames.playerMagic > 8) {
            SpellSelection.spellOneDesc.setText("Strong Heal: MA. Restores a lot of health");
        }
    }
    private void configureNextButton() {
        Button spellOne = (Button) findViewById(R.id.activateSpellOne);
        Button spellTwo = (Button) findViewById(R.id.activateSpellTwo);
        Button spellThree = (Button) findViewById(R.id.activateSpellThree);
        Button spellFour = (Button) findViewById(R.id.activateSpellFour);
        Button spellFive = (Button) findViewById(R.id.activateSpellFive);
        Button spellSix = (Button) findViewById(R.id.activateSpellSix);
        Button spellSeven = (Button) findViewById(R.id.activateSpellSeven);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //This is where the functions of the spells are going to be used when the user presses one of them.
        spellOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trulyCastSpell((byte) 0, Gameplay.thisRoom.numberOne);
                finish();
            }
        });
        spellTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trulyCastSpell((byte) 1, Gameplay.thisRoom.numberOne);
                finish();
            }
        });
        spellThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trulyCastSpell((byte) 2, Gameplay.thisRoom.numberOne);
                finish();
            }
        });
        spellFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trulyCastSpell((byte) 3, Gameplay.thisRoom.numberOne);
                finish();
            }
        });
        spellFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trulyCastSpell((byte) 4, Gameplay.thisRoom.numberOne);
                finish();
            }
        });
        spellSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trulyCastSpell((byte) 5, Gameplay.thisRoom.numberOne);
                finish();
            }
        });
        spellSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trulyCastSpell((byte) 6, Gameplay.thisRoom.numberOne);
                finish();
            }
        });
    }

    private static void trulyCastSpell(byte index, Character c) {

    }

    public static void updateSpell(byte slot, byte spellNum) {
        if (slot == 0) {

        }
    }
}
