package ru.dseymo.customstevechaos.arenas.mobs;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Golem extends Mob {
	
	public static Golem mob = new Golem();
	

	public Golem() {
		super("golem");
	}
	
	
	@Override
	public void onHit(EntityDamageByEntityEvent e) {
		
		if(Math.random() > 0.4) e.setCancelled(true);
		else e.setDamage(e.getDamage()/2);
		
	}
	
}
