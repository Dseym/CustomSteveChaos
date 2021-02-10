package ru.dseymo.customstevechaos.items.items;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.utils.Chat;
import ru.dseymo.customstevechaos.utils.ItemsUtil;

public class Midas extends Item {
	
	public static Midas item = new Midas();
	

	protected Midas() {
		super("midas");
	}
	
	public void cooldown(ItemStack stack) {
		stack.setItemMeta(ItemsUtil.setTag(stack, "cooldown", (System.currentTimeMillis()+95000) + "").getItemMeta());
	}
	
	public boolean isCooldown(ItemStack stack) {
		String tag = ItemsUtil.getTag(stack, "cooldown");
		if(tag == null || tag.isEmpty()) return false;
		return Long.parseLong(ItemsUtil.getTag(stack, "cooldown")) > System.currentTimeMillis();
	}
	
	
	@Override
	public void onCreate(ItemStack stack) {
		
		ItemsUtil.setName(stack, Main.getInstance().getLanguage("items.midas.name"));
		ItemsUtil.setLore(stack, Main.getInstance().getLanguageArray("items.midas.lore"));
		
	}
	
	@Override
	public boolean onInteractEntity(Player _p, ItemStack item, Entity _ent) {
		ru.dseymo.customstevechaos.players.Player p = Game.getInstance().getPlayer(_p.getUniqueId());
		if(!Game.getInstance().isStart() || !(_ent instanceof LivingEntity) || _ent.getType() == EntityType.PLAYER || _ent.getType() == EntityType.ARMOR_STAND) return false;
		if(isCooldown(item)) {
			Chat.FAIL.send(p.getBP(), Main.getInstance().getLanguage("messages.fail.cooldown"));
			return false;
		}
		LivingEntity ent = (LivingEntity)_ent;
		ent.damage(ent.getHealth());
		
		p.deposit(190);
		cooldown(item);
		Chat.INFO.send(ent, Main.getInstance().getLanguage("items.midas.used"));
		
		return false;
	}
	
}
