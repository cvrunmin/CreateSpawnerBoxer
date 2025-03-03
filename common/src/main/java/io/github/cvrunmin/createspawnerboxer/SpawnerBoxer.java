package io.github.cvrunmin.createspawnerboxer;

import com.simibubi.create.Create;
import com.simibubi.create.CreateBuildInfo;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpawnerBoxer {
    public static final String MOD_ID = "createspawnerboxer";
    public static final String NAME = "Create: SpawnerBoxer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static IModExistenceChecker existenceChecker;


    public static void init() {
        LOGGER.info("{} initializing! Create version: {} on platform: {}", NAME, CreateBuildInfo.VERSION, SpawnerBoxerExpectedPlatform.platformName());
    }

    public static void clientInit(){
        PonderIndex.addPlugin(new SpawnerBoxerPonderPlugin());
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static void setModExistenceChecker(IModExistenceChecker checker){
        SpawnerBoxer.existenceChecker = checker;
    }

    public static boolean existsOnServer(){
        if(existenceChecker == null){
            return false;
        }
        return existenceChecker.exists();
    }
}
