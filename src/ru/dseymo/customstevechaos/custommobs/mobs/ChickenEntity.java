package ru.dseymo.customstevechaos.custommobs.mobs;

import net.minecraft.server.v1_8_R3.EntityChicken;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_8_R3.World;

public class ChickenEntity extends EntityChicken {

	public ChickenEntity(World world) {
		super(world);
		
		goalSelector.a(1, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 1.0D, false));
		
	}

}
