package ru.dseymo.customstevechaos.players.perks;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.events.PerkSelectEvent;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.utils.ChatUtil;
import ru.dseymo.customstevechaos.utils.Menu;

public class SelectPerkMenu extends Menu {
	
	private HashMap<Integer, Perk> perks = new HashMap<>();
	
	public SelectPerkMenu() {
		super(Main.getInstance().getLanguage("menus.selectPerk"), 9, true);
		
		List<Perk> perks = Arrays.asList(Perk.values());
		Collections.shuffle(perks);
		
		if(perks.size() > 0) this.perks.put(2, perks.get(0));
		if(perks.size() > 1) this.perks.put(4, perks.get(1));
		if(perks.size() > 2) this.perks.put(6, perks.get(2));
		for(Entry<Integer, Perk> set: this.perks.entrySet())
			inv.setItem(set.getKey(), set.getValue().getItem());
		
	}
	
	
	private boolean selected = false;
	@Override
	public boolean onClick(Player _p, ItemStack item, int slot, ClickType click) {
		
		Perk perk = perks.get(slot);
		if(perk != null) {
			
			ru.dseymo.customstevechaos.players.Player p = Game.getInstance().getPlayer(_p.getUniqueId());
			PerkSelectEvent event = new PerkSelectEvent(perk, p);
			Bukkit.getPluginManager().callEvent(event);
			p.setPerk(event.getPerk());
			
			ChatUtil.success(_p, Main.getInstance().getLanguage("messages.success.perkSelected").replace("%perk%", perk.getName()));
			selected = true;
			
			_p.closeInventory();
		}
		
		return true;
	}
	
	@Override
	public void onClose(Player _p) {
		if(selected) return;
		
		Perk perk = (Perk)perks.values().toArray()[new Random().nextInt(perks.size())];
		ru.dseymo.customstevechaos.players.Player p = Game.getInstance().getPlayer(_p.getUniqueId());
		PerkSelectEvent event = new PerkSelectEvent(perk, p);
		Bukkit.getPluginManager().callEvent(event);
		p.setPerk(event.getPerk());
		
		ChatUtil.success(_p, Main.getInstance().getLanguage("messages.success.perkSelected").replace("%perk%", perk.getName()));
		
	}
	
}
