package ru.dseymo.customstevechaos.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReflUtil {
	
	public static void sendPacket(Player player, Object packet) {
	 
		try {
			
			Object playerConnection = getValue(getHandle(player), "playerConnection");
			invokeMethod(playerConnection, "sendPacket", false, new Class[] {getNMSClass("Packet")}, packet);
		  
		} catch (Exception e) {e.printStackTrace();} 
		  
	}
	
	public static void sendPackets(Object packet) {
		
		for(Player player: Bukkit.getOnlinePlayers())
			sendPacket(player, packet);
		
	}
	
	public static void setValue(Object object, String fieldName, Object value) {
		
		try {
			
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(object, value);
			
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
	public static Object getValue(Object object, String fieldName) {
		
		try {
			
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(object);
			
		} catch (Exception e) {e.printStackTrace(); return null;}
		
	}
	
	public static Object instanceWithConstr(Class<?> clas, Class<?>[] classArgs, Object... args) {
		
		try {
			
			Constructor<?> constr = clas.getConstructor(classArgs);
			return constr.newInstance(args);
			
		} catch (Exception e) {e.printStackTrace(); return null;}
		
	}
	
	public static Object instance(Class<?> clas) {try {return clas.newInstance();} catch (Exception e) {e.printStackTrace(); return null;}}
	
	public static Object invokeMethod(Object object, String methodName, boolean isStatic, Class<?>[] classArgs, Object... args) {
		
		try {
			
			if(isStatic)
				return ((Class<?>)object).getMethod(methodName, classArgs).invoke(null, args);
			else
				return object.getClass().getMethod(methodName, classArgs).invoke(object, args);
			
		} catch (Exception e) {e.printStackTrace(); return null;}
		
	}
	
	public static Object invokeMethod(Object object, String methodName, boolean isStatic) {
		
		try {
			
			if(isStatic)
				return ((Class<?>)object).getMethod(methodName).invoke(null);
			else
				return object.getClass().getMethod(methodName).invoke(object);
			
		} catch (Exception e) {e.printStackTrace(); return null;}
		
	}
	
	public static String getVersion() {return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];}
	public static Class<?> getNMSClass(String name) {try {return Class.forName("net.minecraft.server." + getVersion() + "." + name);} catch (ClassNotFoundException e) {e.printStackTrace(); return null;}}
	public static Object getHandle(Object bukkitObj) {return invokeMethod(bukkitObj, "getHandle", false);}
	
}
