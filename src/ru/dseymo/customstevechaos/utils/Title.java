package ru.dseymo.customstevechaos.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class Title {
	
	private static String colorize(String text) {return ChatColor.translateAlternateColorCodes('&', text);}
	
	private static void sendTitle(Player p, String title) {
		ReflUtil.sendPacket(p, new PacketPlayOutTitle(EnumTitleAction.TITLE, new ChatComponentText(title)));
	}
	
	private static void sendSubTitle(Player p, String subTitle) {
		ReflUtil.sendPacket(p, new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, new ChatComponentText(subTitle)));
	}
	
	public static void send(Player p, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
		sendTitle(p, colorize(title));
		sendSubTitle(p, colorize(subTitle));
		sendTimes(p, fadeIn, stay, fadeOut);
	}
	
	private static void sendTimes(Player p, int fadeIn, int stay, int fadeOut) {
		ReflUtil.sendPacket(p, new PacketPlayOutTitle(fadeIn, stay, fadeOut));
	}
	
	public static void clear(Player p) {
		ReflUtil.sendPacket(p, new PacketPlayOutTitle(EnumTitleAction.CLEAR, new ChatComponentText("")));
	}
	
}
