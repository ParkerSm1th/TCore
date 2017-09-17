package us.tropicalisles.core.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;
import us.tropicalisles.core.utils.UtilMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parker on 9/10/17.
 */
public class CommandBypass implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();
        Player p = event.getPlayer();
        String cmd = event.getMessage().toLowerCase();

        if (cmd.startsWith("/kill")) {
            event.setCancelled(true);
            UtilMethods.permsError(player, "N/A");
        }
        if (cmd.startsWith("/minecraft:")) {
            event.setCancelled(true);
            UtilMethods.permsError(player, "N/A");
        }



    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommandPreProcess2(PlayerCommandPreprocessEvent e) {
        String[] msg = e.getMessage().split(" ");
        Player p = e.getPlayer();

        List<String> plugins = new ArrayList();
        plugins.add("pl");
        plugins.add("plugins");
        plugins.add("bukkit:pl");
        plugins.add("bukkit:plugins");
        plugins.add("plugins");

        List<String> version = new ArrayList();
        version.add("ver");
        version.add("version");
        version.add("bukkit:ver");
        version.add("bukkit:version");

        List<String> about = new ArrayList();
        about.add("about");
        about.add("bukkit:about");

        List<String> question = new ArrayList();
        question.add("?");
        question.add("help");
        question.add("bukkit:?");
        if (UtilMethods.getGroupsForPlayer(p).contains("manager")
                || UtilMethods.getGroupsForPlayer(p).contains("owner")
                || UtilMethods.getGroupsForPlayer(p).contains("admin")
                || UtilMethods.getGroupsForPlayer(p).contains("developer"))

        {

        } else {
            for (String Loop : plugins) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    UtilMethods.permsError(p, "Developer");
                }
            }


            for (String Loop : version) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    UtilMethods.permsError(p, "Developer");
                }
            }


            for (String Loop : question) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    UtilMethods.permsError(p, "Developer");
                }
            }


            for (String Loop : about) {
                if (msg[0].equalsIgnoreCase("/" + Loop)) {

                    e.setCancelled(true);
                    UtilMethods.permsError(p, "Developer");
                }
            }
        }

    }




    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        Player p = event.getPlayer();
        if (!event.isCancelled())
        {
            String command = event.getMessage().split(" ")[0];
            HelpTopic htopic = Bukkit.getServer().getHelpMap().getHelpTopic(command);
            if (htopic == null)
            {
                UtilMethods.sendMessage(p, "This command does not exist.");
                event.setCancelled(true);
            }
        }
    }


}
