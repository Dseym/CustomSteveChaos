package ru.dseymo.customstevechaos.map;

import java.util.ArrayList;

import org.bukkit.Location;

import lombok.Getter;
import ru.dseymo.customstevechaos.arenas.Arena;
import ru.dseymo.customstevechaos.arenas.ArenasConfig;
import ru.dseymo.customstevechaos.duels.Duel;
import ru.dseymo.customstevechaos.game.Game;

public class Map {
	
	@Getter
	private static Map instance = new Map();
	
	public static void loadMap(MapConfig map, ArenasConfig arenas) {
		if(Game.getInstance().isStart()) return;
		instance = new Map();
		
		instance.map = map;
		instance.arenasConfig = arenas;
		for(String str: arenas.getArenas())
			instance.arenas.add(new Arena(str, arenas.getSpawn(str), arenas.getSpawnMob(str)));
		instance.duel = new Duel(map.getDuelView(), map.getDuelp1(), map.getDuelp2());
		
	}
	
	
	private MapConfig map;
	private ArenasConfig arenasConfig;
	@Getter
	private ArrayList<Arena> arenas = new ArrayList<>();
	@Getter
	private Duel duel;
	
	public Location getLobby() {return map.getLobby();}
	public void reload() {
		
		map.load();
		arenasConfig.load();
		loadMap(map, arenasConfig);
		
	}
	
}
