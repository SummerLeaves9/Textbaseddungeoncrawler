package com.example.doome.text_baseddungeoncrawler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterNames extends AppCompatActivity {

    EditText NameEnterName;
    EditText NameEnterWeaponName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_names);
        configureNextButton();
        NameEnterName = (EditText) findViewById(R.id.NameEnterName);
        NameEnterWeaponName = (EditText) findViewById(R.id.NameEnterWeaponName);
    }
    private void configureNextButton() {
        Button progressButton = (Button) findViewById(R.id.NameSubmitButton);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gameplay.playerName = NameEnterName.getText().toString();
                Gameplay.playerWeaponName = NameEnterWeaponName.getText().toString();
                startActivity(new Intent(EnterNames.this, Gameplay.class));
            }
        });
    }
}
