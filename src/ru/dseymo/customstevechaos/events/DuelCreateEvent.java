package ru.dseymo.customstevechaos.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.customstevechaos.players.Player;

public class DuelCreateEvent extends Event {
	
	private static HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() {return handlers;}
	
	@Getter @Setter
	private Player p1, p2;
	@Getter @Setter
	private boolean cancelled = false;
	
    public DuelCreateEvent(Player p1, Player p2) {
    	
    	this.p1 = p1;
    	this.p2 = p2;
    	
    }
    
    public HandlerList getHandlers() {return handlers;}
	
}
