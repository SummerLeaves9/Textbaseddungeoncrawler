package com.example.doome.dungeon_lords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BuyingStuff extends AppCompatActivity {

    static int[] shopConfig = new int[6];

    String goldText = "Gold: " + EnterNames.thisPlayer.myGold;

    final String shop = "Shop:";

    final String master = "Master's House:";

    final String buy = "Buy: ";

    final String purchased = "'Thanks for your purchase!'";

    final String masterPurchased = "'A valuable lesson! Come again.'";

    final String notEnoughGold = "(You don't have enough gold...)";

    final String shopWelcome = "'Welcome! Have a look around.'";

    final String masterWelcome = "'Welcome to my abode. What can I do for you?'";

    final String cantTrain = "'I have nothing to teach you in this art. You've reached your " +
            "limit.'";

    final String strength = "Train Strength";
    final String accuracy = "Train Accuracy";
    final String defense = "Train Defense";
    final String agility = "Train Agility";
    final String intelligence = "Train Intelligence";
    final String magic = "Train Magic";
    final String luck = "Train Luck";

    public static boolean shopOrMaster = false;

    public static final String magicPotion = "-Magic Potion: Restores all Magic Points.";

    final static int baseMagicCost = 500;

    public static final String vitalityPotion = "-Vitality Potion: restores and overfills your HP " +
            "by 40%";

    final static int baseVitalityCost = 400;

    public static final String brainJuice = "-Brain Juice: Grants +3 Intelligence for one dungeon";

    final static int baseBrainCost = 600;

    public static final String greedPotion = "-Greed Potion: makes enemies drop more gold when " +
            "they are defeated";

    final static int baseGreedCost = 400;

    final static int statBuffCost = 1000;

    TextView goldDisplay;
    TextView buyingStuffHeader;
    TextView buyingStuffDesc;
    TextView itemOneDesc;
    TextView itemTwoDesc;
    TextView itemThreeDesc;
    TextView itemFourDesc;
    TextView itemFiveDesc;
    TextView itemSixDesc;
    TextView itemSevenDesc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying_stuff);
        goldDisplay = findViewById(R.id.buyingStuffGoldDisplay);
        buyingStuffHeader = findViewById(R.id.buyingStuffHeader);
        buyingStuffDesc = findViewById(R.id.buyingStuffMessage);
        itemOneDesc = findViewById(R.id.shopItemOneDesc);
        itemTwoDesc = findViewById(R.id.shopItemTwoDesc);
        itemThreeDesc = findViewById(R.id.shopItemThreeDesc);
        itemFourDesc = findViewById(R.id.masterTraitOneDesc);
        itemFiveDesc = findViewById(R.id.masterTraitTwoDesc);
        itemSixDesc = findViewById(R.id.masterTraitThreeDesc);
        itemSevenDesc = findViewById(R.id.masterTraitFourDesc);
        if (shopOrMaster) {
            buyingStuffHeader.setText(shop);
            buyingStuffDesc.setText(shopWelcome);
            itemFourDesc.setVisibility(View.GONE);
            itemFiveDesc.setVisibility(View.GONE);
            itemSixDesc.setVisibility(View.GONE);
            itemSevenDesc.setVisibility(View.GONE);
            if (shopConfig[1] == 0) {
                configureShop();
            }
            Button o = findViewById(R.id.shopBuyButtonOne);
            String buttonOneText = buy + shopConfig[1];
            o.setText(buttonOneText);
            Button tw = findViewById(R.id.shopBuyButtonTwo);
            String buttonTwoText = buy + shopConfig[3];
            tw.setText(buttonTwoText);
            Button th = findViewById(R.id.shopBuyButtonThree);
            String buttonThreeText = buy + shopConfig[5];
            th.setText(buttonThreeText);
            switch (shopConfig[0]) {
                case 0:
                    itemOneDesc.setText(magicPotion);
                    break;
                case 1:
                    itemOneDesc.setText(vitalityPotion);
                    break;
                case 2:
                    itemOneDesc.setText(brainJuice);
                    break;
                case 3:
                    itemOneDesc.setText(greedPotion);
                    break;
            }
            switch (shopConfig[2]) {
                case 0:
                    itemTwoDesc.setText(magicPotion);
                    break;
                case 1:
                    itemTwoDesc.setText(vitalityPotion);
                    break;
                case 2:
                    itemTwoDesc.setText(brainJuice);
                    break;
                case 3:
                    itemTwoDesc.setText(greedPotion);
                    break;
            }
            switch (shopConfig[4]) {
                case 0:
                    itemThreeDesc.setText(magicPotion);
                    break;
                case 1:
                    itemThreeDesc.setText(vitalityPotion);
                    break;
                case 2:
                    itemThreeDesc.setText(brainJuice);
                    break;
                case 3:
                    itemThreeDesc.setText(greedPotion);
                    break;
            }
        } else {
            buyingStuffHeader.setText(master);
            buyingStuffDesc.setText(masterWelcome);
            itemFourDesc.setVisibility(View.VISIBLE);
            itemFiveDesc.setVisibility(View.VISIBLE);
            itemSixDesc.setVisibility(View.VISIBLE);
            itemSevenDesc.setVisibility(View.VISIBLE);
            setMastersHouse();
        }
        goldText = "Gold: " + EnterNames.thisPlayer.myGold;
        goldDisplay.setText(goldText);
        configureNextButton();
    }

    private void configureNextButton() {
        Button buyButtonOne = findViewById(R.id.shopBuyButtonOne);
        Button buyButtonTwo = findViewById(R.id.shopBuyButtonTwo);
        Button buyButtonThree = findViewById(R.id.shopBuyButtonThree);
        Button buyButtonFour = findViewById(R.id.shopBuyButtonFour);
        Button buyButtonFive = findViewById(R.id.shopBuyButtonFive);
        Button buyButtonSix = findViewById(R.id.shopBuyButtonSix);
        Button buyButtonSeven = findViewById(R.id.shopBuyButtonSeven);
        Button backToTownButton = findViewById(R.id.shopBackToTownButton);
        buyButtonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shopOrMaster) {
                    if (EnterNames.thisPlayer.myGold < shopConfig[1]) {
                        buyingStuffDesc.setText(notEnoughGold);
                    } else {
                        buyingStuffDesc.setText(purchased);
                        EnterNames.thisPlayer.myItems[shopConfig[0]]++;
                        EnterNames.thisPlayer.myGold -= shopConfig[1];
                    }
                } else {
                    if (EnterNames.thisPlayer.myGold < statBuffCost) {
                        buyingStuffDesc.setText(notEnoughGold);
                    } else if (EnterNames.thisPlayer.strengthValue == 15) {
                        buyingStuffDesc.setText(cantTrain);
                    } else {
                        EnterNames.thisPlayer.strengthValue++;
                        EnterNames.thisPlayer.setAllStats();
                        EnterNames.thisPlayer.myGold -= statBuffCost;
                        buyingStuffDesc.setText(masterPurchased);
                    }
                }
                goldText = "Gold: " + EnterNames.thisPlayer.myGold;
                goldDisplay.setText(goldText);
            }
        });
        buyButtonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shopOrMaster) {
                    if (EnterNames.thisPlayer.myGold < shopConfig[3]) {
                        buyingStuffDesc.setText(notEnoughGold);
                    } else {
                        buyingStuffDesc.setText(purchased);
                        EnterNames.thisPlayer.myItems[shopConfig[2]]++;
                        EnterNames.thisPlayer.myGold -= shopConfig[3];
                    }
                } else {
                    if (EnterNames.thisPlayer.myGold < statBuffCost) {
                        buyingStuffDesc.setText(notEnoughGold);
                    } else if (EnterNames.thisPlayer.accuracyValue == 10) {
                        buyingStuffDesc.setText(cantTrain);
                    } else {
                        EnterNames.thisPlayer.accuracyValue++;
                        EnterNames.thisPlayer.setAllStats();
                        EnterNames.thisPlayer.myGold -= statBuffCost;
                        buyingStuffDesc.setText(masterPurchased);
                    }
                }
                goldText = "Gold: " + EnterNames.thisPlayer.myGold;
                goldDisplay.setText(goldText);
            }
        });
        buyButtonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shopOrMaster) {
                    if (EnterNames.thisPlayer.myGold < shopConfig[5]) {
                        buyingStuffDesc.setText(notEnoughGold);
                    } else {
                        buyingStuffDesc.setText(purchased);
                        EnterNames.thisPlayer.myItems[shopConfig[4]]++;
                        EnterNames.thisPlayer.myGold -= shopConfig[5];
                    }
                } else {
                    if (EnterNames.thisPlayer.myGold < statBuffCost) {
                        buyingStuffDesc.setText(notEnoughGold);
                    } else if (EnterNames.thisPlayer.defenseValue == 15) {
                        buyingStuffDesc.setText(cantTrain);
                    } else {
                        EnterNames.thisPlayer.defenseValue++;
                        EnterNames.thisPlayer.setAllStats();
                        EnterNames.thisPlayer.myGold -= statBuffCost;
                        buyingStuffDesc.setText(masterPurchased);
                    }
                }
                goldText = "Gold: " + EnterNames.thisPlayer.myGold;
                goldDisplay.setText(goldText);
            }
        });
        buyButtonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EnterNames.thisPlayer.myGold < statBuffCost) {
                    buyingStuffDesc.setText(notEnoughGold);
                } else if (EnterNames.thisPlayer.agilityValue == 10) {
                    buyingStuffDesc.setText(cantTrain);
                } else {
                    EnterNames.thisPlayer.agilityValue++;
                    EnterNames.thisPlayer.setAllStats();
                    EnterNames.thisPlayer.myGold -= statBuffCost;
                    buyingStuffDesc.setText(masterPurchased);
                }
                goldText = "Gold: " + EnterNames.thisPlayer.myGold;
                goldDisplay.setText(goldText);
            }
        });
        buyButtonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EnterNames.thisPlayer.myGold < statBuffCost) {
                    buyingStuffDesc.setText(notEnoughGold);
                } else if (EnterNames.thisPlayer.intelligenceValue == 10) {
                    buyingStuffDesc.setText(cantTrain);
                } else {
                    EnterNames.thisPlayer.intelligenceValue++;
                    EnterNames.thisPlayer.setAllStats();
                    EnterNames.thisPlayer.myGold -= statBuffCost;
                    buyingStuffDesc.setText(masterPurchased);
                }
                goldText = "Gold: " + EnterNames.thisPlayer.myGold;
                goldDisplay.setText(goldText);
            }
        });
        buyButtonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EnterNames.thisPlayer.myGold < statBuffCost) {
                    buyingStuffDesc.setText(notEnoughGold);
                } else if (EnterNames.thisPlayer.magicValue == 15) {
                    buyingStuffDesc.setText(cantTrain);
                } else {
                    EnterNames.thisPlayer.magicValue++;
                    EnterNames.thisPlayer.setAllStats();
                    EnterNames.thisPlayer.myGold -= statBuffCost;
                    buyingStuffDesc.setText(masterPurchased);
                }
                goldText = "Gold: " + EnterNames.thisPlayer.myGold;
                goldDisplay.setText(goldText);
            }
        });
        buyButtonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EnterNames.thisPlayer.myGold < statBuffCost) {
                    buyingStuffDesc.setText(notEnoughGold);
                } else if (EnterNames.thisPlayer.luckValue == 10) {
                    buyingStuffDesc.setText(cantTrain);
                } else {
                    EnterNames.thisPlayer.luckValue++;
                    EnterNames.thisPlayer.setAllStats();
                    EnterNames.thisPlayer.myGold -= statBuffCost;
                    buyingStuffDesc.setText(masterPurchased);
                }
                goldText = "Gold: " + EnterNames.thisPlayer.myGold;
                goldDisplay.setText(goldText);
            }
        });
        backToTownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (shopOrMaster) {
            buyButtonFour.setVisibility(View.GONE);
            buyButtonFive.setVisibility(View.GONE);
            buyButtonSix.setVisibility(View.GONE);
            buyButtonSeven.setVisibility(View.GONE);
        } else {
            buyButtonFour.setVisibility(View.VISIBLE);
            buyButtonFive.setVisibility(View.VISIBLE);
            buyButtonSix.setVisibility(View.VISIBLE);
            buyButtonSeven.setVisibility(View.VISIBLE);
        }
    }

    private void configureShop() {
        byte potionNum = 0;
        byte potionOmitted = (byte) ((Math.random() * 4));
        for (byte i = 0; i < shopConfig.length; i++) {
            if (i % 2 == 0) {
                if (potionNum == potionOmitted) {
                    potionNum++;
                }
                shopConfig[i] = potionNum;
            } else {
                double addOrSubtract = (Math.random() * 2);
                byte priceDifference = (byte) (Math.random() * 10);
                priceDifference *= 5;
                if (addOrSubtract < 1) {
                    priceDifference *= -1;
                }
                switch (potionNum) {
                    case 0:
                        shopConfig[i] = baseMagicCost + priceDifference;
                        break;
                    case 1:
                        shopConfig[i] = baseVitalityCost + priceDifference;
                        break;
                    case 2:
                        shopConfig[i] = baseBrainCost + priceDifference;
                        break;
                    case 3:
                        shopConfig[i] = baseGreedCost + priceDifference;
                        break;
                }
                potionNum++;
            }
        }
    }

    private void setMastersHouse() {
        itemOneDesc.setText(strength);
        itemTwoDesc.setText(accuracy);
        itemThreeDesc.setText(defense);
        itemFourDesc.setText(agility);
        itemFiveDesc.setText(intelligence);
        itemSixDesc.setText(magic);
        itemSevenDesc.setText(luck);
        String masterBuy = buy + statBuffCost;
        Button o = findViewById(R.id.shopBuyButtonOne);
        o.setText(masterBuy);
        Button tw = findViewById(R.id.shopBuyButtonTwo);
        tw.setText(masterBuy);
        Button th = findViewById(R.id.shopBuyButtonThree);
        th.setText(masterBuy);
        Button fo = findViewById(R.id.shopBuyButtonFour);
        fo.setText(masterBuy);
        Button fi = findViewById(R.id.shopBuyButtonFive);
        fi.setText(masterBuy);
        Button si = findViewById(R.id.shopBuyButtonSix);
        si.setText(masterBuy);
        Button se = findViewById(R.id.shopBuyButtonSeven);
        se.setText(masterBuy);
    }

    @Override
    public void onBackPressed() {}
}
