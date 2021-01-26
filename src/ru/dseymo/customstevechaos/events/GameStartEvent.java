package ru.dseymo.customstevechaos.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.customstevechaos.game.Game;

public class GameStartEvent extends Event {
	
	private static HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() {return handlers;}
	
	@Getter
	private Game game;
	@Getter @Setter
	private boolean cancelled = false;
	
    public GameStartEvent(Game game) {
    	
    	this.game = game;
    	
    }
    
    public HandlerList getHandlers() {return handlers;}
    
}
