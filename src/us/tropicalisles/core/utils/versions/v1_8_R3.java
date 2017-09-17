package us.tropicalisles.core.utils.versions;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import us.tropicalisles.core.Main;
import us.tropicalisles.core.utils.GameProfileBuilder;
import us.tropicalisles.core.utils.NickAPI;
import us.tropicalisles.core.utils.NickMySQL;
import us.tropicalisles.core.utils.PlayerNickEvent;
import us.tropicalisles.core.utils.PlayerSkinResetEvent;
import us.tropicalisles.core.utils.PlayerSkinSetEvent;
import us.tropicalisles.core.utils.PlayerUnNickEvent;
import us.tropicalisles.core.utils.UUIDFetcher;
import us.tropicalisles.core.utils.UtilMethods;

public class v1_8_R3
        implements Listener
{
  private static Main pl;

  public v1_8_R3(Main main)
  {
    pl = main;
  }

  private static HashMap<Player, String> DefaultPermsPrefix = new HashMap();
  private static HashMap<Player, Double> health = new HashMap();
  private static HashMap<Player, Integer> food = new HashMap();
  private static HashMap<Player, Location> location = new HashMap();

  public static void setNickName(final UUID p, String nick, String nameprefix, String nametagprefix, String tablistprefix)
  {
    CraftPlayer cp = (CraftPlayer)Bukkit.getPlayer(p);

    Bukkit.getPluginManager().callEvent(new PlayerNickEvent(Bukkit.getPlayer(p), nick));
    if (NickAPI.NickedPlayerExists(p))
    {
      if (nick.length() <= 14)
      {
        UtilMethods.sendMessage(Bukkit.getPlayer(p), "You are now nicked as &6" + nick + "&7 with a random skin!");
        try
        {
          Main.getInstance().nameField.set(cp.getProfile(), nick);
        }
        catch (IllegalArgumentException|IllegalAccessException e)
        {
          UtilMethods.sendMessage(Bukkit.getPlayer(p), "An error happened. It is Riley's fault");
        }
        destroy(p);
        removeFromTablist(p);
        Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main.class), new Runnable()
        {
          public void run()
          {
            v1_8_R3.spawn(p);
            v1_8_R3.addToTablist(p);
            setRandomSkin(p, false);

          }
        }, 4L);

        Bukkit.getPlayer(p).setDisplayName("§7" + nick);
        Bukkit.getPlayer(p).setPlayerListName("§7" + nick);

        Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main.class), new Runnable()
        {
          public void run()
          {
            NickMySQL.update("UPDATE Nicked SET NICKNAME='" + nick + "' WHERE UUID='" + p + "'");
            NickMySQL.update("UPDATE Nicked SET NICKED='true' WHERE UUID='" + p + "'");
          }
        }, 2L);
      }
      else
      {
        UtilMethods.sendMessage(Bukkit.getPlayer(p), "The name you entered was too long. Try making it less than 14 characters.");
      }
    }
    else
    {
      Bukkit.broadcastMessage(" " + NickAPI.getRealName(p) + " " + NickAPI.getNickName(p));
      NickAPI.createNickedPlayer(p);
      setNickName(p, nick, nameprefix, nametagprefix, tablistprefix);
    }
  }

  public static void unNick(UUID p)
  {
    Bukkit.getPluginManager().callEvent(new PlayerUnNickEvent(Bukkit.getPlayer(p)));

    CraftPlayer cp = (CraftPlayer)Bukkit.getPlayer(p);
    if (NickAPI.NickedPlayerExists(p))
    {
      destroy(p);
      removeFromTablist(p);
      Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
      {
        public void run()
        {
          v1_8_R3.addToTablist(p);
          v1_8_R3.spawn(p);
        }
      }, 4L);

      NickMySQL.update("UPDATE Nicked SET NICKNAME='" + Bukkit.getPlayer(p).getName() + "' WHERE UUID='" + p + "'");
      NickMySQL.update("UPDATE Nicked SET NICKED='false' WHERE UUID='" + p + "'");

      Set<String> rank = UtilMethods.getGroupsForPlayer(Bukkit.getPlayer(p));
      try
      {
        if (rank.contains("developer"))
        {
          Bukkit.getPlayer(p).setDisplayName("§5§lDev §7" + Bukkit.getPlayer(p).getName());
          Bukkit.getPlayer(p).setPlayerListName("§5§lDeveloper §7" +NickAPI.getRealName(p));
          Main.getInstance().nameField.set(cp.getProfile(), "§7" + NickAPI.getRealName(p));
        }
        else if (rank.contains("admin"))
        {
          Bukkit.getPlayer(p).setDisplayName("§c§lAdmin §7" + Bukkit.getPlayer(p).getName());
          Bukkit.getPlayer(p).setPlayerListName("§c§lAdministrator §7" + NickAPI.getRealName(p));
          Main.getInstance().nameField.set(cp.getProfile(), "§7" + NickAPI.getRealName(p));
        }
        else if (rank.contains("owner"))
        {
          Bukkit.getPlayer(p).setDisplayName("§3§lOwner §7" + Bukkit.getPlayer(p).getName());
          Bukkit.getPlayer(p).setPlayerListName("§3§lOwner §7" + NickAPI.getRealName(p));
          Main.getInstance().nameField.set(cp.getProfile(), "§7" + NickAPI.getRealName(p));
        }
        else if (rank.contains("builder"))
        {

          Bukkit.getPlayer(p).setDisplayName("§6§lBuilder §7" + Bukkit.getPlayer(p).getName());
          Bukkit.getPlayer(p).setPlayerListName("§6§lBuilder §7" + NickAPI.getRealName(p));
          Main.getInstance().nameField.set(cp.getProfile(), "§7" + NickAPI.getRealName(p));
        }
        else if (rank.contains("mod"))
        {
          Bukkit.getPlayer(p).setDisplayName("§b§lMod §7" + Bukkit.getPlayer(p).getName());
          Bukkit.getPlayer(p).setPlayerListName("§b§lModerator §7" +NickAPI.getRealName(p));
          Main.getInstance().nameField.set(cp.getProfile(), "§7" + NickAPI.getRealName(p));
        }
        else if (rank.contains("helper"))
        {
          Bukkit.getPlayer(p).setDisplayName("§e§lHelper §7" + Bukkit.getPlayer(p).getName());
          Bukkit.getPlayer(p).setPlayerListName("§e§lHelper §7" + NickAPI.getRealName(p));
          Main.getInstance().nameField.set(cp.getProfile(), "§7" + NickAPI.getRealName(p));
        }
        else if (rank.contains("designer"))
        {
          Bukkit.getPlayer(p).setDisplayName("§d§lDesigner §7" + Bukkit.getPlayer(p).getName());
          Bukkit.getPlayer(p).setPlayerListName("§d§lDesigner §7" + NickAPI.getRealName(p));
          Main.getInstance().nameField.set(cp.getProfile(), "§7" + NickAPI.getRealName(p));
        }
        else if (rank.contains("manager"))
        {
          Bukkit.getPlayer(p).setDisplayName("§a§lManager §7" + Bukkit.getPlayer(p).getName());
          Bukkit.getPlayer(p).setPlayerListName("§a§lManager §7" + NickAPI.getRealName(p));
          Main.getInstance().nameField.set(cp.getProfile(), "§7" + NickAPI.getRealName(p));
        }
        else if (rank.contains("youtuber"))
        {
          Bukkit.getPlayer(p).setDisplayName("§c§lY§f§lT §7" + Bukkit.getPlayer(p).getName());
          Main.getInstance().nameField.set(cp.getProfile(), "§7" + NickAPI.getRealName(p));
          Bukkit.getPlayer(p).setPlayerListName("§c§lYou§f§lTuber §7" + NickAPI.getRealName(p));
        }
        if ((!rank.contains("helper")) &&
                (!rank.contains("mod")) &&
                (!rank.contains("trial-staff")) &&
                (!rank.contains("builder")) &&
                (!rank.contains("owner")) &&
                (!rank.contains("admin")) &&
                (!rank.contains("developer")) &&
                (!rank.contains("manager")) &&
                (!rank.contains("designer")) &&
                (!rank.contains("youtuber")))
        {
          Bukkit.getPlayer(p).setDisplayName("§7" + Bukkit.getPlayer(p).getName());
          Main.getInstance().nameField.set(cp.getProfile(), "§7" + NickAPI.getRealName(p));
          Bukkit.getPlayer(p).setPlayerListName("§7" + NickAPI.getRealName(p));



        }
      }
      catch (IllegalArgumentException|IllegalAccessException e)
      {
        Bukkit.broadcastMessage("broken");
      }

      setRandomSkin(p, true);

    }
    else
    {
      NickAPI.createNickedPlayer(p);
      unNick(p);
    }
  }

  public static void setSkin(UUID p, String pskin)
  {
    List skins = new ArrayList();
    skins.add(pskin);
    CraftPlayer cp = (CraftPlayer)Bukkit.getPlayer(p);
    GameProfile profile = GameProfileBuilder.fetch(UUIDFetcher.getUUID(pskin));
    Bukkit.getPluginManager().callEvent(new PlayerSkinSetEvent(Bukkit.getPlayer(p), pskin));
    Collection<Property> properties = profile.getProperties().get("textures");
    cp.getProfile().getProperties().removeAll("textures");
    cp.getProfile().getProperties().putAll("textures", properties);

      destroy(p);
      removeFromTablist(p);
      Bukkit.getScheduler().scheduleSyncDelayedTask(pl, new Runnable()
      {
        public void run()
        {
          v1_8_R3.addToTablist(p);
          v1_8_R3.spawn(p);
        }
      }, 4L);

  }

  public static void setRandomSkin(UUID p, boolean reset)
  {
    CraftPlayer cp = (CraftPlayer)Bukkit.getPlayer(p);
    GameProfile profile = cp.getProfile();
if(reset == false) {
  List skins = new ArrayList();
  skins.add("MakingMiners");
  skins.add("Oxro");
  skins.add("PopUps");
  skins.add("Duppy");
  skins.add("Steve");
  skins.add("Alex");
  skins.add("Christmas");
  skins.add("Susan");
  skins.add("Santa_Claus");
  Random r = new Random();
  int i = r.nextInt(skins.size());
  Bukkit.getPluginManager().callEvent(new PlayerSkinSetEvent(Bukkit.getPlayer(p), skins.get(i).toString()));
  profile = GameProfileBuilder.fetch(UUIDFetcher.getUUID(skins.get(i).toString()));
  Collection<Property> properties = profile.getProperties().get("textures");
  cp.getProfile().getProperties().removeAll("textures");
  cp.getProfile().getProperties().putAll("textures", properties);

  destroy(p);
  removeFromTablist(p);
  Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
    public void run() {
      v1_8_R3.addToTablist(p);
      v1_8_R3.spawn(p);
    }
  }, 4L);

}else{
  List skins = new ArrayList();
  skins.add(NickAPI.getRealName(p));
  Random r = new Random();
  int i = r.nextInt(skins.size());
  Bukkit.getPluginManager().callEvent(new PlayerSkinSetEvent(Bukkit.getPlayer(p), skins.get(i).toString()));
  profile = GameProfileBuilder.fetch(UUIDFetcher.getUUID(skins.get(i).toString()));
  Collection<Property> properties = profile.getProperties().get("textures");
  cp.getProfile().getProperties().removeAll("textures");
  cp.getProfile().getProperties().putAll("textures", properties);

  destroy(p);
  removeFromTablist(p);
  Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
    public void run() {
      v1_8_R3.addToTablist(p);
      v1_8_R3.spawn(p);
    }
  }, 4L);
}
  }

  public static void resetSkin(UUID p)
  {
    CraftPlayer cp = (CraftPlayer)Bukkit.getPlayer(p);
    GameProfile profile = cp.getProfile();
    Bukkit.getPluginManager().callEvent(new PlayerSkinResetEvent(Bukkit.getPlayer(p)));

      profile = GameProfileBuilder.fetch(UUIDFetcher.getUUID(NickAPI.getRealName(p)));
      Collection<Property> properties = profile.getProperties().get("textures");
      cp.getProfile().getProperties().removeAll("textures");
      cp.getProfile().getProperties().putAll("textures", properties);

      destroy(p);
      removeFromTablist(p);
      Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
      {
        public void run()
        {
          v1_8_R3.addToTablist(p);
          v1_8_R3.spawn(p);
        }
      }, 4L);

  }

  public static void sendActionBar(UUID p, String msg)
  {
    if (Bukkit.getPlayer(p) != null)
    {
      PlayerConnection Connection = ((CraftPlayer)Bukkit.getPlayer(p)).getHandle().playerConnection;
      IChatBaseComponent ABchat = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + msg + "\"}");
      PacketPlayOutChat ABpacket = new PacketPlayOutChat(ABchat, (byte)2);
      Connection.sendPacket(ABpacket);
    }
    else
    {
      NickAPI.endActionBar(p);
    }
  }

  public static void spawn(UUID p)
  {
    CraftPlayer cp = (CraftPlayer)Bukkit.getPlayer(p);
    if (Bukkit.getPlayer(p) != null) {
      for (Player all : Bukkit.getOnlinePlayers()) {
        if (!all.equals(Bukkit.getPlayer(p)))
        {
          PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(cp.getHandle());
          ((CraftPlayer)all).getHandle().playerConnection.sendPacket(spawn);
        }
      }
    }
  }

  public static void destroy(UUID p)
  {
    CraftPlayer cp = (CraftPlayer)Bukkit.getPlayer(p);
    PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(new int[] { cp.getEntityId() });
    sendPacket(destroy);
  }

  public static void addToTablist(UUID p)
  {
    CraftPlayer cp = (CraftPlayer)Bukkit.getPlayer(p);
    if (Bukkit.getPlayer(p) != null)
    {
      PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { cp.getHandle() });
      sendPacket(packet);
    }
  }

  public static void removeFromTablist(UUID p)
  {
    CraftPlayer cp = (CraftPlayer)Bukkit.getPlayer(p);
    PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[] { cp.getHandle() });
    sendPacket(packet);
  }

  public static void respawn(Player p)
  {
    ((CraftPlayer)p).getHandle().playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
  }

  public static void sendPacket(Packet<?> packet)
  {
    for (Player all : Bukkit.getOnlinePlayers()) {
      ((CraftPlayer)all).getHandle().playerConnection.sendPacket(packet);
    }
  }
}
