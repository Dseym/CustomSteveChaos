package ru.dseymo.customstevechaos.utils;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	
	private static String folder = "config";
	
	private File file;
	private FileConfiguration config;
	
	public Config(File file, boolean fileJar) {
		
		this.file = file;
		file.getParentFile().mkdirs();
		
		try {
			
			if (!file.exists())
				if (fileJar) FileUtil.copyFromJar(getClass().getResourceAsStream("/" + folder + "/" + file.getName()), file);
				else file.createNewFile();
			
		} catch (Exception e) {e.printStackTrace();}
		
		load();
		
	}
	
	public File getFile() {return file;}
	
	public void save() {try {config.save(file);} catch (Exception e) {}}
	public void load() {config = YamlConfiguration.loadConfiguration(file);}
	public boolean contains(String path) {return config.contains(path);}
	public void set(String path, Object obj) {config.set(path, obj);}
	public Object get(String path) {return config.get(path);}
	public String getString(String path) {return (String)get(path);}
	public int getInt(String path) {return (int)get(path);}
	public boolean getBoolean(String path) {return (boolean)get(path);}
	@SuppressWarnings("unchecked")
	public ArrayList<String> getStringList(String path) {return (ArrayList<String>)get(path);}
	public Set<String> getKeys(boolean deep) {return config.getKeys(deep);}
	public Location getLocation(String path) {
		try {return LocationUtil.parseLoc(getString(path));} catch (ParseException e) {e.printStackTrace(); return null;}
	}
	public void setLocation(String path, Location loc, boolean rotation) {set(path, LocationUtil.toString(loc, rotation));}
	
}
