package ru.dseymo.customstevechaos.players.perks.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import ru.dseymo.customstevechaos.events.PerkSelectEvent;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.perks.Perk;

public class PerkAssassinListener implements Listener {
	
	@EventHandler
	private void move(PerkSelectEvent e) {
		Player p = e.getPlayer();
		org.bukkit.entity.Player _p = p.getBP();
		if(e.getPerk() != Perk.ASSASSIN) return;
		
		_p.setWalkSpeed((float)(_p.getWalkSpeed() + (_p.getWalkSpeed()*0.4)));
		
	}
	
}
