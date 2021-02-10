package ru.dseymo.customstevechaos.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import lombok.Getter;
import ru.dseymo.customstevechaos.Main;

public enum Chat {
		
	NO_PREFIX(""),
	INFO(Main.getInstance().getLanguage("prefix") + "&7"),
	FAIL(Main.getInstance().getLanguage("prefix") + "&4"),
	SUCCESS(Main.getInstance().getLanguage("prefix") + "&2"),
	NO_PERM(Main.getInstance().getLanguage("prefix") + "&4" + Main.getInstance().getLanguage("messages.fail.noPerm") + " ");
	
	@Getter
	private String pref;
	
	private Chat(String prefix) {this.pref = prefix;}
	
	private String colorize(String text) {return ChatColor.translateAlternateColorCodes('&', text);}
	
	public void send(CommandSender sender, String... strs) {
		if(this == NO_PERM) {
			sender.sendMessage(colorize(pref + (strs.length == 0 ? "" : strs[0])));
			if(strs.length > 1)
				FAIL.send(sender, Arrays.copyOfRange(strs, 1, strs.length));
			return;
		}
		
		for(String str: strs)
			sender.sendMessage(colorize((!str.isEmpty() ? pref : "") + str));
	}
	
	public void sendAll(String... strs) {
		send(new ArrayList<>(Bukkit.getOnlinePlayers()), strs);
	}
	
	public void send(List<CommandSender> senders, String... strs) {
		for(CommandSender sender: senders)
			send(sender, strs);
	}
	
}