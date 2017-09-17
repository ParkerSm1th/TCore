package us.tropicalisles.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import us.tropicalisles.core.Main;
import us.tropicalisles.core.utils.versions.v1_8_R3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class NickAPI
implements Listener
{

    private static HashMap<Player, Integer> taskID = new HashMap();
    private static Main pl;

    public NickAPI(Main main)
    {
        pl = main;
    }

    public static String getNickName(UUID p)
    {
        String name = "";
            try
            {
                ResultSet rs = NickMySQL.Result("SELECT NICKNAME FROM Nicked WHERE UUID='" + p + "'");
                if ((rs.next()) && (rs.getString("NICKNAME") == null)) {}
                name = rs.getString("NICKNAME");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        return name;
    }

    public static String getRealName(UUID p)
    {
        String name = "";
            try
            {
                ResultSet rs = NickMySQL.Result("SELECT NAME FROM Nicked WHERE UUID='" + p + "'");
                if ((rs.next()) && (rs.getString("NAME") == null)) {}
                name = rs.getString("NAME");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        return name;
    }

    public static boolean NickedPlayerExists(UUID p)
    {
        boolean exists = false;
            try
            {
                ResultSet rs = NickMySQL.Result("SELECT NAME FROM Nicked WHERE UUID='" + p + "'");
                if (rs.next()) {
                    exists = true;
                } else {
                    exists = false;
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        return exists;
    }


    public static void createNickedPlayer(UUID p)
    {
        if (!NickedPlayerExists(p)) {

         //   String s = getRealName(sender.getUniqueId());
         //   if (s != null) {
       //         Bukkit.broadcastMessage("NAME : " + s);
      //          NickMySQL.update("INSERT INTO Nicked (UUID, NAME, NICKNAME, NICKED) VALUES ('" + sender.getUniqueId() + "', '" + s + "', 'NONE', 'false');");
     //       }
//else{
   //            Bukkit.broadcastMessage(":");
                NickMySQL.update("INSERT INTO Nicked (UUID, NAME, NICKNAME, NICKED) VALUES ('" + p + "', '" + Bukkit.getPlayer(p).getName() + "', '" +Bukkit.getPlayer(p).getName()  + "', 'false');");
        //    }
            }

        }



    public static void setNickName(UUID p, String nick, String nameprefix, String nametagprefix, String tablistprefix)
    {
        if (Bukkit.getVersion().contains("(MC: 1.8.3)")) {
   //         v1_8_R2.setNickName(p, nick, nameprefix, nametagprefix, tablistprefix);
            Bukkit.broadcastMessage("1.8.3");
        }
        if ((Bukkit.getVersion().contains("(MC: 1.8.4)")) || (Bukkit.getVersion().contains("(MC: 1.8.5)")) || (Bukkit.getVersion().contains("(MC: 1.8.6)")) || (Bukkit.getVersion().contains("(MC: 1.8.7)")) || (Bukkit.getVersion().contains("(MC: 1.8.8)"))) {
            v1_8_R3.setNickName(p, nick, nameprefix, nametagprefix, tablistprefix);
        }
      /*  if ((Bukkit.getVersion().contains("(MC: 1.9)")) || (Bukkit.getVersion().contains("(MC: 1.9.1)")) || (Bukkit.getVersion().contains("(MC: 1.9.2)")) || (Bukkit.getVersion().contains("(MC: 1.9.3)"))) {
            v1_9_R1.setNickName(p, nick, nameprefix, nametagprefix, tablistprefix);
        }
        if (Bukkit.getVersion().contains("(MC: 1.9.4)")) {
            v1_9_R2.setNickName(p, nick, nameprefix, nametagprefix, tablistprefix);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.10)")) || (Bukkit.getVersion().contains("(MC: 1.10.1)")) || (Bukkit.getVersion().contains("(MC: 1.10.2)"))) {
            v1_10_R1.setNickName(p, nick, nameprefix, nametagprefix, tablistprefix);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.11)")) || (Bukkit.getVersion().contains("(MC: 1.11.1)")) || (Bukkit.getVersion().contains("(MC: 1.11.2)"))) {
            v1_11_R1.setNickName(p, nick, nameprefix, nametagprefix, tablistprefix);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.12)")) || (Bukkit.getVersion().contains("(MC: 1.12.1)"))) {
            v1_12_R1.setNickName(p, nick, nameprefix, nametagprefix, tablistprefix);
        }
    }

    public static void setRandomNickName(UUID p, String nameprefix, String nametagprefix, String tablistprefix)
    {
        if (Bukkit.getVersion().contains("(MC: 1.8.3)")) {
            v1_8_R2.setRandomNickName(p, nameprefix, nametagprefix, tablistprefix);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.8.4)")) || (Bukkit.getVersion().contains("(MC: 1.8.5)")) || (Bukkit.getVersion().contains("(MC: 1.8.6)")) || (Bukkit.getVersion().contains("(MC: 1.8.7)")) || (Bukkit.getVersion().contains("(MC: 1.8.8)"))) {
            v1_8_R3.setRandomNickName(p, nameprefix, nametagprefix, tablistprefix);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.9)")) || (Bukkit.getVersion().contains("(MC: 1.9.1)")) || (Bukkit.getVersion().contains("(MC: 1.9.2)")) || (Bukkit.getVersion().contains("(MC: 1.9.3)"))) {
            v1_9_R1.setRandomNickName(p, nameprefix, nametagprefix, tablistprefix);
        }
        if (Bukkit.getVersion().contains("(MC: 1.9.4)")) {
            v1_9_R2.setRandomNickName(p, nameprefix, nametagprefix, tablistprefix);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.10)")) || (Bukkit.getVersion().contains("(MC: 1.10.1)")) || (Bukkit.getVersion().contains("(MC: 1.10.2)"))) {
            v1_10_R1.setRandomNickName(p, nameprefix, nametagprefix, tablistprefix);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.11)")) || (Bukkit.getVersion().contains("(MC: 1.11.1)")) || (Bukkit.getVersion().contains("(MC: 1.11.2)"))) {
            v1_11_R1.setRandomNickName(p, nameprefix, nametagprefix, tablistprefix);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.12)")) || (Bukkit.getVersion().contains("(MC: 1.12.1)"))) {
            v1_12_R1.setRandomNickName(p, nameprefix, nametagprefix, tablistprefix);
        }*/
    }

    public static void UnNick(UUID p)
    {
        if (Bukkit.getVersion().contains("(MC: 1.8.3)")) {
     //       v1_8_R2.UnNick(p);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.8.4)")) || (Bukkit.getVersion().contains("(MC: 1.8.5)")) || (Bukkit.getVersion().contains("(MC: 1.8.6)")) || (Bukkit.getVersion().contains("(MC: 1.8.7)")) || (Bukkit.getVersion().contains("(MC: 1.8.8)"))) {
            v1_8_R3.unNick(p);
        }
        /*if ((Bukkit.getVersion().contains("(MC: 1.9)")) || (Bukkit.getVersion().contains("(MC: 1.9.1)")) || (Bukkit.getVersion().contains("(MC: 1.9.2)")) || (Bukkit.getVersion().contains("(MC: 1.9.3)"))) {
            v1_9_R1.UnNick(p);
        }
        if (Bukkit.getVersion().contains("(MC: 1.9.4)")) {
            v1_9_R2.UnNick(p);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.10)")) || (Bukkit.getVersion().contains("(MC: 1.10.1)")) || (Bukkit.getVersion().contains("(MC: 1.10.2)"))) {
            v1_10_R1.UnNick(p);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.11)")) || (Bukkit.getVersion().contains("(MC: 1.11.1)")) || (Bukkit.getVersion().contains("(MC: 1.11.2)"))) {
            v1_11_R1.UnNick(p);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.12)")) || (Bukkit.getVersion().contains("(MC: 1.12.1)"))) {
            v1_12_R1.UnNick(p);
        }
    }

    public static void setSkin(UUID p, String pskin)
    {
        if (Bukkit.getVersion().contains("(MC: 1.8.3)")) {
            v1_8_R2.setSkin(p, pskin);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.8.4)")) || (Bukkit.getVersion().contains("(MC: 1.8.5)")) || (Bukkit.getVersion().contains("(MC: 1.8.6)")) || (Bukkit.getVersion().contains("(MC: 1.8.7)")) || (Bukkit.getVersion().contains("(MC: 1.8.8)"))) {
            v1_8_R3.setSkin(p, pskin);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.9)")) || (Bukkit.getVersion().contains("(MC: 1.9.1)")) || (Bukkit.getVersion().contains("(MC: 1.9.2)")) || (Bukkit.getVersion().contains("(MC: 1.9.3)"))) {
            v1_9_R1.setSkin(p, pskin);
        }
        if (Bukkit.getVersion().contains("(MC: 1.9.4)")) {
            v1_9_R2.setSkin(p, pskin);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.10)")) || (Bukkit.getVersion().contains("(MC: 1.10.1)")) || (Bukkit.getVersion().contains("(MC: 1.10.2)"))) {
            v1_10_R1.setSkin(p, pskin);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.11)")) || (Bukkit.getVersion().contains("(MC: 1.11.1)")) || (Bukkit.getVersion().contains("(MC: 1.11.2)"))) {
            v1_11_R1.setSkin(p, pskin);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.12)")) || (Bukkit.getVersion().contains("(MC: 1.12.1)"))) {
            v1_12_R1.setSkin(p, pskin);
        }
    }

    public static void setRandomSkin(UUID p)
    {
        if (Bukkit.getVersion().contains("(MC: 1.8.3)")) {
            v1_8_R2.setRandomSkin(p);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.8.4)")) || (Bukkit.getVersion().contains("(MC: 1.8.5)")) || (Bukkit.getVersion().contains("(MC: 1.8.6)")) || (Bukkit.getVersion().contains("(MC: 1.8.7)")) || (Bukkit.getVersion().contains("(MC: 1.8.8)"))) {
            v1_8_R3.setRandomSkin(p);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.9)")) || (Bukkit.getVersion().contains("(MC: 1.9.1)")) || (Bukkit.getVersion().contains("(MC: 1.9.2)")) || (Bukkit.getVersion().contains("(MC: 1.9.3)"))) {
            v1_9_R1.setRandomSkin(p);
        }
        if (Bukkit.getVersion().contains("(MC: 1.9.4)")) {
            v1_9_R2.setRandomSkin(p);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.10)")) || (Bukkit.getVersion().contains("(MC: 1.10.1)")) || (Bukkit.getVersion().contains("(MC: 1.10.2)"))) {
            v1_10_R1.setRandomSkin(p);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.11)")) || (Bukkit.getVersion().contains("(MC: 1.11.1)")) || (Bukkit.getVersion().contains("(MC: 1.11.2)"))) {
            v1_11_R1.setRandomSkin(p);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.12)")) || (Bukkit.getVersion().contains("(MC: 1.12.1)"))) {
            v1_12_R1.setRandomSkin(p);
        }*/
    }

    public static void resetSkin(UUID p)
    {
        if (Bukkit.getVersion().contains("(MC: 1.8.3)")) {
       //     v1_8_R2.resetSkin(p);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.8.4)")) || (Bukkit.getVersion().contains("(MC: 1.8.5)")) || (Bukkit.getVersion().contains("(MC: 1.8.6)")) || (Bukkit.getVersion().contains("(MC: 1.8.7)")) || (Bukkit.getVersion().contains("(MC: 1.8.8)"))) {
            v1_8_R3.resetSkin(p);
        }
    /*    if ((Bukkit.getVersion().contains("(MC: 1.9)")) || (Bukkit.getVersion().contains("(MC: 1.9.1)")) || (Bukkit.getVersion().contains("(MC: 1.9.2)")) || (Bukkit.getVersion().contains("(MC: 1.9.3)"))) {
            v1_9_R1.resetSkin(p);
        }
        if (Bukkit.getVersion().contains("(MC: 1.9.4)")) {
            v1_9_R2.resetSkin(p);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.10)")) || (Bukkit.getVersion().contains("(MC: 1.10.1)")) || (Bukkit.getVersion().contains("(MC: 1.10.2)"))) {
            v1_10_R1.resetSkin(p);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.11)")) || (Bukkit.getVersion().contains("(MC: 1.11.1)")) || (Bukkit.getVersion().contains("(MC: 1.11.2)"))) {
            v1_11_R1.resetSkin(p);
        }
        if ((Bukkit.getVersion().contains("(MC: 1.12)")) || (Bukkit.getVersion().contains("(MC: 1.12.1)"))) {
            v1_12_R1.resetSkin(p);
        }*/
    }

    public static boolean isNicked(UUID p)
    {
        boolean nicked = false;
            try
            {
                ResultSet rs = NickMySQL.Result("SELECT * FROM Nicked WHERE UUID='" + p + "'");
                if (!rs.next()) {
                    return nicked;
                }
                nicked = rs.getBoolean("NICKED");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        return nicked;
    }

    public static boolean isNickNameUsed(String name)
    {
        boolean used = false;

            try
            {
                ResultSet rs = NickMySQL.Result("SELECT NICKNAME FROM Nicked WHERE NICKNAME='" + name + "'");
                if (rs.next()) {
                    used = true;
                } else {
                    used = false;
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }


        return used;
    }

    public static void sendActionBar(UUID p, final String msg)
    {
        int tid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable()
        {
            public void run()
            {
                if (Bukkit.getVersion().contains("(MC: 1.8.3)")) {
                //    v1_8_R2.sendActionBar(this, msg);
                }
                if ((Bukkit.getVersion().contains("(MC: 1.8.4)")) || (Bukkit.getVersion().contains("(MC: 1.8.5)")) || (Bukkit.getVersion().contains("(MC: 1.8.6)")) || (Bukkit.getVersion().contains("(MC: 1.8.7)")) || (Bukkit.getVersion().contains("(MC: 1.8.8)"))) {
                    v1_8_R3.sendActionBar(p, msg);
                }

            }
        }, 0L, 40L);
        taskID.put(Bukkit.getPlayer(p), Integer.valueOf(tid));
    }

    public static void endActionBar(UUID p)
    {
        if (taskID.containsKey(Bukkit.getPlayer(p)))
        {
            int tid = ((Integer)taskID.get(Bukkit.getPlayer(p))).intValue();
           Main.getInstance().getServer().getScheduler().cancelTask(tid);
            taskID.remove(Bukkit.getPlayer(p));
        }
    }


}
