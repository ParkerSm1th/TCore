package us.tropicalisles.core.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerNickEvent
  extends Event
{
  public static HandlerList handlers = new HandlerList();
  private Player player;
  private String nick;
  
  public PlayerNickEvent(Player arg0, String arg1)
  {
    this.player = arg0;
    this.nick = arg1;
  }
  
  public Player getPlayer()
  {
    return this.player;
  }
  
  public String getNickName()
  {
    return this.nick;
  }
  
  public void setNickMessage(String arg0)
  {
    this.player.sendMessage(arg0);
  }
  
  public void setNickActionbarMessage(String arg0)
  {
    NickAPI.sendActionBar(this.player.getUniqueId(), arg0);
  }
  
  public void stopNickActionbarMessage()
  {
    NickAPI.endActionBar(this.player.getUniqueId());
  }
  
  public HandlerList getHandlers()
  {
    return handlers;
  }
  
  public static HandlerList getHandlerList()
  {
    return handlers;
  }
}
