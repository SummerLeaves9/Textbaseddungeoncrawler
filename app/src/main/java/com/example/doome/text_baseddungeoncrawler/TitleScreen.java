package com.example.doome.text_baseddungeoncrawler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

public class TitleScreen extends AppCompatActivity {

    private TextView mTextMessage;

    public static Player tempPlayer = new Player((byte) 5,(byte) 5, (byte) 5,(byte) 5,(byte) 5,(byte) 5,(byte) 5,"x", "xx");

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_screen);

        mTextMessage = (TextView) findViewById(R.id.message);
        configureNextButton();
    }
    private void configureNextButton() {
        Button progressButton = (Button) findViewById(R.id.startButton);
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TitleScreen.this, DifficultySelection.class));
            }
        });
    }
}
