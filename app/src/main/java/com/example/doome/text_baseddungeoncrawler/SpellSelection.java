package com.example.doome.text_baseddungeoncrawler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

import org.w3c.dom.Text;

public class SpellSelection extends AppCompatActivity {

    TextView messageBox;
    TextView spellOneDesc;
    TextView spellTwoDesc;
    TextView spellThreeDesc;
    TextView spellFourDesc;
    TextView spellFiveDesc;
    TextView spellSixDesc;
    TextView spellSevenDesc;

    private static String noSpellEquipped = "There is no spell equipped in that slot!";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_selection);
        messageBox = (TextView) findViewById(R.id.messageBox);
        spellOneDesc = (TextView) findViewById(R.id.spellOneDesc);
        spellTwoDesc = (TextView) findViewById(R.id.spellTwoDesc);
        spellThreeDesc = (TextView) findViewById(R.id.spellThreeDesc);
        spellFourDesc = (TextView) findViewById(R.id.spellFourDesc);
        spellFiveDesc = (TextView) findViewById(R.id.spellFiveDesc);
        spellSixDesc = (TextView) findViewById(R.id.spellSixDesc);
        spellSevenDesc = (TextView) findViewById(R.id.spellSevenDesc);
        if (EnterNames.playerMagic >= 0) {
            spellOneDesc.setText(EnterNames.thisPlayer.spells[0].description);
        } if (EnterNames.playerMagic > 3) {
            spellTwoDesc.setText(EnterNames.thisPlayer.spells[1].description);
        } if (EnterNames.playerMagic > 5) {
            spellThreeDesc.setText(EnterNames.thisPlayer.spells[2].description);
        } if (EnterNames.playerMagic > 8) {
            spellOneDesc.setText(EnterNames.thisPlayer.spells[0].description);
        }
        configureNextButton();
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
        if (EnterNames.playerMagic >= 0) {
            spellOne.setText(EnterNames.thisPlayer.spells[0].name);
        } if (EnterNames.playerMagic > 3) {
            spellTwo.setText(EnterNames.thisPlayer.spells[1].name);
        } if (EnterNames.playerMagic > 5) {
            spellThree.setText(EnterNames.thisPlayer.spells[2].name);
        } if (EnterNames.playerMagic > 8) {
            spellOne.setText(EnterNames.thisPlayer.spells[0].name);
        }
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
                if (EnterNames.thisPlayer.spells[0].id != 127) {
                    Gameplay.spellNum = 0;
                    messageBox.setText("");
                    finish();
                } else {
                    messageBox.setText(noSpellEquipped);
                }
            }
        });
        spellTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EnterNames.thisPlayer.spells[1].id != 127) {
                    Gameplay.spellNum = 1;
                    messageBox.setText("");
                    finish();
                } else {
                    messageBox.setText(noSpellEquipped);
                }
            }
        });
        spellThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EnterNames.thisPlayer.spells[2].id != 127) {
                    Gameplay.spellNum = 2;
                    messageBox.setText("");
                    finish();
                } else {
                    messageBox.setText(noSpellEquipped);
                }
            }
        });
        spellFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EnterNames.thisPlayer.spells[3].id != 127) {
                    Gameplay.spellNum = 3;
                    messageBox.setText("");
                    finish();
                } else {
                    messageBox.setText(noSpellEquipped);
                }
            }
        });
        spellFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EnterNames.thisPlayer.spells[4].id != 127) {
                    Gameplay.spellNum = 4;
                    messageBox.setText("");
                    finish();
                } else {
                    messageBox.setText(noSpellEquipped);
                }
            }
        });
        spellSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EnterNames.thisPlayer.spells[5].id != 127) {
                    Gameplay.spellNum = 5;
                    messageBox.setText("");
                    finish();
                } else {
                    messageBox.setText(noSpellEquipped);
                }
            }
        });
        spellSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EnterNames.thisPlayer.spells[6].id != 127) {
                    Gameplay.spellNum = 6;
                    messageBox.setText("");
                    finish();
                } else {
                    messageBox.setText(noSpellEquipped);
                }
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
