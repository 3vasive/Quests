package me.evasive.quests.questpoints;

import me.evasive.quests.configs.PlayersQuestPoints;
import me.evasive.quests.quests.PQuestData;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class QuestPointManager {

    private final Map<UUID, Integer> points;

    public QuestPointManager(){
        this.points = new HashMap<>();
    }

    public void onJoin(UUID uuid){
        if (!points.containsKey(uuid)){
            points.put(uuid, 0);
        }
    }

    public void addPoints(UUID uuid, Integer amount){
        points.put(uuid, points.get(uuid) + amount);
    }

    public void removePoints(UUID uuid, Integer amount){
        points.put(uuid, points.get(uuid) - amount);
    }

    public Integer getPoints(UUID uuid){
        return points.get(uuid);
    }

    public void setPoints(UUID uuid, Integer amount){
        points.put(uuid, amount);
    }

    public void saveWorldData() {
        PlayersQuestPoints.setup();
        for (Map.Entry<UUID, Integer> players : points.entrySet()) {
            PlayersQuestPoints.get().set("Players." + players.getKey() + ".Points", players.getValue());
        }
        PlayersQuestPoints.save();
    }

    public void loadWorldData() {
        FileConfiguration load = PlayersQuestPoints.get();
        if (load != null) {
            if (load.getConfigurationSection("Players") != null) {
                Objects.requireNonNull(load.getConfigurationSection("Players")).getKeys(false).forEach(key -> {
                    Integer point = PlayersQuestPoints.get().getInt("Players." + key + ".Points");
                    points.put(UUID.fromString(key), point);
                });
                PlayersQuestPoints.save();
            }
        }
    }

}
