package me.evasive.quests.commands;

import me.evasive.quests.Quests;
import me.evasive.quests.quests.QuestGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {

    public Quests plugin;

    public AdminCommands(Quests plugin) {
        this.plugin = plugin;
        plugin.getCommand("aquests").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 2) {
                if (args[0].equals("reset")) {
                    Player player2 = Bukkit.getPlayer(args[1]);
                    if (player2 != null) {
                        QuestGUI.questManager.getNewQuests(player2.getUniqueId());
                        player.sendMessage("Quests reset for " + player2.getDisplayName());
                        player2.sendMessage("Your quests have been reset for today");
                    }
                }
            }
        }
        return false;
    }
}
