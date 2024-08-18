package io.github.cvrunmin.createspawnerboxer.fabric;

import io.github.cvrunmin.createspawnerboxer.SpawnerBoxer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;

public class SpawnerBoxerFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SpawnerBoxer.init();
        ServerPlayNetworking.registerGlobalReceiver(ConstantsFabric.EXIST_CHECK_CHANNEL,
                (server, player, handler, buf, responseSender) -> { }
        );
        if(FabricLoader.getInstance().isModLoaded("create")){
            if(FabricLoader.getInstance().getModContainer("create")
                    .map(modContainer -> modContainer.getMetadata().getVersion().getFriendlyString())
                    .map(versionString -> versionString.contains("0.5.1-f")).orElse(false)){
                // separate to another class as ChangeTarget event is shipped after patch.1
                EventListeners.listen();
            }
        }
    }
}
