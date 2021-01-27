package ru.dseymo.customstevechaos.items.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class DiamondSwordPower extends Item {
	
	public static DiamondSwordPower item = new DiamondSwordPower();
	
	
	protected DiamondSwordPower() {
		super("diamondswordpower");
	}
	
	
	@Override
	public void onCreate(ItemStack stack) {
		
		stack.addEnchantment(Enchantment.FIRE_ASPECT, 1);
		stack.addEnchantment(Enchantment.KNOCKBACK, 1);
		
	}

}
