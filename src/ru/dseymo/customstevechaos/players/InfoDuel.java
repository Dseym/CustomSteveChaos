package ru.dseymo.customstevechaos.players;

import lombok.Getter;

public class InfoDuel {
	
	@Getter
	private int wins = 0, loses = 0;
	
	public void lose() {loses++;}
	public void win() {wins++;}
	
}
