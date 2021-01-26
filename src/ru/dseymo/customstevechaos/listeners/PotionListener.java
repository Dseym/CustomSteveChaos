package ru.dseymo.customstevechaos.listeners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.customstevechaos.events.PlayerEndWaveEvent;

public class PotionListener implements Listener {
	
	private HashMap<UUID, ArrayList<ItemStack>> potions = new HashMap<>();
	
	@EventHandler(ignoreCancelled = true)
	private void potion(PlayerItemConsumeEvent e) {
		ItemStack item = e.getItem();
		Player p = e.getPlayer();
		if(item.getType() != Material.POTION) return;
		
		ArrayList<ItemStack> items = potions.containsKey(p.getUniqueId()) ? potions.get(p.getUniqueId()) : new ArrayList<>();
		items.add(item);
		
		potions.put(e.getPlayer().getUniqueId(), items);
		
		p.getInventory().setItemInHand(null);
		
	}
	
	@EventHandler
	private void endWave(PlayerEndWaveEvent e) {
		
		ru.dseymo.customstevechaos.players.Player p = e.getPlayer();
		ArrayList<ItemStack> items = potions.get(p.getUuid());
		if(items == null) return;
		
		potions.remove(p.getBP().getUniqueId());
		for(ItemStack item: items)
			p.getBP().getInventory().addItem(item);
		
	}
	
}
