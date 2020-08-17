package com.example.doome.dungeon_lords;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Items extends AppCompatActivity {

    static boolean wasInitialized = false;
    /**
     * Message when player uses an Magic potion
     */
    public final String magicPotionUse = "You drink a Magic Potion...MP restored!";

    /**
     * Message when player uses a vitality potion
     */
    public final String vitalityPotionUse = "You drink a vitality potion: HP overfilled!";

    /**
     * Message when player uses a brain juice potion
     */
    public final String brainJuiceUse = "You drink a dose of Brain Juice(TM). Intelligence +3!";

    /**
     * Message when the player uses a greed potion
     */
    public final String greedPotionUse = "You drink a greed potion. Now you'll extract more gold " +
            "from enemies! ";

    /**
     * Message when a player tries to use both a brain juice potion and a greed potion in one
     * dungeon
     */
    public final String effectPotionOD = "You can't drink two lasting potions in one sitting! The" +
            " effects are too strong.";

    /**
     * Description of the first item the player gets
     */
    public final String armorDesc = "-A piece of strange, yet not that foreign armor given to you " +
            "by William. Grants +2 Defense.";

    /**
     * Description of the second item the player gets
     */
    public final String secondItemDesc = "-A piece of strange, yet not that foreign armor given to you -A piece of strange, yet not that";

    /**
     * Description of the third item the player gets
     */
    public final String thirdItemDesc = "-A piece of strange, yet not that foreign armor given to you -A piece of strange, yet not that";

    /**
     * Message when player has no items
     */
    public final String noItems = "(You have no items)";

    /**
     * Cereal when haves items
     */
    public final String yesItems = "Items:";

    TextView itemMessage;
    TextView magicPotionDesc;
    TextView vitalityPotionDesc;
    TextView brainJuiceDesc;
    TextView greedPotionDesc;
    TextView magicPotionNum;
    TextView vitalityPotionNum;
    TextView brainJuiceNum;
    TextView greedPotionNum;
    TextView itemOneDesc;
    TextView itemTwoDesc;
    TextView itemThreeDesc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        itemMessage = findViewById(R.id.itemsMessageDisplay);
        magicPotionDesc = findViewById(R.id.magicPotionDisplay);
        vitalityPotionDesc = findViewById(R.id.vitalityPotionDisplay);
        brainJuiceDesc = findViewById(R.id.brainJuiceDisplay);
        greedPotionDesc = findViewById(R.id.greedPotionDisplay);
        magicPotionNum = findViewById(R.id.magicPotionNumDisplay);
        vitalityPotionNum = findViewById(R.id.vitalityPotionNumDisplay);
        brainJuiceNum = findViewById(R.id.brainJuiceNumDisplay);
        greedPotionNum = findViewById(R.id.greedPotionNumDisplay);
        itemOneDesc = findViewById(R.id.itemOneText);
        itemTwoDesc = findViewById(R.id.itemTwoText);
        itemThreeDesc = findViewById(R.id.itemThreeText);
        if (!wasInitialized) {
            magicPotionDesc.setText(BuyingStuff.magicPotion);
            vitalityPotionDesc.setText(BuyingStuff.vitalityPotion);
            brainJuiceDesc.setText(BuyingStuff.brainJuice);
            greedPotionDesc.setText(BuyingStuff.greedPotion);
            itemOneDesc.setText(armorDesc);
            itemTwoDesc.setText(secondItemDesc);
            itemThreeDesc.setText(thirdItemDesc);
        }
        boolean hasItems = false;
        if (EnterNames.thisPlayer.myItems[0] > 0) {
            magicPotionDesc.setVisibility(View.VISIBLE);
            magicPotionNum.setVisibility(View.VISIBLE);
            String magicNum = EnterNames.thisPlayer.myItems[0] + "";
            magicPotionNum.setText(magicNum);
            hasItems = true;
        } else {
            magicPotionDesc.setVisibility(View.GONE);
            magicPotionNum.setVisibility(View.GONE);
        }

        if (EnterNames.thisPlayer.myItems[1] > 0) {
            vitalityPotionDesc.setVisibility(View.VISIBLE);
            vitalityPotionNum.setVisibility(View.VISIBLE);
            String vitalityNum = EnterNames.thisPlayer.myItems[1] + "";
            vitalityPotionNum.setText(vitalityNum);
            hasItems = true;
        } else {
            vitalityPotionDesc.setVisibility(View.GONE);
            vitalityPotionNum.setVisibility(View.GONE);
        }

        if (EnterNames.thisPlayer.myItems[2] > 0) {
            brainJuiceDesc.setVisibility(View.VISIBLE);
            brainJuiceNum.setVisibility(View.VISIBLE);
            String brainNum = EnterNames.thisPlayer.myItems[2] + "";
            brainJuiceNum.setText(brainNum);
            hasItems = true;
        } else {
            brainJuiceDesc.setVisibility(View.GONE);
            brainJuiceNum.setVisibility(View.GONE);
        }

        if (EnterNames.thisPlayer.myItems[3] > 0) {
            greedPotionDesc.setVisibility(View.VISIBLE);
            greedPotionNum.setVisibility(View.VISIBLE);
            String greedNum = EnterNames.thisPlayer.myItems[3] + "";
            greedPotionNum.setText(greedNum);
            hasItems = true;
        } else {
            greedPotionDesc.setVisibility(View.GONE);
            greedPotionNum.setVisibility(View.GONE);
        }

        if (EnterNames.thisPlayer.myItems[4] > 0) {
            itemOneDesc.setVisibility(View.VISIBLE);
            hasItems = true;
        } else {
            itemOneDesc.setVisibility(View.GONE);
        }

        if (EnterNames.thisPlayer.myItems[5] > 0) {
            itemTwoDesc.setVisibility(View.VISIBLE);
            hasItems = true;
        } else {
            itemTwoDesc.setVisibility(View.GONE);
        }

        if (EnterNames.thisPlayer.myItems[6] > 0) {
            itemThreeDesc.setVisibility(View.VISIBLE);
            hasItems = true;
        } else {
            itemThreeDesc.setVisibility(View.GONE);
        }

        if (!hasItems) {
            itemMessage.setText(noItems);
        }

        configureNextButton();
    }

    private void configureNextButton() {
        Button drinkMagicPotion = findViewById(R.id.drinkMagicButton);
        Button drinkVitalityPotion = findViewById(R.id.drinkVitalityButton);
        Button drinkBrainJuice = findViewById(R.id.drinkBrainButton);
        Button drinkGreedPotion = findViewById(R.id.drinkGreedButton);
        Button finishButton = findViewById(R.id.itemsFinishButton);
        drinkMagicPotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnterNames.thisPlayer.myItems[0]--;
                String magicNum = EnterNames.thisPlayer.myItems[0] + "";
                magicPotionNum.setText(magicNum);
                EnterNames.thisPlayer.liveMP = EnterNames.thisPlayer.mp;
                itemMessage.setText(magicPotionUse);
            }
        });
        drinkVitalityPotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnterNames.thisPlayer.myItems[1]--;
                EnterNames.thisPlayer.liveHP = (byte) (EnterNames.thisPlayer.hp * 1.4);
                String vitalityNum = EnterNames.thisPlayer.myItems[1] + "";
                vitalityPotionNum.setText(vitalityNum);
                itemMessage.setText(vitalityPotionUse);
            }
        });
        drinkBrainJuice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Gameplay.usedBrainJuice || Gameplay.usedGreedPotion) {
                    itemMessage.setText(effectPotionOD);
                } else {
                    EnterNames.thisPlayer.myItems[2]--;
                    EnterNames.thisPlayer.intelligenceValue += 3;
                    EnterNames.thisPlayer.setAllStats();
                    Gameplay.usedBrainJuice = true;
                    String brainNum = EnterNames.thisPlayer.myItems[2] + "";
                    brainJuiceNum.setText(brainNum);
                    itemMessage.setText(brainJuiceUse);
                }

            }
        });
        drinkGreedPotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Gameplay.usedBrainJuice || Gameplay.usedGreedPotion) {
                    itemMessage.setText(effectPotionOD);
                } else {
                    EnterNames.thisPlayer.myItems[3]--;
                    Gameplay.usedGreedPotion = true;
                    String greedNum = EnterNames.thisPlayer.myItems[3] + "";
                    greedPotionNum.setText(greedNum);
                    itemMessage.setText(greedPotionUse);
                }
            }
        });
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean sourceActivity = getIntent().getBooleanExtra("source class", false);
                if (sourceActivity) {
                    startActivity(new Intent(Items.this, Outside.class));
                } else {
                    startActivity(new Intent(Items.this, Gameplay.class));
                }
            }
        });

        if (EnterNames.thisPlayer.myItems[0] > 0) {
            drinkMagicPotion.setVisibility(View.VISIBLE);
        } else {
            drinkMagicPotion.setVisibility(View.GONE);
        }

        if (EnterNames.thisPlayer.myItems[1] > 0) {
            drinkVitalityPotion.setVisibility(View.VISIBLE);
        } else {
            drinkVitalityPotion.setVisibility(View.GONE);
        }

        if (EnterNames.thisPlayer.myItems[2] > 0) {
            drinkBrainJuice.setVisibility(View.VISIBLE);
        } else {
            drinkBrainJuice.setVisibility(View.GONE);
        }

        if (EnterNames.thisPlayer.myItems[3] > 0) {
            drinkGreedPotion.setVisibility(View.VISIBLE);
        } else {
            drinkGreedPotion.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {}
}
