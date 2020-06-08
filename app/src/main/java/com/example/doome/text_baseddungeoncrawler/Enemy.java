package com.example.doome.text_baseddungeoncrawler;

public class Enemy extends Character{
    /**
     * The type of this enemy.
     */
    public enemyType enemyType;
    /**
     * Boolean that keeps track of whether this is a strong enemy
     */
    public boolean isStrong;
    /**
     * If the enemy is a warlock, then they drop a page with a spell on it
     */
    public boolean isWarlock;
    /**
     * The amount of points the player earns upon defeating this type of enemy.
     */
    public int pointValue;
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

    private void makeEnemy (String setName, String setWeaponName, byte strengthStat,
                            byte accuracyStat, byte defenseStat, byte agilityStat,
                            int setPointValue) {
        name = setName;
        weaponName = setWeaponName;
        strengthValue = strengthStat;
        accuracyValue = accuracyStat;
        defenseValue = defenseStat;
        agilityValue = agilityStat;
        pointValue = setPointValue;
        intelligenceValue = statMin;
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
    }
    /**
     * Detemines the identity of this enemy
     */
    private void determineType() {
        double type = Math.random();
        double isStrong = Math.random();
        if (isStrong > strongChance) {
            if (type < typeChances[0]) {
                this.enemyType = enemyType.Green_Slime;
                makeEnemy("Green Slime", "body check", (byte) 2, (byte) 2, (byte) 1, (byte) 2, 80);
            } else if (type < typeChances[1]) {
                this.enemyType = enemyType.Red_Slime;
                makeEnemy("Red Slime", "body check", (byte) 3, (byte) 3, (byte) 2, (byte) 2, 90);
            } else if (type < typeChances[2]) {
                this.enemyType = enemyType.Goblin;
                makeEnemy("Goblin", "knife slash", (byte) 2, (byte) 4, (byte) 3, (byte) 3, 110);
            } else if (type < typeChances[3]) {
                this.enemyType = enemyType.Skeleton;
                makeEnemy("Skeleton", "bone toss", (byte) 3, (byte) 1, (byte) 3, (byte) 2, 100);
            } else if (type < typeChances[4]){
                this.enemyType = enemyType.Giant_Isopod;
                makeEnemy("Giant Isopod", "bite", (byte) 2, (byte) 3, (byte) 5, (byte) 2, 120);
            } else if (type < typeChances[5]){
                this.enemyType = enemyType.Giant_Squid;
                makeEnemy("Giant Squid", "tentacle whip", (byte) 4, (byte) 3, (byte) 3, (byte) 2, 120);
            } else {
                isWarlock = true;
                this.enemyType = enemyType.Dusty_Warlock;
                makeEnemy("Dusty Warlock", "fireball", (byte) 5, (byte) 2, (byte) 2, (byte) 3, 150);
            }
        }
        else {
            if (type < typeChances[0]) {
                isWarlock = true;
                this.enemyType = enemyType.Big_Brain_Warlock;
                makeEnemy("Big Brain Warlock", "ice storm", (byte) 4, (byte) 5, (byte) 4, (byte) 3, 200);
            } else if (type < typeChances[1]) {
                isWarlock = true;
                this.enemyType = enemyType.Buff_Warlock;
                makeEnemy("Buff Warlock", "swarm of fists", (byte) 5, (byte) 4, (byte) 5, (byte) 3, 250);
            } else if (type < typeChances[2]) {
                this.enemyType = enemyType.Giant_Cyclops;
                makeEnemy("Giant Cyclops", "rock hurl", (byte) 5, (byte) 3, (byte) 4, (byte) 2, 200);
            } else if (type < typeChances[3]) {
                this.enemyType = enemyType.Botanic_Siren;
                makeEnemy("Botanic Siren", "poisonous cloud", (byte) 3, (byte) 6, (byte) 5, (byte) 2, 240);
            } else if (type < typeChances[4]){
                this.enemyType = enemyType.Anarchist_Eddie;
                makeEnemy("Anarchist Eddie", "crowbar swing", (byte) 4, (byte) 4, (byte) 4, (byte) 4, 220);
            } else if (type < typeChances[5]){
                this.enemyType = enemyType.Soul_Eater;
                makeEnemy("Soul Eater", "absorb", (byte) 6, (byte) 3, (byte) 4, (byte) 4, 250);
            } else {
                this.enemyType = enemyType.A_Single_Rat;
                makeEnemy("A Single Rat", "bite", (byte) 1, (byte) 10, (byte) 1, (byte) 4, 170);
            }
        }
    }
}
