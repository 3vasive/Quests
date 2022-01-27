package me.evasive.quests.configs;

import me.evasive.quests.Quests;
import me.evasive.quests.quests.Quest;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class ConfigSetup {
    public Quests plugin;

    public ConfigSetup(Quests plugin) {
        this.plugin = plugin;
    }

    public void QuestConfigSetup() {
        /*
        String name;
        String description;
        Material item;
        int amount;
        int reward;
        String type;
        EntityType mobtype = null;
        Material blocktype = null;
         */
        QuestsConfig.setup();
        QuestsConfig.get().addDefault("Quests.1.ID", 1);
        QuestsConfig.get().addDefault("Quests.1.Name", "Grave Digger");
        QuestsConfig.get().addDefault("Quests.1.Description", "Kill 100 Zombies");
        QuestsConfig.get().addDefault("Quests.1.Display_Item", Material.ROTTEN_FLESH.toString());
        QuestsConfig.get().addDefault("Quests.1.Amount", 100);
        QuestsConfig.get().addDefault("Quests.1.Reward", 2);
        QuestsConfig.get().addDefault("Quests.1.Type", "Slayer");
        QuestsConfig.get().addDefault("Quests.1.MobType", EntityType.ZOMBIE.toString());
        QuestsConfig.get().addDefault("Quests.1.BlockType", "null");
        QuestsConfig.get().options().copyDefaults(true);
        QuestsConfig.save();

    }
}
