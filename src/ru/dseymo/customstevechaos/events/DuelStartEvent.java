package ru.dseymo.customstevechaos.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.customstevechaos.duels.Duel;

public class DuelStartEvent extends Event {
	
	private static HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() {return handlers;}
	
	@Getter
	private Duel duel;
	@Getter @Setter
	private boolean cancelled = false;
	
    public DuelStartEvent(Duel duel) {
    	
    	this.duel = duel;
    	
    }
    
    public HandlerList getHandlers() {return handlers;}
	
}
