package ru.dseymo.customstevechaos.map;

import java.io.File;

import org.bukkit.Location;

import ru.dseymo.customstevechaos.utils.Config;
import ru.dseymo.customstevechaos.utils.LocationUtil;

public class MapConfig extends Config {

	public MapConfig(File file, boolean fileJar) {
		super(file, fileJar);
	}
	
	public void setLobby(Location loc) {
		
		set("lobby", LocationUtil.toString(loc, true));
		save();
		
	}
	
	public void duelSetp1(Location loc) {
		
		set("duel.locPlayer1", LocationUtil.toString(loc, true));
		save();
		
	}
	
	public void duelSetp2(Location loc) {
		
		set("duel.locPlayer2", LocationUtil.toString(loc, true));
		save();
		
	}
	
	public void duelSetView(Location loc) {
		
		set("duel.locView", LocationUtil.toString(loc, true));
		save();
		
	}
	
	public Location getLobby() {return getLocation("lobby");}
	public Location getDuelp1() {return getLocation("duel.locPlayer1");}
	public Location getDuelp2() {return getLocation("duel.locPlayer2");}
	public Location getDuelView() {return getLocation("duel.locView");}
	
}
