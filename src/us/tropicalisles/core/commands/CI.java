package us.tropicalisles.core.commands;

import com.mysql.jdbc.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import us.tropicalisles.core.utils.UtilMethods;

public class CI implements Listener, CommandExecutor
{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("ci"))
		{
			
			Player p = (Player)sender;
			if(UtilMethods.getGroupsForPlayer(p).contains("manager")
					|| UtilMethods.getGroupsForPlayer(p).contains("owner")
					|| UtilMethods.getGroupsForPlayer(p).contains("admin")
					|| UtilMethods.getGroupsForPlayer(p).contains("developer"))

			{
				if(args.length == 0)
				{
			p.getInventory().clear();
			UtilMethods.sendMessage(p,"Cleared all inventory items!");

				}else if (args.length == 1)
				{
					Player targp = Bukkit.getPlayer(args[0]);
					if(targp != null)
					{
					targp.getInventory().clear();
						UtilMethods.sendMessage(p,"Cleared all inventory items from &6" + targp.getName() + "&7!");

					}else{
						UtilMethods.sendMessage(p,"That player is not online!");
					}
				}else if (args.length > 1)
				{
					UtilMethods.permsError(p,"Helper");
				}
				
			}else{
				UtilMethods.permsError(p,"Helper");
			}
			
		
		}
		return true;
	}

}

