package me.evasive.quests.configs;

import me.evasive.quests.Quests;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayerQuestsProgress {

    public Quests plugin;

    public PlayerQuestsProgress(Quests plugin){
        this.plugin = plugin;
    }

    private static File file;
    private static FileConfiguration questConfig;

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("Quests").getDataFolder(), "questprogress.yml");

        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){

            }

        }
        questConfig = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return questConfig;
    }

    public static void save(){
        try{
            questConfig.save(file);
        }catch (IOException e){

        }

    }

    public static void reload(){
        questConfig = YamlConfiguration.loadConfiguration(file);
    }

}
