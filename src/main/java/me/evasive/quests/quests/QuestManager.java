package me.evasive.quests.quests;

import me.evasive.quests.configs.PlayerQuestsProgress;
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
        while (pQuestData.getQL().size() != amount){
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
        PlayerQuestsProgress.setup();
        for (Map.Entry<UUID, PQuestData> players : pquestMap.entrySet()) {
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Date", players.getValue().getDate().toString());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress1", players.getValue().getP1());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete1", players.getValue().getPC1());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress2", players.getValue().getP2());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete2", players.getValue().getPC2());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress3", players.getValue().getP3());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete3", players.getValue().getPC3());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress4", players.getValue().getP4());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete4", players.getValue().getPC4());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress5", players.getValue().getP5());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete5", players.getValue().getPC5());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress6", players.getValue().getP6());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete6", players.getValue().getPC6());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress7", players.getValue().getP7());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete7", players.getValue().getPC7());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress8", players.getValue().getP8());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete8", players.getValue().getPC8());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress9", players.getValue().getP9());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete9", players.getValue().getPC9());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress10", players.getValue().getP10());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete10", players.getValue().getPC10());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress11", players.getValue().getP11());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete11", players.getValue().getPC11());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress12", players.getValue().getP12());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete12", players.getValue().getPC12());
            List<Integer> questlist = new ArrayList<>();
            for (int i = 0; i < players.getValue().getQL().size(); i++){
                questlist.add(players.getValue().getQL().get(i).getId());
            }
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".QuestList", questlist);
        }
        PlayerQuestsProgress.save();
    }

    public void loadWorldData() {
        FileConfiguration load = PlayerQuestsProgress.get();
        if (load != null) {
            if (load.getConfigurationSection("Players") != null) {
                Objects.requireNonNull(load.getConfigurationSection("Players")).getKeys(false).forEach(key -> {
                    LocalDate date = LocalDate.parse(Objects.requireNonNull(PlayerQuestsProgress.get().getString("Players." + key + ".Date")));
                    PQuestData pQuestData = new PQuestData();
                    pQuestData.setDate(date);
                    pQuestData.setP1(PlayerQuestsProgress.get().getInt("Players." + key + ".Progress1"));
                    pQuestData.setPC1(PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete1"));
                    pQuestData.setP2(PlayerQuestsProgress.get().getInt("Players." + key + ".Progress2"));
                    pQuestData.setPC2(PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete2"));
                    pQuestData.setP3(PlayerQuestsProgress.get().getInt("Players." + key + ".Progress3"));
                    pQuestData.setPC3(PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete3"));
                    pQuestData.setP4(PlayerQuestsProgress.get().getInt("Players." + key + ".Progress4"));
                    pQuestData.setPC4(PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete4"));
                    pQuestData.setP5(PlayerQuestsProgress.get().getInt("Players." + key + ".Progress5"));
                    pQuestData.setPC5(PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete5"));
                    pQuestData.setP6(PlayerQuestsProgress.get().getInt("Players." + key + ".Progress6"));
                    pQuestData.setPC6(PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete6"));
                    pQuestData.setP7(PlayerQuestsProgress.get().getInt("Players." + key + ".Progress7"));
                    pQuestData.setPC7(PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete7"));
                    pQuestData.setP8(PlayerQuestsProgress.get().getInt("Players." + key + ".Progress8"));
                    pQuestData.setPC8(PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete8"));
                    pQuestData.setP9(PlayerQuestsProgress.get().getInt("Players." + key + ".Progress9"));
                    pQuestData.setPC9(PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete9"));
                    pQuestData.setP10(PlayerQuestsProgress.get().getInt("Players." + key + ".Progress10"));
                    pQuestData.setPC10(PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete10"));
                    pQuestData.setP11(PlayerQuestsProgress.get().getInt("Players." + key + ".Progress11"));
                    pQuestData.setPC11(PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete11"));
                    pQuestData.setP12(PlayerQuestsProgress.get().getInt("Players." + key + ".Progress12"));
                    pQuestData.setPC12(PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete12"));
                    List<Integer> questlist = PlayerQuestsProgress.get().getIntegerList("Players." + key + ".QuestList");
                    pQuestData.setQL(getQuests(questlist));

                    pquestMap.put(UUID.fromString(key), pQuestData);
                });
                PlayerQuestsProgress.save();
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
