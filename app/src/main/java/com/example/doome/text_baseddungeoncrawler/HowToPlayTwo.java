package com.example.doome.text_baseddungeoncrawler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HowToPlayTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play_two);
        configureNextButton();
    }
    private void configureNextButton() {
        Button progressButton = (Button) findViewById(R.id.HowTwoNextButton);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HowToPlayTwo.this, StatSelection.class));
            }
        });
        Button progressButton1 = (Button) findViewById(R.id.HowTwoBackButton);
        progressButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HowToPlayTwo.this, HowToPlay.class));
            }
        });
    }
}
