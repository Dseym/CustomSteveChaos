package ru.dseymo.customstevechaos.items.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.utils.ItemsUtil;

public class Item implements Listener {
	
	private String id;
	
	protected Item(String id) {
		this.id = id;
		
		Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
	}
	
	public void unregister() {
		
		PlayerInteractEvent.getHandlerList().unregister(this);
		PlayerDropItemEvent.getHandlerList().unregister(this);
		PlayerPickupItemEvent.getHandlerList().unregister(this);
		InventoryClickEvent.getHandlerList().unregister(this);
		PlayerItemHeldEvent.getHandlerList().unregister(this);
		
	}
	
	public ItemStack generateItem(Material material) {
		ItemStack stack = ItemsUtil.generateItem(material, "");
		
		stack.setItemMeta(ItemsUtil.setTag(stack, "id", id).getItemMeta());
		
		onCreate(stack);
		return stack;
	}
	
	public boolean isItem(ItemStack stack) {return stack == null || stack.getType() == Material.AIR ? false : ItemsUtil.getTag(stack, "id").equals(id);}
	
	@EventHandler
	public void interact(PlayerInteractEvent e) {
		if(!isItem(e.getItem())) return;
		
		onInteract(e);
	}
	
	@EventHandler
	public void interactEntity(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getInventory().getItemInHand();
		if(!isItem(p.getInventory().getItemInHand())) return;
		
		e.setCancelled(onInteractEntity(p, item, e.getRightClicked()));
	}
	
	@EventHandler
	public void hitEntity(EntityDamageByEntityEvent e) {
		Entity damager = e.getDamager();
		if(damager instanceof Projectile) {
			ProjectileSource shooter = ((Projectile)damager).getShooter();
			if(shooter instanceof LivingEntity) damager = (LivingEntity)shooter;
		}
		if(!(damager instanceof LivingEntity)) return;
		LivingEntity ent = (LivingEntity)damager;
		if(!isItem(ent.getEquipment().getItemInHand())) return;
		
		onHitEntity(e);
	}
	
	@EventHandler
	public void drop(PlayerDropItemEvent e) {
		if(!isItem(e.getItemDrop().getItemStack())) return;
		
		e.setCancelled(onDrop(e.getPlayer(), e.getItemDrop()));
	}
	
	@EventHandler
	public void pickUp(PlayerPickupItemEvent e) {
		if(!isItem(e.getItem().getItemStack())) return;
		
		e.setCancelled(onPickup(e.getPlayer(), e.getItem()));
	}
	
	@EventHandler
	public void clickInv(InventoryClickEvent e) {
		if(!isItem(e.getCurrentItem())) return;
		
		onClickInv(e);
	}
	
	@EventHandler
	public void selectHotbar(PlayerItemHeldEvent e) {
		if(!isItem(e.getPlayer().getInventory().getItem(e.getNewSlot()))) return;
		
		e.setCancelled(onSelectHotbar(e.getPlayer().getInventory().getItem(e.getNewSlot()), e.getNewSlot(), e.getPreviousSlot()));
	}
	
	protected void onCreate(ItemStack stack) {}
	protected void onInteract(PlayerInteractEvent e) {}
	protected boolean onInteractEntity(Player p, ItemStack item, Entity ent) {return false;}
	protected void onHitEntity(EntityDamageByEntityEvent e) {}
	protected boolean onDrop(Player p, org.bukkit.entity.Item item) {return false;}
	protected boolean onPickup(Player p, org.bukkit.entity.Item item) {return false;}
	protected void onClickInv(InventoryClickEvent e) {}
	protected boolean onSelectHotbar(ItemStack stack, int slot, int prevSlot) {return false;}
	
}
