package ru.dseymo.customstevechaos.map;

import java.util.ArrayList;

import org.bukkit.Location;

import lombok.Getter;
import ru.dseymo.customstevechaos.Main;
import ru.dseymo.customstevechaos.arenas.Arena;
import ru.dseymo.customstevechaos.arenas.ArenasConfig;
import ru.dseymo.customstevechaos.game.Game;

public class Map {
	
	@Getter
	private static Map instance = new Map();
	
	public static void loadMap(MapConfig map, ArenasConfig arenas) {
		if(Game.getInstance().isStart()) return;
		instance.remove();
		instance = new Map();
		
		instance.map = map;
		instance.arenasConfig = arenas;
		for(String str: arenas.getArenas())
			instance.arenas.add(new Arena(str, arenas.getSpawn(str), arenas.getSpawnMob(str)));
		
	}
	
	
	private MapConfig map;
	private ArenasConfig arenasConfig;
	@Getter
	private ArrayList<Arena> arenas = new ArrayList<>();
	
	public void remove() {
		for(Arena arena: arenas)
			arena.remove();
	}
	
	public Location getLobby() {return map.getLobby();}
	public void reload() {
		
		map.load();
		arenasConfig.load();
		Main.getInstance().getDuelConfig().load();
		loadMap(map, arenasConfig);
		
	}
	
}
