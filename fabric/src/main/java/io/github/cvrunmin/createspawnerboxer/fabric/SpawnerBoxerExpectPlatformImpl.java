package io.github.cvrunmin.createspawnerboxer.fabric;

import io.github.cvrunmin.createspawnerboxer.SpawnerBoxerExpectedPlatform;
import net.fabricmc.loader.api.FabricLoader;

public class SpawnerBoxerExpectPlatformImpl extends SpawnerBoxerExpectedPlatform {
    public static String platformName() {
        return FabricLoader.getInstance().isModLoaded("quilt_loader") ? "Quilt" : "Fabric";
    }
}
