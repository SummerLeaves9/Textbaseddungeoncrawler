package com.example.doome.text_baseddungeoncrawler;

public class Room {
    /**
     * chance that this room contains an enemy
     */
    public double enemyChance = .45;
    /**
     * chance that this room is searchable.
     */
    public double searchableChance = .49;
    /**
     * Boolean that keeps track of whether this room can be searched.
     */
    public boolean disSearchable = false;
    /**
     * If an enemy is in the room, this is how it will be accessed.
     */
    public Enemy numberOne = null;
    /**
     * The boolean that determines whether the user has searched the room.
     */
    public boolean roomSearched = false;

    /**
     * All-purpose constructor
     */
    public Room() {
        if (Gameplay.liveRoomCount == 0) {
            numberOne = null;
            disSearchable = false;
        } else {
            double enemyIndicator = Math.random();
            double isSearchable = Math.random();
            if (enemyIndicator < enemyChance) {
                numberOne = new Enemy();
            }
            if (isSearchable < searchableChance) {
                disSearchable = true;
            }
        }
    }

}
