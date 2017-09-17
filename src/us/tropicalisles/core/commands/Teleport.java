package us.tropicalisles.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.tropicalisles.core.utils.UtilMethods;

public class Teleport  implements Listener, CommandExecutor
{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("tp"))
		{
			
			Player p = (Player)sender;
			if(UtilMethods.getGroupsForPlayer(p).contains("mod")
					|| UtilMethods.getGroupsForPlayer(p).contains("manager")
					|| UtilMethods.getGroupsForPlayer(p).contains("owner")
					|| UtilMethods.getGroupsForPlayer(p).contains("admin")
					|| UtilMethods.getGroupsForPlayer(p).contains("developer")
					|| UtilMethods.getGroupsForPlayer(p).contains("builder"))

			{
				if(args.length == 0)
				{

					UtilMethods.sendMessage(p, "Please specify a player name!");
				}else if (args.length == 1)
				{
					Player targp = Bukkit.getPlayer(args[0]);
					if(targp != null)
					{
					p.teleport(targp);
						UtilMethods.sendMessage(p, "Teleported to &6" + targp.getName());
					}else{
						UtilMethods.sendMessage(p, "That player is not online!");
					}
				}else if(args.length > 1)
				{
					UtilMethods.sendMessage(p, "Wrong usage! [/tp <player>]");
				}
				
			}else{
				UtilMethods.permsError(p, "Moderator");
			}
			
		
		}
		return true;
	}

}

