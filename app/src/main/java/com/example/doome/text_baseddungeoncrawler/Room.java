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
     * On easy difficulty, chance that an enemy is a strong variant.
     */
    public double strongChanceEasy = .1;
    /**
     * On normal difficulty, chance that an enemy is a strong variant.
     */
    public double strongChanceNormal = .27;
    /**
     * On hard difficulty, chance that an enemy is a strong variant.
     */
    public double strongChanceHard = .5;

    /**
     * Boolean that keeps track of whether this room can be searched.
     */
    public boolean disSearchable = false;
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
            numberOne.name = "null";
            disSearchable = false;
        } else {
            double enemyIndicator = Math.random();
            double isSearchable = Math.random();
            double isStrong = Math.random();
            if (enemyIndicator > enemyChance) {
                numberOne.name = "null";
            } else {
                Gameplay.isBattling = true;
            }
            if (isSearchable < searchableChance) {
                disSearchable = true;
            }
        }
    }
}
