package io.github.cvrunmin.createspawnerboxer.mixin.forge;

import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;

import com.simibubi.create.content.kinetics.deployer.DeployerFakePlayer;
import com.simibubi.create.infrastructure.config.AllConfigs;
import com.simibubi.create.infrastructure.config.CKinetics;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mixin(value = DeployerFakePlayer.class, remap = false)
public class DeployerFakePlayerMixin {
    
    @Intrinsic
    @SubscribeEvent
    public static void entitiesDontRetaliate(LivingChangeTargetEvent event){
		if (!(event.getOriginalTarget() instanceof DeployerFakePlayer))
			return;
		LivingEntity entityLiving = event.getEntity();
		if (!(entityLiving instanceof Mob))
			return;
		Mob mob = (Mob) entityLiving;

		CKinetics.DeployerAggroSetting setting = AllConfigs.server().kinetics.ignoreDeployerAttacks.get();

		switch (setting) {
		case ALL:
            event.setCanceled(true);
			break;
		case CREEPERS:
			if (mob instanceof Creeper)
				event.setCanceled(true);
			break;
		case NONE:
		default:
		}
    }
}
