package ru.dseymo.customstevechaos.items.items;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.events.GameStopEvent;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.utils.Chat;
import ru.dseymo.customstevechaos.utils.ItemsUtil;

public class BookDamage extends Item {
	
	public static BookDamage item = new BookDamage();
	

	protected BookDamage() {
		super("book_damage");
	}
	
	
	@EventHandler
	public void damage(EntityDamageByEntityEvent e) {
		boolean bow = false;
		Entity damager = e.getDamager();
		if(damager instanceof Projectile) {
			ProjectileSource shooter = ((Projectile)damager).getShooter();
			if(shooter instanceof LivingEntity) {damager = (LivingEntity)shooter; bow = true;}
		}
		if(!(damager instanceof LivingEntity)) return;
		if(!uuids.containsKey(damager.getUniqueId())) return;
		
		e.setDamage(e.getDamage() + uuids.get(damager.getUniqueId())*(bow ? 0.5 : 1));
		
	}
	
	@EventHandler
	public void gameEnd(GameStopEvent e) {uuids.clear();}
	
	
	@Override
	public void onCreate(ItemStack stack) {
		
		ItemsUtil.setName(stack, Main.getInstance().getLanguage("items.bookDamage.name"));
		ItemsUtil.setLore(stack, Main.getInstance().getLanguageArray("items.bookDamage.lore"));
		
	}
	
	private HashMap<UUID, Integer> uuids = new HashMap<>();
	@Override
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(!Game.getInstance().isStart()) return;
		
		uuids.put(p.getUniqueId(), uuids.containsKey(p.getUniqueId()) ? uuids.get(p.getUniqueId())+1 : 1);
		ItemStack stack = p.getInventory().getItemInHand();
		if(stack.getAmount() == 1) p.getInventory().setItemInHand(null);
		else {
			stack.setAmount(stack.getAmount()-1);
			p.getInventory().setItemInHand(stack);
		}
		
		Chat.INFO.send(p, Main.getInstance().getLanguage("items.bookDamage.used"));
		
	}
	
}
