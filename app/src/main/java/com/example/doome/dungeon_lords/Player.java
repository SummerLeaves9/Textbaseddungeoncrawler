package com.example.doome.dungeon_lords;

public class Player extends Character {
    /**
     * array used to keep track of the stat buffs received throughout the game. Used for the
     * "play again" buttons
     */
    public byte[] buffs = {0, 0, 0, 0, 0, 0, 0};
    /**
     * Scalar for calculating player attack power
     */
    public final static double scalarFive = 1.075;
    /**
     * base hp for player
     */
    public final static byte playerBaseHP = 14;
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
     * The chance to successfully move fast/responsively enough to avoid the enemy attack upon
     * attempting to counterattack.
     */
    public double counterDodgeChance = .6;
    /**
     * Number of magic points for this character: directly influenced by Magic stat.
     */
    public byte mp;
    /**
     * The dynamic value for this character's magic points. This is initially set to mp, but is
     * lowered and raised throughout the course of the game.
     */
    public byte liveMP;
    /**
     * The damage a player deals on landing a counter attack. Set to 1.2 times their normal attack
     * power.
     */
    public byte counterAttackPower;
    /**
     * The damage a player deals on defending, and having the enemy miss. Set to .4 times their
     * normal attack power.
     */
    public byte defendJabAttackPower;
    /**
     * The sum of the player's stats. used for difficulty scaling in exploration/late game dungeons
     */
    public byte statSum;

    /**
     * The player's gold
     */
    public int myGold = 100;

    /**
     * The spells the player knows
     */
    public Spell[] spells = new Spell[7];

    /**
     * The items the player owns
     * index key:
     * 0: # magic potions
     * 1: # vitality potions
     * 2: # brain juice's
     * 3: # greed potions's
     * 4: piece of armor
     * 5: future item
     * 6: future item
     */
    public byte[] myItems = new byte[7];

    /**
     * The scalar to multiply the player's counterDodgeChance by based on their accuracy
     */
    public static final double counterDodgeChanceScalar = 1.05;

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
        randomizeSpells();
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
            if (isNegativeSecret > Gameplay.negativeSecretChance) {
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
     * Buffs one of the player's stats if they find a secret, or increases their stats by some other
     * means
     */
    public void buff(byte whichStat) {
        switch(whichStat) {
            case 0:
                buffs[0]++; strengthValue++; setAttackPower(); break;
            case 1:
                buffs[1]++; accuracyValue++; setHitChance(); break;
            case 2:
                buffs[2]++; defenseValue++; setHp(); break;
            case 3:
                buffs[3]++; agilityValue++; setDodgeChance(); setCounterDodgeChance(); break;
            case 4:
                buffs[4]++; intelligenceValue++; setSecretChance(); break;
            case 5:
                buffs[5]++; magicValue++; setMP(); break;
            case 6:
                buffs[6]++; luckValue++; setCritChance(); setLuckDodgeChance();
                setLuckSecretChance(); setDodgeChance(); setSecretChance(); break;
        }
        statSum++;
    }

    /**
     * Resets stats to original values, which were changed from calling buff(): used for the "play again"
     * buttons
     */
    public void unbuff() {
        for (byte i = 0; i < buffs.length; i++) {
            for (; buffs[i] > 0; buffs[i]--) {
                switch(i) {
                    case 0: strengthValue--; break;
                    case 1: accuracyValue--; break;
                    case 2: defenseValue--; break;
                    case 3: agilityValue--; break;
                    case 4: intelligenceValue--; break;
                    case 5: magicValue--; break;
                    case 6: luckValue--; break;
                }
                statSum--;
            }
        }
    }

    /**
     * Sets player hp.
     * overridden from character
     */
    public void setHp() {
        double scaled = 1;
        for (int i = 0; i < defenseValue; i++) {
            scaled *= scalarSix;
        }
        hp = (byte) (playerBaseHP * scaled);
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
            scaled *= scalarThree;
        }
        secretChance = luckSecretChance + (baseSecretChance * scaled);
    }

    /**
     * Sets this character's additional chance to entirely dodge an attack based on their luck stat,
     * which is to be added to total dodgeChance.
     * Helper function for setAllStats
     */
    public void setLuckDodgeChance() {
        double scaled = 1;
        for (int i = 0; i < luckValue; i++) {
            scaled *= scalarOne;
        }
        luckDodgeChance = (baseLuckDodgeChance * scaled);
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
                scaled *= critScalar;
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
     * Sets this character's chance to successfully move fast/responsively enough to avoid the enemy
     * attack upon attempting to counterattack.
     */
    public void setCounterDodgeChance() {
        double scaled = 1;
        for (byte i = 0; i < agilityValue; i++) {
            scaled *= counterDodgeChanceScalar;
        }
        counterDodgeChance *= scaled;
    }

    /**
     * Overridden from Character, since a player must also have their counterAttackPower and
     * defendJabAttackPower set.
     */
    public void setAttackPower() {
        double scaled = 1;
        for (int i = 0; i < strengthValue; i++) {
            scaled *= scalarFive;
        }
        attackPower = (byte)  (baseAttackPower * scaled);
        counterAttackPower = (byte) (1.4 * attackPower);
        defendJabAttackPower = (byte) (.4 * attackPower);
    }

    /**
     * Overridden from Character, since accessing the enemies' acc multiplier would make the player
     * too powerful
     */
    public void setHitChance() {
        double scaled = 1;
        for (int i = 0; i < accuracyValue; i++) {
            scaled *= scalarThree;
        }
        hitChance = baseHitChance * scaled;
    }

    /**
     * sets this player's stat sum
     */
    public void setStatSum() {
        statSum = (byte) (strengthValue + accuracyValue + defenseValue + agilityValue +
                intelligenceValue + magicValue + luckValue);
    }


    /**
     * Sets the player's first few spells randomly, the number of which depends on their magicValue.
     */
    public void randomizeSpells() {
        byte firstEmptySpellIndex = 1;
        spells[0] = new Spell((byte) 0);
        byte[] otherSpellIDs;
        //setting how many random spells the player should have based on magicValue
        if (magicValue > 8) {
            spells[0] = new Spell((byte) 1);
            otherSpellIDs = new byte[3];
            firstEmptySpellIndex = 4;
        } else if (magicValue > 6) {
            otherSpellIDs = new byte[2];
            firstEmptySpellIndex = 3;
        } else if (magicValue > 2) {
            otherSpellIDs = new byte[1];
            firstEmptySpellIndex = 2;
        } else {
            otherSpellIDs = new byte[0];
        }
        //setting random spell id's
        for (byte i = 0; i < otherSpellIDs.length; i++) {
            otherSpellIDs[i] = (byte) (2 + (Math.random() * 8));
            for (byte j = 0; j < i; j++) {
                if (otherSpellIDs[j] == otherSpellIDs[i]) {
                    i--;
                    break;
                }
            }
            if (otherSpellIDs[i] == 4 || otherSpellIDs[i] == 5) {
                i--;
            }
        }
        //setting the spells with the generated id's
        for (byte i = 0; i < otherSpellIDs.length; i++) {
            spells[i + 1] = new Spell(otherSpellIDs[i]);
            //if sphericon is one spell, setting the next available spell to sphericon charge
            if (otherSpellIDs[i] == 3) {
                spells[firstEmptySpellIndex] = new Spell((byte) 4);
                firstEmptySpellIndex++;
            }
        }
        for (byte i = firstEmptySpellIndex; i < (byte) spells.length; i++) {
            spells[i] = new Spell((byte) 127);
        }
        spells[6] = new Spell((byte) 10);
    }

    public void setAllStats() {
        //unbuff();
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
        setCounterDodgeChance();
        setStatSum();
        //randomizeSpells();
    }

}

