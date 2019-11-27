package com.example.doome.text_baseddungeoncrawler;

public class Spell {

    public static byte spellIndex;
    public static String spellName;

    public void basicHeal(Character c) {
        spellName = "Basic Heal";
        byte tobeHealed = c.hp;
        tobeHealed *= (byte) .35;
    }

    public void strongHeal() {
        spellName = "Strong Heal";
    }

    public void ultimateBlock() {
        spellName = "Ultimate Block";
    }

    public void sphericon() {
        spellName = "Sphericon";
    }

    public void sphericonCharge() {
        spellName = "Sphericon Charge";
    }

    public void sphericonSuperCharge() {
        spellName = "Sphericon Supercharge";
    }

    public void luckyMarksman() {
        spellName = "Lucky Marksman";
    }

    public void blindingLight() {
        spellName = "Blinding Light";
    }

    public void fireball() {
        spellName = "Fireball";
    }

    public void poison() {
        spellName = "Poison";
    }

}
