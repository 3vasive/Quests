package me.evasive.quests.quests;

import me.evasive.quests.configs.QuestsConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

public class QuestCreator {
    public static Map<Integer, Quest> questMap = new HashMap<>();


    public static void createQuest(){
        /*
        String name;
        String description;
        String type;
        Mob mobtype = null;
        Material blocktype = null;
        Material item;
        int amount;
        int reward;
        */
        //Quests//
        /*
        quest1.name = "Excavator";
        quest1.description = "Mine 10 blocks";
        quest1.type = "Break";
        quest1.blocktype = Material.AIR;
        quest1.item = Material.EMERALD;
        quest1.amount = 10;
        quest1.reward = 2;

        quest2.name = "Miner";
        quest2.description = "Mine 15 diamond ore";
        quest2.type = "Break";
        quest2.blocktype = Material.DIAMOND_ORE;
        quest2.item = Material.DIAMOND_ORE;
        quest2.amount = 15;
        quest2.reward = 1;

        quest3.name = "Survivor";
        quest3.description = "Slay 9 zombies";
        quest3.type = "Slayer";
        quest3.mobtype = EntityType.ZOMBIE;
        quest3.item = Material.ROTTEN_FLESH;
        quest3.amount = 9;
        quest3.reward = 1;

        quest4.name = "420";
        quest4.description = "Kill 6 blazes";
        quest4.type = "Slayer";
        quest4.mobtype = EntityType.BLAZE;
        quest4.item = Material.BLAZE_ROD;
        quest4.amount = 6;
        quest4.reward = 1;

        quest5.name = "Winter Storm";
        quest5.description = "Mine 3 snow";
        quest5.type = "Break";
        quest5.blocktype = Material.SNOW;
        quest5.item = Material.SNOWBALL;
        quest5.amount = 3;
        quest5.reward = 3;

        quest6.name = "Assasian";
        quest6.description = "Kill 4 skeletons";
        quest6.type = "Slayer";
        quest6.mobtype = EntityType.SKELETON;
        quest6.item = Material.BONE;
        quest6.amount = 4;
        quest6.reward = 2;

        quest7.name = "Fisherman";
        quest7.description = "Fish up 2 Cod";
        quest7.type = "Fishing";
        quest7.blocktype = Material.COD;
        quest7.item = Material.COD;
        quest7.amount = 2;
        quest7.reward = 5;

        quest8.name = "Noob";
        quest8.description = "Mine 1 Dirt";
        quest8.type = "Break";
        quest8.blocktype = Material.DIRT;
        quest8.item = Material.DIRT;
        quest8.amount = 1;
        quest8.reward = 1;

        quest9.name = "Slaughter";
        quest9.description = "Slay 5 Cows";
        quest9.type = "Slayer";
        quest9.mobtype = EntityType.COW;
        quest9.item = Material.COOKED_BEEF;
        quest9.amount = 5;
        quest9.reward = 1;

        quest10.name = "Journyman";
        quest10.description = "Fish up 1 items";
        quest10.type = "Fishing";
        quest10.blocktype = Material.AIR;
        quest10.item = Material.FISHING_ROD;
        quest10.amount = 1;
        quest10.reward = 1;
        */

        QuestsConfig.get().getConfigurationSection("Quests").getKeys(false).forEach(iteminfo ->{
            Quest quest = new Quest();
            quest.id = QuestsConfig.get().getInt("Quests." + iteminfo + ".ID");
            quest.name = QuestsConfig.get().getString("Quests." + iteminfo + ".Name");
            quest.description = QuestsConfig.get().getString("Quests." + iteminfo + ".Description");
            quest.item = Material.valueOf(QuestsConfig.get().getString("Quests." + iteminfo + ".Display_Item"));
            quest.amount = QuestsConfig.get().getInt("Quests." + iteminfo + ".Amount");
            quest.reward = QuestsConfig.get().getInt("Quests." + iteminfo + ".Reward");
            quest.type = QuestsConfig.get().getString("Quests." + iteminfo + ".Type");
            if (!QuestsConfig.get().getString("Quests." + iteminfo + ".MobType").equals("null")){
                quest.mobtype = EntityType.valueOf(QuestsConfig.get().getString("Quests." + iteminfo + ".MobType"));
            }else{
                quest.mobtype = null;
            }
            if (!QuestsConfig.get().getString("Quests." + iteminfo + ".BlockType").equals("null")){
                quest.blocktype = Material.valueOf(QuestsConfig.get().getString("Quests." + iteminfo + ".BlockType"));
            }else{
                quest.blocktype = null;
            }
            questMap.put(Integer.parseInt(iteminfo), quest);
        });
    }
}
