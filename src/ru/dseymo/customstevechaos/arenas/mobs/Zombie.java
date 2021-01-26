package ru.dseymo.customstevechaos.arenas.mobs;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;

public class Zombie extends Mob {
	
	public static Zombie mob = new Zombie();
	
	
	public Zombie() {
		super("zombie");
	}
	
	
	@Override
	public boolean onSpawn(LivingEntity ent) {
		
		EntityEquipment equip = ent.getEquipment();
		equip.setItemInHand(null);
		equip.setHelmet(null);
		equip.setChestplate(null);
		equip.setLeggings(null);
		equip.setBoots(null);
		
		org.bukkit.entity.Zombie zomb = (org.bukkit.entity.Zombie)ent;
		zomb.setBaby(false);
		zomb.setVillager(false);
		
		return false;
	}
	
}
