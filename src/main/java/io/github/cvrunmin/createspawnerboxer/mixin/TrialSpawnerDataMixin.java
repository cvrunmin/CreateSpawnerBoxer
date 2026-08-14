package io.github.cvrunmin.createspawnerboxer.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.cvrunmin.createspawnerboxer.accessor.IDeployerCacheAccessor;
import io.github.cvrunmin.createspawnerboxer.foundation.IDeployer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawner;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mixin(TrialSpawnerData.class)
public class TrialSpawnerDataMixin {

    @Definition(id = "detect", method = "Lnet/minecraft/world/level/block/entity/trialspawner/PlayerDetector;detect(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/block/entity/trialspawner/PlayerDetector$EntitySelector;Lnet/minecraft/core/BlockPos;DZ)Ljava/util/List;")
    @Expression("?.detect(?, ?, ?, ?, ?)")
    @ModifyExpressionValue(method = "tryDetectPlayers", at = @At("MIXINEXTRAS:EXPRESSION"))
    public List<UUID> warpDetectedPlayer(List<UUID> original, ServerLevel level, BlockPos pos, TrialSpawner spawner){
        var deployerCache = ((IDeployerCacheAccessor)level).getDeployerCache();
        var deployer1 = deployerCache.getDeployers(pos, spawner.getRequiredPlayerRange(), deployer -> deployer.getSpeed() != 0 && ((IDeployer)deployer).isPunchMode());
        var deployerUuid = deployer1.stream().map(deployer -> ((IDeployer) deployer).getPlayer().getUUID()).filter(uid -> !original.contains(uid)).toList();
        if(!deployerUuid.isEmpty()){
            List<UUID> list = new ArrayList<>();
            list.addAll(original);
            list.addAll(deployerUuid);
            return list;
        }
        return original;
    }
}
