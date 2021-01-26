package ru.dseymo.customstevechaos.players.perks.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import ru.dseymo.customstevechaos.events.GameStartEvent;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.perks.Perk;

public class PerkTitanListener implements Listener {
	
	@EventHandler
	private void gameStart(GameStartEvent e) {
		for(Player p: e.getGame().getNotSpecPlayers()) {
			if(p.getPerk() == null || p.getPerk() != Perk.TITAN) return;
			
			p.getBP().setMaxHealth(p.getBP().getMaxHealth() + 8);
			
		}
	}
	
}
