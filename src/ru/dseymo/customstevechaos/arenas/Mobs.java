package ru.dseymo.customstevechaos.arenas;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import ru.dseymo.customstevechaos.arenas.mobs.Endermite;
import ru.dseymo.customstevechaos.arenas.mobs.Golem;
import ru.dseymo.customstevechaos.arenas.mobs.Skeleton;
import ru.dseymo.customstevechaos.arenas.mobs.Spider;
import ru.dseymo.customstevechaos.arenas.mobs.Zombie;
import ru.dseymo.customstevechaos.arenas.mobs.ZombieArmor;

public enum Mobs {
	
	ZOMBIE(EntityType.ZOMBIE), SKELETON(EntityType.SKELETON), SPIDER(EntityType.SPIDER), BLAZE(EntityType.BLAZE),
	IRON_GOLEM(EntityType.IRON_GOLEM), ENDERMITE(EntityType.ENDERMITE), ZOMBIE_ARMOR(EntityType.ZOMBIE);
	
	private EntityType type;
	
	private Mobs(EntityType type) {this.type = type;}
	
	public LivingEntity spawn(Location loc) {
		
		switch (this) {
			case ENDERMITE: return Endermite.mob.spawn(type, loc);
			case IRON_GOLEM: return Golem.mob.spawn(type, loc);
			case ZOMBIE_ARMOR: return ZombieArmor.mob.spawn(type, loc);
			case SPIDER: return Spider.mob.spawn(type, loc);
			case ZOMBIE: return Zombie.mob.spawn(type, loc);
			case SKELETON: return Skeleton.mob.spawn(type, loc);
			default: return (LivingEntity)loc.getWorld().spawnEntity(loc, type);
				
		}
		
	}
	
}
