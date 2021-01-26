package ru.dseymo.customstevechaos.players.perks.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.players.perks.Perk;

public class PerkMasterWeaponListener implements Listener {
	
	@EventHandler
	private void damage(EntityDamageByEntityEvent e) {
		Entity damager = e.getDamager();
		if(damager instanceof Projectile) {
			ProjectileSource shooter = ((Projectile)damager).getShooter();
			if(shooter instanceof Player) damager = (Player)shooter;
		}
		if(!(damager instanceof Player)) return;
		ru.dseymo.customstevechaos.players.Player p = Game.getInstance().getPlayer(damager.getUniqueId());
		if(p.getPerk() == null || p.getPerk() != Perk.MASTER_WEAPON || Math.random() > 0.07) return;
		
		e.setDamage(e.getDamage()*2);
		
	}
	
}
