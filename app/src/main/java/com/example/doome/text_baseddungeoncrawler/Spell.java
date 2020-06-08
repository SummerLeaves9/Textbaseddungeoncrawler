package com.example.doome.text_baseddungeoncrawler;

public class Spell {

    public Player thisPlayer = EnterNames.thisPlayer;

    public byte id;

    public byte mpCost;

    public String name;

    public String description;

    public static final String[][] allSpells = {
            {"Basic Heal", "MA, Cost: 5 MP. Restores some health"},
            {"Strong Heal", "MA, Cost: 10 MP. Restores a lot of health"},
            {"Ultimate Block", "MA, Cost: 20 MP. Ensures you won't take damage this turn."},
            {"Sphericon","M, Cost: 7 MP. Summons a lil' guy to fight for you, but only by " +
                    "charging it."},
            {"Sphericon Charge","M, Cost: 3 MP. Charges your Sphericon for 1 unit."},
            {"Sphericon Supercharge","MA, Cost: 8 MP. Charges your Sphericon for 3 units."},
            {"Lucky Marksman","M, Cost: 6 MP. You are healed half your your party's damage output" +
                    " this turn. But if you miss, you take double what your enemies deal."},
            {"Blinding Light","M, Cost: 3 MP. 80 percent chance to reduce your enemy's accuracy " +
                    "by 3 this turn."},
            {"Fireball","MA, Cost: 6 MP. Cast a flaming projectile whose strength is proportional" +
                    " to your magic stat (from 1.5-2.5 times your base attack power). Less " +
                    "accurate than your direct attack"},
            {"Poison","M, Cost: 7 MP. Deals 15 percent of your enemy's max health for 3 turns, " +
                    "but the enemy is healed 45 percent after 3 turns."},
    };

    public static final byte[] allMPCosts = {5, 10, 20, 7, 3, 8, 6, 3, 6, 7};

    public Spell(byte setId) {
        id = setId;
        mpCost = allMPCosts[setId];
        name = allSpells[setId][0];
        description = allSpells[setId][1];
    }

    public void spellCast(Character p, Character e) {
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
     * Basic Heal: MA
     * Spell 1
     * Restores 35 percent of the player's health
     */

    private void basicHeal(Character p) {
        double tobeHealed = p.hp;
        tobeHealed *= .35;
        p.liveHP += (byte) tobeHealed;
        p.liveMP -= 5;
        Gameplay.attackTurnAdvantage = false;
    }

    /**
     * Strong Heal: MA
     * Spell 2
     * Restores 65 percent of the player's health.
     */

    private void strongHeal(Character p) {
        double tobeHealed = p.hp;
        tobeHealed *= .65;
        p.liveHP += (byte) tobeHealed;
        p.liveMP -= 10;
        Gameplay.attackTurnAdvantage = false;
    }

    /**
     * Ultimate Block: MA
     * Spell 3
     * Makes the player invincible this turn.
     */

    private static void ultimateBlock(Character p, Character e) {
        Gameplay.hasBlocked = true;
        p.liveHP -= 20;
        Gameplay.attackTurnAdvantage = false;
    }

    /**
     * Sphericon: M
     * Spell 4
     * Summons a lil guy to fight for you. To make it deal damage, charge it with
     * either Sphericon Charge or Sphericon Supercharge. The more you charge it, the more damage
     * it will do. It will attack the first turn you don't give it charge.
     */

    private static void sphericon(Character p) {
        Gameplay.hasSphericon = true;
        p.liveMP -= 7;
    }

    /**
     * Sphericon Charge: M
     * Spell 5
     * Charges a sphericon for one unit.
     */

    private static void sphericonCharge(Character p) {
        Gameplay.sphericonCharge++;
        p.liveMP -= 3;
    }

    /**
     * Sphericon Supercharge: MA
     * Spell 6
     * Charges a sphericon for three units.
     */

    private static void sphericonSuperCharge(Character p) {
        Gameplay.sphericonCharge += 3;
        p.liveMP -= 8;
        Gameplay.attackTurnAdvantage = false;
    }

    /**
     * Lucky Marksman: M
     * Spell 7
     * When cast, the following conditions apply to the player's next attack turn:
     * If this player lands their attack, they are healed half of the amount they deal
     * If this player does not hit their attack, AND the enemy hits theirs, the player takes double
     * damage
     */
    private void luckyMarksman(Character p) {

        p.liveMP -= 6;
    }

    /**
     * Blinding Light: M
     * Spell 8
     * When cast,
     * 80 percent chance that your enemy's accuracy is decreased by 3 this turn
     * 20 percent chance of doing nothing
     */

    private void blindingLight(Character p, Character e) {

        p.liveMP -= 3;
    }

    /**
     * Fireball: MA
     * Spell 9
     * Cast a flaming projectile with these properties:
     * Depending on the player's magic stat, deals 1.5-2.5 times normal damage
     * Is -2 accurate compared to your normal attack
     */

    private void fireball(Character p, Character e) {
        int fireballAttackPower = (int) (p.attackPower * (1.5 + (.1 * p.magicValue)));
        double otherDodgeResult = Math.random();
        double thisHitResult = Math.random();
        double thisCritResult = Math.random();
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
            if (thisCritResult < p.critChance) {
                dealtDamage = (byte) (p.attackPower * Character.critMultiplier);
            } else {
                dealtDamage = fireballAttackPower;
            }
            e.liveHP -= dealtDamage;
        }
        //Updates appropriate strings/output in Gameplay class
        if (dealtDamage != 0) {
            Gameplay.consoleOutput += Gameplay.displayHit + dealtDamage + ". ";
            if (dealtDamage > fireballAttackPower) {
                Gameplay.consoleOutput += Gameplay.criticalHit;
            }
            if (e.liveHP <= 0) {
                EnterNames.thisPlayer.myPoints += Gameplay.thisRoom.numberOne.pointValue;
                Gameplay.victoryMessage = "You won! " + e.name +
                        " dropped " + Gameplay.thisRoom.numberOne.pointValue + " points. Now you have " +
                        EnterNames.thisPlayer.myPoints + " points!";
                Gameplay.consoleOutput += Gameplay.victoryMessage;
                Gameplay.isBattling = false;
                Gameplay.enemiesDefeatedCounter++;
                if (Gameplay.thisRoom.disSearchable) {
                    Gameplay.consoleOutput += Gameplay.canBeSearched;
                }
                Gameplay.attackTurnAdvantage = true;
            } else {
                Gameplay.attackTurnAdvantage = false;
                Gameplay.magicTurnAdvantage = false;
            }
        } else {
            Gameplay.consoleOutput = Gameplay.displayMiss;
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

    private void poison(Character p, Character e) {

        p.liveMP -= 7;
    }


}
