package ru.dseymo.customstevechaos.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.arenas.Arena;
import ru.dseymo.customstevechaos.duels.Duel;
import ru.dseymo.customstevechaos.events.GameStartEvent;
import ru.dseymo.customstevechaos.events.GameStopEvent;
import ru.dseymo.customstevechaos.items.SelectItemMenu;
import ru.dseymo.customstevechaos.map.Map;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.perks.SelectPerkMenu;
import ru.dseymo.customstevechaos.utils.Chat;
import ru.dseymo.customstevechaos.utils.ItemsUtil;
import ru.dseymo.customstevechaos.utils.Menu;

public class Game implements Listener {
	
	@Getter
	private static Game instance = new Game();
	
	
	private HashMap<UUID, Player> players = new HashMap<>();
	@Getter @Setter
	private Status status = Status.WAITING_GAME;
	@Getter
	private Wave wave;
	
	public Game() {Bukkit.getPluginManager().registerEvents(this, Main.getInstance());}
	
	public boolean isStart() {return status == Status.WAITING_WAVE || status == Status.WAVE;}
	public void start() {
		if(status != Status.WAITING_GAME || getNotSpecPlayers().size() < Main.getInstance().getMinPlayers()) return;
		
		GameStartEvent event = new GameStartEvent(this);
		Bukkit.getPluginManager().callEvent(event);
		if(event.isCancelled()) return;
		
		status = Status.WAITING_WAVE;
		wave = new Wave(this);
		wave.nextWave();
		
		ArrayList<Arena> arenas = Map.getInstance().getArenas();
		Collections.shuffle(arenas);
		int i = 0;
		for(Player p: getNotSpecPlayers()) {
			
			SelectPerkMenu menu = new SelectPerkMenu();
			menu.open(p.getBP());
			p.setArena(arenas.get(i));
			p.getBP().getInventory().setItem(17, ItemsUtil.generateItem(Material.ARROW, ""));
			i++;
			
		}
		
	}
	
	public void stop() {
		if(!isStart()) return;
		status = Status.STOPPED;
		
		Bukkit.getPluginManager().callEvent(new GameStopEvent(this, getNotSpecPlayers().size() == 0 ? null : getNotSpecPlayers().get(0)));
		
		Duel.getInstance().remove();
		
		for(Arena arena: Map.getInstance().getArenas())
			arena.stop();
		for(Player p: players.values()) {
			Chat.INFO.send(p.getBP(), Main.getInstance().getLanguage("messages.info.gameStopped"));
			p.setSpec();
		}
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for(Player p: getPlayers())
					p.getBP().kickPlayer(Main.getInstance().getLanguage("messages.info.gameStopped"));
				
				status = Status.WAITING_GAME;
				wave.remove();
				wave = null;
				
			}
			
		}.runTaskLater(Main.getInstance(), 100);
	}
	
	public void giveItem() {
		if(!isStart() || !Arrays.asList(1, 2, 4, 6, 8, 11, 14).contains(wave.getWave())) return;
		
		for(Player p: getNotSpecPlayers()) {
			
			Menu menu = new SelectItemMenu();
			menu.open(p.getBP());
			
		}
		
	}
	
	
	public Player getPlayer(UUID uuid) {return players.get(uuid);}
	public ArrayList<Player> getPlayers() {return new ArrayList<>(players.values());}
	public ArrayList<Player> getNotSpecPlayers() {
		
		ArrayList<Player> players = new ArrayList<>();
		for(Player p: this.players.values())
			if(!p.isSpec())
				players.add(p);
		
		return players;
	}
	
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		org.bukkit.entity.Player _p = e.getPlayer();
		Player p = new Player(e.getPlayer());
		if(status != Status.WAITING_GAME) {
			if(!_p.hasPermission("customstevechaos.admin")) {
				_p.kickPlayer(Main.getInstance().getLanguage("messages.fail.alreadyStarted"));
				return;
			} else p.setSpec();
		} else if(Map.getInstance().getArenas().size()-1 < players.size()) {
			if(!_p.hasPermission("customstevechaos.admin")) {
				_p.kickPlayer(Main.getInstance().getLanguage("messages.fail.maxPlayers"));
				return;
			} else p.setSpec();
		}
		
		_p.teleport(Map.getInstance().getLobby());
		players.put(p.getUuid(), p);
		Main.getInstance().getTimer().getBar().addPlayer(_p);
		if(!p.isSpec()) e.setJoinMessage(Main.getInstance().getLanguage("messages.info.joinPlayer").replace("%player%", _p.getName()).replace("%players%", getNotSpecPlayers().size() + "").replace("%maxPlayersCount%", Map.getInstance().getArenas().size() + ""));
		else e.setJoinMessage("");
		
	}
	
	@EventHandler
	public void quit(PlayerQuitEvent e) {
		UUID uuid = e.getPlayer().getUniqueId();
		Player p = players.get(uuid);
		if(p == null) return;
		p.remove();
		
		players.remove(uuid);
		e.setQuitMessage(Main.getInstance().getLanguage("messages.info.quitPlayer").replace("%player%", p.getBP().getName()).replace("%players%", getNotSpecPlayers().size() + "").replace("%maxPlayersCount%", Map.getInstance().getArenas().size() + ""));
		
	}
	
}
