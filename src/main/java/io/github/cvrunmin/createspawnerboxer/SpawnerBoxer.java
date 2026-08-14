package io.github.cvrunmin.createspawnerboxer;

import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpawnerBoxer {
    public static final String MOD_ID = "createspawnerboxer";
    public static final String NAME = "Create: SpawnerBoxer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static IModExistenceChecker existenceChecker;
    public static final ResourceLocation EXIST_CHECK_CHANNEL = SpawnerBoxer.id("existence_check");


    public static void init() {

    }

    public static void clientInit(){
        PonderIndex.addPlugin(new SpawnerBoxerPonderPlugin());
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
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
