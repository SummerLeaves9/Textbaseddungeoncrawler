package com.example.doome.dungeon_lords;

public class Spell {

    //public static Player thisPlayer = TitleScreen.tempPlayer;

    public static boolean usedVision = false;

    public byte id;

    public byte mpCost;

    public String name;

    public String description;

    public String extraCastSuccessMessage;

    public String spellFailMessage;

    public String statusEffect;

    public String lingeringEffectText;

    public String effectEndsText;

    public boolean castFail = false;

    public boolean hasEffect;

    public boolean enemyEffect;

    public boolean isCombatSpell;

    public boolean isExplorationSpell;

    public boolean shouldUpdateSpellNum;

    public final String[][] allSpells = {
            {"Basic Heal", "MA, Cost: 4 MP. Restores some health", "You recover 10 HP. ",
                    "(spell doesn't fail)", "(no status effect)"},
            {"Strong Heal", "MA, Cost: 9 MP. Restores a lot of health", "You recover 18 HP. ",
                    "(spell doesn't fail)", "(no status effect)"},
            {"Ultimate Block", "M, Cost: 15 MP. Ensures you won't take damage this turn.",
                    " You are completely protected from damage this turn. ", "(spell doesn't " +
                    "fail)", "(no status effect)"},
            {"Sphericon","M, Cost: 5 MP. Summons a lil' guy to fight for you, but only by " +
                    "charging it.", " He's here, lads!! ", "You already have a sphericon active! " +
                    "But you summon a second one anyway, and the first one vanishes with a smile " +
                    "and a wave. ",
                    "Sphericon active. Charge: ", "Sphericon was not charged, so it unleashes a " +
                    "powerful blast! For " + 0 + " damage. ", "Your Sphericon turret stands " +
                    "ready. "},
            {"Sphericon Charge","M, Cost: 1 MP. Charges your Sphericon for 1 unit.",
                    "(empty)", "You don't have a sphericon out! ", "Sphericon active. Charge: "},
            {"Sphericon Supercharge","MA, Cost: 4 MP. Charges your Sphericon for 3 units.",
                    "(empty)", "You don't have a sphericon out! ", "Sphericon active. Charge: "},
            {"Lucky Marksman","M, Cost: 5 MP. You deal double damage this turn! But if you miss, " +
                    "you take double what your enemies deal.", " Effect is active for this turn. ",
                    "(spell doesn't fail)", "Lucky Marksman", "Lucky Marksman wears off. "},
            {"Blinding Light","M, Cost: 3 MP. 80 percent chance to reduce your enemy's accuracy " +
                    "by 4 this turn.", "(empty)", "(empty)", "Blinded: Acc down", "Blinding " +
                    "Light wears off."},
            {"Fireball","MA, Cost: 6 MP. Cast a flaming projectile whose strength is proportional" +
                    " to your magic stat (from 1.5-2.5 times your base attack power). Less " +
                    "accurate than your direct attack", "(empty)", " The " +
                    "fireball misses... ", "(no status effect)"},
            {"Poison","M, Cost: 7 MP. Deals 15 percent of your enemy's max health for 3 turns" +
                    " but the enemy is healed 45 percent after 3 turns.", "(empty)", " The " +
                    "purple fumes come and go, dealing only 1 damage. ", "Poisoned", "The effect " +
                    "of Poison wears off, and the enemy regains the health they lost. ", "(empty)"},
            {"Vision", "N/A, Cost: 10 MP. Guarantees you to find a secret in a dungeon if the room" +
                    " is searchable. While exploring, it also lets you see 2 tiles away no " +
                    "matter if you're in jungle or not ", "You feel way more in touch with your " +
                    "environment. ", "(spell doesn't fail)", "(no status effect)"}
    };

    public static final byte[] allMPCosts = {4, 9, 15, 5, 1, 4, 5, 3, 6, 7, 10};

    public static final boolean[][] allSpellBools = {
            {true, true, false, false, true},
            {true, true, false, false, true},
            {true, false, false, false, true},
            {true, false, false, true, true},
            {true, false, false, true, false},
            {true, false, false, true, false},
            {true, false, false, true, true},
            {true, false, true, true, true},
            {true, false, false, false, true},
            {true, false, true, true, true},
            {false, true, false, false, true}
    };

    /**
     * All-purpose constructor.
     * @param setId number which identifies spell
     */

    public Spell(byte setId) {
        id = setId;
        if (id == 127) {
            name = "empty spell";
            return;
        }
        isCombatSpell = allSpellBools[setId][0];
        isExplorationSpell = allSpellBools[setId][1];
        enemyEffect = allSpellBools[setId][2];
        hasEffect  = allSpellBools[setId][3];
        shouldUpdateSpellNum = allSpellBools[setId][4];
        mpCost = allMPCosts[setId];
        name = allSpells[setId][0];
        description = allSpells[setId][1];
        extraCastSuccessMessage = allSpells[setId][2];
        if ((id < 10 && id > 6) || id == 3) {
            spellFailMessage = allSpells[setId][3];
        }
        if (hasEffect) {
            statusEffect = allSpells[setId][4];
            if (shouldUpdateSpellNum) {
                effectEndsText = allSpells[setId][5];
            }
        }
        if (id == 3 || id == 9) {
            lingeringEffectText = allSpells[setId][6];
        }
    }

    /**
     * Method called externally when the desired effect of a spell should be executed.
     * This version of the method is used for Gameplay
     * @param p the player being affected (not always used)
     * @param e the enemy being affected (not always used)
     */
    public void spellCast(Player p, Character e) {
        switch (id) {
            case 127:
                return;
            case 0:
                basicHeal(p);
                break;
            case 1:
                strongHeal(p);
                break;
            case 2:
                ultimateBlock();
                break;
            case 3:
                sphericon();
                break;
            case 4:
                sphericonCharge();
                break;
            case 5:
                sphericonSuperCharge();
                break;
            case 6:
                luckyMarksman();
                break;
            case 7:
                blindingLight(p, e);
                break;
            case 8:
                fireball(p, e);
                break;
            case 9:
                poison(e);
                break;
            case 10:
                vision();
                break;
        }
    }

    /**
     * Method called externally when the desired effect of a spell should be executed.
     * This version of the method is used in Exploration
     * @param p the player being affected (not always used)
     */
    public void spellCast(Player p) {
        if (id == 127) {
            return;
        }
        if (id == 0) {
            basicHeal(p);
        } else if (id == 1) {
            strongHeal(p);
        } else if (id == 10) {
            vision();
        }
    }

    /**
     * General way to call the helper methods that reverse spell effects once the spell is over
     * @param e Enemy or player that
     */
    public void reverseSpellCast(Character e) {
        if (id == 127) {
            return;
        }
        if (id == 3) {
            effectEndsText = "Sphericon was not charged, so it unleashes a " +
                    "powerful blast! For " + sphericonBlast(e) + " damage. ";
        } else if (id == 6) {
            luckyMarksmanReverse();
        } else if (id == 7) {
            blindingLightReverse(e);
        } else if (id == 9) {
            poisonReverse(e);
        }
    }
    /**
     * General way to call spell effects that linger once per turn
     */
    public void lingeringEffect(Character pe) {
        if (id == 3) {

        } else if (id == 9) {
            poisonDamager(pe);
        }
    }

    /**
     * Method called externally for when a spell is cast, and the console output needs to be updated
     * accordingly.
     */
    public String getExtraText() {
        if (id == 127) {
            return "";
        }
        if (castFail) {
            updateSpellFailMessage();
            return spellFailMessage;
        }
        updateExtraCastSuccessMessage();
        return extraCastSuccessMessage;
    }

    /**
     * Updates the effect text
     */
    private void updateStatusEffect() {
        if (id == 4 || id == 5) {
            statusEffect = "Sphericon active. Charge: " + Gameplay.sphericonCharge;
        }
    }

    /**
     * Method called externally for when a spell is cast, and the hud needs to be updated
     * accordingly.
     */
    public String getEffect() {
        updateStatusEffect();
        return statusEffect;
    }

    /**
     * Properly updates dynamic values used in spellFailMessage
     */
    private void updateSpellFailMessage() {
        if (id != 7) {
            return;
        } else {
            spellFailMessage = " The " + Gameplay.thisRoom.numberOne.name + " was unaffected. ";
        }
    }

    /**
     * Properly updates dynamic values used in extraCastSuccessMessage
     */
    private void updateExtraCastSuccessMessage() {
        if (id == 127 || id == 6 || id < 4) {
            return;
        }
        if (id == 4) {
            //sphericonCharge(p);
            extraCastSuccessMessage = " Charge: " + Gameplay.sphericonCharge + ". ";
        } else if (id == 5) {
            //sphericonSuperCharge(p);
            extraCastSuccessMessage = " Charge: " + Gameplay.sphericonCharge + ". ";
        } else if (id == 7) {
            //blindingLight(p, e);
            extraCastSuccessMessage = " The " + Gameplay.thisRoom.numberOne.name + " was " +
                    "successfully blinded! ";
        } else if (id == 8) {
            //fireball(p, e);
            extraCastSuccessMessage = " The fireball hits for " +
                    (byte) (EnterNames.thisPlayer.attackPower * (1.5 + (.1 * EnterNames.thisPlayer.magicValue))) +
                    " damage! ";
        } else if (id == 9) {
            //poison(p, e);
            extraCastSuccessMessage = " The " +
                    Gameplay.thisRoom.numberOne.name + " was successfully poisoned! ";
        }
    }

    private void updateLingeringEffectText(Character pe) {
        if (id == 9) {
            lingeringEffectText = pe.name + " takes " + (byte) (pe.hp * .15) + " poison damage. ";
        }
    }

    public String getLingeringEffectText(Character pe) {
        updateLingeringEffectText(pe);
        return lingeringEffectText;
    }

    /**
     * Basic Heal: MA
     * Spell 1
     * Restores 10 health
     */

    private void basicHeal(Player p) {
        byte tobeHealed = 10;
        if ((p.liveHP + tobeHealed) > p.hp) {
            p.liveHP = p.hp;
        } else {
            p.liveHP += tobeHealed;
        }
        subtractMP();
        if (Gameplay.isBattling) {
            Gameplay.attackTurnAdvantage = false;
        }
    }

    /**
     * Strong Heal: MA
     * Spell 2
     * Restores 18 health
     */

    private void strongHeal(Player p) {
        double tobeHealed = 18;
        if ((p.liveHP + tobeHealed) > p.hp) {
            p.liveHP = p.hp;
        } else {
            p.liveHP += tobeHealed;
        }
        subtractMP();
        if (Gameplay.isBattling) {
            Gameplay.attackTurnAdvantage = false;
        }
    }

    /**
     * Ultimate Block: M
     * Spell 3
     * Makes the player invincible this turn.
     */

    private void ultimateBlock() {
        Gameplay.hasBlocked = true;
        subtractMP();
    }

    /**
     * Sphericon: M
     * Spell 4
     * Summons a lil guy to fight for you. To make it deal damage, charge it with
     * either Sphericon Charge or Sphericon Supercharge. The more you charge it, the more damage
     * it will do. It will attack the first turn you don't give it charge.
     */

    private void sphericon() {
        if (Gameplay.hasSphericon) {
            castFail = true;
        } else {
            Gameplay.hasSphericon = true;
            Gameplay.hasCharged = true;
            subtractMP();
        }
    }

    /**
     * Sphericon blast: when the player doesn't charge the sphericon, it will unleash a blast
     * whose strength is proportional to the number of charges the player has stored.
     */
    private byte sphericonBlast(Character e) {
        byte dealtDamage = 0;
        for (; Gameplay.sphericonCharge > 0; Gameplay.sphericonCharge--) {
            dealtDamage += 2;
            if (Gameplay.sphericonCharge > 3) {
                dealtDamage++;
            }
            if (Gameplay.sphericonCharge > 6) {
                dealtDamage++;
            }
        }
        e.liveHP -= dealtDamage;
        return dealtDamage;
    }
    /**
     * Sphericon Charge: M
     * Spell 5
     * Charges a sphericon for one unit.
     */

    private void sphericonCharge() {
        if (Gameplay.hasSphericon) {
            castFail = false;
            Gameplay.hasCharged = true;
            Gameplay.sphericonCharge++;
            subtractMP();
        } else {
            castFail = true;
        }
    }

    /**
     * Sphericon Supercharge: MA
     * Spell 6
     * Charges a sphericon for three units.
     */

    private void sphericonSuperCharge() {
        Gameplay.hasCharged = true;
        Gameplay.sphericonCharge += 3;
        subtractMP();
        if (Gameplay.isBattling) {
            Gameplay.attackTurnAdvantage = false;
        }
    }

    /**
     * Lucky Marksman: M
     * Spell 7
     * When cast, the following conditions apply to the player's next attack turn:
     * If this player lands their attack, they are healed half of the amount they deal
     * If this player does not hit their attack, AND the enemy hits theirs, the player takes double
     * damage
     */
    private void luckyMarksman() {
        Gameplay.usedLuckyMarksman = true;
        Gameplay.myEffectCounter = 0;
        subtractMP();
    }

    /**
     * Lucky Marksman helper: reverses the effect after one turn
     */
    private void luckyMarksmanReverse() {
        Gameplay.usedLuckyMarksman = false;
    }

    /**
     * Blinding Light: M
     * Spell 8
     * When cast,
     * 80 percent chance that your enemy's accuracy is decreased by 5 this turn
     * 20 percent chance of doing nothing
     */

    private void blindingLight(Player p, Character e) {
        double blinded = Math.random();
        byte initAcc = -1;
        if (blinded < .8) {
            initAcc = e.accuracyValue;
            if (e.accuracyValue < 5) {
                e.accuracyValue = 0;
            } else {
                e.accuracyValue -= 5;
            }
            e.setHitChance();
            castFail = false;
            Gameplay.initStat = initAcc;
            //Gameplay.statusEffect = true;
            Gameplay.enemyEffectCounter = 0;
        } else {
            castFail = true;
        }
        subtractMP();
    }

    /**
     * Blinding Light helper: to undo the effect once the turn is over
     */
    public void blindingLightReverse(Character e) {
        e.accuracyValue = Gameplay.initStat;
        e.setHitChance();
    }

    /**
     * Fireball: MA
     * Spell 9
     * Cast a flaming projectile with these properties:
     * Depending on the player's magic stat, deals 1.5-2.5 times normal damage
     * Is -2 accurate compared to your normal attack
     */

    private void fireball(Player p, Character e) {
        int fireballAttackPower = (int) (p.attackPower * (1.5 + (.1 * p.magicValue)));
        double otherDodgeResult = Math.random();
        double thisHitResult = Math.random();

        //temporarily decreases player accuracy for casting the fireball
        byte initAcc = p.accuracyValue;
        if (initAcc < 2) {
            p.accuracyValue = 0;
        } else {
            p.accuracyValue -= 2;
        }
        p.setHitChance();
        //determines hit
        int dealtDamage = 0;
        if (e.dodgeChance < otherDodgeResult && p.hitChance > thisHitResult) {
            dealtDamage = fireballAttackPower;
            e.liveHP -= dealtDamage;
        }
        if (dealtDamage != 0) {
            castFail = false;
        } else {
            castFail = true;
        }
        if (Gameplay.isBattling) {
            Gameplay.attackTurnAdvantage = false;
        }
        //increases player accuracy back to original level
        p.accuracyValue = initAcc;
        p.setHitChance();
        subtractMP();
    }

    /**
     * Poison: M
     * Spell 10
     * 60 percent chance of lowering your enemy's max health by 20 percent for 3 turns. After the
     * third turn, they will also be healed the number of hp their max hp was lowered by.
     * 40 percent chance of dealing 1 damage.
     */

    private void poison(Character e) {
        double castChance = Math.random();
        if (castChance < .6) {
            Gameplay.enemyEffectCounter = 3;
            //poisonDamager(e);
            castFail = false;
        } else {
            castFail = true;
            e.liveHP -= 1;
        }
        subtractMP();
    }

    /**
     * Poison helper method: the code that actually does the damage. executed 3 times then
     */

    private void poisonDamager(Character e) {
        byte dealtDamage = e.hp;
        dealtDamage *= .15;
        e.liveHP -= dealtDamage;
    }

    /**
     * Poison helper method: heals the enemy for all the damage poison dealt, in the case that
     * the enemy is still alive after 3 turns
     */

    private void poisonReverse(Character e) {
        byte toHeal = e.hp;
        toHeal *= .15;
        toHeal *= 3;
        e.liveHP += toHeal;
    }

    /**
     * Vision: N/A
     * Spell 11
     * Guarantees you to find a secret in a dungeon if the room is searchable. While exploring, it
     * also lets you see 2 tiles away no matter if you're in jungle or not
     */
    private void vision() {
        usedVision = true;
        subtractMP();
    }

    private void subtractMP() {
        EnterNames.thisPlayer.liveMP -= this.mpCost;
    }

}
