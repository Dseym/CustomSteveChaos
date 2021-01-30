package ru.dseymo.customstevechaos.duels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import lombok.Getter;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.events.DuelCreateEvent;
import ru.dseymo.customstevechaos.events.DuelStartEvent;
import ru.dseymo.customstevechaos.events.DuelStopEvent;
import ru.dseymo.customstevechaos.events.PlayerEndWaveEvent;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.map.Map;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.utils.Chat;

public class Duel implements Listener {
	
	@Getter
	private Player p1, p2;
	@Getter
	private HashMap<Player, Integer> rate1 = new HashMap<>(), rate2 = new HashMap<>();
	@Getter
	private boolean start = false;
	@Getter
	private Location lView;
	private Location lP1, lP2;
	@Getter
	private SelectProfileMenu menu;
	private BukkitTask timer;
	
	public Duel(Location lView, Location lP1, Location lP2) {
		
		this.lView = lView;
		this.lP1 = lP1;
		this.lP2 = lP2;
		
	}
	
	public boolean isCreate() {return menu != null;}
	public void newDuel() {
		if(Game.getInstance().getWave().getWave() < 1 || isCreate()) return;
		
		ArrayList<Player> players = Game.getInstance().getNotSpecPlayers();
		Collections.shuffle(players);
		
		DuelCreateEvent event = new DuelCreateEvent(players.get(0), players.get(1));
		Bukkit.getPluginManager().callEvent(event);
		if(event.isCancelled()) return;
		
		this.p1 = event.getP1();
		this.p2 = event.getP2();
		
		String[] info = Main.getInstance().getLanguageArray("messages.info.infoDuel");
		for(int i = 0; i < info.length; i++)
			info[i] = info[i].replace("%p1%", p1.getBP().getName()).replace("%p2%", p2.getBP().getName());
		Chat.INFO.sendAll(info);
		
		menu = new SelectProfileMenu(this);
		TextComponent mainComponent = new TextComponent(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getLanguage("messages.info.clickToRate")));
		mainComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/game duelProfiles"));
		for(Player p: players)
			p.getBP().spigot().sendMessage(mainComponent);
		
		Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
	}
	
	public void remove() {
		if(!isCreate()) return;
		start = false;
		
		rate1.clear();
		rate2.clear();
		p1 = null;
		p2 = null;
		menu.remove();
		menu = null;
		if(timer != null) timer.cancel();
		
		PlayerQuitEvent.getHandlerList().unregister(this);
		EntityDamageEvent.getHandlerList().unregister(this);
		
		for(Player pl: Game.getInstance().getNotSpecPlayers()) {
			if(!pl.getArena().isDone()) continue;
			
			pl.getBP().teleport(Map.getInstance().getLobby());
			
		}
		
	}
	
	public void openMenu(Player p) {
		if(start) {
			Chat.FAIL.send(p.getBP(), Main.getInstance().getLanguage("messages.fail.duelAlreadyStarted"));
			return;
		} else if(!isCreate()) {
			Chat.FAIL.send(p.getBP(), Main.getInstance().getLanguage("messages.fail.duelNotFound"));
			return;
		}
		
		menu.open(p.getBP());
		
	}
	
	public boolean isMember(Player p) {return p.equals(p1) || p.equals(p2);}
	
	public void start() {
		if(start || !isCreate()) return;
		
		DuelStartEvent event = new DuelStartEvent(this);
		Bukkit.getPluginManager().callEvent(event);
		if(event.isCancelled()) return;
		
		start = true;
		
		p1.getBP().teleport(lP1);
		p2.getBP().teleport(lP2);
		
		timer = new BukkitRunnable() {
			
			@Override
			public void run() {
				
				org.bukkit.entity.Player _p1 = p1.getBP();
				org.bukkit.entity.Player _p2 = p2.getBP();
				if(_p1.getHealth() == _p2.getHealth())
					win(p1);
				else
					win(_p1.getHealth() > _p2.getHealth() ? p1 : p2);
				
			}
			
		}.runTaskLater(Main.getInstance(), 45*20);
		
	}
	
	
	public int getBank() {return getBankRate1() + getBankRate2();}
	public int getRate(Player p) {
		
		if(rate1.containsKey(p)) return rate1.get(p);
		else if(rate2.containsKey(p)) return rate2.get(p);
		else return 0;
	
	}
	
	public int getBankRate1() {
		
		int amount = 0;
		for(Integer money: rate1.values()) amount += money;
		
		return amount;
		
	}
	
	public int getBankRate2() {
		
		int amount = 0;
		for(Integer money: rate2.values()) amount += money;
		
		return amount;
		
	}
	
	public void addRate1(Player p, int money) {
		if(start || !isCreate()) return;
		
		if(rate2.containsKey(p)) {
			Chat.FAIL.send(p.getBP(), Main.getInstance().getLanguage("messages.fail.alreadyRate"));
			return;
		}
		if(isMember(p)) {
			Chat.FAIL.send(p.getBP(), Main.getInstance().getLanguage("messages.fail.youMemberDuel"));
			return;
		}
		
		if(p.withdraw(money))
			rate1.put(p, rate1.containsKey(p) ? rate1.get(p)+money : money);
		
	}
	
	public void addRate2(Player p, int money) {
		if(start || !isCreate()) return;
		
		if(rate1.containsKey(p)) {
			Chat.FAIL.send(p.getBP(), Main.getInstance().getLanguage("messages.fail.alreadyRate"));
			return;
		}
		if(isMember(p)) {
			Chat.FAIL.send(p.getBP(), Main.getInstance().getLanguage("messages.fail.youMemberDuel"));
			return;
		}
		
		if(p.withdraw(money))
			rate2.put(p, rate2.containsKey(p) ? rate2.get(p)+money : money);
		
	}
	
	public void win(Player p) {
		if(!p.equals(p1) && !p.equals(p2)) return;
		start = false;
		Player lose = p.equals(p1) ? p2 : p1;
		
		Bukkit.getPluginManager().callEvent(new DuelStopEvent(this, p));
		
		if(p.equals(p1)) {
			
			double k;
			if(getBank() != 0 && getBankRate1() != 0) k = getBank() / getBankRate1();
			else k = 1;
			
			for(Entry<Player, Integer> set: rate1.entrySet())
				set.getKey().deposit((int)(((double)set.getValue()) * k));
			
		} else {
			
			double k;
			if(getBank() != 0 && getBankRate2() != 0) k = getBank() / getBankRate2();
			else k = 1;
			
			for(Entry<Player, Integer> set: rate2.entrySet())
				set.getKey().deposit((int)(((double)set.getValue()) * k));
			
		}
		lose.getInfoDuel().lose();
		p.getInfoDuel().win();
		if(Game.getInstance().getWave().getWave() > 14)
			lose.removeLive();
		
		remove();
		int deposit = getBank() != 0 ? getBank()/(rate1.size()+rate2.size()) : 0;
		PlayerEndWaveEvent event = new PlayerEndWaveEvent(p, Game.getInstance().getWave().getWave(), deposit, true);
		Bukkit.getPluginManager().callEvent(event);
		deposit = event.getDeposit();
		p.deposit(deposit);
		Chat.INFO.sendAll(Main.getInstance().getLanguage("messages.info.duelEnded").replace("%player%", p.getBP().getName()));
		
		Bukkit.getPluginManager().callEvent(new PlayerEndWaveEvent(lose, Game.getInstance().getWave().getWave(), 0, true));
		
	}
	
	
	@EventHandler
	public void playerWin(EntityDamageEvent e) {
		if(e.getEntityType() != EntityType.PLAYER || !start || !isCreate()) return;
		org.bukkit.entity.Player _p = (org.bukkit.entity.Player)e.getEntity();
		if(_p.getHealth() > e.getDamage()) return;
		ru.dseymo.customstevechaos.players.Player p = Game.getInstance().getPlayer(_p.getUniqueId());
		if(!p.equals(p1) && !p.equals(p2)) return;
		
		win(p.equals(p1) ? p2 : p1);
		
		e.setCancelled(true);
	}
	
	@EventHandler
	public void playerQuit(PlayerQuitEvent e) {
		if(!start || !isCreate()) return;
		ru.dseymo.customstevechaos.players.Player p = Game.getInstance().getPlayer(e.getPlayer().getUniqueId());
		if(!p.equals(p1) && !p.equals(p2)) return;
		p.setSpec();
		
		win(p.equals(p1) ? p2 : p1);
		
	}
	
}
