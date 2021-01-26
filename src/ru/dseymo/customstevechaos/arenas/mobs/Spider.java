package ru.dseymo.customstevechaos.arenas.mobs;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Spider extends Mob {
	
	public static Spider mob = new Spider();
	

	public Spider() {
		super("spider");
	}
	
	
	@Override
	public void onHit(EntityDamageByEntityEvent e) {
		if(e.getEntityType() != EntityType.PLAYER) return;
		Player p = (Player)e.getEntity();
		
		if(!p.hasPotionEffect(PotionEffectType.POISON))
			p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 0));
		
	}
	
}
