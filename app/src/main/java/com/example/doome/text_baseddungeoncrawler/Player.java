package com.example.doome.text_baseddungeoncrawler;

public class Player extends Character {

    public double negativeSecretChance;
    private boolean canBasicHeal = false;
    private boolean canStrongHeal = false;
    private boolean canUltimateBlock = false;
    private boolean canSphericon = false;
    private boolean canSphericonCharge = false;
    private boolean canSphericonSuperCharge = false;
    private boolean canLuckyMarksman = false;
    private boolean canBlindingLight = false;
    private boolean canFireball = false;
    private boolean canPoison = false;

    public int myPoints = 100;

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
            canBasicHeal = true;
            canFireball = true;
        } if (magicValue > 3) {
            canPoison = true;
        } if (magicValue > 5) {
            canStrongHeal = true;
        } if (magicValue > 8) {
            canSphericon = true;
            canSphericonCharge = true;
        }
    }
    /**
     * Determines whether a secret was found or not.
     */
    public int foundSecret() {
        double foundSecret = Math.random();
        double secretType = Math.random();
        if (foundSecret < secretChance) {
            if (secretType < 0.333) {
                return 1;
            } else if (secretType < 0.666) {
                return 2;
            } else {
                return 3;
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
     * Restores 35 percent of the player's health
     */

    public void basicHeal() {
        byte tobeHealed = this.hp;
        tobeHealed *= (byte) .35;
        this.liveHP += tobeHealed;
    }

    /**
     * Strong Heal: MA
     * Restores 65 percent of the player's health.
     */

    public void strongHeal() {
        byte tobeHealed = this.hp;
        tobeHealed *= (byte) .65;
        this.liveHP += tobeHealed;
    }

    /**
     * Ultimate Block: MA
     * Makes the player invincible this turn.
     */

    public static void ultimateBlock() {

    }

    /**
     * Sphericon: M
     * Summons a lil guy to fight for you. To make it deal damage, charge it with
     * either Sphericon Charge or Sphericon Supercharge. The more you charge it, the more damage
     * it will do. It will attack the first turn you don't give it charge.
     */

    public static void sphericon() {

    }

    /**
     * Sphericon Charge: M
     * Charges a sphericon for one unit.
     */

    public static void sphericonCharge() {

    }

    /**
     * Sphericon Supercharge: MA
     * Charges a sphericon for three units.
     */

    public static void sphericonSuperCharge() {

    }

    /**
     * Lucky Marksman: M
     * When cast, the following conditions apply to the player's next attack turn:
     * If this player lands their attack, they are healed half of the amount they deal
     * If this player does not hit their attack, AND the enemy hits theirs, the player takes double
     * damage
     */
    public void luckyMarksman() {

    }

    /**
     * Blinding Light: M
     * When cast,
     * 80 percent chance that your enemy's accuracy is decreased by 3 this turn
     * 20 percent chance of doing nothing
     */

    public void blindingLight(Character c) {

    }

    /**
     * Fireball: MA
     * Cast a flaming projectile with these properties:
     * Depending on the player's magic stat, deals 1.5-2 times normal damage
     * Is -2 accuate compared to your normal attack
     */

    public void fireball(Character c) {

    }

    /**
     * Poison: M
     * 60 percent chance of lowering your enemy's max health by 20 percent for 3 turns. After the
     * third turn, they will also be healed the number of hp their max hp was lowered by.
     * 40 percent chance of dealing 1 damage.
     */

    public void poison(Character c) {

    }

}

