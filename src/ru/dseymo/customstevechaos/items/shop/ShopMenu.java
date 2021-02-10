package ru.dseymo.customstevechaos.items.shop;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.items.items.BookDamage;
import ru.dseymo.customstevechaos.items.items.BookHealth;
import ru.dseymo.customstevechaos.items.items.BookRegen;
import ru.dseymo.customstevechaos.items.items.Midas;
import ru.dseymo.customstevechaos.items.items.Thorns;
import ru.dseymo.customstevechaos.utils.Chat;
import ru.dseymo.customstevechaos.utils.ItemsUtil;
import ru.dseymo.customstevechaos.utils.Menu;

public class ShopMenu extends Menu {
	
	private HashMap<Integer, ItemStack> slots = new HashMap<>();
	private HashMap<Integer, Integer> cost = new HashMap<>();
	
	public ShopMenu() {
		super(Main.getInstance().getLanguage("menus.shop.name"), 27, true);
		
		slots.put(11, Midas.item.generateItem(Material.GOLD_NUGGET));
		slots.put(12, BookDamage.item.generateItem(Material.BOOK));
		slots.put(13, BookHealth.item.generateItem(Material.BOOK));
		slots.put(14, BookRegen.item.generateItem(Material.BOOK));
		slots.put(15, Thorns.item.generateItem(Material.MAGMA_CREAM));
		
		cost.put(11, 1200);
		cost.put(12, 1650);
		cost.put(13, 1400);
		cost.put(14, 1350);
		cost.put(15, 1800);
		
		for(int slot: slots.keySet())
			inv.setItem(slot, ItemsUtil.addToLore(slots.get(slot).clone(), Main.getInstance().getLanguage("menus.shop.cost").replace("%money%", cost.get(slot) + "")));
		
	}
	
	public boolean onClick(Player _p, ItemStack _item, int slot, ClickType click) {
		ru.dseymo.customstevechaos.players.Player p = Game.getInstance().getPlayer(_p.getUniqueId());
		ItemStack item = slots.get(slot);
		if(item != null) {
			int cost = this.cost.get(slot);
			if(p.withdraw(cost)) {
				
				_p.getInventory().addItem(item);
				Chat.SUCCESS.send(_p, Main.getInstance().getLanguage("messages.success.itemBought"));
				
				_p.closeInventory();
			}
			
		}
		
		return true;
	}
	
}
