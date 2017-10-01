package us.tropicalisles.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import us.tropicalisles.core.utils.UtilMethods;

public class Vanish implements Listener, CommandExecutor
{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("vanish"))
		{
			
			Player p = (Player)sender;
			if(UtilMethods.getGroupsForPlayer(p).contains("mod")
					|| UtilMethods.getGroupsForPlayer(p).contains("manager")
					|| UtilMethods.getGroupsForPlayer(p).contains("owner")
					|| UtilMethods.getGroupsForPlayer(p).contains("admin")
					|| UtilMethods.getGroupsForPlayer(p).contains("developer")
					|| UtilMethods.getGroupsForPlayer(p).contains("helper")
					|| UtilMethods.getGroupsForPlayer(p).contains("youtuber")
					|| UtilMethods.getGroupsForPlayer(p).contains("builder"))
			{
                   
			





			}else{
				UtilMethods.permsError(p, "Youtuber");
			}
			
		
		}
		return true;
	}

}

