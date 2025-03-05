package io.github.cvrunmin.createspawnerboxer.forge;

import io.github.cvrunmin.createspawnerboxer.SpawnerBoxer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@Mod(SpawnerBoxer.MOD_ID)
public class SpawnerBoxerForge {

    public SpawnerBoxerForge(IEventBus modBus){
        modBus.addListener(SpawnerBoxerForge::registerNetworking);
        SpawnerBoxer.init();
    }

    public static void registerNetworking(final RegisterPayloadHandlersEvent event){
        event.registrar("1").optional()
                .configurationToClient(PseudoPayload.TYPE, PseudoPayload.CODEC, (arg, iPayloadContext) -> {});
    }
}
