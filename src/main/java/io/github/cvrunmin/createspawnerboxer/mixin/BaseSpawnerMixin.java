package io.github.cvrunmin.createspawnerboxer.mixin;

import io.github.cvrunmin.createspawnerboxer.SpawnerBoxer;
import io.github.cvrunmin.createspawnerboxer.accessor.IDeployerCacheAccessor;
import io.github.cvrunmin.createspawnerboxer.accessor.IDeployerModeAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseSpawner.class)
public abstract class BaseSpawnerMixin {

    @Shadow
    private int requiredPlayerRange;

    @Inject(method = "isNearPlayer", at = @At("HEAD"), cancellable = true)
    public void createspawnerboxer$isNearbyPlayer(Level level, BlockPos pos, CallbackInfoReturnable<Boolean> cir){
        if(level.isClientSide){
            if(!SpawnerBoxer.existsOnServer()){
                return;
            }
        }
        var deployerCache = ((IDeployerCacheAccessor)level).getDeployerCache();
        var deployer1 = deployerCache.getNearestDeployer(pos, requiredPlayerRange, deployer -> deployer.getSpeed() != 0 && ((IDeployerModeAccessor)deployer).isPunchMode());
        if(deployer1 != null){
            cir.setReturnValue(true);
        }
    }
}
