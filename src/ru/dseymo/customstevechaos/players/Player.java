package ru.dseymo.customstevechaos.players;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.arenas.Arena;
import ru.dseymo.customstevechaos.players.perks.Perk;
import ru.dseymo.customstevechaos.utils.Board;
import ru.dseymo.customstevechaos.utils.Chat;

@Getter
public class Player {
	
	private UUID uuid;
	@Setter
	private Perk perk;
	private Arena arena;
	private int money = 0, lives = 3;
	@Getter
	private InfoDuel infoDuel = new InfoDuel();
	@Getter
	private Board board;
	
	public Player(org.bukkit.entity.Player p) {this(p.getUniqueId());}
	public Player(UUID uuid) {
		
		this.uuid = uuid;
		board = new Board(this, Main.getInstance().getLanguage("scoreboard.name"));
		
	}
	
	public void remove() {
		
		if(arena != null) {
			
			arena.stop();
			arena.setPl(null);
			arena = null;
		
		}
		removeBoard();
		
	}
	
	public void removeBoard() {
		if(board == null) return;
		
		Board _board = board;
		board = null;
		_board.remove();
		
	}
	
	public org.bukkit.entity.Player getBP() {return Bukkit.getPlayer(uuid);}
	public boolean isSpec() {return getBP().getGameMode() == GameMode.SPECTATOR;}
	public void setSpec() {if(!isSpec()) getBP().setGameMode(GameMode.SPECTATOR);}
	
	public boolean isMoney(int money) {return this.money >= money;}
	public boolean withdraw(int money) {
		if(money < 1) return true;
		if(isMoney(money)) {
			this.money -= money;
			Chat.INFO.send(getBP(), Main.getInstance().getLanguage("messages.info.withdraw").replace("%money%", money + ""));
			return true;
		}
		else {Chat.INFO.send(getBP(), Main.getInstance().getLanguage("messages.fail.notEnoughMoney")); return false;}
	}
	public void deposit(int money) {
		if(money < 1) return;
		
		this.money += money;
		Chat.INFO.send(getBP(), Main.getInstance().getLanguage("messages.info.deposit").replace("%money%", money + ""));
		
	}
	
	public boolean removeLive() {
		if(lives < 1 || isSpec()) return true;
		
		if(--lives < 1) {
			
			setSpec();
			Chat.INFO.sendAll(Main.getInstance().getLanguage("messages.info.playerLose").replace("%player%", getBP().getName()));
			
		} else Chat.INFO.sendAll(Main.getInstance().getLanguage("messages.info.playerLostLive").replace("%player%", getBP().getName()).replace("%lives%", lives + ""));
		
		return lives < 1;
	}
	public void addLive() {lives++;}
	
	public void setArena(Arena arena) {
		
		this.arena = arena;
		arena.setPl(this);
		
	}
	
	
	@Override
	public boolean equals(Object p) {
		
		if(p == null || !(p instanceof Player)) return false;
		
		return ((Player)p).uuid.equals(uuid);
		
	}
	
}
