package ru.dseymo.customstevechaos.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import ru.dseymo.customstevechaos.duels.Duel;
import ru.dseymo.customstevechaos.players.Player;

public class DuelStopEvent extends Event {
	
	private static HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() {return handlers;}
	
	@Getter
	private Duel duel;
	@Getter
	private Player winner;
	
    public DuelStopEvent(Duel duel, Player winner) {
    	
    	this.duel = duel;
    	this.winner = winner;
    	
    }
    
    public HandlerList getHandlers() {return handlers;}
	
}
