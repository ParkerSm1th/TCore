package us.tropicalisles.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.tropicalisles.core.utils.UtilMethods;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;

import static net.minecraft.server.v1_8_R3.StatisticList.p;

public class noDamage implements Listener, CommandExecutor {


    public static ArrayList<String> damage = new ArrayList<String>();


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {

            Player p= (Player) sender;
            if(UtilMethods.getGroupsForPlayer(p).contains("manager")
                    || UtilMethods.getGroupsForPlayer(p).contains("owner")
                    || UtilMethods.getGroupsForPlayer(p).contains("admin")
                    || UtilMethods.getGroupsForPlayer(p).contains("developer"))

            {
                if (damage.contains(p.getName())) {

                    damage.remove(p.getName());
                    UtilMethods.sendMessage(p, "Enabled your damage.");

                } else {
                    damage.add(p.getName());
                    UtilMethods.sendMessage(p, "Disabled your damage.");

                }

            }else{
                UtilMethods.permsError(sender, "Developer");
            }

            return true;
        } else {
            UtilMethods.sendMessage(sender, "Only players can perform this command.");
        }
        return true;
    }

}
