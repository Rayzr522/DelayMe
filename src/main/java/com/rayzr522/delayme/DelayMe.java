package com.rayzr522.delayme;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Rayzr
 */
public class DelayMe extends JavaPlugin {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "You must specify a player and a command!");
            return true;
        }

        @SuppressWarnings("deprecation")
        OfflinePlayer player = getServer().getPlayer(args[0]);
        if (player == null || !player.isOnline()) {
            sender.sendMessage(ChatColor.RED + "That is not a valid player!");
            return true;
        }

        new BukkitRunnable() {
            public void run() {
                if (!player.isOnline()) {
                    return;
                }
                getServer().dispatchCommand(player.getPlayer(), Arrays.stream(Arrays.copyOfRange(args, 1, args.length)).collect(Collectors.joining(" ")));
            }
        }.runTaskLater(this, 2L);

        return true;
    }

}
