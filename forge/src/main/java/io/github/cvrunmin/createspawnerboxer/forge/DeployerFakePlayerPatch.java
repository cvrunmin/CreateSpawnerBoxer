package io.github.cvrunmin.createspawnerboxer.forge;

import com.simibubi.create.content.kinetics.deployer.DeployerFakePlayer;
import com.simibubi.create.infrastructure.config.AllConfigs;
import com.simibubi.create.infrastructure.config.CKinetics;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;

public class DeployerFakePlayerPatch {
     static void noEntityAngersToDeployer(LivingChangeTargetEvent event){
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
