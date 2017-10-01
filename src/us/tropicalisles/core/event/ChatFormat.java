package us.tropicalisles.core.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import us.tropicalisles.core.utils.UtilMethods;

public class ChatFormat implements Listener {
	@EventHandler
	public void redo(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
			
			
					if(UtilMethods.getGroupsForPlayer(p).contains("developer"))
					{
						e.setFormat("§5§lDEV §7" + p.getName() + "§7: §f" + e.getMessage().replace("%", "%%"));
				
					}else if(UtilMethods.getGroupsForPlayer(p).contains("admin"))
					{
						e.setFormat("§c§lADMIN §7" + p.getName() +  "§7: §f" + e.getMessage().replace("%", "%%"));
				
					}else if(UtilMethods.getGroupsForPlayer(p).contains("owner"))
					{
						e.setFormat("§c§lOWNER §7" + p.getName() +  "§7: §f" + e.getMessage().replace("%", "%%"));
				
					}else if(UtilMethods.getGroupsForPlayer(p).contains("founder"))
					{
						e.setFormat("§c§lFOUNDER §7" + p.getName() +  "§7: §f" + e.getMessage().replace("%", "%%"));
				
					}else if(UtilMethods.getGroupsForPlayer(p).contains("builder"))
					{
						e.setFormat("§d§lBUILDER §7" + p.getName() + "§7: §f" + e.getMessage().replace("%", "%%"));
				
					}else if(UtilMethods.getGroupsForPlayer(p).contains("mod"))
					{
						e.setFormat("§a§lMOD §7" + p.getName() + "§7: §f" + e.getMessage().replace("%", "%%"));
				
					}else if(UtilMethods.getGroupsForPlayer(p).contains("helper"))
					{
						e.setFormat("§e§lHELPER §7" + p.getName() + "§7: §f" + e.getMessage().replace("%", "%%"));
				
					}else if(UtilMethods.getGroupsForPlayer(p).contains("vip"))
					{
						e.setFormat("§a§lVIP §7" + p.getName() + "§7: §f" + e.getMessage().replace("%", "%%"));
				
					}else if(UtilMethods.getGroupsForPlayer(p).contains("vip+"))
					{
						e.setFormat("§a§lVIP+ §7" + p.getName() + "§7: §f" + e.getMessage().replace("%", "%%"));
				
					}else if(UtilMethods.getGroupsForPlayer(p).contains("elite"))
					{
						e.setFormat("§e§lELITE §7" + p.getName() + "§7: §f" + e.getMessage().replace("%", "%%"));
				
					}else if(UtilMethods.getGroupsForPlayer(p).contains("legend"))
					{
						e.setFormat("§6§lLEGEND §7" + p.getName() + "§7: §f" + e.getMessage().replace("%", "%%"));
				
					}else if(UtilMethods.getGroupsForPlayer(p).contains("master"))
					{
						e.setFormat("§6§lMASTER §7" + p.getName() + "§7: §f" + e.getMessage().replace("%", "%%"));
				
					}else if(UtilMethods.getGroupsForPlayer(p).contains("youtuber"))
					{
						e.setFormat("§f§lYOU§c§lTUBE §7" + p.getName() + "§7: §f" + e.getMessage().replace("%", "%%"));
				
					}
					
					if(!UtilMethods.getGroupsForPlayer(p).contains("helper") 
							&& !UtilMethods.getGroupsForPlayer(p).contains("mod")
 && !UtilMethods.getGroupsForPlayer(p).contains("helper")
 && !UtilMethods.getGroupsForPlayer(p).contains("builder")
 && !UtilMethods.getGroupsForPlayer(p).contains("owner")
 && !UtilMethods.getGroupsForPlayer(p).contains("admin")
 && !UtilMethods.getGroupsForPlayer(p).contains("founder")
 && !UtilMethods.getGroupsForPlayer(p).contains("vip")
 && !UtilMethods.getGroupsForPlayer(p).contains("vip+")
 && !UtilMethods.getGroupsForPlayer(p).contains("elite")
 && !UtilMethods.getGroupsForPlayer(p).contains("legend")
 && !UtilMethods.getGroupsForPlayer(p).contains("master")
 && !UtilMethods.getGroupsForPlayer(p).contains("youtuber")
 && !UtilMethods.getGroupsForPlayer(p).contains("developer"))
					{
					
					
						e.setFormat("§7" + p.getName() + "§7: " + e.getMessage().replace("%", "%%"));
						
					}
					


    }



}