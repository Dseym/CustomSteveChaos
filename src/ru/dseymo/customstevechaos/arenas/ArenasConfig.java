package ru.dseymo.customstevechaos.arenas;

import java.io.File;
import java.util.Set;

import org.bukkit.Location;

import ru.dseymo.customstevechaos.utils.Config;
import ru.dseymo.customstevechaos.utils.LocationUtil;

public class ArenasConfig extends Config {

	public ArenasConfig(File file) {
		super(file, true);
	}
	
	public void create(String name, Location spawn, Location spawnMob) {
		if(contains(name)) return;
		
		set(name + ".spawn", LocationUtil.toString(spawn, true));
		set(name + ".spawnMob", LocationUtil.toString(spawnMob, true));
		save();
		
	}
	
	public void remove(String name) {
		if(!contains(name)) return;
		
		set(name, null);
		save();
		
	}
	
	public void setSpawn(String name, Location spawn) {
		if(!contains(name)) return;
		
		set(name + ".spawn", LocationUtil.toString(spawn, true));
		save();
		
	}
	
	public void setSpawnMob(String name, Location spawnMob) {
		if(!contains(name)) return;
		
		set(name + ".spawnMob", LocationUtil.toString(spawnMob, true));
		save();
		
	}
	
	public Location getSpawn(String name) {return getLocation(name + ".spawn");}
	public Location getSpawnMob(String name) {return getLocation(name + ".spawnMob");}
	public Set<String> getArenas() {return getKeys(false);}

}
