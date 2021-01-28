package ru.dseymo.customstevechaos.items.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class GoldSword extends Item {
	
	public static GoldSword item = new GoldSword();
	

	protected GoldSword() {
		super("goldsword");
	}
	
	
	@Override
	public void onCreate(ItemStack stack) {
		
		stack.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
		stack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
		stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		
	}
	
}
