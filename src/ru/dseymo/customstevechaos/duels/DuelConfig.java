package ru.dseymo.customstevechaos.duels;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.bukkit.Location;

import ru.dseymo.customstevechaos.utils.Config;
import ru.dseymo.customstevechaos.utils.LocationUtil;

public class DuelConfig extends Config {

	public DuelConfig(File file) {
		super(file, true);
	}
	
	public void create(String name, Location lView, Location lP1, Location lP2) {
		
		set(name + ".locView", LocationUtil.toString(lView, true));
		set(name + ".locPlayer1", LocationUtil.toString(lP1, true));
		set(name + ".locPlayer2", LocationUtil.toString(lP2, true));
		save();
		
	}
	
	public void remove(String name) {
		
		set(name, null);
		save();
		
	}
	
	public void setLView(String name, Location lView) {
		if(!contains(name)) return;
		
		set(name + ".locView", LocationUtil.toString(lView, true));
		save();
		
	}
	
	public void setLP1(String name, Location lP1) {
		if(!contains(name)) return;
		
		set(name + ".locPlayer1", LocationUtil.toString(lP1, true));
		save();
		
	}

	public void setLP2(String name, Location lP2) {
		if(!contains(name)) return;
		
		set(name + ".locPlayer2", LocationUtil.toString(lP2, true));
		save();
		
	}
	
	public Location getLView(String name) {return getLocation(name + ".locView");}
	public Location getLP1(String name) {return getLocation(name + ".locPlayer1");}
	public Location getLP2(String name) {return getLocation(name + ".locPlayer2");}
	public Set<String> getMaps() {return getKeys(false);}
	
	public DuelMap getRandMap() {
		
		ArrayList<String> names = new ArrayList<>(getKeys(false));
		Collections.shuffle(names);
		String name = names.get(0);
		
		return new DuelMap(name, getLView(name), getLP1(name), getLP2(name));
		
	}

}
