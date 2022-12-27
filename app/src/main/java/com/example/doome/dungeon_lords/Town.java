package com.example.doome.dungeon_lords;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Town extends AppCompatActivity {

    final String newLocations = "New locations added to map.";

    final String noGold = "You don't have enough gold...";

    TextView townNameDisplay;
    TextView townMessage;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town);
        townNameDisplay = findViewById(R.id.townNameDisplay);
        townNameDisplay.setText(Exploration.getTownName(Exploration.visitedTowns[Exploration.thisTownIndex][0]));
        townMessage = findViewById(R.id.townMessage);
        townMessage.setVisibility(View.GONE);
        configureNextButton();
    }

    private void configureNextButton() {
        Button visitShopButton = findViewById(R.id.townVisitShopButton);
        Button talkButton = findViewById(R.id.townTalkButton);
        Button hotelButton = findViewById(R.id.townHotelButton);
        Button masterButton = findViewById(R.id.townMasterButton);
        final Button examineBillBoard = findViewById(R.id.townLookForQuestsButton);
        final Button leaveButton = findViewById(R.id.townLeaveButton);
        if (Exploration.thisTownIndex % 2 == 1) {
            if (Exploration.visitedTowns[Exploration.thisTownIndex][3] == 0) {
                leaveButton.setVisibility(View.GONE);
            }
            talkButton.setVisibility(View.VISIBLE);
            examineBillBoard.setVisibility(View.GONE);
        } else {
            leaveButton.setVisibility(View.VISIBLE);
            talkButton.setVisibility(View.GONE);
            if (Exploration.visitedTowns[Exploration.thisTownIndex][3] == 0) {
                examineBillBoard.setVisibility(View.VISIBLE);
            } else {
                examineBillBoard.setVisibility(View.GONE);
            }
        }
        visitShopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                townMessage.setText("");
                BuyingStuff.shopOrMaster = true;
                townMessage.setVisibility(View.GONE);
                startActivity(new Intent(Town.this, BuyingStuff.class));
            }
        });
        talkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                townMessage.setText("");
                Dialogue.atHouse = false;
                mHandler.postDelayed(delayDisplayUpdate, 2000);
                Exploration.visitedTowns[Exploration.thisTownIndex][3] = 1;
                townMessage.setVisibility(View.GONE);
                startActivity(new Intent(Town.this, Dialogue.class));
            }
        });
        hotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EnterNames.thisPlayer.myGold > 99) {
                    townMessage.setText("");
                    Outside.isCamping = false;
                    Spell.usedVision = false;
                    EnterNames.thisPlayer.myGold -= 100;
                    startActivity(new Intent(Town.this, Sleeping.class));
                } else {
                    townMessage.setVisibility(View.VISIBLE);
                    townMessage.setText(noGold);
                }
            }
        });
        masterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                townMessage.setText("");
                BuyingStuff.shopOrMaster = false;
                townMessage.setVisibility(View.GONE);
                startActivity(new Intent(Town.this, BuyingStuff.class));
            }
        });
        examineBillBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exploration.visitedTowns[Exploration.thisTownIndex][3] = 1;
                townMessage.setText(newLocations);
                townMessage.setVisibility(View.VISIBLE);
                Exploration.examineMap();
                examineBillBoard.setVisibility(View.GONE);
            }
        });
        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                townMessage.setText("");
                BuyingStuff.shopConfig[1] = 0;
                Intent townIntent = new Intent(Town.this, Exploration.class);
                townIntent.putExtra("fromTown", true);
                startActivity(townIntent);
            }
        });
    }

    private final Runnable delayDisplayUpdate = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            View l = findViewById(R.id.townLeaveButton);
            l.setVisibility(View.VISIBLE);
        }
    };

    private final Handler mHandler = new Handler();

    @Override
    public void onBackPressed() {}
}
