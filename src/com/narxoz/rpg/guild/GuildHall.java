package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuildHall implements GuildMediator {
    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();
    private int notificationsSent = 0;

    @Override
    public void register(GuildMember member) {
        if (member instanceof Quartermaster) {
            addSubscriber("supplies", member);
            addSubscriber("rewards", member);
        } else if (member instanceof Scout) {
            addSubscriber("scouting", member);
            addSubscriber("orders", member);
        } else if (member instanceof Healer) {
            addSubscriber("healing", member);
            addSubscriber("orders", member);
        } else if (member instanceof Captain) {
            addSubscriber("orders", member);
            addSubscriber("urgent", member);
        }
        addSubscriber("general", member);
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        List<GuildMember> subscribers = subscribersFor(topic);
        int notified = 0;
        for (GuildMember member : subscribers) {
            if (member != from) {
                member.receive(topic, from, payload);
                notified++;
            }
        }
        notificationsSent += notified;
        System.out.println("[GuildHall] Dispatched '" + topic + "' from " + from.getName()
                + " to " + notified + " members.");
    }

    public int getNotificationsSent() {
        return notificationsSent;
    }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
}