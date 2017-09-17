package us.tropicalisles.core;

import java.io.IOException;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import us.tropicalisles.core.utils.UtilMethods;
import org.bukkit.plugin.java.JavaPlugin;


public class Event implements Listener
{


	@EventHandler
	public void onJoin(PlayerJoinEvent e) throws IOException
	{
		
		
		         Player p = e.getPlayer();

		         p.sendMessage("§7§oOpening message for gamemode"); // Join message for all servers
		         p.sendMessage("§6§lGame§3§lMode!"); // ^
				Main.sendPlayerListTab(e.getPlayer(), "\n§6Tropical Isles\n§7       Home to custom gamemodes, minigames and more!", "§7Currently connected to §6SERVERNAME!\n§6play.TropicalIsles.us\n");






	}
	
	@EventHandler
	public void onJoin(PlayerQuitEvent e) throws IOException
	{
		
		
		         Player p = e.getPlayer();
				e.setQuitMessage(null);


		}
		    	  
	private final static int CENTER_PX = 154;
	 
	private final static int MAX_PX = 350;
	
	public static void sendCenteredMessage(Player player, String message){
		message = ChatColor.translateAlternateColorCodes('&', message);
		int messagePxSize = 0;
		boolean previousCode = false;
		boolean isBold = false;
		int charIndex = 0;
		int lastSpaceIndex = 0;
		String toSendAfter = null;
		String recentColorCode = "";
		for(char c : message.toCharArray()){
			if(c == '§'){
				previousCode = true;
				continue;
			}else if(previousCode == true){
				previousCode = false;
				recentColorCode = "§" + c;
				if(c == 'l' || c == 'L'){
					isBold = true;
					continue;
				}else isBold = false;
			}else if(c == ' ') lastSpaceIndex = charIndex;
			else{
				DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
				messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
				messagePxSize++;
			}
			if(messagePxSize >= MAX_PX){
				toSendAfter = recentColorCode + message.substring(lastSpaceIndex + 1, message.length());
				message = message.substring(0, lastSpaceIndex + 1);
				break;
			}
			charIndex++;
		}
		int halvedMessageSize = messagePxSize / 2;
		int toCompensate = CENTER_PX - halvedMessageSize;
		int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
		int compensated = 0;
		StringBuilder sb = new StringBuilder();
		while(compensated < toCompensate){
			sb.append(" ");
			compensated += spaceLength;
		}
		player.sendMessage(sb.toString() + message);
		if(toSendAfter != null) sendCenteredMessage(player, toSendAfter);
	}
	


	    @EventHandler
		  public void onChange(PlayerInteractEvent e)
		  {
		    Player p = e.getPlayer();
		    if (e.getClickedBlock() != null) {
		      if (e.getAction() == Action.RIGHT_CLICK_BLOCK)
		      {
		        Block b = e.getClickedBlock();
		        if ((b.getType() == Material.WALL_SIGN) || (b.getType() == Material.SIGN_POST) || (b.getType() == Material.SIGN))
		        {
		          Sign sign = (Sign)b.getState();
		          if (sign.getLine(0).contains("SB")) {
		            if (sign.getLine(1).contains("Pollution")) {
		               
		            	  ByteArrayDataOutput out = ByteStreams.newDataOutput();
		                  out.writeUTF("Connect");
		                  out.writeUTF("sb1"); 
		              p.sendPluginMessage(JavaPlugin.getPlugin(Main.class), "BungeeCord", out.toByteArray());
		            
		               
		              }
		            }
		          }
	    
	  
}
		      }
		    }
	    

	    @EventHandler
		  public void onChange2(PlayerInteractEvent e)
		  {
		    Player p = e.getPlayer();
		    if (e.getClickedBlock() != null) {
		      if (e.getAction() == Action.RIGHT_CLICK_BLOCK)
		      {
		        Block b = e.getClickedBlock();
		        if ((b.getType() == Material.WALL_SIGN) || (b.getType() == Material.SIGN_POST) || (b.getType() == Material.SIGN))
		        {
		          Sign sign = (Sign)b.getState();
		          if (sign.getLine(0).contains("SB")) {
		            if (sign.getLine(1).contains("Seaweed")) {
		               
		            	  ByteArrayDataOutput out = ByteStreams.newDataOutput();
		                  out.writeUTF("Connect");
		                  out.writeUTF("sb2"); 
		              p.sendPluginMessage(JavaPlugin.getPlugin(Main.class), "BungeeCord", out.toByteArray());
		            
		               
		              }
		            }
		          }
	    
	  
}
		      }
		    }
	    

	    @EventHandler
		  public void onChange3(PlayerInteractEvent e)
		  {
		    Player p = e.getPlayer();
		    if (e.getClickedBlock() != null) {
		      if (e.getAction() == Action.RIGHT_CLICK_BLOCK)
		      {
		        Block b = e.getClickedBlock();
		        if ((b.getType() == Material.WALL_SIGN) || (b.getType() == Material.SIGN_POST) || (b.getType() == Material.SIGN))
		        {
		          Sign sign = (Sign)b.getState();
		          if (sign.getLine(0).contains("SB")) {
		            if (sign.getLine(1).contains("Camp")) {
		               
		            	  ByteArrayDataOutput out = ByteStreams.newDataOutput();
		                  out.writeUTF("Connect");
		                  out.writeUTF("sb3"); 
		              p.sendPluginMessage(JavaPlugin.getPlugin(Main.class), "BungeeCord", out.toByteArray());
		            
		               
		              }
		            }
		          }
	    
	  
}
		      }
		    }

}
