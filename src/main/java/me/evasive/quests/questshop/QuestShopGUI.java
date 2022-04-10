package me.evasive.quests.questshop;

import me.evasive.quests.Quests;
import me.evasive.quests.quests.QuestGUI;
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
        infometa.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "QUEST SHOP INFO");
        ArrayList<String> infolore = new ArrayList();
        infolore.add(ChatColor.GRAY + "Spend quest points earned from");
        infolore.add(ChatColor.GRAY + "completing /quests here");
        infometa.setLore(infolore);
        info.setItemMeta(infometa);
        questsShop.setItem(8, info);

        //Quest Point/shop button
        ItemStack points = new ItemStack(Material.NETHER_STAR);
        ItemMeta pointsItemMeta = points.getItemMeta();
        pointsItemMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "QUEST POINTS");
        ArrayList<String> pointsItemLore = new ArrayList();
        pointsItemLore.add(ChatColor.GRAY + "Obtained through completing quests");
        pointsItemLore.add("");
        pointsItemLore.add(ChatColor.AQUA + "" + ChatColor.BOLD + "YOUR POINTS:");
        pointsItemLore.add(ChatColor.GRAY + "" + "♦ " + ChatColor.RESET + "" + ChatColor.GRAY + QuestGUI.questPointManager.getPoints(player.getUniqueId()) + " Quest Points");
        //pointsItemLore.add("");
        //pointsItemLore.add(ChatColor.GRAY + "Left-Click to open Quest Point Shop");
        pointsItemMeta.setLore(pointsItemLore);
        points.setItemMeta(pointsItemMeta);
        questsShop.setItem(0, points);

        ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta1 = fill.getItemMeta();
        meta1.setDisplayName(ChatColor.GRAY + "");
        fill.setItemMeta(meta1);

        /*ItemStack shop_item1 = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta shop_item1Meta = shop_item1.getItemMeta();
        shop_item1Meta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Quester's Pickaxe");
        ArrayList<String> shop_item1Lore = new ArrayList();
        shop_item1Lore.add(ChatColor.GRAY + "A pickaxe forged for the mightiest questers in the land");
        shop_item1Lore.add("");
        shop_item1Lore.add(ChatColor.GRAY + "- Some really cool ability");
        shop_item1Lore.add("");
        shop_item1Lore.add(ChatColor.GRAY + "Cost: 10 Quest Points");
        shop_item1Meta.setLore(shop_item1Lore);
        shop_item1.setItemMeta(shop_item1Meta);
        questsShop.setItem(11, shop_item1);*/

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
