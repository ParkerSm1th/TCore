package us.tropicalisles.core.commands;



import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import us.tropicalisles.core.Main;
import us.tropicalisles.core.gamemode.ToggleGameMode;
import us.tropicalisles.core.utils.UtilMethods;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WhitelistCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {

        if(sender instanceof Player) {
            Player p = (Player) sender;

            if (UtilMethods.getGroupsForPlayer(p).contains("manager")
                    || UtilMethods.getGroupsForPlayer(p).contains("owner")
                    || UtilMethods.getGroupsForPlayer(p).contains("admin"))

            {
                File file = new File(JavaPlugin.getPlugin(Main.class).getDataFolder(), "whitelist.yml");
                FileConfiguration filez = YamlConfiguration.loadConfiguration(file);

                if (args.length == 0) {

                    UtilMethods.sendMessage(sender, "Wrong Usage! [/whitelist <on,off,list,add,remove>]");
                }

                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("on")) {
                        Main.whiteListEnabled = filez.getBoolean("whitelist.on");

                        if (Main.whiteListEnabled == false) {
                            UtilMethods.sendMessage(sender, "The whitelist has been enabled!");
                            Main.whiteListEnabled = true;
                            filez.set("whitelist.on", true);
                            try {
                                filez.save(file);
                            } catch (Exception ev) {
                                ev.printStackTrace();
                            }
                        } else {
                            UtilMethods.sendMessage(sender, "The whitelist is already enabled!");
                        }

                    }
                    if (args[0].equalsIgnoreCase("off")) {
                        Main.whiteListEnabled = filez.getBoolean("whitelist.on");

                        if (Main.whiteListEnabled == true) {
                            UtilMethods.sendMessage(sender, "The whitelist has been disabled!");
                            Main.whiteListEnabled = false;
                            filez.set("whitelist.on", false);
                            try {
                                filez.save(file);
                            } catch (Exception ev) {
                                ev.printStackTrace();
                            }
                        } else {
                            UtilMethods.sendMessage(sender, "The whitelist is already disabled!");
                        }

                    }
                    if (args[0].equalsIgnoreCase("list")) {
                        List<String> whiteList = filez.getStringList("whitelist.players");
                        List<String> names = new ArrayList();

                        for (String uuid : whiteList) {
                            OfflinePlayer pl = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
                            names.add(pl.getName());
                        }
                        UtilMethods.sendMessage(sender, "Whitelisted Players: &6" + StringUtils.join(names, new StringBuilder().append(ChatColor.GRAY).append(", ").append(ChatColor.GOLD).toString()));
                    }

                }

                if (args.length == 2) {

                    if (args[0].equalsIgnoreCase("add")) {
                        OfflinePlayer playerAdd = Bukkit.getOfflinePlayer(args[1]);
                        List<String> whiteList = filez.getStringList("whitelist.players");

                        if (!whiteList.contains(playerAdd.getUniqueId().toString())) {
                            whiteList.add(playerAdd.getUniqueId().toString());
                            UtilMethods.sendMessage(sender, "You have added " + playerAdd.getName() + " to the whitelist.");

                            for (Player players : Bukkit.getOnlinePlayers()) {
                                if (players.isOp()) {
                                    UtilMethods.sendMessage(players, sender.getName() + " added " + playerAdd.getName() + " to the whitelist on the server you are on.");
                                }
                            }

                            filez.set("whitelist.players", whiteList);
                            try {
                                filez.save(file);
                            } catch (Exception ev) {
                                ev.printStackTrace();
                            }

                        } else {
                            UtilMethods.sendMessage(sender, "That player is already whitelisted.");
                        }
                    }
                    if (args[0].equalsIgnoreCase("remove")) {
                        OfflinePlayer playerAdd = Bukkit.getOfflinePlayer(args[1]);
                        List<String> whiteList = filez.getStringList("whitelist.players");

                        if (whiteList.contains(playerAdd.getUniqueId().toString())) {
                            whiteList.remove(playerAdd.getUniqueId().toString());
                            UtilMethods.sendMessage(sender, "You have remove " + playerAdd.getName() + " from the whitelist.");

                            for (Player players : Bukkit.getOnlinePlayers()) {
                                if (players.isOp()) {
                                    UtilMethods.sendMessage(players, sender.getName() + " removed " + playerAdd.getName() + " from the whitelist on the server you are on.");
                                }
                            }

                            filez.set("whitelist.players", whiteList);
                            try {
                                filez.save(file);
                            } catch (Exception ev) {
                                ev.printStackTrace();
                            }

                        } else {
                            UtilMethods.sendMessage(sender, "That player is not whitelisted.");
                        }
                    }

                }


            } else {
                UtilMethods.permsError(sender, "Admin");
                return false;

            }
        }else{
            if(sender instanceof ConsoleCommandSender) {
                File file = new File(JavaPlugin.getPlugin(Main.class).getDataFolder(), "whitelist.yml");
                FileConfiguration filez = YamlConfiguration.loadConfiguration(file);

                if (args.length == 0) {

                    UtilMethods.sendMessage(sender, "Wrong Usage! [/whitelist <on,off,list,add,remove>]");
                }

                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("on")) {
                        Main.whiteListEnabled = filez.getBoolean("whitelist.on");

                        if (Main.whiteListEnabled == false) {
                            UtilMethods.sendMessage(sender, "The whitelist has been enabled!");
                            Main.whiteListEnabled = true;
                            filez.set("whitelist.on", true);
                            try {
                                filez.save(file);
                            } catch (Exception ev) {
                                ev.printStackTrace();
                            }
                        } else {
                            UtilMethods.sendMessage(sender, "The whitelist is already enabled!");
                        }

                    }
                    if (args[0].equalsIgnoreCase("off")) {
                        Main.whiteListEnabled = filez.getBoolean("whitelist.on");

                        if (Main.whiteListEnabled == true) {
                            UtilMethods.sendMessage(sender, "The whitelist has been disabled!");
                            Main.whiteListEnabled = false;
                            filez.set("whitelist.on", false);
                            try {
                                filez.save(file);
                            } catch (Exception ev) {
                                ev.printStackTrace();
                            }
                        } else {
                            UtilMethods.sendMessage(sender, "The whitelist is already disabled!");
                        }

                    }
                    if (args[0].equalsIgnoreCase("list")) {
                        List<String> whiteList = filez.getStringList("whitelist.players");
                        List<String> names = new ArrayList();

                        for (String uuid : whiteList) {
                            OfflinePlayer pl = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
                            names.add(pl.getName());
                        }

                        UtilMethods.sendMessage(sender, "Whitelisted Players: &6" + names.toString().replace("[", "").replace("]", "").replace("null,", ""));
                    }

                }

                if (args.length == 2) {

                    if (args[0].equalsIgnoreCase("add")) {
                        OfflinePlayer playerAdd = Bukkit.getOfflinePlayer(args[1]);
                        List<String> whiteList = filez.getStringList("whitelist.players");

                        if (!whiteList.contains(playerAdd.getUniqueId().toString())) {
                            whiteList.add(playerAdd.getUniqueId().toString());
                            UtilMethods.sendMessage(sender, "You have added " + playerAdd.getName() + " to the whitelist.");

                            for (Player players : Bukkit.getOnlinePlayers()) {
                                if (players.isOp()) {
                                    UtilMethods.sendMessage(players, sender.getName() + " added " + playerAdd.getName() + " to the whitelist on the server you are on.");
                                }
                            }

                            filez.set("whitelist.players", whiteList);
                            try {
                                filez.save(file);
                            } catch (Exception ev) {
                                ev.printStackTrace();
                            }

                        } else {
                            UtilMethods.sendMessage(sender, "That player is already whitelisted.");
                        }
                    }
                    if (args[0].equalsIgnoreCase("remove")) {
                        OfflinePlayer playerAdd = Bukkit.getOfflinePlayer(args[1]);
                        List<String> whiteList = filez.getStringList("whitelist.players");

                        if (whiteList.contains(playerAdd.getUniqueId().toString())) {
                            whiteList.remove(playerAdd.getUniqueId().toString());
                            UtilMethods.sendMessage(sender, "You have remove " + playerAdd.getName() + " from the whitelist.");

                            for (Player players : Bukkit.getOnlinePlayers()) {
                                if (players.isOp()) {
                                    UtilMethods.sendMessage(players, sender.getName() + " removed " + playerAdd.getName() + " from the whitelist on the server you are on.");
                                }
                            }

                            filez.set("whitelist.players", whiteList);
                            try {
                                filez.save(file);
                            } catch (Exception ev) {
                                ev.printStackTrace();
                            }

                        } else {
                            UtilMethods.sendMessage(sender, "That player is not whitelisted.");
                        }
                    }

                }
            }
        }

        return false;
    }

}

