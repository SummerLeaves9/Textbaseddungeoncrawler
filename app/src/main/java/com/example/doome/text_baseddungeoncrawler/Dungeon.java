package com.example.doome.text_baseddungeoncrawler;

public class Dungeon {
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
     * The int that stores the player's strength stat before the player is initialized.
     */
    public static int playerStrength;
    /**
     * The int that stores the player's defense stat before the player is initialized.
     */
    public static int playerDefense;
    /**
     * The int that stores the player's accuracy stat before the player is initialized.
     */
    public static int playerAccuracy;
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
     * The String that stores the player's name before the player is initalized.
     */
    public static String playerName;
    /**
     * The String that stores the player's weapon name before the player is initalized.
     */
    public static String playerWeaponName;
    /**
     * The string which will be used by the UI to display game text.
     * Is changed and redisplayed throughout play.
     */
    public static String consoleOutput;
    /**
     * The first instructions displayed in console output.
     * Instructs the player to enter their name.
     */
    public static final String enterName = "Please enter your name.";
    /**
     * The second instructions displayed in the console output.
     * Instructs the player to enter the name of their weapon.
     */
    public static final String enterWeaponName = "What weapon do you prefer to use?";
    /**
     * The player.
     */
    public static Player thisPlayer;
    /**
     * The String which will store the user's desired action:
     * Is changed throughout play.
     */
    public static String userInput;
    /**
     * main method for displaying game logic to the app console
     * @param args
     */
    public static void main (String[] args) {
        consoleOutput = enterName;
        //detect when the player has entered their name;
        if (!(userInput.equals(""))) {
            playerName = userInput;
        }
        consoleOutput = enterWeaponName;
        if (!(userInput.equals(""))) {
            playerWeaponName = userInput;
        }
        if (!isRandomized) {
            thisPlayer = new Player(playerStrength, playerAccuracy,
                    playerDefense, playerAgility, playerIntelligence, playerLuck, playerName, playerWeaponName);
        } else {
            thisPlayer = new Player(playerName, playerWeaponName);
        }
        for (int i = 0; i < roomCount; i++) {
            //run methods relevant to game
            Room thisRoom = new Room();
            if (thisRoom.numberOne != null) {
                startBattle();
            }
        }
    }
    public static void startBattle() {
        return;
    }
}
