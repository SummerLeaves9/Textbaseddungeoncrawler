package com.example.doome.dungeon_lords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Outside extends AppCompatActivity {

    public static boolean isStartingOrWaking = false;

    public static boolean isCamping;

    public static boolean firstDungeon = true;

    String firstDungeonComplete = "The next door leads out into the open air, multiple " +
            "times fresher than the air of that place. You're in a forest. But not even this " +
            "sight lets you remember where you are, or what you were doing here. Thankfully, " +
            "you remember where you are on your map. ";

    String campingNightWake1 = "You wake up, feeling stiff but overall decent. HP and MP somewhat" +
            " restored. ";

    String campingNightWake2 = "You pick up your " + EnterNames.thisPlayer.weaponName + " and " +
            "prepare to head out. ";

    String needToFindTown = "You should really find a town, you won't last long in the wild. ";

    String townHotelWake = "You're awoken by the sun shining gently on your bed through the " +
            "window. You slept great! HP and MP fully restored. ";

    public static byte whichTime;

    final String dayTimeMorning = "It's morning. ";

    final String dayTimeMidday = "The sun shines down from overhead. ";

    final String dayTimeSunset = "The sun is setting. ";

    final String dayTimeNight = "It's night time. ";

    final String usualCaseDungeonComplete = "The next door takes you out a secluded exit door. " +
            "You look behind you to see the room's walls reveal tubes of light. The light creeps " +
            "through the tubes, illuminating the bricks a bright orange. Dungeon complete! ";

    private String outsideMessage;

    TextView healthDisplay;
    TextView mpDisplay;
    TextView finishedDungeonDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outside);
        finishedDungeonDisplay = findViewById(R.id.finishedDungeonMessage);
        healthDisplay = findViewById(R.id.hudLine1);
        mpDisplay = findViewById(R.id.hudLine2);

        configureNextButton();

        setFinishedDungeonMessage();
        finishedDungeonDisplay.setText(outsideMessage);

    }

    private void configureNextButton() {
        Button findATown = findViewById(R.id.exploreButton);
        Button setUpCamp = findViewById(R.id.setUpCampButton);
        Button itemsButton = findViewById(R.id.itemsButton);
        Button backToTown = findViewById(R.id.backToTownButton);
        Button viewStatsButton = findViewById(R.id.viewStatsButton);
        /*
        if (!isCamping) {
            backToTown.setVisibility(View.VISIBLE);
            setUpCamp.setVisibility(View.GONE);
            findATown.setVisibility(View.GONE);
            backToTown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Outside.this, Town.class));
                }
            });
        } else {
            setUpCamp.setVisibility(View.VISIBLE);
            backToTown.setVisibility(View.GONE);
            findATown.setVisibility(View.VISIBLE);
        }
        if (firstDungeon || isStartingOrWaking) {
            itemsButton.setVisibility(View.GONE);
            findATown.setVisibility(View.GONE);
            setUpCamp.setVisibility(View.GONE);
            backToTown.setVisibility(View.GONE);
            viewStatsButton.setVisibility(View.GONE);
            Button initSetUpCamp = findViewById(R.id.initSetUpCampButton);
            initSetUpCamp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isCamping = true;
                    startActivity(new Intent(Outside.this, Sleeping.class));
                }
            });
        } else {
            itemsButton.setVisibility(View.VISIBLE);
            findATown.setVisibility(View.VISIBLE);
            setUpCamp.setVisibility(View.VISIBLE);
            viewStatsButton.setVisibility(View.VISIBLE);
            View s = findViewById(R.id.initSetUpCampButton);
            s.setVisibility(View.GONE);
        }
        */
        if (isStartingOrWaking) {
            itemsButton.setVisibility(View.GONE);
            findATown.setVisibility(View.GONE);
            setUpCamp.setVisibility(View.GONE);
            backToTown.setVisibility(View.GONE);
            viewStatsButton.setVisibility(View.GONE);
            Button initSetUpCamp = findViewById(R.id.initSetUpCampButton);
            initSetUpCamp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isCamping = true;
                    startActivity(new Intent(Outside.this, Sleeping.class));
                }
            });
        } else {
            if (isCamping) {
                setUpCamp.setVisibility(View.VISIBLE);
                backToTown.setVisibility(View.GONE);
                findATown.setVisibility(View.VISIBLE);
            } else {
                backToTown.setVisibility(View.VISIBLE);
                setUpCamp.setVisibility(View.GONE);
                findATown.setVisibility(View.GONE);
                backToTown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Outside.this, Town.class));
                    }
                });
            }
            View s = findViewById(R.id.initSetUpCampButton);
            s.setVisibility(View.GONE);
        }
        findATown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstDungeon) {
                    firstDungeon = false;
                }
                if (isStartingOrWaking) {
                    isStartingOrWaking = false;
                }
                startActivity(new Intent(Outside.this, Exploration.class));
            }
        });
        setUpCamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCamping = true;
                startActivity(new Intent(Outside.this, Sleeping.class));
            }
        });
        itemsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent intent = new Intent(Outside.this, Items.class);
                intent.putExtra("source class", true);
                startActivity(intent);
                }
        });
        viewStatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Outside.this, StatSelection.class));
            }
        });
    }

    @Override
    public void onBackPressed() {}

    private void setFinishedDungeonMessage() {
        String health = "HP: " + EnterNames.thisPlayer.liveHP + "/" + EnterNames.thisPlayer.hp;
        String mp = "MP: " + EnterNames.thisPlayer.liveMP + "/" + EnterNames.thisPlayer.mp;
        healthDisplay.setText(health);
        mpDisplay.setText(mp);
        /*
        if (isStartingOrWaking) {
            if (isCamping) {
                outsideMessage = campingNightWake1;
                switch (whichTime) {
                    case 0: outsideMessage += dayTimeMorning;
                        break;
                    case 1: outsideMessage += dayTimeMidday;
                        break;
                    case 2: outsideMessage += dayTimeSunset;
                        break;
                    case 3: outsideMessage += dayTimeNight;
                        break;
                }
                outsideMessage += campingNightWake2;
                if (Exploration.visitedTowns[0].length == 0) {
                    outsideMessage += needToFindTown;
                }
            } else {
                outsideMessage = townHotelWake;
                switch (whichTime) {
                    case 0: outsideMessage += dayTimeMorning;
                        break;
                    case 1: outsideMessage += dayTimeMidday;
                        break;
                    case 2: outsideMessage += dayTimeSunset;
                        break;
                    case 3: outsideMessage += dayTimeNight;
                        break;
                }
                outsideMessage += campingNightWake2;
            }
        } else {
            if (firstDungeon) {
                outsideMessage = firstDungeonComplete;
                whichTime = (byte) (Math.random() * 4);
                switch (whichTime) {
                    case 0: outsideMessage += dayTimeMorning;
                        break;
                    case 1: outsideMessage += dayTimeMidday;
                        break;
                    case 2: outsideMessage += dayTimeSunset;
                        break;
                    case 3: outsideMessage += dayTimeNight;
                        break;
                }
                firstDungeon = false;
            } else {
                outsideMessage = usualCaseDungeonComplete;
                switch (whichTime) {
                    case 0: outsideMessage += dayTimeMorning;
                        break;
                    case 1: outsideMessage += dayTimeMidday;
                        break;
                    case 2: outsideMessage += dayTimeSunset;
                        break;
                    case 3: outsideMessage += dayTimeNight;
                        break;
                }
            }
        }
        */
        if (isStartingOrWaking) {
            if (firstDungeon) {
                outsideMessage = firstDungeonComplete;
                whichTime = (byte) (Math.random() * 4);
            } else {
                outsideMessage = usualCaseDungeonComplete;
            }
            switch (whichTime) {
                case 0: outsideMessage += dayTimeMorning;
                    break;
                case 1: outsideMessage += dayTimeMidday;
                    break;
                case 2: outsideMessage += dayTimeSunset;
                    break;
                case 3: outsideMessage += dayTimeNight;
                    break;
            }
        } else {
            if (isCamping) {
                outsideMessage = campingNightWake1;
            } else {
                outsideMessage = townHotelWake;
            }
            switch (whichTime) {
                case 0: outsideMessage += dayTimeMorning;
                    break;
                case 1: outsideMessage += dayTimeMidday;
                    break;
                case 2: outsideMessage += dayTimeSunset;
                    break;
                case 3: outsideMessage += dayTimeNight;
                    break;
            }
            outsideMessage += campingNightWake2;
            if (Exploration.visitedTowns[0].length == 0) {
                outsideMessage += needToFindTown;
            }
        }
    }
}
