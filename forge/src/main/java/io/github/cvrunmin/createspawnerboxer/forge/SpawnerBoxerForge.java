package io.github.cvrunmin.createspawnerboxer.forge;

import io.github.cvrunmin.createspawnerboxer.SpawnerBoxer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.moddiscovery.ModFileInfo;

@Mod(SpawnerBoxer.MOD_ID)
public class SpawnerBoxerForge {

    public SpawnerBoxerForge(){
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(SpawnerBoxerForge::onCommonSetup);
        SpawnerBoxer.init();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> SpawnerBoxerForge.onClientCtor(modEventBus));
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> SpawnerBoxer.setModExistenceChecker(new ModExistenceCheckerForge()));
    }

    public static void onClientCtor(IEventBus modEventBus){
        modEventBus.addListener(EventPriority.HIGHEST, SpawnerBoxerForge::clientInit);
    }

    public static void clientInit(final FMLClientSetupEvent event) {
        SpawnerBoxer.clientInit();
    }

    public static void onCommonSetup(FMLCommonSetupEvent event){
        ModFileInfo modFileInfo = FMLLoader.getLoadingModList().getModFileById("create");
        if(modFileInfo != null && modFileInfo.versionString().contains("0.5.1.f")){
            IEventBus eventBus = MinecraftForge.EVENT_BUS;
            eventBus.addListener(DeployerFakePlayerPatch::noEntityAngersToDeployer);
        }
    }
}
