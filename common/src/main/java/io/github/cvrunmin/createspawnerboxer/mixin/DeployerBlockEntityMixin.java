package io.github.cvrunmin.createspawnerboxer.mixin;

import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import com.simibubi.create.content.kinetics.deployer.DeployerItemHandler;

import io.github.cvrunmin.createspawnerboxer.DeployerFieldCache;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DeployerBlockEntity.class, remap = false)
public abstract class DeployerBlockEntityMixin implements IDeployerModeAccessor{

    @Shadow(remap = false)
	private DeployerItemHandler invHandler;

    @Inject(method = "initHandler", at=@At(value = "RETURN"), remap = false)
    public void whenInitHandler(CallbackInfo cinfo){
        if(invHandler != null){
            return;
        }
        var deployerCache = ((IDeployerCacheAccessor)((DeployerBlockEntity)(Object)this).getLevel()).getDeployerCache();
        deployerCache.addDeployer(((DeployerBlockEntity)(Object)this));
    }

    @Inject(method = "discardPlayer", at=@At("RETURN"), remap = false)
    public void whenDiscardPlayer(CallbackInfo cinfo){
        var deployerCache = ((IDeployerCacheAccessor)((DeployerBlockEntity)(Object)this).getLevel()).getDeployerCache();
        deployerCache.removeDeployer(((DeployerBlockEntity)(Object)this));
    }

    @Override
    public boolean isPunchMode() {
        Object mode = DeployerFieldCache.getMode(((DeployerBlockEntity)(Object)this));
        return mode != null && mode == DeployerFieldCache.getPunchValue();
    }
}
