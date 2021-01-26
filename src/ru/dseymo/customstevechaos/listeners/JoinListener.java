package ru.dseymo.customstevechaos.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.customstevechaos.utils.ChatUtil;

public class JoinListener implements Listener {
	
	@EventHandler
	private void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		ChatUtil.info(p, "&9Hi, this is the plugin creator, &lDseymo&9!",
						 "It might be interesting &bhttps://www.youtube.com/c/Dseymo");
		
		p.setGameMode(GameMode.ADVENTURE);
		p.getInventory().clear();
		p.getInventory().setArmorContents(new ItemStack[] {null, null, null, null});
		p.setMaxHealth(20);
		p.setHealth(p.getMaxHealth());
		p.setFoodLevel(20);
		p.setExp(0);
		p.setWalkSpeed(0.2f);
		
	}
	
	@EventHandler
	private void quit(PlayerQuitEvent e) {
		e.setQuitMessage("");
	}
	
}
