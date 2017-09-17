package us.tropicalisles.core.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import us.tropicalisles.core.Main;
import us.tropicalisles.core.event.Listeners;
import us.tropicalisles.core.utils.UtilMethods;

import java.util.Arrays;

public class Transporter
		implements Listener
{

	public static void serverTransporter(Player p)
	{

		Inventory i = Bukkit.createInventory(null, 54, "§8§nTransporter");
		ItemStack a = new ItemStack(Material.DIAMOND_SWORD);
		ItemStack b = new ItemStack(Material.BOAT);
		ItemStack c = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemStack d= new ItemStack(Material.RAILS);
		ItemStack e= new ItemStack(Material.SIGN);
		ItemStack f= new ItemStack(Material.EYE_OF_ENDER);
		ItemStack l= new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack g= new ItemStack(Material.SKULL_ITEM, 1, (short)3);

		ItemMeta am = a.getItemMeta();
		ItemMeta bm = b.getItemMeta();
		ItemMeta cm = c.getItemMeta();
		ItemMeta dm = d.getItemMeta();
		ItemMeta em = e.getItemMeta();
		ItemMeta fm = f.getItemMeta();
		ItemMeta lm = l.getItemMeta();
		SkullMeta gm = (SkullMeta) g.getItemMeta();


		bm.addEnchant(Enchantment.DURABILITY, 10, true);
		bm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		bm.setDisplayName("§a§lARCADE");
		bm.setLore(
				Arrays.asList(" ",
						"§7Travel to the arcade where you can ",
						"§7play our custom minigames, such",
						"§7as Skywars, Model Mayhem and more. ",
						"§7Buy perks, kits and more!",
						"§8Featuring famous minigames!",
						" ",
						"§eThis gametype is currently under development!"));


		b.setItemMeta(bm);

		am.addEnchant(Enchantment.DURABILITY, 10, true);
		am.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am.setDisplayName("§e§lFACTIONS");
		am.setLore(
				Arrays.asList(" ",
						"§7Travel to the factions where you ",
						"§7have to setup or join a faction/gang. ",
						"§7You can either be a leader or a member, ",
						"§7where you raid other people's bases!",
						"§8Featuring custom features!",
						" ",
						"§eThis gametype is currently under development!"));

		a.setItemMeta(am);

		lm.addEnchant(Enchantment.DURABILITY, 10, true);
		lm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		lm.setDisplayName("§9§lKITPVP");
		lm.setLore(
				Arrays.asList(" ",
						"§7Travel to the factions where you ",
						"§7have to setup or join a faction/gang. ",
						"§7You can either be a leader or a member, ",
						"§7where you raid other people's bases!",
						"§8Featuring custom features!",
						" ",
						"§eClick to connect to this gamemode!"));

		l.setItemMeta(lm);

		cm.addEnchant(Enchantment.DURABILITY, 10, true);
		cm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		cm.setDisplayName("§b§lPRISON");
		cm.setLore(
				Arrays.asList(" ",
						"§7Travel to the prison where you ",
						"§7have to mine to keep your reputation. ",
						"§7Visit other islands and complete quests. ",
						"§7Battle the pirates to take over the island.",
						"§8Will you mine enough blocks!",
						" ",
						"§eThis gametype is currently under development!"));

		c.setItemMeta(cm);

		dm.addEnchant(Enchantment.DURABILITY, 10, true);
		dm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		dm.setDisplayName("§6§lSKYBLOCK");
		dm.setLore(
				Arrays.asList(" ",
						"§7Travel to the skyblock where you ",
						"§7have to survive on your own or with ",
						"§7a friend in the sky! Buy and sell things ",
						"§7but be careful who you trade with!",
						"§8Want to play multiplayer? You can!",
						" ",
						"§eClick to connect to this gamemode!"));

		d.setItemMeta(dm);

		em.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		em.setDisplayName("§d§lPARTY");
		em.setLore(
				Arrays.asList(" ",
						"§7Create a party with your friends ",
						"§7and they will join any server/game you join!",
						" ",
						"§eClick to create or add members to your party!"));

		e.setItemMeta(em);

		fm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		fm.setDisplayName("§6§lHUB");
		fm.setLore(
				Arrays.asList(" ",
						"§7Return to the Hub server! ",
						"§7",
						" ",
						"§eClick to return to the Hub!"));

		f.setItemMeta(fm);

		if(Listeners.skulls != null) {
			for (net.minecraft.server.v1_8_R3.ItemStack is : Listeners.skulls) {
				org.bukkit.inventory.ItemStack is1 = CraftItemStack.asCraftMirror(is);
				if (is1.hasItemMeta()) {
					if (is1.getItemMeta().hasDisplayName()) {
						if (is1.getItemMeta().getDisplayName().contains("FRIENDS")) {
							i.setItem(50, is1);
							break;
						}
					}
				}
			}
		}else{
			gm.setOwner("MHF_Question");
			gm.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
			gm.setDisplayName("§e§lFRIENDS");
			gm.setLore(
					Arrays.asList(new String[] { " ", "§7Friend people using our ", "§7Friend system! They will get", "§7notified upon your join, and more!", "§8Right click to get started!", " ", "§eClick to open up your Friend Menu!" }));

			g.setItemMeta(gm);
			i.setItem(50, g);

		}
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
		ItemMeta gMm = glass.getItemMeta();
		gMm.setDisplayName(" ");
		gMm.setLore(Arrays.asList(" "));
		glass.setItemMeta(gMm);
		i.setItem(11, glass);
		i.setItem(10, glass);
		i.setItem(15, glass);
		i.setItem(16, glass);


		i.setItem(2, glass);
		i.setItem(3, glass);
		i.setItem(4, glass);
		i.setItem(5, glass);
		i.setItem(6, glass);
		i.setItem(20, glass);
		i.setItem(19, glass);
		i.setItem(21, glass);
		i.setItem(23, glass);
		i.setItem(24, glass);
		i.setItem(25, glass);
		i.setItem(29, glass);
		i.setItem(39, glass);
		i.setItem(40, glass);
		i.setItem(38, glass);
		i.setItem(41, glass);
		i.setItem(42, glass);
		i.setItem(28, glass);
		i.setItem(34, glass);
		i.setItem(33, glass);

		//ITEMS:

		i.setItem(22, b);
		i.setItem(21, a);
		i.setItem(23, c);
		i.setItem(31, d);
		i.setItem(13, l);

		i.setItem(48, f);
		i.setItem(49, e);
		p.openInventory(i);
	}

	public static void playerSettings(Player p)
	{
		Inventory i = Bukkit.createInventory(null, 27, "§8§nPlayer Settings");
		ItemStack a = new ItemStack(Material.INK_SACK, 1, (short) 1);
		ItemStack b = new ItemStack(Material.INK_SACK, 1, (short) 1);
		ItemStack c = new ItemStack(Material.INK_SACK, 1, (short) 10);
		ItemStack d = new ItemStack(Material.INK_SACK, 1, (short) 10);


		ItemMeta am = a.getItemMeta();
		ItemMeta bm = b.getItemMeta();
		ItemMeta cm = c.getItemMeta();
		ItemMeta dm = d.getItemMeta();



		bm.addEnchant(Enchantment.DURABILITY, 10, true);
		bm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		bm.setDisplayName("§c§lDisable Speed");


		b.setItemMeta(bm);

		cm.addEnchant(Enchantment.DURABILITY, 10, true);
		cm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		cm.setDisplayName("§a§lEnable Speed");


		c.setItemMeta(cm);

		am.addEnchant(Enchantment.DURABILITY, 10, true);
		am.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am.setDisplayName("§c§lVanish Players");


		a.setItemMeta(am);

		dm.addEnchant(Enchantment.DURABILITY, 10, true);
		dm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		dm.setDisplayName("§a§lReveal Players");


		d.setItemMeta(dm);


		//ITEMS:

		i.setItem(11, a);
		i.setItem(20, d);
		i.setItem(15, b);
		i.setItem(24, c);

		p.openInventory(i);
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("Transporter")) return;
		if (e.getCurrentItem().getItemMeta() == null) return;


		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("ARCADE")) {
			e.setCancelled(true);

			Player player = (Player)e.getWhoClicked();
			e.getWhoClicked().closeInventory();
			UtilMethods.sendMessage(player, "This gamemode is currently under development!");
		}

	}



	@EventHandler
	public void onInventoryClick1(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("Transporter")) return;
		if (e.getCurrentItem().getItemMeta() == null) return;


		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("FACTIONS")) {
			e.setCancelled(true);

			Player player = (Player)e.getWhoClicked();
			e.getWhoClicked().closeInventory();
			UtilMethods.sendMessage(player, "This gamemode is currently under development!");
		}

	}

	@EventHandler
	public void onInventoryClick2(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("Transporter")) return;
		if (e.getCurrentItem().getItemMeta() == null) return;


		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("SKYBLOCK")) {
			e.setCancelled(true);


			Player player = (Player)e.getWhoClicked();
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("skyblock");
			player.sendPluginMessage(JavaPlugin.getPlugin(Main.class), "BungeeCord", out.toByteArray());
			e.getWhoClicked().closeInventory();
			UtilMethods.sendMessage(player, "Sending to §6Skyblock&7...");
		}

	}

	@EventHandler
	public void onInventoryClick8(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("Transporter")) return;
		if (e.getCurrentItem().getItemMeta() == null) return;


		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("KITPVP")) {
			e.setCancelled(true);


			Player player = (Player)e.getWhoClicked();
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("kitpvp");
			player.sendPluginMessage(JavaPlugin.getPlugin(Main.class), "BungeeCord", out.toByteArray());
			e.getWhoClicked().closeInventory();
			UtilMethods.sendMessage(player, "Sending to §6KitPVP&7...");
		}

	}

	@EventHandler
	public void onInventoryClick3(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("Transporter")) return;
		if (e.getCurrentItem().getItemMeta() == null) return;


		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("PRISON")) {
			e.setCancelled(true);

			Player player = (Player)e.getWhoClicked();
			e.getWhoClicked().closeInventory();
			UtilMethods.sendMessage(player, "This gametype is currently under development!");
		}

	}

	@EventHandler
	public void onInventoryClick4(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("Transporter")) return;
		if (e.getCurrentItem().getItemMeta() == null) return;


		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("PARTY")) {
			e.setCancelled(true);

			Player player = (Player)e.getWhoClicked();
			e.getWhoClicked().closeInventory();
			UtilMethods.sendMessage(player, "Partying players is currently under development!");
		}

	}

	@EventHandler
	public void onInventoryClick5(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("Transporter")) return;
		if (e.getCurrentItem().getItemMeta() == null) return;


		if (e.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)) {
			e.setCancelled(true);

			Player player = (Player)e.getWhoClicked();
			e.getWhoClicked().closeInventory();

		}

	}
	@EventHandler
	public void onInventoryClick6(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("Transporter")) return;
		if (e.getCurrentItem().getItemMeta() == null) return;


		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("FRIENDS")) {
			e.setCancelled(true);

			Player player = (Player)e.getWhoClicked();
			e.getWhoClicked().closeInventory();
			UtilMethods.sendMessage(player, "Friending players is currently under development!");
		}

	}

	@EventHandler
	public void onInventoryClick7(InventoryClickEvent e) {
		if (!e.getInventory().getName().contains("Transporter")) return;
		if (e.getCurrentItem().getItemMeta() == null) return;
		Player player = (Player) e.getWhoClicked();

		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("HUB")) {
			e.setCancelled(true);
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("Connect");
			out.writeUTF("hub");
			player.sendPluginMessage(JavaPlugin.getPlugin(Main.class), "BungeeCord", out.toByteArray());
			e.getWhoClicked().closeInventory();
			UtilMethods.sendMessage(player, "Sending to §6Hub&7...");
		}

	}


}
