package com.example.doome.dungeon_lords;

public class Room {
    /**
     * chance that this room contains an enemy
     */
    public double enemyChance = .65;
    /**
     * chance that this room is searchable.
     * nice
     */
    public double searchableChance = .39;
    /**
     * Chance that this room is an oasis
     */
    public double oasisChance = .17;
    /**
     * Boolean to keep track of whether an oasis has occurred in this dungeon
     */
    public static boolean oasisUsed = false;
    /**
     * Boolean to keep track of whether this room is an oasis
     */
    public boolean isOasis = false;
    /**
     * Boolean that keeps track of whether this room can be searched.
     */
    public boolean disSearchable = false;
    /**
     * Boolean that keeps track of whether this room has an enemy in it.
     */
    public boolean hasEnemy;
    /**
     * randomly generated number that will determine the identity of this Enemy, based on which
     * Enemys are configured to be in Gameplay.thisDungeon;
     */
    private byte enemyIndex;
    /**
     * Arrays that determine the chances of which Enemy will appear in a given room. Longer arrays
     * correspond to dungeons having more Enemy types available
     */
    private double[][] typeChances = {
            {.45, .75, .93},
            {.4, .67, .87, .95},
            {.36, .61, .78, .88, .95},
            {.33, .58, .72, .81, .89, .95}
    };
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
        if (Gameplay.roomCount == 1) {
            disSearchable = false;
            hasEnemy = true;
            setEnemyForEncounter();
        } else {
            double oasis = Math.random();
            if (oasis < oasisChance && !oasisUsed && Gameplay.liveRoomCount > 4) {
                hasEnemy = false;
                disSearchable = false;
                oasisUsed = true;
                isOasis = true;
            } else {
                double enemyIndicator = Math.random();
                double isSearchable = Math.random();
                if (enemyIndicator > enemyChance) {
                    hasEnemy = false;
                } else {
                    hasEnemy = true;
                    pickEnemyForStandardDungeon();
                }
                if (isSearchable < searchableChance) {
                    disSearchable = true;
                }
            }
        }

    }

    private void pickEnemyForStandardDungeon() {
        double[] thisDungeonTypeChances;
        switch (Gameplay.thisDungeon.length - 4) {
            case 4:
                thisDungeonTypeChances = typeChances[0]; break;
            case 5:
                thisDungeonTypeChances = typeChances[1]; break;
            case 6:
                thisDungeonTypeChances = typeChances[2]; break;
            case 7:
                thisDungeonTypeChances = typeChances[3]; break;
            default:
                thisDungeonTypeChances = typeChances[4]; break;
        }
        double whichEnemy = Math.random();
        for (byte i = 0; i < thisDungeonTypeChances.length; i++) {
            if (whichEnemy < thisDungeonTypeChances[i]) {
                numberOne = new Enemy(Gameplay.thisDungeon[i + 4]);
                break;
            }
            if (i + 1 == thisDungeonTypeChances.length) {
                numberOne = new Enemy(Gameplay.thisDungeon[i + 5]);
            }
        }
    }

    private void setEnemyForEncounter() {
        if (Gameplay.diedInEncounter) {
            numberOne.liveHP = numberOne.hp;
        } else {
            double enemyCoinFlip = Math.random() * 2;
            if (enemyCoinFlip < 1) {
                numberOne = new Enemy(Gameplay.thisDungeon[1]);
            } else {
                numberOne = new Enemy(Gameplay.thisDungeon[2]);
            }
        }
    }
}
