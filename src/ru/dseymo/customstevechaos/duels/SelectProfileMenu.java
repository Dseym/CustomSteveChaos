package ru.dseymo.customstevechaos.duels;

import java.util.ArrayList;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.players.ProfileMenu;
import ru.dseymo.customstevechaos.utils.ItemsUtil;
import ru.dseymo.customstevechaos.utils.Menu;

public class SelectProfileMenu extends Menu {
	
	private Duel duel;
	private Player p1, p2;
	
	public SelectProfileMenu(Duel duel) {
		super(Main.getInstance().getLanguage("menus.duel.name"), 27);
		
		this.duel = duel;
		p1 = duel.getP1();
		p2 = duel.getP2();
		
		ArrayList<String> loreStr = Main.getInstance().getLanguageList("menus.duel.loreFormat");
		String[] format = new String[loreStr.size()];
		for(int i = 0; i < format.length; i++)
			format[i] = loreStr.get(i);
		
		String[] lore = format.clone();
		for(int i = 0; i < lore.length; i++)
			lore[i] = lore[i].replace("%wins%", p1.getInfoDuel().getWins() + "").replace("%loses%", p1.getInfoDuel().getLoses() + "");
		
		inv.setItem(11, ItemsUtil.generateSkull(p1.getBP().getName(), p1.getBP().getName(), lore));
		
		lore = format.clone();
		for(int i = 0; i < lore.length; i++)
			lore[i] = lore[i].replace("%wins%", p2.getInfoDuel().getWins() + "").replace("%loses%", p2.getInfoDuel().getLoses() + "");
		
		inv.setItem(15, ItemsUtil.generateSkull(p2.getBP().getName(), p2.getBP().getName(), lore));
		
	}
	
	
	@Override
	public boolean onClick(org.bukkit.entity.Player _p, ItemStack item, int slot, ClickType click) {
		Player p;
		if(slot == 11) p = p1;
		else if(slot == 15) p = p2;
		else return true;
		
		if(click.isLeftClick()) {
			
			RatesMenu menu = new RatesMenu(duel, Game.getInstance().getPlayer(_p.getUniqueId()), p, this);
			menu.open(_p);
			
		} else if(click.isRightClick()) {
			
			ProfileMenu menu = new ProfileMenu(p, this);
			menu.open(_p);
			
		}
		
		return true;
	}

}
