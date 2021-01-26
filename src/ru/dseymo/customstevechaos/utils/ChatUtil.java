package ru.dseymo.customstevechaos.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import ru.dseymo.customstevechaos.Main;

public class ChatUtil {
	
	public static String prefix = "&8[&9&lCSC&8]&r ";

    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void message(CommandSender sender, String message) {
    	noPrefix(sender, " ");
        sender.sendMessage(colorize(message));
        noPrefix(sender, " ");
    }

    public static void success(CommandSender sender, String message) {
    	noPrefix(sender, " ");
        sender.sendMessage(colorize(prefix + "&2" + message));
        noPrefix(sender, " ");
    }

    public static void fail(CommandSender sender, String message) {
    	noPrefix(sender, " ");
        sender.sendMessage(colorize(prefix + "&4" + message));
        noPrefix(sender, " ");
    }

    public static void info(CommandSender sender, String... messages) {
    	noPrefix(sender, " ");
        for (String message : messages)
        	sender.sendMessage(colorize(prefix + "&7" + message));
        noPrefix(sender, " ");
    }

    public static void noPrefix(CommandSender sender, String... messages) {
        for (String message : messages)
                sender.sendMessage(colorize("&7" + message));
    }

    public static void noPerm(CommandSender sender) {
        fail(sender, Main.getInstance().getLanguage("messages.fail.noPerm"));
    }
}