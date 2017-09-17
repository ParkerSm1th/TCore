package us.tropicalisles.core.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.mysql.jdbc.PreparedStatement;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import us.tropicalisles.core.DefaultFontInfo;
import us.tropicalisles.core.event.Listeners;

public class UtilMethods 
implements Listener
{
public void sendActionbar(Player p, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" +
                ChatColor.translateAlternateColorCodes('&', message) + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
    }


	private static final String GROUP_PREFIX = "group.";

	public static Set<String> getGroupsForPlayer(Player player) {
		Set<String> groups = new HashSet<String>();
		for (PermissionAttachmentInfo pai : player.getEffectivePermissions()) {
			if (!pai.getPermission().startsWith(GROUP_PREFIX) || !pai.getValue())
				continue;
			groups.add(pai.getPermission().substring(GROUP_PREFIX.length()));
		}
		return groups;
	}

	public static void sendMessage(CommandSender sender, String message) {

		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Tropical Isles &8» &7" + message));

	}

	public static void permsError(CommandSender sender, String message) {
		sendMessage(sender, "You must have the rank of &6" + message + " &7or higher to execute this command.");
	}



	public static void sendCenteredMessage(Player player, String message)
	{
		message = ChatColor.translateAlternateColorCodes('&', message);
		int messagePxSize = 0;
		boolean previousCode = false;
		boolean isBold = false;
		int charIndex = 0;
		int lastSpaceIndex = 0;
		String toSendAfter = null;
		String recentColorCode = "";
		char[] arrayOfChar;
		int j = (arrayOfChar = message.toCharArray()).length;
		for (int i = 0; i < j; i++)
		{
			char c = arrayOfChar[i];
			if (c == '§')
			{
				previousCode = true;
			}
			else
			{
				if (previousCode)
				{
					previousCode = false;
					recentColorCode = "§" + c;
					if ((c == 'l') || (c == 'L'))
					{
						isBold = true;
						continue;
					}
					isBold = false;
				}
				else if (c == ' ')
				{
					lastSpaceIndex = charIndex;
				}
				else
				{
					DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
					messagePxSize += (isBold ? dFI.getBoldLength() : dFI.getLength());
					messagePxSize++;
				}
				if (messagePxSize >= 350)
				{
					toSendAfter = recentColorCode + message.substring(lastSpaceIndex + 1, message.length());
					message = message.substring(0, lastSpaceIndex + 1);
					break;
				}
				charIndex++;
			}
		}
		int halvedMessageSize = messagePxSize / 2;
		int toCompensate = 154 - halvedMessageSize;
		int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
		int compensated = 0;
		StringBuilder sb = new StringBuilder();
		while (compensated < toCompensate)
		{
			sb.append(" ");
			compensated += spaceLength;
		}
		player.sendMessage(sb.toString() + message);
		if (toSendAfter != null) {
			sendCenteredMessage(player, toSendAfter);
		}
	}
    
    
}
