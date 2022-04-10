package me.evasive.quests.commands;

import me.evasive.quests.Quests;
import me.evasive.quests.quests.QuestGUI;
import me.evasive.quests.questshop.QuestShopGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommands implements CommandExecutor {

    public Quests plugin;

    public PlayerCommands(Quests plugin) {
        this.plugin = plugin;
        plugin.getCommand("quests").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0){
                QuestGUI questGUI = new QuestGUI(plugin);
                questGUI.openGui(player);
                return true;
            }
            if (args.length == 1){
                if (args[0].equals("shop")){
                    QuestShopGUI questShopGUI = new QuestShopGUI(plugin);
                    questShopGUI.openGui(player);
                    return true;
                }
            }

        }
        return false;
    }
}
