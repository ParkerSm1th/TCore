package us.tropicalisles.core.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.tyrannyofheaven.bukkit.zPermissions.ZPermissionsCore;
import us.tropicalisles.core.utils.NickAPI;
import us.tropicalisles.core.utils.UtilMethods;

import java.util.Set;

public class ChatFormat implements Listener {

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();
/* if(NickAPI.isNicked(p.getUniqueId()))
 {
     e.setFormat("§7" + NickAPI.getNickName(p.getUniqueId()) + "§8: §7" + e.getMessage().replace("%", "%%"));

 }else {
*/
     Set<String> rank = UtilMethods.getGroupsForPlayer(p);

     if (rank.contains("developer")) {
         e.setFormat("§5§lDeveloper §7" + p.getName() + "§8: §d" + e.getMessage().replace("%", "%%"));

     } else if (rank.contains("admin")) {
         e.setFormat("§c§lAdmin §7" + p.getName() + "§8: §c" + e.getMessage().replace("%", "%%"));

     } else if (rank.contains("owner")) {
         e.setFormat("§3§lOwner §7" + p.getName() + "§8: §3" + e.getMessage().replace("%", "%%"));

     } else if (rank.contains("builder")) {
         e.setFormat("§6§lBuilder §7" + p.getName() + "§8: §6" + e.getMessage().replace("%", "%%"));

     } else if (rank.contains("mod")) {
         e.setFormat("§b§lModerator §7" + p.getName() + "§8: §b" + e.getMessage().replace("%", "%%"));

     } else if (rank.contains("helper")) {
         e.setFormat("§e§lHelper §7" + p.getName() + "§8: §e" + e.getMessage().replace("%", "%%"));

     } else if (rank.contains("designer")) {
         e.setFormat("§d§lDesigner §7" + p.getName() + "§8: §d" + e.getMessage().replace("%", "%%"));

     } else if (rank.contains("manager")) {
         e.setFormat("§a§lManager §7" + p.getName() + "§8: §a" + e.getMessage().replace("%", "%%"));

     } else if (rank.contains("youtuber")) {
         e.setFormat("§c§lYou§f§lTube §7" + p.getName() + "§8: §f" + e.getMessage().replace("%", "%%"));

     }

     if (!rank.contains("helper")
             && !rank.contains("mod")
             && !rank.contains("trial-staff")
             && !rank.contains("builder")
             && !rank.contains("owner")
             && !rank.contains("admin")
             && !rank.contains("developer")
             && !rank.contains("manager")
             && !rank.contains("designer")
             && !rank.contains("youtuber")) {


         e.setFormat("§7" + p.getName() + "§8: §7" + e.getMessage().replace("%", "%%"));

     }




    }



}