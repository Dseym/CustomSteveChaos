package ru.dseymo.customstevechaos.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import ru.dseymo.customstevechaos.items.items.Bow;
import ru.dseymo.customstevechaos.items.items.BowFire;
import ru.dseymo.customstevechaos.items.items.DiamondSwordPower;
import ru.dseymo.customstevechaos.items.items.GoldSword;
import ru.dseymo.customstevechaos.items.items.IronSwordFire;
import ru.dseymo.customstevechaos.utils.ItemsUtil;

public enum Items {
	
	DIAMOND_HELMET(Material.DIAMOND_HELMET), DIAMOND_CHESTPLATE(Material.DIAMOND_CHESTPLATE), BOW(Material.BOW),
	DIAMOND_LEGGINGS(Material.DIAMOND_LEGGINGS), DIAMOND_BOOTS(Material.DIAMOND_BOOTS), POTION_HEAL(Material.POTION),
	DIAMOND_SWORD(Material.DIAMOND_SWORD), IRON_HELMET(Material.IRON_HELMET), IRON_CHESTPLATE(Material.IRON_CHESTPLATE),
	IRON_LEGGINGS(Material.IRON_LEGGINGS), IRON_BOOTS(Material.IRON_BOOTS), POTION_REGEN2(Material.POTION),
	IRON_SWORD(Material.IRON_SWORD), LEATHER_HELMET(Material.LEATHER_HELMET), POTION_STRENGTH2(Material.POTION),
	LEATHER_CHESTPLATE(Material.LEATHER_CHESTPLATE), LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS), BOW2(Material.BOW),
	LEATHER_BOOTS(Material.LEATHER_BOOTS), POTION_STRENGTH(Material.POTION), POTION_HEAL2(Material.POTION),
	POTION_REGEN(Material.POTION), DIAMOND_SWORD2(Material.DIAMOND_SWORD), IRON_SWORD2(Material.IRON_SWORD),
	GOLD_SWORD(Material.GOLD_SWORD), WOOD_SWORD(Material.WOOD_SWORD);
	
	private Material material;
	
	private Items(Material material) {this.material = material;}
	
	public ItemStack generate() {
		
		ItemStack stack = ItemsUtil.generateItem(material, "");
		ItemsUtil.unbreakable(stack);
		
		switch (this) {
			case POTION_STRENGTH: new Potion(PotionType.STRENGTH, 1).apply(stack); break;
			case POTION_STRENGTH2: new Potion(PotionType.STRENGTH, 2).apply(stack); break;
			case POTION_REGEN: new Potion(PotionType.REGEN, 1).apply(stack); break;
			case POTION_REGEN2: new Potion(PotionType.STRENGTH, 2).apply(stack); break;
			case POTION_HEAL: new Potion(PotionType.INSTANT_HEAL, 1).apply(stack); break;
			case POTION_HEAL2: new Potion(PotionType.INSTANT_HEAL, 2).apply(stack); break;
			case BOW: stack = Bow.item.generateItem(material);
			case BOW2: stack = BowFire.item.generateItem(material);
			case DIAMOND_SWORD2: stack = DiamondSwordPower.item.generateItem(material);
			case IRON_SWORD2: stack = IronSwordFire.item.generateItem(material);
			case GOLD_SWORD: stack = GoldSword.item.generateItem(material);
			default: break;
			
		}
		
		return stack;
	}
	
}
