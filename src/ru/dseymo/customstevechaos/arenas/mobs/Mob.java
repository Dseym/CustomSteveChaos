package ru.dseymo.customstevechaos.arenas.mobs;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.projectiles.ProjectileSource;

import ru.dseymo.customstevechaos.Main;

public class Mob implements Listener {
	
	private String id;
	
	protected Mob(String id) {
		this.id = id;
		
		Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
	}
	
	public void unregister() {
		
		EntityDeathEvent.getHandlerList().unregister(this);
		EntityDamageByEntityEvent.getHandlerList().unregister(this);
		EntityDamageEvent.getHandlerList().unregister(this);
		
	}
	
	public LivingEntity spawn(EntityType type, Location loc) {
		LivingEntity ent = (LivingEntity)loc.getWorld().spawnEntity(loc, type);
		if(onSpawn(ent)) {
			ent.remove();
			return null;
		}
		
		ent.setMetadata("id", new FixedMetadataValue(Main.getInstance(), id));
		
		return ent;
	}
	
	public boolean isMob(LivingEntity ent) {return ent.getMetadata("id").size() != 0 && ent.getMetadata("id").get(0).asString().equals(id);}
	
	
	@EventHandler
	public void death(EntityDeathEvent e) {
		LivingEntity ent = e.getEntity();
		if(!isMob(ent)) return;
		
		onDeath(e);
	}
	
	@EventHandler
	public void damageEntity(EntityDamageByEntityEvent e) {
		Entity damager = e.getDamager();
		if(damager instanceof Projectile) {
			ProjectileSource shooter = ((Projectile)damager).getShooter();
			if(shooter instanceof LivingEntity) damager = (LivingEntity)shooter;
		}
		if(!(damager instanceof LivingEntity) || !isMob((LivingEntity)damager)) return;
		
		onHit(e);
	}
	
	@EventHandler
	public void damage(EntityDamageEvent e) {
		Entity ent = e.getEntity();
		if(!(ent instanceof LivingEntity) || !isMob((LivingEntity)ent)) return;
		
		onDamage(e);
	}
	
	@EventHandler
	public void damage(EntityDamageByEntityEvent e) {
		Entity ent = e.getEntity();
		if(!(ent instanceof LivingEntity) || !isMob((LivingEntity)ent)) return;
		
		onDamageByEntity(e);
	}
	
	protected boolean onSpawn(LivingEntity ent) {return false;}
	protected void onDeath(EntityDeathEvent e) {}
	protected void onHit(EntityDamageByEntityEvent e) {}
	protected void onDamage(EntityDamageEvent e) {}
	protected void onDamageByEntity(EntityDamageByEntityEvent e) {}
	
}
