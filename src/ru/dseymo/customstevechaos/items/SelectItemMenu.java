package ru.dseymo.customstevechaos.items;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.utils.ChatUtil;
import ru.dseymo.customstevechaos.utils.Menu;

public class SelectItemMenu extends Menu {
	
	private HashMap<Integer, ItemStack> items = new HashMap<>();
	
	public SelectItemMenu() {
		super(Main.getInstance().getLanguage("menus.selectItem"), 45);
		
		List<Items> items = Arrays.asList(Items.values());
		Collections.shuffle(items);
		
		int i = 0;
		for(int num: Arrays.asList(10, 13, 16, 28, 31, 34)) {
			
			ItemStack item = items.get(i).generate();
			this.items.put(num, item);
			inv.setItem(num, item);
			i++;
			
		}
		
	}
	
	private boolean selected = false;
	@Override
	public boolean onClick(Player _p, ItemStack _item, int slot, ClickType click) {
		
		ItemStack item = items.get(slot);
		if(item != null) {
			
			_p.getInventory().addItem(item);
			ChatUtil.success(_p, Main.getInstance().getLanguage("messages.success.itemSelected"));
			selected = true;
			
			_p.closeInventory();
		}
		
		return true;
	}
	
	@Override
	public void onClose(Player p) {
		if(selected) return;
		
		ItemStack item = (ItemStack)items.values().toArray()[new Random().nextInt(items.size())];
		p.getInventory().addItem(item);
		ChatUtil.success(p, Main.getInstance().getLanguage("messages.success.itemSelected"));
		
	}

}
