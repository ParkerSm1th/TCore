package us.tropicalisles.core.utils;

import org.bukkit.event.Listener;
import us.tropicalisles.core.Main;

import java.sql.*;

public class NickMySQL
implements Listener
{

    private static Main pl;
    private static String username;
    private static String password;
    private static String database;
    private static String host;
    private static String port;
    private static Connection connection;

    public NickMySQL(Main main)
    {
        pl = main;
    }

    public NickMySQL(String user, String pass, String host1, String database1)
    {
        username = user;
        password = pass;
        host = host1;
        database = database1;
    }

    public static void connectNickSQL()
    {
        username = "kindal_mod";
        password = "Developer3001";
        database = "kindal_mod";
        host = "108.167.140.118";
    }

    public static boolean connected()
    {
        return connection != null;
    }

    public static void connect()
    {
        if (!connected()) {
            try
            {
                connection = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "?user=" + username + "&password=" + password + "&autoReconnect=true");
               System.out.print("Connected to MySQL: Nick Database successfully");
            }
            catch (SQLException s)
            {
                s.printStackTrace();
            }
        }
    }

    public static void close()
    {
        if (connected()) {
            try
            {
                connection.close();
                System.out.print("Disconnected to MySQL: Nick Database successfully");
            }
            catch (SQLException s)
            {
                s.printStackTrace();
            }
        }
    }

    public static void createTable()
    {
        if (connected()) {
            try {
                connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS " +
                        "Nicked (id INTEGER, UUID VARCHAR(100), NAME VARCHAR(100), NICKNAME VARCHAR(100), NICKED VARCHAR(10), primary key (id))");
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
    }


    public static void update(String query)
    {
        if (connected()) {
            try
            {
                connection.createStatement().executeUpdate(query);
            }
            catch (SQLException s)
            {
                s.printStackTrace();
            }
        }
    }

    public static ResultSet Result(String query)
    {
        ResultSet r = null;
        try
        {
            Statement t = connection.createStatement();
            r = t.executeQuery(query);
        }
        catch (SQLException s)
        {
            connect();
            System.err.println(s);
        }
        catch (NullPointerException s)
        {
            connect();
            System.err.println(s);
        }
        return r;
    }


}
