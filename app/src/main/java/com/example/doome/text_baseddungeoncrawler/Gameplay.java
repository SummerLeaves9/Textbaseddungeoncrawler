package com.example.doome.text_baseddungeoncrawler;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import org.w3c.dom.Text;

public class Gameplay extends AppCompatActivity {
    /**
     * Boolean to keep track of whether this class has been initiated. If so, the onCreate method
     * only serves to update the game based on a player's spell usage
     */
    public boolean hasBeenInitiated = false;
    /**
     * This variable keeps count of how many rooms have been traversed.
     */
    public static byte liveRoomCount = 0;
    /**
     * The amount of rooms which will be used for this game.
     * Set by the user's selection between 15, 30, or 45.
     */
    public byte roomCount = 0;
    /**
     * The boolean to track when the user is in battle
     */
    public static boolean isBattling = false;
    /**
     * The boolean that determines if the user has used their attack turn.
     */
    public static boolean attackTurnAdvantage = true;
    /**
     * The boolean that determines if the user has used their magic turn.
     */
    public static boolean magicTurnAdvantage = true;
    /**
     * The boolean that determines whether the user has enabled ultimate block this turn.
     */
    public static boolean hasBlocked = false;
    /**
     * The boolean that stores whether the player has an active Sphericon on the battlefield
     */
    public static boolean hasSphericon = false;
    /**
     * The int that stores how many Sphericon charges are loaded up
     */
    public static int sphericonCharge = 0;
    /**
     * For spells that have effects over one or more turns, this counter keeps track of turns when
     * the effect is active. If the counter reaches 0, the effect ends.
     */
    public static byte effectCounter = 0;
    /**
     * The final string to compare the userInput to attack.
     */
    public final String attack = "a";
    /**
     * The final string to compare the userInput to run.
     */
    public final String run = "r";
    /**
     * The final string to compare the userInput to progress.
     */
    public final String progress = "p";
    /**
     * The final string to compare the userInput to look.
     */
    public final String look = "l";
    /**
     * The final string to compare the userInput to heal.
     */
    public final String heal = "h";
    /**
     * The final string to compare the userInput to spell casting.
     */
    public final String spell = "s";
    /**
     * The final string which executes a spell
     */
    public final String cast = "c";
    /**
     * A counter used to show the player how many enemies they've defeated by the end of the game.
     */
    public static int enemiesDefeatedCounter = 0;
    /**
     * A counter used to show the player how many secrets they've found throughout the adventure.
     */
    public static int secretsFoundCounter = 0;
    /**
     * The room the player is currently in.
     */
    public static Room thisRoom = new Room();
    /**
     * The message displayed to the user upon trying an illegal action while in battle.
     */
    public String cantInBattle = "You tried to, but the enemy insisted you stay!";
    /**
     * The message displayed to the user when they attack a monster.
     */
    public String displayHit = "You use " + EnterNames.thisPlayer.weaponName + " and it hits! for ";
    /**
     * The message displayed to the user when they attempt to attack, but miss
     */
    public static String displayMiss = "You use your " + EnterNames.thisPlayer.weaponName + ", but you miss. ";
    /**
     * The message displayed when an enemy's attack hits the player.
     */
    public String displayEnemyHit = thisRoom.numberOne.name + " uses " +
            thisRoom.numberOne.weaponName + " and it hits! for ";
    /**
     * The message displayed when an enemy's attack misses the player.
     */
    public String displayEnemyMiss = thisRoom.numberOne.name + "uses " +
            thisRoom.numberOne.weaponName + ", but they miss.";
    /**
     * The message displayed to the user when they win and end a battle.
     * To be added onto an attack meesage, as this must be displayed before the battle could end.
     */
    public static String victoryMessage = "You won! " + thisRoom.numberOne.name +
            " dropped " + thisRoom.numberOne.pointValue + " points. Now you have " +
            EnterNames.thisPlayer.myPoints + " points!";
    /**
     * The message displayed when the user doesn't have enough points to run or heal.
     */
    public final String insufficientPoints = "You do not have enough points to do that. ";
    /**
     * The message displayed when the user runs from a battle successfully.
     */
    public final String ranAway = "You have ran away successfully. -100 points. ";
    /**
     * The message displayed when the user heals successfully.
     */
    public final String healed = "You drink some soy milk. You recovered 12 HP! -50 points. ";
    /**
     * The message displayed when the user tries to attack nothing.
     */
    public final String attackEmptyRoom = "You swing wildly, and you hit! Oh wait, that " +
            "was just the floor. No one is here with you, but they'd be quite concerned if they " +
            "were. ";
    /**
     * The message displayed when the player tries to run from nothing.
     */
    public final String runEmptyRoom = "You go to run, but look around for the enemy to " +
            "avoid and find nothing. You realize your folly and return where you were standing. ";
    /**
     * The message displayed when the user cannot search a room.
     */
    public final String unsearchable = "You can tell everything about this room with a " +
            "single glance. No need to search for secrets. ";
    /**
     * The first way of entry to a secret on finding one.
     */
    public final String secretIntro1 = "You found a passageway behind some loose bricks, and you " +
            "enter. ";
    /**
     * The second way of entry into a secret on finding one.
     */
    public final String secretIntro2 = "While walking around, you notice a patch of bricks " +
            "sounds hollow when you step on them. You break some away and find an underground " +
            "room! ";
    /**
     * The third way of entry into a secret on finding one.
     */
    public final String secretIntro3 = "You run your hand along the wall, and find a part where " +
            "your hand passes right through! You walk through the illusory wall. ";
    /**
     * The message displayed when the user finds a attack boost secret.
     */
    public final String foundAttackUp = "In the room you find a pink-purple potion which " +
            "increases muscular efficiency. Strength +1! ";
    /**
     * The message displayed when the user finds a defense boost secret.
     */
    public final String foundDefenseUp = "Lying on the floor you find a piece of armor. Stylish! " +
            "Defense +1. ";
    /**
     * The message displayed when the user finds a point boost secret.
     */
    public final String foundNewSpell = "You found an ancient book... read it? ";
    /**
     * The message for the first kind of bad secret
     */
    public final String tripwire = "On your second step, you trip on a wire and fall forward. ";
    /**
     * The message for evading the tripwire trap
     */
    public final String tripwireSuccess = "Thanks to your Agility, you're able to roll over and " +
            "kip up before a spiky ball drops on you! ";
    /**
     * The message for getting hit by the tripwire trap
     */
    public final String tripwireFail = "You exert yourself to get up, but before you can you get " +
            "crushed by a spiky ball falling from the ceiling... -14 HP. ";
    /**
     * The message for the second kind of bad secret
     */
    public final String magicSapper = "You find a one foot-tall figure with a glowing green face " +
            "who's wearing an oversized robe. They lunge at you! ";
    /**
     * The message for fending off the shortcerer
     */
    public String magicSapperSuccess = "You try to block them with your " +
            EnterNames.thisPlayer.weaponName + ", and it works, thanks to your Accuracy!" +
            " You fling the shortcerer off, and they flee into a small hole in the corner of " +
            "the room.";
    /**
     * The message for getting your magic zapped from your body
     */
    public final String magicSapperFail = "You try to prevent their advance, but you mispredict " +
            "the trajectory and the shortcerer clings to you! Their hands on your shoulder makes " +
            "it go numb. You swipe them off, but it gets away before you can do anything else. " +
            "-8 MP. ";
    /**
     * The message for the third kind of bad secret
     */
    public final String scrollQuestion = "You enter a room, more bright and futuristic than the " +
            "endless torch-lit brick walls of this place. A fizzle of orange embers appears in " +
            "the air before you, revealing a scroll and a quill. You hear some clanks as the " +
            "entrance gets closed behind you.";
    /**
     * The message for beating the puzzle room
     */
    public final String scrollQuestionSuccess = "You read the scroll, and find you know the " +
            "answer to the question, thanks to your Intelligence! You write it down and the " +
            "scroll casts itself out of existence. The entrance reopens itself. ";
    /**
     * The message for losing the puzzle room
     */
    public final String scrollQuestionFail = "You have no idea what the scroll is talking about," +
            "so you jot down your best guess. The scroll disappears. The walls open and you're" +
            "pierced by an arrow! -13 HP.";
    /**
     * The message displayed when the user fails to find a secret.
     */
    public final String noSecretFound = "You looked everywhere, but were unable to find " +
            "anything... ";
    /**
     * The message displayed when the user has already searched a room.
     */
    public final String alreadySearched = "You've already searched this room: it would be " +
            "unproductive to search it again. ";
    /**
     * The string which will be used by the UI to display game text.
     * Is changed and redisplayed throughout play.
     */
    public static String consoleOutput = "";
    /**
     * The message displayed when a user progresses and enters battle with an enemy.
     */
    public String startBattle = " A " + thisRoom.numberOne.name + " appears in this room! " +
            "Prepare for battle! ";
    /**
     * The message displayed when the player enters battle with a miniboss.
     */
    public String strongStartBattle = " A miniboss appears, it's a " +
            thisRoom.numberOne.name + "! Prepare for battle! ";
    /**
     * The add-on message displayed to the user when the room can be searched.
     */
    public static final String canBeSearched = " You find a hidden opening in the wall here! " +
            "What could it be? ";
    /**
     * The message displayed when the player enters an empty room.
     */
    public String emptyRoom = "You have entered the next room, room " + liveRoomCount + ". ";
    /**
     * The message displayed to the user when either they or the enemy lands a critical hit.
     */
    public final String criticalHit = "Critical hit! ";
    /**
     * the message added on to the end when the player looks for and finds a secret
     */
    public final String nothingElse = "It seems there is nothing else in this room. ";
    /**
     * the message displayed when the player tries to do something that is unhelpful.
     */
    public final String invalidCommand = "You can't do that right now.";
    /**
     * the message below gameInfo that tells you that you have used your magic turn
     */
    public final String yesMagicTurn = "Magic Turn: Yes ";
    /**
     * the message below gameInfo that tells you that you have NOT used your magic turn
     */
    public final String noMagicTurn = "Magic Turn: No ";
    /**
     * the message displayed when you try to use a spell, but you have already used your magic turn.
     */
    public final String doNotHaveMagicTurn = "You have already used your magic turn! ";
    /**
     * The message displayed when you try to use a spell, but you have no magic points
     */
    public final String notEnoughMP = "You don't have enough MP to cast that spell!";
    /**
     * The message displayed when you try to go to the spell selection screen, but you have no MP
     */
    public final String noMP = "You have no MP!";
    /**
     * the message displayed when you successfully cast a spell
     */
    public String castSuccess = "You cast...a spell? ";
    /**
     * the message displayed that shows what spell is selected before the users presses "cast".
     */
    public String newLastSpellText = "Selected Spell: ";
    /**
     * the message displayed when a spell is cast in combat, but it is only meant for exploration
     */
    public final String explorationOnly = "This spell can't be used in combat!";
    /**
     * the message displayed when a spell is cast while exploring, but is only meant for combat
     */
    public final String combatOnly = "This spell is only meant for combat.";
    /**
     * The byte that is changed in SpellSelection to indicate which spell in which slot to use
     */
    public static byte spellNum = 0;

    TextView hud;
    TextView hud2;
    TextView hud3;
    TextView gameInfo;
    TextView magicTurnIndicator;
    TextView enemyName;
    TextView enemyHealthIndicator;
    TextView lastSpellUsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);
        Spell.thisPlayer = EnterNames.thisPlayer;
        setRoomCount();
        displayHit = "You use " + EnterNames.thisPlayer.weaponName + " and it hits! for ";
        displayMiss = "You use your " + EnterNames.thisPlayer.weaponName + ", but you miss. ";
        victoryMessage = "You won! " + thisRoom.numberOne.name +
                " dropped " + thisRoom.numberOne.pointValue + " points. Now you have " +
                EnterNames.thisPlayer.myPoints + " points!";
        magicSapperSuccess = "You try to block them with your " +
                EnterNames.thisPlayer.weaponName + ", and it works! You fling the shortcerer off, and" +
                "they flee into a small hole in the corner of the room.";
        consoleOutput = "Welcome, " + EnterNames.thisPlayer.name + "! You are in an empty room, too small to be searched. If it could be searched, you could Look. To enter the next room, press Progress.";
        //Combat TextView's
        hud = (TextView) findViewById(R.id.hudLine1);
        hud2 = (TextView) findViewById(R.id.hudLine2);
        hud3 = (TextView) findViewById(R.id.hudLine3);
        gameInfo = (TextView) findViewById(R.id.gameInfo);
        magicTurnIndicator = (TextView) findViewById(R.id.magicTurnIndicator);
        enemyName = (TextView) findViewById(R.id.enemyName);
        enemyHealthIndicator = (TextView) findViewById(R.id.enemyHealthIndicator);
        lastSpellUsed = (TextView) findViewById(R.id.lastSpellUsed);
        configureNextButton();
        setGameInfo();
        setHud();
    }

    private void configureNextButton() {
        //Combat Buttons
        Button progressButton = (Button) findViewById(R.id.progressButton);
        Button attackButton = (Button) findViewById(R.id.attackButton);
        Button spellButton = (Button) findViewById(R.id.spellButton);
        Button lookButton = (Button) findViewById(R.id.lookButton);
        Button healButton = (Button) findViewById(R.id.healButton);
        Button runButton = (Button) findViewById(R.id.runButton);
        Button castButton = (Button) findViewById(R.id.castButton);
        //Configuring Combat Buttons
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOutput("p");
            }
        });
        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOutput("a");
            }
        });
        spellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOutput("s");
            }
        });
        lookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOutput("l");
            }
        });
        healButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOutput("h");
            }
        });
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOutput("r");
            }
        });
        castButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOutput("c");
            }
        });
    }

    /**
     * sets consoleOutput appropriately depending on what the user inputs.
     * @param action the passed command from the user to
     */
    public void changeOutput (String action) {
        if (gameInfo.getTextSize() != 22) {
            gameInfo.setTextSize(22);
        }
        if (isBattling) {
            if (attackTurnAdvantage) {
                myBattleStatus(action);
            }
            if (!attackTurnAdvantage && !magicTurnAdvantage) {
                enemyBattleStatus();
            }
        } else {
            movementStatus(action);
        }
        setGameInfo();
        setHud();
    }

    public void myBattleStatus (String action) {
        if (action.equals(look) || action.equals(progress)) {
            consoleOutput = cantInBattle;
        } else if (action.equals(attack)) {
            attack();
        } else if (action.equals(run)) {
            run();
        } else if (action.equals(heal)) {
            heal();
        } else if (action.equals(spell)) {
            spell();
        } else if (action.equals(cast)) {
            trulyCastSpell(spellNum);
        } else {
            consoleOutput = invalidCommand;
        }
    }

    public void enemyBattleStatus() {
        int damageDealt = thisRoom.numberOne.determineHit(EnterNames.thisPlayer);
        if (isBattling) {
            if (damageDealt != 0) {
                consoleOutput += displayEnemyHit + damageDealt + ". ";
                if (damageDealt > thisRoom.numberOne.attackPower) {
                    consoleOutput += criticalHit;
                }
                if (EnterNames.thisPlayer.liveHP <= 0) {
                    EnterNames.thisPlayer.liveHP = 0;
                    isBattling = false;
                    startActivity(new Intent(Gameplay.this, GameOverScreen.class));
                }
            } else {
                consoleOutput += displayEnemyMiss;
            }
            attackTurnAdvantage = true;
            magicTurnAdvantage = true;
        }
    }

    public void movementStatus(String action) {
        if (action.equals(progress)) {
            //finished game
            if (liveRoomCount == roomCount) {
                startActivity(new Intent(Gameplay.this, FinishedGame.class));
            }
            //general procedure for preparing next room
            if (liveRoomCount < roomCount) {
                thisRoom = new Room();
                liveRoomCount++;
                emptyRoom = "You have entered the next room, room " + liveRoomCount + ".";
                consoleOutput = emptyRoom;
                if (thisRoom.hasEnemy) {
                    isBattling = true;
                    displayEnemyHit = thisRoom.numberOne.name + " uses " +
                            thisRoom.numberOne.weaponName + " and it hits! for ";
                    displayEnemyMiss = thisRoom.numberOne.name + " uses " +
                            thisRoom.numberOne.weaponName + ", but you dodge it.";
                    if (!thisRoom.numberOne.isStrong) {
                        startBattle = " A " + thisRoom.numberOne.name + " appears in the room! Prepare for battle!";
                        consoleOutput = startBattle;
                    } else {
                        strongStartBattle = " A miniboss appears, it's a " + thisRoom.numberOne.name + "! Prepare for battle!";
                        consoleOutput = strongStartBattle;
                    }
                } else {
                    consoleOutput = emptyRoom;
                    if (thisRoom.disSearchable) {
                        consoleOutput += canBeSearched;
                    }
                }
            }
        } else if (action.equals(look)) {
            look();
        } else if (action.equals(heal)) {
            heal();
        } else if (action.equals(spell)) {
            spell();
        } else if (action.equals(attack)){
            consoleOutput = attackEmptyRoom;
        } else if (action.equals(run)) {
            consoleOutput = runEmptyRoom;
        } else if (action.equals(cast)) {
            trulyCastSpell(spellNum);
        } else {
            consoleOutput = invalidCommand;
        }
    }

    public void setGameInfo() {
        gameInfo.setText(consoleOutput);
    }

    public void setHud() {
        String newHudText = "HP: " + EnterNames.thisPlayer.liveHP + "/" +
                EnterNames.thisPlayer.hp;
        String newHud2Text = "MP: " + EnterNames.thisPlayer.liveMP + "/" +
                EnterNames.thisPlayer.mp;
        String newHud3Text = "Points: " + EnterNames.thisPlayer.myPoints;
        hud.setText(newHudText);
        hud2.setText(newHud2Text);
        hud3.setText(newHud3Text);
        String magicTurn = "";
        String enemyNameText = "";
        String enemyHealth = "";
        newLastSpellText = "";
        if (magicTurnAdvantage && isBattling) {
            magicTurn = yesMagicTurn;
        } else if (isBattling) {
            magicTurn = noMagicTurn;
        }
        if (isBattling) {
            enemyNameText = thisRoom.numberOne.name;
            enemyHealth = thisRoom.numberOne.liveHP + "/" + thisRoom.numberOne.hp;
            //newLastSpellText = "Last: " + EnterNames.thisPlayer.spells[spellNum].name;
        }
        magicTurnIndicator.setText(magicTurn);
        enemyName.setText(enemyNameText);
        enemyHealthIndicator.setText(enemyHealth);
        lastSpellUsed.setText(newLastSpellText);
    }

    public void checkIfEnemyDefeated() {
        if (thisRoom.numberOne.liveHP <= 0) {
            EnterNames.thisPlayer.myPoints += thisRoom.numberOne.pointValue;
            victoryMessage = "You won! " + thisRoom.numberOne.name +
                    " dropped " + thisRoom.numberOne.pointValue + " points. Now you have " +
                    EnterNames.thisPlayer.myPoints + " points!";
            consoleOutput += victoryMessage;
            isBattling = false;
            enemiesDefeatedCounter++;
            if (thisRoom.disSearchable) {
                consoleOutput += canBeSearched;
            }
            attackTurnAdvantage = true;
            magicTurnAdvantage = true;
        }
    }

    public void attack() {
        int dealtDamage = EnterNames.thisPlayer.determineHit(thisRoom.numberOne);
        if (dealtDamage != 0) {
            consoleOutput = displayHit + dealtDamage + ". ";
            if (dealtDamage > EnterNames.thisPlayer.attackPower) {
                consoleOutput += criticalHit;
            }
        } else {
            consoleOutput = displayMiss;
        }
        attackTurnAdvantage = false;
        magicTurnAdvantage = false;
        checkIfEnemyDefeated();
    }

    public void heal() {
        if (EnterNames.thisPlayer.myPoints < 50) {
            consoleOutput = insufficientPoints;
        } else {
            EnterNames.thisPlayer.myPoints -= 50;
            consoleOutput = healed;
            if (EnterNames.thisPlayer.liveHP <= EnterNames.thisPlayer.hp - 12) {
                EnterNames.thisPlayer.liveHP += 12;
            } else {
                EnterNames.thisPlayer.liveHP = EnterNames.thisPlayer.hp;
            }
            attackTurnAdvantage = false;
            magicTurnAdvantage = false;
        }
    }

    public void run() {
        if (EnterNames.thisPlayer.myPoints < 100) {
            consoleOutput = insufficientPoints;
        } else {
            EnterNames.thisPlayer.myPoints -= 100;
            consoleOutput = ranAway;
            isBattling = false;
            EnterNames.thisPlayer.liveHP = EnterNames.thisPlayer.hp;
            movementStatus(progress);
        }
    }

    public void look() {
        if (thisRoom.disSearchable) {
            if (!thisRoom.roomSearched) {
                int foundSecret = EnterNames.thisPlayer.foundSecret();
                if (foundSecret == 0) {
                    //secret was not found
                    consoleOutput = noSecretFound;
                } else {
                    //secret was found
                    secretsFoundCounter++;
                    //setting secret intro
                    double whichIntro = Math.random();
                    if (whichIntro < .333) {
                        consoleOutput = secretIntro1;
                    } else if (whichIntro < .666) {
                        consoleOutput = secretIntro2;
                    } else {
                        consoleOutput = secretIntro3;
                    }
                    //determining output for type of secret
                    gameInfo.setTextSize(16);
                    if (foundSecret == 1) {
                        consoleOutput += foundAttackUp + nothingElse;
                        EnterNames.thisPlayer.strengthValue++;
                        EnterNames.thisPlayer.setAttackPower();
                    } else if (foundSecret == 2) {
                        consoleOutput += foundDefenseUp + nothingElse;
                        EnterNames.thisPlayer.defenseValue++;
                        EnterNames.thisPlayer.setHp();
                    } else if (foundSecret == 3) {
                        consoleOutput += foundNewSpell + nothingElse;
                        EnterNames.thisPlayer.myPoints += 300;
                    } else if (foundSecret == 4) {
                        gameInfo.setTextSize(16);
                        consoleOutput += tripwire;
                        if (EnterNames.thisPlayer.agilityValue > 5) {
                            consoleOutput += tripwireSuccess;
                            determineNegativeSecretReward();
                        } else {
                            consoleOutput += tripwireFail;
                            EnterNames.thisPlayer.liveHP -= 14;
                            if (EnterNames.thisPlayer.liveHP <= 0) {
                                EnterNames.thisPlayer.liveHP = 0;
                                isBattling = false;
                                startActivity(new Intent(Gameplay.this, GameOverScreen.class));
                            } else {
                                determineNegativeSecretReward();
                            }
                        }
                    } else if (foundSecret == 5) {
                        consoleOutput += magicSapper;
                        if (EnterNames.thisPlayer.accuracyValue > 5) {
                            consoleOutput += magicSapperSuccess;
                        } else {
                            consoleOutput += magicSapperFail;
                            EnterNames.thisPlayer.liveMP -= 8;
                        }
                        determineNegativeSecretReward();
                    } else {
                        consoleOutput += scrollQuestion;
                        if (EnterNames.thisPlayer.intelligenceValue > 5) {
                            consoleOutput += scrollQuestionSuccess;
                            determineNegativeSecretReward();
                        } else {
                            consoleOutput += scrollQuestionFail;
                            EnterNames.thisPlayer.liveHP -= 13;
                            if (EnterNames.thisPlayer.liveHP <= 0) {
                                EnterNames.thisPlayer.liveHP = 0;
                                isBattling = false;
                                startActivity(new Intent(Gameplay.this, GameOverScreen.class));
                            } else {
                                determineNegativeSecretReward();
                            }
                        }
                    }
                }
                thisRoom.roomSearched = true;
                setHud();
            } else {
                consoleOutput = alreadySearched;
            }
        }  else {
            consoleOutput = unsearchable;
        }
    }

    public void determineNegativeSecretReward() {
        double whichSecret = Math.random();
        if (whichSecret < .333) {
            consoleOutput += foundAttackUp + nothingElse;
            EnterNames.thisPlayer.strengthValue++;
            EnterNames.thisPlayer.setAttackPower();
        } else if (whichSecret < .666) {
            consoleOutput += foundDefenseUp + nothingElse;
            EnterNames.thisPlayer.defenseValue++;
            EnterNames.thisPlayer.setHp();
        } else {
            consoleOutput += foundNewSpell + nothingElse;
            EnterNames.thisPlayer.myPoints += 300;
        }
    }

    public void spell() {
        if (!magicTurnAdvantage) {
            consoleOutput = doNotHaveMagicTurn;
        } else if (EnterNames.thisPlayer.liveMP == 0) {
            consoleOutput = noMP;
        } else {
            startActivity(new Intent(Gameplay.this, SpellSelection.class));
        }
    }

    public void trulyCastSpell(byte spellIndex) {
        if (!magicTurnAdvantage) {
            consoleOutput = noMagicTurn;
        } else if (EnterNames.thisPlayer.spells[spellIndex].mpCost > EnterNames.thisPlayer.liveMP) {
            consoleOutput = notEnoughMP;
        } else if (isBattling && !(EnterNames.thisPlayer.spells[spellIndex].isCombatSpell)) {
            consoleOutput = explorationOnly;
        } else if (!isBattling && !(EnterNames.thisPlayer.spells[spellIndex].isExplorationSpell)) {
            consoleOutput = combatOnly;
        } else {
            EnterNames.thisPlayer.spells[spellIndex].spellCast(EnterNames.thisPlayer, thisRoom.numberOne);
            updateCastSuccess(spellIndex);
            consoleOutput = castSuccess + EnterNames.thisPlayer.spells[spellIndex].getExtraText();
            if (isBattling) {
                magicTurnAdvantage = false;
                checkIfEnemyDefeated();
            }
        }
    }

    public void updateCastSuccess(byte spellIndex) {
        castSuccess = "You cast " + EnterNames.thisPlayer.spells[spellIndex].name + "...";
    }

    public void setRoomCount() {
        byte variableRoomCount = (byte) Math.round(10 * Math.random());
        byte baseRoomCount = (byte) 20;
        roomCount = (byte) (baseRoomCount + variableRoomCount);
    }

}