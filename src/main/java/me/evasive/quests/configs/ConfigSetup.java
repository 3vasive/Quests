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
        //
        QuestsConfig.get().addDefault("Quests.2.ID", 2);
        QuestsConfig.get().addDefault("Quests.2.Name", "Excavator");
        QuestsConfig.get().addDefault("Quests.2.Description", "Mine 10 blocks");
        QuestsConfig.get().addDefault("Quests.2.Display_Item", Material.EMERALD.toString());
        QuestsConfig.get().addDefault("Quests.2.Amount", 10);
        QuestsConfig.get().addDefault("Quests.2.Reward", 1);
        QuestsConfig.get().addDefault("Quests.2.Type", "Break");
        QuestsConfig.get().addDefault("Quests.2.MobType", "null");
        QuestsConfig.get().addDefault("Quests.2.BlockType", Material.AIR.toString());
        //
        QuestsConfig.get().addDefault("Quests.3.ID", 3);
        QuestsConfig.get().addDefault("Quests.3.Name", "Miner");
        QuestsConfig.get().addDefault("Quests.3.Description", "Mine 15 diamond ore");
        QuestsConfig.get().addDefault("Quests.3.Display_Item", Material.DIAMOND_ORE.toString());
        QuestsConfig.get().addDefault("Quests.3.Amount", 15);
        QuestsConfig.get().addDefault("Quests.3.Reward", 1);
        QuestsConfig.get().addDefault("Quests.3.Type", "Break");
        QuestsConfig.get().addDefault("Quests.3.MobType", "null");
        QuestsConfig.get().addDefault("Quests.3.BlockType", Material.DIAMOND_ORE.toString());
        //
        QuestsConfig.get().addDefault("Quests.4.ID", 4);
        QuestsConfig.get().addDefault("Quests.4.Name", "420");
        QuestsConfig.get().addDefault("Quests.4.Description", "Kill 6 blazes");
        QuestsConfig.get().addDefault("Quests.4.Display_Item", Material.BLAZE_ROD.toString());
        QuestsConfig.get().addDefault("Quests.4.Amount", 6);
        QuestsConfig.get().addDefault("Quests.4.Reward", 1);
        QuestsConfig.get().addDefault("Quests.4.Type", "Slayer");
        QuestsConfig.get().addDefault("Quests.4.MobType", EntityType.BLAZE.toString());
        QuestsConfig.get().addDefault("Quests.4.BlockType", "null");
        //
        QuestsConfig.get().addDefault("Quests.5.ID", 5);
        QuestsConfig.get().addDefault("Quests.5.Name", "Winter Storm");
        QuestsConfig.get().addDefault("Quests.5.Description", "Mine 3 snow");
        QuestsConfig.get().addDefault("Quests.5.Display_Item", Material.SNOWBALL.toString());
        QuestsConfig.get().addDefault("Quests.5.Amount", 3);
        QuestsConfig.get().addDefault("Quests.5.Reward", 1);
        QuestsConfig.get().addDefault("Quests.5.Type", "Break");
        QuestsConfig.get().addDefault("Quests.5.MobType", "null");
        QuestsConfig.get().addDefault("Quests.5.BlockType", Material.SNOW.toString());
        //
        QuestsConfig.get().addDefault("Quests.6.ID", 6);
        QuestsConfig.get().addDefault("Quests.6.Name", "Fisherman");
        QuestsConfig.get().addDefault("Quests.6.Description", "Fish up 2 Cod");
        QuestsConfig.get().addDefault("Quests.6.Display_Item", Material.COD.toString());
        QuestsConfig.get().addDefault("Quests.6.Amount", 2);
        QuestsConfig.get().addDefault("Quests.6.Reward", 1);
        QuestsConfig.get().addDefault("Quests.6.Type", "Fishing");
        QuestsConfig.get().addDefault("Quests.6.MobType", "null");
        QuestsConfig.get().addDefault("Quests.6.BlockType", Material.COD.toString());
        //
        QuestsConfig.get().addDefault("Quests.7.ID", 7);
        QuestsConfig.get().addDefault("Quests.7.Name", "Assasian");
        QuestsConfig.get().addDefault("Quests.7.Description", "Kill 4 Skeletons");
        QuestsConfig.get().addDefault("Quests.7.Display_Item", Material.BONE.toString());
        QuestsConfig.get().addDefault("Quests.7.Amount", 4);
        QuestsConfig.get().addDefault("Quests.7.Reward", 2);
        QuestsConfig.get().addDefault("Quests.7.Type", "Slayer");
        QuestsConfig.get().addDefault("Quests.7.MobType", EntityType.SKELETON.toString());
        QuestsConfig.get().addDefault("Quests.7.BlockType", "null");
        //
        QuestsConfig.get().addDefault("Quests.8.ID", 8);
        QuestsConfig.get().addDefault("Quests.8.Name", "Noob");
        QuestsConfig.get().addDefault("Quests.8.Description", "Mine 1 Dirt");
        QuestsConfig.get().addDefault("Quests.8.Display_Item", Material.DIRT.toString());
        QuestsConfig.get().addDefault("Quests.8.Amount", 1);
        QuestsConfig.get().addDefault("Quests.8.Reward", 1);
        QuestsConfig.get().addDefault("Quests.8.Type", "Break");
        QuestsConfig.get().addDefault("Quests.8.MobType", "null");
        QuestsConfig.get().addDefault("Quests.8.BlockType", Material.DIRT.toString());
        //
        QuestsConfig.get().addDefault("Quests.9.ID", 9);
        QuestsConfig.get().addDefault("Quests.9.Name", "Slaughter");
        QuestsConfig.get().addDefault("Quests.9.Description", "Slay 5 Cows");
        QuestsConfig.get().addDefault("Quests.9.Display_Item", Material.COOKED_BEEF.toString());
        QuestsConfig.get().addDefault("Quests.9.Amount", 5);
        QuestsConfig.get().addDefault("Quests.9.Reward", 1);
        QuestsConfig.get().addDefault("Quests.9.Type", "Slayer");
        QuestsConfig.get().addDefault("Quests.9.MobType", EntityType.COW.toString());
        QuestsConfig.get().addDefault("Quests.9.BlockType", "null");
        //
        QuestsConfig.get().addDefault("Quests.10.ID", 10);
        QuestsConfig.get().addDefault("Quests.10.Name", "Journyman");
        QuestsConfig.get().addDefault("Quests.10.Description", "Fish up 1 items");
        QuestsConfig.get().addDefault("Quests.10.Display_Item", Material.FISHING_ROD.toString());
        QuestsConfig.get().addDefault("Quests.10.Amount", 1);
        QuestsConfig.get().addDefault("Quests.10.Reward", 1);
        QuestsConfig.get().addDefault("Quests.10.Type", "Fishing");
        QuestsConfig.get().addDefault("Quests.10.MobType", "null");
        QuestsConfig.get().addDefault("Quests.10.BlockType", Material.AIR.toString());
        //
        QuestsConfig.get().addDefault("Quests.11.ID", 11);
        QuestsConfig.get().addDefault("Quests.11.Name", "Soul Searcher");
        QuestsConfig.get().addDefault("Quests.11.Description", "Mine 10 soulsand");
        QuestsConfig.get().addDefault("Quests.11.Display_Item", Material.SOUL_SAND.toString());
        QuestsConfig.get().addDefault("Quests.11.Amount", 10);
        QuestsConfig.get().addDefault("Quests.11.Reward", 1);
        QuestsConfig.get().addDefault("Quests.11.Type", "Break");
        QuestsConfig.get().addDefault("Quests.11.MobType", "null");
        QuestsConfig.get().addDefault("Quests.11.BlockType", Material.SOUL_SAND.toString());
        //
        QuestsConfig.get().addDefault("Quests.12.ID", 12);
        QuestsConfig.get().addDefault("Quests.12.Name", "Master");
        QuestsConfig.get().addDefault("Quests.12.Description", "Fish up 5 items");
        QuestsConfig.get().addDefault("Quests.12.Display_Item", Material.PUFFERFISH.toString());
        QuestsConfig.get().addDefault("Quests.12.Amount", 5);
        QuestsConfig.get().addDefault("Quests.12.Reward", 2);
        QuestsConfig.get().addDefault("Quests.12.Type", "Fishing");
        QuestsConfig.get().addDefault("Quests.12.MobType", "null");
        QuestsConfig.get().addDefault("Quests.12.BlockType", Material.AIR.toString());
        //
        QuestsConfig.get().addDefault("Quests.13.ID", 13);
        QuestsConfig.get().addDefault("Quests.13.Name", "BACON!");
        QuestsConfig.get().addDefault("Quests.13.Description", "Kill 10 pigs");
        QuestsConfig.get().addDefault("Quests.13.Display_Item", Material.COOKED_PORKCHOP.toString());
        QuestsConfig.get().addDefault("Quests.13.Amount", 10);
        QuestsConfig.get().addDefault("Quests.13.Reward", 1);
        QuestsConfig.get().addDefault("Quests.13.Type", "Slayer");
        QuestsConfig.get().addDefault("Quests.13.MobType", EntityType.PIG.toString());
        QuestsConfig.get().addDefault("Quests.13.BlockType", "null");
        //
        QuestsConfig.get().addDefault("Quests.14.ID", 14);
        QuestsConfig.get().addDefault("Quests.14.Name", "Salmon");
        QuestsConfig.get().addDefault("Quests.14.Description", "Fish up 3 salmon");
        QuestsConfig.get().addDefault("Quests.14.Display_Item", Material.SALMON.toString());
        QuestsConfig.get().addDefault("Quests.14.Amount", 3);
        QuestsConfig.get().addDefault("Quests.14.Reward", 1);
        QuestsConfig.get().addDefault("Quests.14.Type", "Fishing");
        QuestsConfig.get().addDefault("Quests.14.MobType", "null");
        QuestsConfig.get().addDefault("Quests.14.BlockType", Material.SALMON.toString());
        //
        QuestsConfig.get().addDefault("Quests.15.ID", 15);
        QuestsConfig.get().addDefault("Quests.15.Name", "Runner");
        QuestsConfig.get().addDefault("Quests.15.Description", "Run 100 blocks");
        QuestsConfig.get().addDefault("Quests.15.Display_Item", Material.SUGAR.toString());
        QuestsConfig.get().addDefault("Quests.15.Amount", 100);
        QuestsConfig.get().addDefault("Quests.15.Reward", 1);
        QuestsConfig.get().addDefault("Quests.15.Type", "Move");
        QuestsConfig.get().addDefault("Quests.15.MobType", "null");
        QuestsConfig.get().addDefault("Quests.15.BlockType", "null");
        //
        QuestsConfig.get().addDefault("Quests.16.ID", 16);
        QuestsConfig.get().addDefault("Quests.16.Name", "Planter");
        QuestsConfig.get().addDefault("Quests.16.Description", "Place 11 grass blocks");
        QuestsConfig.get().addDefault("Quests.16.Display_Item", Material.GRASS_BLOCK.toString());
        QuestsConfig.get().addDefault("Quests.16.Amount", 11);
        QuestsConfig.get().addDefault("Quests.16.Reward", 1);
        QuestsConfig.get().addDefault("Quests.16.Type", "Place");
        QuestsConfig.get().addDefault("Quests.16.MobType", "null");
        QuestsConfig.get().addDefault("Quests.16.BlockType", Material.GRASS_BLOCK.toString());
        //
        QuestsConfig.get().addDefault("Quests.17.ID", 17);
        QuestsConfig.get().addDefault("Quests.17.Name", "Farmer");
        QuestsConfig.get().addDefault("Quests.17.Description", "Harvest 15 wheat");
        QuestsConfig.get().addDefault("Quests.17.Display_Item", Material.WHEAT.toString());
        QuestsConfig.get().addDefault("Quests.17.Amount", 15);
        QuestsConfig.get().addDefault("Quests.17.Reward", 1);
        QuestsConfig.get().addDefault("Quests.17.Type", "Farm");
        QuestsConfig.get().addDefault("Quests.17.MobType", "null");
        QuestsConfig.get().addDefault("Quests.17.BlockType", Material.WHEAT.toString());
        QuestsConfig.get().options().copyDefaults(true);
        QuestsConfig.save();

    }
}
