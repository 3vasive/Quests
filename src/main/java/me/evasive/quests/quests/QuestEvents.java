package me.evasive.quests.quests;

import me.evasive.quests.Quests;
import me.evasive.quests.questshop.QuestShopGUI;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.EnumSet;
import java.util.Objects;
import java.util.UUID;

public class QuestEvents implements Listener {

    public Quests plugin;

    public QuestEvents(Quests plugin) {
        this.plugin = plugin;
    }

    public static EnumSet<Material> farm = EnumSet.of(Material.PUMPKIN, Material.MELON, Material.CARROTS, Material.POTATOES, Material.SUGAR_CANE, Material.WHEAT, Material.NETHER_WART, Material.BEETROOTS);

    @EventHandler
    public void move(PlayerMoveEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        int amount = QuestGUI.questManager.checkPerms(uuid);
        for (int i = 0; i < amount; i++) {
            if (QuestGUI.questManager.getQuestList(uuid).get(i) == null) continue;
            Quest quest = QuestGUI.questManager.getQuestList(uuid).get(i);
            if (!quest.getType().equals("Move")) continue;
            if (e.getFrom().getBlockX() == Objects.requireNonNull(e.getTo()).getBlockX() && e.getFrom().getBlockZ() == e.getTo().getBlockZ())
                continue;
            if (QuestGUI.questManager.GetQuestProgression(uuid, i + 1) >= quest.getAmount()) continue;
            QuestGUI.questManager.QuestProggression(uuid, i + 1);
            QuestGUI.questManager.checkCompletion(e.getPlayer(), uuid, i + 1);
        }
    }

    @EventHandler
    public void place(BlockPlaceEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        int amount = QuestGUI.questManager.checkPerms(uuid);
        for (int i = 0; i < amount; i++) {
            if (QuestGUI.questManager.getQuestList(uuid).get(i) == null) continue;
            Quest quest = QuestGUI.questManager.getQuestList(uuid).get(i);
            if (!quest.getType().equals("Place")) continue;
            if (!e.getBlock().getType().equals(quest.getBlocktype()) && !quest.getBlocktype().equals(Material.AIR))
                continue;
            if (QuestGUI.questManager.GetQuestProgression(uuid, i + 1) >= quest.getAmount()) continue;
            QuestGUI.questManager.QuestProggression(uuid, i + 1);
            QuestGUI.questManager.checkCompletion(e.getPlayer(), uuid, i + 1);
        }
    }

    public boolean isFullyGrown(Block block) {
        Ageable ageable = (Ageable) block.getBlockData();
        int maximumAge = ageable.getMaximumAge();
        return maximumAge == ageable.getAge();
    }

    @EventHandler
    public void mine(BlockBreakEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        int amount = QuestGUI.questManager.checkPerms(uuid);
        for (int i = 0; i < amount; i++) {
            if (QuestGUI.questManager.getQuestList(uuid).get(i) == null) continue;
            Quest quest = QuestGUI.questManager.getQuestList(uuid).get(i);
            if (quest.getBlocktype() == null || (!e.getBlock().getType().equals(quest.getBlocktype()) && !quest.getBlocktype().equals(Material.AIR)))
                continue;
            if (quest.getType().equals("Break")) {
                if (QuestGUI.questManager.GetQuestProgression(uuid, i + 1) >= quest.getAmount() && !(farm.contains(e.getBlock().getType())))
                    continue;
                QuestGUI.questManager.QuestProggression(uuid, i + 1);
                QuestGUI.questManager.checkCompletion(e.getPlayer(), uuid, i + 1);

            } else if (quest.getType().equals("Farm")) {
                if (QuestGUI.questManager.GetQuestProgression(uuid, i + 1) >= quest.getAmount() && farm.contains(e.getBlock().getType()) && isFullyGrown(e.getBlock()))
                    continue;
                QuestGUI.questManager.QuestProggression(uuid, i + 1);
                QuestGUI.questManager.checkCompletion(e.getPlayer(), uuid, i + 1);

            }
        }
    }

    @EventHandler
    public void fish(PlayerFishEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        int amount = QuestGUI.questManager.checkPerms(uuid);
        for (int i = 0; i < amount; i++) {
            if (QuestGUI.questManager.getQuestList(uuid).get(i) == null) continue;
            Quest quest = QuestGUI.questManager.getQuestList(uuid).get(i);
            if (!quest.getType().equals("Fishing")) continue;
            if (!e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH) || !(e.getCaught() instanceof Item)) continue;
            Item fished = (Item) e.getCaught();
            if (!fished.getItemStack().getType().equals(quest.getBlocktype()) && !quest.getBlocktype().equals(Material.AIR))
                continue;
            if (QuestGUI.questManager.GetQuestProgression(uuid, i + 1) + 1 > quest.getAmount()) continue;
            QuestGUI.questManager.QuestProggression(uuid, i + 1);
            QuestGUI.questManager.checkCompletion(e.getPlayer(), uuid, i + 1);
        }
    }

    @EventHandler
    public void slay(EntityDeathEvent e) {
        if (e.getEntity().getKiller() == null) return;
        Player player = e.getEntity().getKiller();
        UUID uuid = player.getUniqueId();
        int amount = QuestGUI.questManager.checkPerms(uuid);
        for (int i = 0; i < amount; i++) {
            if (QuestGUI.questManager.getQuestList(uuid).get(i) == null) continue;
            Quest quest = QuestGUI.questManager.getQuestList(uuid).get(i);
            if (!quest.getType().equals("Slayer")) continue;
            if (!e.getEntityType().equals(quest.getMobtype())) continue;
            if (QuestGUI.questManager.GetQuestProgression(uuid, i + 1) + 1 > quest.getAmount()) continue;
            QuestGUI.questManager.QuestProggression(uuid, i + 1);
            QuestGUI.questManager.checkCompletion(player, uuid, i + 1);
        }
    }

    @EventHandler
    public void CloseInv(InventoryCloseEvent event) {
        QuestGUI questGUI = new QuestGUI(plugin);
        questGUI.stopTimer();
    }

    @EventHandler
    public void ItemClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("Quest Menu") && !event.getView().getTitle().equals("Quest Shop"))
            return;
        if (event.getView().getTitle().equals("Quest Shop")) {
            /*
            Shop items put into object then a list of object to be placed in shop
            Every player will have the same items that rotate every day
            rewards will be tiered and every week there will be 3 commons, 2 rares, 1 epic, 1 legendary, 2 random
            Also double check already made objects to see if you can simplify
            */
            event.setCancelled(true);
            return;
        }
        if (event.getSlot() == 0) {
            QuestShopGUI questShopGUI = new QuestShopGUI(plugin);
            questShopGUI.openGui((Player) event.getWhoClicked());
        }
        event.setCancelled(true);
        return;

    }

    @EventHandler
    public void Join(PlayerJoinEvent event) {
        QuestGUI.questManager.onJoin(event.getPlayer().getUniqueId());
        QuestGUI.questPointManager.onJoin(event.getPlayer().getUniqueId());
    }
}
