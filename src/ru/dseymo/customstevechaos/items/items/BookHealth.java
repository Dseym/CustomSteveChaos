package ru.dseymo.customstevechaos.items.items;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.utils.Chat;
import ru.dseymo.customstevechaos.utils.ItemsUtil;

public class BookHealth extends Item {
	
	public static BookHealth item = new BookHealth();
	

	protected BookHealth() {
		super("bookHealth");
	}
	
	
	@Override
	public void onCreate(ItemStack stack) {
		
		ItemsUtil.setName(stack, Main.getInstance().getLanguage("items.bookHealth.name"));
		ItemsUtil.setLore(stack, Main.getInstance().getLanguageArray("items.bookHealth.lore"));
		
	}
	
	@Override
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(!Game.getInstance().isStart()) return;
		
		Game.getInstance().getPlayer(p.getUniqueId()).addMaxHealth(4);
		ItemStack stack = p.getInventory().getItemInHand();
		if(stack.getAmount() == 1) p.getInventory().setItemInHand(null);
		else {
			stack.setAmount(stack.getAmount()-1);
			p.getInventory().setItemInHand(stack);
		}
		
		Chat.INFO.send(p, Main.getInstance().getLanguage("items.bookHealth.used"));
		
	}
	
}
