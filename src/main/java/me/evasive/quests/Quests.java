package me.evasive.quests;

import me.evasive.quests.commands.AdminCommands;
import me.evasive.quests.commands.PlayerCommands;
import me.evasive.quests.configs.*;
import me.evasive.quests.quests.QuestCreator;
import me.evasive.quests.quests.QuestEvents;
import me.evasive.quests.quests.QuestGUI;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Quests extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        QuestsConfig.setup();

        QuestCreator.createQuest();

        //PlayersQuestPoints.setup();
        //PlayerQuestsProgress.setup();
        QuestShopConfig.setup();
        PlayerDataConfig.setup();

        QuestGUI.questManager.loadWorldData();
        QuestGUI.questPointManager.loadWorldData();
        QuestGUI.questManager.ServerStart(this);

        new AdminCommands(this);
        new PlayerCommands(this);

        getServer().getPluginManager().registerEvents(new QuestEvents(this), this);

        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Quest Started");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        QuestGUI.questManager.saveWorldData();
        QuestGUI.questPointManager.saveWorldData();
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Quest Stopped");
    }
}
