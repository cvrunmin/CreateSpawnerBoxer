package io.github.cvrunmin.createspawnerboxer.fabric;

import io.github.cvrunmin.createspawnerboxer.IModExistenceChecker;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ModExistenceCheckerFabric implements IModExistenceChecker {
    @Override
    public boolean exists() {
        return ClientPlayNetworking.canSend(ConstantsFabric.EXIST_CHECK_CHANNEL);
    }
}
