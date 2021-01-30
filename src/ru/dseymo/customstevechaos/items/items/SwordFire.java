package ru.dseymo.customstevechaos.items.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class SwordFire extends Item {
	
	public static SwordFire item = new SwordFire();
	

	protected SwordFire() {
		super("sword_fire");
	}
	
	@Override
	public void onCreate(ItemStack stack) {
		
		stack.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
		
	}
	
}
