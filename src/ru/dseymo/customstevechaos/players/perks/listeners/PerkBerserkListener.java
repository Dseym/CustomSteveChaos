package ru.dseymo.customstevechaos.players.perks.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.perks.Perk;

public class PerkBerserkListener implements Listener {
	
	@EventHandler
	private void damage(EntityDamageByEntityEvent e) {
		if(e.getDamager().getType() != EntityType.PLAYER) return;
		org.bukkit.entity.Player _p = (org.bukkit.entity.Player)e.getDamager();
		Player p = Game.getInstance().getPlayer(_p.getUniqueId());
		if(p.getPerk() == null || p.getPerk() != Perk.BERSERK) return;
		
		double procent = _p.getHealth()/_p.getMaxHealth();
		if(procent < 0.425) e.setDamage(e.getDamage() + (e.getDamage()*0.1));
		else if(procent < 0.225) e.setDamage(e.getDamage() + (e.getDamage()*0.15));
		else if(procent < 0.125) e.setDamage(e.getDamage() + (e.getDamage()*0.2));
		
	}
	
	@EventHandler
	private void speed(EntityDamageEvent e) {
		if(e.getEntityType() != EntityType.PLAYER) return;
		org.bukkit.entity.Player _p = (org.bukkit.entity.Player)e.getEntity();
		Player p = Game.getInstance().getPlayer(_p.getUniqueId());
		if(p.getPerk() == null || p.getPerk() != Perk.BERSERK) return;
		
		double procent = _p.getHealth()/_p.getMaxHealth();
		if(procent < 0.425) _p.setWalkSpeed(_p.getWalkSpeed() + (_p.getWalkSpeed()*0.1f));
		else if(procent < 0.225) _p.setWalkSpeed(_p.getWalkSpeed() + (_p.getWalkSpeed()*0.15f));
		else if(procent < 0.125) _p.setWalkSpeed(_p.getWalkSpeed() + (_p.getWalkSpeed()*0.2f));
		
	}
	
}
