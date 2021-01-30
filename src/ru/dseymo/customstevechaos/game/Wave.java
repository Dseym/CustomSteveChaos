package ru.dseymo.customstevechaos.game;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.arenas.Arena;
import ru.dseymo.customstevechaos.arenas.PackMobs;
import ru.dseymo.customstevechaos.events.PlayerEndWaveEvent;
import ru.dseymo.customstevechaos.events.WaveStartEvent;
import ru.dseymo.customstevechaos.map.Map;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.utils.Chat;

public class Wave implements Listener {
	
	@Getter
	private Game game;
	@Getter
	private PackMobs pack;
	@Getter
	private int wave = 0;
	
	public Wave(Game game) {
		this.game = game;
		Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				if(game.getStatus() != Status.WAVE || game.getNotSpecPlayers().size() < 2 || Map.getInstance().getDuel().isCreate()) return;
				for(Player p: game.getNotSpecPlayers())
					if(!p.getArena().isDone())
						return;
				
				Map.getInstance().getDuel().newDuel();
				game.giveItem();
				nextWave();
				Chat.SUCCESS.sendAll(Main.getInstance().getLanguage("messages.success.waveEnded"));
				
				game.setStatus(Status.WAITING_WAVE);
			}
			
		}.runTaskTimer(Main.getInstance(), 20, 20);
		
	}
	
	public void remove() {
		
		game = null;
		PlayerEndWaveEvent.getHandlerList().unregister(this);
		
	}
	
	public void nextWave() {
		
		pack = PackMobs.values()[new Random().nextInt(PackMobs.values().length)];
		wave++;
		
	}
	
	public void start() {
		if(game.getStatus() != Status.WAITING_WAVE) return;
		
		WaveStartEvent event = new WaveStartEvent(wave, pack);
		Bukkit.getPluginManager().callEvent(event);
		if(event.isCancelled()) return;
		
		for(Player p: game.getNotSpecPlayers()) {
			if(Map.getInstance().getDuel().isMember(p)) continue;
			
			p.getBP().closeInventory();
			Arena arena = p.getArena();
			p.getBP().teleport(arena.getSpawn());
			p.getArena().spawnMobs(pack);
			for(PotionEffect eff: p.getBP().getActivePotionEffects())
				p.getBP().removePotionEffect(eff.getType());
			
			String[] mess = Main.getInstance().getLanguageArray("messages.info.waveStarted");
			for(int i = 0; i < mess.length; i++)
				mess[i] = mess[i].replace("%wave%", wave + "");
			Chat.INFO.send(p.getBP(), mess);
			
		}
		
		game.setStatus(Status.WAVE);
		
	}
	
	
	@EventHandler
	public void waveEnded(PlayerEndWaveEvent e) {
		
		org.bukkit.entity.Player _p = e.getPlayer().getBP();
		new BukkitRunnable() {@Override public void run() {if(_p != null) _p.setHealth(_p.getMaxHealth());}}.runTaskLater(Main.getInstance(), 5);
		for(PotionEffect eff: _p.getActivePotionEffects())
			_p.removePotionEffect(eff.getType());
		_p.setMaxHealth(_p.getMaxHealth() + 2);
		
		if(!e.isDuel()) Chat.INFO.sendAll(Main.getInstance().getLanguage("messages.info.playerWinWave").replace("%player%", _p.getName()));
		
	}
	
}
