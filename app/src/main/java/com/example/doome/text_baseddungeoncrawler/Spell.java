package com.example.doome.text_baseddungeoncrawler;

public class Spell {

    public static byte spellIndex;
    public static String spellName;

    public Spell() {}

    public Spell(byte index) {
        spellIndex = index;
        if (index == 1) {
            spellName = "Basic Heal";
        } else if (index == 2) {
            spellName = "Strong Heal";
        } else if (index == 3) {
            spellName = "Ultimate Block";
        } else if (index == 4) {
            spellName = "Sphericon";
        } else if (index == 5) {
            spellName = "Sphericon Charge";
        } else if (index == 6) {
            spellName = "Sphericon Supercharge";
        } else if (index == 7) {
            spellName = "Lucky Marksman";
        } else if (index == 8) {
            spellName = "Blinding Light";
        } else if (index == 9) {
            spellName = "Fireball";
        } else if (index == 10) {
            spellName = "Poison";
        }
    }

    public void basicHeal(Character c) {
        byte tobeHealed = c.hp;
        tobeHealed *= (byte) .35;
        c.liveHP += tobeHealed;
    }

    public void strongHeal(Character c) {
        byte tobeHealed = c.hp;
        tobeHealed *= (byte) .65;
        c.liveHP += tobeHealed;
    }

    public void ultimateBlock() {

    }

    public void sphericon() {

    }

    public void sphericonCharge() {

    }

    public void sphericonSuperCharge() {

    }

    public void luckyMarksman() {

    }

    public void blindingLight() {

    }

    public void fireball() {

    }

    public void poison() {

    }

    public void determineSpell(byte index) {

    }

    public void cast() {

    }

}
