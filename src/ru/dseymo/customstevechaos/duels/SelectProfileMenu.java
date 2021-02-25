package ru.dseymo.customstevechaos.duels;

import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.ProfileMenu;
import ru.dseymo.customstevechaos.utils.Chat;
import ru.dseymo.customstevechaos.utils.ItemsUtil;
import ru.dseymo.customstevechaos.utils.Menu;

public class SelectProfileMenu extends Menu {
	
	private Duel duel;
	private Player p1, p2;
	
	public SelectProfileMenu(Duel duel) {
		super(Main.getInstance().getLanguage("menus.duel.name"), 36, false);
		
		this.duel = duel;
		p1 = duel.getP1();
		p2 = duel.getP2();
		
		String[] format = Main.getInstance().getLanguageArray("menus.duel.loreFormat");
		
		inv.setItem(13, ItemsUtil.generateItem(Material.ANVIL, Main.getInstance().getLanguage("menus.duel.nameArena").replace("%arena%", duel.getMap().getName())));
		
		String[] lore = format.clone();
		for(int i = 0; i < lore.length; i++)
			lore[i] = lore[i].replace("%wins%", p1.getInfoDuel().getWins() + "").replace("%loses%", p1.getInfoDuel().getLoses() + "");
		
		inv.setItem(20, ItemsUtil.generateSkull(p1.getBP().getName(), p1.getBP().getName(), lore));
		
		lore = format.clone();
		for(int i = 0; i < lore.length; i++)
			lore[i] = lore[i].replace("%wins%", p2.getInfoDuel().getWins() + "").replace("%loses%", p2.getInfoDuel().getLoses() + "");
		
		inv.setItem(24, ItemsUtil.generateSkull(p2.getBP().getName(), p2.getBP().getName(), lore));
		
	}
	
	
	@Override
	public boolean onClick(org.bukkit.entity.Player _p, ItemStack item, int slot, ClickType click) {
		Player p;
		if(slot == 20) p = p1;
		else if(slot == 24) p = p2;
		else return true;
		
		if(click.isLeftClick()) {
			if(duel.isMember(Game.getInstance().getPlayer(_p.getUniqueId()))) {
				Chat.FAIL.send(_p, Main.getInstance().getLanguage("messages.fail.youMemberDuel"));
				return true;
			}
			
			RatesMenu menu = new RatesMenu(duel, Game.getInstance().getPlayer(_p.getUniqueId()), p, this);
			menu.open(_p);
			
		} else if(click.isRightClick()) {
			
			ProfileMenu menu = new ProfileMenu(p, this);
			menu.open(_p);
			
		}
		
		return true;
	}

}
