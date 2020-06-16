package com.example.doome.text_baseddungeoncrawler;

public class Spell {

    public static Player thisPlayer = TitleScreen.tempPlayer;

    public byte id;

    public byte mpCost;

    public String name;

    public String description;

    public String extraCastSuccessMessage;

    public String spellFailMessage;

    public String statusEffect;

    public boolean castFail = false;

    public boolean isCombatSpell;

    public boolean isExplorationSpell;

    public final String[][] allSpells = {
            {"Basic Heal", "MA, Cost: 5 MP. Restores some health", "(empty)", "(spell doesn't " +
                    "fail)", "(no status effect)"},
            {"Strong Heal", "MA, Cost: 10 MP. Restores a lot of health", "(empty)", "(spell " +
                    "doesn't fail)", "(no status effect)"},
            {"Ultimate Block", "MA, Cost: 20 MP. Ensures you won't take damage this turn.",
                    " You are completely protected from damage this turn. ", "(spell doesn't " +
                    "fail)", "(no status effect)"},
            {"Sphericon","M, Cost: 7 MP. Summons a lil' guy to fight for you, but only by " +
                    "charging it.", " He's here, laaaaaads!! ", "(spell doesn't fail)",
                    "Sphericon active. Charge: "},
            {"Sphericon Charge","M, Cost: 2 MP. Charges your Sphericon for 1 unit.",
                    "(empty)", "(spell doesn't fail)", "no status effect"},
            {"Sphericon Supercharge","MA, Cost: 6 MP. Charges your Sphericon for 3 units.",
                    "(empty)"},
            {"Lucky Marksman","M, Cost: 6 MP. You are healed half your your party's damage output" +
                    " this turn. But if you miss, you take double what your enemies deal.",
                    " Effect is active for this turn. "},
            {"Blinding Light","M, Cost: 3 MP. 80 percent chance to reduce your enemy's accuracy " +
                    "by 3 this turn.", "(empty)", "(empty)"},
            {"Fireball","MA, Cost: 6 MP. Cast a flaming projectile whose strength is proportional" +
                    " to your magic stat (from 1.5-2.5 times your base attack power). Less " +
                    "accurate than your direct attack", "(empty)", " The " +
                    "fireball misses... "},
            {"Poison","M, Cost: 7 MP. Deals 15 percent of your enemy's max health for 3 turns",
                    " but the enemy is healed 45 percent after 3 turns.", "(empty)", " The " +
                    "purple fumes come and go, dealing only 1 damage. "},
    };

    public static final byte[] allMPCosts = {5, 10, 20, 7, 3, 8, 6, 3, 6, 7};

    public static final boolean [][] allSpellTypes = {
            {true, true},
            {true, true},
            {true, false},
            {true, false},
            {true, false},
            {true, false},
            {true, false},
            {true, false},
            {true, false},
            {true, false},
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
        mpCost = allMPCosts[setId];
        name = allSpells[setId][0];
        description = allSpells[setId][1];
        extraCastSuccessMessage = allSpells[setId][2];
        if (id < 10 && id > 6) {
            spellFailMessage = allSpells[setId][3];
        }
        isCombatSpell = allSpellTypes[setId][0];
        isExplorationSpell = allSpellTypes[setId][1];
        statusEffect = allSpells[setId][4];
    }

    /**
     * Method called externally when the desired effect of a spell should be executed.
     * @param p the player being affected
     * @param e the enemy being affected (not always used)
     */
    public void spellCast(Player p, Character e) {
        if (id == 127) {
            return;
        }
        if (id == 0) {
            basicHeal(p);
        } else if (id == 1) {
            strongHeal(p);
        } else if (id == 2) {
            ultimateBlock(p, e);
        } else if (id == 3) {
            sphericon(p);
        } else if (id == 4) {
            sphericonCharge(p);
        } else if (id == 5) {
            sphericonSuperCharge(p);
        } else if (id == 6) {
            luckyMarksman(p);
        } else if (id == 7) {
            blindingLight(p, e);
        } else if (id == 8) {
            fireball(p, e);
        } else {
            poison(p, e);
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
        if (id == 127 || id == 2 || id == 3 || id == 6) {
            return;
        }
        if (id == 0) {
            //basicHeal(p);
            extraCastSuccessMessage = " You recover " + ((byte) (thisPlayer.hp * .35)) + " HP. ";
        } else if (id == 1) {
            //strongHeal(p);
            extraCastSuccessMessage = " You recover " + ((byte) (thisPlayer.hp * .65)) + " HP. ";
        } else if (id == 4) {
            //sphericonCharge(p);
            extraCastSuccessMessage = " Total units: " + Gameplay.sphericonCharge + ". ";
        } else if (id == 5) {
            //sphericonSuperCharge(p);
            extraCastSuccessMessage = " Total units: " + Gameplay.sphericonCharge + ". ";
        } else if (id == 7) {
            //blindingLight(p, e);
            extraCastSuccessMessage = " The " + Gameplay.thisRoom.numberOne.name + " was " +
                    "successfully blinded! ";
        } else if (id == 8) {
            //fireball(p, e);
            extraCastSuccessMessage = " The fireball hits for " +
                    (byte) (thisPlayer.attackPower * (1.5 + (.1 * thisPlayer.magicValue))) +
                    " damage. ";
        } else {
            //poison(p, e);
            extraCastSuccessMessage = " The " +
                    Gameplay.thisRoom.numberOne.name + " was successfully poisoned! ";
        }
    }
    /**
     * Basic Heal: MA
     * Spell 1
     * Restores 35 percent of the player's health
     */

    private void basicHeal(Player p) {
        double tobeHealed = p.hp;
        tobeHealed *= .35;
        tobeHealed = (byte) tobeHealed;
        if ((p.liveHP + tobeHealed) > p.hp) {
            p.liveHP = p.hp;
        } else {
            p.liveHP += tobeHealed;
        }
        p.liveMP -= 5;
        if (Gameplay.isBattling) {
            Gameplay.attackTurnAdvantage = false;
        }
    }

    /**
     * Strong Heal: MA
     * Spell 2
     * Restores 65 percent of the player's health.
     */

    private void strongHeal(Player p) {
        double tobeHealed = p.hp;
        tobeHealed *= .65;
        tobeHealed = (byte) tobeHealed;
        if ((p.liveHP + tobeHealed) > p.hp) {
            p.liveHP = p.hp;
        } else {
            p.liveHP += tobeHealed;
        }
        p.liveMP -= 10;
        if (Gameplay.isBattling) {
            Gameplay.attackTurnAdvantage = false;
        }
    }

    /**
     * Ultimate Block: MA
     * Spell 3
     * Makes the player invincible this turn.
     */

    private static void ultimateBlock(Player p, Character e) {
        Gameplay.hasBlocked = true;
        p.liveHP -= 20;
        if (Gameplay.isBattling) {
            Gameplay.attackTurnAdvantage = false;
        }
    }

    /**
     * Sphericon: M
     * Spell 4
     * Summons a lil guy to fight for you. To make it deal damage, charge it with
     * either Sphericon Charge or Sphericon Supercharge. The more you charge it, the more damage
     * it will do. It will attack the first turn you don't give it charge.
     */

    private void sphericon(Player p) {
        Gameplay.hasSphericon = true;
        p.liveMP -= 7;
    }

    /**
     * Sphericon blast: when the player doesn't charge the sphericon, that turn the
     */
    public void sphericonBlast(Character e) {
        byte dealtDamage = 0;
        for (; Gameplay.sphericonCharge > 0; Gameplay.sphericonCharge--) {
            dealtDamage += 3;
            if (Gameplay.sphericonCharge > 4) {
                dealtDamage++;
            }
            if (Gameplay.sphericonCharge > 7) {
                dealtDamage++;
            }
        }
        e.liveHP -= dealtDamage;
    }
    /**
     * Sphericon Charge: M
     * Spell 5
     * Charges a sphericon for one unit.
     */

    private void sphericonCharge(Player p) {
        Gameplay.sphericonCharge++;
        p.liveMP -= 2;
    }

    /**
     * Sphericon Supercharge: MA
     * Spell 6
     * Charges a sphericon for three units.
     */

    private void sphericonSuperCharge(Player p) {
        Gameplay.sphericonCharge += 3;
        p.liveMP -= 6;
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
    private void luckyMarksman(Player p) {

        p.liveMP -= 6;
    }

    /**
     * Blinding Light: M
     * Spell 8
     * When cast,
     * 80 percent chance that your enemy's accuracy is decreased by 3 this turn
     * 20 percent chance of doing nothing
     */

    private void blindingLight(Player p, Character e) {
        double blinded = Math.random();
        byte initAcc = -1;
        if (blinded < .8) {
            initAcc = e.accuracyValue;
            if (e.accuracyValue < 3) {
                e.accuracyValue = 0;
            } else {
                e.accuracyValue -= 3;
            }
            e.setHitChance();
            castFail = false;
            Gameplay.initStat = initAcc;
            Gameplay.statusEffect = true;
        } else {
            castFail = true;
        }
        p.liveMP -= 3;
    }

    /**
     * Blinding Light helper: to undo the effect once the turn is over
     */
    public void blindingLightReverse(Character e, byte initAcc) {
        e.accuracyValue = initAcc;
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
        p.liveMP -= 6;
    }

    /**
     * Poison: M
     * Spell 10
     * 60 percent chance of lowering your enemy's max health by 20 percent for 3 turns. After the
     * third turn, they will also be healed the number of hp their max hp was lowered by.
     * 40 percent chance of dealing 1 damage.
     */

    private void poison(Player p, Character e) {

        p.liveMP -= 7;
    }


}
