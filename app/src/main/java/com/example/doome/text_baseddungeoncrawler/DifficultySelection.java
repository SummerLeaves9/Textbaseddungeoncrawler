package com.example.doome.text_baseddungeoncrawler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DifficultySelection extends AppCompatActivity {

    public static int gameRoomCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty_selection);
        configureNextButton();
    }
    private void configureNextButton() {
        Button progressButton1 = (Button) findViewById(R.id.EasyButton);
        progressButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DifficultySelection.this, HowToPlay.class));
                gameRoomCount = Dungeon.easyRoomCount;
            }
        });
        Button progressButton2 = (Button) findViewById(R.id.NormalButton);
        progressButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DifficultySelection.this, HowToPlay.class));
                gameRoomCount = Dungeon.normalRoomCount;
            }
        });
        Button progressButton3 = (Button) findViewById(R.id.HardButton);
        progressButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DifficultySelection.this, HowToPlay.class));
                gameRoomCount = Dungeon.hardRoomCount;
            }
        });
    }
}
