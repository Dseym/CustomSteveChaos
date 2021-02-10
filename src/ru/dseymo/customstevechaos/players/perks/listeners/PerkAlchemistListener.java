package ru.dseymo.customstevechaos.players.perks.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;

import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.perks.Perk;

public class PerkAlchemistListener implements Listener {
	
	@EventHandler
	private void potion(PlayerItemConsumeEvent e) {
		Player p = Game.getInstance().getPlayer(e.getPlayer().getUniqueId());
		ItemStack item = e.getItem();
		if(p.getPerk() == null || p.getPerk() != Perk.ALCHEMIST || item.getType() != Material.POTION) return;
		
		Potion potion = Potion.fromItemStack(item);
		try {potion.setLevel(potion.getLevel()+1);} catch (Exception e2) {return;}
		potion.apply(item);
		e.setItem(item);
		
	}
	
}
