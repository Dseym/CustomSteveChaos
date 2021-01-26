package ru.dseymo.customstevechaos.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.customstevechaos.arenas.PackMobs;

public class WaveStartEvent extends Event {
	
	private static HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() {return handlers;}
	
	@Getter
	private int wave;
	@Getter
	private PackMobs pack;
	@Getter @Setter
	private boolean cancelled = false;
	
    public WaveStartEvent(int wave, PackMobs pack) {
    	
    	this.wave = wave;
    	this.pack = pack;
    	
    }
    
    public HandlerList getHandlers() {return handlers;}
	
}
