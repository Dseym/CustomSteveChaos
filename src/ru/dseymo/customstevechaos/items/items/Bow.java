package ru.dseymo.customstevechaos.items.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class Bow extends Item {
	
	public static Bow item = new Bow();
	

	public Bow() {
		super("bow");
	}
	
	
	@Override
	public void onCreate(ItemStack stack) {
		
		stack.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		
	}

}
