package us.tropicalisles.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import us.tropicalisles.core.utils.UtilMethods;

public class TeleportAll  implements Listener, CommandExecutor
{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("tpall"))
		{
			
			Player p = (Player)sender;
			if(UtilMethods.getGroupsForPlayer(p).contains("developer")
					|| UtilMethods.getGroupsForPlayer(p).contains("manager")
					|| UtilMethods.getGroupsForPlayer(p).contains("owner")
					|| UtilMethods.getGroupsForPlayer(p).contains("admin"))
			{
				if(args.length == 0)
				{
			Location l = p.getLocation();
			for(Player onlinePlayer : Bukkit.getOnlinePlayers())
			{
				onlinePlayer.teleport(l, TeleportCause.COMMAND);
				
			}
					UtilMethods.sendMessage(p, "Teleporting all players...");
		
				}else if (args.length > 0)
				{
					UtilMethods.sendMessage(p, "Wrong usage! [/tpall]");
					
				}
				
				
			}else{
				UtilMethods.permsError(p, "Developer");
			}
			
		
		}
		return true;
	}

}