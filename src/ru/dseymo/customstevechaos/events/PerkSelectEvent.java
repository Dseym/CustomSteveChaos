package ru.dseymo.customstevechaos.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.perks.Perk;

public class PerkSelectEvent extends Event {
	
	private static HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() {return handlers;}
	
	@Getter @Setter
	private Perk perk;
	@Getter
	private Player player;
	
    public PerkSelectEvent(Perk perk, Player player) {
    	
    	this.perk = perk;
    	this.player = player;
    	
    }
    
    public HandlerList getHandlers() {return handlers;}
	
}
