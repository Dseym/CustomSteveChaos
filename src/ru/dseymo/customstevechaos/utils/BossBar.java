package ru.dseymo.customstevechaos.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import net.minecraft.server.v1_8_R3.EntityWither;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;

public class BossBar implements Listener {
	
	@Getter
	private static ArrayList<BossBar> bars = new ArrayList<>();
	
	public static void removeAll() {
		for(BossBar bar: bars)
			bar.remove();
	}
	
	 
    private String title;
    private Map<String, EntityWither> withers = new HashMap<String, EntityWither>();

    public BossBar(Plugin plugin, String title) {
    	
        this.title = title;
        Bukkit.getPluginManager().registerEvents(this, plugin);
        new BukkitRunnable() {
			
			@Override
			public void run() {update();}
			
		}.runTaskTimer(plugin, 1, 1);
        
    }
    
    public void remove() {
    	
    	PlayerQuitEvent.getHandlerList().unregister(this);
    	
    	for(String name: withers.keySet()) {
    		
    		Player p = Bukkit.getPlayer(name);
            EntityWither wither = withers.remove(p.getName());
            ReflUtil.sendPacket(p, new PacketPlayOutEntityDestroy(wither.getId()));
    		
    	}
    	
    	bars.remove(this);
    }
 
    public void addPlayer(Player p) {
    	
    	if(withers.containsKey(p.getName())) return;
    	
        EntityWither wither = new EntityWither(((CraftWorld)p.getWorld()).getHandle());
        Location l = getWitherLocation(p.getLocation());
        
        wither.setCustomName(title);
        wither.setInvisible(true);
        wither.setLocation(l.getX(), l.getY(), l.getZ(), 0, 0);
        
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(wither);
        ReflUtil.sendPacket(p, packet);
        withers.put(p.getName(), wither);
        
    }

    public void removePlayer(Player p) {
    	
    	if(!withers.containsKey(p.getName())) return;
    	
        EntityWither wither = withers.remove(p.getName());
        ReflUtil.sendPacket(p, new PacketPlayOutEntityDestroy(wither.getId()));
        
    }
 
    public void setTitle(String title) {
    	
        this.title = title;
        for (String name: withers.keySet()) {
        	
        	EntityWither wither = withers.get(name);
            wither.setCustomName(title);
            PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(wither.getId(), wither.getDataWatcher(), true);
            ReflUtil.sendPacket(Bukkit.getPlayer(name), packet);
            
        }
        
    }
 
    public void setProgress(double progress) {
    	
    	for (String name: withers.keySet()) {
    		
            EntityWither wither = withers.get(name);
            wither.setHealth((float) (progress * wither.getMaxHealth()));
            PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(wither.getId(), wither.getDataWatcher(), true);
            ReflUtil.sendPacket(Bukkit.getPlayer(name), packet);
            
        }
    	
    }
 
    public Location getWitherLocation(Location l) {
    	
        return l.add(l.getDirection().multiply(30));
        
    }

    public void update() {
    	
    	for (String name: withers.keySet()) {
        	
    		Player p = Bukkit.getPlayer(name);
            EntityWither wither = withers.get(name);
            Location l = getWitherLocation(p.getLocation());
            wither.setLocation(l.getX(), l.getY(), l.getZ(), 0, 0);
            PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(wither);
            ReflUtil.sendPacket(p, packet);
            ReflUtil.sendPacket(p, new PacketPlayOutEntityTeleport(wither.getId(), l.getBlockX()*32, l.getBlockY()*32, l.getBlockZ()*32, (byte)0, (byte)0, false));
            
        }
        
    }
    
    
	@EventHandler
	public void quit(PlayerQuitEvent e) {
		if(withers.containsKey(e.getPlayer().getName()))
			withers.remove(e.getPlayer().getName());
	}

}