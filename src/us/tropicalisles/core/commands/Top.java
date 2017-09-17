package us.tropicalisles.core.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import us.tropicalisles.core.utils.UtilMethods;

public class Top implements Listener, CommandExecutor
{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("top"))
		{
			
			Player p = (Player)sender;
			if(UtilMethods.getGroupsForPlayer(p).contains("manager")
					|| UtilMethods.getGroupsForPlayer(p).contains("owner")
					|| UtilMethods.getGroupsForPlayer(p).contains("admin")
					|| UtilMethods.getGroupsForPlayer(p).contains("developer"))

			{
			if(args.length == 0)
			{
				int topX = p.getLocation().getBlockX();
				int topZ = p.getLocation().getBlockZ();
				float pitch = p.getLocation().getPitch();
				float yaw = p.getLocation().getYaw();
				Location loc = new Location(p.getWorld(), topX, p.getWorld().getMaxHeight(), topZ, yaw, pitch);
				p.teleport(loc, TeleportCause.COMMAND);
				UtilMethods.sendMessage(p, "Teleporting to top.");

			}else if(args.length > 1)
			{
				UtilMethods.sendMessage(p, "Wrong usage! [/top]");
			}
			
				
				}else{
					UtilMethods.permsError(p, "Developer");
				}
		
				
			}
		return false;

}
			
		
		
}
