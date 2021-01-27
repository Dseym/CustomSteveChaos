package ru.dseymo.customstevechaos.utils;

import java.text.ParseException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtil {
	
	public static String toString(Location loc, boolean rotation) {
		
		if(loc == null) return "";
		
		String str = loc.getWorld().getName() + " " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ();
		if(rotation)
			str += " " + loc.getYaw() + " " + loc.getPitch();
		
		return str;
		
	}
	
	public static Location parseLoc(String str) throws ParseException {
		
		String[] split = str.split(" ");
		World world = Bukkit.getWorld(split[0]);
		if(world == null) throw new ParseException("Location: " + str + " - World not found", 0);
		
		try {
			int x = Integer.parseInt(split[1]),
				y = Integer.parseInt(split[2]),
				z = Integer.parseInt(split[3]);
			
			Location loc;
			if (split.length > 5) {

				float yaw = Float.parseFloat(split[4]),
					  pitch = Float.parseFloat(split[5]);

				loc = new Location(world, x, y, z, yaw, pitch);

			} else loc = new Location(world, x, y, z);
			
			return loc;
		} catch (Exception e) {throw new ParseException("Location: " + str, 0);}
		
	}
	
}
