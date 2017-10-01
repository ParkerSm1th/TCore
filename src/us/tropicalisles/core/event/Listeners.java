package us.tropicalisles.core.event;

import java.io.File;
import java.io.IOException;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;
import us.tropicalisles.core.Main;

import java.util.*;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import us.tropicalisles.core.commands.Vanish;
import us.tropicalisles.core.commands.noDamage;
import us.tropicalisles.core.utils.NickAPI;
import us.tropicalisles.core.utils.UtilMethods;
import us.tropicalisles.core.utils.versions.v1_8_R3;

public class Listeners implements Listener
{

	public static ArrayList<net.minecraft.server.v1_8_R3.ItemStack> skulls;


	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerLogin(PlayerLoginEvent e)
	{
		String ip = e.getHostname().toString().split(":")[0];
		int port = Integer.parseInt(e.getHostname().toString().split(":")[1]);

		File file = new File(((Main)JavaPlugin.getPlugin(Main.class)).getDataFolder(), "whitelist.yml");
		FileConfiguration filez = YamlConfiguration.loadConfiguration(file);
		List<String> whiteList = filez.getStringList("whitelist.players");
		boolean whiteListEnabled = filez.getBoolean("whitelist.on");
		if ((ip.contains("play.tropicalisles.us")) && (port == 25565))
		{
			if (whiteListEnabled == true)
			{
				if (whiteList.contains(e.getPlayer().getUniqueId().toString())) {
					e.allow();
				} else {
					e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§7'You are not whitelisted on this server!'");
				}
			}
			else {
				e.allow();
			}
		}
		else {
			e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§7'Failed to authenticate your connection!'");
		}
	}


	@EventHandler
   public void onDamage(EntityDamageEvent e){
   	
	
		Player player = (Player) e.getEntity();


		if (noDamage.damage.contains(player.getName())) {
			e.setCancelled(true);
		}


	}



	public void spawn(Player p){
		net.minecraft.server.v1_8_R3.World w = ((CraftWorld) p.getWorld()).getHandle();
		PlayerInteractManager interact = new PlayerInteractManager(w);
		Custom c = new Custom(MinecraftServer.getServer(), ((CraftWorld) p.getWorld()).getHandle(),
				new GameProfile(UUID.randomUUID(), "Test"), interact);
		Location loc = p.getLocation();
		c.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		c.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		c.setInvisible(false);
		c.teleportTo(loc, false);
		c.playerConnection = new PlayerConnection(MinecraftServer.getServer(),
				new NetworkManager(EnumProtocolDirection.CLIENTBOUND), c);
	//	w.addEntity(c, CreatureSpawnEvent.SpawnReason.CUSTOM);


	}
	public class Custom extends EntityPlayer {

		public Custom(MinecraftServer srv, WorldServer world, GameProfile game, PlayerInteractManager interact) {
			super(srv, world, game, interact);
		}
	}



	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		final Player player = e.getPlayer();


		if(player.getName().equalsIgnoreCase("Spoogo"))
		{
		player.sendMessage("[DEPLOY] " + Bukkit.getVersion());
		}


			Set<String> rank = UtilMethods.getGroupsForPlayer(player);

			if(rank.contains("developer"))
			{
				player.setPlayerListName("§5§lDeveloper §7" + player.getName());

			}else if(rank.contains("admin"))
			{
				player.setPlayerListName("§c§lAdmin §7" + player.getName());

			}else if(rank.contains("owner"))
			{
				player.setPlayerListName("§3§lOwner §7" + player.getName());

			}else if(rank.contains("builder"))
			{
				player.setPlayerListName("§6§lBuilder §7" + player.getName());

			}else if(rank.contains("mod"))
			{
				player.setPlayerListName("§b§lMod §7" + player.getName());

			}else if(rank.contains("helper"))
			{
				player.setPlayerListName("§e§lHelper §7" + player.getName());

			}else if(rank.contains("designer"))
			{
				player.setPlayerListName("§d§lDesigner §7" + player.getName());

			}else if(rank.contains("manager"))
			{
				player.setPlayerListName("§a§lManager §7" + player.getName());
			}else if(rank.contains("trial-staff"))
			{
				player.setPlayerListName("§e§lTrial-Staff §7" + player.getName());
			}else if(rank.contains("youtuber"))
			{
				player.setPlayerListName("§c§lYou§f§lTube §7" + player.getName());
			}
			if(!rank.contains("helper")
					&& !rank.contains("mod")
					&& !rank.contains("trial-staff")
					&& !rank.contains("builder")
					&& !rank.contains("owner")
					&& !rank.contains("admin")
					&& !rank.contains("developer")
					&& !rank.contains("manager")
					&& !rank.contains("designer")
					&& !rank.contains("youtuber"))
			{


				player.setPlayerListName("§7" + player.getName());

			}




		player.setGameMode(GameMode.SURVIVAL);

		e.setJoinMessage(null);



		skulls = new ArrayList();

		org.bukkit.inventory.ItemStack s = new org.bukkit.inventory.ItemStack(Material.SKULL_ITEM, 1, (short)3);
		SkullMeta meta = (SkullMeta)s.getItemMeta();
		meta.setDisplayName("§e§lFRIENDS");
		meta.setLore(	Arrays.asList(" ",
				"§7Friend people using our ",
				"§7Friend system! They will get",
				"§7notified upon your join, and more!",
				"§8Right click to get started!",
				" ",
				"§eClick to open up your Friend Menu!"));
		meta.setOwner("MHF_QUESTION");
		s.setItemMeta(meta);

		skulls.add(CraftItemStack.asNMSCopy(s));





	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e)
	{
		e.setQuitMessage(null);

		if(noDamage.damage.contains(e.getPlayer().getName()))
		{
			noDamage.damage.remove(e.getPlayer().getName());
		}





	}

	@EventHandler(priority=EventPriority.HIGHEST)

	  public void onWeatherChange(WeatherChangeEvent event) {

	         boolean rain = event.toWeatherState();
	         if(rain)
	             event.setCancelled(true);
	     }


 @EventHandler(priority=EventPriority.HIGHEST)
   public void onThunderChange(ThunderChangeEvent event) {
     
       boolean storm = event.toThunderState();
       if(storm)
           event.setCancelled(true);
   }

 @EventHandler
 public void onDeath(PlayerDeathEvent e)
 {
	 e.setDeathMessage(null);
 }

}
 
	

