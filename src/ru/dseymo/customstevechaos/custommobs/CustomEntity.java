package ru.dseymo.customstevechaos.custommobs;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityTypes;
import ru.dseymo.customstevechaos.custommobs.mobs.ChickenEntity;

public enum CustomEntity {
	
	CHICKEN(ChickenEntity.class, "Chicken", 93);
	
	private CustomEntity(Class<? extends EntityInsentient> customClazz, String name, int id) {
		
		try {
			
			ArrayList<Map<?, ?>> data = new ArrayList<Map<?, ?>>();
			for(Field f: EntityTypes.class.getDeclaredFields()) {
				
				f.setAccessible(true);
				data.add((Map<?, ?>)f.get(null));
				
			}
			
			if(data.get(1).containsKey(id)) {
				
				data.get(1).remove(id);
				data.get(3).remove(name);
				
			}
			
			Method method = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class);
	        method.setAccessible(true);
	        method.invoke(null, customClazz, name, id);
			
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
}
