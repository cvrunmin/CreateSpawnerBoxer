package io.github.cvrunmin.createspawnerboxer.mixin;

import org.spongepowered.asm.mixin.Mixin;

import io.github.cvrunmin.createspawnerboxer.LevelBasedDeployerCache;
import net.minecraft.world.level.Level;

@Mixin(Level.class)
public abstract class LevelMixin implements IDeployerCacheAccessor {

    private LevelBasedDeployerCache deployerCache = new LevelBasedDeployerCache();

    @Override
    public LevelBasedDeployerCache getDeployerCache() {
        return deployerCache;
    }
}
