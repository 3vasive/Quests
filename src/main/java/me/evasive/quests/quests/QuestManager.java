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
        if (Objects.requireNonNull(Bukkit.getPlayer(uuid)).hasPermission("Quests.extra_1")){
            amount = 6;
        }
        if (Objects.requireNonNull(Bukkit.getPlayer(uuid)).hasPermission("Quests.extra_2")){
            amount = 7;
        }
        if (Objects.requireNonNull(Bukkit.getPlayer(uuid)).hasPermission("Quests.extra_3")){
            amount = 8;
        }
        if (Objects.requireNonNull(Bukkit.getPlayer(uuid)).hasPermission("Quests.extra_4")){
            amount = 9;
        }
        if (Objects.requireNonNull(Bukkit.getPlayer(uuid)).hasPermission("Quests.extra_5")){
            amount = 10;
        }
        if (Objects.requireNonNull(Bukkit.getPlayer(uuid)).hasPermission("Quests.extra_6")){
            amount = 11;
        }
        if (Objects.requireNonNull(Bukkit.getPlayer(uuid)).hasPermission("Quests.extra_7")){
            amount = 12;
        }
        return amount;
    }


    public void getNewQuests(UUID uuid){
        ArrayList noDupe = new ArrayList();
        PQuestData pQuestData = new PQuestData();
        int amount = checkPerms(uuid);
        while (pQuestData.QL.size() != amount){
            Random rand = new Random();
            int randomNum = rand.nextInt((QuestCreator.questMap.size() - 1) + 1) + 1;
            if (!noDupe.contains(randomNum)) {
                noDupe.add(randomNum);
                pQuestData.QL.add(QuestCreator.questMap.get(randomNum));
            }
            pQuestData.date = java.time.LocalDate.now();
        }
        pquestMap.put(uuid, pQuestData);
    }

    public ArrayList<Quest> getQuests(List<Integer> qids){
        ArrayList<Quest> old_quests = new ArrayList<>();
        for (Integer qid : qids) {
            old_quests.add(QuestCreator.questMap.get(qid));
        }
        return old_quests;
    }

    public ArrayList<Quest> getQuestList(UUID uuid){
        return pquestMap.get(uuid).QL;
    }

    public void QuestProggression(UUID uuid, Integer qnum){
        PQuestData pQuestData = pquestMap.get(uuid);
        if (qnum.equals(1)){
            pQuestData.P1 += 1;
        } else if (qnum.equals(2)){
            pQuestData.P2 += 1;
        } else if (qnum.equals(3)){
            pQuestData.P3 += 1;
        } else if (qnum.equals(4)){
            pQuestData.P4 += 1;
        } else if (qnum.equals(5)){
            pQuestData.P5 += 1;
        } else if (qnum.equals(6)){
            pQuestData.P6 += 1;
        } else if (qnum.equals(7)){
            pQuestData.P7 += 1;
        } else if (qnum.equals(8)){
            pQuestData.P8 += 1;
        } else if (qnum.equals(9)){
            pQuestData.P9 += 1;
        } else if (qnum.equals(10)){
            pQuestData.P10 += 1;
        } else if (qnum.equals(11)){
            pQuestData.P11 += 1;
        } else if (qnum.equals(12)){
            pQuestData.P12 += 1;
        }
        pquestMap.put(uuid, pQuestData);
    }

    public int GetQuestProgression(UUID uuid, Integer qnum){
        PQuestData pQuestData = pquestMap.get(uuid);
        if (qnum.equals(1)){
            return pQuestData.P1;
        } else if (qnum.equals(2)){
            return pQuestData.P2;
        } else if (qnum.equals(3)){
            return pQuestData.P3;
        } else if (qnum.equals(4)){
            return pQuestData.P4;
        } else if (qnum.equals(5)){
            return pQuestData.P5;
        } else if (qnum.equals(6)){
            return pQuestData.P6;
        } else if (qnum.equals(7)){
            return pQuestData.P7;
        } else if (qnum.equals(8)){
            return pQuestData.P8;
        } else if (qnum.equals(9)){
            return pQuestData.P9;
        } else if (qnum.equals(10)){
            return pQuestData.P10;
        } else if (qnum.equals(11)){
            return pQuestData.P11;
        } else if (qnum.equals(12)){
            return pQuestData.P12;
        }
        return 0;
    }

    public void checkCompletion(Player player, UUID uuid, Integer qnum){
        PQuestData pQuestData = pquestMap.get(uuid);
        int test = qnum - 1;
        Quest quest = pQuestData.QL.get(test);
        if (qnum.equals(1)){
            //player.sendMessage("P1:" + pQuestData.P1 + "/" + quest.amount);
            if (pQuestData.P1 == quest.amount && !pQuestData.PC1) {
                player.sendMessage("Quest 1 complete");
                QuestGUI.questPointManager.addPoints(uuid, quest.reward);
                pQuestData.PC1 = true;
            }
        } else if (qnum.equals(2)){
            //player.sendMessage("P2:" + pQuestData.P2 + "/" + quest.amount);
            if (pQuestData.P2 == quest.amount && !pQuestData.PC2) {
                player.sendMessage("Quest 2 complete");
                QuestGUI.questPointManager.addPoints(uuid, quest.reward);
                pQuestData.PC2 = true;
            }
        } else if (qnum.equals(3)){
            //player.sendMessage("P3:" + pQuestData.P3 + "/" + quest.amount);
            if (pQuestData.P3 == quest.amount && !pQuestData.PC3) {
                player.sendMessage("Quest 3 complete");
                QuestGUI.questPointManager.addPoints(uuid, quest.reward);
                pQuestData.PC3 = true;
            }
        } else if (qnum.equals(4)){
            //player.sendMessage("P4:" + pQuestData.P4 + "/" + quest.amount);
            if (pQuestData.P4 == quest.amount && !pQuestData.PC4) {
                player.sendMessage("Quest 4 complete");
                QuestGUI.questPointManager.addPoints(uuid, quest.reward);
                pQuestData.PC4 = true;
            }
        } else if (qnum.equals(5)){
            //player.sendMessage("P5:" + pQuestData.P5 + "/" + quest.amount);
            if (pQuestData.P5 == quest.amount && !pQuestData.PC5) {
                player.sendMessage("Quest 5 complete");
                QuestGUI.questPointManager.addPoints(uuid, quest.reward);
                pQuestData.PC5 = true;
            }
        } else if (qnum.equals(6)){
            if (pQuestData.P6 == quest.amount && !pQuestData.PC6) {
                player.sendMessage("Quest 6 complete");
                QuestGUI.questPointManager.addPoints(uuid, quest.reward);
                pQuestData.PC6 = true;
            }
        } else if (qnum.equals(7)){
            if (pQuestData.P7 == quest.amount && !pQuestData.PC7) {
                player.sendMessage("Quest 7 complete");
                QuestGUI.questPointManager.addPoints(uuid, quest.reward);
                pQuestData.PC7 = true;
            }
        } else if (qnum.equals(8)){
            if (pQuestData.P8 == quest.amount && !pQuestData.PC8) {
                player.sendMessage("Quest 8 complete");
                QuestGUI.questPointManager.addPoints(uuid, quest.reward);
                pQuestData.PC8 = true;
            }
        } else if (qnum.equals(9)){
            if (pQuestData.P9 == quest.amount && !pQuestData.PC9) {
                player.sendMessage("Quest 9 complete");
                QuestGUI.questPointManager.addPoints(uuid, quest.reward);
                pQuestData.PC9 = true;
            }
        } else if (qnum.equals(10)){
            if (pQuestData.P10 == quest.amount && !pQuestData.PC10) {
                player.sendMessage("Quest 10 complete");
                QuestGUI.questPointManager.addPoints(uuid, quest.reward);
                pQuestData.PC10 = true;
            }
        } else if (qnum.equals(11)){
            if (pQuestData.P11 == quest.amount && !pQuestData.PC11) {
                player.sendMessage("Quest 11 complete");
                QuestGUI.questPointManager.addPoints(uuid, quest.reward);
                pQuestData.PC11 = true;
            }
        } else if (qnum.equals(12)){
            if (pQuestData.P12 == quest.amount && !pQuestData.PC12) {
                player.sendMessage("Quest 12 complete");
                QuestGUI.questPointManager.addPoints(uuid, quest.reward);
                pQuestData.PC12 = true;
            }
        }
        pquestMap.put(uuid, pQuestData);
    }

    public boolean complete(UUID uuid, Integer qnum){
        PQuestData pQuestData = pquestMap.get(uuid);
        if (qnum.equals(1)){
            return pQuestData.PC1;
        } else if (qnum.equals(2)){
            return pQuestData.PC2;
        } else if (qnum.equals(3)){
            return pQuestData.PC3;
        } else if (qnum.equals(4)){
            return pQuestData.PC4;
        } else if (qnum.equals(5)){
            return pQuestData.PC5;
        } else if (qnum.equals(6)){
            return pQuestData.PC6;
        } else if (qnum.equals(7)){
            return pQuestData.PC7;
        } else if (qnum.equals(8)){
            return pQuestData.PC8;
        } else if (qnum.equals(9)){
            return pQuestData.PC9;
        } else if (qnum.equals(10)){
            return pQuestData.PC10;
        } else if (qnum.equals(11)){
            return pQuestData.PC11;
        } else if (qnum.equals(12)){
            return pQuestData.PC12;
        }
         return false;
    }

    public void onJoin(UUID uuid){
        if (!pquestMap.containsKey(uuid)){
            getNewQuests(uuid);
        }
        if (!pquestMap.get(uuid).date.equals(java.time.LocalDate.now())){
            getNewQuests(uuid);
        }
    }

    public void saveWorldData() {
        PlayerQuestsProgress.setup();
        for (Map.Entry<UUID, PQuestData> players : pquestMap.entrySet()) {
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Date", players.getValue().date.toString());
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress1", players.getValue().P1);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete1", players.getValue().PC1);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress2", players.getValue().P2);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete2", players.getValue().PC2);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress3", players.getValue().P3);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete3", players.getValue().PC3);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress4", players.getValue().P4);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete4", players.getValue().PC4);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress5", players.getValue().P5);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete5", players.getValue().PC5);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress6", players.getValue().P6);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete6", players.getValue().PC6);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress7", players.getValue().P7);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete7", players.getValue().PC7);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress8", players.getValue().P8);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete8", players.getValue().PC8);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress9", players.getValue().P9);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete9", players.getValue().PC9);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress10", players.getValue().P10);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete10", players.getValue().PC10);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress11", players.getValue().P11);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete11", players.getValue().PC11);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Progress12", players.getValue().P12);
            PlayerQuestsProgress.get().set("Players." + players.getKey() + ".Complete12", players.getValue().PC12);
            List<Integer> questlist = new ArrayList<>();
            for (int i = 0; i < players.getValue().QL.size(); i++){
                questlist.add(players.getValue().QL.get(i).id);
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
                    pQuestData.date = date;
                    pQuestData.P1 = PlayerQuestsProgress.get().getInt("Players." + key + ".Progress1");
                    pQuestData.PC1 = PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete1");
                    pQuestData.P2 = PlayerQuestsProgress.get().getInt("Players." + key + ".Progress2");
                    pQuestData.PC2 = PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete2");
                    pQuestData.P3 = PlayerQuestsProgress.get().getInt("Players." + key + ".Progress3");
                    pQuestData.PC3 = PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete3");
                    pQuestData.P4 = PlayerQuestsProgress.get().getInt("Players." + key + ".Progress4");
                    pQuestData.PC4 = PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete4");
                    pQuestData.P5 = PlayerQuestsProgress.get().getInt("Players." + key + ".Progress5");
                    pQuestData.PC5 = PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete5");
                    pQuestData.P6 = PlayerQuestsProgress.get().getInt("Players." + key + ".Progress6");
                    pQuestData.PC6 = PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete6");
                    pQuestData.P7 = PlayerQuestsProgress.get().getInt("Players." + key + ".Progress7");
                    pQuestData.PC7 = PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete7");
                    pQuestData.P8 = PlayerQuestsProgress.get().getInt("Players." + key + ".Progress8");
                    pQuestData.PC8 = PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete8");
                    pQuestData.P9 = PlayerQuestsProgress.get().getInt("Players." + key + ".Progress9");
                    pQuestData.PC9 = PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete9");
                    pQuestData.P10 = PlayerQuestsProgress.get().getInt("Players." + key + ".Progress10");
                    pQuestData.PC10 = PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete10");
                    pQuestData.P11 = PlayerQuestsProgress.get().getInt("Players." + key + ".Progress11");
                    pQuestData.PC11 = PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete11");
                    pQuestData.P12 = PlayerQuestsProgress.get().getInt("Players." + key + ".Progress12");
                    pQuestData.PC12 = PlayerQuestsProgress.get().getBoolean("Players." + key + ".Complete12");
                    List<Integer> questlist = PlayerQuestsProgress.get().getIntegerList("Players." + key + ".QuestList");
                    pQuestData.QL = getQuests(questlist);

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
