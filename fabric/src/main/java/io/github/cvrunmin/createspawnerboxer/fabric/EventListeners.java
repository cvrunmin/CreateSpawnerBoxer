package io.github.cvrunmin.createspawnerboxer.fabric;

import io.github.fabricators_of_create.porting_lib.entity.events.LivingEntityEvents;

public class EventListeners {
    public static void listen(){
        LivingEntityEvents.CHANGE_TARGET.register(DeployerFakePlayerPatch::noEntityAngersToDeployer);
    }
}
