package ru.dseymo.customstevechaos.game;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.items.upgrade.UpgradeMenu;
import ru.dseymo.customstevechaos.map.Map;
import ru.dseymo.customstevechaos.utils.ChatUtil;

public class GameExecute implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 0) 
			help(sender);
		else if(args[0].equalsIgnoreCase("start"))
			start(sender);
		else if(args[0].equalsIgnoreCase("stop"))
			stop(sender);
		else if(args[0].equalsIgnoreCase("duelProfiles"))
			duelProfiles(sender);
		else if(args[0].equalsIgnoreCase("upgrade"))
			upgrade(sender);
		else
			help(sender);
		
		return true;
	}

	private void help(CommandSender sender) {
		
		ArrayList<String> strs = Main.getInstance().getLanguageList("messages.info.helpGame");
		String[] str = new String[strs.size()];
		for(int i = 0; i < str.length; i++)
			str[i] = strs.get(i);
		
		ChatUtil.info(sender, str);
		
	}

	private void upgrade(CommandSender sender) {
		if(sender instanceof Player && Game.getInstance().getStatus() == Status.WAITING_WAVE) {
			UpgradeMenu menu = new UpgradeMenu();
			menu.open((Player)sender);
		}
	}

	private void duelProfiles(CommandSender sender) {
		if(sender instanceof Player && Map.getInstance().getDuel().isCreate())
			Map.getInstance().getDuel().openMenu(Game.getInstance().getPlayer(((Player)sender).getUniqueId()));
	}

	private void stop(CommandSender sender) {
		if(!sender.hasPermission("customstevechaos.admin")) {
			
			Game.getInstance().stop();
			ChatUtil.success(sender, Main.getInstance().getLanguage("messages.success.successStopped"));
			
		} else ChatUtil.noPerm(sender);
	}
	
	private void start(CommandSender sender) {
		if(!sender.hasPermission("customstevechaos.admin")) {
			if(Game.getInstance().getStatus() != Status.WAITING_GAME) {
				ChatUtil.fail(sender, Main.getInstance().getLanguage("messages.fail.alreadyStarted"));
				return;
			} else if(Game.getInstance().getNotSpecPlayers().size() < Main.getInstance().getMinPlayers()) {
				ChatUtil.fail(sender, Main.getInstance().getLanguage("messages.fail.notEnoughPlayers"));
				return;
			}
			
			Game.getInstance().start();
			ChatUtil.success(sender, Main.getInstance().getLanguage("messages.success.successStarted"));

		} else ChatUtil.noPerm(sender);
	}
	
}
