package ru.dseymo.customstevechaos.arenas.mobs;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Endermite extends Mob {
	
	public static Endermite mob = new Endermite();
	

	public Endermite() {
		super("endermite");
	}
	
	
	@Override
	public void onHit(EntityDamageByEntityEvent e) {
		if(e.getEntityType() != EntityType.PLAYER) return;
		Player p = (Player)e.getEntity();
		
		if(!p.hasPotionEffect(PotionEffectType.BLINDNESS))
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0));
		
	}
	
}
