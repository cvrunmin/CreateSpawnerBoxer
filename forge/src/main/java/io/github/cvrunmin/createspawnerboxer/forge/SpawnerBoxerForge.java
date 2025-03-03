package io.github.cvrunmin.createspawnerboxer.forge;

import io.github.cvrunmin.createspawnerboxer.SpawnerBoxer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SpawnerBoxer.MOD_ID)
public class SpawnerBoxerForge {

    public SpawnerBoxerForge(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        SpawnerBoxer.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> SpawnerBoxerForge.onClientCtor(modEventBus));
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> SpawnerBoxer.setModExistenceChecker(new ModExistenceCheckerForge()));
    }

    public static void onClientCtor(IEventBus modEventBus){
        modEventBus.addListener(SpawnerBoxerForge::clientInit);
    }

    public static void clientInit(final FMLClientSetupEvent event) {
        SpawnerBoxer.clientInit();
    }
}
