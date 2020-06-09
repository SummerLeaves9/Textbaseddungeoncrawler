package com.example.doome.text_baseddungeoncrawler;

//import java.util.Pair;

public class Player extends Character {

    private double negativeSecretChance = DifficultySelection.getFinalNegativeSecretChance();
    /**
     * The additional chance that this character has of dodging an attack entirely, directly
     * influenced by luck stat, and is to be added to dodgeChance.
     */
    public double luckDodgeChance;
    /**
     * The additional chance that this character has of finding a secret upon using the "look"
     * command, directly influenced by luck stat, and is to be added to secretChance.
     */
    public double luckSecretChance;
    /**
     * Number of magic points for this character: directly influenced by Magic stat.
     */
    public byte mp;
    /**
     * The dynamic value for this character's magic points. This is initially set to mp, but is
     * lowered and raised throughout the course of the game.
     */
    public byte liveMP;

    public int myPoints = 100;

    public Spell[] spells = new Spell[7];

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
        byte firstSpellIndex = 1;
        spells[0] = new Spell((byte) 0);
        if (magicValue > 3) {
            spells[1] = new Spell((byte) 8);
            firstSpellIndex++;
        } if (magicValue > 5) {
            spells[2] = new Spell((byte) 7);
            firstSpellIndex++;
        } if (magicValue > 8) {
            spells[0] = new Spell((byte) 1);
        }
        for (byte i = firstSpellIndex; i < (byte) spells.length; i++) {
            spells[i] = new Spell((byte) 127);
        }
    }

    public Player() {
        hp = 10;
        liveHP = 10;
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
     * Sets this player's magic points based on their magic stat.
     * Helper function for setAllStats.
     */
    public void setMP() {
        mp = (byte) (10 + (5 * magicValue));
    }
    /**
     * Player class-overridden method
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
     * Player class-overridden method
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

    /**
     * Sets this character's
     */
    public void setNegativeSecretDodgeChance() {

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
        setNegativeSecretDodgeChance();
    }



}

