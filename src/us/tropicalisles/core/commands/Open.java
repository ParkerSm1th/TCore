package us.tropicalisles.core.commands;

import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import us.tropicalisles.core.utils.Transporter;
import us.tropicalisles.core.utils.UtilMethods;

public class Open implements Listener, CommandExecutor
{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("server"))
		{
			
			
	if(sender instanceof Player)
	{	
		Player p = (Player)sender;
		Transporter.serverTransporter(p);
		
	}else{
		UtilMethods.sendMessage(sender, "You cannot use this command as a &6" + sender.getName());
	}
			
		
		}
		return true;
	}
	
	
	public static Entity[] getNearbyEntities(Location l, int radius) {

		    int chunkRadius = radius < 16 ? 1 : (radius - (radius % 16)) / 16;

		   HashSet <Entity> radiusEntities = new HashSet < Entity > ();


		  for (int chX = 0 - chunkRadius; chX <= chunkRadius; chX++) {

		      for (int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++) {
		           int x = (int) l.getX(), y = (int) l.getY(), z = (int) l.getZ();

		       for (Entity e: new Location(l.getWorld(), x + (chX * 16), y, z + (chZ * 16)).getChunk().getEntities()) {

		             if (e.getLocation().distance(l) <= radius && e.getLocation().getBlock() != l.getBlock())

	                radiusEntities.add(e);

			}
		      }
}

		

			   return radiusEntities.toArray(new Entity[radiusEntities.size()]);

		}
	
	
	}

