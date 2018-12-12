package com.example.doome.text_baseddungeoncrawler;

public class Character {
    /**
     * Number of stat points this character has for Accuracy.
     */
    public int accuracyValue;
    /**
     * Index of accuracyValue used for accessing the value when it's in an array.
     */
    public int accuracyIndex = 0;
    /**
     * Number of stat points this character has for Strength.
     */
    public int strengthValue;
    /**
     * Index of strengthValue used for accessing the value when it's in an array.
     */
    public int strengthIndex = 1;
    /**
     * Number of stat points this character has for Defense.
     */
    public int defenseValue;
    /**
     * Index of defenseValue used for accessing the value when it's in an array.
     */
    public int defenseIndex = 2;
    /**
     * Number of stat points this character has for Agility.
     */
    public int agilityValue;
    /**
     * Index of agilityValue used for accessing the value when it's in an array.
     */
    public int agilityIndex = 3;
    /**
     * Number of stat points this character has for Intelligence.
     */
    public int intelligenceValue;
    /**
     * Index of intelligenceValue used for accessing the value when it's in an array.
     */
    public int intelligenceIndex = 4;
    /**
     * Number of stat points this character has for Luck.
     */
    public int luckValue;
    /**
     * Index of luckValue used for accessing the value when it's in an array.
     */
    public int luckIndex = 5;
    /**
     * Number of hitpoints for this character: directly influenced by defense stat.
     */
    public int hp;
    /**
     * The dynamic value for this character's health. This is initially set to hp, but is lowered
     * and raised throughout the run of play.
     */
    public int liveHP;
    /**
     * The amount of damage this character will deal per hit, based on strengthValue.
     */
    public int attackPower;
    /**
     * The actual chance this character has of dodging an attack entirely, based on agility stat.
     */
    public double dodgeChance;
    /**
     * The actual chance that this character has of hitting their attack, based on accuracy stat.
     */
    public double hitChance;
    /**
     * The chance that this character has of finding a secret upon entering a "look" command, based
     * on intelligence stat.
     */
    public double secretChance;
    /**
     * The chance this character has of landing a critical hit upon hitting their attack, based on
     * luck stat.
     */
    public double critChance;
    /**
     * The additional chance that this character has of dodging an attack entirely, based on luck
     * stat, and is to be added to dodgeChance.
     */
    public double luckDodgeChance;
    /**
     * The additional chance that this character has of finding a secret upon using the "look"
     * command, based on luck stat, and is to be added to secretChance.
     */
    public double luckSecretChance;
    /**
     * The name for this character!
     */
    public String name;
    /**
     * The name of this character's attack method
     */
    public String weaponName;
    /**
     * If this character lands a critical hit, their damage output is multiplied by this value.
     */
    public static double critMultiplier = 1.8;
    /**
     * The base hp for all characters
     */
    public static int baseHP = 10;
    /**
     * The base attackPower for all characters
     */
    public static int baseAttackPower = 4;
    /**
     * The base chance to land a critical hit for all characters. Only for characters with luckValue
     * above 0, otherwise their critChance is 0.
     */
    public static double baseCritChance = .07;
    /**
     * The base hitChance for all characters
     */
    public static double baseHitChance = .5;
    /**
     * The base dodge chance for all characters
     */
    public static double baseDodgeChance = .06;
    /**
     * The base additional dodge chance granted by your luck value for all characters
     */
    public static double baseLuckDodgeChance = .02;
    /**
     * The base chance that a player finds a secret upon entering the "look" command, based on their
     * intelligence stat.
     */
    public static double baseSecretChance = .08;
    /**
     * The base additional chance to find a secret upon entering the "look" command based on the
     * intelligence stat.
     */
    public static double baseLuckSecretChance = .02;
    /**
     * The base luck for all characters. Set to 0 by default in case this character's
     * luckValue is 0, which can happen if this character is an enemy, or the player invests
     * 0 points into their luck stat.
     */
    public final static double baseAllLuckChance = 0;
    /**
     * The most generous value to scale base calculable stats depending on a character's stats
     * Used in calculating luckSecretChance, luckDodgeChance, critChance, hp, dodgeChance, and
     * secretChance
     */
    public final static double scalarOne = 1.2;
    /**
     * The middle of the road value to scale base calculable stats depending on a character's stats
     * Used in calculating attackPower
     */
    public final static double scalarTwo = 1.12;
    /**
     * The least generous value to scale base calculable stats depending on a character's stats
     * Used in calculating hitChance
     */
    public final static double scalarThree = 1.065;
    /**
     * The value to multiply scalarOne by, then add to the base hp to get the final hp value
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
     * Sets this character's hitpoints based on their defense stat.
     * Helper function for setAllStats.
     */
    public void setHp() {
        double scaled = 1;
        for (int i = 0; i < defenseValue; i++) {
            scaled *= scalarOne;
        }
        hp = (int) Math.round(baseHP + (hpScalar * scaled));
        System.out.println(hp);
    }
    /**
     * Sets this character's damage dealt per hit based on their strength stat.
     * Helper function for setAllStats.
     */
    public void setAttackPower() {
        double scaled = 1;
        for (int i = 0; i < strengthValue; i++) {
            scaled *= scalarTwo;
        }
        attackPower = (int) Math.round(baseAttackPower * scaled);
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
        dodgeChance = luckDodgeChance + (baseDodgeChance * scaled);
    }
    /**
     * Sets this character's chance of finding a secret upon using the "look" command, based on
     * their intelligence stat.
     * Helper function for setAllStats.
     */
    public void setSecretChance() {
        double scaled = 1;
        for (int i = 0; i < intelligenceValue; i++) {
            scaled *= scalarOne;
        }
        secretChance = luckSecretChance + (baseSecretChance * scaled);
    }
    /**
     * Sets this character's chance of landing their attack, based on their accuracy stat.
     * Helper function for setAllStats.
     */
    public void setHitChance() {
        double scaled = 1;
        for (int i = 0; i < accuracyValue; i++) {
            scaled *= scalarThree;
        }
        hitChance = baseHitChance;
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
     * Sets this character's additional chance to find a secret based on their luck stat.
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
    /**
     * Uses the stats of both characters to determine if this character landed a hit on other
     * @param other the character that is being attacked
     */
    public int determineHit(Character other) {
        double otherDodgeResult = Math.random();
        double thisHitResult = Math.random();
        double thisCritResult = Math.random();
        if (other.dodgeChance < otherDodgeResult && this.hitChance < thisHitResult) {
            if (this.critChance < thisCritResult) {
                other.liveHP -= (int) (this.attackPower * critMultiplier);
                return (int) (this.attackPower * critMultiplier);
            } else {
                other.liveHP -= this.attackPower;
                return this.attackPower;
            }
        }
        return 0;
    }
    /**
     * Determines whether a secret was found or not.
     */
    public int foundSecret() {
        double foundSecret = Math.random();
        double secretType = Math.random();
        if (foundSecret < secretChance) {
            if (secretType < 0.33) {
                return 1;
            } else if (secretType < 0.66) {
                return 2;
            } else {
                return 3;
            }
        } else {
            return 0;
        }
    }

    /**
     * Sets all stats for this character in the correct order so that none of the helper functions
     * break due to a lack of a needed variable being set.
     */
    public void setAllStats() {
        setLuckDodgeChance();
        setDodgeChance();
        setLuckSecretChance();
        setSecretChance();
        setCritChance();
        setHp();
        liveHP = hp;
        setHitChance();
        setAttackPower();
    }
}
