package me.evasive.quests.quests;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class Quest {
    int id;
    String name;
    String description;
    Material item;
    int amount;
    int reward;
    String type;
    EntityType mobtype = null;
    Material blocktype = null;
}
