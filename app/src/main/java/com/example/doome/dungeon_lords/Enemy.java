package com.example.doome.dungeon_lords;

public class Enemy extends Character {
    /**
     * The type of this enemy.
     */
    public enemyType enemyType;
    /**
     * Boolean that keeps track of whether this is a strong enemy
     */
    public boolean isStrong = false;
    /**
     * If the enemy is a warlock, then there is a chance that they drop a page with a spell on it
     */
    public boolean isWarlock = false;
    /**
     * Chance that, if warlock, this enemy will drop a page with a spell
     */
    public static final double dropsNewSpellPage = .65;
    /**
     * Byte that keeps track of how much MP this enemy drops
     */
    public byte MPDrop = 0;
    /**
     * The amount of points the player earns upon defeating this type of enemy.
     */
    public int goldDrop;
    /**
     * The chance that this enemy will read your counter attack. If they do, they will
     * feint an attack, wait for you to slip up your response, and attack.
     */
    public double counterReadChance = .11;
    /**
     * The live value of counterReadChance that is changed throughout combat: the original must
     * be preserved to know when not to misuse counterReadMultiplier
     */
    public double liveCounterReadChance;
    /**
     * The multiplier that counterReadChance will be multiplied by every time you counter,
     * and be divided by when you do anything else
     */
    public static double counterReadMultiplier = 1.6;
    /**
     * The scalar for initially setting counterReadChance based on the enemy's intelligence
     */
    public static double counterReadChanceScalar = 1.15;
    /**
     * Array of doubles to determine the likelihood of this enemy being each type.
     */
    public static final double[] typeChances = {.15, .25, .38, .63, .75, .85};
    /**
     * The chance that an enemy is a strong variant. Depends on selected difficulty.
     */
    public static double strongChance;

    public Enemy() {
        determineType();
        setAllStats();
    }

    public Enemy(byte enemyIndex) {
        determineType(enemyIndex);
        setAllStats();
    }

    private void makeEnemy (String setName, String setWeaponName, byte strengthStat,
                            byte accuracyStat, byte defenseStat, byte agilityStat,
                            byte intelligenceStat, int setGoldDrop) {
        name = setName;
        weaponName = setWeaponName;
        strengthValue = strengthStat;
        accuracyValue = accuracyStat;
        defenseValue = defenseStat;
        agilityValue = agilityStat;
        goldDrop = setGoldDrop;
        intelligenceValue = intelligenceStat;
        luckValue = statMin;
    }
    /**
     * Possible types this enemy could be.
     */
    public enum enemyType {
        Goblin,
        Skeleton,
        Red_Slime,
        Green_Slime,
        Giant_Isopod,
        Giant_Squid,
        Dusty_Warlock,
        Big_Brain_Warlock,
        Buff_Warlock,
        Giant_Cyclops,
        Botanic_Siren,
        Anarchist_Eddie,
        Soul_Eater,
        A_Single_Rat,
        Lone_Wolf,
        Angry_Penguin,
        Feisty_Crab,
        Master_Thief,
        Grizzly_Bear,
        Possessed_Tree,
        Centaur
    }
    /**
     * Determines the identity of this enemy
     */
    private void determineType() {
        double type = Math.random();
        double isMiniboss = Math.random();
        if (isMiniboss > strongChance) {
            if (type < typeChances[0]) {
                MPDrop = 3;
                this.enemyType = enemyType.Green_Slime;
                makeEnemy("Green Slime", "body check", (byte) 1, (byte) 2, (byte) 1, (byte) 2, (byte) 2, 80);
            } else if (type < typeChances[1]) {
                MPDrop = 4;
                this.enemyType = enemyType.Red_Slime;
                makeEnemy("Red Slime", "body check", (byte) 2, (byte) 3, (byte) 2, (byte) 2, (byte) 2, 90);
            } else if (type < typeChances[2]) {
                this.enemyType = enemyType.Goblin;
                makeEnemy("Goblin", "knife slash", (byte) 1, (byte) 4, (byte) 3, (byte) 3, (byte) 3, 110);
            } else if (type < typeChances[3]) {
                MPDrop = 4;
                this.enemyType = enemyType.Skeleton;
                makeEnemy("Skeleton", "bone toss", (byte) 2, (byte) 1, (byte) 3, (byte) 2, (byte) 4, 100);
            } else if (type < typeChances[4]){
                this.enemyType = enemyType.Giant_Isopod;
                makeEnemy("Giant Isopod", "bite", (byte) 1, (byte) 3, (byte) 5, (byte) 2, (byte) 2, 120);
            } else if (type < typeChances[5]){
                this.enemyType = enemyType.Giant_Squid;
                makeEnemy("Giant Squid", "tentacle whip", (byte) 3, (byte) 3, (byte) 3, (byte) 2, (byte) 5, 120);
            } else {
                MPDrop = 10;
                isWarlock = true;
                this.enemyType = enemyType.Dusty_Warlock;
                makeEnemy("Dusty Warlock", "fireball", (byte) 4, (byte) 2, (byte) 2, (byte) 3, (byte) 4, 150);
            }
        }
        else {
            isStrong = true;
            if (type < typeChances[0]) {
                MPDrop = 12;
                isWarlock = true;
                this.enemyType = enemyType.Big_Brain_Warlock;
                makeEnemy("Big Brain Warlock", "ice storm", (byte) 5, (byte) 5, (byte) 6, (byte) 3, (byte) 9, 200);
            } else if (type < typeChances[1]) {
                MPDrop = 9;
                isWarlock = true;
                this.enemyType = enemyType.Buff_Warlock;
                makeEnemy("Buff Warlock", "swarm of fists", (byte) 6, (byte) 4, (byte) 7, (byte) 3, (byte) 6, 250);
            } else if (type < typeChances[2]) {
                MPDrop = 6;
                this.enemyType = enemyType.Giant_Cyclops;
                makeEnemy("Giant Cyclops", "rock hurl", (byte) 6, (byte) 3, (byte) 6, (byte) 2, (byte) 2, 200);
            } else if (type < typeChances[3]) {
                MPDrop = 7;
                this.enemyType = enemyType.Botanic_Siren;
                makeEnemy("Botanic Siren", "poisonous cloud", (byte) 4, (byte) 6, (byte) 7, (byte) 2, (byte) 5, 240);
            } else if (type < typeChances[4]){
                this.enemyType = enemyType.Anarchist_Eddie;
                makeEnemy("Anarchist Eddie", "crowbar swing", (byte) 5, (byte) 4, (byte) 6, (byte) 4, (byte) 1, 220);
            } else if (type < typeChances[5]){
                MPDrop = 15;
                this.enemyType = enemyType.Soul_Eater;
                makeEnemy("Soul Eater", "absorb", (byte) 7, (byte) 3, (byte) 6, (byte) 4, (byte) 3, 250);
            } else {
                this.enemyType = enemyType.A_Single_Rat;
                makeEnemy("Single Rat", "bite", (byte) 1, (byte) 10, (byte) 2, (byte) 4, (byte) 3, 170);
            }
        }
    }


    /**
     * Makes the enemy you want based on the index you give
     *
     * @param enemyIndex number which maps to the enemy
     */
    private void determineType(byte enemyIndex) {
        switch (enemyIndex) {
            case 0:
                MPDrop = 3;
                this.enemyType = enemyType.Green_Slime;
                makeEnemy("Green Slime", "body check", (byte) 1, (byte) 2, (byte) 1, (byte) 2, (byte) 2, 80);
                break;
            case 1:
                MPDrop = 4;
                this.enemyType = enemyType.Red_Slime;
                makeEnemy("Red Slime", "body check", (byte) 2, (byte) 3, (byte) 2, (byte) 2, (byte) 2, 90);
                break;
            case 2:
                this.enemyType = enemyType.Goblin;
                makeEnemy("Goblin", "knife slash", (byte) 1, (byte) 4, (byte) 3, (byte) 3, (byte) 3, 110);
                break;
            case 3:
                MPDrop = 4;
                this.enemyType = enemyType.Skeleton;
                makeEnemy("Skeleton", "bone toss", (byte) 2, (byte) 1, (byte) 3, (byte) 2, (byte) 4, 100);
                break;
            case 4:
                this.enemyType = enemyType.Giant_Isopod;
                makeEnemy("Giant Isopod", "bite", (byte) 1, (byte) 3, (byte) 5, (byte) 2, (byte) 2, 120);
                break;
            case 5:
                this.enemyType = enemyType.Giant_Squid;
                makeEnemy("Giant Squid", "tentacle whip", (byte) 3, (byte) 3, (byte) 3, (byte) 2, (byte) 5, 120);
                break;
            case 6:
                MPDrop = 10;
                isWarlock = true;
                this.enemyType = enemyType.Dusty_Warlock;
                makeEnemy("Dusty Warlock", "fireball", (byte) 5, (byte) 2, (byte) 3, (byte) 3, (byte) 4, 150);
                break;
            case 7:
                isStrong = true;
                MPDrop = 12;
                isWarlock = true;
                this.enemyType = enemyType.Big_Brain_Warlock;
                makeEnemy("Big Brain Warlock", "ice storm", (byte) 5, (byte) 5, (byte) 6, (byte) 3, (byte) 9, 200);
                break;
            case 8:
                isStrong = true;
                MPDrop = 9;
                isWarlock = true;
                this.enemyType = enemyType.Buff_Warlock;
                makeEnemy("Buff Warlock", "swarm of fists", (byte) 6, (byte) 4, (byte) 7, (byte) 3, (byte) 6, 250);
                break;
            case 9:
                isStrong = true;
                MPDrop = 6;
                this.enemyType = enemyType.Giant_Cyclops;
                makeEnemy("Giant Cyclops", "rock hurl", (byte) 7, (byte) 3, (byte) 6, (byte) 2, (byte) 2, 200);
                break;
            case 10:
                isStrong = true;
                MPDrop = 7;
                this.enemyType = enemyType.Botanic_Siren;
                makeEnemy("Botanic Siren", "poisonous cloud", (byte) 4, (byte) 6, (byte) 7, (byte) 2, (byte) 5, 240);
                break;
            case 11:
                isStrong = true;
                this.enemyType = enemyType.Anarchist_Eddie;
                makeEnemy("Anarchist Eddie", "crowbar swing", (byte) 6, (byte) 4, (byte) 6, (byte) 4, (byte) 1, 220);
                break;
            case 12:
                isStrong = true;
                MPDrop = 15;
                this.enemyType = enemyType.Soul_Eater;
                makeEnemy("Soul Eater", "absorb", (byte) 9, (byte) 3, (byte) 6, (byte) 4, (byte) 3, 250);
                break;
            case 13:
                isStrong = true;
                this.enemyType = enemyType.A_Single_Rat;
                makeEnemy("Single Rat", "bite", (byte) 1, (byte) 10, (byte) 2, (byte) 4, (byte) 3, 170);
                break;
            case 14:
                this.enemyType = enemyType.Lone_Wolf;
                makeEnemy("Lone Wolf", "bite", (byte) 2, (byte) 3, (byte) 3, (byte) 4, (byte) 1, 110);
                break;
            case 15:
                this.enemyType = enemyType.Angry_Penguin;
                makeEnemy("Angry Penguin", "telekinetic snowball", (byte) 1, (byte) 7, (byte) 4, (byte) 2, (byte) 4, 110);
                break;
            case 16:
                this.enemyType = enemyType.Feisty_Crab;
                makeEnemy("Feisty Crab", "pince", (byte) 5, (byte) 3, (byte) 5, (byte) 1, (byte) 2, 130);
                break;
            case 17:
                this.enemyType = enemyType.Master_Thief;
                makeEnemy("Master Thief", "knife slash", (byte) 5, (byte) 5, (byte) 4, (byte) 4, (byte) 7, 170);
                break;
            case 18:
                this.enemyType = enemyType.Grizzly_Bear;
                makeEnemy("Grizzly Bear", "claws", (byte) 6, (byte) 6, (byte) 3, (byte) 4, (byte) 3, 160);
                break;
            case 19:
                this.enemyType = enemyType.Possessed_Tree;
                makeEnemy("Possessed Tree", "root overgrowth", (byte) 5, (byte) 7, (byte) 7, (byte) 0, (byte) 2, 200);
                break;
            case 20:
                this.enemyType = enemyType.Centaur;
                makeEnemy("Centaur", "kick", (byte) 7, (byte) 3, (byte) 5, (byte) 6, (byte) 5, 300);
                break;
        }
    }

    /**
     * Overridden setAllStats for unique enemy traits
     */
    public void setAllStats() {
        setDodgeChance();
        setSecretChance();
        setHp();
        liveHP = hp;
        setHitChance();
        setAttackPower();
        //unique to enemy
        setCounterReadChance();
    }

    public void setCounterReadChance() {
        for (int i = 0; i < intelligenceValue; i++) {
            counterReadChance *= counterReadChanceScalar;
        }
        liveCounterReadChance = counterReadChance;
    }
}
