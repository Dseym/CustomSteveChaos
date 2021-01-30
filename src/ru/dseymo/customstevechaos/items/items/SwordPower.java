package ru.dseymo.customstevechaos.items.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class SwordPower extends Item {
	
	public static SwordPower item = new SwordPower();
	
	
	protected SwordPower() {
		super("sword_power");
	}
	
	
	@Override
	public void onCreate(ItemStack stack) {
		
		stack.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
		stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
		
	}

}
