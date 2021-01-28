package ru.dseymo.customstevechaos.players.perks.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import ru.dseymo.customstevechaos.events.PerkSelectEvent;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.perks.Perk;

public class PerkTitanListener implements Listener {
	
	@EventHandler
	private void gameStart(PerkSelectEvent e) {
		if(e.getPerk() != Perk.TITAN) return;
		Player p = e.getPlayer();
		
		p.getBP().setMaxHealth(p.getBP().getMaxHealth() + 8);
		
	}
	
}
