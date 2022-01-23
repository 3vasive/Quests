package me.evasive.quests.quests;

import me.evasive.quests.Quests;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class QuestEvents implements Listener {

    public Quests plugin;

    public QuestEvents(Quests plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void mine(BlockBreakEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        for (int i = 0; i < 5; i++) {
            if (QuestGUI.questManager.getQuestList(uuid).get(i) != null) {
                Quest quest = QuestGUI.questManager.getQuestList(uuid).get(i);
                if (quest.type.equals("Break")) {
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

    @EventHandler
    public void fish(PlayerFishEvent e) {
        UUID uuid = e.getPlayer().getUniqueId();
        for (int i = 0; i < 5; i++) {
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
            for (int i = 0; i < 5; i++) {
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
            if (event.getSlot() == 0){
                Player player = (Player) event.getWhoClicked();
                QuestShopGUI questShopGUI = new QuestShopGUI(plugin);
                questShopGUI.openGui(player);
            }else{
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
