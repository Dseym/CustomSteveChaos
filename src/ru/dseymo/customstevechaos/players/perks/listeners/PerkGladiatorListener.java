package ru.dseymo.customstevechaos.players.perks.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import ru.dseymo.customstevechaos.events.PlayerEndWaveEvent;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.perks.Perk;

public class PerkGladiatorListener implements Listener {
	
	@EventHandler
	private void win(PlayerEndWaveEvent e) {
		Player p = e.getPlayer();
		if(p.getPerk() != Perk.GLADIATOR || !e.isDuel()) return;
		
		e.setDeposit((int)(e.getDeposit() + (e.getDeposit()*0.45)));
		
	}
	
}
