package com.example.doome.dungeon_lords;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import static com.example.doome.dungeon_lords.Gameplay.spellsToLearn;

public class SpellSelection extends AppCompatActivity {

    TextView messageBox;
    TextView spellOneDesc;
    TextView spellTwoDesc;
    TextView spellThreeDesc;
    TextView spellFourDesc;
    TextView spellFiveDesc;
    TextView spellSixDesc;
    TextView spellSevenDesc;

    private final String noSpellEquipped = "There is no spell equipped in that slot!";

    public static String selectSlotForSpell = "Select Spell slot for " + Gameplay.spellsToLearn[0].name;

    private final String spellsSelected = "Spells configured! ";

    private byte spellsToLearnCounter = 0;

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
            if (EnterNames.thisPlayer.spells[0].id != 127) {
                spellOneDesc.setText(EnterNames.thisPlayer.spells[0].description);
            }
            if (EnterNames.thisPlayer.spells[1].id != 127) {
                spellTwoDesc.setText(EnterNames.thisPlayer.spells[1].description);
            }
            if (EnterNames.thisPlayer.spells[2].id != 127) {
                spellThreeDesc.setText(EnterNames.thisPlayer.spells[2].description);
            }
            if (EnterNames.thisPlayer.spells[3].id != 127) {
                spellFourDesc.setText(EnterNames.thisPlayer.spells[3].description);
            }
            if (EnterNames.thisPlayer.spells[4].id != 127) {
                spellFiveDesc.setText(EnterNames.thisPlayer.spells[4].description);
            }
            if (EnterNames.thisPlayer.spells[5].id != 127) {
                spellSixDesc.setText(EnterNames.thisPlayer.spells[5].description);
            }
            if (EnterNames.thisPlayer.spells[6].id != 127) {
                spellSevenDesc.setText(EnterNames.thisPlayer.spells[6].description);
            }
        if (Gameplay.isAddingSpell) {
            messageBox.setVisibility(View.VISIBLE);
            messageBox.setText(selectSlotForSpell);
        } else {
            messageBox.setVisibility(View.GONE);
        }
        configureNextButton();
    }
    private void configureNextButton() {
        final Button spellOne = (Button) findViewById(R.id.activateSpellOne);
        final Button spellTwo = (Button) findViewById(R.id.activateSpellTwo);
        final Button spellThree = (Button) findViewById(R.id.activateSpellThree);
        final Button spellFour = (Button) findViewById(R.id.activateSpellFour);
        final Button spellFive = (Button) findViewById(R.id.activateSpellFive);
        final Button spellSix = (Button) findViewById(R.id.activateSpellSix);
        final Button spellSeven = (Button) findViewById(R.id.activateSpellSeven);
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
            if (EnterNames.thisPlayer.spells[0].id != 127) {
                spellOne.setText(EnterNames.thisPlayer.spells[0].name);
            }
            if (EnterNames.thisPlayer.spells[1].id != 127) {
                spellTwo.setText(EnterNames.thisPlayer.spells[1].name);
            }
            if (EnterNames.thisPlayer.spells[2].id != 127) {
                spellThree.setText(EnterNames.thisPlayer.spells[2].name);
            }
            if (EnterNames.thisPlayer.spells[3].id != 127) {
                spellFour.setText(EnterNames.thisPlayer.spells[3].name);
            }
            if (EnterNames.thisPlayer.spells[4].id != 127) {
                spellFive.setText(EnterNames.thisPlayer.spells[4].name);
            }
            if (EnterNames.thisPlayer.spells[5].id != 127) {
                spellSix.setText(EnterNames.thisPlayer.spells[5].name);
            }
            if (EnterNames.thisPlayer.spells[6].id != 127) {
                spellSeven.setText(EnterNames.thisPlayer.spells[6].name);
            }
        if (Gameplay.isAddingSpell) {
            cancelButton.setVisibility(View.GONE);
        } else {
            cancelButton.setVisibility(View.VISIBLE);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        //This is where the functions of the spells are used when the user presses
        //the corresponding button.
        spellOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Gameplay.isAddingSpell) {
                    setGameplaySpellNum((byte) 0);
                } else {
                    setNewSpell((byte) 0);
                    spellOne.setText(EnterNames.thisPlayer.spells[0].name);
                    spellOneDesc.setText(EnterNames.thisPlayer.spells[0].description);
                }
            }
        });
        spellTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Gameplay.isAddingSpell) {
                    setGameplaySpellNum((byte) 1);
                } else {
                    setNewSpell((byte) 1);
                    spellTwo.setText(EnterNames.thisPlayer.spells[1].name);
                    spellTwoDesc.setText(EnterNames.thisPlayer.spells[1].description);
                }
            }
        });
        spellThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Gameplay.isAddingSpell) {
                    setGameplaySpellNum((byte) 2);
                } else {
                    setNewSpell((byte) 2);
                    spellThree.setText(EnterNames.thisPlayer.spells[2].name);
                    spellThreeDesc.setText(EnterNames.thisPlayer.spells[2].description);
                }
            }
        });
        spellFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Gameplay.isAddingSpell) {
                    setGameplaySpellNum((byte) 3);
                } else {
                    setNewSpell((byte) 3);
                    spellFour.setText(EnterNames.thisPlayer.spells[3].name);
                    spellFourDesc.setText(EnterNames.thisPlayer.spells[3].description);
                }
            }
        });
        spellFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Gameplay.isAddingSpell) {
                    setGameplaySpellNum((byte) 4);
                } else {
                    setNewSpell((byte) 4);
                    spellFive.setText(EnterNames.thisPlayer.spells[4].name);
                    spellFiveDesc.setText(EnterNames.thisPlayer.spells[4].description);
                }
            }
        });
        spellSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Gameplay.isAddingSpell) {
                    setGameplaySpellNum((byte) 5);
                } else {
                    setNewSpell((byte) 5);
                    spellSix.setText(EnterNames.thisPlayer.spells[5].name);
                    spellSixDesc.setText(EnterNames.thisPlayer.spells[5].description);
                }
            }
        });
        spellSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Gameplay.isAddingSpell) {
                    setGameplaySpellNum((byte) 6);
                } else {
                    setNewSpell((byte) 6);
                    spellSeven.setText(EnterNames.thisPlayer.spells[6].name);
                    spellSevenDesc.setText(EnterNames.thisPlayer.spells[6].description);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {}

    private void setGameplaySpellNum(byte buttonID) {
        if (EnterNames.thisPlayer.spells[buttonID].id != 127) {
            Gameplay.spellNum = buttonID;
            messageBox.setText("");
            //if (!(Gameplay.hasPickedSpell) && Gameplay.isBattling) {
            //    Gameplay.hasPickedSpell = true;
            //}
            finish();
        } else {
            messageBox.setText(noSpellEquipped);
        }
    }

    private void setNewSpell(byte buttonID) {
        //setting Player's new spell and updating spell selection menu text
        EnterNames.thisPlayer.spells[buttonID] = Gameplay.spellsToLearn[spellsToLearnCounter];
        //setting the spell in the 'new spells' array to the default spell value
        Gameplay.spellsToLearn[spellsToLearnCounter] = new Spell((byte) 127);
        //checks whether any spells are left in spellsToLearn
        if (spellsToLearn[0].id == spellsToLearn[2].id &&
                spellsToLearn[0].id == spellsToLearn[1].id) {
            spellsToLearnCounter = 0;
            messageBox.setText(spellsSelected);
            Gameplay.isAddingSpell = false;
            /*
            View y = findViewById(R.id.sayYesButton);
            y.setVisibility(View.GONE);
            View n = findViewById(R.id.sayNoButton);
            n.setVisibility(View.GONE);
            View d = findViewById(R.id.doneButton);
            d.setVisibility(View.VISIBLE);
             */
            mHandler.postDelayed(delayDisplayUpdate, 1000);
        } else {
            //updating more spell selection menu text
            spellsToLearnCounter++;
            selectSlotForSpell = "Select Spell slot for " + spellsToLearn[spellsToLearnCounter].name;
            messageBox.setText(selectSlotForSpell);
        }
    }

    private final Runnable delayDisplayUpdate = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            finish();
        }
    };

    private final Handler mHandler = new Handler();

}
