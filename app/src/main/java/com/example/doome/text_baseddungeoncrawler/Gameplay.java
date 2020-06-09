package com.example.doome.text_baseddungeoncrawler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class Gameplay extends AppCompatActivity {
    /**
     * Boolean to keep track of whether this class has been initiated. If so, the onCreate method
     * only serves to update the game based on a player's spell usage
     */
    public static boolean hasBeenInitiated = false;
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
    public static final String attack = "a";
    /**
     * The final string to compare the userInput to run.
     */
    public static final String run = "r";
    /**
     * The final string to compare the userInput to progress.
     */
    public static final String progress = "p";
    /**
     * The final string to compare the userInput to look.
     */
    public static final String look = "l";
    /**
     * The final string to compare the userInput to heal.
     */
    public static final String heal = "h";
    /**
     * The final string to compare the userInput to spell casting.
     */
    public static final String spell = "s";
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
    public static String cantInBattle = "You tried to, but the enemy insisted you stay!";
    /**
     * The message displayed to the user when they attack a monster.
     */
    public static String displayHit = "You use " + EnterNames.thisPlayer.weaponName + " and it hits! for ";
    /**
     * The message displayed to the user when they attempt to attack, but miss
     */
    public static String displayMiss = "You use your " + EnterNames.thisPlayer.weaponName + ", but you miss. ";
    /**
     * The message displayed when an enemy's attack hits the player.
     */
    public static String displayEnemyHit = thisRoom.numberOne.name + " uses " +
            thisRoom.numberOne.weaponName + " and it hits! for ";
    /**
     * The message displayed when an enemy's attack misses the player.
     */
    public static String displayEnemyMiss = thisRoom.numberOne.name + "uses " +
            thisRoom.numberOne.weaponName + ", but they miss.";
    /**
     *
     */
    public static char selectedDifficulty1 = DifficultySelection.selectedDifficulty;
    /**
     * The message displayed to the user when they win and end a battle.
     * To be added onto an attack meesage, as this must be displayed before the battle could end.
     */
    public static String victoryMessage = "You won! " + thisRoom.numberOne.name +
            " dropped " + thisRoom.numberOne.pointValue + " points. Now you have " +
            EnterNames.thisPlayer.myPoints + " points!";
    /**
     * The message displayed when the user is defeated.
     */
    public static String darkSouls = "You Died. Final Score: " + EnterNames.thisPlayer.myPoints + "\nPress enter to see how you did. ";
    /**
     * The message displayed when the user doesn't have enough points to run or heal.
     */
    public static final String insufficientPoints = "You do not have enough points to do that. ";
    /**
     * The message displayed when the user runs from a battle successfully.
     */
    public static final String ranAway = "You have ran away successfully. -100 points. ";
    /**
     * The message displayed when the user heals successfully.
     */
    public static final String healed = "You drink some soy milk. You recovered 12 HP! -50 points. ";
    /**
     * The message displayed when the user tries to attack nothing.
     */
    public static final String attackEmptyRoom = "You swing wildly, and you hit! Oh wait, that was just the floor. No one is here with you, but they'd be quite concerned if they were. ";
    /**
     * The message displayed when the player tries to run from nothing.
     */
    public static final String runEmptyRoom = "You go to run, but look around for the enemy to avoid and find nothing. You realize your folly and return where you were standing. ";
    /**
     * The message displayed when the user cannot search a room.
     */
    public static final String unsearchable = "You can tell everything about this room with a single glance. No need to search for secrets. ";
    /**
     * The message displayed when the user finds a attack boost secret.
     */
    public static final String foundAttackUp = "You found a pinkish-purple potion which increases muscular efficiency. Attack Power +1! ";
    /**
     * The message displayed when the user finds a defense boost secret.
     */
    public static final String foundDefenseUp = "You found some armor. Stylish! Max HP +2! ";
    /**
     * The message displayed when the user finds a point boost secret.
     */
    public static final String foundNewSpell = "You found an ancient book... read it? ";
    /**
     * The message for the first kind of bad secret
     */
    //public static final String
    /**
     * The message displayed when the user fails to find a secret.
     */
    public static final String noSecretFound = "You looked everywhere, but were unable to find anything... ";
    /**
     * The message displayed when the user has already searched a room.
     */
    public static final String alreadySearched = "You've already searched this room: it would be unproductive to search it again. ";
    /**
     * The string which will be used by the UI to display game text.
     * Is changed and redisplayed throughout play.
     */
    public static String consoleOutput = "";
    /**
     * The message displayed when a user progresses and enters battle with an enemy.
     */
    public static String startBattle = " A " + thisRoom.numberOne.name + " appears in this room! Prepare for battle! ";
    /**
     * The message displayed when the player enters battle with a miniboss.
     */
    public static String strongStartBattle = " A miniboss appears, it's a " + thisRoom.numberOne.name + "! Prepare for battle! ";
    /**
     * The add-on message displayed to the user when the room can be searched.
     */
    public static final String canBeSearched = " You find a hidden opening in the wall here! What could it be? ";
    /**
     * The message displayed when the player enters an empty room.
     */
    public static String emptyRoom = "You have entered the next room, room " + liveRoomCount + ". ";
    /**
     * The message displayed to the user when either they or the enemy lands a critical hit.
     */
    public static final String criticalHit = "Critical hit! ";
    /**
     * the message added on to the end when the player looks for and finds a secret
     */
    public static final String nothingElse = "It seems there is nothing else in this room. ";
    /**
     * the message displayed when the player tries to do something that is unhelpful.
     */
    public static final String invalidCommand = "You can't do that right now.";
    /**
     * the message below gameInfo that tells you that you have used your magic turn
     */
    public static final String yesMagicTurn = "Magic Turn: Yes ";
    /**
     * the message below gameInfo that tells you that you have NOT used your magic turn
     */
    public static final String noMagicTurn = "Magic Turn: No ";
    /**
     * the message displayed when you try to use a spell, but you have already used your magic turn.
     */
    public static final String doNotHaveMagicTurn = "You have already used your magic turn! ";
    /**
     * The message displayed when you try to use a spell, but you have no magic points
     */
    public static final String notEnoughMP = "You don't have enough MP to cast that spell!";
    /**
     * The message displayed when you try to go to the spell selection screen, but you have no MP
     */
    public static final String noMP = "You have no MP!";
    /**
     * the message displayed when you successfully cast a spell
     */
    public static String castSuccess = "You cast...a spell? ";

    TextView hud;
    TextView hud2;
    TextView hud3;
    TextView gameInfo;
    TextView magicTurnIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);
        if (!hasBeenInitiated) {
            Spell.thisPlayer = EnterNames.thisPlayer;
            setRoomCount();
            displayHit = "You use " + EnterNames.thisPlayer.weaponName + " and it hits! for ";
            displayMiss = "You use your " + EnterNames.thisPlayer.weaponName + ", but you miss. ";
            darkSouls = "You Died. Final Score: " + EnterNames.thisPlayer.myPoints + "\nPress enter to see how you did. ";
            victoryMessage = "You won! " + thisRoom.numberOne.name +
                    " dropped " + thisRoom.numberOne.pointValue + " points. Now you have " +
                    EnterNames.thisPlayer.myPoints + " points!";
            consoleOutput = "Welcome, " + EnterNames.thisPlayer.name + "! You are in an empty room, too small to be searched. If it could be searched, you could Look. To enter the next room, press Progress.";
            configureNextButton();
            hud = (TextView) findViewById(R.id.hudLine1);
            hud2 = (TextView) findViewById(R.id.hudLine2);
            hud3 = (TextView) findViewById(R.id.hudLine3);
            gameInfo = (TextView) findViewById(R.id.gameInfo);
            magicTurnIndicator = (TextView) findViewById(R.id.magicTurnIndicator);
            hasBeenInitiated = true;
        }
        setGameInfo();
        setHud();
    }
    private void configureNextButton() {
        Button progressButton = (Button) findViewById(R.id.progressButton);
        Button attackButton = (Button) findViewById(R.id.attackButton);
        Button spellButton = (Button) findViewById(R.id.spellButton);
        Button lookButton = (Button) findViewById(R.id.lookButton);
        Button healButton = (Button) findViewById(R.id.healButton);
        Button runButton = (Button) findViewById(R.id.runButton);
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
    }

    /**
     * sets consoleOutput appropriately depending on what the user inputs.
     * @param action the passed command from the user to
     */
    public void changeOutput (String action) {
        if (isBattling) {
            if (attackTurnAdvantage) {
                myBattleStatus(action);
            }
            if (!attackTurnAdvantage) {
                enemyBattleStatus();
                attackTurnAdvantage = true;
                magicTurnAdvantage = true;
            }
        } else {
            movementStatus(action);
            attackTurnAdvantage = true;
            magicTurnAdvantage = true;
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
                    //consoleOutput += darkSouls;
                }
            } else {
                consoleOutput += displayEnemyMiss;
            }
        }
    }
    public void movementStatus(String action) {
        if (action.equals(progress)) {
            //finished game
            if (liveRoomCount == roomCount && !isBattling) {
                startActivity(new Intent(Gameplay.this, FinishedGame.class));
            }
            //general procedure for preparing next room
            if (liveRoomCount < roomCount) {
                thisRoom = new Room();
                liveRoomCount++;
                emptyRoom = "You have entered the next room, room " + liveRoomCount + ".";
                //consoleOutput = emptyRoom;
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
        String magicTurn;
        if (magicTurnAdvantage && isBattling) {
            magicTurn = yesMagicTurn;
        } else if (isBattling) {
            magicTurn = noMagicTurn;
        } else {
            magicTurn = "";
        }
        if (thisRoom.hasEnemy) {
            magicTurn += thisRoom.numberOne.liveHP + "/" + thisRoom.numberOne.hp;
        }
        magicTurnIndicator.setText(magicTurn);
    }
    public static void updateCastSuccess(byte spellIndex) {
        castSuccess = "You cast " + EnterNames.thisPlayer.spells[spellIndex].name + "! ";
    }
    public static void attack() {
        int dealtDamage = EnterNames.thisPlayer.determineHit(thisRoom.numberOne);
        if (dealtDamage != 0) {
            consoleOutput = displayHit + dealtDamage + ". ";
            if (dealtDamage > EnterNames.thisPlayer.attackPower) {
                consoleOutput += criticalHit;
            }
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
            } else {
                attackTurnAdvantage = false;
                magicTurnAdvantage = false;
            }
        } else {
            consoleOutput = displayMiss;
            attackTurnAdvantage = false;
        }
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
                    consoleOutput = noSecretFound;
                } else if (foundSecret == 1) {
                    consoleOutput = foundAttackUp + nothingElse;
                    EnterNames.thisPlayer.attackPower++;
                } else if (foundSecret == 2) {
                    consoleOutput = foundDefenseUp + nothingElse;
                    EnterNames.thisPlayer.hp += 2;
                    EnterNames.thisPlayer.liveHP += 2;
                } else if (foundSecret == 3) {
                    consoleOutput = foundNewSpell + nothingElse;
                    EnterNames.thisPlayer.myPoints += 300;
                } else if (foundSecret == 4) {
                    //consoleOutput =
                } else if (foundSecret == 5) {

                } else {

                }
                thisRoom.roomSearched = true;
                secretsFoundCounter++;
                setHud();
            } else {
                consoleOutput = alreadySearched;
            }
        }  else {
            consoleOutput = unsearchable;
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
    public static void trulyCastSpell(byte spellIndex) {
        if (EnterNames.thisPlayer.spells[spellIndex].name != "empty spell") {
            if (EnterNames.thisPlayer.spells[spellIndex].mpCost > EnterNames.thisPlayer.liveMP) {
                consoleOutput = notEnoughMP;
            } else {
                EnterNames.thisPlayer.spells[spellIndex].spellCast(EnterNames.thisPlayer, thisRoom.numberOne);
                updateCastSuccess(spellIndex);
                consoleOutput = castSuccess + EnterNames.thisPlayer.spells[spellIndex].getExtraText();
                if (isBattling) {
                    magicTurnAdvantage = false;
                }
            }
        }
    }
    public void setRoomCount() {
        byte variableRoomCount = (byte) Math.round(10 * Math.random());
        byte baseRoomCount = (byte) 15;
        roomCount = (byte) (baseRoomCount + variableRoomCount);
    }

}