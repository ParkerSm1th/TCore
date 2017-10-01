package us.tropicalisles.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.tropicalisles.core.utils.UtilMethods;

public class HubReload
implements Listener, CommandExecutor
{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("hubreload"))
		{
			
			Player p = (Player)sender;
			if(UtilMethods.getGroupsForPlayer(p).contains("developer")
					|| UtilMethods.getGroupsForPlayer(p).contains("manager")
					|| UtilMethods.getGroupsForPlayer(p).contains("owner")
					|| UtilMethods.getGroupsForPlayer(p).contains("admin"))
			{
				Bukkit.broadcastMessage("§c** LOBBY RELOADED **");
				Bukkit.broadcastMessage("§7Players were kicked as the lobby was reloaded!");
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rl");
				String kickReason = args.length > 0 ? getFinalArg(args, 0) : "§cThe lobby you were in was reloaded!";
				for(Player onlinePlayer : Bukkit.getOnlinePlayers())
				{
					if(onlinePlayer.isOp() || onlinePlayer.hasPermission("mystical.hubreload"))
					{
					return false;
					}else{
						onlinePlayer.kickPlayer(kickReason);
					}
				}
				
				}else{
					UtilMethods.permsError(p, "Developer");
				}
		
				
			}
		return false;

}
			
		
		
	
	

public static String getFinalArg(String[] args, int start)
{
  StringBuilder bldr = new StringBuilder();
  for (int i = start; i < args.length; i++)
  {
    if (i != start) {
      bldr.append(" ");
    }
    bldr.append(args[i]);
  }
  return bldr.toString();
}

 
}
