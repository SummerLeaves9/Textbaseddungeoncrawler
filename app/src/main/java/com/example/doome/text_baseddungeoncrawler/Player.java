package com.example.doome.text_baseddungeoncrawler;

public class Player extends Character {
    /**
     * limit for stat points a player can give themselves
     */
    public final int statPoints = 30;

    public final int randomlyGeneratedStatCutoff = 6;

    public int myPoints = 0;

    /**
     * The purpose of this empty constructor is for the case where the player decides
     * to trust an algorithm to determine their stats.
     */
    public Player(String setName, String setWeaponName) {
        name = setName;
        weaponName = setWeaponName;
        //Randomly determines the stats of a player if they choose to do so.
        int statPointsUsed = 0;
        int strengthRandom = (int) (statMax * Math.random());
        strengthValue = strengthRandom;
        statPointsUsed += strengthRandom;
        int accuracyRandom = (int) (statMax * Math.random());
        accuracyValue = accuracyRandom;
        statPointsUsed += accuracyRandom;
        int defenseRandom = (int) (statMax * Math.random());
        defenseValue = defenseRandom;
        statPointsUsed += defenseRandom;
        int agilityRandom = (int) (statMax * Math.random());
        agilityValue = agilityRandom;
        statPointsUsed += agilityRandom;
        int intelligenceRandom = (int) (statMax * Math.random());
        intelligenceValue = intelligenceRandom;
        statPointsUsed += intelligenceRandom;
        int luckRandom = (int) (statMax * Math.random());
        luckValue = luckRandom;
        statPointsUsed += luckRandom;
        int[] allStats = {accuracyValue, strengthValue, defenseValue,
                agilityValue, intelligenceValue, luckValue};
        if (statPointsUsed < 30) {
            int minOfStats = statMax;
            int smallestStatIndex = 0;
            for (; statPointsUsed < statPoints; statPointsUsed++) {
                for (int i = 0; i < allStats.length; i++) {
                    if (allStats[i] < minOfStats) {
                        minOfStats = allStats[i];
                        smallestStatIndex = i;
                    }
                }
                if (smallestStatIndex == accuracyIndex) {
                    accuracyValue++;
                } else if (smallestStatIndex == strengthIndex) {
                    strengthValue++;
                } else if (smallestStatIndex == defenseIndex) {
                    defenseValue++;
                } else if (smallestStatIndex == agilityIndex) {
                    agilityValue++;
                } else if (smallestStatIndex == intelligenceIndex) {
                    intelligenceValue++;
                } else {
                    luckValue++;
                }
            }
        } else if (statPointsUsed > 30) {
            int maxOfStats = statMin;
            int biggestStatIndex = 0;
            for (; statPointsUsed > statPoints; statPointsUsed--) {
                for (int i = 0; i < allStats.length; i++) {
                    if (allStats[i] > maxOfStats) {
                        maxOfStats = allStats[i];
                        biggestStatIndex = i;
                    }
                }
                if (biggestStatIndex == accuracyIndex) {
                    accuracyValue--;
                } else if (biggestStatIndex == strengthIndex) {
                    strengthValue--;
                } else if (biggestStatIndex == defenseIndex) {
                    defenseValue--;
                } else if (biggestStatIndex == agilityIndex) {
                    agilityValue--;
                } else if (biggestStatIndex == intelligenceIndex) {
                    intelligenceValue--;
                } else {
                    luckValue--;
                }
            }
        }
    }
    public Player(final int setStrength, final int setAccuracy, final int setDefense,
                  final int setAgility, final int setIntelligence, final int setLuck,
                  final String setName, final String setAttackName) {
        if (setAccuracy + setAgility + setStrength + setDefense + setIntelligence + setLuck
                == statPoints) {
            strengthValue = setStrength;
            accuracyValue = setAccuracy;
            defenseValue = setDefense;
            agilityValue = setAgility;
            intelligenceValue = setIntelligence;
            luckValue = setLuck;
            name = setName;
            weaponName = setAttackName;
        }
    }
}
