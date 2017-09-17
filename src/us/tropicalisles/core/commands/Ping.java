package us.tropicalisles.core.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.tropicalisles.core.utils.UtilMethods;

public class Ping
implements Listener, CommandExecutor
{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ping")) {

			Player p = (Player) sender;

			if (args.length == 0) {
				int playerPing = ((CraftPlayer) p).getHandle().ping;
				UtilMethods.sendMessage(p, "Your current ping: &6" + playerPing + "&7ms");
			} else if (args.length == 1) {
				Player targp = Bukkit.getPlayer(args[0]);
				if (targp != null) {
					int playerPing = ((CraftPlayer) targp).getHandle().ping;

					UtilMethods.sendMessage(p, "§6" + targp.getName() + "&7's current ping: &6" + playerPing + "&7ms");
				} else {
					UtilMethods.sendMessage(p, "That player is not online!");
				}
			} else if (args.length > 1) {
				UtilMethods.sendMessage(p, "Wrong usage! [/ping <player>]");


			}
			return false;

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
