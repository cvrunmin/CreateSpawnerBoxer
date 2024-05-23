package io.github.cvrunmin.createspawnerboxer.mixin;

import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = DeployerBlockEntity.class, remap = false)
public abstract class DeployerBlockEntityMixin {

    @Inject(method = "initHandler", at=@At("RETURN"), remap = false)
    public void whenInitHandler(CallbackInfo cinfo){

    }

    @Inject(method = "discardPlayer", at=@At("RETURN"), remap = false)
    public void whenDiscardPlayer(CallbackInfo cinfo){

    }
}
