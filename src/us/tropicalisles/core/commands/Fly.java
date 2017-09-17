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

public class Fly implements Listener, CommandExecutor
{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("fly"))
		{
		
			Player p = (Player)sender;
			if (UtilMethods.getGroupsForPlayer(p).contains("developer")
					|| UtilMethods.getGroupsForPlayer(p).contains("manager")
					|| UtilMethods.getGroupsForPlayer(p).contains("owner")
					|| UtilMethods.getGroupsForPlayer(p).contains("admin")
					|| UtilMethods.getGroupsForPlayer(p).contains("youtuber")
					|| UtilMethods.getGroupsForPlayer(p).contains("helper")
					|| UtilMethods.getGroupsForPlayer(p).contains("builder")
					|| UtilMethods.getGroupsForPlayer(p).contains("designer")
					|| UtilMethods.getGroupsForPlayer(p).contains("mod")) {
				if(args.length == 0)
				{
			if(p.getAllowFlight())
			{

			      p.setAllowFlight(false);
			      p.setFlying(false);
				UtilMethods.sendMessage(p, p.getName() + "'s Fly Mode: &6False");
			
			}else{
				   p.setAllowFlight(true);
				     p.setFlying(true);
				UtilMethods.sendMessage(p, p.getName() + "'s Fly Mode: &6True");
					
			}
				}else if (args.length == 1)
				{
					Player targp = Bukkit.getPlayer(args[0]);
					if(targp != null)
					{
						if(targp.getAllowFlight())
						{
							targp.setAllowFlight(false);
							targp.setFlying(false);
							UtilMethods.sendMessage(p, targp.getName() + "'s Fly Mode: " + ChatColor.GOLD + "False");
							UtilMethods.sendMessage(targp, p.getName() + " toggled your Build Mode: " + ChatColor.GOLD + "False");
				
						}else{
							targp.setAllowFlight(true);
							targp.setFlying(true);
							UtilMethods.sendMessage(p, targp.getName() + "'s Fly Mode: " + ChatColor.GOLD + "True");
							UtilMethods.sendMessage(targp, p.getName() + " toggled your Build Mode: " + ChatColor.GOLD + "True");

						}
					}else{
						UtilMethods.sendMessage(p,"That player is not online!");
					}
				}
				
			}else{
				UtilMethods.permsError(p, "Helper");
			}
			
		
		}
		return true;
	}

}

