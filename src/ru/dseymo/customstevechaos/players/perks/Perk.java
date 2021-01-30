package ru.dseymo.customstevechaos.players.perks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.players.perks.listeners.PerkAlchemistListener;
import ru.dseymo.customstevechaos.players.perks.listeners.PerkArcherListener;
import ru.dseymo.customstevechaos.players.perks.listeners.PerkAssassinListener;
import ru.dseymo.customstevechaos.players.perks.listeners.PerkBerserkListener;
import ru.dseymo.customstevechaos.players.perks.listeners.PerkDodgerListener;
import ru.dseymo.customstevechaos.players.perks.listeners.PerkGamblingListener;
import ru.dseymo.customstevechaos.players.perks.listeners.PerkGladiatorListener;
import ru.dseymo.customstevechaos.players.perks.listeners.PerkLuckyListener;
import ru.dseymo.customstevechaos.players.perks.listeners.PerkMasterWeaponListener;
import ru.dseymo.customstevechaos.players.perks.listeners.PerkSwordmanListener;
import ru.dseymo.customstevechaos.players.perks.listeners.PerkTitanListener;
import ru.dseymo.customstevechaos.utils.ItemsUtil;

@Getter
public enum Perk {
	
	BERSERK(Material.IRON_AXE, "perks.berserk.name", "perks.berserk.lore", new PerkBerserkListener()),
	SWORDMAN(Material.IRON_SWORD, "perks.swordman.name", "perks.swordman.lore", new PerkSwordmanListener()),
	ARCHER(Material.BOW, "perks.archer.name", "perks.archer.lore", new PerkArcherListener()),
	LUCKY(Material.GOLD_INGOT, "perks.lucky.name", "perks.lucky.lore", new PerkLuckyListener()),
	GLADIATOR(Material.WOOD_SWORD, "perks.gladiator.name", "perks.gladiator.lore", new PerkGladiatorListener()),
	ALCHEMIST(Material.POTION, "perks.alchemist.name", "perks.alchemist.lore", new PerkAlchemistListener()),
	TITAN(Material.IRON_BLOCK, "perks.titan.name", "perks.titan.lore", new PerkTitanListener()),
	DODGER(Material.FEATHER, "perks.dodger.name", "perks.dodger.lore", new PerkDodgerListener()),
	MASTER_WEAPON(Material.ANVIL, "perks.masterweapon.name", "perks.masterweapon.lore", new PerkMasterWeaponListener()),
	ASSASSIN(Material.LEATHER_BOOTS, "perks.assassin.name", "perks.assassin.lore", new PerkAssassinListener()),
	GAMBLING(Material.GOLD_NUGGET, "perks.gambling.name", "perks.gambling.lore", new PerkGamblingListener());
	
	private ItemStack item;
	private String name;
	private String[] description;
	
	private Perk(Material mat, String pathName, String pathDescription, Listener listener) {
		
		name = Main.getInstance().getLanguage(pathName);
		description = Main.getInstance().getLanguageArray(pathDescription);
		item = ItemsUtil.generateItem(mat, name, description);
		
		Bukkit.getPluginManager().registerEvents(listener, Main.getInstance());
	}
	
}
