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
            quest.setId(QuestsConfig.get().getInt("Quests." + iteminfo + ".ID"));
            quest.setName(QuestsConfig.get().getString("Quests." + iteminfo + ".Name"));
            quest.setDescription(QuestsConfig.get().getString("Quests." + iteminfo + ".Description"));
            quest.setItem(Material.valueOf(QuestsConfig.get().getString("Quests." + iteminfo + ".Display_Item")));
            quest.setAmount(QuestsConfig.get().getInt("Quests." + iteminfo + ".Amount"));
            quest.setReward(QuestsConfig.get().getInt("Quests." + iteminfo + ".Reward"));
            quest.setType(QuestsConfig.get().getString("Quests." + iteminfo + ".Type"));
            if (!QuestsConfig.get().getString("Quests." + iteminfo + ".MobType").equals("null"))
                quest.setMobtype(EntityType.valueOf(QuestsConfig.get().getString("Quests." + iteminfo + ".MobType")));
            else
                quest.setMobtype(null);
            if (!QuestsConfig.get().getString("Quests." + iteminfo + ".BlockType").equals("null"))
                quest.setBlocktype(Material.valueOf(QuestsConfig.get().getString("Quests." + iteminfo + ".BlockType")));
            else
                quest.setBlocktype(null);
            questMap.put(Integer.parseInt(iteminfo), quest);
        });
    }
}
