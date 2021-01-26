package ru.dseymo.customstevechaos.listeners;

import java.util.Arrays;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import ru.dseymo.customstevechaos.duels.Duel;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.game.Status;
import ru.dseymo.customstevechaos.map.Map;
import ru.dseymo.customstevechaos.players.Player;

public class CancelListener implements Listener {
	
	@EventHandler
	private void cancelDamagePlayer(EntityDamageEvent e) {
		if(e.getEntityType() != EntityType.PLAYER) return;
		Player p = Game.getInstance().getPlayer(((org.bukkit.entity.Player)e.getEntity()).getUniqueId());
		
		Duel duel = Map.getInstance().getDuel();
		if((duel.isMember(p) && duel.isStart()) || (p.getArena() != null && !p.getArena().isDone())) return;
		
		e.setCancelled(true);
	}
	
	@EventHandler
	private void cancelRegen(EntityRegainHealthEvent e) {
		if(Arrays.asList(RegainReason.EATING, RegainReason.SATIATED).contains(e.getRegainReason()))
			e.setCancelled(true);
	}
	
	@EventHandler
	private void cancelHunger(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	private void cancelPotion(PlayerItemConsumeEvent e) {
		Player p = Game.getInstance().getPlayer(((org.bukkit.entity.Player)e.getPlayer()).getUniqueId());
		
		if((Map.getInstance().getDuel().isMember(p) && Game.getInstance().getStatus() == Status.WAVE) || (p.getArena() != null && !p.getArena().isDone())) return;
		
		e.setCancelled(true);
	}
	
	@EventHandler
	private void cancelDrop(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	
}
