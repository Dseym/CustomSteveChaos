package ru.dseymo.customstevechaos.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagString;

public class ItemsUtil {
	
	public static void setLore(ItemStack item, String... lines) {
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>();
		for (String line : lines)
			lore.add(ChatColor.translateAlternateColorCodes('&', line));
		
		meta.setLore(lore);
		item.setItemMeta(meta);
	}
	
	public static void insertLoreLines(ItemStack item, int afterLines, String... lines) {
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>();
		
		List<String> oldLore = meta.getLore();
		
		int currentLine = 0;
		while (currentLine < afterLines) {
			lore.add(oldLore.get(currentLine));
			currentLine++;
		}

		for (String line : lines)
			lore.add(ChatColor.translateAlternateColorCodes('&', line));
		
		
		while (currentLine < oldLore.size()) {
			lore.add(oldLore.get(currentLine));
			currentLine++;
		}
		
		meta.setLore(lore);
		item.setItemMeta(meta);
	}
	
	public static ItemStack unbreakable(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.spigot().setUnbreakable(true);
		item.setItemMeta(meta);
		return item;
	}
	
	public static String getTag(ItemStack item, String str) {
		net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(item);
		NBTTagCompound nbt = nms.hasTag() ? nms.getTag() : new NBTTagCompound();
		return nbt.getString(str);
	}
	
	public static ItemStack setTag(ItemStack item, String str, String value) {
		net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(item);
		NBTTagCompound nbt = nms.hasTag() ? nms.getTag() : new NBTTagCompound();
		nbt.set(str, new NBTTagString(value));
		nms.setTag(nbt);
		return CraftItemStack.asBukkitCopy(nms);
	}
	
	public static ItemStack addToLore(ItemStack item, String... newLines) {
		ItemMeta meta = item.getItemMeta();
		List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
		for (String line : newLines)
			lore.add(ChatColor.translateAlternateColorCodes('&', line));
		
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static void setName(ItemStack item, String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		item.setItemMeta(meta);
	}
	
	public static void changeLore(ItemStack item, int line, String newText) {
		ItemMeta meta = item.getItemMeta();
		List<String> lore = meta.getLore();
		lore.set(line, ChatColor.translateAlternateColorCodes('&', newText));
		meta.setLore(lore);
		item.setItemMeta(meta);
	}
	
	public static ItemStack addGlow(ItemStack item){
		item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
    }
	
	public static ItemStack removeGlow(ItemStack item){
		item.removeEnchantment(Enchantment.DAMAGE_ALL);
		ItemMeta meta = item.getItemMeta();
		meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
    }
	
	public static ItemStack generateItem(Material type, byte data, int amount, String name, String... lines) {
		ItemStack item = new ItemStack(type, amount, data);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		List<String> lore = new ArrayList<>();
		for (String line : lines)
			lore.add(ChatColor.translateAlternateColorCodes('&', line));
		
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack generateItem(Material type, byte data, String name, String... lines) {
		ItemStack item = new ItemStack(type, 1, data);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		List<String> lore = new ArrayList<>();
		for (String line : lines)
			lore.add(ChatColor.translateAlternateColorCodes('&', line));
		
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack generateItem(Material type, int amount, String name, String... lines) {
		ItemStack item = new ItemStack(type, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		List<String> lore = new ArrayList<>();
		for (String line : lines)
			lore.add(ChatColor.translateAlternateColorCodes('&', line));
		
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack generateItem(Material type, String name, String... lines) {
		ItemStack item = new ItemStack(type, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		List<String> lore = new ArrayList<>();
		for (String line : lines)
			lore.add(ChatColor.translateAlternateColorCodes('&', line));
		
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack generateSkull(String playerName, String displayName, String... lines) {
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		List<String> lore = new ArrayList<>();
		for (String line : lines)
			lore.add(ChatColor.translateAlternateColorCodes('&', line));
		
		meta.setLore(lore);
		meta.setOwner(playerName);
		skull.setItemMeta(meta);
		return skull;
	}
	
	public static String getFriendlyName(ItemStack item) {
		if (item.hasItemMeta()) {
			ItemMeta meta = item.getItemMeta();
			if (meta.hasDisplayName())
				return ChatColor.stripColor(meta.getDisplayName());
			
		}
		return "";
	}
	
	public static String getName(ItemStack item) {
		if (item.hasItemMeta()) {
			ItemMeta meta = item.getItemMeta();
			if (meta.hasDisplayName())
				return meta.getDisplayName();
			
		}
		return "";
	}
	
}
