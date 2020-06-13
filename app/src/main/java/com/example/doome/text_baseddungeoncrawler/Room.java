package com.example.doome.text_baseddungeoncrawler;

public class Room {
    /**
     * chance that this room contains an enemy
     */
    public double enemyChance = .65;
    /**
     * chance that this room is searchable.
     * nice
     */
    public double searchableChance = .69;
    /**
     * Chance that this room is an oasis
     */
    public double oasisChance = .07;
    /**
     * Boolean to keep track of whether an oasis has occurred this game
     */
    public static boolean oasisUsed = false;

    /**
     * Boolean that keeps track of whether this room can be searched.
     */
    public boolean disSearchable = false;
    /**
     * Boolean that keeps track of whether this room has an enemy in it.
     */
    public boolean hasEnemy;
    /**
     * If an enemy is in the room, this is how it will be accessed.
     */
    public Enemy numberOne = new Enemy();
    /**
     * The boolean that determines whether the user has searched the room.
     */
    public boolean roomSearched = false;

    /**
     * All-purpose constructor
     */
    public Room() {

        if (Gameplay.liveRoomCount == 0) {
            /*
            hasEnemy = false;
            disSearchable = false;

             */
        } else {
            double enemyIndicator = Math.random();
            double isSearchable = Math.random();
            if (enemyIndicator > enemyChance) {
                hasEnemy = false;
            } else {
                hasEnemy = true;
            }
            if (isSearchable < searchableChance) {
                disSearchable = true;
            }
        }
    }
}
