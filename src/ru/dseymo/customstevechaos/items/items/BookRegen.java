package ru.dseymo.customstevechaos.items.items;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.events.GameStopEvent;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.utils.Chat;
import ru.dseymo.customstevechaos.utils.ItemsUtil;

public class BookRegen extends Item {
	
	public static BookRegen item = new BookRegen();
	

	protected BookRegen() {
		super("bookRegen");
	}
	
	@EventHandler
	public void regen(EntityRegainHealthEvent e) {
		if(e.getEntityType() != EntityType.PLAYER || !uuids.containsKey(e.getEntity().getUniqueId())) return;
		
		Player p = (Player)e.getEntity();
		Game.getInstance().getPlayer(p.getUniqueId()).regen(uuids.get(p.getUniqueId()));
		
	}
	
	@EventHandler
	public void gameEnd(GameStopEvent e) {uuids.clear();}
	
	
	@Override
	public void onCreate(ItemStack stack) {
		
		ItemsUtil.setName(stack, Main.getInstance().getLanguage("items.bookRegen.name"));
		ItemsUtil.setLore(stack, Main.getInstance().getLanguageArray("items.bookRegen.lore"));
		
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
		
		Chat.INFO.send(p, Main.getInstance().getLanguage("items.bookRegen.used"));
		
	}
	
}
