package io.github.cvrunmin.createspawnerboxer.fabric;

import io.github.cvrunmin.createspawnerboxer.SpawnerBoxer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class SpawnerBoxerFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SpawnerBoxer.init();
        ServerPlayNetworking.registerGlobalReceiver(ConstantsFabric.EXIST_CHECK_CHANNEL,
                (server, player, handler, buf, responseSender) -> { }
        );
    }
}
