package me.evasive.quests.quests;

import me.evasive.quests.Quests;
import me.evasive.quests.questpoints.QuestPointManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.TimerTask;

public class QuestGUI {

    int id = 0;
    public Quests plugin;

    public QuestGUI(Quests plugin) {
        this.plugin = plugin;
    }

    public static QuestManager questManager = new QuestManager();
    public static QuestPointManager questPointManager = new QuestPointManager();

    Inventory quests = Bukkit.createInventory(null, 36, "Quest Menu");

    public void openGui(Player player) {

        //Quest Point/shop button
        ItemStack points = new ItemStack(Material.NETHER_STAR);
        ItemMeta pointsItemMeta = points.getItemMeta();
        pointsItemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "QUEST POINTS");
        ArrayList<String> pointsItemLore = new ArrayList();
        pointsItemLore.add(ChatColor.GRAY + "Obtained through completing quests");
        pointsItemLore.add("");
        pointsItemLore.add(ChatColor.AQUA + "" + ChatColor.BOLD + "YOUR POINTS:");
        pointsItemLore.add(ChatColor.GRAY + "" + "♦ " + ChatColor.RESET + "" + ChatColor.GRAY + questPointManager.getPoints(player.getUniqueId()) + " Quest Points");
        pointsItemLore.add("");
        pointsItemLore.add(ChatColor.GRAY + "Left-Click to open Quest Point Shop");
        pointsItemMeta.setLore(pointsItemLore);
        points.setItemMeta(pointsItemMeta);
        quests.setItem(0, points);

        //Quest info
        ItemStack info = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta infometa = info.getItemMeta();
        infometa.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "QUEST INFO");
        ArrayList<String> infolore = new ArrayList();
        infolore.add(ChatColor.GRAY + "Complete the following quests to recieve");
        infolore.add(ChatColor.GRAY + "quest points to spend in the quest shop");
        infometa.setLore(infolore);
        info.setItemMeta(infometa);
        quests.setItem(8, info);

        //Timer for reset quest
        ItemStack timer = new ItemStack(Material.CLOCK);
        ItemMeta timermeta = timer.getItemMeta();
        timermeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "QUESTS RESET");
        ArrayList<String> timerlore = new ArrayList();
        ZoneId z = ZoneId.of("America/New_York");
        ZonedDateTime now = ZonedDateTime.now(z);
        ZonedDateTime todayStart = now.toLocalDate().atStartOfDay(z);
        Duration timeer = Duration.between(todayStart, now);
        long delay = 86400000 - timeer.toMillis();
        int seconds = (int) (delay / 1000) % 60;
        int minutes = (int) ((delay / (1000 * 60)) % 60);
        int hours = (int) ((delay / (1000 * 60 * 60)) % 24);
        timerlore.add(ChatColor.GRAY + "Quest's reset in: " + "" + hours + ":" + minutes + ":" + seconds);
        timermeta.setLore(timerlore);
        timer.setItemMeta(timermeta);
        quests.setItem(4, timer);

        ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta1 = fill.getItemMeta();
        meta1.setDisplayName(ChatColor.GRAY + "");
        fill.setItemMeta(meta1);


        quests.setItem(1, fill);
        quests.setItem(2, fill);
        quests.setItem(3, fill);
        quests.setItem(5, fill);
        quests.setItem(6, fill);
        quests.setItem(7, fill);
        quests.setItem(9, fill);
        quests.setItem(17, fill);
        quests.setItem(18, fill);
        quests.setItem(19, fill);
        quests.setItem(25, fill);
        quests.setItem(26, fill);
        quests.setItem(27, fill);
        quests.setItem(28, fill);
        quests.setItem(29, fill);
        quests.setItem(30, fill);
        quests.setItem(31, fill);
        quests.setItem(32, fill);
        quests.setItem(33, fill);
        quests.setItem(34, fill);
        quests.setItem(35, fill);

        //Quest setup

        for (int i = 0; i < 12; i++) {
            ItemStack tempQuest;
            if (i < questManager.getQuestList(player.getUniqueId()).size()) {
                Quest quest = questManager.getQuestList(player.getUniqueId()).get(i);
                tempQuest = new ItemStack(quest.item);
                ItemMeta tempQuestItemMeta = tempQuest.getItemMeta();
                tempQuestItemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + quest.name + ":");
                ArrayList<String> tempQuestItemLore = new ArrayList();
                tempQuestItemLore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "◍ " + ChatColor.RESET + "" + ChatColor.GRAY + quest.description);
                tempQuestItemLore.add("");
                tempQuestItemLore.add(ChatColor.AQUA + "" + ChatColor.BOLD + "Progress:");
                tempQuestItemLore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "◍ " + ChatColor.RESET + "" + ChatColor.GRAY + questManager.GetQuestProgression(player.getUniqueId(), i + 1) + "/" + quest.amount);
                tempQuestItemLore.add("");
                tempQuestItemLore.add(ChatColor.AQUA + "" + ChatColor.BOLD + "Reward:");
                tempQuestItemLore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "◍ " + ChatColor.RESET + "" + ChatColor.GRAY + quest.reward + " Quest Points");
                if (questManager.complete(player.getUniqueId(), i + 1)) {
                    tempQuestItemLore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "QUEST COMPLETED");
                    tempQuestItemMeta.setDisplayName(ChatColor.STRIKETHROUGH + quest.name);
                    tempQuestItemMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                    tempQuestItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
                tempQuestItemMeta.setLore(tempQuestItemLore);
                tempQuest.setItemMeta(tempQuestItemMeta);
            } else {
                tempQuest = new ItemStack(Material.BARRIER);
                ItemMeta tempQuestItemMeta = tempQuest.getItemMeta();
                tempQuestItemMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "LOCKED QUEST");
                ArrayList<String> tempQuestItemLore = new ArrayList();
                tempQuestItemLore.add(ChatColor.GRAY + "This quest can be unlocked by");
                tempQuestItemLore.add(ChatColor.GRAY + "leveling up your /{ranks command} or");
                tempQuestItemLore.add(ChatColor.GRAY + "by purchasing a rank on /buy");
                tempQuestItemMeta.setLore(tempQuestItemLore);
                tempQuest.setItemMeta(tempQuestItemMeta);
            }
            if (10 + i <= 16) {
                quests.setItem(10 + i, tempQuest);
            } else {
                quests.setItem(13 + i, tempQuest);
            }
        }


        player.openInventory(quests);
        updateTimer(quests);
    }

    public void updateTimer(Inventory quests) {
        id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Quests.getPlugin(Quests.class), new TimerTask() {
            @Override
            public void run() {
                ItemStack timer = new ItemStack(Material.CLOCK);
                ItemMeta timermeta = timer.getItemMeta();
                timermeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "QUESTS RESET");
                ArrayList<String> timerlore = new ArrayList();
                ZoneId z = ZoneId.of("America/New_York");
                ZonedDateTime now = ZonedDateTime.now(z);
                ZonedDateTime todayStart = now.toLocalDate().atStartOfDay(z);
                Duration timeer = Duration.between(todayStart, now);
                long delay = 86400000 - timeer.toMillis();
                int seconds = (int) (delay / 1000) % 60;
                int minutes = (int) ((delay / (1000 * 60)) % 60);
                int hours = (int) ((delay / (1000 * 60 * 60)) % 24);
                timerlore.add(ChatColor.GRAY + "Quest's reset in: " + "" + hours + ":" + minutes + ":" + seconds);
                timermeta.setLore(timerlore);
                timer.setItemMeta(timermeta);
                quests.setItem(4, timer);
            }
        }, 0, 20);
    }

    public void stopTimer() {
        Bukkit.getServer().getScheduler().cancelTasks(Quests.getPlugin(Quests.class));
    }
}
