package ru.dseymo.customstevechaos.utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.DragType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.customstevechaos.Main;

public class Menu implements Listener {
	
	protected Inventory inv;
	
	public Menu(String title, int size) {
		inv = Bukkit.createInventory(null, size, title);
		
		for(int i = 0; i < inv.getSize(); i++)
			inv.setItem(i, ItemsUtil.generateItem(Material.STAINED_GLASS_PANE, (byte)15, " "));
		
		Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
	}
	
	public void remove() {
		
		ArrayList<HumanEntity> viewers = new ArrayList<>(inv.getViewers());
		for(HumanEntity h: viewers)
			h.closeInventory();
		
		InventoryClickEvent.getHandlerList().unregister(this);
		InventoryDragEvent.getHandlerList().unregister(this);
		InventoryOpenEvent.getHandlerList().unregister(this);
		InventoryCloseEvent.getHandlerList().unregister(this);
		
	}
	
	public void open(Player p) {p.openInventory(inv);}
	
	
	@EventHandler
	public void click(InventoryClickEvent e) {
		
		if(inv.equals(e.getClickedInventory()))
			e.setCancelled(onClick((Player)e.getWhoClicked(), e.getCurrentItem(), e.getSlot(), e.getClick()));
		
	}
	
	@EventHandler
	public void drag(InventoryDragEvent e) {
		
		if(inv.equals(e.getInventory()))
			e.setCancelled(onDrag((Player)e.getWhoClicked(), e.getNewItems(), e.getInventorySlots(), e.getType()));
		
	}
	
	@EventHandler
	public void open(InventoryOpenEvent e) {
		
		if(inv.equals(e.getInventory()))
			e.setCancelled(onOpen((Player)e.getPlayer()));
		
	}
	
	@EventHandler
	public void close(InventoryCloseEvent e) {
		
		if(inv.equals(e.getInventory()))
			onClose((Player)e.getPlayer());
		
		if(inv.getViewers().size() == 0) remove();
		
	}
	
	protected boolean onClick(Player p, ItemStack item, int slot, ClickType click) {return true;}
	protected boolean onDrag(Player p, Map<Integer, ItemStack> items, Set<Integer> slots, DragType drag) {return true;}
	protected boolean onOpen(Player p) {return false;}
	protected void onClose(Player p) {}
	
}
