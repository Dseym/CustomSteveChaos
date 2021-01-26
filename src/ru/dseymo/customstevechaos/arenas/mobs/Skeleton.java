package ru.dseymo.customstevechaos.arenas.mobs;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.inventory.EntityEquipment;

public class Skeleton extends Mob {
	
	public static Skeleton mob = new Skeleton();
	

	public Skeleton() {
		super("skeleton");
	}
	
	@Override
	public boolean onSpawn(LivingEntity ent) {
		
		EntityEquipment equip = ent.getEquipment();
		equip.setHelmet(null);
		equip.setChestplate(null);
		equip.setLeggings(null);
		equip.setBoots(null);
		
		org.bukkit.entity.Skeleton skel = (org.bukkit.entity.Skeleton)ent;
		skel.setSkeletonType(SkeletonType.NORMAL);
		
		return false;
	}

}
