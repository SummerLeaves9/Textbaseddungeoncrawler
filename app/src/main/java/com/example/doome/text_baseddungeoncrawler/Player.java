package com.example.doome.text_baseddungeoncrawler;

//import java.util.Pair;

public class Player extends Character {

    public static double negativeSecretChance;

    public int myPoints = 100;

    public String[] spells = {"None", "None", "None", "None", "None", "None", "None"};

    public static final String[][] allSpells = {
            {"Basic Heal", "MA, Cost: 5 MP. Restores some health"},
            {"Strong Heal", "MA, Cost: 15 MP. Restores a lot of health"},
            {"Ultimate Block", "MA, Cost: 20 MP. Ensures you won't take damage this turn."},
            {"Sphericon","M, Cost: 7 MP. Summons a lil' guy to fight for you, but only by " +
                    "charging it."},
            {"Sphericon Charge","M, Cost: 3 MP. Charges your Sphericon for 1 unit."},
            {"Sphericon Supercharge","MA, Cost: 12 MP. Charges your Sphericon for 3 units."},
            {"Lucky Marksman","M, Cost: 6 MP. You are healed half your your party's damage output" +
                    " this turn. But if you miss, you take double what your enemies deal."},
            {"Blinding Light","M, Cost: 3 MP. 80 percent chance to reduce your enemy's accuracy " +
                    "by 3 this turn."},
            {"Fireball","MA, Cost: 6 MP. Cast a flaming projectile whose strength is proportional" +
                    " to your magic stat (from 1.5-2.5 times your base attack power). Less " +
                    "accurate than your direct attack"},
            {"Poison","M, Cost: 7 MP. Deals 15 percent of your enemy's max health for 3 turns, " +
                    "but the enemy is healed 45 percent after 3 turns."},
            };

    public Player(final byte setStrength, final byte setAccuracy, final byte setDefense,
                  final byte setAgility, final byte setIntelligence, final byte setMagic, final byte setLuck,
                  final String setName, final String setAttackName) {
        strengthValue = setStrength;
        accuracyValue = setAccuracy;
        defenseValue = setDefense;
        agilityValue = setAgility;
        intelligenceValue = setIntelligence;
        magicValue = setMagic;
        luckValue = setLuck;
        name = setName;
        weaponName = setAttackName;
        setAllStats();
        if (magicValue > 0) {
            spells[0] = allSpells[0][0];
        } if (magicValue > 3) {
            spells[1] = allSpells[8][0];
        } if (magicValue > 5) {
            spells[2] = allSpells[7][0];
        } if (magicValue > 8) {
            spells[0] = allSpells[1][0];
        }
    }
    /**
     * Determines whether a secret was found or not.
     * Returns the type of secret if found, or 0 if no secret was found.
     */
    public int foundSecret() {
        double foundSecret = Math.random();
        double isNegativeSecret = Math.random();
        if (foundSecret < secretChance) {
            double secretType = Math.random();
            if (isNegativeSecret > negativeSecretChance) {
                if (secretType < 0.333) {
                    return 1;
                } else if (secretType < 0.666) {
                    return 2;
                } else {
                    return 3;
                }
            } else {
                if (secretType < 0.333) {
                    return 4;
                } else if (secretType < 0.666) {
                    return 5;
                } else {
                    return 6;
                }
            }
        } else {
            return 0;
        }
    }
    /**
     * Sets this character's additional chance to entirely dodge an attack based on their luck stat,
     * which is to be added to total dodgeChance.
     * Helper function for setAllStats
     */
    public void setLuckDodgeChance() {
        if (luckValue > 0) {
            double scaled = 1;
            for (int i = 0; i < luckValue; i++) {
                scaled *= scalarOne;
            }
            luckDodgeChance = (baseLuckDodgeChance * scaled);
        } else {
            luckDodgeChance = baseAllLuckChance;
        }
    }

    /**
     * Sets this character's chance to land a critical hit upon landing a normal hit based on their
     * luck stat.
     * sets critChance to 0 if this character's luckValue is 0.
     * Helper function for setAllStats.
     */
    public void setCritChance() {
        if (luckValue > 0) {
            double scaled = 1;
            for (int i = 0; i < luckValue; i++) {
                scaled *= scalarOne;
            }
            critChance = scaled * baseCritChance;
        } else {
            critChance = baseAllLuckChance;
        }
    }
    /**
     * Sets this character's additional chance to find a secret based on their luck stat, which is
     * to be added to secretChance.
     * Helper function for setAllStats.
     */
    public void setLuckSecretChance() {
        if (luckValue > 0) {
            double scaled = 1;
            for (int i = 0; i < luckValue; i++) {
                scaled *= scalarOne;
            }
            luckSecretChance = baseLuckSecretChance * scaled;
        } else {
            luckSecretChance = baseAllLuckChance;
        }
    }
    public void setAllStats() {
        setLuckDodgeChance();
        setDodgeChance();
        setLuckSecretChance();
        setSecretChance();
        setCritChance();
        setHp();
        liveHP = hp;
        setMP();
        liveMP = mp;
        setHitChance();
        setAttackPower();
    }

    /**
     * The following methods are unlockable spells available to a player in battle only if their
     * magic stat is high enough, or if they find Spell Scrolls on their quest.
     */

    /**
     * Basic Heal: MA
     * Spell 1
     * Restores 35 percent of the player's health
     */

    public void basicHeal() {
        double tobeHealed = this.hp;
        tobeHealed *= .35;
        this.liveHP += (byte) tobeHealed;
    }

    /**
     * Strong Heal: MA
     * Spell 2
     * Restores 65 percent of the player's health.
     */

    public void strongHeal() {
        double tobeHealed = this.hp;
        tobeHealed *= .65;
        this.liveHP += (byte) tobeHealed;
    }

    /**
     * Ultimate Block: MA
     * Spell 3
     * Makes the player invincible this turn.
     */

    public static void ultimateBlock() {

    }

    /**
     * Sphericon: M
     * Spell 4
     * Summons a lil guy to fight for you. To make it deal damage, charge it with
     * either Sphericon Charge or Sphericon Supercharge. The more you charge it, the more damage
     * it will do. It will attack the first turn you don't give it charge.
     */

    public static void sphericon() {

    }

    /**
     * Sphericon Charge: M
     * Spell 5
     * Charges a sphericon for one unit.
     */

    public static void sphericonCharge() {

    }

    /**
     * Sphericon Supercharge: MA
     * Spell 6
     * Charges a sphericon for three units.
     */

    public static void sphericonSuperCharge() {

    }

    /**
     * Lucky Marksman: M
     * Spell 7
     * When cast, the following conditions apply to the player's next attack turn:
     * If this player lands their attack, they are healed half of the amount they deal
     * If this player does not hit their attack, AND the enemy hits theirs, the player takes double
     * damage
     */
    public void luckyMarksman() {

    }

    /**
     * Blinding Light: M
     * Spell 8
     * When cast,
     * 80 percent chance that your enemy's accuracy is decreased by 3 this turn
     * 20 percent chance of doing nothing
     */

    public void blindingLight(Character c) {

    }

    /**
     * Fireball: MA
     * Spell 9
     * Cast a flaming projectile with these properties:
     * Depending on the player's magic stat, deals 1.5-2 times normal damage
     * Is -2 accurate compared to your normal attack
     */

    public void fireball(Character c) {

    }

    /**
     * Poison: M
     * Spell 10
     * 60 percent chance of lowering your enemy's max health by 20 percent for 3 turns. After the
     * third turn, they will also be healed the number of hp their max hp was lowered by.
     * 40 percent chance of dealing 1 damage.
     */

    public void poison(Character c) {

    }

}

