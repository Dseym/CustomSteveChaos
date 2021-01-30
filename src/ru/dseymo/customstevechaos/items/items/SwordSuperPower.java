package ru.dseymo.customstevechaos.items.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class SwordSuperPower extends Item {
	
	public static SwordSuperPower item = new SwordSuperPower();
	

	protected SwordSuperPower() {
		super("sword_super_power");
	}
	
	
	@Override
	public void onCreate(ItemStack stack) {
		
		stack.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
		stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
		stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		
	}
	
}
