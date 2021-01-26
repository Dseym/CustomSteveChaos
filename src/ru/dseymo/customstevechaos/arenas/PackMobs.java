package ru.dseymo.customstevechaos.arenas;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import lombok.Getter;

public enum PackMobs {
	
	ZOMBIES(Mobs.ZOMBIE, Mobs.ZOMBIE, Mobs.ZOMBIE, Mobs.ZOMBIE, Mobs.ZOMBIE),
	ZOMBIES_ARMOR(Mobs.ZOMBIE_ARMOR, Mobs.ZOMBIE_ARMOR, Mobs.ZOMBIE_ARMOR, Mobs.ZOMBIE_ARMOR, Mobs.ZOMBIE_ARMOR),
	SKELETONS(Mobs.SKELETON, Mobs.SKELETON, Mobs.SKELETON, Mobs.SKELETON, Mobs.SKELETON),
	BLAZES(Mobs.BLAZE, Mobs.BLAZE, Mobs.BLAZE, Mobs.BLAZE, Mobs.BLAZE),
	SPIDERS(Mobs.SPIDER, Mobs.SPIDER, Mobs.SPIDER, Mobs.SPIDER, Mobs.SPIDER),
	ENDERMITES(Mobs.ENDERMITE, Mobs.ENDERMITE, Mobs.ENDERMITE, Mobs.ENDERMITE, Mobs.ENDERMITE),
	MINI_BOSS(Mobs.IRON_GOLEM, Mobs.ZOMBIE, Mobs.ZOMBIE),
	MIX1(Mobs.ZOMBIE, Mobs.SKELETON, Mobs.SPIDER, Mobs.BLAZE, Mobs.ZOMBIE_ARMOR),
	MIX2(Mobs.ZOMBIE, Mobs.SPIDER, Mobs.SPIDER, Mobs.BLAZE, Mobs.BLAZE, Mobs.ZOMBIE);
	
	@Getter
	private Mobs[] mobs;
	
	private PackMobs(Mobs... mobs) {this.mobs = mobs;}
	
	public ArrayList<LivingEntity> spawn(Location loc) {
		
		ArrayList<LivingEntity> mobs = new ArrayList<>();
		for(Mobs mob: this.mobs)
			mobs.add(mob.spawn(loc));
		
		return mobs;
	}
	
}
