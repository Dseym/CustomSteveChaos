package ru.dseymo.customstevechaos.game;

import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.duels.Duel;
import ru.dseymo.customstevechaos.map.Map;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.utils.BossBar;
import ru.dseymo.customstevechaos.utils.Chat;

public class GameTimer extends BukkitRunnable {
	
	@Getter
	private BossBar bar = new BossBar(Main.getInstance(), Main.getInstance().getLanguage("bossbar.waitingGame"));
	private Game game = Game.getInstance();
	private Duel duel = Duel.getInstance();

	@Override
	public void run() {

		autoStart();
		autoWave();
		checkWin();
		boostMob();
		timerToBoost();
		
	}
	
	private boolean boostEnable = false;
	private int boostTime = 0;
	private void boostMob() {
		if(game.getStatus() != Status.WAVE || !boostEnable) {boostEnable = false; boostTime = 0; return;}
		boostTime++;
		
		bar.setTitle(Main.getInstance().getLanguage("bossbar.timerToDeath"));
		bar.setProgress((40-((double)boostTime))/40.0);
		
		if(boostTime % 10 == 0) {
			for(Player p: game.getNotSpecPlayers())
				if(!p.getArena().isDone()) {
					
					p.getArena().boostMobs();
					checkWin();
					
				}
			if(boostTime != 40)
				Chat.FAIL.sendAll(Main.getInstance().getLanguage("messages.fail.mobsBoost"));
		}
		
	}
	
	private int timeToBoost = 30;
	private void timerToBoost() {
		if(game.getStatus() != Status.WAVE || boostEnable) {timeToBoost = 30; return;}
		
		if(--timeToBoost < 1) boostEnable = true;
		else {
			
			bar.setTitle(Main.getInstance().getLanguage("bossbar.timerToBoost"));
			bar.setProgress(((double)timeToBoost)/30.0);
			
		}
		
	}

	private void checkWin() {
		if(!game.isStart()) return;
		if(game.getNotSpecPlayers().size() < 1) game.stop();
		if(game.getNotSpecPlayers().size() != 1) return;
		
		for(Player p: game.getPlayers())
			Chat.INFO.send(p.getBP(), Main.getInstance().getLanguage("messages.info.playerWin").replace("%player%", game.getNotSpecPlayers().get(0).getBP().getName()));
		
		game.stop();
		
	}

	private int timeToWave = 30;
	private void autoWave() {
		if(game.getStatus() != Status.WAITING_WAVE) {
			timeToWave = 30;
			return;
		}
		
		if(--timeToWave < 1) {
			
			timeToWave = 30;
			game.getWave().start();
			new BukkitRunnable() {
				
				@Override
				public void run() {duel.start();}
				
			}.runTaskLater(Main.getInstance(), 100);
			
		} else {
			
			bar.setTitle(Main.getInstance().getLanguage("bossbar.waitingWave").replace("%wave%", (game.getWave().getWave()) + "").replace("%nameWave%", game.getWave().getPack().getName()));
			bar.setProgress(((double)timeToWave)/30.0);
			
		}
		
	}
	
	private int timeToStart = 60;
	private void autoStart() {
		if(game.getStatus() != Status.WAITING_GAME) {
			timeToStart = 60;
			return;
		} else if(game.getNotSpecPlayers().size() < Main.getInstance().getMinPlayers()) {
			bar.setTitle(Main.getInstance().getLanguage("bossbar.waitingPlayers").replace("%players%", (Main.getInstance().getMinPlayers()-game.getNotSpecPlayers().size())+ ""));
			bar.setProgress(1.0);
			timeToStart = 60;
			return;
		}
		if(game.getNotSpecPlayers().size() == Map.getInstance().getArenas().size() && timeToStart > 10) timeToStart = 10;
		
		if(--timeToStart < 1) {timeToStart = 60; game.start();}
		else {
			
			bar.setTitle(Main.getInstance().getLanguage("bossbar.waitingGame"));
			bar.setProgress(((double)timeToStart)/60.0);
			
		}
		
	}

}
