package com.example.doome.text_baseddungeoncrawler;

public class Player extends Character {

    public static double negativeSecretChance;

    public int myPoints = 100;

    public byte[] spells = {0, 0, 0, 0, 0, 0, 0};

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
            spells[0] = 1;
            //SpellSelection.spellOneDesc.setText("Basic Heal: MA. Restores some health");
        } if (magicValue > 3) {
            spells[1] = 9;
            //SpellSelection.spellTwoDesc.setText("Fireball: MA. Does more damage than your regular attack, but slightly less accurate");
        } if (magicValue > 5) {
            spells[2] = 8;
            //SpellSelection.spellThreeDesc.setText("Blinding Light: M. 80 percent chance to reduce enemy accuracy this turn");
        } if (magicValue > 8) {
            spells[0] = 2;
            //SpellSelection.spellOneDesc.setText("Strong Heal: MA. Restores a lot of health");
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

    /**
     * Cast spell
     * Maps each number in the array to a spell.
     */

    public void castSpell(byte spell, Character c) {
        if (spell == 1){

        }
    }
}

