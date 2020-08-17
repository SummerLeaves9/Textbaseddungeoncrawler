package com.example.doome.dungeon_lords;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Sleeping extends AppCompatActivity {

    final String dungeonExitCampingText = "You're tired from fighting your way through that " +
            "place. You decide to acquire a sufficient amount of sleep.";

    final String exhaustedFromWalking = "You stop next to a tree and turn in for the day.";

    final String sleepingInATown = "You lie in the soft bed, exhausted. You couldn't bring " +
            "yourself to get out of it, it's so comfortable!";

    final String inconvenient = "Well, that was inconvenient. You should really rest, every " +
            "second counts now.";

    final String restingText = "Resting";

    boolean encounterOver = false;

    byte randomEncounterSeconds;

    byte maxSecondCount = 7;

    private byte[] earlyGameEncounter = {4, 14, 15};

    private byte[] midGameEncounter = {4, 16, 17};

    private byte[] lateGameEncounter = {4, 18, 19};

    pl.droidsonroids.gif.GifImageView loadingDots;
    TextView restingView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camping);
        loadingDots = findViewById(R.id.loadingDotsGifView);
        restingView = findViewById(R.id.campingMessage);
        if (Outside.isCamping) {
            randomEncounterSeconds = (byte) ((Math.random() * 9) + 1);
        } else {
            randomEncounterSeconds = 10;
        }
        if (!encounterOver) {
            loadingDots.setVisibility(View.INVISIBLE);
            configureNextButton();
        } else {
            loadingDots.setVisibility(View.VISIBLE);
            restingView.setVisibility(View.VISIBLE);
        }
        setRestingView();
    }

    private void configureNextButton() {
        final Button sleepButton = findViewById(R.id.sleepButton);
        sleepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sleepButton.setVisibility(View.GONE);
                loadingDots.setVisibility(View.VISIBLE);
                restingView.setText(restingText);
                if ((randomEncounterSeconds < maxSecondCount) && !encounterOver) {
                    mHandler.postDelayed(mEncounterChanceInstance, randomEncounterSeconds * 1000);
                } else if (encounterOver) {
                    mHandler.postDelayed(mPeacefulNightInstance, (maxSecondCount - randomEncounterSeconds) * 1000);
                } else {
                    mHandler.postDelayed(mPeacefulNightInstance, maxSecondCount * 1000);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {}

    private void setRestingView() {
        if (Outside.isStartingOrWaking) {
            restingView.setText(dungeonExitCampingText);
        } else if (!Outside.isCamping) {
            restingView.setText(sleepingInATown);
        } else {
            restingView.setText(exhaustedFromWalking);
        }

    }

    private final Runnable mEncounterChanceInstance = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            if (EnterNames.thisPlayer.statSum > 50) {
                hardCampEncounter();
            } else if (EnterNames.thisPlayer.statSum > 40) {
                middleCampEncounter();
            } else {
                easyCampEncounter();
            }
        }
    };

    private final Runnable mPeacefulNightInstance = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            endNight();
        }
    };

    private final Runnable delayDisplayUpdate = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            restingView.setText(inconvenient);
            loadingDots.setVisibility(View.INVISIBLE);
            View s = findViewById(R.id.sleepButton);
            s.setVisibility(View.VISIBLE);
        }
    };

    private final Handler mHandler = new Handler();

    private void easyCampEncounter() {
        Gameplay.thisDungeon = earlyGameEncounter;
        generalCampEncounter();
    }

    private void middleCampEncounter() {
        Gameplay.thisDungeon = midGameEncounter;
        generalCampEncounter();
    }

    private void hardCampEncounter() {
        Gameplay.thisDungeon = lateGameEncounter;
        generalCampEncounter();
    }

    private void generalCampEncounter() {
        encounterOver = true;
        startActivity(new Intent(Sleeping.this, Gameplay.class));
        mHandler.postDelayed(delayDisplayUpdate, 2000);
    }

    private void endNight() {
        Outside.whichTime += 2;
        Outside.whichTime %= 4;
        Outside.isStartingOrWaking = false;
        if (Outside.isCamping) {
            byte hpToRestore = (byte) (EnterNames.thisPlayer.hp - EnterNames.thisPlayer.liveHP);
            hpToRestore *= .5;
            EnterNames.thisPlayer.liveHP += hpToRestore;
            byte mpToRestore = (byte) (EnterNames.thisPlayer.mp - EnterNames.thisPlayer.liveMP);
            mpToRestore *= .5;
            EnterNames.thisPlayer.liveMP += mpToRestore;
        } else {
            EnterNames.thisPlayer.liveHP = EnterNames.thisPlayer.hp;
            EnterNames.thisPlayer.liveMP = EnterNames.thisPlayer.mp;
        }
        Exploration.stepsTraversed = 0;
        startActivity(new Intent(Sleeping.this, Outside.class));
    }
}
