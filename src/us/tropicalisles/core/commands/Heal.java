package us.tropicalisles.core.commands;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;
import us.tropicalisles.core.utils.UtilMethods;

public class Heal  implements Listener, CommandExecutor
{
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("heal"))
		{
			
			Player p = (Player)sender;
			if(UtilMethods.getGroupsForPlayer(p).contains("manager")
					|| UtilMethods.getGroupsForPlayer(p).contains("owner")
					|| UtilMethods.getGroupsForPlayer(p).contains("admin")
					|| UtilMethods.getGroupsForPlayer(p).contains("developer")
					|| UtilMethods.getGroupsForPlayer(p).contains("builder"))

			{
				if(args.length == 0)
				{
				    p.setHealth(20.0D);
				    p.setFoodLevel(20);
				    UtilMethods.sendMessage(p, "Reset &6" + p.getName() + "&7's Health & Food!");
				    p.removePotionEffect(PotionEffectType.SPEED);
				    
				}else if (args.length == 1)
				{
					Player targp = Bukkit.getPlayer(args[0]);
					if(targp != null)
					{
					   targp.setHealth(20.0D);
					  targp.setFoodLevel(20);
					    targp.removePotionEffect(PotionEffectType.SPEED);
						UtilMethods.sendMessage(p, "Reset &6" + targp.getName() + "&7's Health & Food!");
					}else{
						UtilMethods.sendMessage(p, "That player is not online!");
					}
				}
				
			}else{
				UtilMethods.permsError(p, "Developer");
			}
			
		
		}
		return true;
	}

}
