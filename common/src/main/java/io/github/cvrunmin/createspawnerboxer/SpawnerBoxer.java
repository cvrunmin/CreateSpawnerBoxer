package io.github.cvrunmin.createspawnerboxer;

import com.simibubi.create.Create;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpawnerBoxer {
    public static final String MOD_ID = "createspawnerboxer";
    public static final String NAME = "Create: SpawnerBoxer";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    public static void init() {
        LOGGER.info("{} initializing! Create version: {} on platform: {}", NAME, Create.VERSION, SpawnerBoxerExpectedPlatform.platformName());
//        ExampleBlocks.init(); // hold registrate in a separate class to avoid loading early on forge
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
