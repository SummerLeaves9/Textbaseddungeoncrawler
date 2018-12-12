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
    public static int liveRoomCount = 0;
    /**
     * The amount of rooms which will be used for this game.
     * Set by the user's selection between 15, 30, or 45.
     */
    public static int roomCount;
    /**
     * The amount of rooms if the player selects "easy".
     */
    public static final int easyRoomCount = 15;
    /**
     * The amount of rooms if the player selects "normal".
     */
    public static final int normalRoomCount = 30;
    /**
     * The amount of rooms if the player selects "hard".
     */
    public static final int hardRoomCount = 45;
    /**
     * Used to detect whether the player has chosen to randomize their stats.
     * Set to true if yes.
     */
    public static boolean isRandomized = false;
    /**
     * The boolean to track when the user is in battle
     */
    public static boolean isBattling = false;
    /**
     * The int that stores the player's strength stat before the player is initialized.
     */
    public static int playerStrength;
    /**
     * The int that stores the player's accuracy stat before the player is initialized.
     */
    public static int playerAccuracy;
    /**
     * The int that stores the player's defense stat before the player is initialized.
     */
    public static int playerDefense;
    /**
     * The int that stores the player's agility stat before the player is initialized.
     */
    public static int playerAgility;
    /**
     * The int that stores the player's intelligence stat before the player is initialized.
     */
    public static int playerIntelligence;
    /**
     * The int that stores the player's luck stat before the player is initialized.
     */
    public static int playerLuck;
    /**
     * The boolean that determines if the user has used their turn.
     */
    public static boolean turnAdvantage = true;
    /**
     * The String that stores the player's name before the player is initalized.
     */
    public static String playerName;
    /**
     * The String that stores the player's weapon name before the player is initalized.
     */
    public static String playerWeaponName;
    /**
     * The boolean that determines when the user leaves a room.
     */
    public boolean hasProgressed = false;
    /**
     * The final string to compare the userInput to attack.
     */
    public static final String attack = "attack";
    /**
     * The final string to compare the userInput to run.
     */
    public static final String run = "run";
    /**
     * The final string to compare the userInput to progress.
     */
    public static final String progress = "progress";
    /**
     * The final string to compare the userInput to look.
     */
    public static final String look = "look";
    /**
     * The final string to compare the userInput to heal.
     */
    public static final String heal = "heal";
    /**
     * A counter used to show the player how many enemies they've defeated by the end of the game.
     */
    public static int enemiesDefeatedCounter = 0;
    /**
     * A counter used to show the player how many secrets they've found throughout the adventure.
     */
    public static int secretsFoundCounter = 0;
    /**
     * The player.
     */
    public static Player thisPlayer = new Player("name", "weaponName");
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
    public static String displayHit = "You use " + thisPlayer.weaponName + " and it hits! for ";
    /**
     * The message displayed to the user when they attempt to attack, but miss
     */
    public static String displayMiss = "You use your " + thisPlayer.weaponName + ", but you miss. ";
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
     * The message displayed to the user when they win and end a battle.
     * To be added onto an attack meesage, as this must be displayed before the battle could end.
     */
    public static String victoryMessage = "You won! " + thisRoom.numberOne.name +
            " dropped " + thisRoom.numberOne.pointValue + " points. Now you have " +
            thisPlayer.myPoints + " points!";
    /**
     * The message displayed when the user is defeated.
     */
    public static final String darkSouls = "You Died. Final Score: " + thisPlayer.myPoints + "\nPlease close the app and try again.";
    /**
     * The message displayed when the user doesn't have enough points to run or heal.
     */
    public static final String insufficientPoints = "You do not have enough points to do that.";
    /**
     * The message displayed when the user runs from a battle successfully.
     */
    public static final String ranAway = "You have ran away successfully.";
    /**
     * The message displayed when the user heals successfully.
     */
    public static final String healed = "Your health has been fully restored.";
    /**
     * The message displayed when the user puts in an invalid command.
     */
    public static final String invalidCommand = "Unlike real life, you can't do literally anything. Please enter a valid command.";
    /**
     * The message displayed when the user cannot search a room.
     */
    public static final String unsearchable = "You can tell everything about this room with a single glance. No need to search for secrets.";
    /**
     * The message displayed when the user finds a attack boost secret.
     */
    public static final String foundAttackUp = "You have found a potion, which increases muscular efficiency. Attack Power +2!";
    /**
     * The message displayed when the user finds a defense boost secret.
     */
    public static final String foundDefenseUp = "You have found some new armor. It's still in good condition! Max HP +3!";
    /**
     * The message displayed when the user finds a point boost secret.
     */
    public static final String foundPoints = "You have found some gold! Surely this will fetch a nice price. +300 Points!";
    /**
     * The message displayed when the user fails to find a secret.
     */
    public static final String noSecretFound = "You searched every nook and crany, but were unable to find anything. :( ";
    /**
     * The message displayed when the user has already searched a room.
     */
    public static final String alreadySearched = "You've already searched this room, seems unproductive to search it again.";
    /**
     * The string which will be used by the UI to display game text.
     * Is changed and redisplayed throughout play.
     */
    public static String consoleOutput;
    /**
     * The message displayed when a user progresses and enters battle with an enemy.
     */
    public static String startBattle = " A " + thisRoom.numberOne.name + " appears in the room! Prepare for battle!";
    /**
     * The add-on message displayed to the user when the room can be searched.
     */
    public static final String canBeSearched = " This room looks pretty big, so it may be worth looking around.";
    /**
     * The message displayed when the player enters an empty room.
     */
    public static String emptyRoom = "You have entered the next room, room " + liveRoomCount + ".";

    EditText actionInput;
    static TextView healthDisplay;
    static TextView gameInfo;
    String displayInfo = new String("Hp:" + thisPlayer.liveHP + "/" + thisPlayer.hp + " Points: " + thisPlayer.myPoints);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        roomCount = DifficultySelection.gameRoomCount;
        playerName = EnterNames.enterName;
        playerWeaponName = EnterNames.enterWeaponName;
        playerStrength = StatSelection.statPlayerStrength;
        playerAccuracy = StatSelection.statPlayerAccuracy;
        playerDefense = StatSelection.statPlayerDefense;
        playerAgility = StatSelection.statPlayerAgility;
        playerIntelligence = StatSelection.statPlayerIntelligence;
        playerLuck = StatSelection.statPlayerLuck;
        isRandomized = StatSelection.statsAreRandomized;
        if (isRandomized) {
            thisPlayer = new Player(playerName, playerWeaponName);
        } else {
            thisPlayer = new Player(playerStrength, playerAccuracy, playerDefense, playerAgility,
                    playerIntelligence, playerLuck, playerName, playerWeaponName);
        }
        consoleOutput = "Welcome, " + thisPlayer.name + "! You are in an empty room, too small to be searched. If it could be searched, you could type, 'look'. To enter the next room, type 'progress'.";
        configureNextButton();
        actionInput = (EditText) findViewById(R.id.actionInput);
        healthDisplay = (TextView) findViewById(R.id.healthDisplay);
        gameInfo = (TextView) findViewById(R.id.gameInfo);
        thisPlayer.setAllStats();
        setGameInfo();
        healthDisplay.setText(displayInfo);
    }
    private void configureNextButton() {
        final Button progressButton = (Button) findViewById(R.id.submitButton);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInput = actionInput.getText().toString();
                actionInput.setText("");
                if (liveRoomCount == roomCount) {
                    progressButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Gameplay.this, FinishedGame.class));
                        }
                    });
                }
                changeOutput(userInput);
                setGameInfo();
            }
        });
    }

    /**
     * sets consoleOutput appropriately depending on what the user inputs.
     * @param action
     */
    public static void changeOutput (String action) {
        if (isBattling) {
            if (turnAdvantage) {
                myBattleStatus(action);
            }
            if (!turnAdvantage) {
                enemyBattleStatus();
            }
        } else {
            movementStatus(action);
        }
        setGameInfo();
        setHealthDisplay();
    }
    public static void myBattleStatus (String action) {
        if (action.equals(look) || action.equals(progress)) {
            consoleOutput = cantInBattle;
        } else if (action.equals(attack)) {
            int dealtDamage = thisPlayer.determineHit(thisRoom.numberOne);
            if (dealtDamage != 0) {
                consoleOutput = displayHit + dealtDamage;
                if (thisRoom.numberOne.liveHP <= 0) {
                    consoleOutput += victoryMessage;
                    isBattling = false;
                    thisPlayer.myPoints += thisRoom.numberOne.pointValue;
                    enemiesDefeatedCounter++;
                    if (thisRoom.disSearchable) {
                        consoleOutput += canBeSearched;
                    }
                }
            } else {
                consoleOutput = displayMiss;
            }
            turnAdvantage = false;
        } else if (action.equals(run)) {
            if (thisPlayer.myPoints < 100) {
                consoleOutput = insufficientPoints;
            } else {
                thisPlayer.myPoints -= 100;
                consoleOutput = ranAway;
                isBattling = false;
                thisPlayer.liveHP = thisPlayer.hp;
                movementStatus(progress);
            }
        } else if (action.equals(heal)) {
            if (thisPlayer.myPoints < 50) {
                consoleOutput = insufficientPoints;
            } else {
                thisPlayer.myPoints -= 50;
                consoleOutput = healed;
                thisPlayer.liveHP = thisPlayer.hp;
                turnAdvantage = false;
            }
        } else {
            consoleOutput = invalidCommand;
        }
    }
    public static void enemyBattleStatus() {
        int damageDealt = thisRoom.numberOne.determineHit(thisPlayer);
        if (isBattling) {
            if (damageDealt != 0) {
                consoleOutput += displayEnemyHit + damageDealt;
                if (thisPlayer.liveHP <= 0) {
                    consoleOutput += darkSouls;
                    isBattling = false;
                }
            } else {
                consoleOutput += displayEnemyMiss;
            }
        }
        turnAdvantage = true;
    }
    public static void movementStatus(String action) {
        if (action.equals(progress)) {
            if (liveRoomCount < roomCount) {
                thisRoom = new Room();
                liveRoomCount++;
                emptyRoom = "You have entered the next room, room " + liveRoomCount + ".";
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
                    int foundSecret = thisPlayer.foundSecret();
                    if (foundSecret == 0) {
                        consoleOutput = noSecretFound;
                    } else if (foundSecret == 1) {
                        consoleOutput = foundAttackUp;
                        thisPlayer.attackPower += 2;
                    } else if (foundSecret == 2) {
                        consoleOutput = foundDefenseUp;
                        thisPlayer.hp += 3;
                        thisPlayer.liveHP += 3;
                    } else if (foundSecret == 3) {
                        consoleOutput = foundPoints;
                        thisPlayer.myPoints += 300;
                    }
                    thisRoom.roomSearched = true;
                    secretsFoundCounter++;
                    setHealthDisplay();
                } else {
                    consoleOutput = alreadySearched;
                }
            } else {
                consoleOutput = unsearchable;
            }
        } else {
            consoleOutput = invalidCommand;
        }
    }
    public static void setGameInfo() {
        gameInfo.setText(consoleOutput);
    }
    public static void setHealthDisplay() {
        healthDisplay.setText("Hp: " + thisPlayer.liveHP + "/" + thisPlayer.hp + " Points: " + thisPlayer.myPoints);
    }
    public static void main(String[] args) {

    }
}
