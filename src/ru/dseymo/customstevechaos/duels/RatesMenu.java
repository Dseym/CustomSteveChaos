package ru.dseymo.customstevechaos.duels;

import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.game.Game;
import ru.dseymo.customstevechaos.players.Player;
import ru.dseymo.customstevechaos.utils.ItemsUtil;
import ru.dseymo.customstevechaos.utils.Menu;

public class RatesMenu extends Menu {
	
	private Menu from;
	private Duel duel;
	private Player target;
	
	public RatesMenu(Duel duel, Player p, Player target, Menu from) {
		super(Main.getInstance().getLanguage("menus.rates.name").replace("%player%", target.getBP().getName()), 27);
		
		this.duel = duel;
		this.target = target;
		
		this.from = from;
		
		inv.setItem(0, ItemsUtil.generateItem(Material.SIGN, Main.getInstance().getLanguage("menus.rates.back")));
		inv.setItem(2, ItemsUtil.generateItem(Material.STAINED_GLASS_PANE, (byte)5, "+50"));
		inv.setItem(3, ItemsUtil.generateItem(Material.STAINED_GLASS_PANE, (byte)5, "+100"));
		inv.setItem(5, ItemsUtil.generateItem(Material.STAINED_GLASS_PANE, (byte)5, "+200"));
		inv.setItem(6, ItemsUtil.generateItem(Material.STAINED_GLASS_PANE, (byte)5, "+500"));
		inv.setItem(13, ItemsUtil.generateItem(Material.GLOWSTONE_DUST, Main.getInstance().getLanguage("menus.rates.costRate").replace("%money%", duel.getRate(p) + "")));
		
	}
	
	
	@Override
	public boolean onClick(org.bukkit.entity.Player _p, ItemStack item, int slot, ClickType click) {
		
		if(slot == 0) from.open(_p);
		else {
			
			Player p = Game.getInstance().getPlayer(_p.getUniqueId());
			int cost = 0;
			switch (slot) {
				case 2: cost = 50; break;
				case 3: cost = 100; break;
				case 5: cost = 200; break;
				case 6: cost = 500; break;
				
			}
			if(duel.getP1().equals(target)) duel.addRate1(p, cost);
			else if(duel.getP2().equals(target)) duel.addRate2(p, cost);
			
			inv.setItem(13, ItemsUtil.generateItem(Material.GLOWSTONE_DUST, Main.getInstance().getLanguage("menus.rates.costRate").replace("%money%", duel.getRate(p) + "")));
			
		}
		
		return true;
	}
	
}
