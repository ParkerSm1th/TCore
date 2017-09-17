package us.tropicalisles.core.commands;



import com.mysql.jdbc.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.tropicalisles.core.gamemode.ToggleGameMode;
import us.tropicalisles.core.utils.UtilMethods;

public class GameModeCommand implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player)) {
            UtilMethods.sendMessage(sender, "This command can only be used through players.");

            return true;
        }
        Player p = (Player)sender;
        if(UtilMethods.getGroupsForPlayer(p).contains("manager")
                || UtilMethods.getGroupsForPlayer(p).contains("owner")
                || UtilMethods.getGroupsForPlayer(p).contains("admin")
                || UtilMethods.getGroupsForPlayer(p).contains("developer")
                || UtilMethods.getGroupsForPlayer(p).contains("builder"))

        {
            Player player = (Player) sender;
            if (args.length == 0) {
                ToggleGameMode.toggleGameMode(player);
            }
            if (args.length > 0) {
                ToggleGameMode.togglePlayersGameMode(player, args[0]);
            }
            return true;

        } else{
            UtilMethods.permsError(p, "Developer");
            return false;

        }

    }
}

