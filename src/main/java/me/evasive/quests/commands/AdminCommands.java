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
                        return true;
                    }
                }
            }else if(args.length == 3){
                if (args[0].equals("givepoints")) {
                    Player player2 = Bukkit.getPlayer(args[1]);
                    if (player2 != null) {
                        Integer amount = Integer.valueOf(args[2]);
                        QuestGUI.questPointManager.addPoints(player2.getUniqueId(), amount);
                        player.sendMessage(player2.getDisplayName() + " Has received " + amount + " Quest Points");
                        player2.sendMessage("You have been given " + amount + " Quest Points");
                        return true;
                    }
                } else if (args[0].equals("removepoints")) {
                    Player player2 = Bukkit.getPlayer(args[1]);
                    if (player2 != null) {
                        Integer amount = Integer.valueOf(args[2]);
                        QuestGUI.questPointManager.removePoints(player2.getUniqueId(), amount);
                        player.sendMessage(player2.getDisplayName() + " Has lost " + amount + " Quest Points");
                        player2.sendMessage("You have lost " + amount + " Quest Points");
                        return true;
                    }
                } else if (args[0].equals("setpoints")) {
                    Player player2 = Bukkit.getPlayer(args[1]);
                    if (player2 != null) {
                        Integer amount = Integer.valueOf(args[2]);
                        QuestGUI.questPointManager.setPoints(player2.getUniqueId(), amount);
                        player.sendMessage(player2.getDisplayName() + " Now has " + amount + " Quest Points");
                        player2.sendMessage("You now have " + amount + " Quest Points");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
