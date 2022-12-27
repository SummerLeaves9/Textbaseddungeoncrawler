package com.example.doome.dungeon_lords;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Spell extends AppCompatActivity {

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

    public boolean effectLingers;

    //'(empty)' generally means the string is used and modified in methods below, as they require
    //the characters' names
    public static final String[][] allSpells = {
            {"Basic Heal", "MA, Cost: 4 MP. Restores some health", "You recover 10 HP. ",
                    "(spell doesn't fail)", "(no status effect)"},
            {"Strong Heal", "MA, Cost: 9 MP. Restores a lot of health", "You recover 18 HP. ",
                    "(spell doesn't fail)", "(no status effect)"},
            {"Ultimate Block", "M, Cost: 5 MP. Ensures you won't take damage this turn.",
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
            {"Lucky Marksman","M, Cost: 5 MP. You deal double damage this turn with your " +
                    "regular attack! But if you miss, " +
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
                    "matter if you're in jungle or not the rest of that day", "You feel way more in touch with your " +
                    "environment. ", "(spell doesn't fail)", "(no status effect)"},
            //Status: almost, if not wholly implemented
            {"Mark of Death", "M, Cost: 6 MP. For the next three turns, hitting with a normal " +
                    "attack will mark the enemy with a magic dagger. On your third turn, " +
                    "marks will hit the enemy, dealing half your attack damage times the number " +
                    "of marks. ", "Your marks are summoned and ready. ", "(spell doesn't fail)",
                    "Mark of Death", "Mark of Death expires, and all your marks hit! for " + 0 +
                    " damage. ", ""},
            //Status: almost, if not wholly implemented
            {"Achilles' Blessing", "M, Cost: 8 MP. " +
                    "80% chance to receive a massive increase in your Accuracy (+6) for 4 turns. " +
                    "20% chance to summon a spear that deals 7 damage. ",
                    "May your aim be true! ", "A spear pierces the enemy from over your head, " +
                    "hitting for 7. ", "Accuracy Up", "Achilles' Blessing wears off."},
            //Status: almost, if not wholly implemented
            {"Absolute Zero", "M, Cost: 9 MP. 90 percent chance to gradually freeze your enemy " +
                    "over 3 turns. On the second turn, the enemy's agility and accuracy are " +
                    "reduced to 0. On the third turn, your enemy freezes completely, " +
                    "guaranteeing that you strike them on that turn, and they don't strike you.",
                    "The " + " starts to cool down! ", "No effect! ", "Frozen", "The enemy " +
                    "shakes off the ice and regains their strength. "},
            //Status: almost, if not wholly implemented
            {"Berserk", "M, Cost: 7 MP. Can only be activated if you have 12 health or less. " +
                    "Shrug off 1/3 of incoming damage and deal 1.6 times damage with attack, " +
                    "counter, defend, and certain spells for 2 turns.", "you gain a fiery aura " +
                    "as you feel your muscles accepting the ballistic energy. ", "but the spell " +
                    "refuses to empower you! You have too much HP. ", "Berserk",
                    "The effects of Berserk wear off. "},
            //Status: almost, if not wholly implemented
            {"Electric Touch", "MA, Cost: 4 MP. If your enemy uses a melee attack, shock them for " +
                    "3 damage next time they hit you. They become stunned on the following turn.",
                    "bolts of energy briefly fly from the ground to your legs and hands. ",
                    "(spell doesn't fail)", "Electric Touch", "(spell doesn't linger)"},
            //Status: almost, if not wholly implemented
            {"Hex of Draining", "M, Cost: 6 MP. 70% chance to, for this rest of this fight, each " +
                    "time you hit with an attack or counter more than once in a row, heal for " +
                    "half the damage you deal.", "A black aura pulsates off the top of the " + ". ",
                    "No effect!", "Hex of Draining", "(spell doesn't linger)"}

    };

    //TODO: DISABLE THE PLAYER FROM USING 2 SPELLS AT A TIME THAT EACH HAVE A BUFF/DEBUFF EFFECT ON
    // EITHER THEMSELF OR THE ENEMY
    //done I think

    public static final byte[] allMPCosts = {4, 9, 5, 5, 1, 4, 5, 3, 6, 7, 10, 6, 8, 9, 7, 4, 6,};

    //isCombatSpell, isExplorationSpell, enemyEffect, hasEffect, shouldUpdateSpellNum, effectLingers
    public static final boolean[][] allSpellBools = {
            {true, true, false, false, true, false}, // Basic Heal
            {true, true, false, false, true, false}, // Strong Heal
            {true, false, false, false, true, false}, // Ultimate Block
            {true, false, false, true, true, false}, // Sphericon
            {true, false, false, true, false, false}, // Sphericon Charge
            {true, false, false, true, false, false}, // Sphericon Supercharge
            {true, false, false, true, true, false}, // Lucky Marksman
            {true, false, true, true, true, false}, // Blinding Light
            {true, false, false, false, true, false}, // Fireball
            {true, false, true, true, true, true}, // Poison
            {false, true, false, false, true, false}, // Vision
            {true, false, false, true, true, false}, // Mark of Death
            {true, false, false, true, true, false}, // Achilles' Blessing
            {true, false, true, true, true, true}, // Absolute Zero
            {true, false, false, true, true, false}, // Berserk
            {true, false, false, true, true, false}, // Electric Touch
            {true, false, true, true, true, false}, // Hex of Draining
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
        effectLingers = allSpellBools[setId][5];
        mpCost = allMPCosts[setId];
        name = allSpells[setId][0];
        description = allSpells[setId][1];
        extraCastSuccessMessage = allSpells[setId][2];
        if ((id < 10 && id > 6) || id == 3 || id == 12 || id == 13 || id == 14 || id == 16) {
            spellFailMessage = allSpells[setId][3];
        }
        if (hasEffect) {
            statusEffect = allSpells[setId][4];
            if (shouldUpdateSpellNum) {
                effectEndsText = allSpells[setId][5];
            }
        }
        if (id == 3 || id == 9 || id == 11) {
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
            case 11:
                markOfDeath();
                break;
            case 12:
                achillesBlessing(e);
                break;
            case 13:
                absoluteZero(e);
                break;
            case 14:
                berserk(e);
                break;
            case 15:
                electricTouch();
                break;
            case 16:
                hexOfDraining(e);
                break;
        }
        subtractMP();
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
     * @param e Enemy being affected
     */
    public void reverseSpellCast(Character e) {
        switch (id) {
            case 127:
                return;
            case 3:
                effectEndsText = "Sphericon was not charged, so it unleashes a " +
                        "powerful blast! For " + sphericonBlast(e) + " damage. ";
                break;
            case 6:
                luckyMarksmanReverse();
                break;
            case 7:
                blindingLightReverse(e);
                break;
            case 9:
                poisonReverse(e);
                break;
            case 11:
                markOfDeathReverse();
                break;
            case 12:
                achillesBlessingReverse();
                break;
            case 13:
                absoluteZeroReverse();
                break;
            case 14:
                berserkReverse(e);
        }
    }
    /**
     * General way to call spell effects that linger once per turn
     */
    public void lingeringEffect(Character pe) {
        switch (id) {
            case 9:
                poisonDamager(pe);
                break;
            case 13:
                absoluteZeroLinger(pe);
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
        } else if (id == 13) {
            //absoluteZero(e)
            extraCastSuccessMessage = "The " + Gameplay.thisRoom.numberOne.name + " starts to " +
                    "cool down! ";
        } else if (id == 16) {
            extraCastSuccessMessage = "A black aura pulsates off the top of the " +
                    Gameplay.thisRoom.numberOne.name + ". ";
        }
    }

    private void updateLingeringEffectText(Character pe) {
        switch (id) {
            case 9:
                lingeringEffectText = pe.name + " takes " + (byte) (pe.hp * .15) + " poison damage. ";
                break;
            case 13:
                if (Gameplay.enemyEffectCounter == 2) {
                    lingeringEffectText = "The " + Gameplay.thisRoom.numberOne.name + " gets " +
                            "chillier...Accuracy and Agility down! ";
                } else if (Gameplay.enemyEffectCounter == 1) {
                    lingeringEffectText = Gameplay.thisRoom.numberOne.name + " is fully frozen! ";
                }
        }
    }

    public String getLingeringEffectText(Character pe) {
        updateLingeringEffectText(pe);
        return lingeringEffectText;
    }

    /**
     * Basic Heal: MA
     * Spell 0
     * Restores 10 health
     */

    private void basicHeal(Player p) {
        byte tobeHealed = 10;
        if ((p.liveHP + tobeHealed) > p.hp) {
            p.liveHP = p.hp;
        } else {
            p.liveHP += tobeHealed;
        }
        if (Gameplay.isBattling) {
            Gameplay.attackTurnAdvantage = false;
        }
    }

    /**
     * Strong Heal: MA
     * Spell 1
     * Restores 18 health
     */

    private void strongHeal(Player p) {
        double tobeHealed = 18;
        if ((p.liveHP + tobeHealed) > p.hp) {
            p.liveHP = p.hp;
        } else {
            p.liveHP += tobeHealed;
        }
        if (Gameplay.isBattling) {
            Gameplay.attackTurnAdvantage = false;
        }
    }

    /**
     * Ultimate Block: M
     * Spell 2
     * Makes the player invincible this turn.
     */

    private void ultimateBlock() {
        Gameplay.hasBlocked = true;
    }

    /**
     * Sphericon: M
     * Spell 3
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
     * Spell 4
     * Charges a sphericon for one unit.
     */

    private void sphericonCharge() {
        if (Gameplay.hasSphericon) {
            castFail = false;
            Gameplay.hasCharged = true;
            Gameplay.sphericonCharge++;
        } else {
            castFail = true;
        }
    }

    /**
     * Sphericon Supercharge: MA
     * Spell 5
     * Charges a sphericon for three units.
     */

    private void sphericonSuperCharge() {
        Gameplay.hasCharged = true;
        Gameplay.sphericonCharge += 3;
        if (Gameplay.isBattling) {
            Gameplay.attackTurnAdvantage = false;
        }
    }

    /**
     * Lucky Marksman: M
     * Spell 6
     * When cast, the following conditions apply to the player's next attack turn:
     * If this player lands their attack, they are healed half of the amount they deal
     * If this player does not hit their attack, AND the enemy hits theirs, the player takes double
     * damage
     */
    private void luckyMarksman() {
        Gameplay.usedLuckyMarksman = true;
        Gameplay.myEffectCounter = 0;
    }

    /**
     * Lucky Marksman helper: reverses the effect after one turn
     */
    private void luckyMarksmanReverse() {
        Gameplay.usedLuckyMarksman = false;
    }

    /**
     * Blinding Light: M
     * Spell 7
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
            Gameplay.enemInitStat = initAcc;
            //Gameplay.statusEffect = true;
            Gameplay.enemyEffectCounter = 0;
        } else {
            castFail = true;
        }
    }

    /**
     * Blinding Light helper: to undo the effect once the turn is over
     */
    public void blindingLightReverse(Character e) {
        e.accuracyValue = Gameplay.enemInitStat;
        e.setHitChance();
    }

    /**
     * Fireball: MA
     * Spell 8
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
        if ((e.dodgeChance < otherDodgeResult && p.hitChance > thisHitResult) || Gameplay.frozen) {
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
    }

    /**
     * Poison: M
     * Spell 9
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
     * Spell 10
     * Guarantees you to find a secret in a dungeon if the room is searchable. While exploring, it
     * also lets you see 2 tiles away no matter if you're in jungle or not
     */
    private void vision() {
        usedVision = true;
    }

    /**
     * Mark of Death: M
     * Spell 11
     * For the next three turns, hitting with a normal attack will mark the enemy with a magic
     * dagger. On your fourth turn, marks will hit the enemy, dealing half your attack damage
     * times the number of marks.
     */
    private void markOfDeath() {
        Gameplay.usedMarkOfDeath = true;
        Gameplay.myEffectCounter = 2;
    }

    /**
     * Mark of Death helper: reverses the effect after 3 turns
     */
    private void markOfDeathReverse() {
        int dealtDamage = (int) (.5 * EnterNames.thisPlayer.attackPower) * Gameplay.markCount;
        Gameplay.thisRoom.numberOne.liveHP -= dealtDamage;
        effectEndsText =  "Mark of Death expires, and all your marks hit! for " + dealtDamage +
                " damage. ";
        Gameplay.usedMarkOfDeath = false;
    }

    /**
     * Achilles' Blessing: M
     * Spell 12
     * 80% chance to receive a massive increase in your Accuracy (+6) for 4 turns. 20% chance to
     * summon a spear that deals 7 damage.
     */
    private void achillesBlessing(Character e) {
        double accChance = Math.random();
        //EnterNames.thisPlayer.liveHP -= (byte) (EnterNames.thisPlayer.liveHP * .5);
        if (accChance < .8) {
            byte tempAccValue = EnterNames.thisPlayer.accuracyValue;
            Gameplay.playerInitStat = tempAccValue;
            if (tempAccValue > 4) {
                EnterNames.thisPlayer.accuracyValue = 10;
            } else {
                EnterNames.thisPlayer.accuracyValue += 6;
            }
            EnterNames.thisPlayer.setHitChance();
            castFail = false;
            Gameplay.myEffectCounter = 4;
        } else {
            castFail = true;
            e.liveHP -= 7;
        }
    }

    private void achillesBlessingReverse() {
        EnterNames.thisPlayer.accuracyValue = Gameplay.playerInitStat;
        EnterNames.thisPlayer.setHitChance();
    }

    /**
     * Absolute Zero: M
     * Spell 13
     * 90 percent chance to gradually freeze your enemy over 3 turns. On the second turn, the
     * enemy's agility and accuracy are reduced to 0. On the third turn, your enemy freezes
     * completely, guaranteeing that you can strike them on that turn, and they don't strike you.
     */
    private void absoluteZero(Character e) {
        //TextView h = findViewById(R.id.enemyEffectIndicator);
        //h.setTextColor(15720141);
        double castSuccess = Math.random();
        if (castSuccess < .9) {
            Gameplay.enemyEffectCounter = 2;
            castFail = false;
        } else {
            castFail = true;
        }
    }

    private byte enemyTempAg;
    private byte enemyTempAcc;

    /**
     * Absolute Zero helper: inflicts effects on second and third turns
     */
    private void absoluteZeroLinger(Character e) {
        if (Gameplay.enemyEffectCounter == 2) {
            enemyTempAg = e.agilityValue;
            enemyTempAcc = e.accuracyValue;
            e.accuracyValue = 0;
            e.agilityValue = 0;
            e.setHitChance();
            e.setDodgeChance();
        } else if (Gameplay.enemyEffectCounter == 1) {
            e.accuracyValue = enemyTempAcc;
            e.agilityValue = enemyTempAg;
            e.setHitChance();
            e.setDodgeChance();
            Gameplay.frozen = true;
        }
    }

    /**
     * Absolute Zero reverse: restores enemies to original state
     */
    private void absoluteZeroReverse() {
        Gameplay.frozen = false;
    }

    /**
     * Berserk: M
     * Spell 14
     * Can only be activated if you have 12 health or less. Shrug off 1/3 of
     * incoming damage and deal 1.6 times damage with attack, counter, defend, and certain spells
     * for 2 turns.
     */
    private void berserk(Character e) {
        if (EnterNames.thisPlayer.liveHP > 12) {
            castFail = true;
        } else {
            castFail = false;
            e.scaleAttackPower(.666);
            EnterNames.thisPlayer.scaleAttackPower(1.6);
            Gameplay.myEffectCounter = 1;
        }
    }

    /**
     * BerserkReverse: undoes effects
     */
    private void berserkReverse(Character e) {
        EnterNames.thisPlayer.scaleAttackPower(1);
        e.scaleAttackPower(1);
    }

    /**
     * Electric Touch: MA
     * Spell 15
     * MA, Cost: 4 MP. If your enemy uses a melee attack, shock them for 3 damage next time they
     * hit you. They become stunned on the following turn.
     */
    private void electricTouch() {
        Gameplay.myEffectCounter = 127;
        Gameplay.hasElectricTouch = true;
        Gameplay.attackTurnAdvantage = false;
    }

    /**
     * Hex of Draining: M
     * Spell 16
     * MA, Cost: 6 MP 70% chance to, for this rest of this fight, each time you hit with an attack
     * or counter more than once in a row, heal for half the damage you deal.
     */
    private void hexOfDraining(Character e) {
        double spellWorked = Math.random();
        if (spellWorked < .7) {
            e.hasHexOfDraining = true;
            Gameplay.enemyEffectCounter = 127;
            castFail = false;
        } else {
            castFail = true;
        }
    }

    private void subtractMP() {
        EnterNames.thisPlayer.liveMP -= this.mpCost;
    }

}
