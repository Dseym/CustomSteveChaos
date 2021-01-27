package ru.dseymo.customstevechaos.items.items;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class IronSwordFire extends Item {
	
	public static IronSwordFire item = new IronSwordFire();
	

	protected IronSwordFire() {
		super("ironswordfire");
	}
	
	@Override
	public void onCreate(ItemStack stack) {
		
		stack.addEnchantment(Enchantment.FIRE_ASPECT, 1);
		
	}
	
}
