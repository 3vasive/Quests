package me.evasive.quests.quests;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

@Getter @Setter
public class Quest {
    private int id;
    private String name;
    private String description;
    private Material item;
    private int amount;
    private int reward;
    private String type;
    private EntityType mobtype = null;
    private Material blocktype = null;
}
