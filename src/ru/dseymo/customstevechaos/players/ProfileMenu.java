package ru.dseymo.customstevechaos.players;

import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.utils.ItemsUtil;
import ru.dseymo.customstevechaos.utils.Menu;

public class ProfileMenu extends Menu {
	
	private Menu from;
	
	public ProfileMenu(Player p, Menu from) {
		super(Main.getInstance().getLanguage("menus.profile.name").replace("%player%", p.getBP().getName()), 54, true);
		
		this.from = from;
		
		inv.setItem(0, ItemsUtil.generateItem(Material.SIGN, Main.getInstance().getLanguage("menus.profile.back")));
		
		ItemStack empty = ItemsUtil.generateItem(Material.STAINED_GLASS_PANE, (byte)14, " ");
		PlayerInventory pInv = p.getBP().getInventory();
		inv.setItem(2, pInv.getHelmet() == null || pInv.getHelmet().getType() == Material.AIR ? empty : pInv.getHelmet());
		inv.setItem(3, pInv.getChestplate() == null || pInv.getChestplate().getType() == Material.AIR ? empty : pInv.getChestplate());
		inv.setItem(5, pInv.getLeggings() == null || pInv.getLeggings().getType() == Material.AIR ? empty : pInv.getLeggings());
		inv.setItem(6, pInv.getBoots() == null || pInv.getBoots().getType() == Material.AIR ? empty : pInv.getBoots());
		inv.setItem(13, p.getPerk().getItem());
		for(int i = 0; i < 9; i++)
			inv.setItem(45 + i, pInv.getItem(i));
		for(int i = 9; i < pInv.getSize(); i++)
			inv.setItem(9 + i, pInv.getItem(i));
		
	}
	
	
	@Override
	public boolean onClick(org.bukkit.entity.Player _p, ItemStack item, int slot, ClickType click) {
		
		if(slot == 0) from.open(_p);
		
		return true;
	}

}
