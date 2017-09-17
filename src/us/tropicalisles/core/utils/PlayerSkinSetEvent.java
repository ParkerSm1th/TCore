package us.tropicalisles.core.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerSkinSetEvent
  extends Event
{
  public static HandlerList handlers = new HandlerList();
  private Player player;
  private String pSkin;
  
  public PlayerSkinSetEvent(Player arg0, String arg1)
  {
    this.player = arg0;
    this.pSkin = arg1;
  }
  
  public Player getPlayer()
  {
    return this.player;
  }
  
  public String getPlayerSkinName()
  {
    return this.pSkin;
  }
  
  public void setSkinSetMessage(String arg0)
  {
    this.player.sendMessage(arg0);
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
