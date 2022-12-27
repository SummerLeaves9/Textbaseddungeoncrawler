package com.example.doome.dungeon_lords;

public class Character {
    /**
     * Number of stat points this character has for Accuracy.
     */
    public byte accuracyValue;
    /**
     * Number of stat points this character has for Strength.
     */
    public byte strengthValue;
    /**
     * Number of stat points this character has for Defense.
     */
    public byte defenseValue;
    /**
     * Number of stat points this character has for Agility.
     */
    public byte agilityValue;
    /**
     * Number of stat points this character has for Intelligence.
     */
    public byte intelligenceValue;
    /**
     * Number of stat points this character has for Magic.
     */
    public byte magicValue;
    /**
     * Number of stat points this character has for Luck.
     */
    public byte luckValue;
    /**
     * Number of hitpoints for this character: directly influenced by defense stat.
     */
    public byte hp;
    /**
     * The dynamic value for this character's health. This is initially set to hp, but is lowered
     * and raised throughout the course of the game.
     */
    public byte liveHP;
    /**
     * The amount of damage this character will deal per hit, directly influenced by strengthValue.
     */
    public byte attackPower;
    /**
     * The actual chance this character has of dodging an attack entirely, directly influenced by
     * agility stat.
     */
    public double dodgeChance;
    /**
     * The actual chance this character has of hitting their attack, directly influenced by
     * accuracy stat.
     */
    public double hitChance;
    /**
     * The chance that this character has of finding a secret upon entering a "look" command
     * directly influenced by intelligence stat.
     */
    public double secretChance;
    /**
     * The chance this character has of landing a critical hit upon hitting their attack
     * directly influenced by luck stat.
     */
    public double critChance;

    /**
     * The name for this character
     */
    public String name;
    /**
     * The name of this character's attack method
     */
    public String weaponName;
    /**
     * If this character lands a critical hit, their damage output is multiplied by this value.
     */
    public static final double critMultiplier = 1.8;
    /**
     * The base hp for enemies
     */
    public static final int baseHP = 10;
    /**
     * The base attackPower for all characters
     */
    public static final double baseAttackPower = 4.4;
    /**
     * The scalable double used for changing characters' attack power more precisely
     */
    public double doubleAttackPower;
    /**
     * The base chance to land a critical hit for all characters. Only for characters with luckValue
     * above 0, otherwise their critChance is 0.
     */
    public static final double baseCritChance = .06;
    /**
     * The base hitChance for all characters
     */
    public static final double baseHitChance = .55;
    /**
     * The base dodge chance for all characters
     */
    public static final double baseDodgeChance = .06;
    /**
     * The base additional dodge chance granted by your luck value for all characters
     */
    public static final double baseLuckDodgeChance = .02;
    /**
     * The base chance that a player finds a secret upon entering the "look" command, based on their
     * intelligence stat.
     */
    public static final double baseSecretChance = .23;
    /**
     * The base additional chance to find a secret upon entering the "look" command based on the
     * intelligence stat.
     */
    public static final double baseLuckSecretChance = .02;
    /**
     * The base luck for all characters. Set to 0 by default in case this character's
     * luckValue is 0, which can happen if this character is an enemy, or the player invests
     * 0 points into their luck stat.
     */
    public final static double baseAllLuckChance = 0;
    /**
     * The most generous value to scale base calculable stats depending on a character's stats
     * Used in calculating luckSecretChance, luckDodgeChance, dodgeChance
     */
    public final static double scalarOne = 1.2;
    /**
     * scalar used for determining critChance
     */
    public final static double critScalar = 1.23;
    /**
     * The middle of the road value to scale base calculable stats depending on a character's stats
     * Used in calculating attackPower for enemies only, and hp
     */
    public final static double scalarTwo = 1.1;
    /**
     * The least generous value to scale base calculable stats depending on a character's stats
     * Used in calculating hitChance for player
     */
    public final static double scalarThree = 1.045;
    /**
     * Scalar for calculating hp
     */
    public final static double scalarFour = 1.155;
    /**
     * scalar used in calculating hitchance for enemies
     */
    public final static double scalarFive = 1.07;
    /**
     * scalar used in calculating hp for player
     */
    public final static double scalarSix = 1.08;
    /**
     * The value to multiply scalarOne^defenseValue by, then add to the base hp to get the final hp
     * value
     */
    public final static int hpScalar = 7;
    /**
     * Maximum value for any stat
     */
    public final static int statMax = 10;
    /**
     * Minimum Value for any stat
     */
    public final static int statMin = 0;
    /**
     * Boolean that tracks whether Hex of Draining was cast on this enemy
     */
    public boolean hasHexOfDraining = false;

    /**
     * Sets this character's hitpoints based on their defense stat.
     * This version only applies to Enemy's, as it is overridden in Player
     * Helper function for setAllStats.
     */
    public void setHp() {
        double scaled = 1;
        for (int i = 0; i < defenseValue; i++) {
            scaled *= scalarFour;
        }
        hp = (byte) Math.round(baseHP + (hpScalar * scaled));
        if (defenseValue == 0) {
            hp -= hpScalar;
        }
    }

    /**
     * Sets this character's damage dealt upon hit based on their strength stat.
     * Helper function for setAllStats.
     */
    public void setAttackPower() {
        double scaled = 1;
        for (int i = 0; i < strengthValue; i++) {
            scaled *= scalarTwo;
        }
        doubleAttackPower = baseAttackPower * scaled;
        attackPower = (byte)  doubleAttackPower;
    }

    /**
     * Used for certain spells to scale characters' attack power (across attack, counter, and
     * defend)
     */
    public void scaleAttackPower(double scalar) {
        double scaled = scalar * doubleAttackPower;
        attackPower = (byte) scaled;
    }

    /**
     * Sets this character's chance to entirely dodge an attack, assuming the attacker lands their
     * hit
     * Helper function for setAllStats.
     */
    public void setDodgeChance() {
        double scaled = 1;
        for (int i = 0; i < agilityValue; i++) {
            scaled *= scalarOne;
        }
        dodgeChance = (baseDodgeChance * scaled);
    }
    /**
     * Sets this character's chance of finding a secret upon using the "look" command, based on
     * their intelligence stat.
     * Helper function for setAllStats.
     */
    public void setSecretChance() {
        double scaled = 1;
        for (int i = 0; i < intelligenceValue; i++) {
            scaled *= scalarTwo;
        }
        secretChance = (baseSecretChance * scaled);
    }
    /**
     * Sets this character's chance of landing their attack, based on their accuracy stat.
     * This version only applies to Enemy's, as Player overrides this method
     * Helper function for setAllStats.
     */
    public void setHitChance() {
        double scaled = 1;
        for (int i = 0; i < accuracyValue; i++) {
            scaled *= scalarFive;
        }
        hitChance = baseHitChance * scaled;
    }
    /**
     * Uses the stats of both characters to determine if this character landed a hit on the other.
     * @param other the character that is being attacked.
     */
    public int determineHit(Character other) {
        double otherDodgeResult = Math.random();
        double thisHitResult = Math.random();
        double thisCritResult = Math.random();
        if (other.dodgeChance < otherDodgeResult && this.hitChance > thisHitResult) {
            if (thisCritResult < this.critChance) {
                other.liveHP -= (byte) (this.attackPower * critMultiplier);
                return (byte) (this.attackPower * critMultiplier);
            } else {
                other.liveHP -= this.attackPower;
                return this.attackPower;
            }
        }
        return 0;
    }
    /**
     * Uses the stats of both characters to determine if this character landed a hit on the other.
     * Unlike determineHit, this method does not actually deal damage, it just returns the
     * damage this character would have dealt.
     * @param other the character that is being attacked.
     */
    public int determineHitNoDamage(Character other) {
        double otherDodgeResult = Math.random();
        double thisHitResult = Math.random();
        double thisCritResult = Math.random();
        if (other.dodgeChance < otherDodgeResult && this.hitChance > thisHitResult) {
            if (thisCritResult < this.critChance) {
                return (byte) (this.attackPower * critMultiplier);
            } else {
                return this.attackPower;
            }
        }
        return 0;
    }


    /**
     * Sets all stats for this character in the correct order so that none of the helper functions
     * break due to a lack of a needed variable being set.
     */
    public void setAllStats() {
        setDodgeChance();
        setSecretChance();
        setHp();
        liveHP = hp;
        setHitChance();
        setAttackPower();
    }
}
