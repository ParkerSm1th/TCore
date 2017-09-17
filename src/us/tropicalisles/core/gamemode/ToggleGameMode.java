package us.tropicalisles.core.gamemode;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import us.tropicalisles.core.utils.UtilMethods;


public class ToggleGameMode
        implements Listener
{
    public static HashMap<Player, Player> PlayerGameMode = new HashMap();

    @EventHandler
    public void onQuit(PlayerQuitEvent event) { Player player = event.getPlayer();
        if (PlayerGameMode.containsKey(player)) {
            Player target = (Player)PlayerGameMode.get(player);
            if ((target != null) &&
                    (target.getGameMode() == GameMode.CREATIVE)) {
                target.setGameMode(GameMode.SURVIVAL);
                PlayerGameMode.remove(player);
            }
        }
    }

    public static void toggleGameMode(Player player) {
        if ((player.getGameMode() == GameMode.CREATIVE) || (player.getGameMode() == GameMode.SPECTATOR)) {
            player.setGameMode(GameMode.SURVIVAL);
            UtilMethods.sendMessage(player, player.getName() + "'s Build Mode: " + ChatColor.GOLD + "False");
            return;
        }if ((player.getGameMode() == GameMode.SURVIVAL) || (player.getGameMode() == GameMode.ADVENTURE)) {
            player.setGameMode(GameMode.CREATIVE);
            UtilMethods.sendMessage(player, player.getName() + "'s Build Mode: " + ChatColor.GOLD + "True");
            player.getInventory().clear();
            return;
        }
    }

    public static void togglePlayersGameMode(Player caller, String target) { Player t1 = Bukkit.getPlayerExact(target);
        if (t1 == null) {
            UtilMethods.sendMessage(caller, "That player is not online!");

            return;
        }
        if ((t1.getGameMode() == GameMode.CREATIVE) || (t1.getGameMode() == GameMode.SPECTATOR)) {
            t1.setGameMode(GameMode.SURVIVAL);
            UtilMethods.sendMessage(caller, t1.getName() + "'s Build Mode: " + ChatColor.GOLD + "False");
            UtilMethods.sendMessage(t1, caller.getName() + " toggled your Build Mode: " + ChatColor.GOLD + "False");
            PlayerGameMode.remove(caller);
            return;
        }
        if ((t1.getGameMode() == GameMode.SURVIVAL) || (t1.getGameMode() == GameMode.ADVENTURE)) {
            t1.setGameMode(GameMode.CREATIVE);
            UtilMethods.sendMessage(caller, t1.getName() + "'s Build Mode: " + ChatColor.GOLD + "True");
            UtilMethods.sendMessage(t1, caller.getName() + " toggled your Build Mode: " + ChatColor.GOLD + "True");
            t1.getInventory().clear();
            PlayerGameMode.put(caller, t1);
            return;
        }
    }
}
