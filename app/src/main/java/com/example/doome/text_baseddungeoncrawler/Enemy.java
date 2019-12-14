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
     * The amount of points the player earns upon defeating this type of enemy.
     */
    public int pointValue;

    public Enemy() {
        determineType();
        determineEnemy(enemyType);
        intelligenceValue = statMin;
        luckValue = statMin;
        setAllStats();
    }
    /**
     * Possible types this enemy could be.
     */
    public enum enemyType {
        Goblin,
        Skeleton,
        Red_Slime,
        Green_Slime,
        Giant_Isopod
    }
    /**
     * Detemines the identity of this enemy
     */
    private void determineType() {
        double lolRandom = Math.random();
        if (lolRandom < typeChances[0]) {
            this.enemyType = enemyType.Green_Slime;
        } else if (lolRandom < typeChances[1]) {
            this.enemyType = enemyType.Red_Slime;
        } else if (lolRandom < typeChances[2]) {
            this.enemyType = enemyType.Goblin;
        } else if (lolRandom < typeChances[3]) {
            this.enemyType = enemyType.Skeleton;
        } else {
            this.enemyType = enemyType.Giant_Isopod;
        }
    }
    private void determineEnemy(enemyType type) {
        if (type == enemyType.Green_Slime) {
            name = "Green Slime";
            weaponName = "body check";
            this.accuracyValue = 2;
            this.defenseValue = 1;
            this.strengthValue = 2;
            this.agilityValue = 2;
            this.pointValue = 80;
        } else if (type == enemyType.Red_Slime) {
            name = "Red Slime";
            weaponName = "body check";
            this.accuracyValue = 3;
            this.defenseValue = 2;
            this.strengthValue = 3;
            this.agilityValue = 2;
            this.pointValue = 90;
        } else if (type == enemyType.Goblin) {
            name = "Goblin";
            weaponName = "punch";
            this.accuracyValue = 4;
            this.defenseValue = 3;
            this.strengthValue = 2;
            this.agilityValue = 3;
            this.pointValue = 110;
        } else if (type == enemyType.Skeleton) {
            name = "Skeleton";
            weaponName = "bone throw";
            this.accuracyValue = 1;
            this.defenseValue = 3;
            this.strengthValue = 3;
            this.agilityValue = 2;
            this.pointValue = 100;
        } else {
            name = "Giant Isopod";
            weaponName = "bite";
            this.accuracyValue = 3;
            this.defenseValue = 5;
            this.strengthValue = 2;
            this.agilityValue = 2;
            this.pointValue = 120;
        }
    }
}
