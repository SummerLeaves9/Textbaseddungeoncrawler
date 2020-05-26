package com.example.doome.text_baseddungeoncrawler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

public class SpellSelection extends AppCompatActivity {

    //public static byte magicStat;

    static TextView spellOneDesc;
    static TextView spellTwoDesc;
    static TextView spellThreeDesc;
    static TextView spellFourDesc;
    static TextView spellFiveDesc;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_selection);
        spellOneDesc = (TextView) findViewById(R.id.spellOneDesc);
        spellTwoDesc = (TextView) findViewById(R.id.spellTwoDesc);
        spellThreeDesc = (TextView) findViewById(R.id.spellThreeDesc);
        spellFourDesc = (TextView) findViewById(R.id.spellFourDesc);
        spellFiveDesc = (TextView) findViewById(R.id.spellFiveDesc);
        configureNextButton();
    }
    private void configureNextButton() {
        Button spellOne = (Button) findViewById(R.id.activateSpellOne);
        Button spellTwo = (Button) findViewById(R.id.activateSpellTwo);
        Button spellThree = (Button) findViewById(R.id.activateSpellThree);
        Button spellFour = (Button) findViewById(R.id.activateSpellFour);
        Button spellFive = (Button) findViewById(R.id.activateSpellFive);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spellOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spellTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spellThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spellFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spellFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
