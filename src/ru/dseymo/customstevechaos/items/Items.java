package ru.dseymo.customstevechaos.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import ru.dseymo.customstevechaos.items.items.ArmorThorns;
import ru.dseymo.customstevechaos.items.items.Bow;
import ru.dseymo.customstevechaos.items.items.BowFire;
import ru.dseymo.customstevechaos.items.items.SwordPower;
import ru.dseymo.customstevechaos.items.items.SwordSuperPower;
import ru.dseymo.customstevechaos.items.items.SwordFire;
import ru.dseymo.customstevechaos.utils.ItemsUtil;

public enum Items {
	
	DIAMOND_HELMET1(Material.DIAMOND_HELMET), DIAMOND_CHESTPLATE1(Material.DIAMOND_CHESTPLATE), BOW(Material.BOW),
	DIAMOND_LEGGINGS1(Material.DIAMOND_LEGGINGS), DIAMOND_BOOTS1(Material.DIAMOND_BOOTS), POTION_HEAL(Material.POTION),
	DIAMOND_SWORD(Material.DIAMOND_SWORD), IRON_HELMET1(Material.IRON_HELMET), IRON_CHESTPLATE1(Material.IRON_CHESTPLATE),
	IRON_LEGGINGS1(Material.IRON_LEGGINGS), IRON_BOOTS1(Material.IRON_BOOTS), POTION_REGEN2(Material.POTION),
	IRON_SWORD(Material.IRON_SWORD), LEATHER_HELMET(Material.LEATHER_HELMET), POTION_STRENGTH2(Material.POTION),
	LEATHER_CHESTPLATE(Material.LEATHER_CHESTPLATE), LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS), BOW2(Material.BOW),
	LEATHER_BOOTS(Material.LEATHER_BOOTS), POTION_STRENGTH(Material.POTION), POTION_HEAL2(Material.POTION),
	POTION_REGEN(Material.POTION), DIAMOND_SWORD2(Material.DIAMOND_SWORD), IRON_SWORD2(Material.IRON_SWORD),
	GOLD_SWORD(Material.GOLD_SWORD), WOOD_SWORD(Material.WOOD_SWORD), IRON_HELMET2(Material.IRON_HELMET),
	IRON_CHESTPLATE2(Material.IRON_CHESTPLATE), IRON_LEGGINGS2(Material.IRON_LEGGINGS), IRON_BOOTS2(Material.IRON_BOOTS),
	DIAMOND_HELMET2(Material.DIAMOND_HELMET), DIAMOND_CHESTPLATE2(Material.DIAMOND_CHESTPLATE),
	DIAMOND_LEGGINGS2(Material.DIAMOND_LEGGINGS), DIAMOND_BOOTS2(Material.DIAMOND_BOOTS), DIAMOND_SWORD3(Material.DIAMOND_SWORD),
	GOLD_HELMET1(Material.GOLD_HELMET), GOLD_CHESTPLATE1(Material.GOLD_CHESTPLATE), STONE_SWORD1(Material.STONE_SWORD),
	GOLD_LEGGINGS1(Material.GOLD_LEGGINGS), GOLD_BOOTS1(Material.GOLD_BOOTS), STONE_SWORD2(Material.STONE_SWORD),
	GOLD_HELMET2(Material.GOLD_HELMET), GOLD_CHESTPLATE2(Material.GOLD_CHESTPLATE), STONE_SWORD3(Material.STONE_SWORD),
	GOLD_LEGGINGS2(Material.GOLD_LEGGINGS), GOLD_BOOTS2(Material.GOLD_BOOTS), STONE_SWORD4(Material.STONE_SWORD);
	
	private Material material;
	
	private Items(Material material) {this.material = material;}
	
	public ItemStack generate() {
		
		ItemStack stack = ItemsUtil.generateItem(material, "");
		
		switch (this) {
			case POTION_STRENGTH: new Potion(PotionType.STRENGTH, 1).apply(stack); break;
			case POTION_STRENGTH2: new Potion(PotionType.STRENGTH, 2).apply(stack); break;
			case POTION_REGEN: new Potion(PotionType.REGEN, 1).apply(stack); break;
			case POTION_REGEN2: new Potion(PotionType.STRENGTH, 2).apply(stack); break;
			case POTION_HEAL: new Potion(PotionType.INSTANT_HEAL, 1).apply(stack); break;
			case POTION_HEAL2: new Potion(PotionType.INSTANT_HEAL, 2).apply(stack); break;
			
			case BOW: stack = Bow.item.generateItem(material); break;
			case BOW2: stack = BowFire.item.generateItem(material); break;
			case DIAMOND_SWORD2: stack = SwordPower.item.generateItem(material); break;
			case DIAMOND_SWORD3: stack = SwordSuperPower.item.generateItem(material); break;
			case IRON_SWORD2: stack = SwordFire.item.generateItem(material); break;
			case GOLD_SWORD: stack = SwordSuperPower.item.generateItem(material); break;
			case STONE_SWORD2: stack = SwordPower.item.generateItem(material); break;
			case STONE_SWORD3: stack = SwordFire.item.generateItem(material); break;
			case STONE_SWORD4: stack = SwordSuperPower.item.generateItem(material); break;
			
			case IRON_CHESTPLATE2: stack = ArmorThorns.item.generateItem(material); break;
			case IRON_LEGGINGS2: stack = ArmorThorns.item.generateItem(material); break;
			case IRON_BOOTS2: stack = ArmorThorns.item.generateItem(material); break;
			case IRON_HELMET2: stack = ArmorThorns.item.generateItem(material); break;
			
			case DIAMOND_HELMET2: stack = ArmorThorns.item.generateItem(material); break;
			case DIAMOND_CHESTPLATE2: stack = ArmorThorns.item.generateItem(material); break;
			case DIAMOND_LEGGINGS2: stack = ArmorThorns.item.generateItem(material); break;
			case DIAMOND_BOOTS2: stack = ArmorThorns.item.generateItem(material); break;
			
			case GOLD_HELMET2: stack = ArmorThorns.item.generateItem(material); break;
			case GOLD_CHESTPLATE2: stack = ArmorThorns.item.generateItem(material); break;
			case GOLD_LEGGINGS2: stack = ArmorThorns.item.generateItem(material); break;
			case GOLD_BOOTS2: stack = ArmorThorns.item.generateItem(material); break;
			
			default: break;
		}
		
		ItemsUtil.unbreakable(stack);
		
		return stack;
	}
	
}
