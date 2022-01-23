package me.evasive.quests.quests;

import me.evasive.quests.Quests;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class QuestShopGUI {

    public Quests plugin;

    public QuestShopGUI(Quests plugin){
        this.plugin = plugin;
    }

    Inventory questsShop = Bukkit.createInventory(null, 36, "Quest Shop");

    public void openGui(Player player){
        //Quest info
        ItemStack info = new ItemStack(Material.WRITABLE_BOOK);
        ItemMeta infometa = info.getItemMeta();
        infometa.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Quest Shop info");
        ArrayList<String> infolore = new ArrayList();
        infolore.add(ChatColor.YELLOW + "Mobs have a chance on death to give you");
        infolore.add(ChatColor.YELLOW + "tokens. Depending on the mob they may");
        infolore.add(ChatColor.YELLOW + "drop more tokens or have a better chance.");
        infometa.setLore(infolore);
        info.setItemMeta(infometa);
        questsShop.setItem(8, info);

        //Quest Point/shop button
        ItemStack points = new ItemStack(Material.GHAST_TEAR);
        ItemMeta pointsItemMeta = points.getItemMeta();
        pointsItemMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Quest Points");
        ArrayList<String> pointsItemLore = new ArrayList();
        pointsItemLore.add(ChatColor.YELLOW + "Balance: " + QuestGUI.questPointManager.getPoints(player.getUniqueId()));
        pointsItemMeta.setLore(pointsItemLore);
        points.setItemMeta(pointsItemMeta);
        questsShop.setItem(0, points);

        ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta1 = fill.getItemMeta();
        meta1.setDisplayName(ChatColor.GRAY + "");
        fill.setItemMeta(meta1);

        questsShop.setItem(1, fill);
        questsShop.setItem(2, fill);
        questsShop.setItem(3, fill);
        questsShop.setItem(4, fill);
        questsShop.setItem(5, fill);
        questsShop.setItem(6, fill);
        questsShop.setItem(7, fill);
        questsShop.setItem(9, fill);
        questsShop.setItem(10, fill);
        questsShop.setItem(16, fill);
        questsShop.setItem(17, fill);
        questsShop.setItem(18, fill);
        questsShop.setItem(19, fill);
        questsShop.setItem(20, fill);
        questsShop.setItem(24, fill);
        questsShop.setItem(25, fill);
        questsShop.setItem(26, fill);
        questsShop.setItem(27, fill);
        questsShop.setItem(28, fill);
        questsShop.setItem(29, fill);
        questsShop.setItem(30, fill);
        questsShop.setItem(31, fill);
        questsShop.setItem(32, fill);
        questsShop.setItem(33, fill);
        questsShop.setItem(34, fill);
        questsShop.setItem(35, fill);

        player.openInventory(questsShop);
    }
}
