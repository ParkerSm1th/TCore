package us.tropicalisles.core.commands;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.tropicalisles.core.utils.UtilMethods;

import java.util.Set;

public class SetRank implements Listener, CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        Set<String> rank = UtilMethods.getGroupsForPlayer(p);
        if (rank.contains("owner") || rank.contains("manager") || rank.contains("admin")) {
            if (args.length < 2) {
                UtilMethods.sendMessage(p, "Wrong Usage! [/setrank <player> <rank>]");
                return true;
            }
            String target = args[0];
            String group = args[1];
            setRank(p, target, group);
        } else {
            UtilMethods.permsError(p, "Manager");
        }

        return true;
    }

    public static void setRank(Player caller, String target, String group) {
        Player t1 = Bukkit.getPlayerExact(target);
        if (t1 == null) {
            UtilMethods.sendMessage(caller, "That player is not online!");
            return;
        }
        if (group.equalsIgnoreCase("developer") || group.equalsIgnoreCase("admin") || group.equalsIgnoreCase("designer") || group.equalsIgnoreCase("manager") || group.equalsIgnoreCase("default") || group.equalsIgnoreCase("builder") || group.equalsIgnoreCase("helper") || group.equalsIgnoreCase("mod") || group.equalsIgnoreCase("owner") || group.equalsIgnoreCase("youtuber")) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "permissions player " + target + " setgroup " + group);
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "permissions refresh");
            UtilMethods.sendMessage(caller, "Updated &6" + target + "&7's rank to &6" + group.toUpperCase() + "&7.");
            UtilMethods.sendMessage(t1, "Your rank has been set to &6" + group.toUpperCase() + "&7.");
            if(group.equalsIgnoreCase("developer"))
            {
                t1.setPlayerListName("§5§lDeveloper §7" + t1.getName());

            }else if(group.equalsIgnoreCase("admin"))
            {
                t1.setPlayerListName("§c§lAdmin §7" + t1.getName());

            }else if(group.equalsIgnoreCase("owner"))
            {
                t1.setPlayerListName("§3§lOwner §7" + t1.getName());

            }else if(group.equalsIgnoreCase("builder"))
            {
                t1.setPlayerListName("§6§lBuilder §7" + t1.getName());

            }else if(group.equalsIgnoreCase("mod"))
            {
                t1.setPlayerListName("§b§lMod §7" + t1.getName());

            }else if(group.equalsIgnoreCase("helper"))
            {
                t1.setPlayerListName("§e§lHelper §7" + t1.getName());

            }else if(group.equalsIgnoreCase("designer"))
            {
                t1.setPlayerListName("§d§lDesigner §7" + t1.getName());

            }else if(group.equalsIgnoreCase("manager"))
            {
                t1.setPlayerListName("§a§lManager §7" + t1.getName());
            }else if(group.equalsIgnoreCase("youtuber"))
            {
               t1.setPlayerListName("§c§lYou§f§lTube §7" + t1.getName());
            }
            if(!group.equalsIgnoreCase("helper")
                    && !group.equalsIgnoreCase("mod")
                    && !group.equalsIgnoreCase("trial-staff")
                    && !group.equalsIgnoreCase("builder")
                    && !group.equalsIgnoreCase("owner")
                    && !group.equalsIgnoreCase("admin")
                    && !group.equalsIgnoreCase("developer")
                    && !group.equalsIgnoreCase("manager")
                    && !group.equalsIgnoreCase("designer")
                    && !group.equalsIgnoreCase("youtuber"))
            {


                t1.setPlayerListName("§7" + t1.getName());

            }
        } else {
            UtilMethods.sendMessage(caller, "That rank does not exist. Ranks are:\n&3&lOwner\n&a&lManager\n&c&lAdmin\n&d&lDesigner\n&5&lDeveloper\n&6&lBuilder\n&b&lMod\n&3&lHelper\n&e&lTrial-Staff\n&c&lYou&f&lTube\n&7Default");
        }
    }
}
