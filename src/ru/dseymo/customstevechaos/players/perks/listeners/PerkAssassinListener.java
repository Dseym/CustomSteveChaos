package ru.dseymo.customstevechaos.players.perks.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.perks.Perk;

public class PerkAssassinListener implements Listener {
	
	@EventHandler()
	private void move(PlayerMoveEvent e) {
		Player p = Game.getInstance().getPlayer(e.getPlayer().getUniqueId());
		org.bukkit.entity.Player _p = p.getBP();
		if(p.getPerk() == null || p.getPerk() != Perk.ASSASSIN) return;
		_p.setWalkSpeed((float)(_p.getWalkSpeed() + (_p.getWalkSpeed()*0.4)));
		
	}
	
}
