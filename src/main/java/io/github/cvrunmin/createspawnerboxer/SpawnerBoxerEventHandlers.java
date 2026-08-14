package io.github.cvrunmin.createspawnerboxer;

import com.simibubi.create.content.kinetics.deployer.DeployerFakePlayer;
import io.github.cvrunmin.createspawnerboxer.forge.Config;
import io.github.cvrunmin.createspawnerboxer.mixin.BaseSpawnerAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerState;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.Optional;

@EventBusSubscriber
public class SpawnerBoxerEventHandlers {
    @SubscribeEvent
    public static void onLeftClickEntity(PlayerInteractEvent.LeftClickBlock event){
        if(event.getEntity() instanceof DeployerFakePlayer fakePlayer){
            Level level = event.getLevel();
            if (fakePlayer.getMainHandItem().isEmpty()) {
                BlockState state = level.getBlockState(event.getPos());
                if (state.is(Blocks.SPAWNER)) {
                    Optional<SpawnerBlockEntity> optBE = level.getBlockEntity(event.getPos(), BlockEntityType.MOB_SPAWNER);
                    if (optBE.isPresent()) {
                        var be = optBE.get();
                        if (level.getRandom().nextDouble() < Config.BUMP_MOB_SPAWNER_RESET_CHANCE.getAsDouble()) {
                            ((BaseSpawnerAccessor) be.getSpawner()).setSpawnDelay(0);
                        }
                        event.setCanceled(true);
                    }
                }else if (state.is(Blocks.TRIAL_SPAWNER)){
                    var optBE = level.getBlockEntity(event.getPos(), BlockEntityType.TRIAL_SPAWNER);
                    if(optBE.isPresent()){
                        var be = optBE.get();
                        if (be.getState() == TrialSpawnerState.COOLDOWN && level.getRandom().nextDouble() < Config.BUMP_TRIAL_SPAWNER_RESET_CHANCE.getAsDouble()) {
                            be.getTrialSpawner().getData().reset();
                        }
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
