package com.example.doome.text_baseddungeoncrawler;

//import java.util.Pair;

public class Player extends Character {

    private double negativeSecretChance = DifficultySelection.getFinalNegativeSecretChance();

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
        if (magicValue >= 0) {
            spells[0] = new Spell((byte) 0);
        } if (magicValue > 3) {
            spells[1] = new Spell((byte) 8);
        } if (magicValue > 5) {
            spells[2] = new Spell((byte) 7);
        } if (magicValue > 8) {
            spells[0] = new Spell((byte) 1);
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

