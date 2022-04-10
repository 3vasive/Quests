package me.evasive.quests.quests;

import me.evasive.quests.configs.PlayerDataConfig;
//import me.evasive.quests.configs.PlayerQuestsProgress;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.time.LocalDate;
import java.util.*;

public class QuestManager {
    private final Map<UUID, PQuestData> pquestMap;

    public QuestManager(){
        this.pquestMap = new HashMap<>();
    }

    public int checkPerms(UUID uuid){
        int amount = 5;
        if(Bukkit.getPlayer(uuid) == null) return 5;
        Player player = Bukkit.getPlayer(uuid);
        if (player.hasPermission("Quests.extra_1")) amount = 6;
        if (player.hasPermission("Quests.extra_2")) amount = 7;
        if (player.hasPermission("Quests.extra_3")) amount = 8;
        if (player.hasPermission("Quests.extra_4")) amount = 9;
        if (player.hasPermission("Quests.extra_5")) amount = 10;
        if (player.hasPermission("Quests.extra_6")) amount = 11;
        if (player.hasPermission("Quests.extra_7")) amount = 12;
        return amount;
    }


    public void getNewQuests(UUID uuid){
        ArrayList<Integer> noDupe = new ArrayList<>();
        PQuestData pQuestData = new PQuestData();
        int amount = checkPerms(uuid);
        while (pQuestData.getQL().size() < amount && noDupe.size() < QuestCreator.questMap.size()){
            Random rand = new Random();
            int randomNum = rand.nextInt((QuestCreator.questMap.size() - 1) + 1) + 1;
            if (!noDupe.contains(randomNum)) {
                noDupe.add(randomNum);
                pQuestData.getQL().add(QuestCreator.questMap.get(randomNum));
            }
            pQuestData.setDate(LocalDate.now());
        }
        pquestMap.put(uuid, pQuestData);
    }

    public ArrayList<Quest> getQuests(List<Integer> qids){
        ArrayList<Quest> oldQuests = new ArrayList<>();
        for (Integer qid : qids) {
            oldQuests.add(QuestCreator.questMap.get(qid));
        }
        return oldQuests;
    }

    public ArrayList<Quest> getQuestList(UUID uuid){
        return pquestMap.get(uuid).getQL();
    }

    public void QuestProggression(UUID uuid, Integer qnum){
        PQuestData pQuestData = pquestMap.get(uuid);
        pQuestData.addProgress(qnum, pQuestData);
        pquestMap.put(uuid, pQuestData);
    }

    public int GetQuestProgression(UUID uuid, Integer qnum){
        PQuestData pQuestData = pquestMap.get(uuid);
        return pQuestData.getQuestProgress(qnum, pQuestData);
    }

    public void checkCompletion(Player player, UUID uuid, Integer qnum){
        PQuestData pQuestData = pquestMap.get(uuid);
        int questProgress = pQuestData.getQuestProgress(qnum, pQuestData);
        int questGoal = pQuestData.getQL().get(qnum - 1).getAmount();
        if (questProgress == questGoal && !pQuestData.checkQuestComplete(qnum, pQuestData)) {
                player.sendMessage("Quest " + qnum + " complete");
                QuestGUI.questPointManager.addPoints(uuid, pQuestData.getQL().get(qnum - 1).getReward());
                pQuestData.setQuestComplete(qnum, pQuestData);
        }
        pquestMap.put(uuid, pQuestData);
    }

    public boolean complete(UUID uuid, Integer qnum){
        PQuestData pQuestData = pquestMap.get(uuid);
        return pQuestData.checkQuestComplete(qnum, pQuestData);
    }

    public void onJoin(UUID uuid){
        if (!pquestMap.containsKey(uuid)){
            getNewQuests(uuid);
        }
        if (!pquestMap.get(uuid).getDate().equals(LocalDate.now())){
            getNewQuests(uuid);
        }
    }

    public void saveWorldData() {
        PlayerDataConfig.setup();
        for (Map.Entry<UUID, PQuestData> players : pquestMap.entrySet()) {
                PlayerDataConfig.get().set("Players." + players.getKey() + ".Date", players.getValue().getDate().toString());
                PlayerDataConfig.get().set("Players." + players.getKey() + ".Progress", players.getValue().getProgress());
                PlayerDataConfig.get().set("Players." + players.getKey() + ".Completed", players.getValue().getCompleted());
            List<Integer> questlist = new ArrayList<>();
            for (int i = 0; i < players.getValue().getQL().size(); i++){
                questlist.add(players.getValue().getQL().get(i).getId());
            }
            PlayerDataConfig.get().set("Players." + players.getKey() + ".QuestList", questlist);
        }
        PlayerDataConfig.save();
    }

    public void loadWorldData() {
        FileConfiguration load = PlayerDataConfig.get();
        if (load != null) {
            if (load.getConfigurationSection("Players") != null) {
                Objects.requireNonNull(load.getConfigurationSection("Players")).getKeys(false).forEach(key -> {
                    LocalDate date = LocalDate.parse(Objects.requireNonNull(PlayerDataConfig.get().getString("Players." + key + ".Date")));
                    PQuestData pQuestData = new PQuestData();
                    pQuestData.setDate(date);
                    ArrayList<Integer> Progress = (ArrayList<Integer>) PlayerDataConfig.get().getList("Players." + key + ".Progress");
                    ArrayList<Boolean> Completed = (ArrayList<Boolean>) PlayerDataConfig.get().getList("Players." + key + ".Completed");
                    pQuestData.setProgress(Progress);
                    pQuestData.setCompleted(Completed);
                    List<Integer> questlist = PlayerDataConfig.get().getIntegerList("Players." + key + ".QuestList");
                    pQuestData.setQL(getQuests(questlist));

                    pquestMap.put(UUID.fromString(key), pQuestData);
                });
                PlayerDataConfig.save();
            }
        }
    }

    public void scheduleRepeatAtTime(Plugin plugin, Runnable task, int hour){
        Calendar cal = Calendar.getInstance();
        long now = cal.getTimeInMillis();
        if(cal.get(Calendar.HOUR_OF_DAY) >= hour)
            cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long offset = cal.getTimeInMillis() - now;
        long ticks = offset / 50L;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, ticks, 1728000L);
    }

    public void ServerStart(Plugin plugin){
        scheduleRepeatAtTime(plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p != null) {
                    p.sendMessage(ChatColor.AQUA + "Quests have now been reset!");
                    QuestGUI.questManager.getNewQuests(p.getUniqueId());
                }
            }
        }, 0);
    }

}
