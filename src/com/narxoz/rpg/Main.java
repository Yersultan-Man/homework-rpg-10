package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===\n");

        List<Hero> party = new ArrayList<>();
        party.add(new Hero("Aragorn", 120, 25, 15));
        party.add(new Hero("Gandalf", 80, 40, 10));

        System.out.println("Party:");
        party.forEach(System.out::println);

        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Goblin Den", QuestPriority.NORMAL, 150, false));
        questLog.add(new Quest("Dragon Lair", QuestPriority.URGENT, 800, true));
        questLog.add(new Quest("Lost Ruins", QuestPriority.HIGH, 400, false));
        questLog.add(new Quest("Bandit Camp", QuestPriority.LOW, 80, false));
        questLog.add(new Quest("Cursed Forest", QuestPriority.HIGH, 350, true));

        System.out.println("\nQuestLog size: " + questLog.size());

        GuildHall hall = new GuildHall();
        new Quartermaster("Thorin", hall);
        new Scout("Legolas", hall);
        new Healer("Elrond", hall);
        new Captain("Boromir", hall);

        System.out.println("\nGuild Officers registered.");

        new Captain("Boromir", hall).issueOrder("orders", "Prepare for Dragon Lair");
        new Scout("Legolas", hall).reportRoute("scouting", "Safe path found");

        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(party, questLog, hall);

        System.out.println(result);
        System.out.println("\n=== Demo Complete ===");
    }
}