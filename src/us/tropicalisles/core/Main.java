package us.tropicalisles.core;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Lists;

import org.tyrannyofheaven.bukkit.zPermissions.ZPermissionsService;
import us.tropicalisles.core.commands.*;
import us.tropicalisles.core.event.ChatFormat;
import us.tropicalisles.core.event.CommandBypass;
import us.tropicalisles.core.event.Listeners;
import us.tropicalisles.core.utils.NickAPI;
import us.tropicalisles.core.utils.NickMySQL;
import us.tropicalisles.core.utils.Transporter;
import us.tropicalisles.core.utils.UtilMethods;
import us.tropicalisles.core.utils.versions.v1_8_R3;

public class Main extends JavaPlugin implements Listener
{
//	public static Economy econ = null;
	public static  Main m;
	public static String server;
	public static Integer playerCount;
	public static boolean whiteListEnabled;
	public Logger log = getLogger();
	public Field nameField;
	public ArrayList<String> nickedPlayers = new ArrayList();

public void onEnable()
{
	NickMySQL.connectNickSQL();
	NickMySQL.connect();
	NickMySQL.createTable();

	m = this;
	File file = new File(JavaPlugin.getPlugin(Main.class).getDataFolder(), "whitelist.yml");
	FileConfiguration filez = YamlConfiguration.loadConfiguration(file);
	Main.whiteListEnabled = filez.getBoolean("whitelist.on");

	
	  getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	PluginManager pm = Bukkit.getPluginManager();
	ZPermissionsService service = null;
	try {
		service = Bukkit.getServicesManager().load(ZPermissionsService.class);
	}
	catch (NoClassDefFoundError e) {
		// Eh...
	}
	if (service == null) {
		// zPermissions not found, do something else
	}
	System.out.print("§eTropicalIsles Core was successfully enabled for SERVER!");
	
	
	 Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable()
	    {
	
	      public void run()
	      {
	          Iterator localIterator;
	     	 Player localPlayer;
	      
	    	  for (localIterator = Main.this.getOnlinePlayers().iterator(); localIterator.hasNext();)
	            {
	    		  localPlayer = (Player)localIterator.next();

	            }
	     
	      }
	    }, 20L);
	Bukkit.getServer().broadcastMessage("§7§o[" + "CONSOLE" + "§7: §aProcessing..§7§o]");
	Bukkit.getServer().broadcastMessage("§7§o[" + "CONSOLE" + "§7: §aData Upload Complete§7§o]");
	Bukkit.getServer().broadcastMessage("§7§o[" + "CONSOLE" + "§7: §aRank Load Complete§7§o]");
	Bukkit.getServer().broadcastMessage("§7§o[" + "CONSOLE" + "§7: §aUpdate Complete§7§o]");
	Bukkit.getServer().broadcastMessage("§7§o[" + "CONSOLE" + "§7: §aPlease wait until reload completes to use commands§7§o]");

	 pm.registerEvents(this, this);
	 pm.registerEvents(new Listeners(), this);
	 pm.registerEvents(new Transporter(), this);
	 pm.registerEvents(new ChatFormat(), this);
	 pm.registerEvents(new CommandBypass(), this);

	getCommand("gm").setExecutor(new GameModeCommand());
	getCommand("tp").setExecutor(new Teleport());
	getCommand("heal").setExecutor(new Heal());
	getCommand("hubreload").setExecutor(new HubReload());
	getCommand("ping").setExecutor(new Ping());
	getCommand("tpall").setExecutor(new TeleportAll());
	getCommand("top").setExecutor(new Top());
	getCommand("fly").setExecutor(new Fly());
	getCommand("damage").setExecutor(new noDamage());
	getCommand("ci").setExecutor(new CI());
	getCommand("server").setExecutor(new Open());
	getCommand("setrank").setExecutor(new SetRank());
	getCommand("whitelist").setExecutor(new WhitelistCommand());


	this.nameField = getField(GameProfile.class, "name");

	
	for(Player player : Bukkit.getOnlinePlayers())
	{
		Main.sendPlayerListTab(player, "§6§lTropical Isles", "\n§7Currently connected to §6SERVERNAME!\n§6play.TropicalIsles.us\n");

	}
}

public void onDisable()

{
	System.out.print("§eTropicalIsles Core was successfully disabled for SERVER!");

	for(Player player : Bukkit.getOnlinePlayers())
	{
		if(NickAPI.isNicked(player.getUniqueId()))
		{
			v1_8_R3.unNick(player.getUniqueId());
		}
	}

	noDamage.damage.clear();
}


	public static void sendPlayerListTab(Player player, String header, String footer)
	{
		CraftPlayer craftplayer = (CraftPlayer)player;
		PlayerConnection connection =
				craftplayer.getHandle().playerConnection;
		IChatBaseComponent hj = IChatBaseComponent.ChatSerializer.a(ChatColor.translateAlternateColorCodes('&', "{text: '" + header + "'}"));
		IChatBaseComponent fj = IChatBaseComponent.ChatSerializer.a(ChatColor.translateAlternateColorCodes('&', "{text: '" + footer + "'}"));
		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		try
		{
			Field headerField = packet.getClass().getDeclaredField("a");
			headerField.setAccessible(true);
			headerField.set(packet, hj);
			headerField.setAccessible(!headerField.isAccessible());

			Field footerField = packet.getClass().getDeclaredField("b");
			footerField.setAccessible(true);
			footerField.set(packet, fj);
			footerField.setAccessible(!footerField.isAccessible());
		}
		catch (Exception localException) {}
		connection.sendPacket(packet);
	}



	public static Field getField(Class<?> clazz, String name)
	{
		try
		{
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		}
		catch (NoSuchFieldException|SecurityException e)
		{
			e.printStackTrace();
		}
		return null;
	}

public List<Player> getOnlinePlayers()
{
  ArrayList localArrayList = Lists.newArrayList();
  for (World localWorld : Bukkit.getWorlds()) {
    localArrayList.addAll(localWorld.getPlayers());
  }
  return Collections.unmodifiableList(localArrayList);
}

public static Main getInstance()
{
	return m;
}

}



