package ru.dseymo.customstevechaos.players.perks.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.perks.Perk;

public class PerkSwordmanListener implements Listener {
	
	@EventHandler
	private void damage(EntityDamageByEntityEvent e) {
		if(e.getDamager().getType() != EntityType.PLAYER) return;
		Player p = Game.getInstance().getPlayer(((org.bukkit.entity.Player)e.getDamager()).getUniqueId());
		if(p.getPerk() == null || p.getPerk() != Perk.SWORDMAN) return;
		
		e.setDamage(e.getDamage() + (e.getDamage()*0.15));
		
	}
	
}
