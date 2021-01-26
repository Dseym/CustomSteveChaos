package ru.dseymo.customstevechaos.players.perks.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.perks.Perk;

public class PerkDodgerListener implements Listener {
	
	@EventHandler
	private void damage(EntityDamageEvent e) {
		if(e.getEntityType() != EntityType.PLAYER) return;
		Player p = Game.getInstance().getPlayer(e.getEntity().getUniqueId());
		if(p.getPerk() == null || p.getPerk() != Perk.DODGER || Math.random() > 0.2) return;
		
		e.setCancelled(true);
	}
	
}
