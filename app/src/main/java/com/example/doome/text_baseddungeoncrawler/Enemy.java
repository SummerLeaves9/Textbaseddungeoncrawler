package com.example.doome.text_baseddungeoncrawler;

public class Enemy extends Character{
    /**
     * The type of this enemy.
     */
    public enemyType enemyType;
    /**
     * Array of doubles to determine the likelihood of this enemy being each type.
     */
    public final double[] typeChances = {.25, .35, .7, .95 };
    /**
     * Stat value, 1 for enemies' stats
     */
    public final int enemyStatOne = 1;
    /**
     * Stat value, 2 for enemies' stats
     */
    public final int enemyStatTwo = 2;
    /**
     * Stat value, 3 for enemies' stats
     */
    public final int enemyStatThree = 3;
    /**
     * Stat value, 4 for enemies' stats
     */
    public final int enemyStatFour = 4;
    /**
     * Stat value, 5 for enemies' stats
     */
    public final int enemyStatFive = 5;

    public Enemy() {
        determineType();
        determineEnemy(enemyType);
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
    }
    /**
     * Detemines the identity of this enemy
     */
    private void determineType() {
        double lolRandom = Math.random();
        if (lolRandom < typeChances[0]) {
            enemyType = enemyType.Green_Slime;
        } else if (lolRandom < typeChances[1]) {
            enemyType = enemyType.Red_Slime;
        } else if (lolRandom < typeChances[2]) {
            enemyType = enemyType.Goblin;
        } else if (lolRandom < typeChances[3]) {
            enemyType = enemyType.Skeleton;
        } else {
            enemyType = enemyType.Giant_Isopod;
        }
    }
    private void determineEnemy(enemyType type) {
        if (type == enemyType.Green_Slime) {
            name = "Green Slime";
            weaponName = "body check";
            this.accuracyValue = enemyStatTwo;
            this.defenseValue = enemyStatOne;
            this.strengthValue = enemyStatTwo;
            this.agilityValue = enemyStatTwo;
        } else if (type == enemyType.Red_Slime) {
            name = "Red Slime";
            weaponName = "body check";
            this.accuracyValue = enemyStatThree;
            this.defenseValue = enemyStatTwo;
            this.strengthValue = enemyStatThree;
            this.agilityValue = enemyStatTwo;
        } else if (type == enemyType.Goblin) {
            name = "Goblin";
            weaponName = "punch";
            this.accuracyValue = enemyStatFour;
            this.defenseValue = enemyStatThree;
            this.strengthValue = enemyStatTwo;
            this.agilityValue = enemyStatThree;
        } else if (type == enemyType.Skeleton) {
            name = "Skeleton";
            weaponName = "bone throw";
            this.accuracyValue = enemyStatOne;
            this.defenseValue = enemyStatThree;
            this.strengthValue = enemyStatThree;
            this.agilityValue = enemyStatTwo;
        } else {
            name = "Giant Isopod";
            weaponName = "bite";
            this.accuracyValue = enemyStatThree;
            this.defenseValue = enemyStatFive;
            this.strengthValue = enemyStatTwo;
            this.agilityValue = enemyStatTwo;
        }
    }
}
