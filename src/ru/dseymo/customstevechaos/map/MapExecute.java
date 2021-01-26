package ru.dseymo.customstevechaos.map;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.utils.ChatUtil;

public class MapExecute implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("customstevechaos")) {ChatUtil.noPerm(sender); return true;}
		else if(!(sender instanceof Player)) return true;
		Player p = (Player)sender;
		
		if(args.length == 0) 
			help(p);
		else if(args[0].equalsIgnoreCase("setlobby"))
			setLobby(p);
		else if(args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("create"))
			arenaCreate(p, args);
		else if(args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("setspawn"))
			arenaSetSpawn(p, args);
		else if(args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("setspawnmob"))
			arenaSetSpawnMob(p, args);
		else if(args[0].equalsIgnoreCase("duel") && args[1].equalsIgnoreCase("setp1"))
			duelSetp1(p);
		else if(args[0].equalsIgnoreCase("duel") && args[1].equalsIgnoreCase("setp2"))
			duelSetp2(p);
		else if(args[0].equalsIgnoreCase("duel") && args[1].equalsIgnoreCase("setview"))
			duelSetView(p);
		else if(args[0].equalsIgnoreCase("reload"))
			reload(p);
		else
			help(p);
		
		return true;
	}

	private void reload(Player p) {
		
		Map.getInstance().reload();
		ChatUtil.success(p, Main.getInstance().getLanguage("messages.success.mapReloaded"));
		
	}

	private void help(Player p) {
		
		ArrayList<String> strs = Main.getInstance().getLanguageList("messages.info.helpMap");
		String[] str = new String[strs.size()];
		for(int i = 0; i < str.length; i++)
			str[i] = strs.get(i);
		
		ChatUtil.info(p, str);
		
	}

	private void duelSetView(Player p) {
		
		Main.getInstance().getMapConfig().duelSetView(p.getLocation());
		ChatUtil.success(p, Main.getInstance().getLanguage("messages.success.locChanged"));
		
	}

	private void duelSetp2(Player p) {
		
		Main.getInstance().getMapConfig().duelSetp2(p.getLocation());
		ChatUtil.success(p, Main.getInstance().getLanguage("messages.success.locChanged"));
		
	}

	private void duelSetp1(Player p) {
		
		Main.getInstance().getMapConfig().duelSetp1(p.getLocation());
		ChatUtil.success(p, Main.getInstance().getLanguage("messages.success.locChanged"));
		
	}

	private void arenaSetSpawnMob(Player p, String[] args) {
		if(args.length < 3) {
			ChatUtil.fail(p, Main.getInstance().getLanguage("messages.fail.notEnoughArgs"));
			return;
		} else if(!Main.getInstance().getArenasConfig().contains(args[2])) {
			ChatUtil.fail(p, Main.getInstance().getLanguage("messages.fail.notFoundArena"));
			return;
		}
		
		Main.getInstance().getArenasConfig().setSpawnMob(args[2], p.getLocation());
		ChatUtil.success(p, Main.getInstance().getLanguage("messages.success.locChanged"));
		
	}

	private void arenaSetSpawn(Player p, String[] args) {
		if(args.length < 3) {
			ChatUtil.fail(p, Main.getInstance().getLanguage("messages.fail.notEnoughArgs"));
			return;
		} else if(!Main.getInstance().getArenasConfig().contains(args[2])) {
			ChatUtil.fail(p, Main.getInstance().getLanguage("messages.fail.notFoundArena"));
			return;
		}
		
		Main.getInstance().getArenasConfig().setSpawn(args[2], p.getLocation());
		ChatUtil.success(p, Main.getInstance().getLanguage("messages.success.locChanged"));
		
	}

	private void arenaCreate(Player p, String[] args) {
		if(args.length < 3) {
			ChatUtil.fail(p, Main.getInstance().getLanguage("messages.fail.notEnoughArgs"));
			return;
		}
		
		Main.getInstance().getArenasConfig().create(args[2], p.getLocation(), p.getLocation());
		ChatUtil.success(p, Main.getInstance().getLanguage("messages.success.arenaCreated"));
		
	}

	private void setLobby(Player p) {
		
		Main.getInstance().getMapConfig().setLobby(p.getLocation());
		ChatUtil.success(p, Main.getInstance().getLanguage("messages.success.lobbySetted"));
		
	}

}
