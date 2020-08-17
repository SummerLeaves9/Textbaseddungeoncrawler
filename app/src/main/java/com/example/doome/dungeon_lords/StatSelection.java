package com.example.doome.dungeon_lords;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

public class StatSelection extends AppCompatActivity {

    public static boolean statsChosen = false;

    public static byte whichStat = 0;

    public static final byte thisStatPoints = 30;

    public static byte thisLiveStatPoints = 30;

    String statIndicatorText = "Select stats. Points remaining: " + thisLiveStatPoints;

    String tooFewPointsWarning = "You don't have enough points to set this stat so high." +
            " Points remaining: " + thisLiveStatPoints;

    String strength = "Strength: ";

    String accuracy = "Accuracy: ";

    String defense = "Defense: ";

    String agility = "Agility: ";

    String intelligence = "Intelligence: ";

    String magic = "Magic: ";

    String luck = "Luck: ";

    TextView StatSelect;
    TextView StatIndicator;
    TextView statStrengthIndicator;
    TextView statAccuracyIndicator;
    TextView statDefenseIndicator;
    TextView statAgilityIndicator;
    TextView statIntelligenceIndicator;
    TextView statMagicIndicator;
    TextView statLuckIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_selection);
        StatSelect = findViewById(R.id.StatSelect);
        StatIndicator = findViewById(R.id.StatIndicator);
        statStrengthIndicator = findViewById(R.id.statStrengthIndicator);
        statAccuracyIndicator = findViewById(R.id.statAccuracyIndicator);
        statDefenseIndicator = findViewById(R.id.statDefenseIndicator);
        statAgilityIndicator = findViewById(R.id.statAgilityIndicator);
        statIntelligenceIndicator = findViewById(R.id.statIntelligenceIndicator);
        statMagicIndicator = findViewById(R.id.statMagicIndicator);
        statLuckIndicator = findViewById(R.id.statLuckIndicator);
        StatIndicator.setText(strength);
        StatSelect.setText(statIndicatorText);
        Button one = findViewById(R.id.EnterOne);
        Button two = findViewById(R.id.EnterTwo);
        Button three = findViewById(R.id.EnterThree);
        Button four = findViewById(R.id.EnterFour);
        Button five = findViewById(R.id.EnterFive);
        Button six = findViewById(R.id.EnterSix);
        Button seven = findViewById(R.id.EnterSeven);
        Button eight = findViewById(R.id.EnterEight);
        Button nine = findViewById(R.id.EnterNine);
        Button ten = findViewById(R.id.EnterTen);
        Button zero = findViewById(R.id.EnterZero);
        if (!statsChosen) {
            one.setVisibility(View.VISIBLE);
            two.setVisibility(View.VISIBLE);
            three.setVisibility(View.VISIBLE);
            four.setVisibility(View.VISIBLE);
            five.setVisibility(View.VISIBLE);
            six.setVisibility(View.VISIBLE);
            seven.setVisibility(View.VISIBLE);
            eight.setVisibility(View.VISIBLE);
            nine.setVisibility(View.VISIBLE);
            ten.setVisibility(View.VISIBLE);
            zero.setVisibility(View.VISIBLE);
            StatSelect.setVisibility(View.VISIBLE);
            StatIndicator.setVisibility(View.VISIBLE);
        } else {
            strength = "Strength: " + EnterNames.thisPlayer.strengthValue;
            accuracy = "Accuracy: " + EnterNames.thisPlayer.accuracyValue;
            defense = "Defense: " + EnterNames.thisPlayer.defenseValue;
            agility = "Agility: " + EnterNames.thisPlayer.agilityValue;
            intelligence = "Intelligence: " + EnterNames.thisPlayer.intelligenceValue;
            magic = "Magic: " + EnterNames.thisPlayer.magicValue;
            luck = "Luck: " + EnterNames.thisPlayer.luckValue;
            statStrengthIndicator.setText(strength);
            statAccuracyIndicator.setText(accuracy);
            statDefenseIndicator.setText(defense);
            statAgilityIndicator.setText(agility);
            statIntelligenceIndicator.setText(intelligence);
            statMagicIndicator.setText(magic);
            statLuckIndicator.setText(luck);
            one.setVisibility(View.GONE);
            two.setVisibility(View.GONE);
            three.setVisibility(View.GONE);
            four.setVisibility(View.GONE);
            five.setVisibility(View.GONE);
            six.setVisibility(View.GONE);
            seven.setVisibility(View.GONE);
            eight.setVisibility(View.GONE);
            nine.setVisibility(View.GONE);
            ten.setVisibility(View.GONE);
            zero.setVisibility(View.GONE);
            StatSelect.setVisibility(View.GONE);
            StatIndicator.setVisibility(View.GONE);
        }
        configureNextButton();
    }
    private void configureNextButton() {
        Button zero = findViewById(R.id.EnterZero);
        Button one = findViewById(R.id.EnterOne);
        Button two = findViewById(R.id.EnterTwo);
        Button three = findViewById(R.id.EnterThree);
        Button four = findViewById(R.id.EnterFour);
        Button five = findViewById(R.id.EnterFive);
        Button six = findViewById(R.id.EnterSix);
        Button seven = findViewById(R.id.EnterSeven);
        Button eight = findViewById(R.id.EnterEight);
        Button nine = findViewById(R.id.EnterNine);
        Button ten = findViewById(R.id.EnterTen);
        Button backButton = findViewById(R.id.statBackButton1);
        if (statsChosen) {
            backButton.setVisibility(View.VISIBLE);
        } else {
            backButton.setVisibility(View.GONE);
        }


        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 0);
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 1);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 2);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 3);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 4);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 5);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 6);
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 7);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 8);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 9);
            }
        });
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPlayerStat((byte) 10);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private final Runnable delayDisplayUpdate = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            Button one = findViewById(R.id.EnterOne);
            Button two = findViewById(R.id.EnterTwo);
            Button three = findViewById(R.id.EnterThree);
            Button four = findViewById(R.id.EnterFour);
            Button five = findViewById(R.id.EnterFive);
            Button six = findViewById(R.id.EnterSix);
            Button seven = findViewById(R.id.EnterSeven);
            Button eight = findViewById(R.id.EnterEight);
            Button nine = findViewById(R.id.EnterNine);
            Button ten = findViewById(R.id.EnterTen);
            Button zero = findViewById(R.id.EnterZero);
            TextView statSelection = findViewById(R.id.StatSelect);
            TextView statSelectionDisplay = findViewById(R.id.StatIndicator);
            one.setVisibility(View.GONE);
            two.setVisibility(View.GONE);
            three.setVisibility(View.GONE);
            four.setVisibility(View.GONE);
            five.setVisibility(View.GONE);
            six.setVisibility(View.GONE);
            seven.setVisibility(View.GONE);
            eight.setVisibility(View.GONE);
            nine.setVisibility(View.GONE);
            ten.setVisibility(View.GONE);
            zero.setVisibility(View.GONE);
            statSelection.setVisibility(View.GONE);
            statSelectionDisplay.setVisibility(View.GONE);
        }
    };

    private final Handler mHandler = new Handler();

    @Override
    public void onBackPressed() {}

    public void setStatIndicator(boolean valid) {
        if (valid) {
            if (whichStat == 0) {
                StatIndicator.setText(accuracy);
            } else if (whichStat == 1) {
                StatIndicator.setText(defense);
            } else if (whichStat == 2) {
                StatIndicator.setText(agility);
            } else if (whichStat == 3) {
                StatIndicator.setText(intelligence);
            } else if (whichStat == 4) {
                StatIndicator.setText(magic);
            } else if (whichStat == 5) {
                StatIndicator.setText(luck);
            }
        }
        else {
            tooFewPointsWarning = "You don't have enough points to set this stat so high." +
                    " Points remaining: " + thisLiveStatPoints;
            StatSelect.setText(tooFewPointsWarning);
        }
    }

    public void setPlayerStat(byte value) {
        if (whichStat == 0) {
            EnterNames.playerStrength = value;
            strength += value;
            statStrengthIndicator.setText(strength);
        } else if (whichStat == 1) {
            EnterNames.playerAccuracy = value;
            accuracy += value;
            statAccuracyIndicator.setText(accuracy);
        } else if (whichStat == 2) {
            EnterNames.playerDefense = value;
            defense += value;
            statDefenseIndicator.setText(defense);
        } else if (whichStat == 3) {
            EnterNames.playerAgility = value;
            agility += value;
            statAgilityIndicator.setText(agility);
        } else if (whichStat == 4) {
            EnterNames.playerIntelligence = value;
            intelligence += value;
            statIntelligenceIndicator.setText(intelligence);
        } else if (whichStat == 5) {
            EnterNames.playerMagic = value;
            magic += value;
            statMagicIndicator.setText(magic);
        } else {
            EnterNames.playerLuck = value;
            if (!(thisLiveStatPoints - value < 0)) {
                luck += value;
                statLuckIndicator.setText(luck);
                mHandler.postDelayed(delayDisplayUpdate, 1000);
                startActivity(new Intent(StatSelection.this, EnterNames.class));
            }
        }
        if (thisLiveStatPoints - value < 0) {
            setStatIndicator(false);
        }
        else {
            setStatIndicator(true);
            thisLiveStatPoints -= value;
            statIndicatorText = "Select stats. Points remaining: " + thisLiveStatPoints;
            StatSelect.setText(statIndicatorText);
            whichStat++;
        }
    }
}
