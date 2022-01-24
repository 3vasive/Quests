package me.evasive.quests.quests;

import me.evasive.quests.Quests;
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
            if (QuestGUI.questManager.getQuestList(uuid).get(i) != null) {
                Quest quest = QuestGUI.questManager.getQuestList(uuid).get(i);
                if (quest.type.equals("Move")) {
                    if (e.getFrom().getBlockX() != Objects.requireNonNull(e.getTo()).getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ())
                    if (QuestGUI.questManager.GetQuestProgression(uuid, i + 1) < quest.amount) {
                        QuestGUI.questManager.QuestProggression(uuid, i + 1);
                        QuestGUI.questManager.checkCompletion(e.getPlayer(), uuid, i + 1);
                    }

                }
            }
        }
    }

    @EventHandler
    public void place(BlockPlaceEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        int amount = QuestGUI.questManager.checkPerms(uuid);
        for (int i = 0; i < amount; i++) {
            if (QuestGUI.questManager.getQuestList(uuid).get(i) != null) {
                Quest quest = QuestGUI.questManager.getQuestList(uuid).get(i);
                if (quest.type.equals("Place")) {
                    if (e.getBlock().getType().equals(quest.blocktype) || quest.blocktype.equals(Material.AIR)) {
                        if (QuestGUI.questManager.GetQuestProgression(uuid, i + 1) < quest.amount) {
                            QuestGUI.questManager.QuestProggression(uuid, i + 1);
                            QuestGUI.questManager.checkCompletion(e.getPlayer(), uuid, i + 1);
                        }
                    }
                }
            }
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
            if (QuestGUI.questManager.getQuestList(uuid).get(i) != null) {
                Quest quest = QuestGUI.questManager.getQuestList(uuid).get(i);
                if (quest.type.equals("Break")) {
                    if (e.getBlock().getType().equals(quest.blocktype) || quest.blocktype.equals(Material.AIR)) {
                        if (QuestGUI.questManager.GetQuestProgression(uuid, i + 1) < quest.amount && !(farm.contains(e.getBlock().getType()))) {
                            QuestGUI.questManager.QuestProggression(uuid, i + 1);
                            QuestGUI.questManager.checkCompletion(e.getPlayer(), uuid, i + 1);
                        }
                    }
                }else if (quest.type.equals("Farm")) {
                    if (e.getBlock().getType().equals(quest.blocktype) || quest.blocktype.equals(Material.AIR)) {
                        if (QuestGUI.questManager.GetQuestProgression(uuid, i + 1) < quest.amount && farm.contains(e.getBlock().getType()) && isFullyGrown(e.getBlock())) {
                            QuestGUI.questManager.QuestProggression(uuid, i + 1);
                            QuestGUI.questManager.checkCompletion(e.getPlayer(), uuid, i + 1);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void fish(PlayerFishEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        int amount = QuestGUI.questManager.checkPerms(uuid);
        for (int i = 0; i < amount; i++) {
            if (QuestGUI.questManager.getQuestList(uuid).get(i) != null) {
                Quest quest = QuestGUI.questManager.getQuestList(uuid).get(i);
                if (quest.type.equals("Fishing")) {
                    if (e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH) && e.getCaught() instanceof Item) {
                        Item fished = (Item) e.getCaught();
                        if (fished.getItemStack().getType().equals(quest.blocktype) || quest.blocktype.equals(Material.AIR)) {
                            if (QuestGUI.questManager.GetQuestProgression(uuid, i + 1) + 1 <= quest.amount) {
                                QuestGUI.questManager.QuestProggression(uuid, i + 1);
                                QuestGUI.questManager.checkCompletion(e.getPlayer(), uuid, i + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void slay(EntityDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            Player player = e.getEntity().getKiller();
            UUID uuid = player.getUniqueId();
            int amount = QuestGUI.questManager.checkPerms(uuid);
            for (int i = 0; i < amount; i++) {
                if (QuestGUI.questManager.getQuestList(uuid).get(i) != null) {
                    Quest quest = QuestGUI.questManager.getQuestList(uuid).get(i);
                    if (quest.type.equals("Slayer")) {
                        if (e.getEntityType().equals(quest.mobtype)) {
                            if (QuestGUI.questManager.GetQuestProgression(uuid, i + 1) + 1 <= quest.amount) {
                                QuestGUI.questManager.QuestProggression(uuid, i + 1);
                                QuestGUI.questManager.checkCompletion(player, uuid, i + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void CloseInv(InventoryCloseEvent event) {
        QuestGUI questGUI = new QuestGUI(plugin);
        questGUI.stopTimer();
    }

    @EventHandler
    public void ItemClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Quest Menu")) {
            if (event.getSlot() == 0) {
                Player player = (Player) event.getWhoClicked();
                QuestShopGUI questShopGUI = new QuestShopGUI(plugin);
                questShopGUI.openGui(player);
            } else {
                event.setCancelled(true);
            }
        }
        if (event.getView().getTitle().equals("Quest Shop")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void Join(PlayerJoinEvent event) {
        QuestGUI.questManager.onJoin(event.getPlayer().getUniqueId());
        QuestGUI.questPointManager.onJoin(event.getPlayer().getUniqueId());
    }
}
