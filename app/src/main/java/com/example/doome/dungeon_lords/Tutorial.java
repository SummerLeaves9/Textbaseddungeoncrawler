package com.example.doome.dungeon_lords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tutorial extends AppCompatActivity {

    private String tutorialText = "Welcome to Dungeon Lords! This game is all about exploring " +
            "dungeons with a character whose strengths and weaknesses you can choose! You have 3 " +
            "physical actions in a fight: attack, counter, and defend. Attack is blunt and " +
            "simple: you use your turn to attack the enemy, and they use theirs to attack you. " +
            "Counter is a special way to inflict damage: You wait for the enemy to approach you, " +
            "and since you've used your energy to dodge them, you can catch them while they're " +
            "vulnerable and counterattack. But be careful, if you spam it the enemy will catch " +
            "on. They'll start feinting attacks, then while you're committed to catching their " +
            "false " +
            "approach, they will get a free hit on you. This action becomes more viable if you " +
            "have a high agility stat. The last action is defense. You get in a " +
            "strong stance and brace your arms to catch the brunt of the attack. If the enemy " +
            "misses though, this low-commitment action allows you enough time for a free jab. " +
            "This is much weaker than your normal attack power, though. If you invest in the " +
            "magic " +
            "stat, you'll gain access to spells! Spells are a great way to get a unique edge on " +
            "your opponent, and many of them you can do on top of a physical combat action " +
            "(attack, counter, or defend) in one turn. But watch your magic points, they can run " +
            "out fast if " +
            "you spam spells. You can learn new spells throughout the game if you find and " +
            "defeat warlocks, or you find secrets (which you can find more of with high " +
            "intelligence and luck). Secrets also net you other stat boosts, such as attack power" +
            " and defense. Some secrets are potentially dangerous though, so you may choose not " +
            "to explore one. When exploring, it's safest to stay on the paths around the map, as " +
            "they allow you to see further, and you are less likely to be attacked.";

    TextView tutorial;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        tutorial = findViewById(R.id.tutorial);
        tutorial.setText(tutorialText);
        configureNextButton();
    }
    private void configureNextButton() {
        Button backToTitle = (Button) findViewById(R.id.tutorialBackButton);
        backToTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {}
}
