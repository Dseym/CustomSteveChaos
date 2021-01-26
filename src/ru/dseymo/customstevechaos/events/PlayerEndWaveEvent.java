package ru.dseymo.customstevechaos.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.customstevechaos.players.Player;

public class PlayerEndWaveEvent extends Event {
	
	private static HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() {return handlers;}
	
	@Getter
	private Player player;
	@Getter
	private int wave;
	@Getter @Setter
	private int deposit;
	@Getter
	private boolean duel;
	
    public PlayerEndWaveEvent(Player p, int wave, int deposit, boolean duel) {
    	
    	player = p;
    	this.wave = wave;
    	this.deposit = deposit;
    	this.duel = duel;
    	
    }
    
    public HandlerList getHandlers() {return handlers;}
	
}
