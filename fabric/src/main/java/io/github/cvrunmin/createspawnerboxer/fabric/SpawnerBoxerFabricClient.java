package io.github.cvrunmin.createspawnerboxer.fabric;

import io.github.cvrunmin.createspawnerboxer.SpawnerBoxer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class SpawnerBoxerFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(ConstantsFabric.EXIST_CHECK_CHANNEL,
                (client, handler, buf, responseSender) -> { }
        );
        SpawnerBoxer.setModExistenceChecker(new ModExistenceCheckerFabric());
    }
}
