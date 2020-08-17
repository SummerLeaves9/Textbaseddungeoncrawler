package com.example.doome.dungeon_lords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

public class Gameplay extends AppCompatActivity {

    public static final char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
    /**
     * possible negativeSecretChance values, depending on the difficulty of the dungeon
     */
    private final double[] negativeSecretValues = {.1, .23, .3, .35};
    /**
     * variable to track the chance that a found secret will have negative consequences.
     */
    public static double negativeSecretChance;
    /**
     * Boolean to track whether the player has used the Lucky Marksman spell this turn. If true,
     * the protocol for the attack() method changes.
     */
    public static boolean usedLuckyMarksman = false;
    /**
     * Boolean to keep track of whether gameplay is in focus
     */
    public static boolean gameplayInFocus = false;
    /**
     * This variable keeps count of how many rooms have been traversed.
     */
    public static byte liveRoomCount = 0;
    /**
     * The amount of rooms which will be used for this game.
     * Set by the user's selection between 15, 30, or 45.
     */
    public static byte roomCount = 0;
    /**
     * Boolean to track if the player has chosen to counterattack this turn. Influences what
     * enemyBattleStatus does for the turn.
     */
    public static boolean hasCountered = false;
    /**
     * Boolean to track if the player has chosen to defend this turn. Influences what
     * enemyBattleStatus does for the turn.
     */
    public static boolean hasDefended = false;
    /**
     * Boolean to track if the player is adding a new spell to their collection. This is used so
     * SpellSelection knows to set a spell to that slot rather than selecting a slot for casting
     */
    public static boolean isAddingSpell = false;
    /**
     * Boolean to keep track of the method the player is able to learn a new spell from: finding it
     * on a warlock or finding a secret
     */
    public boolean learningFromWarlock;
    /**
     * Boolean to track if the player is restarting an encounter because they died
     */
    public static boolean diedInEncounter = false;
    /**
     * Byte to track which enemy the player was fighting before they died in an encounter
     */
    public byte encounterEnemy = -1;
    /**
     * Array of spell id's used for letting the player pick new spells: If they find a spellbook
     * in a secret, or learn it by other means. Max 3 spells
     */
    public static Spell[] spellsToLearn = {new Spell((byte) 127), new Spell((byte) 127), new Spell((byte) 127)};
    /**
     * Array of player's hp and mp to be set to when they die and start the dungeon over
     */
    public static byte[] tempPlayerValues = new byte[9];
    /**
     * player's gold at start of dungeon
     */
    public static int tempGold = 0;
    /**
     * The boolean to track when the user is in battle
     */
    public static boolean isBattling = false;
    /**
     * The boolean that determines if the user has used their attack turn.
     */
    public static boolean attackTurnAdvantage = true;
    /**
     * The boolean that determines if the user has used their magic turn.
     */
    public static boolean magicTurnAdvantage = true;
    /**
     * The boolean that determines whether the user has enabled ultimate block this turn.
     */
    public static boolean hasBlocked = false;
    /**
     * The boolean that stores whether the player has an active Sphericon on the battlefield
     */
    public static boolean hasSphericon = false;
    /**
     * The boolean that stores whether the player has used a brain juice potion in this dungeon
     */
    public static boolean usedBrainJuice = false;
    /**
     * The boolean that stores whether gameplay is being booted from the items screen or not
     */
    public static boolean cameFromItems = false;
    /**
     * The boolean that stores whether the player has used a greed potion in this dungeon
     */
    public static boolean usedGreedPotion = false;
    /**
     * The byte to store the enemy's initial stat value when it is successfully reduced by a player
     * spell.
     */
    public static byte initStat = 0;
    /**
     * The int that stores how many Sphericon charges are loaded up
     */
    public static int sphericonCharge = 0;
    /**
     * Boolean to track whether the player charged their sphericon last turn, if one is out. If not,
     * it releases its energy in an attack
     */
    public static boolean hasCharged = false;
    /**
     * For spells that have effects over one or more turns, this counter keeps track of turns when
     * the effect is active. If the counter reaches 0, the effect ends. Enemy effect
     */
    public static byte enemyEffectCounter = -1;
    /**
     * The index of the spell if it caused a status effect on the enemy last turn: used to get that
     * spell's status effect label
     */
    public byte enemyEffectSpellNum = -1;
    /**
     * For spells that have effects over one or more turns, this counter keeps track of turns when
     * the effect is active. If the counter reaches 0, the effect ends. Player effect
     */
    public static byte myEffectCounter = -1;
    /**
     * The index of the spell if it caused a status effect on the player last turn: used to get that
     * spell's status effect label
     */
    public byte myEffectSpellNum = -1;
    /**
     * The index of the spell that is sphericon
     */
    public byte sphericonSpellNum = -1;
    /**
     * The final string to compare the userInput to attack.
     */
    public final String attack = "a";
    /**
     * The final string to compare the userInput to progress.
     */
    public final String progress = "p";
    /**
     * The final string to compare the userInput to look.
     */
    public final String look = "l";
    /**
     * The final string to compare the userInput to spell casting.
     */
    public final String spell = "s";
    /**
     * The final string to compare the userInput to executing a spell
     */
    public final String cast = "c";
    /**
     * The final string to compare the userInput to counterattacking
     */
    public final String counterAttack = "ca";
    /**
     * The final string to compare the userInput to defending
     */
    public final String defend = "d";
    /**
     * A counter used to show the player how many enemies they've defeated by the end of the game.
     */
    public static int enemiesDefeatedCounter = 0;
    /**
     * A counter used to show the player how many secrets they've found throughout the adventure.
     */
    public static int secretsFoundCounter = 0;
    /**
     * The very first dungeon
     */
    public static final byte[] firstDungeon = {0, 0, 0, 0, 0, 1, 2, 6};
    /**
     * The dungeon that is being played through in this instance of Gameplay. Initially set to the
     * first dungeon configuration
     */
    public static byte[] thisDungeon = firstDungeon;
    /**
     * The room the player is currently in.
     */
    public static Room thisRoom = new Room();
    /**
     * The first message in the game.
     */
    public final String openingMessage = "You wake up in a torchlit brick room. Your head hurts " +
            "and your limbs feel sore. You don't know how you got here. ";
    /**
     * The default text for entering a new dungeon
     */
    public final String dungeonOpener = "You enter the new dungeon. ";
    /**
     * The message displayed to the user when they attack a monster.
     */
    public String displayHit = EnterNames.thisPlayer.weaponName + " hits for ";
    /**
     * The message displayed to the user when they attempt to attack, but miss
     */
    public static String displayMiss = "You use your " + EnterNames.thisPlayer.weaponName + ", but you miss. ";
    /**
     * The message displayed when an enemy's attack hits the player.
     */
    public static String displayEnemyHit = thisRoom.numberOne.name + "'s " +
            thisRoom.numberOne.weaponName + " hits for ";
    /**
     * The message displayed when an enemy's attack misses the player.
     */
    public static String displayEnemyMiss = thisRoom.numberOne.name + "'s " +
            thisRoom.numberOne.weaponName + " misses. ";
    /**
     * The message displayed to the user when they win and end a battle.
     * To be added onto an attack message, as this must be displayed before the battle could end.
     */
    public static String victoryMessage = "You won! +" + thisRoom.numberOne.goldDrop + " gold. ";
    /**
     * The message displayed when they win a random encounter battle.
     */
    public static String encounterVictoryMessage = "You won! The " + thisRoom.numberOne.name +
            " is defeated. Time to get some more sleep. ";
    /**
     * The message displayed when the user cannot search a room.
     */
    public final String unsearchable = "You can tell everything about this room with a " +
            "single glance. No need to search for secrets. ";
    /**
     * The first way of entry to a secret on finding one.
     */
    public final String secretIntro1 = "You found a passageway behind some loose bricks, and you " +
            "enter. ";
    /**
     * The second way of entry into a secret on finding one.
     */
    public final String secretIntro2 = "While walking around, you notice a patch of bricks " +
            "sounds hollow when you step on them. You break some away and find an underground " +
            "room! ";
    /**
     * The third way of entry into a secret on finding one.
     */
    public final String secretIntro3 = "You run your hand along the wall, and find a part where " +
            "you pass right through! You walk through the illusory wall. ";
    /**
     * The message displayed when the user finds a attack boost secret.
     */
    public final String foundAttackUp = "In the room you find a pink-purple potion which " +
            "increases muscular efficiency. Strength +1! ";
    /**
     * The message displayed when the user finds a defense boost secret.
     */
    public final String foundDefenseUp = "Lying on the floor you find a piece of armor. Stylish! " +
            "Defense +1. ";
    /**
     * The message displayed when the user finds a point boost secret.
     */
    public String foundNewSpell = "You found an ancient book...it can teach you the spell " +
            spellsToLearn[0].name + "! Read it? ";
    /**
     * The message for the first kind of bad secret
     */
    public final String tripwire = "On your second step, you trip on a wire and fall forward. ";
    /**
     * The message for evading the tripwire trap
     */
    public final String tripwireSuccess = "Thanks to your Agility, you're able to roll over and " +
            "kip up before a spiky ball drops on you! ";
    /**
     * The message for getting hit by the tripwire trap
     */
    public final String tripwireFail = "You exert yourself to get up, but before you can you get " +
            "crushed by a spiky ball falling from the ceiling... -14 HP. ";
    /**
     * The message for the second kind of bad secret
     */
    public final String magicSapper = "You find a one foot-tall figure with a glowing green face " +
            "who's wearing an oversized robe. They lunge at you! ";
    /**
     * The message for fending off the shortcerer
     */
    public String magicSapperSuccess = "You try to block them with " +
            EnterNames.thisPlayer.weaponName + ", and it works, thanks to your Accuracy!" +
            " You fling the shortcerer off, and they flee into a small hole in the corner of " +
            "the room.";
    /**
     * The message for getting your magic zapped from your body
     */
    public final String magicSapperFail = "You try to prevent their advance, but you mispredict " +
            "the trajectory and the shortcerer clings to you! Their hands on your shoulder makes " +
            "it go numb. You swipe them off, but it gets away before you can do anything else. " +
            "-8 MP. ";
    /**
     * The message for the third kind of bad secret
     */
    public final String scrollQuestion = "You enter a room, more bright and futuristic than the " +
            "endless torch-lit brick walls of this place. A fizzle of orange embers appears in " +
            "the air before you, revealing a scroll and a quill. You hear some clanks as the " +
            "entrance gets closed behind you. ";
    /**
     * The message for beating the puzzle room
     */
    public final String scrollQuestionSuccess = "You read the scroll, and find you know the " +
            "answer to the question, thanks to your Intelligence! You write it down and the " +
            "scroll casts itself out of existence. The entrance reopens itself. ";
    /**
     * The message for losing the puzzle room
     */
    public final String scrollQuestionFail = "You have no idea what the scroll is talking about, " +
            "so you jot down your best guess. The scroll disappears. The entrance opens, but " +
            "you're pierced by an arrow! -13 HP. ";
    /**
     * The message displayed when the user fails to find a secret.
     */
    public final String noSecretFound = "You looked everywhere, but were unable to find " +
            "anything... ";
    /**
     * The message displayed when the user has already searched a room.
     */
    public final String alreadySearched = "You've already searched this room: it would be " +
            "unproductive to search it again. ";
    /**
     * The string which will be used by the UI to display game text.
     * Is changed and redisplayed throughout play.
     */
    public static String consoleOutput = "";
    /**
     * The message displayed when a user progresses and enters battle with an enemy.
     */
    public String startBattle = "A " + thisRoom.numberOne.name + " appears in this room! " +
            "Prepare for battle! ";
    /**
     * The message displayed when the player enters battle with a miniboss.
     */
    public String strongStartBattle = "A miniboss appears, it's a " +
            thisRoom.numberOne.name + "! Prepare for battle! ";
    /**
     * The add-on message displayed to the user when the room can be searched.
     */
    public static final String canBeSearched = "You think this room might be hiding something... ";
    /**
     * The message displayed when the player enters an empty room.
     */
    public String emptyRoom = "You enter the next room, room " + liveRoomCount + ". ";
    /**
     * The message displayed to the user when either they or the enemy lands a critical hit.
     */
    public final String criticalHit = "Critical hit! ";
    /**
     * the message added on to the end when the player looks for and finds a secret
     */
    public final String nothingElse = "It seems there is nothing else in this room. ";
    /**
     * the message displayed when the player encounters an oasis
     */
    public final String oasisText = "It's an oasis! It's well-lit in here, and there's a healing " +
            "spring in the middle of the room. There are also doors that lock from the inside. " +
            "Restore all health and magic points? ";
    /**
     * the message displayed when the player chooses to use the oasis
     */
    public final String oasisYes = "You lock the doors, disrobe and step in the spring. You feel " +
            "your wounds melting away, and your energy flooding back to you! ";
    /**
     * the message displayed when the player does not use the oasis
     */
    public final String oasisNo = "You decide to ignore the spring and continue. ";
    /**
     * the message displayed when the player tries to do something that is unhelpful.
     */
    public final String invalidCommand = "You can't do that right now.";
    /**
     * the message below gameInfo that tells you that you have used your magic turn
     */
    public final String yesMagicTurn = "Magic Turn: Yes ";
    /**
     * the message below gameInfo that tells you that you have NOT used your magic turn
     */
    public final String noMagicTurn = "Magic Turn: No ";
    /**
     * the message displayed when you try to use a spell, but you have already used your magic turn.
     */
    public final String doNotHaveMagicTurn = "You have already used your magic turn! ";
    /**
     * The message displayed when you try to use a spell, but you have no magic points
     */
    public static final String notEnoughMP = "Not enough MP!";
    /**
     * The message displayed when you try to go to the spell selection screen, but you have no MP
     */
    public final String noMP = "No MP!";
    /**
     * the message displayed when you successfully cast a spell
     */
    public String castSuccess = "You cast...a spell? ";
    /**
     * the message displayed when the player tries to cast a spell which has an effect on the enemy,
     * but they already have an effect currently
     */
    public String enemyAlreadyHasEffect = "The " + thisRoom.numberOne.name + " already has an " +
            "effect active, you must wait until the effect passes. ";
    /**
     * the message displayed that shows what spell is selected before the users presses "cast".
     */
    public String newLastSpellText = "Selected Spell: ";
    /**
     * the message displayed when a spell is cast in combat, but it is only meant for exploration
     */
    public final String explorationOnly = "This spell can't be used in combat!";
    /**
     * the message displayed when a spell is cast while exploring, but is only meant for combat
     */
    public static final String combatOnly = "This spell is only meant for combat.";
    /**
     * the message displayed when the cast button is pressed, but spellNum is -1 instead of 0-6
     */
    public static final String noSpellSelected = "No spell selected! ";
    /**
     * the message displayed when you activate ultimate block
     */
    public String usedUltimateBlock = thisRoom.numberOne.name + " couldn't touch you " +
            "this turn! ";
    /**
     * Sphericon
     */
    public final String sphericon = "Sphericon";
    /**
     * HUD display for Sphericon's charge
     */
    public String sphericonChargeDisplay = "Charge: " + sphericonCharge;
    /**
     * Message displayed when
     */
    public final String noSphericon = "You don't have a Sphericon active!";
    /**
     * the message displayed when lucky Marksman affects the outcome of an attack turn, with the
     * player hitting
     */
    public final String luckyMarksmanHit = "Lucky Marksman powers up your attack for double " +
            "damage! ";
    /**
     * the message displayed when lucky Marksman affects the outcome of an attack turn, with the
     * enemy hitting
     */
    public final String luckyMarksmanEnemyHit = "You missed your attack with Lucky Marksman " +
            "active, so you take double damage... ";
    /**
     * the message displayed when you try to use Vision, but the room has already been searched
     */
    public final String visionAlreadySearched = "Vision can't be used after searching the room!";
    /**
     * the message displayed when you try to use Vision, but the room is not searchable
     */
    public final String visionCannotSearch = "This room is not searchable!";
    /**
     * the message displayed at the end of battle if the enemy drops MP
     */
    public String droppedMP = "+" + thisRoom.numberOne.MPDrop + " MP. ";
    /**
     * the message displayed after defeating a warlock, and if they drop a spell page
     */
    public String warlockDroppedSpellPage = "You see a page left on the " +
            thisRoom.numberOne.name + "'s remains. On it is written the instructions to learn a " +
            "new spell! Learn " + spellsToLearn[0].name + "? ";
    /**
     * the message displayed after rejecting learning a new spell
     */
    public final String rejectSpell = "You leave the page unstudied. ";
    /**
     * the message displayed when you counter attack successfully
     */
    public String counterSuccess = "You anticipate the " + thisRoom.numberOne.name + "'s attack," +
            "and you dodge it! You use " + EnterNames.thisPlayer.weaponName + " and deal a" +
            "powerful blow, for " + EnterNames.thisPlayer.counterAttackPower + " damage! ";
    /**
     * the message displayed when a counter attack is attempted, but the player's agility
     * makes them fail to dodge the attack
     */
    public String counterFailAgility = "You try to dodge the attack, but you aren't quick enough " +
            "and you are hit by the " + thisRoom.numberOne.name + "'s " +
            thisRoom.numberOne.weaponName + "! For " + thisRoom.numberOne.attackPower + " damage. ";
    /**
     * the message displayed when a counter attack is attempted, but the enemy misses you, and can
     * recover before you can counter attack.
     */
    public String counterFailEnemyMiss = "You go to dodge the attack, but the " +
            thisRoom.numberOne.name + " misses, and can recover before you can do any serious " +
            "damage. ";
    /**
     * the message displayed when a counter attack is attempted, but the enemy catches on and
     * feints an attack. This leads to a free blow on the player.
     */
    public String counterFailEnemyRead = "You anticipate the enemy's approach. You watch the " +
            thisRoom.numberOne.name + "approach, and you try to counter! But they catch on, and" +
            "you are baited by their feint! The enemy lands a free " +
            thisRoom.numberOne.weaponName + " for " + thisRoom.numberOne.attackPower + " damage. ";
    /**
     * the message displayed when you defend, and you successfully block half the damage.
     */
    public String defendBlock = "You take a strong stance and block. The " +
            thisRoom.numberOne.name + " uses their " + thisRoom.numberOne.weaponName + " and it " +
            "hits! For " + (byte) (thisRoom.numberOne.attackPower / 2) + " damage. ";
    /**
     * the message displayed when you defend, and the enemy misses. This allows time for a weak jab
     * before the enemy can retreat.
     */
    public String defendEnemyMiss = "You take a strong stance and block. The " +
            thisRoom.numberOne.name + " misses, and you get in a quick jab while they retreat! " +
            "For " + EnterNames.thisPlayer.defendJabAttackPower + " damage. ";
    /**
     * The message when you face a special encounter, i.e. a "dungeon" with only one room.
     */
    public String encounterText = "You are woken up by the sound of running and snarling. A " +
            thisRoom.numberOne.name + " picks a fight with you, prepare for battle!";
    /**
     * Game over
     */
    public final String gameOver = "You died! Game over. ";
    /**
     * Bits of flavor text: When you enter a room with no enemy or after you defeat it, a random
     * short description of the room is added
     */
    public String[] flavorText = {"It smells like moss in here. ",
            "Smells like pizza left out in the sun. ",
            "There are vines growing out of some of the bricks. ",
            "One of the torches in here has fallen to the ground. ",
            "Some flies buzz around an unrecognizable lump of fur and flesh. ",
            "The ceiling is pretty low in here. ",
            "You think you hear some grumbling up ahead. ",
            "You see some cracks in the bricks to your left. ",
            "You see some cracks in the bricks to your right. ",
            "You see some cracks in the bricks at the opposite wall. ",
            "A previous dungeon crawler lays lifeless to your left. ",
            "A previous dungeon crawler lays lifeless to your right. ",
            "A previous dungeon crawler lays lifeless before you. ",};
    /**
     * The byte that is changed in SpellSelection to indicate which spell in which slot to use
     */
    public static byte spellNum = -1;

    TextView hud;
    TextView hud2;
    TextView hud3;
    TextView gameInfo;
    TextView magicTurnIndicator;
    TextView enemyName;
    TextView enemyHealthIndicator;
    TextView lastSpellUsed;
    TextView myEffectIndicator;
    TextView enemyEffectIndicator;
    TextView liveRoomCountDisplay;
    TextView sphericonIndicator1;
    TextView sphericonIndicator2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        hud = (TextView) findViewById(R.id.hudLine1);
        hud2 = (TextView) findViewById(R.id.hudLine2);
        hud3 = (TextView) findViewById(R.id.hudLine3);
        gameInfo = (TextView) findViewById(R.id.gameInfo);
        magicTurnIndicator = (TextView) findViewById(R.id.magicTurnIndicator);
        enemyName = (TextView) findViewById(R.id.enemyName);
        enemyHealthIndicator = (TextView) findViewById(R.id.enemyHealthIndicator);
        lastSpellUsed = (TextView) findViewById(R.id.lastSpellUsed);
        myEffectIndicator = (TextView) findViewById(R.id.myEffectIndicator);
        enemyEffectIndicator = (TextView) findViewById(R.id.enemyEffectIndicator);
        liveRoomCountDisplay = (TextView) findViewById(R.id.liveRoomCountDisplay);
        sphericonIndicator1 = (TextView) findViewById(R.id.sphericonIndicator1);
        sphericonIndicator2 = (TextView) findViewById(R.id.sphericonIndicator2);
        View y = findViewById(R.id.sayYesButton);
        y.setVisibility(View.GONE);
        View n = findViewById(R.id.sayNoButton);
        n.setVisibility(View.GONE);
        View done = findViewById(R.id.doneButtonGameplay);
        done.setVisibility(View.GONE);
        View co = findViewById(R.id.continueButton);
        co.setVisibility(View.GONE);
        View r = findViewById(R.id.returnToCampButton);
        r.setVisibility(View.GONE);
        if (cameFromItems) {
            configureNextButton();
            View a = findViewById(R.id.attackButton);
            a.setVisibility(View.GONE);
            View c = findViewById(R.id.counterButton);
            c.setVisibility(View.GONE);
            View d = findViewById(R.id.defendButton);
            d.setVisibility(View.GONE);
            setGameInfo();
            setHud();
            cameFromItems = false;
        } else {
            gameplayInFocus = true;
            configureDungeon();
            displayHit = EnterNames.thisPlayer.weaponName + " hits for ";
            displayMiss = "You use " + EnterNames.thisPlayer.weaponName + ", but you miss. ";
            victoryMessage = "You won! +" + thisRoom.numberOne.goldDrop + " gold. ";
            magicSapperSuccess = "You try to block them with " +
                    EnterNames.thisPlayer.weaponName + ", and it works! You fling the shortcerer " +
                    "off, and they flee into a small hole in the corner of the room.";
            if (roomCount == 1) {
                setEncounterText();
                consoleOutput = encounterText;
            } else {
                switch (thisDungeon[1]) {
                    case 0:
                        consoleOutput = openingMessage;
                        break;
                    default:
                        consoleOutput = dungeonOpener;
                }
                if (thisRoom.disSearchable) {
                    consoleOutput += canBeSearched;
                }
            }
            configureNextButton();
            setGameInfo();
            setHud();
            if (roomCount > 1) {
                View a = findViewById(R.id.attackButton);
                a.setVisibility(View.GONE);
                View c = findViewById(R.id.counterButton);
                c.setVisibility(View.GONE);
                View d = findViewById(R.id.defendButton);
                d.setVisibility(View.GONE);
            } else {
                displayEnemyHit = thisRoom.numberOne.name + "'s " +
                        thisRoom.numberOne.weaponName + " hits for ";
                displayEnemyMiss = thisRoom.numberOne.name + "'s " +
                        thisRoom.numberOne.weaponName + " misses. ";
                View p = findViewById(R.id.progressButton);
                p.setVisibility(View.GONE);
                View l = findViewById(R.id.lookButton);
                l.setVisibility(View.GONE);
                View i = findViewById(R.id.gameplayItemsButton);
                i.setVisibility(View.GONE);
            }
        }

    }

    /*
    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_gameplay);
        consoleOutput = "onStartWorking";
        setGameInfo();
    }
    */

    @Override
    public void onBackPressed() {}

    private void setEncounterText() {
        for (byte i = 0; i < vowels.length; i++) {
            if (thisRoom.numberOne.name.charAt(0) == vowels[i]) {
                encounterText = "You are woken up by the sound of running and snarling. An " +
                        thisRoom.numberOne.name + " picks a fight with you, prepare for battle!";
                return;
            }
        }
        encounterText = "You are woken up by the sound of running and snarling. A " +
                thisRoom.numberOne.name + " picks a fight with you, prepare for battle!";
    }

    private void configureNextButton() {
        //Combat Buttons
        Button progressButton = (Button) findViewById(R.id.progressButton);
        Button attackButton = (Button) findViewById(R.id.attackButton);
        Button spellButton = (Button) findViewById(R.id.spellButton);
        Button lookButton = (Button) findViewById(R.id.lookButton);
        Button castButton = (Button) findViewById(R.id.castButton);
        Button counterButton = (Button) findViewById(R.id.counterButton);
        Button defendButton = (Button) findViewById(R.id.defendButton);
        final Button sayYesButton = (Button) findViewById(R.id.sayYesButton);
        final Button sayNoButton = (Button) findViewById(R.id.sayNoButton);
        final Button doneButton = (Button) findViewById(R.id.doneButtonGameplay);
        final Button continueButton = (Button) findViewById(R.id.continueButton);
        final Button campButton = findViewById(R.id.returnToCampButton);
        final Button itemsButton = findViewById(R.id.gameplayItemsButton);

        //Configuring Combat Buttons
        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOutput(progress);
            }
        });
        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOutput(attack);
            }
        });
        spellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOutput(spell);
            }
        });
        lookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOutput(look);
            }
        });
        castButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOutput(cast);
            }
        });
        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { changeOutput(counterAttack); }});
        defendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeOutput(defend);
            }
        });
        sayYesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAddingSpell) {
                    sayYesButton.setVisibility(View.GONE);
                    sayNoButton.setVisibility(View.GONE);
                    doneButton.setVisibility(View.VISIBLE);
                    startActivity(new Intent(Gameplay.this, SpellSelection.class));
                } else if (thisRoom.isOasis) {
                    EnterNames.thisPlayer.liveHP = EnterNames.thisPlayer.hp;
                    EnterNames.thisPlayer.liveMP = EnterNames.thisPlayer.mp;
                    consoleOutput = oasisYes;
                    gameInfo.setTextSize(22);
                    if (consoleOutput.length() > 200) {
                        gameInfo.setTextSize(18);
                    }
                    if (consoleOutput.length() > 400) {
                        gameInfo.setTextSize(14);
                    }
                    setGameInfo();
                    setHud();
                    toggleYesAndNoInvisible();
                }
            }
        });
        sayNoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAddingSpell) {
                    isAddingSpell = false;
                    toggleYesAndNoInvisible();
                    if (learningFromWarlock) {
                        if (thisRoom.disSearchable) {
                            consoleOutput = rejectSpell + canBeSearched;
                        } else {
                            consoleOutput = rejectSpell + nothingElse;
                        }
                    } else {
                        consoleOutput = rejectSpell + nothingElse;
                    }
                } else if (thisRoom.isOasis) {
                    consoleOutput = oasisNo;
                    toggleYesAndNoInvisible();
                }
                gameInfo.setTextSize(22);
                if (consoleOutput.length() > 200) {
                    gameInfo.setTextSize(18);
                }
                if (consoleOutput.length() > 400) {
                    gameInfo.setTextSize(14);
                }
                setGameInfo();
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAddingSpell) {
                    toggleYesAndNoInvisible();
                    if (learningFromWarlock) {
                        if (thisRoom.disSearchable) {
                            consoleOutput = canBeSearched;
                        } else {
                            consoleOutput = nothingElse;
                        }
                    } else {
                        consoleOutput = nothingElse;
                    }
                    gameInfo.setTextSize(22);
                    if (consoleOutput.length() > 200) {
                        gameInfo.setTextSize(18);
                    }
                    if (consoleOutput.length() > 400) {
                        gameInfo.setTextSize(14);
                    }
                    setGameInfo();
                    doneButton.setVisibility(View.GONE);
                }
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isBattling = false;
                startActivity(new Intent(Gameplay.this, GameOverScreen.class));
                finish();
            }
        });
        campButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isBattling = false;
                finish();
            }
        });
        itemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameFromItems = true;
                Intent intent = new Intent(Gameplay.this, Items.class);
                intent.putExtra("source class", false);
                startActivity(intent);
            }
        });
    }

    /**
     * sets consoleOutput appropriately depending on what the user inputs.
     * @param action the passed command from the user to
     */
    public void changeOutput (String action) {
        if (isBattling) {
            if (magicTurnAdvantage) {
                hasCharged = false;
            }
            //a battle turn
            int playerDamage = 0;
            if (attackTurnAdvantage) {
                playerDamage = myBattleStatus(action);
            }
            if (!attackTurnAdvantage && !magicTurnAdvantage) {
                enemyBattleStatus(playerDamage);
                if (myEffectSpellNum != -1 || enemyEffectSpellNum != -1 || hasSphericon) {
                    regulateSpells();
                }
            }
        } else {
            //a movement turn
            movementStatus(action);
        }
        gameInfo.setTextSize(22);
        if (consoleOutput.length() > 200) {
            gameInfo.setTextSize(18);
        }
        if (consoleOutput.length() > 400) {
            gameInfo.setTextSize(14);
        }
        //if (liveRoomCount < roomCount) {
            setGameInfo();
            setHud();
        //}
    }

    public void regulateSpells() {
        if (enemyEffectCounter > -1) {
            if (enemyEffectCounter > 0) {
                EnterNames.thisPlayer.spells[enemyEffectSpellNum].
                        lingeringEffect(thisRoom.numberOne);
                consoleOutput += EnterNames.thisPlayer.spells[enemyEffectSpellNum].
                        getLingeringEffectText(thisRoom.numberOne);
            } else {
                EnterNames.thisPlayer.spells[enemyEffectSpellNum].
                        reverseSpellCast(thisRoom.numberOne);
                consoleOutput += EnterNames.thisPlayer.spells[enemyEffectSpellNum].effectEndsText;
            }
            enemyEffectCounter--;
            checkIfEnemyDefeated();
        }
        if (myEffectCounter > -1) {
            if (myEffectCounter > 0) {
                EnterNames.thisPlayer.spells[myEffectSpellNum].
                        lingeringEffect(EnterNames.thisPlayer);
            }
            else {
                EnterNames.thisPlayer.spells[myEffectSpellNum].
                        reverseSpellCast(EnterNames.thisPlayer);
                consoleOutput += EnterNames.thisPlayer.spells[myEffectSpellNum].effectEndsText;
            }
            myEffectCounter--;
        }
        if (!hasCharged && sphericonCharge > 0) {
            EnterNames.thisPlayer.spells[sphericonSpellNum].reverseSpellCast(thisRoom.numberOne);
            consoleOutput += EnterNames.thisPlayer.spells[sphericonSpellNum].effectEndsText;
            if (checkIfEnemyDefeated()) {
                return;
            }
        }
    }

    public int myBattleStatus (String action) {
        if (action.equals(attack)) {
            return attack();
        } else if (action.equals(counterAttack)) {
            counterAttack();
        } else if (action.equals(defend)) {
            defend();
        } else if (action.equals(spell)) {
            spell();
        } else if (action.equals(cast)) {
            cast(spellNum);
        } else {
            consoleOutput = invalidCommand;
        }
        return 0;
    }

    public void enemyBattleStatus(int playerDamage) {
        if (isBattling) {
            if (hasCountered) {
                int damageDealt = thisRoom.numberOne.determineHitNoDamage(EnterNames.thisPlayer);
                if (damageDealt == 0) {
                    counterFailEnemyMiss = "You go to dodge the attack, but the " +
                            thisRoom.numberOne.name + " misses, and can recover before you can do " +
                            "any serious damage. ";
                    consoleOutput = counterFailEnemyMiss;
                } else {
                    double counterDodgeSuccessful = Math.random();
                    double enemyReadCounter = Math.random();
                    if (counterDodgeSuccessful < EnterNames.thisPlayer.counterDodgeChance) {
                        if (enemyReadCounter > thisRoom.numberOne.liveCounterReadChance) {
                            counterSuccess = "You anticipate the " + thisRoom.numberOne.name + "'s " +
                                    "attack, and you dodge it! You use " +
                                    EnterNames.thisPlayer.weaponName + " and deal a powerful blow, " +
                                    "for " + EnterNames.thisPlayer.counterAttackPower + " damage! ";
                            thisRoom.numberOne.liveHP -= EnterNames.thisPlayer.counterAttackPower;
                            consoleOutput = counterSuccess;
                            checkIfEnemyDefeated();
                        } else {
                            EnterNames.thisPlayer.liveHP -= thisRoom.numberOne.attackPower;
                            counterFailEnemyRead = "You anticipate the enemy's approach. You " +
                                    "watch the " + thisRoom.numberOne.name + " approach, and you " +
                                    "try to counter! But they catch on, and you are baited by " +
                                    "their feint! The enemy lands a free " +
                                    thisRoom.numberOne.weaponName + " for " +
                                    thisRoom.numberOne.attackPower + " damage. ";
                            consoleOutput = counterFailEnemyRead;
                            if(checkForGameOver()) {
                                return;
                            }
                        }
                    } else {
                        EnterNames.thisPlayer.liveHP -= thisRoom.numberOne.attackPower;
                        counterFailAgility = "You try to dodge the attack, but you aren't quick " +
                                "enough and you are hit by the " + thisRoom.numberOne.name + "'s " +
                                thisRoom.numberOne.weaponName + "! For " +
                                thisRoom.numberOne.attackPower + " damage. ";
                        consoleOutput = counterFailAgility;
                        if(checkForGameOver()) {
                            return;
                        }
                    }
                }
                thisRoom.numberOne.liveCounterReadChance *= Enemy.counterReadMultiplier;
                hasCountered = false;
            } else if (hasDefended) {
                int damageDealt = thisRoom.numberOne.determineHitNoDamage(EnterNames.thisPlayer);
                if (damageDealt == 0) {
                    thisRoom.numberOne.liveHP -= EnterNames.thisPlayer.defendJabAttackPower;
                    defendEnemyMiss = "You take a strong stance and block. The " +
                            thisRoom.numberOne.name + " misses, and you get in a quick jab while " +
                            "they retreat! For " + EnterNames.thisPlayer.defendJabAttackPower +
                            " damage. ";
                    consoleOutput = defendEnemyMiss;
                    checkIfEnemyDefeated();
                } else {
                    damageDealt *= .5;
                    EnterNames.thisPlayer.liveHP -= damageDealt;
                    defendBlock = "You take a strong stance and block. The " +
                            thisRoom.numberOne.name + " uses their " +
                            thisRoom.numberOne.weaponName + " and it hits! For " +
                            (byte) (thisRoom.numberOne.attackPower / 2) + " damage. ";
                    consoleOutput = defendBlock;
                    if (checkForGameOver()) {
                        return;
                    }
                }
                if (thisRoom.numberOne.liveCounterReadChance >
                        thisRoom.numberOne.counterReadChance) {
                    thisRoom.numberOne.liveCounterReadChance /= Enemy.counterReadMultiplier;
                }
                hasDefended = false;
            } else if (hasBlocked) {
                usedUltimateBlock = thisRoom.numberOne.name + " couldn't touch you this " +
                        "turn!";
                consoleOutput += usedUltimateBlock;
                hasBlocked = false;
            } else {
                int damageDealt = thisRoom.numberOne.determineHit(EnterNames.thisPlayer);
                if (damageDealt != 0) {
                    if (playerDamage == 0 && usedLuckyMarksman) {
                        EnterNames.thisPlayer.liveHP -= damageDealt;
                        damageDealt *= 2;
                        consoleOutput += displayEnemyHit + damageDealt + ". " +
                                luckyMarksmanEnemyHit;
                        if (damageDealt > (thisRoom.numberOne.attackPower * 2)) {
                            consoleOutput += criticalHit;
                        }
                    } else {
                        consoleOutput += displayEnemyHit + damageDealt + ". ";
                        if (damageDealt > thisRoom.numberOne.attackPower) {
                            consoleOutput += criticalHit;
                        }
                    }
                    if (checkForGameOver()) {
                        return;
                    }
                } else {
                    consoleOutput += displayEnemyMiss;
                }
                if (thisRoom.numberOne.liveCounterReadChance >
                        thisRoom.numberOne.counterReadChance) {
                    thisRoom.numberOne.liveCounterReadChance /= Enemy.counterReadMultiplier;
                }
            }
            attackTurnAdvantage = true;
            magicTurnAdvantage = true;
        }
    }

    public void movementStatus(String action) {
        if (action.equals(progress)) {
            //finished dungeon
            if (liveRoomCount == roomCount) {
                liveRoomCount = 0;
                enemiesDefeatedCounter = 0;
                secretsFoundCounter = 0;
                if (usedBrainJuice) {
                    EnterNames.thisPlayer.intelligenceValue -= 3;
                    EnterNames.thisPlayer.setAllStats();
                    usedBrainJuice = false;
                }
                if (usedGreedPotion) {
                    usedGreedPotion = false;
                }
                Room.oasisUsed = false;
                Outside.isStartingOrWaking = true;
                startActivity(new Intent(Gameplay.this, Outside.class));
                finish();
            }
            //general procedure for preparing next room
            else if (liveRoomCount < roomCount) {
                thisRoom = new Room();
                liveRoomCount++;
                emptyRoom = "You enter the next room, room " + liveRoomCount + ". ";
                consoleOutput = emptyRoom;
                if (thisRoom.isOasis) {
                    consoleOutput += oasisText;
                    toggleYesAndNoVisible();
                } else if (thisRoom.hasEnemy) {
                    isBattling = true;
                    //setting combat buttons to visible
                    View a = findViewById(R.id.attackButton);
                    a.setVisibility(View.VISIBLE);
                    View c = findViewById(R.id.counterButton);
                    c.setVisibility(View.VISIBLE);
                    View d = findViewById(R.id.defendButton);
                    d.setVisibility(View.VISIBLE);
                    //setting exploration buttons to invisible
                    View p = findViewById(R.id.progressButton);
                    p.setVisibility(View.GONE);
                    View l = findViewById(R.id.lookButton);
                    l.setVisibility(View.GONE);
                    View i = findViewById(R.id.gameplayItemsButton);
                    i.setVisibility(View.GONE);
                    displayEnemyHit = thisRoom.numberOne.name + "'s " +
                            thisRoom.numberOne.weaponName + " hits for ";
                    displayEnemyMiss = thisRoom.numberOne.name + "'s " +
                            thisRoom.numberOne.weaponName + " misses. ";
                    if (!thisRoom.numberOne.isStrong) {
                        startBattle = "A " + thisRoom.numberOne.name + " appears in the room! Prepare for battle!";
                        consoleOutput += startBattle;
                    } else {
                        if (thisRoom.numberOne.name.equals("Anarchist Eddie")) {
                            strongStartBattle = "A miniboss appears, it's " + thisRoom.numberOne.name + "! Prepare for battle!";
                        } else {
                            strongStartBattle = "A miniboss appears, it's a " + thisRoom.numberOne.name + "! Prepare for battle!";
                        }
                        consoleOutput += strongStartBattle;
                    }
                } else {
                    int flavorTextIndex = (int) (Math.random() * flavorText.length);
                    consoleOutput += flavorText[flavorTextIndex];
                    if (thisRoom.disSearchable) {
                        consoleOutput += canBeSearched;
                    }
                }
            }
        } else if (action.equals(look)) {
            look();
        } else if (action.equals(spell)) {
            spell();
        } else if (action.equals(cast)) {
            cast(spellNum);
        } else {
            consoleOutput = invalidCommand;
        }
    }

    public void setGameInfo() {
        gameInfo.setText(consoleOutput);
    }

    public void setHud() {
        String newHudText = "HP: " + EnterNames.thisPlayer.liveHP + "/" +
                EnterNames.thisPlayer.hp;
        String newHud2Text = "MP: " + EnterNames.thisPlayer.liveMP + "/" +
                EnterNames.thisPlayer.mp;
        String newHud3Text = "Gold: " + EnterNames.thisPlayer.myGold;
        hud.setText(newHudText);
        hud2.setText(newHud2Text);
        hud3.setText(newHud3Text);
        String magicTurn = "";
        String enemyNameText = "";
        String enemyHealth = "";
        if (magicTurnAdvantage && isBattling) {
            magicTurn = yesMagicTurn;
        } else if (isBattling) {
            magicTurn = noMagicTurn;
        }
        if (isBattling) {
            if (hasSphericon) {
                sphericonIndicator1.setText(sphericon);
                sphericonChargeDisplay = "Charge: " + sphericonCharge;
                sphericonIndicator2.setText(sphericonChargeDisplay);
            }
            enemyNameText = thisRoom.numberOne.name;
            enemyHealth = thisRoom.numberOne.liveHP + "/" + thisRoom.numberOne.hp;
            if (enemyEffectCounter > -1) {
                enemyEffectIndicator.setText(EnterNames.thisPlayer.spells[enemyEffectSpellNum].getEffect());
            } else {
                enemyEffectIndicator.setText("");
            }
            if (myEffectCounter > -1) {
                myEffectIndicator.setText(EnterNames.thisPlayer.spells[myEffectSpellNum].getEffect());
            } else {
                myEffectIndicator.setText("");
            }
        } else {
            enemyEffectIndicator.setText("");
            myEffectIndicator.setText("");
            sphericonIndicator1.setText("");
            sphericonIndicator2.setText("");
        }
        magicTurnIndicator.setText(magicTurn);
        enemyName.setText(enemyNameText);
        enemyHealthIndicator.setText(enemyHealth);
        String roomCountText = "";
        if (roomCount > 1) {
            roomCountText = "Room " + liveRoomCount;
        }
        if (liveRoomCount != 0) {
            liveRoomCountDisplay.setText(roomCountText);
        }
    }

    public boolean checkIfEnemyDefeated() {
        if (thisRoom.numberOne.liveHP <= 0) {
            isBattling = false;
            hasSphericon = false;
            sphericonCharge = 0;
            hasBlocked = false;
            usedLuckyMarksman = false;
            attackTurnAdvantage = true;
            magicTurnAdvantage = true;
            enemyEffectCounter = -1;
            myEffectCounter = -1;
            enemiesDefeatedCounter++;
            if (roomCount == 1) {
                encounterVictoryMessage = "You won! The " + thisRoom.numberOne.name + " is " +
                        "defeated. Time to get some more sleep. ";
                consoleOutput += encounterVictoryMessage;
                toggleReturnToCampVisible();
            } else {
                if (usedGreedPotion) {
                    EnterNames.thisPlayer.myGold += (thisRoom.numberOne.goldDrop * 1.5);
                    victoryMessage = "You won! +" + (int) (thisRoom.numberOne.goldDrop * 1.5) + " gold. ";
                } else {
                    EnterNames.thisPlayer.myGold += thisRoom.numberOne.goldDrop;
                    victoryMessage = "You won! +" + thisRoom.numberOne.goldDrop + " gold. ";
                }
                consoleOutput += victoryMessage;
                if (thisRoom.numberOne.MPDrop != 0) {
                    droppedMP = "+" + thisRoom.numberOne.MPDrop + " MP. ";
                    consoleOutput += droppedMP;
                    EnterNames.thisPlayer.liveMP += thisRoom.numberOne.MPDrop;
                    if (EnterNames.thisPlayer.liveMP > EnterNames.thisPlayer.mp) {
                        EnterNames.thisPlayer.liveMP = EnterNames.thisPlayer.mp;
                    }
                }
                double droppedPage = Math.random();
                if (thisRoom.numberOne.isWarlock && droppedPage < Enemy.dropsNewSpellPage) {
                    isAddingSpell = true;
                    learningFromWarlock = true;
                    determineWhichSpellsToLearn();
                    toggleYesAndNoVisible();
                    warlockDroppedSpellPage = "You see a page left on the " + thisRoom.numberOne.name +
                            "'s remains. On it is written the instructions to learn a new spell! " +
                            "Learn " + spellsToLearn[0].name + "? ";
                    consoleOutput += warlockDroppedSpellPage;
                } else {
                    int flavorTextIndex = (int) (Math.random() * flavorText.length);
                    consoleOutput += flavorText[flavorTextIndex];
                    if (thisRoom.disSearchable) {
                        consoleOutput += canBeSearched;
                    }
                    //setting combat buttons to invisible
                    View a = findViewById(R.id.attackButton);
                    a.setVisibility(View.GONE);
                    View c = findViewById(R.id.counterButton);
                    c.setVisibility(View.GONE);
                    View d = findViewById(R.id.defendButton);
                    d.setVisibility(View.GONE);
                    //setting exploration buttons to visible
                    View p = findViewById(R.id.progressButton);
                    p.setVisibility(View.VISIBLE);
                    View l = findViewById(R.id.lookButton);
                    l.setVisibility(View.VISIBLE);
                    View i = findViewById(R.id.gameplayItemsButton);
                    i.setVisibility(View.VISIBLE);
                }
            }

            return true;
        }
        return false;
    }

    public boolean checkForGameOver() {
        if (EnterNames.thisPlayer.liveHP <= 0) {
            EnterNames.thisPlayer.liveHP = 0;
            toggleContinueVisible();
            consoleOutput += gameOver;
            if (roomCount == 1) {
                diedInEncounter = true;
            }
            return true;
        }
        return false;
    }

    public int attack() {
        int dealtDamage = EnterNames.thisPlayer.determineHit(thisRoom.numberOne);
        if (dealtDamage != 0) {
            if (usedLuckyMarksman) {
                thisRoom.numberOne.liveHP -= dealtDamage;
                dealtDamage *= 2;
                consoleOutput = displayHit + dealtDamage + ". " + luckyMarksmanHit;
                if (dealtDamage > (EnterNames.thisPlayer.attackPower * 2)) {
                    consoleOutput += criticalHit;
                }
            } else {
                consoleOutput = displayHit + dealtDamage + ". ";
                if (dealtDamage > EnterNames.thisPlayer.attackPower) {
                    consoleOutput += criticalHit;
                }
            }
        } else {
            consoleOutput = displayMiss;
        }
        attackTurnAdvantage = false;
        magicTurnAdvantage = false;
        checkIfEnemyDefeated();
        return dealtDamage;
    }

    public void counterAttack() {
        hasCountered = true;
        attackTurnAdvantage = false;
        magicTurnAdvantage = false;
    }

    public void defend() {
        hasDefended = true;
        attackTurnAdvantage = false;
        magicTurnAdvantage = false;
    }

    public void look() {
        if (thisRoom.disSearchable) {
            if (!thisRoom.roomSearched) {
                int foundSecret;
                if (Spell.usedVision) {
                    double isNegativeSecret = Math.random();
                    if (isNegativeSecret < negativeSecretChance) {
                        foundSecret = (int) (4 + (3 * Math.random()));
                    } else {
                        foundSecret = (int) (1 + (3 * Math.random()));
                    }
                    Spell.usedVision = false;
                } else {
                    foundSecret = EnterNames.thisPlayer.foundSecret();
                }
                if (foundSecret == 0) {
                    //secret was not found
                    consoleOutput = noSecretFound;
                } else {
                    //secret was found
                    secretsFoundCounter++;
                    //setting secret intro
                    double whichIntro = Math.random();
                    if (whichIntro < .333) {
                        consoleOutput = secretIntro1;
                    } else if (whichIntro < .666) {
                        consoleOutput = secretIntro2;
                    } else {
                        consoleOutput = secretIntro3;
                    }
                    //determining output for type of secret
                    gameInfo.setTextSize(15);
                    if (foundSecret == 1) {
                        consoleOutput += foundAttackUp + nothingElse;
                        EnterNames.thisPlayer.buff((byte) 0);
                    } else if (foundSecret == 2) {
                        consoleOutput += foundDefenseUp + nothingElse;
                        EnterNames.thisPlayer.buff((byte) 2);
                    } else if (foundSecret == 3) {
                        isAddingSpell = true;
                        learningFromWarlock = false;
                        determineWhichSpellsToLearn();
                        foundNewSpell = "You found an ancient book...it can teach you the spell " +
                                spellsToLearn[0].name + "! Read it? ";
                        consoleOutput += foundNewSpell;
                        toggleYesAndNoVisible();
                        //EnterNames.thisPlayer.myPoints += 300;
                    } else if (foundSecret == 4) {
                        gameInfo.setTextSize(16);
                        consoleOutput += tripwire;
                        if (EnterNames.thisPlayer.agilityValue > 5) {
                            consoleOutput += tripwireSuccess;
                            determineNegativeSecretReward();
                        } else {
                            consoleOutput += tripwireFail;
                            EnterNames.thisPlayer.liveHP -= 14;
                            if(checkForGameOver()) {
                                return;
                            }
                            determineNegativeSecretReward();
                        }
                    } else if (foundSecret == 5) {
                        consoleOutput += magicSapper;
                        if (EnterNames.thisPlayer.accuracyValue > 5) {
                            consoleOutput += magicSapperSuccess;
                        } else {
                            consoleOutput += magicSapperFail;
                            if (EnterNames.thisPlayer.liveMP >= 8) {
                                EnterNames.thisPlayer.liveMP -= 8;
                            } else {
                                EnterNames.thisPlayer.liveMP = 0;
                            }
                        }
                        determineNegativeSecretReward();
                    } else {
                        consoleOutput += scrollQuestion;
                        if (EnterNames.thisPlayer.intelligenceValue > 5) {
                            consoleOutput += scrollQuestionSuccess;
                            determineNegativeSecretReward();
                        } else {
                            consoleOutput += scrollQuestionFail;
                            EnterNames.thisPlayer.liveHP -= 13;
                            if(checkForGameOver()) {
                                return;
                            }
                            determineNegativeSecretReward();
                        }
                    }
                }
                thisRoom.roomSearched = true;
                setHud();
            } else {
                consoleOutput = alreadySearched;
            }
        }  else {
            consoleOutput = unsearchable;
        }
    }

    public void determineNegativeSecretReward() {
        double whichSecret = Math.random();
        if (whichSecret < .333) {
            consoleOutput += foundAttackUp + nothingElse;
            EnterNames.thisPlayer.buff((byte) 0);
        } else if (whichSecret < .666) {
            consoleOutput += foundDefenseUp + nothingElse;
            EnterNames.thisPlayer.buff((byte) 2);
        } else {
            isAddingSpell = true;
            learningFromWarlock = false;
            determineWhichSpellsToLearn();
            foundNewSpell = "You found an ancient book...it can teach you the spell " +
                    spellsToLearn[0].name + "! Read it? ";
            consoleOutput += foundNewSpell;
            toggleYesAndNoVisible();
        }
    }

    public void determineWhichSpellsToLearn() {
        byte spellID = generateSpellID();
        for (byte index = 0; index < EnterNames.thisPlayer.spells.length; index++) {
            if (spellID == EnterNames.thisPlayer.spells[index].id) {
                spellID = generateSpellID();
                index = -1;
            }
        }
        if (spellID == 3 || spellID == 4) {
            spellsToLearn[0] = new Spell((byte) 3);
            spellsToLearn[1] = new Spell((byte) 4);
        } else {
            spellsToLearn[0] = new Spell(spellID);
        }
        SpellSelection.selectSlotForSpell = "Select Spell slot for " + spellsToLearn[0].name;
    }

    public byte generateSpellID() {
        return (byte) (Math.random() * 11);
    }

    public void toggleYesAndNoVisible() {
        View a = findViewById(R.id.attackButton);
        a.setVisibility(View.GONE);
        View co = findViewById(R.id.counterButton);
        co.setVisibility(View.GONE);
        View d = findViewById(R.id.defendButton);
        d.setVisibility(View.GONE);
        View p = findViewById(R.id.progressButton);
        p.setVisibility(View.GONE);
        View l = findViewById(R.id.lookButton);
        l.setVisibility(View.GONE);
        View i = findViewById(R.id.gameplayItemsButton);
        i.setVisibility(View.GONE);
        View s = findViewById(R.id.spellButton);
        s.setVisibility(View.GONE);
        View c = findViewById(R.id.castButton);
        c.setVisibility(View.GONE);
        View y = findViewById(R.id.sayYesButton);
        y.setVisibility(View.VISIBLE);
        View n = findViewById(R.id.sayNoButton);
        n.setVisibility(View.VISIBLE);
    }

    public void toggleYesAndNoInvisible() {
        View i = findViewById(R.id.gameplayItemsButton);
        i.setVisibility(View.VISIBLE);
        View p = findViewById(R.id.progressButton);
        p.setVisibility(View.VISIBLE);
        View l = findViewById(R.id.lookButton);
        l.setVisibility(View.VISIBLE);
        View s = findViewById(R.id.spellButton);
        s.setVisibility(View.VISIBLE);
        View c = findViewById(R.id.castButton);
        c.setVisibility(View.VISIBLE);
        View y = findViewById(R.id.sayYesButton);
        y.setVisibility(View.GONE);
        View n = findViewById(R.id.sayNoButton);
        n.setVisibility(View.GONE);
        View done = findViewById(R.id.doneButtonGameplay);
        done.setVisibility(View.GONE);
    }

    public void toggleContinueVisible() {
        View a = findViewById(R.id.attackButton);
        a.setVisibility(View.GONE);
        View co = findViewById(R.id.counterButton);
        co.setVisibility(View.GONE);
        View d = findViewById(R.id.defendButton);
        d.setVisibility(View.GONE);
        View i = findViewById(R.id.gameplayItemsButton);
        i.setVisibility(View.GONE);
        View p = findViewById(R.id.progressButton);
        p.setVisibility(View.GONE);
        View l = findViewById(R.id.lookButton);
        l.setVisibility(View.GONE);
        View s = findViewById(R.id.spellButton);
        s.setVisibility(View.GONE);
        View c = findViewById(R.id.castButton);
        c.setVisibility(View.GONE);
        View con = findViewById(R.id.continueButton);
        con.setVisibility(View.VISIBLE);
    }

    public void toggleReturnToCampVisible() {
        View a = findViewById(R.id.attackButton);
        a.setVisibility(View.GONE);
        View co = findViewById(R.id.counterButton);
        co.setVisibility(View.GONE);
        View d = findViewById(R.id.defendButton);
        d.setVisibility(View.GONE);
        View s = findViewById(R.id.spellButton);
        s.setVisibility(View.GONE);
        View c = findViewById(R.id.castButton);
        c.setVisibility(View.GONE);
        View r = findViewById(R.id.returnToCampButton);
        r.setVisibility(View.VISIBLE);
    }

    public void spell() {
        if (!magicTurnAdvantage) {
            consoleOutput = doNotHaveMagicTurn;
        } else if (EnterNames.thisPlayer.liveMP == 0) {
            consoleOutput = noMP;
        } else {
            startActivity(new Intent(Gameplay.this, SpellSelection.class));
        }
    }

    public void cast(byte spellIndex) {
        if (!magicTurnAdvantage) {
            consoleOutput = doNotHaveMagicTurn;
        } else if (spellNum == -1) {
            consoleOutput = noSpellSelected;
        } else if (isBattling && !(EnterNames.thisPlayer.spells[spellIndex].isCombatSpell)) {
            consoleOutput = explorationOnly;
        } else if (!isBattling && !(EnterNames.thisPlayer.spells[spellIndex].isExplorationSpell)) {
            consoleOutput = combatOnly;
        } else if (EnterNames.thisPlayer.spells[spellIndex].mpCost > EnterNames.thisPlayer.liveMP) {
            consoleOutput = notEnoughMP;
        } else if (enemyEffectCounter > 0 && EnterNames.thisPlayer.spells[spellIndex].enemyEffect) {
            enemyAlreadyHasEffect = "The " + thisRoom.numberOne.name + " already has an " +
                    "effect active, you must wait until the effect passes. ";
            consoleOutput = enemyAlreadyHasEffect;
        } else if (EnterNames.thisPlayer.spells[spellIndex].id < 6 &&
                EnterNames.thisPlayer.spells[spellIndex].id > 3 && !hasSphericon) {
            consoleOutput = noSphericon;
        } else if (EnterNames.thisPlayer.spells[spellIndex].id == 10 && !thisRoom.disSearchable) {
            consoleOutput = visionCannotSearch;
        } else if (EnterNames.thisPlayer.spells[spellIndex].id == 10 && thisRoom.roomSearched) {
            consoleOutput = visionAlreadySearched;
        } else {
            newLastSpellText = "Last used: " + EnterNames.thisPlayer.spells[spellNum].name;
            lastSpellUsed.setText(newLastSpellText);
            EnterNames.thisPlayer.spells[spellIndex].spellCast(EnterNames.thisPlayer, thisRoom.numberOne);
            updateCastSuccess(spellIndex);
            consoleOutput = castSuccess + EnterNames.thisPlayer.spells[spellIndex].getExtraText();
            if (isBattling) {
                magicTurnAdvantage = false;
                checkIfEnemyDefeated();
                //updating myEffectSpellNum and enemyEffectSpellNum
                if (thisRoom.numberOne.liveHP > 0 &&
                        EnterNames.thisPlayer.spells[spellIndex].hasEffect &&
                        EnterNames.thisPlayer.spells[spellIndex].shouldUpdateSpellNum) {
                    if (EnterNames.thisPlayer.spells[spellIndex].enemyEffect) {
                        enemyEffectSpellNum = spellIndex;
                    } else {
                        if (EnterNames.thisPlayer.spells[spellIndex].id == 3) {
                            sphericonSpellNum = spellIndex;
                        } else {
                            myEffectSpellNum = spellIndex;
                        }
                    }
                }
            }
        }
    }

    public void updateCastSuccess(byte spellIndex) {
        castSuccess = "You cast " + EnterNames.thisPlayer.spells[spellIndex].name + "...";
    }

    public void configureDungeon() {
        tempPlayerValues[0] = EnterNames.thisPlayer.liveHP;
        tempPlayerValues[1] = EnterNames.thisPlayer.liveMP;
        tempPlayerValues[2] = EnterNames.thisPlayer.strengthValue;
        tempPlayerValues[3] = EnterNames.thisPlayer.accuracyValue;
        tempPlayerValues[4] = EnterNames.thisPlayer.defenseValue;
        tempPlayerValues[5] = EnterNames.thisPlayer.agilityValue;
        tempPlayerValues[6] = EnterNames.thisPlayer.intelligenceValue;
        tempPlayerValues[7] = EnterNames.thisPlayer.magicValue;
        tempPlayerValues[8] = EnterNames.thisPlayer.luckValue;

        tempGold = EnterNames.thisPlayer.myGold;
        byte variableRoomCount = (byte) Math.round(5 * Math.random());
        byte baseRoomCount;
        switch (thisDungeon[0]) {
            case 0:
                baseRoomCount = 10;
                negativeSecretChance = negativeSecretValues[0];
                break;
            case 1:
                baseRoomCount = 15;
                negativeSecretChance = negativeSecretValues[1];
                break;
            case 2:
                baseRoomCount = 20;
                negativeSecretChance = negativeSecretValues[2];
                break;
            case 3:
                baseRoomCount = 25;
                negativeSecretChance = negativeSecretValues[3];
                break;
            default:
                baseRoomCount = 0;
                variableRoomCount = 1;
                isBattling = true;
        }
        roomCount = (byte) (baseRoomCount + variableRoomCount);
        if (thisDungeon == firstDungeon) {
            liveRoomCount = roomCount;
        }
        if (diedInEncounter) {
            thisRoom.numberOne.liveHP = thisRoom.numberOne.hp;
            diedInEncounter = false;
        } else {
            thisRoom = new Room();
        }
    }

}