package ru.dseymo.customstevechaos.items.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ArmorThorns extends Item {
	
	public static ArmorThorns item = new ArmorThorns();
	

	public ArmorThorns() {
		super("armor_thorns");
	}
	
	
	@Override
	public void onCreate(ItemStack stack) {
		
		stack.addUnsafeEnchantment(Enchantment.THORNS, 1);
		
	}
	
}
