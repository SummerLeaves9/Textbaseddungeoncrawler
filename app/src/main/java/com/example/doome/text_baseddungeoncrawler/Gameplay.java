package com.example.doome.text_baseddungeoncrawler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Gameplay extends AppCompatActivity {

    EditText actionInput;
    TextView healthDisplay;
    TextView gameInfo;
    String healthDisplayInfo = new String("Hp: " + Dungeon.thisPlayer.liveHP + "/" + Dungeon.thisPlayer.hp);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        configureNextButton();
        actionInput = (EditText) findViewById(R.id.actionInput);
        healthDisplay = (TextView) findViewById(R.id.healthDisplay);
        gameInfo = (TextView) findViewById(R.id.gameInfo);
        gameInfo.setText(Dungeon.consoleOutput);
        healthDisplay.setText(healthDisplayInfo);
    }
    private void configureNextButton() {
        Button progressButton = (Button) findViewById(R.id.submitButton);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dungeon.userInput = actionInput.getText().toString();
                actionInput.setText("");
            }
        });
    }
    public static void startBattle() {
        return;
    }
}
