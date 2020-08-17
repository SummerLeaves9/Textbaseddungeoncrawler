package com.example.doome.dungeon_lords;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.doome.dungeon_lords.Gameplay.combatOnly;
import static com.example.doome.dungeon_lords.Gameplay.noSpellSelected;
import static com.example.doome.dungeon_lords.Gameplay.spellNum;

public class Exploration extends AppCompatActivity {

    public static byte thisTownIndex;

    public static byte thisHouseIndex;

    /**
     * Note: Dungeon arrays are of the following form
     * index 0: difficulty of dungeon ranging from 0-3. 4 and up denotes a special encounter other
     * than a full dungeon
     * index 1: number that is mapped to a name for the dungeon
     * index 2: x-coordinate of the dungeon on the map
     * index 3: y-coordinate of the dungeon on the map
     * index 4+: mapped to which enemies reside in the dungeon
     */
    public static byte[][] knownDungeons = new byte[20][0];

    public static boolean[] hasCleared = {false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false};

    private static final String[] dungeonNames = {"the unknown dungeon", "Balthar's Lair",
            "Forest Dungeon", "Mages' Hideaway", "The Twisting Dungeon", "The Moaning Crypt",
            "The Decaying Chambers", "The Serene Caverns", "Wolf's Grotto", "The Marble " +
            "Mines", "The Windy Tombs", "Mountain's Peak Dungeon", "The Jagged Tunnels",
            "Theives' Tomb", "Tomb of the Unknown Warrior", "Underground Ruins", "The Golden " +
            "Maze", "The Scarlet Crypt", "The Restless Depths", "Cells of the Full Moon"};

    private final String[] difficulties = {"easy", "medium", "hard", "Uber"};

    private static final String[] townNames = {"Skyham", "Clodcross", "Unterborn", "Torlen",
            "Ushed", "Zonruk", "Noxhold", "Heatherton", "Poetris", "Nova"};

    private static final String[] houseNames = {"William's House", "Elizabeth's House",
            "Valentine's House", "John's House", "Alice's House"};

    /**
     * The numbers corresponding to the towns the player has visited, along with their coordinates
     */
    public static byte[][] visitedTowns = new byte[10][0];

    /**
     * The numbers corresponding to the houses the player has visited, along with their coordinates
     */
    public static byte[][] visitedHouses = new byte[5][0];

    /**
     * helps distinguish tasks that should only execute the very first time the class is created
     */
    public static boolean mapInitiated = false;

    /**
     * boolean to denote whether the player knows the twist of the game
     */
    public static boolean godIsALie = false;

    /**
     * boolean to denote whether the player is walking on the path or not
     */
    static boolean onPath = false;

    /**
     * the map
     */
    public static char[][] worldMap = new char[25][25];

    /**
     * a modifiable copy of the world map to show the player specifically where they've been
     */
    public static char[][] liveWorldMap = new char[25][25];

    /**
     * the 25 characters to show to the player in their map window
     */
    public static byte[][] mapWindowCoords = new byte[25][2];

    /**
     * The number of steps traversed by the player on this day
     */
    public static byte stepsTraversed = 0;

    /**
     * the number of steps a player can take before they must rest
     */
    final static byte stepsCanTraversePerDay = 10;

    /**
     * Various map markers
     */
    final char player = 'M';
    final char forest = 'J';
    final char dense = '#';
    final char walkway = 'W';
    final char town = 'T';
    final char dungeon = 'D';
    final char centaurCave = 'C';
    final char house = 'H';
    final char MTOlympus = 'O';
    final char nothing = '.';
    final char unseen = '-';

    /**
     * Specifications for the map: the numbers of various locations there should be per map
     */
    final int walkwayNum = 150;
    final byte townNum = 10;
    final byte dungeonNum = 20;
    final byte houseNum = 100;
    final byte centaurCaveNum = 8;

    /**
     * Strings to display each line of the player's map view
     */
    String textLine1;
    String textLine2;
    String textLine3;
    String textLine4;
    String textLine5;

    /**
     * The message in the exploration window that will be changed to display the right text
     */
    String explorationInfo = "";

    /**
     * Message that shows how many spaces the player can walk before they must rest
     */
    String milesBeforeRest = "Miles before exhaustion: " +
            (stepsCanTraversePerDay - stepsTraversed);

    /**
     * Message when a player lands on a NEW dungeon
     */
    String dungeonFindUnknown = "You find a dungeon, " + "! Difficulty: " + ". You mark it on " +
            "your map. Do you want to enter right now?";

    /**
     * Message when a player lands on an ALREADY KNOWN dungeon that they have also finished
     */
    String dungeonFindKnownComplete = "You've returned to " + ". Since you've cleared it out, it " +
            "would be a safe place to camp.";

    /**
     * Message when a player lands on an ALREADY KNOWN dungeon that they have not beaten
     */
    String dungeonFindKnown = "You've returned to " + ". Difficulty: " + ". Do you want to enter " +
            "right now?";

    /**
     * Message when a player lands on a town
     */
    String townFind = "You find a town, " + "! You mark it on your map. Enter?";

    /**
     * Message when the player lands on a known town
     */
    String townReturn = "You've returned to the town of " + ". Enter?";

    /**
     * Message when a player lands on a house
     */
    final String houseFind = "You find a house: you make sure to stay far enough so the people " +
            "living here don't notice you. Approach the house?";

    /**
     * Message when you leave a town
     */
    final String leaveTown = "You leave town and keep walking. ";

    /**
     * Message when a player returns to a previous house
     */
    String houseReturn = "You've found your way back to " + ". They'd probably like to have " +
            "you as company, but you have more important things to do.";

    /**
     * Message when a player lands on Mt Olympus, but they don't know the twist! >:3
     */
    final String MTOlympusFindEarly = "You find Mount Olympus, home of the gods. You mark it on " +
            "your map, but you don't dare climb it: you aren't strong enough yet. ";

    /**
     * Message when a player lands on Mt. Olympus, and they are ready/know the game's twist
     */
    final String MTOlympusFindLate = "You arrive at Mount Olympus, home of the 'gods'. This will" +
            "surely be your toughest battle yet. Are you ready to face them?";

    /**
     * Message displayed when the player tries to move out of bounds
     */
    final String outOfBounds = "You can't go any further!";

    /**
     * Message displayed when the player tries to move but they are out of miles that day
     */
    final String noMoreMiles = "You can't travel any more today: you need to rest.";

    /**
     * The message displayed when you try to go to the spell selection screen, but you have no MP
     */
    public final String noMP = "You have no MP!";
    /**
     * the message displayed when you successfully cast a spell
     */
    public String castSuccess = "You cast...a spell? ";

    TextView mapViewLine1;
    TextView mapViewLine2;
    TextView mapViewLine3;
    TextView mapViewLine4;
    TextView mapViewLine5;
    TextView extraMapViewTop;
    TextView extraMapViewBottom;
    TextView mapMessage;
    TextView milesLeftDisplay;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exploration);
        Gameplay.gameplayInFocus = false;
        mapViewLine1 = findViewById(R.id.mapWindowLine1);
        mapViewLine2 = findViewById(R.id.mapWindowLine2);
        mapViewLine3 = findViewById(R.id.mapWindowLine3);
        mapViewLine4 = findViewById(R.id.mapWindowLine4);
        mapViewLine5 = findViewById(R.id.mapWindowLine5);
        extraMapViewTop = findViewById(R.id.extraMapViewTop);
        extraMapViewBottom = findViewById(R.id.extraMapViewBottom);
        extraMapViewTop.setVisibility(View.GONE);
        extraMapViewBottom.setVisibility(View.GONE);
        mapMessage = findViewById(R.id.mapMessage);
        milesLeftDisplay = findViewById(R.id.stepsLeftToday);
        if (!mapInitiated) {
            knownDungeons[0] = Gameplay.firstDungeon;
            hasCleared[0] = true;
            generateMapThisGame();
            initializePlayerStart();
            mapInitiated = true;
        } else {
            setMapWindow();
            setMilesLeft();
        }
        boolean fromTown = getIntent().getBooleanExtra("fromTown", false);
        if (fromTown) {
            explorationInfo = leaveTown;
        }
        Outside.isStartingOrWaking = false;
        setMapMessage();
        configureNextButton();
    }

    private void configureNextButton() {
        Button goNorthButton = findViewById(R.id.goNorthButton);
        Button goSouthButton = findViewById(R.id.goSouthButton);
        Button goEastButton = findViewById(R.id.goEastButton);
        Button goWestButton = findViewById(R.id.goWestButton);
        Button viewWorldMapButton = findViewById(R.id.viewWorldMap);
        Button explorationCampingButton = findViewById(R.id.explorationCampButton);
        Button spellButton = findViewById(R.id.explorationSpellButton);
        Button castButton = findViewById(R.id.explorationCastButton);
        Button sayYesButton = findViewById(R.id.yesButtonExploration);
        Button sayNoButton = findViewById(R.id.noButtonExploration);
        Button continueButton = findViewById(R.id.explorationContinueButton);
        sayYesButton.setVisibility(View.GONE);
        sayNoButton.setVisibility(View.GONE);
        continueButton.setVisibility(View.GONE);
        viewWorldMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Exploration.this, ViewWorldMap.class));
            }
        });
        explorationCampingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Outside.isCamping = true;
                Spell.usedVision = false;
                Intent intent = new Intent(Exploration.this, Sleeping.class);
                //intent.putExtra("fromDungeon", false);
                startActivity(intent);
            }
        });

        goNorthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movePlayer((byte) 0);
            }
        });
        goSouthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movePlayer((byte) 1);
            }
        });
        goEastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movePlayer((byte) 2);
            }
        });
        goWestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movePlayer((byte) 3);
            }
        });
        spellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spell();
            }
        });
        castButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cast(spellNum);
            }
        });
        sayYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (worldMap[mapWindowCoords[12][0]][mapWindowCoords[12][1]]) {
                    case dungeon:
                        Spell.usedVision = false;
                        stepsTraversed = 10;
                        byte i = 0;
                        while (knownDungeons[i][2] != mapWindowCoords[12][0] || knownDungeons[i][3] != mapWindowCoords[12][1]) {
                            i++;
                        }
                        Gameplay.thisDungeon = knownDungeons[i];
                        hasCleared[i] = true;
                        startActivity(new Intent(Exploration.this, Gameplay.class));
                        break;
                    case town:
                        Dialogue.atHouse = false;
                        startActivity(new Intent(Exploration.this, Town.class));
                        break;
                    case house:
                        Dialogue.atHouse = true;
                        if (thisHouseIndex == 0) {
                            EnterNames.thisPlayer.defenseValue += 2;
                            EnterNames.thisPlayer.setAllStats();
                        }
                        startActivity(new Intent(Exploration.this, Dialogue.class));
                        break;
                    case MTOlympus:
                        Dialogue.atOlympus = true;
                        startActivity(new Intent(Exploration.this, Dialogue.class));
                        break;
                }
                mHandler.postDelayed(delayDisplayUpdate, 2000);
            }
        });
        sayNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explorationInfo = "";
                setMapMessage();
                toggleYesAndNoInvisible();
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleContinueInvisible();
                explorationInfo = "";
                setMapMessage();
            }
        });
    }

    @Override
    public void onBackPressed() {}

    private void setMapMessage() {
        mapMessage.setText(explorationInfo);
        if (explorationInfo.equals("")) {
            mapMessage.setVisibility(View.GONE);
        } else {
            mapMessage.setVisibility(View.VISIBLE);
        }
    }

    private void setMilesLeft() {
        milesBeforeRest = "Miles before exhaustion: " + (stepsCanTraversePerDay - stepsTraversed);
        milesLeftDisplay.setText(milesBeforeRest);
    }

    private final Runnable delayDisplayUpdate = new Runnable() {
        public void run() {
            // do whatever you want to change here, like:
            explorationInfo = "";
            setMapMessage();
            toggleYesAndNoInvisible();
        }
    };

    private final Handler mHandler = new Handler();

    /**
     * One of the coolest parts of this game I've written for a while. Generates the map for this
     * game with the following parameters:
     * -All towns must be connected to walkways
     * -Dungeons MUST be surrounded by forest
     * -there should be around 80 walkways
     * -there should be no more than 4 disconnected networks of walkways (3 should be somewhat
     * rare, 4 should be rare)
     * -there may never be 4 walkway tiles in a square
     * -There are 10 towns
     * -There are anywhere from 10 to 20 dungeons
     * -There are 5 houses (may be placed next to a walkway, in forest, next to a town, or
     * anywhere else)
     */
    private void generateMapThisGame() {
        knownDungeons[0] = Gameplay.firstDungeon;
        //Initially set all tiles to forest
        for (byte i = 0; i < worldMap.length; i++) {
            for (byte j = 0; j < worldMap[0].length; j++) {
                worldMap[i][j] = forest;
                liveWorldMap[i][j] = unseen;
            }
        }
        //randomly placing the seeds for the routes
        byte networkNum = 4;
        byte[][] currentWalkwayCoords = new byte[networkNum][2];
        for (byte i = 0; i < networkNum; i++) {
            byte xCoord;
            byte yCoord;
            switch(i) {
                case 0:
                    xCoord = (byte) ((Math.random() * 4) + 4);
                    yCoord = (byte) ((Math.random() * 4) + 4);
                    break;
                case 1:
                    xCoord = (byte) ((Math.random() * 4) + 16);
                    yCoord = (byte) ((Math.random() * 4) + 4);
                    break;
                case 2:
                    xCoord = (byte) ((Math.random() * 4) + 4);
                    yCoord = (byte) ((Math.random() * 4) + 16);
                    break;
                default:
                    xCoord = (byte) ((Math.random() * 4) + 16);
                    yCoord = (byte) ((Math.random() * 4) + 16);
                    break;
            }
            worldMap[xCoord][yCoord] = walkway;
            currentWalkwayCoords[i][0] = xCoord;
            currentWalkwayCoords[i][1] = yCoord;
        }
        //pathfinding random routes such that there are never 4 walkway tiles in a square
        //If there are 2 walkway networks, each will have 2 branches of varying length.
        //If there are more than 2, each will have 1 branch
        for (int i = 0; i < networkNum; i++) {
            byte branch1Length = (byte) (5 + (Math.random()) * 4);
            byte branch2Length = (byte) (5 + (Math.random()) * 4);
            byte branch1Start = (byte) (1 + Math.random() * ((walkwayNum / networkNum) - 1));
            byte branch2Start = (byte) (1 + Math.random() * ((walkwayNum / networkNum) - 1));
            for (int j = 0; j < ((walkwayNum / networkNum) - 1); j++) {
                //0 == North, 1 == South, 2 == East, 3 == West
                if (j == 0) {
                    byte direction = (byte) (Math.random() * 4);
                    switch (direction) {
                        case 0:
                            if (placeTileIfValid(currentWalkwayCoords[i][0],
                                    (byte) (currentWalkwayCoords[i][1] + 1), walkway)) {
                                currentWalkwayCoords[i][1] += 1;
                                break;
                            }
                        case 1:
                            if (placeTileIfValid(currentWalkwayCoords[i][0],
                                    (byte) (currentWalkwayCoords[i][1] - 1), walkway)) {
                                currentWalkwayCoords[i][1] -= 1;
                                break;
                            }
                        case 2:
                            if (placeTileIfValid((byte) (currentWalkwayCoords[i][0] + 1),
                                    currentWalkwayCoords[i][1], walkway)) {
                                currentWalkwayCoords[i][0] += 1;
                                break;
                            }
                        case 3:
                            if (placeTileIfValid((byte) (currentWalkwayCoords[i][0] - 1),
                                    currentWalkwayCoords[i][1], walkway)) {
                                currentWalkwayCoords[i][0] -= 1;
                                break;
                            }
                    }
                } else {
                    if (j == branch1Start) {
                        startNewBranch(branch1Length, currentWalkwayCoords[i][0],
                                currentWalkwayCoords[i][1]);
                        j += branch1Length;
                    } else if (j == branch2Start) {
                        startNewBranch(branch2Length, currentWalkwayCoords[i][0],
                                currentWalkwayCoords[i][1]);
                        j += branch2Length;
                    }
                    byte direction = (byte) (Math.random() * 4);
                    switch (direction) {
                        case 0:
                            if (placeTileIfValid(currentWalkwayCoords[i][0],
                                    (byte) (currentWalkwayCoords[i][1] + 1), walkway)) {
                                currentWalkwayCoords[i][1] += 1;
                                break;
                            }
                        case 1:
                            if (placeTileIfValid(currentWalkwayCoords[i][0],
                                    (byte) (currentWalkwayCoords[i][1] - 1), walkway)) {
                                currentWalkwayCoords[i][1] -= 1;
                                break;
                            }
                        case 2:
                            if (placeTileIfValid((byte) (currentWalkwayCoords[i][0] + 1),
                                    currentWalkwayCoords[i][1], walkway)) {
                                currentWalkwayCoords[i][0] += 1;
                                break;
                            }
                        case 3:
                            if (placeTileIfValid((byte) (currentWalkwayCoords[i][0] - 1),
                                    currentWalkwayCoords[i][1], walkway)) {
                                currentWalkwayCoords[i][0] -= 1;
                                break;
                            }
                    }
                }
            }
        }
        //Randomly place towns on border of path, ensuring two towns are never next to each other
        for (byte i = 0; i < townNum; i++) {
            byte thisTownX = (byte) (Math.random() * 25);
            byte thisTownY = (byte) (Math.random() * 25);
            boolean isWalkwayAdjacent = false;
            for (byte walkWayAdjacent = 0; walkWayAdjacent < 4; walkWayAdjacent++) {
                switch (walkWayAdjacent) {
                    case 0:
                        if (thisTownX > 0) {
                            if (worldMap[thisTownX - 1][thisTownY] == walkway) {
                                isWalkwayAdjacent = true;
                            }
                        }
                    case 1:
                        if (thisTownY > 0) {
                            if (worldMap[thisTownX][thisTownY - 1] == walkway) {
                                isWalkwayAdjacent = true;
                            }
                        }
                    case 2:
                        if (thisTownX < worldMap.length - 1) {
                            if (worldMap[thisTownX + 1][thisTownY] == walkway) {
                                isWalkwayAdjacent = true;
                            }
                        }
                    case 3:
                        if (thisTownY < worldMap.length - 1) {
                            if (worldMap[thisTownX][thisTownY + 1] == walkway) {
                                isWalkwayAdjacent = true;
                            }
                        }
                }
            }
            if (worldMap[thisTownX][thisTownY] == forest && isWalkwayAdjacent) {
                worldMap[thisTownX][thisTownY] = town;
            } else {
                i--;
            }
        }

        //Randomly dot dungeons across the map
        byte playerStartDungeon = /*(byte) (Math.random() * dungeonNum)*/ 0;
        for (byte i = 0; i < /*dungeonNum*/ 1; i++) {
            byte thisDungeonX = (byte) (Math.random() * 25);
            byte thisDungeonY = (byte) (Math.random() * 25);
            boolean isDungeonOrWalkWayAdjacent = false;
            byte coordCounter = 0;
            for (; coordCounter < 8; coordCounter++) {
                switch (coordCounter) {
                    case 0:
                        if (thisDungeonX > 0 && thisDungeonY > 0) {
                            char charToCheck = worldMap[thisDungeonX - 1][thisDungeonY - 1];
                            if (charToCheck == dungeon || charToCheck == walkway) {
                                isDungeonOrWalkWayAdjacent = true;
                            }
                        }
                        break;
                    case 1:
                        if (thisDungeonX > 0) {
                            char charToCheck = worldMap[thisDungeonX - 1][thisDungeonY];
                            if (charToCheck == dungeon || charToCheck == walkway) {
                                isDungeonOrWalkWayAdjacent = true;
                            }
                        }
                        break;
                    case 2:
                        if (thisDungeonX > 0 && thisDungeonY < worldMap.length - 1) {
                            char charToCheck = worldMap[thisDungeonX - 1][thisDungeonY + 1];
                            if (charToCheck == dungeon || charToCheck == walkway) {
                                isDungeonOrWalkWayAdjacent = true;
                            }
                        }
                        break;
                    case 3:
                        if (thisDungeonY < worldMap.length - 1) {
                            char charToCheck = worldMap[thisDungeonX][thisDungeonY + 1];
                            if (charToCheck == dungeon || charToCheck == walkway) {
                                isDungeonOrWalkWayAdjacent = true;
                            }
                        }
                        break;
                    case 4:
                        if (thisDungeonY < worldMap.length - 1 && thisDungeonX < worldMap.length - 1) {
                            char charToCheck = worldMap[thisDungeonX + 1][thisDungeonY + 1];
                            if (charToCheck == dungeon || charToCheck == walkway) {
                                isDungeonOrWalkWayAdjacent = true;
                            }
                        }
                        break;
                    case 5:
                        if (thisDungeonX < worldMap.length - 1) {
                            char charToCheck = worldMap[thisDungeonX + 1][thisDungeonY];
                            if (charToCheck == dungeon || charToCheck == walkway) {
                                isDungeonOrWalkWayAdjacent = true;
                            }
                        }
                        break;
                    case 6:
                        if (thisDungeonX < worldMap.length - 1 && thisDungeonY > 0) {
                            char charToCheck = worldMap[thisDungeonX + 1][thisDungeonY - 1];
                            if (charToCheck == dungeon || charToCheck == walkway) {
                                isDungeonOrWalkWayAdjacent = true;
                            }
                        }
                        break;
                    case 7:
                        if (thisDungeonY > 0) {
                            char charToCheck = worldMap[thisDungeonX][thisDungeonY - 1];
                            if (charToCheck == dungeon || charToCheck == walkway) {
                                isDungeonOrWalkWayAdjacent = true;
                            }
                        }
                        break;
                }
                if (isDungeonOrWalkWayAdjacent) {
                    break;
                }
            }
            if (worldMap[thisDungeonX][thisDungeonY] == forest && !isDungeonOrWalkWayAdjacent) {
                worldMap[thisDungeonX][thisDungeonY] = dungeon;
                if (i == playerStartDungeon) {
                    mapWindowCoords[12][0] = thisDungeonX;
                    mapWindowCoords[12][1] = thisDungeonY;
                    knownDungeons[0][2] = thisDungeonX;
                    knownDungeons[0][3] = thisDungeonY;
                }
            } else {
                i--;
            }
        }

        //dotting houses around the map
        for (byte i = 0; i < houseNum; i++) {
            byte thisHouseX = (byte) (Math.random() * worldMap.length);
            byte thisHouseY = (byte) (Math.random() * worldMap.length);
            worldMap[thisHouseX][thisHouseY] = house;
        }
        /*
        //placing Mount Olympus
        byte MTOlympusX = (byte) (Math.random() * worldMap.length);
        byte MTOlympusY = (byte) (Math.random() * worldMap[0].length);
        while (Math.sqrt((mapWindowCoords[12][0] - MTOlympusX) ^ 2 + (mapWindowCoords[12][1] - MTOlympusY) ^ 2) > 12) {
            MTOlympusX = (byte) (Math.random() * worldMap.length);
            MTOlympusY = (byte) (Math.random() * worldMap[0].length);
        }
        worldMap[MTOlympusX][MTOlympusY] = MTOlympus;
        */
    }

    private boolean placeTileIfValid(byte xCoord, byte yCoord, char tileType) {
        if (xCoord >= 0 && xCoord < worldMap.length && yCoord >= 0 && yCoord < worldMap[0].length && worldMap[xCoord][yCoord] == forest) {
            byte consecWalkwayCounter = 0;
            for (byte coordCounter = 0; coordCounter < 8; coordCounter++) {
                switch (coordCounter) {
                    case 0:
                        if (xCoord > 0 && yCoord > 0) {
                            if (worldMap[xCoord - 1][yCoord - 1] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 1:
                        if (xCoord > 0) {
                            if (worldMap[xCoord - 1][yCoord] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 2:
                        if (xCoord > 0 && yCoord < worldMap.length - 1) {
                            if (worldMap[xCoord - 1][yCoord + 1] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 3:
                        if (yCoord < worldMap.length - 1) {
                            if (worldMap[xCoord][yCoord + 1] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 4:
                        if (yCoord < worldMap.length - 1 && xCoord < worldMap.length - 1) {
                            if (worldMap[xCoord + 1][yCoord + 1] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 5:
                        if (xCoord < worldMap.length - 1) {
                            if (worldMap[xCoord + 1][yCoord] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 6:
                        if (xCoord < worldMap.length - 1 && yCoord > 0) {
                            if (worldMap[xCoord + 1][yCoord - 1] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 7:
                        if (yCoord > 0) {
                            if (worldMap[xCoord][yCoord - 1] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                }
                if (consecWalkwayCounter > 2) {
                    return false;
                }
            }
            consecWalkwayCounter = 0;
            for (byte coordCounter = 0; coordCounter < 8; coordCounter++) {
                switch (coordCounter) {
                    case 0:
                        if (yCoord < worldMap.length - 1 && xCoord < worldMap.length - 1) {
                            if (worldMap[xCoord + 1][yCoord + 1] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 1:
                        if (xCoord < worldMap.length - 1) {
                            if (worldMap[xCoord + 1][yCoord] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 2:
                        if (xCoord < worldMap.length - 1 && yCoord > 0) {
                            if (worldMap[xCoord + 1][yCoord - 1] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 3:
                        if (yCoord > 0) {
                            if (worldMap[xCoord][yCoord - 1] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 4:
                        if (xCoord > 0 && yCoord > 0) {
                            if (worldMap[xCoord - 1][yCoord - 1] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 5:
                        if (xCoord > 0) {
                            if (worldMap[xCoord - 1][yCoord] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 6:
                        if (xCoord > 0 && yCoord < worldMap.length - 1) {
                            if (worldMap[xCoord - 1][yCoord + 1] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                    case 7:
                        if (yCoord < worldMap.length - 1) {
                            if (worldMap[xCoord][yCoord + 1] == walkway) {
                                consecWalkwayCounter++;
                            } else {
                                consecWalkwayCounter = 0;
                            }
                        }
                        break;
                }
                if (consecWalkwayCounter > 2) {
                    return false;
                }
            }
            worldMap[xCoord][yCoord] = tileType;
            return true;
        } else {
            return false;
        }
    }

    private void startNewBranch(byte branchLength, byte initXCoord, byte initYCoord) {
        byte currentXCoord = initXCoord;
        byte currentYCoord = initYCoord;
        for (byte i = 0; i < branchLength; i++) {
            byte direction = (byte) (Math.random() * 4);
            switch (direction) {
                case 0:
                    if (placeTileIfValid(currentXCoord, (byte) (currentYCoord + 1), walkway)) {
                        currentYCoord += 1;
                        break;
                    }
                case 1:
                    if (placeTileIfValid(currentXCoord, (byte) (currentYCoord - 1), walkway)) {
                        currentYCoord -= 1;
                        break;
                    }
                case 2:
                    if (placeTileIfValid((byte) (currentXCoord + 1), currentYCoord, walkway)) {
                        currentXCoord += 1;
                        break;
                    }
                case 3:
                    if (placeTileIfValid((byte) (currentXCoord - 1), currentYCoord, walkway)) {
                        currentXCoord -= 1;
                        break;
                    }
            }
        }
    }

    public static void examineMap() {
        byte tryCounter = 0;
        for (byte i = 0; i < 5; i++) {
            byte xOffset = (byte) (Math.random() * 5);
            byte yOffset = (byte) (Math.random() * 5);
            double xCoinFlip = Math.random();
            double yCoinFlip = Math.random();
            if (xCoinFlip < .5) {
                xOffset *= -1;
            }
            if (yCoinFlip < .5) {
                yOffset *= -1;
            }
            byte xTry = (byte) (visitedTowns[thisTownIndex][1] + xOffset);
            byte yTry = (byte) (visitedTowns[thisTownIndex][2] + yOffset);
            if (xTry < 0) {
                xTry = 0;
            }
            if (yTry < 0) {
                yTry = 0;
            }
            if (xTry >= worldMap.length) {
                xTry = (byte) (worldMap.length - 1);
            }
            if (yTry >= worldMap.length) {
                yTry = (byte) (worldMap[0].length - 1);
            }
            if (worldMap[xTry][yTry] == 'J') {
                if (i < 4) {
                    worldMap[xTry][yTry] = 'D';
                    liveWorldMap[xTry][yTry] = 'D';
                } else {
                    worldMap[xTry][yTry] = 'H';
                    liveWorldMap[xTry][yTry] = 'H';
                }
            } else {
                i--;
            }
            tryCounter++;
            if (tryCounter > 100) {
                break;
            }
        }
    }

    private void initializePlayerStart() {
        for (byte i = 0; i < mapWindowCoords.length; i++) {
            byte currentX = -1;
            byte currentY = -1;
            switch (i) {
                case 0:
                    currentX = (byte) (mapWindowCoords[12][0] - 2);
                    currentY = (byte) (mapWindowCoords[12][1] - 2);
                    break;
                case 1:
                    currentX = (byte) (mapWindowCoords[12][0] - 2);
                    currentY = (byte) (mapWindowCoords[12][1] - 1);
                    break;
                case 2:
                    currentX = (byte) (mapWindowCoords[12][0] - 2);
                    currentY = mapWindowCoords[12][1];
                    break;
                case 3:
                    currentX = (byte) (mapWindowCoords[12][0] - 2);
                    currentY = (byte) (mapWindowCoords[12][1] + 1);
                    break;
                case 4:
                    currentX = (byte) (mapWindowCoords[12][0] - 2);
                    currentY = (byte) (mapWindowCoords[12][1] + 2);
                    break;
                case 5:
                    currentX = (byte) (mapWindowCoords[12][0] - 1);
                    currentY = (byte) (mapWindowCoords[12][1] - 2);
                    break;
                case 6:
                    currentX = (byte) (mapWindowCoords[12][0] - 1);
                    currentY = (byte) (mapWindowCoords[12][1] - 1);
                    break;
                case 7:
                    currentX = (byte) (mapWindowCoords[12][0] - 1);
                    currentY = mapWindowCoords[12][1];
                    break;
                case 8:
                    currentX = (byte) (mapWindowCoords[12][0] - 1);
                    currentY = (byte) (mapWindowCoords[12][1] + 1);
                    break;
                case 9:
                    currentX = (byte) (mapWindowCoords[12][0] - 1);
                    currentY = (byte) (mapWindowCoords[12][1] + 2);
                    break;
                case 10:
                    currentX = mapWindowCoords[12][0];
                    currentY = (byte) (mapWindowCoords[12][1] - 2);
                    break;
                case 11:
                    currentX = mapWindowCoords[12][0];
                    currentY = (byte) (mapWindowCoords[12][1] - 1);
                    break;
                case 12:
                    break;
                case 13:
                    currentX = mapWindowCoords[12][0];
                    currentY = (byte) (mapWindowCoords[12][1] + 1);
                    break;
                case 14:
                    currentX = mapWindowCoords[12][0];
                    currentY = (byte) (mapWindowCoords[12][1] + 2);
                    break;
                case 15:
                    currentX = (byte) (mapWindowCoords[12][0] + 1);
                    currentY = (byte) (mapWindowCoords[12][1] - 2);
                    break;
                case 16:
                    currentX = (byte) (mapWindowCoords[12][0] + 1);
                    currentY = (byte) (mapWindowCoords[12][1] - 1);
                    break;
                case 17:
                    currentX = (byte) (mapWindowCoords[12][0] + 1);
                    currentY = mapWindowCoords[12][1];
                    break;
                case 18:
                    currentX = (byte) (mapWindowCoords[12][0] + 1);
                    currentY = (byte) (mapWindowCoords[12][1] + 1);
                    break;
                case 19:
                    currentX = (byte) (mapWindowCoords[12][0] + 1);
                    currentY = (byte) (mapWindowCoords[12][1] + 2);
                    break;
                case 20:
                    currentX = (byte) (mapWindowCoords[12][0] + 2);
                    currentY = (byte) (mapWindowCoords[12][1] - 2);
                    break;
                case 21:
                    currentX = (byte) (mapWindowCoords[12][0] + 2);
                    currentY = (byte) (mapWindowCoords[12][1] - 1);
                    break;
                case 22:
                    currentX = (byte) (mapWindowCoords[12][0] + 2);
                    currentY = mapWindowCoords[12][1];
                    break;
                case 23:
                    currentX = (byte) (mapWindowCoords[12][0] + 2);
                    currentY = (byte) (mapWindowCoords[12][1] + 1);
                    break;
                case 24:
                    currentX = (byte) (mapWindowCoords[12][0] + 2);
                    currentY = (byte) (mapWindowCoords[12][1] + 2);
                    break;
            }
            if (i != 12) {
                mapWindowCoords[i][0] = currentX;
                mapWindowCoords[i][1] = currentY;
            }
        }
        char toTest = worldMap[mapWindowCoords[12][0]][mapWindowCoords[12][1]];
        if (toTest == town || toTest == walkway) {
            onPath = true;
        } else {
            onPath = false;
        }
        setMapWindow();
        setMilesLeft();
    }

    private void movePlayer(byte direction) {
        if (stepsTraversed == stepsCanTraversePerDay) {
            explorationInfo = noMoreMiles;
            toggleContinueVisible();
        } else {
            switch (direction) {
                case 0:
                    if (mapWindowCoords[12][0] == 0) {
                        explorationInfo = outOfBounds;
                        setMapMessage();
                        toggleContinueVisible();
                        return;
                    }
                    for (byte i = 0; i < mapWindowCoords.length; i++) {
                        mapWindowCoords[i][0]--;
                    }
                    break;
                case 1:
                    if (mapWindowCoords[12][0] == (byte) (worldMap.length - 1)) {
                        explorationInfo = outOfBounds;
                        setMapMessage();
                        toggleContinueVisible();
                        return;
                    }
                    for (byte i = 0; i < mapWindowCoords.length; i++) {
                        mapWindowCoords[i][0]++;
                    }
                    break;
                case 2:
                    if (mapWindowCoords[12][1] == (byte) (worldMap[0].length - 1)) {
                        explorationInfo = outOfBounds;
                        setMapMessage();
                        toggleContinueVisible();
                        return;
                    }
                    for (byte i = 0; i < mapWindowCoords.length; i++) {
                        mapWindowCoords[i][1]++;
                    }
                    break;
                case 3:
                    if (mapWindowCoords[12][1] == 0) {
                        explorationInfo = outOfBounds;
                        setMapMessage();
                        toggleContinueVisible();
                        return;
                    }
                    for (byte i = 0; i < mapWindowCoords.length; i++) {
                        mapWindowCoords[i][1]--;
                    }
                    break;
            }

            explorationInfo = "";

            switch (worldMap[mapWindowCoords[12][0]][mapWindowCoords[12][1]]) {
                case town:
                    onPath = true;
                    startMapEvent(town);
                    break;
                case dungeon:
                    onPath = false;
                    startMapEvent(dungeon);
                    break;
                case house:
                    onPath = false;
                    startMapEvent(house);
                    break;
                case MTOlympus:
                    onPath = false;
                    startMapEvent(MTOlympus);
                    break;
                case walkway:
                    onPath = true;
                    break;
                case forest:
                    onPath = false;
                    break;
            }
            stepsTraversed++;
            milesBeforeRest =
                    "Miles before exhaustion: " + (stepsCanTraversePerDay - stepsTraversed);
        }


        setMapWindow();
        setMapMessage();
        setMilesLeft();
    }

    private void setMapWindow() {
        textLine1 = "";
        textLine2 = "";
        textLine3 = "";
        textLine4 = "";
        textLine5 = "";
        byte i = 0;
        for (; i < 5; i++) {
            if (onPath || Spell.usedVision) {
                if (mapWindowCoords[i][0] < 0 || mapWindowCoords[i][0] > worldMap.length - 1 ||
                        mapWindowCoords[i][1] < 0 || mapWindowCoords[i][1] > worldMap.length - 1) {
                    textLine1 += nothing;
                } else {
                    char toReveal = worldMap[mapWindowCoords[i][0]][mapWindowCoords[i][1]];
                    textLine1 += toReveal;
                    liveWorldMap[mapWindowCoords[i][0]][mapWindowCoords[i][1]] = toReveal;
                }
            } else {
                textLine1 += dense;
            }
        }
        for (; i < 10; i++) {
            if (onPath || (i > 5 && i < 9) || Spell.usedVision) {
                if (mapWindowCoords[i][0] < 0 || mapWindowCoords[i][0] > worldMap.length - 1 ||
                        mapWindowCoords[i][1] < 0 || mapWindowCoords[i][1] > worldMap.length - 1) {
                    textLine2 += nothing;
                } else {
                    char toReveal = worldMap[mapWindowCoords[i][0]][mapWindowCoords[i][1]];
                    textLine2 += toReveal;
                    liveWorldMap[mapWindowCoords[i][0]][mapWindowCoords[i][1]] = toReveal;
                }
            } else {
                textLine2 += dense;
            }
        }
        for (; i < 15; i++) {
            if (onPath || (i > 10 && i < 14) || Spell.usedVision) {
                if (i == 12) {
                    textLine3 += player;
                    liveWorldMap[mapWindowCoords[i][0]][mapWindowCoords[i][1]] =
                            worldMap[mapWindowCoords[i][0]][mapWindowCoords[i][1]];
                } else {
                    if (mapWindowCoords[i][0] < 0 || mapWindowCoords[i][0] > worldMap.length - 1 ||
                            mapWindowCoords[i][1] < 0 || mapWindowCoords[i][1] > worldMap.length - 1) {
                        textLine3 += nothing;
                    } else {
                        char toReveal = worldMap[mapWindowCoords[i][0]][mapWindowCoords[i][1]];
                        textLine3 += toReveal;
                        liveWorldMap[mapWindowCoords[i][0]][mapWindowCoords[i][1]] = toReveal;
                    }
                }
            } else {
                textLine3 += dense;
            }
        }
        for (; i < 20; i++) {
            if (onPath || (i > 15 && i < 19) || Spell.usedVision) {
                if (mapWindowCoords[i][0] < 0 || mapWindowCoords[i][0] > worldMap.length - 1 ||
                        mapWindowCoords[i][1] < 0 || mapWindowCoords[i][1] > worldMap.length - 1) {
                    textLine4 += nothing;
                } else {
                    char toReveal = worldMap[mapWindowCoords[i][0]][mapWindowCoords[i][1]];
                    textLine4 += toReveal;
                    liveWorldMap[mapWindowCoords[i][0]][mapWindowCoords[i][1]] = toReveal;
                }
            } else {
                textLine4 += dense;
            }
        }
        for (; i < 25; i++) {
            if (onPath || Spell.usedVision) {
                if (mapWindowCoords[i][0] < 0 || mapWindowCoords[i][0] > worldMap.length - 1 ||
                        mapWindowCoords[i][1] < 0 || mapWindowCoords[i][1] > worldMap.length - 1) {
                    textLine5 += nothing;
                } else {
                    char toReveal = worldMap[mapWindowCoords[i][0]][mapWindowCoords[i][1]];
                    textLine5 += toReveal;
                    liveWorldMap[mapWindowCoords[i][0]][mapWindowCoords[i][1]] = toReveal;
                }
            } else {
                textLine5 += dense;
            }
        }
        mapViewLine1.setText(textLine1);
        mapViewLine2.setText(textLine2);
        mapViewLine3.setText(textLine3);
        mapViewLine4.setText(textLine4);
        mapViewLine5.setText(textLine5);
    }

    private void startMapEvent(char event) {
        boolean shouldToggleYesNo = true;
        switch (event) {
            case town:
                boolean newTown = false;
                thisTownIndex = 0;
                for (; thisTownIndex < visitedTowns.length; thisTownIndex++) {
                    if (visitedTowns[thisTownIndex].length == 0) {
                        newTown = true;
                        break;
                    }
                    if (visitedTowns[thisTownIndex][1] == mapWindowCoords[12][0] &&
                            visitedTowns[thisTownIndex][2] == mapWindowCoords[12][1]) {
                        townReturn = "You've returned to the town of " +
                                getTownName(visitedTowns[thisTownIndex][0]) + ". Enter?";
                        explorationInfo = townReturn;
                        break;
                    }
                }
                if (newTown) {
                    visitedTowns[thisTownIndex] = generateTown();
                    townFind = "You find a town, " + getTownName(visitedTowns[thisTownIndex][0]) + "! You " +
                            "mark it on your map. Enter?";
                    explorationInfo = townFind;
                }
                break;
            case dungeon:
                boolean newDungeon = false;
                byte i = 0;
                for (; i < knownDungeons.length; i++) {
                    if (knownDungeons[i].length == 0) {
                        newDungeon = true;
                        break;
                    }
                    boolean dungeonIsKnown = (knownDungeons[i][2] == mapWindowCoords[12][0] &&
                            knownDungeons[i][3] == mapWindowCoords[12][1]);
                    if (dungeonIsKnown && hasCleared[i]) {
                        shouldToggleYesNo = false;
                        dungeonFindKnownComplete = "You've returned to " +
                                getDungeonName(knownDungeons[i][1]) + ". Since you've cleared it " +
                                "out, it would be a safe place to camp.";
                        explorationInfo = dungeonFindKnownComplete;
                        toggleContinueVisible();
                        break;
                    } else if (dungeonIsKnown) {
                        dungeonFindKnown = "You've returned to " +
                                getDungeonName(knownDungeons[i][1]) + ". Difficulty: " +
                                getDungeonDifficulty(knownDungeons[i][0]) + ". Do you want to " +
                                "enter right now?";
                        explorationInfo = dungeonFindKnown;
                        break;
                    }
                }
                if (newDungeon) {
                    knownDungeons[i] = generateDungeon();
                    dungeonFindUnknown = "You find a dungeon, " +
                            getDungeonName(knownDungeons[i][1]) +
                            "! Difficulty: " +
                            getDungeonDifficulty(knownDungeons[i][0]) +
                            ". You mark it on your map. Do you want to enter right now?";
                    explorationInfo = dungeonFindUnknown;
                }
                break;
            case house:
                boolean newHouse = false;
                thisHouseIndex = 0;
                for (; thisHouseIndex < visitedHouses.length; thisHouseIndex++) {
                    if (visitedHouses[thisHouseIndex].length == 0) {
                        newHouse = true;
                        break;
                    }
                    if (visitedHouses[thisHouseIndex][0] == mapWindowCoords[12][0] &&
                            visitedHouses[thisHouseIndex][1] == mapWindowCoords[12][1]) {
                        shouldToggleYesNo = false;
                        houseReturn = "You've found your way back to " + houseNames[thisHouseIndex] + ". " +
                                "They'd probably like to have you as company, but you have more " +
                                "important things to do.";
                        explorationInfo = houseReturn;
                        toggleContinueVisible();
                        break;
                    }
                }
                if (newHouse) {
                    visitedHouses[thisHouseIndex] = generateHouse();
                    explorationInfo = houseFind;
                }
                if (thisHouseIndex == 2) {
                    godIsALie = true;
                }
                break;
            case MTOlympus:
                if (!godIsALie) {
                    explorationInfo = MTOlympusFindEarly;
                } else {
                    explorationInfo = MTOlympusFindLate;
                }
                break;
        }
        setMapMessage();
        if (shouldToggleYesNo) {
            toggleYesAndNoVisible();
        }
    }

    public static String getTownName(byte i) {
        return townNames[i];
    }

    public static String getDungeonName(byte i) {
        return dungeonNames[i];
    }

    private String getDungeonDifficulty(byte i) {
        return difficulties[i];
    }

    private byte[] generateDungeon() {
        byte[] dungeon = new byte[(int) (8 + (Math.random() * 4))];
        double coinFlip = Math.random() * 4;
        if (EnterNames.thisPlayer.statSum < 45) {
            if (coinFlip < 4) {
                dungeon[0] = 0;
            } else {
                dungeon[0] = 1;
            }
        } else if (EnterNames.thisPlayer.statSum < 55) {
            if (coinFlip < 4) {
                dungeon[0] = 1;
            } else {
                dungeon[0] = 2;
            }
        } else if (EnterNames.thisPlayer.statSum < 65) {
            if (coinFlip < 4) {
                dungeon[0] = 2;
            } else {
                dungeon[0] = 3;
            }
        } else {
            dungeon[0] = 3;
        }
        byte thisName = (byte) (Math.random() * dungeonNames.length);
        //check that this name has not been used yet
        for (byte i = 0; i < knownDungeons.length; i++) {
            if (knownDungeons[i].length == 0) {
                break;
            } else if (knownDungeons[i][1] == thisName) {
                thisName = (byte) (Math.random() * dungeonNames.length);
                i = -1;
            }
        }
        dungeon[1] = thisName;
        dungeon[2] = mapWindowCoords[12][0];
        dungeon[3] = mapWindowCoords[12][1];
        //setting enemies to null indicies so they may be changed to appropriate numbers later
        for (byte i = 4; i < dungeon.length; i++) {
            dungeon[i] = -1;
        }
        switch (thisName) {
            case 3:
                dungeon[4] = 6;
                break;
            case 5:
                dungeon[4] = 3;
                break;
            case 8:
                dungeon[4] = 14;
                break;
            case 11:
                if (dungeon[0] > 1) {
                    dungeon[4] = 18;
                } else {
                    dungeon[4] = 14;
                }
                break;
        }
        byte strongEnemyNum = (byte) (dungeon[0] - 1);
        for (byte i = 4; i < dungeon.length; i++) {
            if (dungeon[i] == -1) {
                if (dungeon.length - i <= strongEnemyNum) {
                    dungeon[i] = (byte) (7 + Math.random() * 7);
                } else {
                    dungeon[i] = (byte) (Math.random() * 7);
                }
            }
        }
        return dungeon;
    }

    private byte[] generateTown() {
        byte[] thisTown = new byte[4];
        byte thisName = (byte) (Math.random() * townNames.length);
        //check that this name has not been used yet
        for (byte i = 0; i < townNames.length; i++) {
            if (visitedTowns[i].length == 0) {
                break;
            } else if (visitedTowns[i][0] == thisName) {
                thisName = (byte) (Math.random() * townNames.length);
                i = -1;
            }
        }
        thisTown[0] = thisName;
        thisTown[1] = mapWindowCoords[12][0];
        thisTown[2] = mapWindowCoords[12][1];
        thisTown[3] = 0;
        return thisTown;
    }

    private byte[] generateHouse() {
        byte[] thisHouse = new byte[2];
        thisHouse[0] = mapWindowCoords[12][0];
        thisHouse[1] = mapWindowCoords[12][1];
        return thisHouse;
    }

    private void toggleYesAndNoVisible() {
        toggleAllInvisible();
        View y = findViewById(R.id.yesButtonExploration);
        y.setVisibility(View.VISIBLE);
        View no = findViewById(R.id.noButtonExploration);
        no.setVisibility(View.VISIBLE);
    }

    private void toggleYesAndNoInvisible() {
        toggleAllVisible();
        View y = findViewById(R.id.yesButtonExploration);
        y.setVisibility(View.GONE);
        View no = findViewById(R.id.noButtonExploration);
        no.setVisibility(View.GONE);
        View c = findViewById(R.id.explorationContinueButton);
        c.setVisibility(View.GONE);
    }

    private void toggleContinueVisible() {
        mapMessage.setVisibility(View.VISIBLE);
        toggleAllInvisible();
        View c = findViewById(R.id.explorationContinueButton);
        c.setVisibility(View.VISIBLE);
    }

    private void toggleContinueInvisible() {
        mapMessage.setVisibility(View.GONE);
        toggleAllVisible();
        View c = findViewById(R.id.explorationContinueButton);
        c.setVisibility(View.GONE);
        View y = findViewById(R.id.yesButtonExploration);
        y.setVisibility(View.GONE);
        View n = findViewById(R.id.noButtonExploration);
        n.setVisibility(View.GONE);
    }

    private void toggleAllInvisible() {
        View n = findViewById(R.id.goNorthButton);
        n.setVisibility(View.GONE);
        View s = findViewById(R.id.goSouthButton);
        s.setVisibility(View.GONE);
        View e = findViewById(R.id.goEastButton);
        e.setVisibility(View.GONE);
        View w = findViewById(R.id.goWestButton);
        w.setVisibility(View.GONE);
        View wo = findViewById(R.id.viewWorldMap);
        wo.setVisibility(View.GONE);
        View se = findViewById(R.id.explorationCampButton);
        se.setVisibility(View.GONE);
        View sp = findViewById(R.id.explorationSpellButton);
        sp.setVisibility(View.GONE);
        View c = findViewById(R.id.explorationCastButton);
        c.setVisibility(View.GONE);
        View y = findViewById(R.id.yesButtonExploration);
        y.setVisibility(View.GONE);
        View no = findViewById(R.id.noButtonExploration);
        no.setVisibility(View.GONE);
        View co = findViewById(R.id.explorationContinueButton);
        co.setVisibility(View.GONE);
    }

    private void toggleAllVisible() {
        View n = findViewById(R.id.goNorthButton);
        n.setVisibility(View.VISIBLE);
        View s = findViewById(R.id.goSouthButton);
        s.setVisibility(View.VISIBLE);
        View e = findViewById(R.id.goEastButton);
        e.setVisibility(View.VISIBLE);
        View w = findViewById(R.id.goWestButton);
        w.setVisibility(View.VISIBLE);
        View wo = findViewById(R.id.viewWorldMap);
        wo.setVisibility(View.VISIBLE);
        View se = findViewById(R.id.explorationCampButton);
        se.setVisibility(View.VISIBLE);
        View sp = findViewById(R.id.explorationSpellButton);
        sp.setVisibility(View.VISIBLE);
        View c = findViewById(R.id.explorationCastButton);
        c.setVisibility(View.VISIBLE);
        View y = findViewById(R.id.yesButtonExploration);
        y.setVisibility(View.VISIBLE);
        View no = findViewById(R.id.noButtonExploration);
        no.setVisibility(View.VISIBLE);
        View co = findViewById(R.id.explorationContinueButton);
        co.setVisibility(View.VISIBLE);
    }

    private void spell() {
        if (EnterNames.thisPlayer.liveMP == 0) {
            explorationInfo = noMP;
            setMapMessage();
            toggleContinueVisible();
        } else {
            startActivity(new Intent(Exploration.this, SpellSelection.class));
        }
    }

    public void updateCastSuccess(byte spellIndex) {
        castSuccess = "You cast " + EnterNames.thisPlayer.spells[spellIndex].name + "...";
    }

    private void cast(byte spellIndex) {
        if (spellNum == -1) {
            explorationInfo = noSpellSelected;
        } else if (!(EnterNames.thisPlayer.spells[spellIndex].isExplorationSpell)) {
            explorationInfo = combatOnly;
        } else if (EnterNames.thisPlayer.spells[spellIndex].mpCost > EnterNames.thisPlayer.liveMP) {
            explorationInfo = Gameplay.notEnoughMP;
        } else {
            EnterNames.thisPlayer.spells[spellIndex].spellCast(EnterNames.thisPlayer);
            updateCastSuccess(spellIndex);
            if (EnterNames.thisPlayer.spells[spellIndex].id == 10) {
                setMapWindow();
            }
            explorationInfo = castSuccess + EnterNames.thisPlayer.spells[spellIndex].getExtraText();
        }
        setMapMessage();
        toggleContinueVisible();
    }
}
