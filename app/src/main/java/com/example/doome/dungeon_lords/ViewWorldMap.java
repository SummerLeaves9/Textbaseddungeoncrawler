package com.example.doome.dungeon_lords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewWorldMap extends AppCompatActivity {

    char[][] worldMap = Exploration.liveWorldMap;

    TextView line1;
    TextView line2;
    TextView line3;
    TextView line4;
    TextView line5;
    TextView line6;
    TextView line7;
    TextView line8;
    TextView line9;
    TextView line10;
    TextView line11;
    TextView line12;
    TextView line13;
    TextView line14;
    TextView line15;
    TextView line16;
    TextView line17;
    TextView line18;
    TextView line19;
    TextView line20;
    TextView line21;
    TextView line22;
    TextView line23;
    TextView line24;
    TextView line25;
    //TextView line26;
    //TextView line27;
    //TextView line28;
    //TextView line29;
    //TextView line30;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_map);

        line1 = findViewById(R.id.textView4);
        line2 = findViewById(R.id.textView5);
        line3 = findViewById(R.id.textView6);
        line4 = findViewById(R.id.textView7);
        line5 = findViewById(R.id.textView8);
        line6 = findViewById(R.id.textView9);
        line7 = findViewById(R.id.textView10);
        line8 = findViewById(R.id.textView11);
        line9 = findViewById(R.id.textView12);
        line10 = findViewById(R.id.textView13);
        line11 = findViewById(R.id.textView14);
        line12 = findViewById(R.id.textView15);
        line13 = findViewById(R.id.textView16);
        line14 = findViewById(R.id.textView17);
        line15 = findViewById(R.id.textView18);
        line16 = findViewById(R.id.textView19);
        line17 = findViewById(R.id.textView20);
        line18 = findViewById(R.id.textView21);
        line19 = findViewById(R.id.textView22);
        line20 = findViewById(R.id.textView23);
        line21 = findViewById(R.id.textView24);
        line22 = findViewById(R.id.textView25);
        line23 = findViewById(R.id.textView26);
        line24 = findViewById(R.id.textView27);
        line25 = findViewById(R.id.textView28);
        //line26 = findViewById(R.id.textView29);
        //line27 = findViewById(R.id.textView30);
        //line28 = findViewById(R.id.textView31);
        //line29 = findViewById(R.id.textView32);
        //line30 = findViewById(R.id.textView33);

        setWorldMap();
        configureNextButton();
    }

    private void configureNextButton() {
        Button backButton = findViewById(R.id.worldMapBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {}

    private void setWorldMap() {
        String line1Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line1Text += worldMap[0][i];
        }
        line1.setText(line1Text);
        String line2Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line2Text += worldMap[1][i];
        }
        line2.setText(line2Text);
        String line3Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line3Text += worldMap[2][i];
        }
        line3.setText(line3Text);
        String line4Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line4Text += worldMap[3][i];
        }
        line4.setText(line4Text);
        String line5Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line5Text += worldMap[4][i];
        }
        line5.setText(line5Text);
        String line6Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line6Text += worldMap[5][i];
        }
        line6.setText(line6Text);
        String line7Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line7Text += worldMap[6][i];
        }
        line7.setText(line7Text);
        String line8Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line8Text += worldMap[7][i];
        }
        line8.setText(line8Text);
        String line9Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line9Text += worldMap[8][i];
        }
        line9.setText(line9Text);
        String line10Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line10Text += worldMap[9][i];
        }
        line10.setText(line10Text);
        String line11Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line11Text += worldMap[10][i];
        }
        line11.setText(line11Text);
        String line12Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line12Text += worldMap[11][i];
        }
        line12.setText(line12Text);
        String line13Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line13Text += worldMap[12][i];
        }
        line13.setText(line13Text);
        String line14Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line14Text += worldMap[13][i];
        }
        line14.setText(line14Text);
        String line15Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line15Text += worldMap[14][i];
        }
        line15.setText(line15Text);
        String line16Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line16Text += worldMap[15][i];
        }
        line16.setText(line16Text);
        String line17Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line17Text += worldMap[16][i];
        }
        line17.setText(line17Text);
        String line18Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line18Text += worldMap[17][i];
        }
        line18.setText(line18Text);
        String line19Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line19Text += worldMap[18][i];
        }
        line19.setText(line19Text);
        String line20Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line20Text += worldMap[19][i];
        }
        line20.setText(line20Text);
        String line21Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line21Text += worldMap[20][i];
        }
        line21.setText(line21Text);
        String line22Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line22Text += worldMap[21][i];
        }
        line22.setText(line22Text);
        String line23Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line23Text += worldMap[22][i];
        }
        line23.setText(line23Text);
        String line24Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line24Text += worldMap[23][i];
        }
        line24.setText(line24Text);
        String line25Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            line25Text += worldMap[24][i];
        }
        line25.setText(line25Text);
        String line26Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            //line26Text += worldMap[25][i];
        }
        //line26.setText(line26Text);
        String line27Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            //line27Text += worldMap[26][i];
        }
        //line27.setText(line27Text);
        String line28Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            //line28Text += worldMap[27][i];
        }
        //line28.setText(line28Text);
        String line29Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            //line29Text += worldMap[28][i];
        }
        //line29.setText(line29Text);
        String line30Text = "";
        for (byte i = 0; i < worldMap.length; i++) {
            //line30Text += worldMap[29][i];
        }
        //line30.setText(line30Text);

    }
}
