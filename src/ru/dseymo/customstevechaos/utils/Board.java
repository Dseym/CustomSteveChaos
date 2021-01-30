package ru.dseymo.customstevechaos.utils;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import lombok.Getter;
import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.perks.Perk;

public class Board {
	
	@Getter
	private Scoreboard board;
	private Player player;
	private String title;
	private BukkitTask task;
	
	public Board(Player p, String title) {
		
		player = p;
		this.title = title;
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective(title, "dummy");
		obj.setDisplayName(title);
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		p.getBP().setScoreboard(board);
		
		task = new BukkitRunnable() {
			
			@Override
			public void run() {update();}
			
		}.runTaskTimer(Main.getInstance(), 20, 20);
	}
	
	public void remove() {
		
		task.cancel();
		player.getBP().setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		for(String str: board.getEntries())
			board.resetScores(str);
		
		player.removeBoard();
		player = null;
		
	}
	
	private void addLines(String... lines) {
		
		Objective obj = board.getObjective(title);
		for(int i = 0; i < lines.length; i++)
			obj.getScore(lines[i]).setScore(lines.length-i-1);
		
	}
	
	public void update() {
		if(!Game.getInstance().isStart()) return;
		
		for(String str: board.getEntries())
			board.resetScores(str);
		
		String[] lines = Main.getInstance().getLanguageArray("scoreboard.lines");
		for(int i = 0; i < lines.length; i++) {
			
			Perk perk = player.getPerk();
			
			lines[i] = lines[i].replace("%money%", "" + player.getMoney());
			lines[i] = lines[i].replace("%wave%", "" + Game.getInstance().getWave().getWave());
			lines[i] = lines[i].replace("%players%", "" + Game.getInstance().getNotSpecPlayers().size());
			lines[i] = lines[i].replace("%perk%", "" + (perk == null ? "" : perk.getName()));
			
		}
		
		addLines(lines);
	}
	
}
