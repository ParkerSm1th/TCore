package us.tropicalisles.core.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerUnNickEvent
  extends Event
{
  public static HandlerList handlers = new HandlerList();
  private Player player;
  
  public PlayerUnNickEvent(Player arg0)
  {
    this.player = arg0;
  }
  
  public Player getPlayer()
  {
    return this.player;
  }
  
  public void setUnNickMessage(String arg0)
  {
    this.player.sendMessage(arg0);
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
