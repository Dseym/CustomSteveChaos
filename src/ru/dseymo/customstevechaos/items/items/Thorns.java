package ru.dseymo.customstevechaos.items.items;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.utils.Chat;
import ru.dseymo.customstevechaos.utils.ItemsUtil;

public class Thorns extends Item {
	
	public static Thorns item = new Thorns();
	

	protected Thorns() {
		super("thorns");
	}
	
	public void cooldown(ItemStack stack) {
		stack.setItemMeta(ItemsUtil.setTag(stack, "cooldown", (System.currentTimeMillis()+95000) + "").getItemMeta());
	}
	
	public boolean isCooldown(ItemStack stack) {
		String tag = ItemsUtil.getTag(stack, "cooldown");
		if(tag == null || tag.isEmpty()) return false;
		return Long.parseLong(ItemsUtil.getTag(stack, "cooldown")) > System.currentTimeMillis();
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void damage(EntityDamageByEntityEvent e) {
		Entity ent = e.getEntity();
		if(!uuids.contains(ent.getUniqueId())) return;
		
		e.setCancelled(true);
		
		Entity damager = e.getDamager();
		if(damager instanceof Projectile) {
			ProjectileSource shooter = ((Projectile)damager).getShooter();
			if(shooter instanceof LivingEntity) damager = (LivingEntity)shooter;
		}
		if(!(damager instanceof LivingEntity)) return;
		((LivingEntity)damager).damage(e.getDamage()/2);
		Bukkit.getPluginManager().callEvent(new EntityDamageEvent(damager, DamageCause.THORNS, e.getDamage()/2));
		
	}
	
	
	@Override
	public void onCreate(ItemStack stack) {
		
		ItemsUtil.setName(stack, Main.getInstance().getLanguage("items.thorns.name"));
		ItemsUtil.setLore(stack, Main.getInstance().getLanguageArray("items.thorns.lore"));
		
	}
	
	private ArrayList<UUID> uuids = new ArrayList<>();
	@Override
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(isCooldown(e.getItem())) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.cooldown"));
			return;
		}
		
		uuids.add(p.getUniqueId());
		new BukkitRunnable() {
			
			@Override
			public void run() {uuids.remove(p.getUniqueId());}
			
		}.runTaskLater(Main.getInstance(), 120);
		cooldown(e.getItem());
		Chat.INFO.send(p, Main.getInstance().getLanguage("items.thorns.used"));
		
	}
	
}
