package ru.dseymo.customstevechaos.items.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class BowFire extends Item {
	
	public static BowFire item = new BowFire();
	
	
	protected BowFire() {
		super("bowfire");
	}
	
	@Override
	public void onCreate(ItemStack stack) {
		
		stack.addEnchantment(Enchantment.ARROW_FIRE, 1);
		
	}
	
}
