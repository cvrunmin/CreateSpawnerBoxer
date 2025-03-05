package io.github.cvrunmin.createspawnerboxer.forge;

import io.github.cvrunmin.createspawnerboxer.SpawnerBoxer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = SpawnerBoxer.MOD_ID, dist = Dist.CLIENT)
public class SpawnerBoxerForgeClient {

    public SpawnerBoxerForgeClient(IEventBus modBus){
        SpawnerBoxer.setModExistenceChecker(new ModExistenceCheckerForge());
        modBus.addListener(SpawnerBoxerForgeClient::clientInit);
    }

    public static void clientInit(final FMLClientSetupEvent event) {
        SpawnerBoxer.clientInit();
    }

}
