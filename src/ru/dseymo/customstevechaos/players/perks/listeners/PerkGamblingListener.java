package ru.dseymo.customstevechaos.players.perks.listeners;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import ru.dseymo.customstevechaos.duels.Duel;
import ru.dseymo.customstevechaos.events.DuelStopEvent;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.perks.Perk;

public class PerkGamblingListener implements Listener {
	
	@EventHandler
	private void duelStop(DuelStopEvent e) {
		Duel duel = e.getDuel();
		Player winner = e.getWinner();
		HashMap<Player, Integer> rates = duel.getP1().equals(winner) ? duel.getRate1() : duel.getRate2();
		for(Entry<Player, Integer> set: rates.entrySet()) {
			if(set.getKey().getPerk() == null || set.getKey().getPerk() != Perk.GAMBLING) continue;
			
			set.setValue((int)(set.getValue() + (set.getValue()*0.2)));
			
		}
		
		
	}
	
}
