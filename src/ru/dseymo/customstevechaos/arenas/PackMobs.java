package ru.dseymo.customstevechaos.arenas;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import lombok.Getter;
import ru.dseymo.customstevechaos.Main;

public enum PackMobs {
	
	ZOMBIES(Main.getInstance().getLanguage("packs.zombie"), Mobs.ZOMBIE, Mobs.ZOMBIE, Mobs.ZOMBIE, Mobs.ZOMBIE, Mobs.ZOMBIE),
	ZOMBIES_ARMOR(Main.getInstance().getLanguage("packs.zombieArmor"), Mobs.ZOMBIE_ARMOR, Mobs.ZOMBIE_ARMOR, Mobs.ZOMBIE_ARMOR, Mobs.ZOMBIE_ARMOR, Mobs.ZOMBIE_ARMOR),
	SKELETONS(Main.getInstance().getLanguage("packs.skeletons"), Mobs.SKELETON, Mobs.SKELETON, Mobs.SKELETON, Mobs.SKELETON, Mobs.SKELETON),
	BLAZES(Main.getInstance().getLanguage("packs.blazes"), Mobs.BLAZE, Mobs.BLAZE, Mobs.BLAZE, Mobs.BLAZE, Mobs.BLAZE),
	SPIDERS(Main.getInstance().getLanguage("packs.spiders"), Mobs.SPIDER, Mobs.SPIDER, Mobs.SPIDER, Mobs.SPIDER, Mobs.SPIDER),
	CAVE_SPIDERS(Main.getInstance().getLanguage("packs.caveSpiders"), Mobs.CAVE_SPIDER, Mobs.CAVE_SPIDER, Mobs.CAVE_SPIDER, Mobs.CAVE_SPIDER, Mobs.CAVE_SPIDER),
	ENDERMITES(Main.getInstance().getLanguage("packs.endermites"), Mobs.ENDERMITE, Mobs.ENDERMITE, Mobs.ENDERMITE, Mobs.ENDERMITE, Mobs.ENDERMITE),
	SILVERFISHES(Main.getInstance().getLanguage("packs.silverfishes"), Mobs.SILVERFISH, Mobs.SILVERFISH, Mobs.SILVERFISH, Mobs.SILVERFISH, Mobs.SILVERFISH),
	MINI_BOSS(Main.getInstance().getLanguage("packs.miniBoss"), Mobs.IRON_GOLEM),
	PIG_ZOMBIES(Main.getInstance().getLanguage("packs.pigZombies"), Mobs.PIG_ZOMBIE, Mobs.PIG_ZOMBIE, Mobs.PIG_ZOMBIE, Mobs.PIG_ZOMBIE, Mobs.PIG_ZOMBIE),
	MIX1(Main.getInstance().getLanguage("packs.mix1"), Mobs.ZOMBIE, Mobs.SKELETON, Mobs.SPIDER, Mobs.BLAZE, Mobs.PIG_ZOMBIE),
	MIX2(Main.getInstance().getLanguage("packs.mix2"), Mobs.ZOMBIE_ARMOR, Mobs.CAVE_SPIDER, Mobs.SILVERFISH, Mobs.SKELETON, Mobs.ENDERMITE);
	
	@Getter
	private Mobs[] mobs;
	@Getter
	private String name;
	
	private PackMobs(String name, Mobs... mobs) {
		this.mobs = mobs;
		this.name = name;
	}
	
	public ArrayList<LivingEntity> spawn(Location loc) {
		
		ArrayList<LivingEntity> mobs = new ArrayList<>();
		for(Mobs mob: this.mobs)
			mobs.add(mob.spawn(loc));
		
		return mobs;
	}
	
}
