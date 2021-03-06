package ru.dseymo.customstevechaos.map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.utils.Chat;

public class MapExecute implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("customstevechaos.admin")) {Chat.NO_PERM.send(sender); return true;}
		else if(!(sender instanceof Player)) return true;
		Player p = (Player)sender;
		
		if(args.length == 0) 
			help(p);
		else if(args[0].equalsIgnoreCase("setlobby"))
			setLobby(p);
		else if(args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("create"))
			arenaCreate(p, args);
		else if(args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("remove"))
			arenaRemove(p, args);
		else if(args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("list"))
			arenaList(p);
		else if(args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("setspawn"))
			arenaSetSpawn(p, args);
		else if(args[0].equalsIgnoreCase("arena") && args[1].equalsIgnoreCase("setspawnmob"))
			arenaSetSpawnMob(p, args);
		else if(args[0].equalsIgnoreCase("duel") && args[1].equalsIgnoreCase("create"))
			duelCreate(p, args);
		else if(args[0].equalsIgnoreCase("duel") && args[1].equalsIgnoreCase("remove"))
			duelRemove(p, args);
		else if(args[0].equalsIgnoreCase("duel") && args[1].equalsIgnoreCase("list"))
			duelList(p, args);
		else if(args[0].equalsIgnoreCase("duel") && args[1].equalsIgnoreCase("setp1"))
			duelSetp1(p, args);
		else if(args[0].equalsIgnoreCase("duel") && args[1].equalsIgnoreCase("setp2"))
			duelSetp2(p, args);
		else if(args[0].equalsIgnoreCase("duel") && args[1].equalsIgnoreCase("setview"))
			duelSetView(p, args);
		else if(args[0].equalsIgnoreCase("reload"))
			reload(p);
		else
			help(p);
		
		return true;
	}

	private void duelList(Player p, String[] args) {
		
		String str = "";
		for(String _str: Main.getInstance().getDuelConfig().getMaps())
			str += "&9&l" + _str + "&8; ";
		
		Chat.INFO.send(p, str);
		
	}

	private void duelRemove(Player p, String[] args) {
		if(args.length < 3) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notEnoughArgs"));
			return;
		} else if(!Main.getInstance().getDuelConfig().contains(args[2])) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notFoundDuel"));
			return;
		}
		
		Main.getInstance().getDuelConfig().remove(args[2]);
		Chat.SUCCESS.send(p, Main.getInstance().getLanguage("messages.success.duelRemoved"));
		
	}

	private void duelCreate(Player p, String[] args) {
		if(args.length < 3) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notEnoughArgs"));
			return;
		} else if(Main.getInstance().getDuelConfig().contains(args[2])) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.duelAlreadyCreated"));
			return;
		}
		
		Main.getInstance().getDuelConfig().create(args[2], p.getLocation(), p.getLocation(), p.getLocation());
		Chat.SUCCESS.send(p, Main.getInstance().getLanguage("messages.success.duelCreated"));
		
	}

	private void arenaList(Player p) {
		
		String str = "";
		for(String _str: Main.getInstance().getArenasConfig().getArenas())
			str += "&9&l" + _str + "&8; ";
		
		Chat.INFO.send(p, str);
		
	}

	private void arenaRemove(Player p, String[] args) {
		if(args.length < 3) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notEnoughArgs"));
			return;
		} else if(!Main.getInstance().getArenasConfig().contains(args[2])) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notFoundArena"));
			return;
		}
		
		Chat.SUCCESS.send(p, Main.getInstance().getLanguage("messages.success.arenaRemoved"));
		Main.getInstance().getArenasConfig().remove(args[2]);
		
	}

	private void reload(Player p) {
		
		Map.getInstance().reload();
		Chat.SUCCESS.send(p, Main.getInstance().getLanguage("messages.success.mapReloaded"));
		
	}

	private void help(Player p) {
		
		Chat.INFO.send(p, Main.getInstance().getLanguageArray("messages.info.helpMap"));
		
	}

	private void duelSetView(Player p, String[] args) {
		if(args.length < 3) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notEnoughArgs"));
			return;
		} else if(!Main.getInstance().getDuelConfig().contains(args[2])) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notFoundDuel"));
			return;
		}
		
		Main.getInstance().getDuelConfig().setLView(args[2], p.getLocation());
		Chat.SUCCESS.send(p, Main.getInstance().getLanguage("messages.success.locChanged"));
		
	}

	private void duelSetp2(Player p, String[] args) {
		if(args.length < 3) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notEnoughArgs"));
			return;
		} else if(!Main.getInstance().getDuelConfig().contains(args[2])) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notFoundDuel"));
			return;
		}
		
		Main.getInstance().getDuelConfig().setLP2(args[2], p.getLocation());
		Chat.SUCCESS.send(p, Main.getInstance().getLanguage("messages.success.locChanged"));
		
	}

	private void duelSetp1(Player p, String[] args) {
		if(args.length < 3) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notEnoughArgs"));
			return;
		} else if(!Main.getInstance().getDuelConfig().contains(args[2])) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notFoundDuel"));
			return;
		}
		
		Main.getInstance().getDuelConfig().setLP1(args[2], p.getLocation());
		Chat.SUCCESS.send(p, Main.getInstance().getLanguage("messages.success.locChanged"));
		
	}

	private void arenaSetSpawnMob(Player p, String[] args) {
		if(args.length < 3) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notEnoughArgs"));
			return;
		} else if(!Main.getInstance().getArenasConfig().contains(args[2])) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notFoundArena"));
			return;
		}
		
		Main.getInstance().getArenasConfig().setSpawnMob(args[2], p.getLocation());
		Chat.SUCCESS.send(p, Main.getInstance().getLanguage("messages.success.locChanged"));
		
	}

	private void arenaSetSpawn(Player p, String[] args) {
		if(args.length < 3) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notEnoughArgs"));
			return;
		} else if(!Main.getInstance().getArenasConfig().contains(args[2])) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notFoundArena"));
			return;
		}
		
		Main.getInstance().getArenasConfig().setSpawn(args[2], p.getLocation());
		Chat.SUCCESS.send(p, Main.getInstance().getLanguage("messages.success.locChanged"));
		
	}

	private void arenaCreate(Player p, String[] args) {
		if(args.length < 3) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.notEnoughArgs"));
			return;
		} else if(Main.getInstance().getArenasConfig().contains(args[2])) {
			Chat.FAIL.send(p, Main.getInstance().getLanguage("messages.fail.arenaAlreadyCreated"));
			return;
		}
		
		Main.getInstance().getArenasConfig().create(args[2], p.getLocation(), p.getLocation());
		Chat.SUCCESS.send(p, Main.getInstance().getLanguage("messages.success.arenaCreated"));
		
	}

	private void setLobby(Player p) {
		
		Main.getInstance().getMapConfig().setLobby(p.getLocation());
		Chat.SUCCESS.send(p, Main.getInstance().getLanguage("messages.success.lobbySetted"));
		
	}

}
