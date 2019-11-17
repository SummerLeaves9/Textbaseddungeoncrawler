package com.example.doome.text_baseddungeoncrawler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class Gameplay extends AppCompatActivity {
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
     * The boolean that determines if the user has used their turn.
     */
    public static boolean turnAdvantage = true;
    /**
     * The final string to compare the userInput to attack.
     */
    public static final String attack = "a";
    /**
     * The final string used to bypass the shake to attack
     */
    public static final String attackBypass = "ab";
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
     * The String which will store the user's desired action.
     * Is changed throughout play.
     */
    public static String userInput;
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
    public static String selectedDifficulty1 = DifficultySelection.selectedDifficulty;
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
     * The message displayed when the user puts in an invalid command.
     */
    public static final String invalidCommand = "Unlike real life, you can't do anything you want. Please enter an appropriate command. ";
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
    public static final String foundPoints = "You found some soy milk. Nutritious! +300 Points! ";
    /**
     * The message displayed when the user fails to find a secret.
     */
    public static final String noSecretFound = "You searched every nook and cranny, but were unable to find anything... There's nothing else to do in this room. ";
    /**
     * The message displayed when the user has already searched a room.
     */
    public static final String alreadySearched = "You've already searched this room: it would be unproductive to search it again. ";
    /**
     * The string which will be used by the UI to display game text.
     * Is changed and redisplayed throughout play.
     */
    public static String consoleOutput;
    /**
     * The message displayed when a user progresses and enters battle with an enemy.
     */
    public static String startBattle = " A " + thisRoom.numberOne.name + " appears in this room! Prepare for battle! ";
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

    EditText actionInput;
    static TextView hud;
    static TextView gameInfo;
    static String displayInfo = new String("Hp:" + EnterNames.thisPlayer.liveHP + "/" + EnterNames.thisPlayer.hp + " Points: " + EnterNames.thisPlayer.myPoints);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);
        setRoomCount();
        displayHit = "You use " + EnterNames.thisPlayer.weaponName + " and it hits! for ";
        displayMiss = "You use your " + EnterNames.thisPlayer.weaponName + ", but you miss. ";
        darkSouls = "You Died. Final Score: " + EnterNames.thisPlayer.myPoints + "\nPress enter to see how you did. ";
        victoryMessage = "You won! " + thisRoom.numberOne.name +
                " dropped " + thisRoom.numberOne.pointValue + " points. Now you have " +
                EnterNames.thisPlayer.myPoints + " points!";
        consoleOutput = "Welcome, " + EnterNames.thisPlayer.name + "! You are in an empty room, too small to be searched. If it could be searched, you could type, 'l'. To enter the next room, type 'p'.";
        displayInfo = new String("Hp: " + EnterNames.thisPlayer.liveHP + "/" + EnterNames.thisPlayer.hp + " Points: " + EnterNames.thisPlayer.myPoints);
        configureNextButton();
        actionInput = (EditText) findViewById(R.id.actionInput);
        hud = (TextView) findViewById(R.id.hud);
        gameInfo = (TextView) findViewById(R.id.gameInfo);
        setGameInfo();
        hud.setText(displayInfo);
    }
    private void configureNextButton() {
        Button progressButton = (Button) findViewById(R.id.submitButton);
        Button attackButton = (Button) findViewById(R.id.attackButton);
        Button spellButton = (Button) findViewById(R.id.spellButton);
        Button defendButton = (Button) findViewById(R.id.defendButton);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInput = actionInput.getText().toString();
                actionInput.setText("");
                if (liveRoomCount == roomCount && !isBattling) {
                    startActivity(new Intent(Gameplay.this, FinishedGame.class));
                }
                if (EnterNames.thisPlayer.liveHP <= 0) {
                    startActivity(new Intent(Gameplay.this, GameOverScreen.class));
                }
                changeOutput(userInput);
                setGameInfo();
            }
        });
    }

    /**
     * sets consoleOutput appropriately depending on what the user inputs.
     * @param action the passed command from the user to
     */
    public void changeOutput (String action) {
        if (isBattling) {
            if (turnAdvantage) {
                myBattleStatus(action);
            }
            if (!turnAdvantage) {
                enemyBattleStatus();
                turnAdvantage = true;
            }
        } else {
            movementStatus(action);
            turnAdvantage = true;
        }
        setGameInfo();
        setHud();
    }
    public void myBattleStatus (String action) {
        if (action.equals(look) || action.equals(progress)) {
            consoleOutput = cantInBattle;
        } else if (action.equals(attack)) {
            startActivity(new Intent(Gameplay.this, Shake.class));
            attack();
        } else if (action.equals(attackBypass)) {
            attack();
        } else if (action.equals(run)) {
            if (EnterNames.thisPlayer.myPoints < 100) {
                consoleOutput = insufficientPoints;
            } else {
                EnterNames.thisPlayer.myPoints -= 100;
                consoleOutput = ranAway;
                isBattling = false;
                EnterNames.thisPlayer.liveHP = EnterNames.thisPlayer.hp;
                movementStatus(progress);
            }
        } else if (action.equals(heal)) {
            heal();
        } else {
            consoleOutput = invalidCommand;
        }
    }
    public static void enemyBattleStatus() {
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
                    consoleOutput += darkSouls;
                }
            } else {
                consoleOutput += displayEnemyMiss;
            }
        }
    }
    public void movementStatus(String action) {
        if (action.equals(progress)) {
            if (liveRoomCount < roomCount) {
                thisRoom = new Room();
                liveRoomCount++;
                emptyRoom = "You have entered the next room, room " + liveRoomCount + ".";
                displayEnemyHit = thisRoom.numberOne.name + " uses " +
                        thisRoom.numberOne.weaponName + " and it hits! for ";
                displayEnemyMiss = thisRoom.numberOne.name + " uses " +
                        thisRoom.numberOne.weaponName + ", but they miss. Nice dodge!";
                consoleOutput = emptyRoom;
                if (!(thisRoom.numberOne.name.equals("null"))) {
                    startBattle = " A " + thisRoom.numberOne.name + " appears in the room! Prepare for battle!";
                    consoleOutput += startBattle;
                } else {
                    consoleOutput = emptyRoom;
                    if (thisRoom.disSearchable) {
                        consoleOutput += canBeSearched;
                    }
                }
            }
        } else if (action.equals(look)) {
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
                        consoleOutput = foundPoints + nothingElse;
                        EnterNames.thisPlayer.myPoints += 300;
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
        } else if (action.equals(heal)) {
            heal();
        } else {
            consoleOutput = invalidCommand;
        }
    }
    public static void setGameInfo() {
        gameInfo.setText(consoleOutput);
    }
    public static void setHud() {
        hud.setText("Hp: " + EnterNames.thisPlayer.liveHP + "/" + EnterNames.thisPlayer.hp + " Points: " + EnterNames.thisPlayer.myPoints);
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
                consoleOutput += Gameplay.victoryMessage;
                isBattling = false;
                enemiesDefeatedCounter++;
                if (thisRoom.disSearchable) {
                    consoleOutput += canBeSearched;
                }
                displayInfo = "Hp: " + EnterNames.thisPlayer.liveHP + "/" + EnterNames.thisPlayer.hp + " Points: " + EnterNames.thisPlayer.myPoints;
                turnAdvantage = true;
            } else {
                turnAdvantage = false;
            }
        } else {
            consoleOutput = displayMiss;
            turnAdvantage = false;
        }
    }
    public static void heal() {
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
            turnAdvantage = false;
        }
    }
    public void setRoomCount() {
        byte variableRoomCount = (byte) Math.round(10 * Math.random());
        byte baseRoomCount = (byte) 15;
        roomCount = (byte) (baseRoomCount + variableRoomCount);
    }
}