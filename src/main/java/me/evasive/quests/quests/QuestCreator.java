package me.evasive.quests.quests;

import me.evasive.quests.configs.QuestsConfig;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class QuestCreator {
    public static Map<Integer, Quest> questMap = new HashMap<>();


    public static void createQuest(){
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
