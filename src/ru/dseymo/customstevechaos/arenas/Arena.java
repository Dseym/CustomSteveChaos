package ru.dseymo.customstevechaos.arenas;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.duels.Duel;
import ru.dseymo.customstevechaos.events.PlayerEndWaveEvent;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.map.Map;

public class Arena implements Listener {
	
	@Getter @Setter
	private ru.dseymo.customstevechaos.players.Player pl;
	@Getter
	private String name;
	@Getter
	private Location spawn, spawnMob;
	private ArrayList<LivingEntity> mobs = new ArrayList<>();
	private boolean spawning = false;
	
	public Arena(String name, Location spawn, Location spawnMob) {
		
		this.name = name;
		this.spawn = spawn;
		this.spawnMob = spawnMob;
		
		Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
	}
	
	public void spawnMobs(PackMobs pack) {
		if(!isDone()) return;
		
		int wave = Game.getInstance().getWave();
		boost = 0;
		spawning = true;
		spawn.getWorld().setDifficulty(Difficulty.HARD);
		spawn.getWorld().setSpawnFlags(false, false);
		for(LivingEntity ent: spawn.getWorld().getLivingEntities())
			if(!Arrays.asList(EntityType.ARMOR_STAND, EntityType.PLAYER).contains(ent.getType()))
				ent.remove();
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for(LivingEntity ent: pack.spawn(spawnMob)) {
					
					ent.setMaxHealth(wave*3);
					ent.setHealth(ent.getMaxHealth());
					mobs.add(ent);
					
				}
				
				spawning = false;
				
			}
			
		}.runTaskLater(Main.getInstance(), 100);
		
	}
	
	private int boost = 0;
	public void boostMobs() {
		if(++boost < 4)
			for(LivingEntity ent: mobs) {
				
				if(ent.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
					ent.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
				if(ent.hasPotionEffect(PotionEffectType.SPEED))
					ent.removePotionEffect(PotionEffectType.SPEED);
				ent.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999, boost/2));
				ent.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999, boost));
				
			}
		else {
			
			for(int i = 0; i < pl.getLives(); i++)
				pl.removeLive();
			
			PlayerEndWaveEvent event = new PlayerEndWaveEvent(pl, Game.getInstance().getWave(), 0, false);
			Bukkit.getPluginManager().callEvent(event);
			
			stop();
			
		}
			
		
	}
	
	public void stop() {
		if(isDone()) return;
		
		ArrayList<LivingEntity> mobs = new ArrayList<>(this.mobs);
		for(LivingEntity ent: mobs)
			ent.remove();
		
		Duel duel = Map.getInstance().getDuel();
		if(pl != null && pl.getBP() != null && !pl.isSpec()) pl.getBP().teleport(duel.isStart() ? duel.getLView() : Map.getInstance().getLobby());
		
	}
	
	public boolean isDone() {return !spawning ? mobs.size() < 1 : false;}
	
	
	@EventHandler
	private void deathMob(EntityDeathEvent e) {
		if(!mobs.contains(e.getEntity())) return;
		
		e.getDrops().clear();
		e.setDroppedExp(0);
		
		mobs.remove(e.getEntity());
		if(isDone()) {
			
			Duel duel = Map.getInstance().getDuel();
			pl.getBP().teleport(duel.isStart() ? duel.getLView() : Map.getInstance().getLobby());
			
			int deposit = Game.getInstance().getWave()*50 + 150;
			PlayerEndWaveEvent event = new PlayerEndWaveEvent(pl, Game.getInstance().getWave(), deposit, false);
			Bukkit.getPluginManager().callEvent(event);
			deposit = event.getDeposit();
			pl.deposit(deposit);
			
		}
		
	}
	
	@EventHandler
	private void playerDied(EntityDamageEvent e) {
		if(e.getEntityType() != EntityType.PLAYER || isDone()) return;
		Player _p = (Player)e.getEntity();
		if(_p.getHealth() > e.getDamage()) return;
		ru.dseymo.customstevechaos.players.Player p = Game.getInstance().getPlayer(_p.getUniqueId());
		if(p.getArena() != this) return;
		
		if(!p.removeLive()) {
			
			_p.setHealth(_p.getMaxHealth());
			_p.teleport(spawn);
			
		} else {
			
			PlayerEndWaveEvent event = new PlayerEndWaveEvent(p, Game.getInstance().getWave(), 0, false);
			Bukkit.getPluginManager().callEvent(event);
			
		}
		
		e.setCancelled(true);
	}
	
	
	@Override
	public void finalize() {
		
		EntityDamageEvent.getHandlerList().unregister(this);
		EntityDeathEvent.getHandlerList().unregister(this);
		
		spawn = null;
		spawnMob = null;
		pl = null;
		
	}
	
}
