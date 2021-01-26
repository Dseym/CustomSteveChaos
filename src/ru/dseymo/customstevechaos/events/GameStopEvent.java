package ru.dseymo.customstevechaos.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.players.Player;

public class GameStopEvent extends Event {
	
	private static HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() {return handlers;}
	
	@Getter
	private Game game;
	@Getter
	private Player winner;
	
    public GameStopEvent(Game game, Player winner) {
    	
    	this.game = game;
    	this.winner = winner;
    	
    }
    
    public HandlerList getHandlers() {return handlers;}
	
}
