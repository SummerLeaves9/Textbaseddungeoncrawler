package com.example.doome.text_baseddungeoncrawler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HowToPlay extends AppCompatActivity {

    TextView HowInstructionsOne;
    TextView HowSelectedDifficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        configureNextButton();
        HowInstructionsOne = (TextView) findViewById(R.id.HowInstructionsOne);
        HowInstructionsOne.setText("You wake up in a dark room, illuminated only by a torch mounted on the wall. The walls are made of rounded, coarse, gray bricks. To play, you enter text commands to battle monsters and search for secrets. After every action, you will be given a few options for things to do next. ");
        HowSelectedDifficulty = (TextView) findViewById(R.id.HowSelectedDifficulty);
        HowSelectedDifficulty.setText("Selected Difficulty: " + DifficultySelection.gameRoomCount + " rooms");
    }
    private void configureNextButton() {
        Button progressButton = (Button) findViewById(R.id.HowNextButton);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HowToPlay.this, HowToPlayTwo.class));
            }
        });
    }
}
