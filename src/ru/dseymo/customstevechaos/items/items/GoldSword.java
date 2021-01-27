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
		
		stack.addEnchantment(Enchantment.FIRE_ASPECT, 2);
		stack.addEnchantment(Enchantment.KNOCKBACK, 3);
		stack.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		
	}
	
}
