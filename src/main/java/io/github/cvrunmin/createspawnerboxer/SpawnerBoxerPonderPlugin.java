package io.github.cvrunmin.createspawnerboxer;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class SpawnerBoxerPonderPlugin implements PonderPlugin {
    @Override
    public String getModId() {
        return SpawnerBoxer.MOD_ID;
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        SpawnerBoxerPonders.register(helper);
    }
}
