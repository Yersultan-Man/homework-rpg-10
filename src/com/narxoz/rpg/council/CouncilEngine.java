package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.*;

import java.util.List;

public class CouncilEngine {
    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        int questsTraversed = 0;
        int messagesRouted = 0;

        System.out.println("\n=== Starting War Council Planning ===");

        QuestIterator ordered = questLog.ordered();
        while (ordered.hasNext()) {
            Quest q = ordered.next();
            System.out.println("  Planning: " + q);
            questsTraversed++;
            messagesRouted++;
        }

        QuestIterator priority = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (priority.hasNext()) {
            Quest q = priority.next();
            System.out.println("  Urgent Planning: " + q);
            questsTraversed++;
            messagesRouted++;
        }

        System.out.println("\n=== Council Session Complete ===\n");

        int membersNotified = ((GuildHall) hall).getNotificationsSent();
        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}