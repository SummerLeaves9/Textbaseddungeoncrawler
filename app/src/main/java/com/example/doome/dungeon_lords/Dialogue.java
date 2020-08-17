package com.example.doome.dungeon_lords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dialogue extends AppCompatActivity {

    public static boolean atHouse = false;

    public static boolean atOlympus = false;

    private int responseIndex = 0;

    private int conversationProgress = 0;

    String[][] currentConversation;

    String[][] currentResponses;

    private static final String houseOneMonologue = "'The man approached my house much like you did. " +
            "He claimed to be a god, and put on a light show with his big metallic " +
            "cannons. No one's ever seen anything like what he did, at least around " +
            "here. He said I would sacrifice my crops in exchange for spiritual " +
            "redemption, so I'd go to heaven. But my bloodline has a tradition of not " +
            "believing in that stuff, since we pretty much live on our own. So I think " +
            "he's just someone from a far-off land who wants free things. But he was " +
            "downright intimidating, I tell you what. I told him no, but then he " +
            "threatened to shoot at me with his cannons, and took what he wanted anyway.' ";

    private static final String houseOneMonologueTwo = "'Well, " +
            "thankfully he dropped this piece of armor with an insignia on it. A nicely " +
            "made piece. Otherwise I'd have no evidence that he was here...say, " +
            "why don't you take it? It might lead you to him. God or no god, he was " +
            "still incredibly powerful, so maybe he knows something about why you " +
            "can't seem to remember anything.'";

    private static final String[][] houseConversation1 = {
            {"You see a man on the porch of the house. As you approach, he draws a sword. 'Come" +
                    " to terrorize my family again? Steal my harvest?'"},
            {"You get closer and give the man a better view of your face. 'Oh', says the man. " +
                    "'Thought you were someone else. But I don't know you, what do you go by?'",
                    "You get closer and give the man a better view of your face. 'You're not " +
                            "him,' the man admits. 'I thought you were gunna take these here " +
                            "crops from me again. But if ya ain't, then why are you here? "},
            {"'Huh, " + EnterNames.thisPlayer.name + ". Funny name. So what brings you here, " +
                    EnterNames.thisPlayer.name + "?'", "'Fine. I'm William. And you're? " +
                    EnterNames.thisPlayer.name + ". Funny name. Why are you here, then?'", "'That" +
                    " sounds rough. I can tell you the story of my encounter with the man I " +
                    "thought you were. It ain't much, but it's honest truth.'", "'Gladly. Have a " +
                    "seat.'"},
            {"'That sounds rough. I can tell you the story of my encounter with the man I " +
                    "thought you were. It ain't much, but it's honest truth.'", "'Gladly. Have a " +
                    "seat.'", "'That sounds rough. I can tell you the story of my encounter with " +
                    "the man I thought you were. It ain't much, but it's honest truth.'",
                    "'Gladly. Have a seat.'", houseOneMonologue, houseOneMonologue,
                    houseOneMonologue, houseOneMonologue},
            {houseOneMonologue, houseOneMonologue, houseOneMonologue, houseOneMonologue,
                    houseOneMonologue, houseOneMonologue, houseOneMonologue, houseOneMonologue,
                    houseOneMonologueTwo, "He dropped a piece of his massive armor when he was " +
                    "hauling away my corn. There's an insignia on it, maybe you can use it to " +
                    "find him! If you could get my crops back for me somehow, I'd be in your debt.",
                    houseOneMonologueTwo, "He dropped a piece of his massive armor when he was " +
                    "hauling away my corn. There's an insignia on it, maybe you can use it to " +
                    "find him! If you could get my crops back for me somehow, I'd be in your debt.",
                    houseOneMonologueTwo, "He dropped a piece of his massive armor when he was " +
                    "hauling away my corn. There's an insignia on it, maybe you can use it to " +
                    "find him! If you could get my crops back for me somehow, I'd be in your debt.",
                    houseOneMonologueTwo, "He dropped a piece of his massive armor when he was " +
                    "hauling away my corn. There's an insignia on it, maybe you can use it to " +
                    "find him! If you could get my crops back for me somehow, I'd be in your debt."},
            {houseOneMonologueTwo, "He dropped a piece of his massive armor when he was " +
                    "hauling away my corn. There's an insignia on it, maybe you can use it to " +
                    "find him! If you could get my crops back for me somehow, I'd be in your debt.",
                    houseOneMonologueTwo, "He dropped a piece of his massive armor when he was " +
                    "hauling away my corn. There's an insignia on it, maybe you can use it to " +
                    "find him! If you could get my crops back for me somehow, I'd be in your debt.",
                    houseOneMonologueTwo, "He dropped a piece of his massive armor when he was " +
                    "hauling away my corn. There's an insignia on it, maybe you can use it to " +
                    "find him! If you could get my crops back for me somehow, I'd be in your debt.",
                    houseOneMonologueTwo, "He dropped a piece of his massive armor when he was " +
                    "hauling away my corn. There's an insignia on it, maybe you can use it to " +
                    "find him! If you could get my crops back for me somehow, I'd be in your debt.",
                    houseOneMonologueTwo, "He dropped a piece of his massive armor when he was " +
                    "hauling away my corn. There's an insignia on it, maybe you can use it to " +
                    "find him! If you could get my crops back for me somehow, I'd be in your debt.",
                    houseOneMonologueTwo, "He dropped a piece of his massive armor when he was " +
                    "hauling away my corn. There's an insignia on it, maybe you can use it to " +
                    "find him! If you could get my crops back for me somehow, I'd be in your debt.",
                    houseOneMonologueTwo, "He dropped a piece of his massive armor when he was " +
                    "hauling away my corn. There's an insignia on it, maybe you can use it to " +
                    "find him! If you could get my crops back for me somehow, I'd be in your debt.",
                    houseOneMonologueTwo, "He dropped a piece of his massive armor when he was " +
                    "hauling away my corn. There's an insignia on it, maybe you can use it to " +
                    "find him! If you could get my crops back for me somehow, I'd be in your debt.",
                    "'I'd be mighty thankful if you did.'", "'Don't mention it. And if it's in " +
                    "your power to get my crops back, I'd appreciate it.'", "'Best of luck, " +
                    EnterNames.thisPlayer.name + ". Get along now, I've got work to do.'", "He " +
                    "gives you a sour look, but it transforms into a smile. 'Just don't forget " +
                    "who gave that to ya. Get along, now.'", "'I'd be mighty thankful if you did.'",
                    "'Don't mention it. And if it's in your power to get my crops back, I'd " +
                    "appreciate it.'", "'Best of luck, " + EnterNames.thisPlayer.name + ". Get " +
                    "along now, I've got work to do.'", "He gives you a sour look, but it " +
                    "transforms into a smile. 'Just don't forget who gave that to ya. Get along, " +
                    "now.'", "'I'd be mighty thankful if you did.'", "'Don't mention it. And if " +
                    "it's in " +
                    "your power to get my crops back, I'd appreciate it.'", "'Best of luck, " +
                    EnterNames.thisPlayer.name + ". Get along now, I've got work to do.'", "He " +
                    "gives you a sour look, but it transforms into a smile. 'Just don't forget " +
                    "who gave that to ya. Get along, now.'", "'I'd be mighty thankful if you did.'",
                    "'Don't mention it. And if it's in your power to get my crops back, I'd " +
                    "appreciate it.'", "'Best of luck, " + EnterNames.thisPlayer.name + ". Get " +
                    "along now, I've got work to do.'", "He gives you a sour look, but it " +
                    "transforms into a smile. 'Just don't forget who gave that to ya. Get along, " +
                    "now.'"},
            { "'I'd be mighty thankful if you did.'", "'Don't mention it. And if it's in " +
                    "your power to get my crops back, I'd appreciate it.'", "'Best of luck, " +
                    EnterNames.thisPlayer.name + ". Get along now, I've got work to do.'", "He " +
                    "gives you a sour look, but it transforms into a smile. 'Just don't forget " +
                    "who gave that to ya. Get along, now.'", "'I'd be mighty thankful if you did.'",
                    "'Don't mention it. And if it's in your power to get my crops back, I'd " +
                    "appreciate it.'", "'Best of luck, " + EnterNames.thisPlayer.name + ". Get " +
                    "along now, I've got work to do.'", "He gives you a sour look, but it " +
                    "transforms into a smile. 'Just don't forget who gave that to ya. Get along, " +
                    "now.'", "'I'd be mighty thankful if you did.'", "'Don't mention it. And if " +
                    "it's in " +
                    "your power to get my crops back, I'd appreciate it.'", "'Best of luck, " +
                    EnterNames.thisPlayer.name + ". Get along now, I've got work to do.'", "He " +
                    "gives you a sour look, but it transforms into a smile. 'Just don't forget " +
                    "who gave that to ya. Get along, now.'", "'I'd be mighty thankful if you did.'",
                    "'Don't mention it. And if it's in your power to get my crops back, I'd " +
                    "appreciate it.'", "'Best of luck, " + EnterNames.thisPlayer.name + ". Get " +
                    "along now, I've got work to do.'", "He gives you a sour look, but it " +
                    "transforms into a smile. 'Just don't forget who gave that to ya. Get along, " +
                    "now.'", "'I'd be mighty thankful if you did.'", "'Don't mention it. And if " +
                    "it's in " +
                    "your power to get my crops back, I'd appreciate it.'", "'Best of luck, " +
                    EnterNames.thisPlayer.name + ". Get along now, I've got work to do.'", "He " +
                    "gives you a sour look, but it transforms into a smile. 'Just don't forget " +
                    "who gave that to ya. Get along, now.'", "'I'd be mighty thankful if you did.'",
                    "'Don't mention it. And if it's in your power to get my crops back, I'd " +
                    "appreciate it.'", "'Best of luck, " + EnterNames.thisPlayer.name + ". Get " +
                    "along now, I've got work to do.'", "He gives you a sour look, but it " +
                    "transforms into a smile. 'Just don't forget who gave that to ya. Get along, " +
                    "now.'", "'I'd be mighty thankful if you did.'", "'Don't mention it. And if " +
                    "it's in " +
                    "your power to get my crops back, I'd appreciate it.'", "'Best of luck, " +
                    EnterNames.thisPlayer.name + ". Get along now, I've got work to do.'", "He " +
                    "gives you a sour look, but it transforms into a smile. 'Just don't forget " +
                    "who gave that to ya. Get along, now.'", "'I'd be mighty thankful if you did.'",
                    "'Don't mention it. And if it's in your power to get my crops back, I'd " +
                    "appreciate it.'", "'Best of luck, " + EnterNames.thisPlayer.name + ". Get " +
                    "along now, I've got work to do.'", "He gives you a sour look, but it " +
                    "transforms into a smile. 'Just don't forget who gave that to ya. Get along, " +
                    "now.'", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", ""}
    };

    private static final String[][] houseResponseText1 = {
            {"No, I'm an explorer. I just want to talk.", "I don't want any trouble. Who do you " +
                    "think I am?"},
            {"*Tell him your name*", "First, what's your name?", "I'm trying to figure out how I " +
                    "got here. I've been wandering for the last few days, but nothing looks " +
                    "familiar.", "I don't exactly know. Can you tell me about whoever is " +
                    "terrorizing your family?"},
            {"I'm trying to figure out how I got here. I've been wandering for the last few " +
                    "days, but nothing looks familiar.", "I don't exactly know. Can you tell me " +
                    "about whoever is terrorizing your family?", "I'm trying to figure out how I " +
                    "got here. I've been wandering for the last few days, but nothing looks " +
                    "familiar.", "I don't exactly know. Can you tell me about whoever is " +
                    "terrorizing your family?", "Sure.", "Thanks.", "Sure.", "Thanks."},
            {"Sure.", "Thanks.", "Sure.", "Thanks.", "Sure.", "Thanks.", "Sure.", "Thanks.", "I'm sorry " +
                    "that happened to you.", "How can I help?", "I'm sorry that happened to you.",
                    "How can I help?", "I'm sorry that happened to you.", "How can I help?",
                    "I'm sorry that happened to you.", "How can I help?"},
            {"I'm sorry that happened to you.", "How can I help?", "I'm sorry that happened to " +
                    "you.", "How can I help?", "I'm sorry that happened to you.", "How can I " +
                    "help?", "I'm sorry that happened to you.", "How can I help?", "I'm sorry " +
                    "that happened to you.", "How can I help?", "I'm sorry that happened to " +
                    "you.", "How can I help?", "I'm sorry that happened to you.", "How can I " +
                    "help?", "I'm sorry that happened to you.", "How can I help?", "Maybe I " +
                    "could even get your crops back!", "Thank you, I'll take good care of it.",
                    "I'll gladly try.", "Nice, some new armor! I mean- I'll try to get your " +
                    "crops back, William. Thank you.", "Maybe I could even get your crops back!",
                    "Thank you, I'll take good care of it.", "I'll gladly try.", "Nice, some new " +
                    "armor! I mean- I'll try to get your crops back, William. Thank you.", "Maybe I " +
                    "could even get your crops back!", "Thank you, I'll take good care of it.",
                    "I'll gladly try.", "Nice, some new armor! I mean- I'll try to get your " +
                    "crops back, William. Thank you.", "Maybe I could even get your crops back!",
                    "Thank you, I'll take good care of it.", "I'll gladly try.", "Nice, some new " +
                    "armor! I mean- I'll try to get your crops back, William. Thank you."},
            {"Maybe I could even get your crops back!", "Thank you, I'll take good care of it.",
                    "I'll gladly try.", "Nice, some new armor! I mean- I'll try to get your " +
                    "crops back, William. Thank you.", "Maybe I could even get your crops back!",
                    "Thank you, I'll take good care of it.", "I'll gladly try.", "Nice, some new " +
                    "armor! I mean- I'll try to get your crops back, William. Thank you.",
                    "Maybe I could even get your crops back!", "Thank you, I'll take good care " +
                    "of it.", "I'll gladly try.", "Nice, some new armor! I mean- I'll try to " +
                    "get your crops back, William. Thank you.", "Maybe I could even get your " +
                    "crops back!", "Thank you, I'll take good care of it.", "I'll gladly try.",
                    "Nice, some new armor! I mean- I'll try to get your crops back, William. " +
                    "Thank you.", "Maybe I could even get your crops back!", "Thank you, I'll " +
                    "take good care of it.",
                    "I'll gladly try.", "Nice, some new armor! I mean- I'll try to get your " +
                    "crops back, William. Thank you.", "Maybe I could even get your crops back!",
                    "Thank you, I'll take good care of it.", "I'll gladly try.", "Nice, some new " +
                    "armor! I mean- I'll try to get your crops back, William. Thank you.",
                    "Maybe I could even get your crops back!", "Thank you, I'll take good care " +
                    "of it.", "I'll gladly try.", "Nice, some new armor! I mean- I'll try to " +
                    "get your crops back, William. Thank you.", "Maybe I could even get your " +
                    "crops back!", "Thank you, I'll take good care of it.", "I'll gladly try.",
                    "Nice, some new armor! I mean- I'll try to get your crops back, William. " +
                    "Thank you.", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", ""}
    };

    private static final String houseConversation2Monologue1 = "‘Alright, wait here. I’ll be " +
            "right back with my favorite one. It happens to have all 6 of them!’  The woman " +
            "comes back with the " +
            "painting. It’s almost as wide as her whole armspan, and about 4 feet tall. She " +
            "starts explaining who they are, but it turns to muffles in your ears. The armor, " +
            "the faces, the insignia. It matches the piece of armor William gave you. You knew " +
            "it looked familiar. You know the gods. You’ve…known them for years! They’re your " +
            "best friends! But how??";

    private static final String houseConversation2Monologue2 = "‘No, I’ve made so many at this " +
            "point that they have decided favorites. I have their least favorite ones. But " +
            "I’ll be right back with my personal favorite one. It happens to have all 6 of " +
            "them!’ You find a cushioned chair, another memory returns to you. You used to sit " +
            "in cushioned chairs all the time, but it’s been so long now. The woman comes back " +
            "with the painting. It’s almost as wide as her whole armspan, and about 4 feet " +
            "tall. She starts explaining who they are, but it turns to muffles in your ears. " +
            "The armor, the faces, the insignia. It matches the piece of armor William gave " +
            "you. You knew it looked familiar. You know the gods. You’ve…known them for years! " +
            "They’re your best friends! But how??";

    private static final String[][] houseConversation2 = {
            {"You walk up to the unsuspecting house. It’s much nicer than William’s house, not " +
                    "that he wasn’t well off. This time you have to knock on the door, a " +
                    "formality that somehow hasn’t slipped from your memory, unlike everything " +
                    "else. You’re met with a slim figure in an intricate jacket. ‘Can I help " +
                    "you?’"},
            {"‘Hmm. You look rather intimidating, but you also look truly distraught. Plus, " +
                    "you’re the only visitor I get besides them. Please, come in.’", "‘A bit " +
                    "blunt, aren’t we?’ The woman paces back into her house a step."},
            {"‘I mean none other than the gods themselves. You see, I am a painter. They come " +
                    "to my house every few weeks to make me paint them in their image. ‘",
                    "‘Of course. Have a seat in the parlor, I’ll be back with drinks in a minute.’",
                    "‘Oh, that’s awful! …But please stow your weapon. I’m uncomfortable with it " +
                            "in your hands.’", "‘Hmm. You look rather intimidating, but you also " +
                    "look truly distraught. Plus, you’re the only visitor I get besides them. " +
                    "Please, come in.’"},
            {"She leads you into the house. ‘It’s not that bad. I’m more than flattered that " +
                    "the gods chose me over anyone else to immortalize them in my work. " +
                    "Besides, they say they’ll put me on the highest throne in heaven as long " +
                    "as I keep painting them. Would you like to see one of  them?’", "She leads " +
                    "you into the house. ‘I’m afraid I actually haven’t learned much. They are " +
                    "particularly quiet when I paint them, except to tell me they’ll put me on " +
                    "the highest throne in heaven for doing this. But I have kept a couple " +
                    "paintings, if you’d like to see.’", "You find a cushioned chair, another " +
                    "memory returns to you. You used to sit in cushioned chairs all the time, " +
                    "but it’s been so long now. The woman returns with water.", "You see a " +
                    "painting in the parlor, of two nude people, reaching out to each other " +
                    "with an abstract explosion of colors between them. Did she paint this? " +
                    "If she did, she’s an incredible painter.", "She lowers her guard a bit, " +
                    "and motions you to come in. ‘I’ll be back with drinks in a minute.’", "She " +
                    "lowers her guard a bit, and motions you to come in. ‘I’ll be back with " +
                    "drinks in a minute.’", "‘I mean none other than the gods themselves. " +
                    "You see, I am a painter. They come to my house every few weeks to make me " +
                    "paint them in their image. ‘", "‘Of course. Have a seat in the parlor, I’ll " +
                    "be back with drinks in a minute.’"},
            {houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2, "‘So, you want " +
                    "to know about the gods. Lucky for you, I get to see them quite often. They " +
                    "come here every few weeks and make me paint them. They take the ones they " +
                    "like the most and leave me with the ones they least like. Maybe you’d like " +
                    "to see one?’", "‘Haha, no. I am merely the gods’ painter. They come here " +
                    "every few weeks and make me paint them. They take the ones they like the " +
                    "most and leave me with the ones they least like. Maybe you’d like to see " +
                    "one?’", "You find a cushioned chair, another memory returns to you. You " +
                    "used to sit in cushioned chairs all the time, but it’s been so long now. " +
                    "The woman returns with water.", "You find a cushioned chair, another " +
                    "memory returns to you. You used to sit in cushioned chairs all the time, " +
                    "but it’s been so long now. The woman returns with water.", "You find a " +
                    "cushioned chair, another memory returns to you. You used to sit in " +
                    "cushioned chairs all the time, but it’s been so long now. The woman " +
                    "returns with water.", "You see a painting in the parlor, of two nude " +
                    "people, reaching out to each other with an abstract explosion of colors " +
                    "between them. Did she paint this? If she did, she’s an incredible painter.",
                    "You find a cushioned chair, another memory returns to you. You used to sit " +
                            "in cushioned chairs all the time, but it’s been so long now. The " +
                            "woman returns with water.", "You see a painting in the parlor, of " +
                    "two nude people, reaching out to each other with an abstract explosion of " +
                    "colors between them. Did she paint this? If she did, she’s an incredible " +
                    "painter.", "She leads you into the house. ‘It’s not that bad. I’m more " +
                    "than flattered that the gods chose me over anyone else to immortalize them " +
                    "in my work. Besides, they say they’ll put me on the highest throne in " +
                    "heaven as long as I keep painting them. Would you like to see one of  them?’",
                    "She leads you into the house. ‘I’m afraid I actually haven’t learned much. " +
                            "They are particularly quiet when I paint them, except to tell me " +
                            "they’ll put me on the highest throne in heaven for doing this. But " +
                            "I have kept a couple paintings, if you’d like to see.’", "You find " +
                    "a cushioned chair, another memory returns to you. You used to sit in " +
                    "cushioned chairs all the time, but it’s been so long now. The woman " +
                    "returns with water.", "You see a painting in the parlor, of two nude " +
                    "people, reaching out to each other with an abstract explosion of colors " +
                    "between them. Did she paint this? If she did, she’s an incredible painter."},
            {"'Call me Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way " +
                            "out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to " +
                    "help. Please shut the door on your way out.'", "'Call me Elizabeth. And I’m " +
                    "happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2, "‘So, you want " +
                    "to know about the gods. Lucky for you, I get to see them quite often. They " +
                    "come here every few weeks and make me paint them. They take the ones they " +
                    "like the most and leave me with the ones they least like. Maybe you’d like " +
                    "to see one?’", "‘Haha, no. I am merely the gods’ painter. They come here " +
                    "every few weeks and make me paint them. They take the ones they like the " +
                    "most and leave me with the ones they least like. Maybe you’d like to see " +
                    "one?’", "‘So, you want " +
                    "to know about the gods. Lucky for you, I get to see them quite often. They " +
                    "come here every few weeks and make me paint them. They take the ones they " +
                    "like the most and leave me with the ones they least like. Maybe you’d like " +
                    "to see one?’", "‘Haha, no. I am merely the gods’ painter. They come here " +
                    "every few weeks and make me paint them. They take the ones they like the " +
                    "most and leave me with the ones they least like. Maybe you’d like to see " +
                    "one?’", "‘So, you want " +
                    "to know about the gods. Lucky for you, I get to see them quite often. They " +
                    "come here every few weeks and make me paint them. They take the ones they " +
                    "like the most and leave me with the ones they least like. Maybe you’d like " +
                    "to see one?’", "‘Haha, no. I am merely the gods’ painter. They come here " +
                    "every few weeks and make me paint them. They take the ones they like the " +
                    "most and leave me with the ones they least like. Maybe you’d like to see " +
                    "one?’", "You find a cushioned chair, another memory returns to you. You " +
                    "used to sit in cushioned chairs all the time, but it’s been so long now. " +
                    "The woman returns with water.", "You find a cushioned chair, another memory " +
                    "returns to you. You used to sit in cushioned chairs all the time, but it’s " +
                    "been so long now. The woman returns with water.", "‘So, you want to know " +
                    "about the gods. Lucky for you, I get to see them quite often. They come " +
                    "here every few weeks and make me paint them. They take the ones they like " +
                    "the most and leave me with the ones they least like. Maybe you’d like to " +
                    "see one?’", "‘Haha, no. I am merely the gods’ painter. They come here " +
                    "every few weeks and make me paint them. They take the ones they like the " +
                    "most and leave me with the ones they least like. Maybe you’d like to see " +
                    "one?’", "You find a cushioned chair, another memory " +
                    "returns to you. You used to sit in cushioned chairs all the time, but it’s " +
                    "been so long now. The woman returns with water.", "You find a cushioned " +
                    "chair, another memory returns to you. You used to sit in cushioned chairs " +
                    "all the time, but it’s been so long now. The woman returns with water.",
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2, "‘So, you want " +
                    "to know about the gods. Lucky for you, I get to see them quite often. They " +
                    "come here every few weeks and make me paint them. They take the ones they " +
                    "like the most and leave me with the ones they least like. Maybe you’d like " +
                    "to see one?’", "‘Haha, no. I am merely the gods’ painter. They come here " +
                    "every few weeks and make me paint them. They take the ones they like the " +
                    "most and leave me with the ones they least like. Maybe you’d like to see " +
                    "one?’", "You find a cushioned chair, another memory " +
                    "returns to you. You used to sit in cushioned chairs all the time, but it’s " +
                    "been so long now. The woman returns with water.", "You find a cushioned " +
                    "chair, another memory returns to you. You used to sit in cushioned chairs " +
                    "all the time, but it’s been so long now. The woman returns with water."},
            {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way " +
                            "out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to " +
                    "help. Please shut the door on your way out.'", "'Call me Elizabeth. And I’m " +
                    "happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    "‘So, you want " +
                    "to know about the gods. Lucky for you, I get to see them quite often. They " +
                    "come here every few weeks and make me paint them. They take the ones they " +
                    "like the most and leave me with the ones they least like. Maybe you’d like " +
                    "to see one?’", "‘Haha, no. I am merely the gods’ painter. They come here " +
                    "every few weeks and make me paint them. They take the ones they like the " +
                    "most and leave me with the ones they least like. Maybe you’d like to see " +
                    "one?’", "‘So, you want to know about the gods. Lucky for you, I get to see " +
                    "them quite often. They " +
                    "come here every few weeks and make me paint them. They take the ones they " +
                    "like the most and leave me with the ones they least like. Maybe you’d like " +
                    "to see one?’", "‘Haha, no. I am merely the gods’ painter. They come here " +
                    "every few weeks and make me paint them. They take the ones they like the " +
                    "most and leave me with the ones they least like. Maybe you’d like to see " +
                    "one?’", houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2, "‘So, you want " +
                    "to know about the gods. Lucky for you, I get to see them quite often. They " +
                    "come here every few weeks and make me paint them. They take the ones they " +
                    "like the most and leave me with the ones they least like. Maybe you’d like " +
                    "to see one?’", "‘Haha, no. I am merely the gods’ painter. They come here " +
                    "every few weeks and make me paint them. They take the ones they like the " +
                    "most and leave me with the ones they least like. Maybe you’d like to see " +
                    "one?’", "‘So, you want " +
                    "to know about the gods. Lucky for you, I get to see them quite often. They " +
                    "come here every few weeks and make me paint them. They take the ones they " +
                    "like the most and leave me with the ones they least like. Maybe you’d like " +
                    "to see one?’", "‘Haha, no. I am merely the gods’ painter. They come here " +
                    "every few weeks and make me paint them. They take the ones they like the " +
                    "most and leave me with the ones they least like. Maybe you’d like to see " +
                    "one?’", "'Call me Elizabeth. And I’m happy to help. Please shut the door on " +
                    "your way out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to help. " +
                    "Please shut the door on your way out.'", "'Call me Elizabeth. And I’m happy " +
                    "to help. Please shut the door on your way out.'", "'Call me Elizabeth. And " +
                    "I’m happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2, "‘So, you want " +
                    "to know about the gods. Lucky for you, I get to see them quite often. They " +
                    "come here every few weeks and make me paint them. They take the ones they " +
                    "like the most and leave me with the ones they least like. Maybe you’d like " +
                    "to see one?’", "‘Haha, no. I am merely the gods’ painter. They come here " +
                    "every few weeks and make me paint them. They take the ones they like the " +
                    "most and leave me with the ones they least like. Maybe you’d like to see " +
                    "one?’", "‘So, you want " +
                    "to know about the gods. Lucky for you, I get to see them quite often. They " +
                    "come here every few weeks and make me paint them. They take the ones they " +
                    "like the most and leave me with the ones they least like. Maybe you’d like " +
                    "to see one?’", "‘Haha, no. I am merely the gods’ painter. They come here " +
                    "every few weeks and make me paint them. They take the ones they like the " +
                    "most and leave me with the ones they least like. Maybe you’d like to see " +
                    "one?’"},
            {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way " +
                            "out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to " +
                    "help. Please shut the door on your way out.'", "'Call me Elizabeth. And I’m " +
                    "happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way " +
                            "out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to " +
                    "help. Please shut the door on your way out.'", "'Call me Elizabeth. And I’m " +
                    "happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way " +
                            "out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to " +
                    "help. Please shut the door on your way out.'", "'Call me Elizabeth. And I’m " +
                    "happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way " +
                            "out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to " +
                    "help. Please shut the door on your way out.'", "'Call me Elizabeth. And I’m " +
                    "happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way " +
                            "out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to " +
                    "help. Please shut the door on your way out.'", "'Call me Elizabeth. And I’m " +
                    "happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2,
                    houseConversation2Monologue1, houseConversation2Monologue2},
            {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "",  "'Call me Elizabeth. And I’m happy to help. Please shut the door on " +
                    "your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way " +
                    "out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to " +
                    "help. Please shut the door on your way out.'", "'Call me Elizabeth. And I’m " +
                    "happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please " +
                            "shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way " +
                            "out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to " +
                    "help. Please shut the door on your way out.'", "'Call me Elizabeth. And I’m " +
                    "happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "'Call me Elizabeth. And I’m happy to help. Please " +
                            "shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way " +
                            "out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to " +
                    "help. Please shut the door on your way out.'", "'Call me Elizabeth. And I’m " +
                    "happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please " +
                            "shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way " +
                            "out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to " +
                    "help. Please shut the door on your way out.'", "'Call me Elizabeth. And I’m " +
                    "happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "'Call me Elizabeth. And I’m happy to help. Please " +
                            "shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way " +
                            "out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to " +
                    "help. Please shut the door on your way out.'", "'Call me Elizabeth. And I’m " +
                    "happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please " +
                            "shut the door on your way out.'",
                    "'Call me Elizabeth. And I’m happy to help. Please shut the door on your way " +
                            "out.'", "'Call me Elizabeth. And I’m happy to help. Please shut the " +
                    "door on your way out.'", "'Call me Elizabeth. And I’m happy to help. Please " +
                    "shut the door on your way out.'", "'Call me Elizabeth. And I’m happy to " +
                    "help. Please shut the door on your way out.'", "'Call me Elizabeth. And I’m " +
                    "happy to help. Please shut the door on your way out.'", "'Call me Elizabeth. " +
                    "And I’m happy to help. Please shut the door on your way out.'", "'Call me " +
                    "Elizabeth. And I’m happy to help. Please shut the door on your way out.'",
                }
    };

    private static final String[][] houseResponseText2 = {
            {"Hi, I’m " + EnterNames.thisPlayer.name + ". I woke up in a dungeon not too far " +
                    "from here and I’m trying to figure out how I got to this land. I know I’m " +
                    "not from around here, but I can’t remember much. Can you help me?",
                    "What can you tell me about the gods of Mount Olympus?"},
            {"Wait, who are you talking about?", "Thank you.", "I’m sorry, I just really need to " +
                    "know. I just woke up in a dungeon a few days ago and I have no memories " +
                    "from before then.", "Please, I can offer gold or some other compensation. " +
                    "They’re my only hope to figuring out why I’m here."},
            {"You mean, they come here whenever they want, make you paint them, and just take " +
                    "the painting for themselves? That’s awful.", "Really? What have you found " +
                    "out about them?", "*sit*", "*look at some paintings*", "Oh yeah, sorry.",
                    "If I must.", "Wait, who are you talking about?", "Thank you."},
            {"Yes, please.", "You mean they don’t take all the paintings you create for them?",
                    "Yes, please.", "You mean they don’t take all the paintings you create for " +
                    "them?", "Thanks.", "So, you seem pretty well off, living in a place like " +
                    "this. Are you by chance one of the gods?", "*sit*", "*sit*", "*sit*",
                    "*look at some paintings*", "*sit*", "*look at some paintings*", "You mean, " +
                    "they come here whenever they want, make you paint them, and just take the " +
                    "painting for themselves? That’s awful.", "Really? What have you found out " +
                    "about them?", "*sit*", "*look at some paintings*"},
            {"Miss, I didn’t catch your name. But thank you, I needed to see this.", "I know " +
                    "them! I’ve met with these gods before. I don’t know how, but they surely " +
                    "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                    "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?", "Yes, please.", "You " +
                    "mean they don’t take all the paintings you create for them?", "Yes, please.",
                    "You mean they don’t take all the paintings you create for them?", "Thanks.",
                    "So, you seem pretty well off, living in a place like this. Are you by " +
                            "chance one of the gods?", "Thanks.", "So, you seem pretty well off, " +
                    "living in a place like this. Are you by chance one of the gods?", "Thanks.",
                    "So, you seem pretty well off, living in a place like this. Are you by " +
                            "chance one of the gods?", "*sit*", "*sit*", "Thanks.", "So, you " +
                    "seem pretty well off, living in a place like this. Are you by chance one " +
                    "of the gods?", "*sit*", "*sit*", "Yes, please.", "You mean they don’t take " +
                    "all the paintings you create for them?", "Yes, please.", "You mean they " +
                    "don’t take all the paintings you create for them?", "Thanks.", "So, you " +
                    "seem pretty well off, living in a place like this. Are you by chance one " +
                    "of the gods?", "*sit*", "*sit*"},
            {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "Miss, I didn’t " +
                    "catch your name. But thank you, I needed to see this.", "I know " +
                    "them! I’ve met with these gods before. I don’t know how, but they surely " +
                    "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                    "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?", "Yes, please.", "You " +
                    "mean they don’t take all the paintings you create for them?", "Yes, please.",
                    "You mean they don’t take all the paintings you create for them?", "Yes, " +
                    "please.", "You mean they don’t take all the paintings you create for them?",
                    "Yes, please.", "You mean they don’t take all the paintings you create for " +
                    "them?", "Yes, please.", "You mean they don’t take all the paintings you " +
                    "create for them?", "Yes, please.", "You mean they don’t take all the " +
                    "paintings you create for them?", "Thanks.", "So, you " +
                    "seem pretty well off, living in a place like this. Are you by chance one of " +
                    "the gods?", "Thanks.", "So, you seem pretty well off, living in a place " +
                    "like this. Are you by chance one of the gods?", "Yes, please.", "You mean " +
                    "they don’t take all the paintings you create for them?", "Yes, please.",
                    "You mean they don’t take all the paintings you create for them?", "Thanks.",
                    "So, you " +
                    "seem pretty well off, living in a place like this. Are you by chance one of " +
                    "the gods?", "Thanks.", "So, you seem pretty well off, living in a place " +
                    "like this. Are you by chance one of the gods?",
                    "Miss, I didn’t catch your name. But thank you, I needed to see this.",
                    "I know " +
                    "them! I’ve met with these gods before. I don’t know how, but they surely " +
                    "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                    "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?", "Yes, please.", "You " +
                    "mean they don’t take all the paintings you create for them?", "Yes, please.",
                    "You mean they don’t take all the paintings you create for them?", "Thanks.",
                    "So, you seem pretty well off, living in a place like this. Are you by " +
                    "chance one of the gods?", "Thanks.", "So, you seem pretty well off, living in a place " +
                    "like this. Are you by chance one of the gods?"},
            {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "Miss, I didn’t " +
                    "catch your name. But thank you, I needed to see this.", "I know " +
                    "them! I’ve met with these gods before. I don’t know how, but they surely " +
                    "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                    "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?", "Miss, I didn’t " +
                    "catch your name. But thank you, I needed to see this.", "I know " +
                    "them! I’ve met with these gods before. I don’t know how, but they surely " +
                    "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                    "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?", "Miss, I didn’t " +
                    "catch your name. But thank you, I needed to see this.", "I know " +
                    "them! I’ve met with these gods before. I don’t know how, but they surely " +
                    "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                    "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?", "Yes, please.",
                    "You mean they don’t take all the paintings you create for them?",
                    "Yes, please.", "You mean they don’t take all the paintings you create for " +
                    "them?", "Yes, please.", "You mean they don’t take all the paintings you " +
                    "create for them?", "Yes, please.", "You mean they don’t take all the " +
                    "paintings you create for them?", "Miss, I didn’t " +
                    "catch your name. But thank you, I needed to see this.", "I know " +
                    "them! I’ve met with these gods before. I don’t know how, but they surely " +
                    "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                    "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?", "Yes, please.", "You mean they don’t take all the paintings you create for them?",
                    "Yes, please.", "You mean they don’t take all the paintings you create for " +
                    "them?", "Yes, please.", "You mean they don’t take all the paintings you create for them?",
                    "Yes, please.", "You mean they don’t take all the paintings you create for " +
                    "them?", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "Miss, I didn’t catch your name. But thank you, I needed to see this.",
                    "I know " +
                    "them! I’ve met with these gods before. I don’t know how, but they surely " +
                    "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                    "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?", "Yes, please.",
                    "You mean they don’t take all the paintings you create for them?",
                    "Yes, please.", "You mean they don’t take all the paintings you create for " +
                    "them?", "Yes, please.", "You mean they don’t take all the paintings you " +
                    "create for them?", "Yes, please.", "You mean they don’t take all the " +
                    "paintings you create for them?"},
            {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "",
                    "Miss, I didn’t catch your name. But thank you, I needed to see this.",
                    "I know " +
                    "them! I’ve met with these gods before. I don’t know how, but they surely " +
                    "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                    "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?",
                    "Miss, I didn’t catch your name. But thank you, I needed to see this.",
                    "I know " +
                            "them! I’ve met with these gods before. I don’t know how, but they surely " +
                            "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                            "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "",
                    "Miss, I didn’t catch your name. But thank you, I needed to see this.",
                    "I know " +
                            "them! I’ve met with these gods before. I don’t know how, but they surely " +
                            "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                            "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?",
                    "Miss, I didn’t catch your name. But thank you, I needed to see this.",
                    "I know " +
                            "them! I’ve met with these gods before. I don’t know how, but they surely " +
                            "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                            "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "Miss, I didn’t catch your name. But thank you, I needed to see this.",
                    "I know " +
                            "them! I’ve met with these gods before. I don’t know how, but they surely " +
                            "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                            "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?",
                    "Miss, I didn’t catch your name. But thank you, I needed to see this.",
                    "I know " +
                            "them! I’ve met with these gods before. I don’t know how, but they surely " +
                            "know my name. I have to get to the bottom of this. It’s been a pleasure, " +
                            "miss…?", "Miss, I didn’t catch your name. But thank you, I needed to see " +
                    "this.", "I know them! I’ve met with these gods before. I don’t know how, " +
                    "but they surely know my name. I have to get to the bottom of this. It’s " +
                    "been a pleasure, miss…?", "Miss, I didn’t catch your name. But thank you, " +
                    "I needed to see this.", "I know them! I’ve met with these gods before. I " +
                    "don’t know how, but they surely know my name. I have to get to the bottom " +
                    "of this. It’s been a pleasure, miss…?", "Miss, I didn’t catch your name. " +
                    "But thank you, I needed to see this.", "I know them! I’ve met with these " +
                    "gods before. I don’t know how, but they surely know my name. I have to get " +
                    "to the bottom of this. It’s been a pleasure, miss…?",
            },
            {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",}
    };

    private static final String houseConversation3Monologue = "You get in the bed and immediately feel your body go numb. You feel an intense cold sweat, butterflies in your stomach, and numbness everywhere. Your head really hurts. You pass out. The memories start to flow in a dreamlike way. You're not a travelling warrior living in 1500's Europe. You're from the year 2172. The last thing you remember before you woke up in that dungeon is you and your crew boarding a ship, no, a time machine. So that's how you got here. The mission was to extract some DNA from extinct species from " +
    "Europe and bring them to the present, to have them set free and repopulate. But, you notice your crew-oh yes! Your crew. They were Dustin, Ally, Josh, Peter, Katie, Brianna, and...Valentine. Your best friend. But the crew had packed a lot more gadgets than the mission allowed. Weapons, tons of armor, portable bunkers, and the strangest of all, a life form generator. A device that accepted a code of DNA, cell media, and food, and made any life form you wanted. Human, ant, or something entirely new. It was being used to attempt to recreate several extinct species in 2172, but to no avail. " +
    "The mission would enable your present day to know and love the extinct species. The trip to 1559 Europe would take a week of real time, living in the time machine. You couldn't be seen by anyone living there, so your commander set your course for a place no one had yet visited in the year 1559. Mount Olympus! Just like the lady from town was talking about. Life in the machine was quiet and peaceful. You got about 40% through a book Valentine recommended. But on the night before you were to arrive, you heard voices coming from the lounge. You saw Josh, " +
    "Katie, and Dustin. 'How many people live in this country?' 'About 10,000. But we won't have to worry about them all at once. Communication was slow back then.' 'Right, right. So the plan is to make about 100 at first, and raid a small village? And after that we just keep manufacturing them until we can fill 20 portable bunkers?' 'Yes sirree. I can't wait to tell " + EnterNames.thisPlayer.name + " about this.' You butted in. You were always the brutally honest type. 'Tell me about what, guys?' The 3 looked at you in horror. Josh smacked his " +
    "head. 'Dang it, " + EnterNames.thisPlayer.name + ", you ruined the surprise. We were going to tell you and Valentine, but you had to miss your sleep schedule today, didn't you?' You got flustered. 'Doesn't matter. What in god's name are you planning to do? I already saw the extra equipment you 'hid' in cargo bay 4.' You made air quotes with your fingers. Dustin and Katie looked at each other, and Dustin spoke. 'Guess we HAVE to tell you now, don't we?' Katie smiled her sly smile. She spoke: 'Alright, here's the deal. This job blows. Everyone on this ship agrees except you and " +
    "Valentine. That's why we waited to tell you but we knew you'd love our plan.' Josh smiled and gave you a playful nod. Katie continued, 'We're not doing this mission to bring back some ancient rhino piss. We're gonna live like gods in this period. We make a few monsters to scare some villagers, and we kill them with our guns and explosives to make the villagers think we're a bunch of gods. Then we make em feed us and clothe us for free, for the rest of time!' You got furious. 'You want to scare people with our technology so you can be " +
    "a bunch of freeloaders?! Screw off!' 'Think about it man,' Dustin threw out. 'No job in the world would let us live this good. We're not taking over the world or anything, it's just a small country. And we're not gonna kill anyone, just make them do what we say sometimes!' 'No, Dustin. No, all of you! This is slavery. Is everyone besides Valentine and I on board with this idiotic plan of yours?' Katie looked around. Still just the four of you. 'Yep, everyone except you and Valentine.' 'Well, this bullshit isn't happening. I'm stopping the ship.' " +
    "You ran through the tunnels and made for the cockpit. The three chased you. '" + EnterNames.thisPlayer.name + ", don't do this! You won't like what happens if you do!' shouted Katie. 'Listen to yourself! I have to save to the people you're going to enslave!' You were livid, filled with adrenaline. You thought these guys you were assigned were a bit shady, sure, but they had crossed the line and kept going for miles. You got to the door of the cockpit, but you figured whoever was in the cockpit found out what was happening, and wasn't Valentine. " +
    "Because the next thing you saw was a bat flying at your face. You almost passed out, but didn't. You were dragged and set in a chair, a device strapped to your head. The whole crew had gathered. You heard mumbles of 'memory' and 'forget this ever happened'. Everyone's face was either solemn or happy to see you go, except Valentine's. A tear dripped down her face. In your last burst of consciousness, you heard the metal clank of a lever to your right. Then it went black. Blackness... blackness... until you see yourself, in third person. In the first " +
    "dungeon. You fling up in Valentine's bed. The pain and numbness is almost gone. But you feel weak. Valentine is sitting in a chair with your favorite meal prepared. Milk and cookies. 'It's me, " + EnterNames.thisPlayer.name + ". Welcome back, cadet.'";

    private static final String[][] villagerConversation1 = {
            {"You see a lanky, well-dressed man. 'Greetings, traveler. Looking to move here?'"},
            {"'Oh, well what are you looking for? I've been around a bit, maybe I can help.'",
                    "'Ah, hope you enjoy your stay. Be sure to visit the shop, we've got the best " +
                            "prices around here!'"},
            {"'Hm, I'm afraid I can't help you if you don't know. Until we meet again.'",
                    "'I hope it wasn't important then. Until we meet again.'",}
    };

    private static final String[][] villagerResponseText1 = {
            {"No, I'm looking for something.", "No, just visiting."},
            {"I...I don't know.", "...I forgot.", "", ""},
            {"", "", "", ""}
    };

    private static final String[][] villagerConversation2 = {
            {"You come across a stout mother of 2 toddlers. 'Say, traveler, don’t go to Mount " +
                    "Olympus, y'hear?'"},
            {"‘If yur thinkin’ the gods ain’t real, they are and they’re vengeful. My son, he " +
                    "went up weeks ago and never came back.’", "‘It’s the home of the gods! They " +
                    "really are gods among men, and if you don’t believe in ‘em, you’ll pay the " +
                    "ultimate price.’"},
            {"‘My son, bless him, he got angry that the gods kept taking our crops and supplies " +
                    "as sacrifice.  He wanted to prove something was wrong, to prove this wasn’t " +
                    "the reality we had to live with. So he set off to find answers, but only " +
                    "found his demise...’ She lowers her head and stifles a sob.", "‘No, don’t!! " +
                    "The gods took him to set an example. He doubted their power and they showed " +
                    "us what happens when you doubt. If they find out I sent someone to save my " +
                    "boy, they would strike me down! Do NOT be the ‘hero’.’", "‘Yes! They make " +
                    "their rounds to every town in the kingdom every few months. They demand " +
                    "food each time, sometimes a statue in their likeness, and they leave with " +
                    "their wheelbarrows full. If someone doesn’t give up their day’s meal, " +
                    "they’re cast down with beams of light.’", "‘Shh, watch your mouth there! " +
                    "You may not like it, but this is what peace looks like. They created the " +
                    "land, and they created us. This is the only way balance can exist. ’"},
            {"‘No, don’t!! " +
                    "The gods took him to set an example. He doubted their power and they showed " +
                    "us what happens when you doubt. If they find out I sent someone to save my " +
                    "boy, they would strike me down! Do NOT be the ‘hero’.’", "‘Yes! They make " +
                    "their rounds to every town in the kingdom every few months. They demand " +
                    "food each time, sometimes a statue in their likeness, and they leave with " +
                    "their wheelbarrows full. If someone doesn’t give up their day’s meal, " +
                    "they’re cast down with beams of light.’", "‘Yes! They make " +
                    "their rounds to every town in the kingdom every few months. They demand " +
                    "food each time, sometimes a statue in their likeness, and they leave with " +
                    "their wheelbarrows full. If someone doesn’t give up their day’s meal, " +
                    "they’re cast down with beams of light.’", "Thank ye, traveller. Trust me, " +
                    "whatever's up there to see ain't worth dyin' and an eternity in the Dark " +
                    "Place.", "Thank ye, traveller. Trust me, whatever's up there to see ain't " +
                    "worth dyin' and an eternity in the Dark Place.", "‘Yes! They make " +
                    "their rounds to every town in the kingdom every few months. They demand " +
                    "food each time, sometimes a statue in their likeness, and they leave with " +
                    "their wheelbarrows full. If someone doesn’t give up their day’s meal, " +
                    "they’re cast down with beams of light.’", "It's nothin', traveller. I can't " +
                    "make ya do nothin', but please don't go till you're ready.", "‘Yes! They " +
                    "make their rounds to every town in the kingdom every few months. They demand" +
                    " food each time, sometimes a statue in their likeness, and they leave with " +
                    "their wheelbarrows full. If someone doesn't give up their day’s meal, " +
                    "they’re cast down with beams of light.’"},
            {"Thank ye, traveller. Trust me, " +
                    "whatever's up there to see ain't worth dyin' and an eternity in the Dark " +
                    "Place.", "It's nothin', traveller. I can't make ya do nothin', but please " +
                    "don't go till you're ready.", "Thank ye, traveller. Trust me, whatever's up " +
                    "there to see ain't worth dyin' and an eternity in the Dark Place.", "It's " +
                    "nothin', traveller. I can't make ya do nothin', but please don't go till " +
                    "you're ready.", "Thank ye, traveller. Trust me, whatever's up there to see " +
                    "ain't worth dyin' and an eternity in the Dark Place.", "It's nothin', " +
                    "traveller. I can't make ya do nothin', but please don't go till you're ready.",
                    "", "", "", "", "Thank ye, traveller. Trust me, whatever's up there to see " +
                    "ain't worth dyin' and an eternity in the Dark Place.", "It's nothin', " +
                    "traveller. I can't make ya do nothin', but please don't go till you're ready.",
                    "", "", "Thank ye, traveller. Trust me, whatever's up there to see ain't " +
                    "worth dyin' and an eternity in the Dark Place.", "It's nothin', traveller. " +
                    "I can't make ya do nothin', but please don't go till you're ready.",}
    };

    private static final String[][] villagerResponseText2 = {
            {"Why not?", "What's that?"},
            {"That's awful...why did he climb it?", "Maybe I can bring him back for you.", "Gods " +
                    "among men? Have they shown their faces around here?", "They don’t sound " +
                    "like my kind of gods."},
            {"Maybe I can climb the mountain and get him back? I’m a decent fighter.", "He " +
                    "sounds brave. Have you seen the gods themselves?", "Doubted their power? " +
                    "You mean you’ve seen them?", "Fine, I won’t.", "Thank you for telling me. " +
                    "I’ll be sure to be careful.", "That’s awful. Some gods they are.",
                    "If you say so. Thanks for the information.", "Alright, I won’t go up the " +
                    "mountain. Can you at least tell me what you know of these gods?"},
            {"Alright, I won't go.", "That's awful. Thank you for the word of warning.",
                    "Alright, I won't go.", "That's awful. Thank you for the word of warning.",
                    "Alright, I won't go.", "That's awful. Thank you for the word of warning.",
                    "", "", "", "", "Alright, I won't go.", "That's awful. Thank you for the " +
                    "word of warning.", "", "", "Alright, I won't go.", "That's awful. Thank you " +
                    "for the word of warning.",},
            {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "",}
    };

    final String farewell = "Goodbye.";

    TextView characterDialogue;
    TextView optionOneText;
    TextView optionTwoText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogue);
        characterDialogue = findViewById(R.id.characterDialogueBox);
        optionOneText = findViewById(R.id.dialogueOptionOneText);
        optionTwoText = findViewById(R.id.dialogueOptionTwoText);
        if (atHouse) {
            byte houseNum = 0;
            while (Exploration.visitedHouses[houseNum].length > 0) {
                houseNum++;
            }
            houseNum--;
            if (houseNum < 3) {
                EnterNames.thisPlayer.myItems[4 + houseNum]++;
            }
            switch (houseNum) {
                case 0:
                    currentConversation = houseConversation1;
                    currentResponses = houseResponseText1;
                    break;
                case 1:
                    currentConversation = houseConversation2;
                    currentResponses = houseResponseText2;
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        } else {
            switch (Exploration.thisTownIndex) {
                case 0:
                    currentConversation = villagerConversation1;
                    currentResponses = villagerResponseText1;
                    break;
                case 2:
                    currentConversation = villagerConversation2;
                    currentResponses = villagerResponseText2;
                    break;
            }
        }
        characterDialogue.setText(currentConversation[conversationProgress][responseIndex]);
        optionOneText.setText(currentResponses[conversationProgress][responseIndex]);
        optionTwoText.setText(currentResponses[conversationProgress][responseIndex + 1]);
        configureNextButton();
    }

    private void configureNextButton() {
        Button dialogueOptionOne = findViewById(R.id.dialogueOptionOneButton);
        Button dialogueOptionTwo = findViewById(R.id.dialogueOptionTwoButton);
        dialogueOptionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressConversation();
            }
        });
        dialogueOptionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responseIndex++;
                progressConversation();
            }
        });
    }

    private void progressConversation() {
        if (optionOneText.getText().equals(farewell)) {
            finish();
        } else {
            conversationProgress++;
            characterDialogue.setText(currentConversation[conversationProgress][responseIndex]);
            responseIndex *= 2;
            optionOneText.setText(currentResponses[conversationProgress][responseIndex]);
            optionTwoText.setText(currentResponses[conversationProgress][responseIndex + 1]);
            if (currentResponses[conversationProgress][responseIndex].equals("")) {
                optionOneText.setText(farewell);
                optionTwoText.setText(farewell);
            }
        }
    }

    @Override
    public void onBackPressed() {}
}
