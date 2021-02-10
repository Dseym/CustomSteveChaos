package ru.dseymo.customstevechaos.duels;

import org.bukkit.Location;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class DuelMap {
	
	private String name;
	private Location lView, lP1, lP2;
	
}
