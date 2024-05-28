package io.github.cvrunmin.createspawnerboxer.fabric;

import com.simibubi.create.content.kinetics.deployer.DeployerFakePlayer;
import com.simibubi.create.infrastructure.config.AllConfigs;
import com.simibubi.create.infrastructure.config.CKinetics;
import io.github.fabricators_of_create.porting_lib.entity.events.LivingEntityEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Creeper;

public class DeployerFakePlayerPatch {
    static void noEntityAngersToDeployer(LivingEntityEvents.ChangeTarget.ChangeTargetEvent event){
        if (!(event.getOriginalTarget() instanceof DeployerFakePlayer))
            return;
        Entity entityLiving = event.getEntity();
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
