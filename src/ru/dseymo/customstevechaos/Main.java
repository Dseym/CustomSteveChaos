package ru.dseymo.customstevechaos;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import ru.dseymo.customstevechaos.arenas.ArenasConfig;
import ru.dseymo.customstevechaos.game.GameExecute;
import ru.dseymo.customstevechaos.game.GameTimer;
import ru.dseymo.customstevechaos.listeners.CancelListener;
import ru.dseymo.customstevechaos.listeners.JoinListener;
import ru.dseymo.customstevechaos.listeners.PotionListener;
import ru.dseymo.customstevechaos.map.Map;
import ru.dseymo.customstevechaos.map.MapConfig;
import ru.dseymo.customstevechaos.map.MapExecute;
import ru.dseymo.customstevechaos.utils.BossBar;
import ru.dseymo.customstevechaos.utils.Config;

@Getter
public class Main extends JavaPlugin {
	
	@Getter
	private static Main instance;
	
	private GameTimer timer;
	private int minPlayers = 3;
	private MapConfig mapConfig;
	private ArenasConfig arenasConfig;
	private Config language;
	
	@Override
	public void onEnable() {
		instance = this;
		language = new Config(new File(getDataFolder() + "/language.yml"), true);
		mapConfig = new MapConfig(new File(getDataFolder() + "/map.yml"), true);
		arenasConfig = new ArenasConfig(new File(getDataFolder() + "/arenas.yml"), true);
		
		Map.loadMap(mapConfig, arenasConfig);
		
		registerEvents();
		
		Bukkit.getPluginCommand("game").setExecutor(new GameExecute());
		Bukkit.getPluginCommand("map").setExecutor(new MapExecute());
		
		timer = new GameTimer();
		timer.runTaskTimer(this, 20, 20);
		
		getLogger().info("Enabled!");
	}
	
	private void registerEvents() {
		PluginManager mn = Bukkit.getPluginManager();
		
		mn.registerEvents(new CancelListener(), this);
		mn.registerEvents(new JoinListener(), this);
		mn.registerEvents(new PotionListener(), this);
		
	}
	
	
	@Override
	public void onDisable() {
		
		for(Player p: Bukkit.getOnlinePlayers())
			p.kickPlayer(getLanguage("messages.info.serverReload"));
		
		BossBar.removeAll();
		
		getLogger().info("Disabled!");
	}
	
	public String getLanguage(String path) {return language.getString(path);}
	public ArrayList<String> getLanguageList(String path) {return language.getStringList(path);}
	public String[] getLanguageArray(String path) {
		ArrayList<String> arrList = Main.getInstance().getLanguageList(path);
		String[] array = new String[arrList.size()];
		for(int i = 0; i < array.length; i++)
			array[i] = arrList.get(i);
		return array;
	}
	
}
