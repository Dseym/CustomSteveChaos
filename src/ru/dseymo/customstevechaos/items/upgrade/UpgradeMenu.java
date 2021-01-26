package ru.dseymo.customstevechaos.items.upgrade;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.utils.ChatUtil;
import ru.dseymo.customstevechaos.utils.ItemsUtil;
import ru.dseymo.customstevechaos.utils.Menu;

public class UpgradeMenu extends Menu {
	
	public static ItemStack upgrade(ItemStack stack) {
		Enchantment ench = getEnch(stack);
		if(ench == null) return null;
		
		stack.addUnsafeEnchantment(ench, stack.containsEnchantment(ench) ? stack.getEnchantmentLevel(ench)+1 : 1);
		
		return stack;
	}
	
	public static int getCost(ItemStack stack) {
		Enchantment ench = getEnch(stack);
		if(ench == null) return -1;
		
		return stack.getEnchantmentLevel(ench)*300+200;
	}
	
	private static Enchantment getEnch(ItemStack stack) {
		
		String strType = stack.getType().toString();
		if(contains(strType, "SWORD")) return Enchantment.DAMAGE_ALL;
		else if(contains(strType, "HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS"))
			return Enchantment.PROTECTION_ENVIRONMENTAL;
		else if(contains(strType, "BOW")) return Enchantment.ARROW_DAMAGE;
		else return null;
		
	}
	
	private static boolean contains(String str, String... strs) {
		for(String _str: strs)
			if(str.contains(_str)) return true;
		
		return false;
	}
	

	private ItemStack stack;
	private ItemStack upgradeStack;
	private int cost;
	
	public UpgradeMenu() {super(Main.getInstance().getLanguage("menus.upgrade.name"), 45);}
	
	
	private boolean buy = false;
	@Override
	public boolean onClick(org.bukkit.entity.Player _p, ItemStack item, int slot, ClickType click) {
		if(slot == 22) {
			Player p = Game.getInstance().getPlayer(_p.getUniqueId());
			if(p.withdraw(cost)) {
				
				_p.getInventory().addItem(upgrade(stack));
				ChatUtil.success(_p, Main.getInstance().getLanguage("messages.success.itemUpgraded"));
				buy = true;
				
			}
			
			_p.closeInventory();
		}
		
		return true;
	}
	
	@Override
	public boolean onOpen(org.bukkit.entity.Player p) {
		
		stack = p.getInventory().getItemInHand();
		p.getInventory().setItemInHand(null);
		if(stack == null) {
			
			ChatUtil.fail(p, Main.getInstance().getLanguage("messages.fail.selectItem"));
			return true;
			
		}
		upgradeStack = upgrade(stack.clone());
		if(upgradeStack == null) {
			
			ChatUtil.fail(p, Main.getInstance().getLanguage("messages.fail.itemCannotUpgrade"));
			return true;
			
		}
		
		cost = getCost(upgradeStack);
		ItemsUtil.addToLore(upgradeStack, "", Main.getInstance().getLanguage("menus.upgrade.costUpgrade").replace("%money%", cost + ""));
		inv.setItem(22, upgradeStack);
		
		return false;
	}
	
	@Override
	public void onClose(org.bukkit.entity.Player p) {
		if(!buy) p.getInventory().addItem(stack);
	}

}
