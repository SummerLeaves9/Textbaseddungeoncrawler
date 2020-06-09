package com.example.doome.text_baseddungeoncrawler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

public class SpellSelection extends AppCompatActivity {

    static TextView spellOneDesc;
    TextView spellTwoDesc;
    TextView spellThreeDesc;
    TextView spellFourDesc;
    TextView spellFiveDesc;
    TextView spellSixDesc;
    TextView spellSevenDesc;

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
            spellOneDesc.setText(EnterNames.thisPlayer.spells[0].description);
        } if (EnterNames.playerMagic > 3) {
            spellTwoDesc.setText(EnterNames.thisPlayer.spells[1].description);
        } if (EnterNames.playerMagic > 5) {
            spellThreeDesc.setText(EnterNames.thisPlayer.spells[2].description);
        } if (EnterNames.playerMagic > 8) {
            spellOneDesc.setText(EnterNames.thisPlayer.spells[0].description);
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
        //This is where the functions of the spells are used when the user presses
        //the corresponding button.
        spellOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gameplay.trulyCastSpell((byte) 0);
                finish();
            }
        });
        spellTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gameplay.trulyCastSpell((byte) 1);
                finish();
            }
        });
        spellThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gameplay.trulyCastSpell((byte) 2);
                finish();
            }
        });
        spellFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gameplay.trulyCastSpell((byte) 3);
                finish();
            }
        });
        spellFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gameplay.trulyCastSpell((byte) 4);
                finish();
            }
        });
        spellSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gameplay.trulyCastSpell((byte) 5);
                finish();
            }
        });
        spellSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gameplay.trulyCastSpell((byte) 6);
                finish();
            }
        });
    }

    public void updateSpell(byte slot, byte spellNum) {
        EnterNames.thisPlayer.spells[slot] = new Spell(spellNum);
        if (slot == 0) {
            spellOneDesc.setText(EnterNames.thisPlayer.spells[slot].description);
        }
    }
}
