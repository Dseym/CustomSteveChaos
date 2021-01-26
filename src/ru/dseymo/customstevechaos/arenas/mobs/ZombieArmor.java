package ru.dseymo.customstevechaos.arenas.mobs;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;

import ru.dseymo.customstevechaos.utils.ItemsUtil;

public class ZombieArmor extends Mob {
	
	public static ZombieArmor mob = new ZombieArmor();
	

	public ZombieArmor() {
		super("zombie_armor");
	}
	
	
	@Override
	public boolean onSpawn(LivingEntity ent) {
		
		EntityEquipment equip = ent.getEquipment();
		equip.setItemInHand(null);
		equip.setHelmet(ItemsUtil.generateItem(Material.IRON_HELMET, ""));
		equip.setChestplate(ItemsUtil.generateItem(Material.IRON_CHESTPLATE, ""));
		equip.setLeggings(ItemsUtil.generateItem(Material.IRON_LEGGINGS, ""));
		equip.setBoots(ItemsUtil.generateItem(Material.IRON_BOOTS, ""));
		
		org.bukkit.entity.Zombie zomb = (org.bukkit.entity.Zombie)ent;
		zomb.setBaby(false);
		zomb.setVillager(false);
		
		return false;
	}
	
}
