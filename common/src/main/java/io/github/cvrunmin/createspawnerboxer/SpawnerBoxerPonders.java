package io.github.cvrunmin.createspawnerboxer;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.ponder.*;
import com.simibubi.create.foundation.ponder.instruction.EmitParticlesInstruction;
import com.simibubi.create.infrastructure.ponder.PonderIndex;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class SpawnerBoxerPonders {

    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(SpawnerBoxer.MOD_ID);

    public static void register(){
        HELPER.forComponents(AllBlocks.DEPLOYER).addStoryBoard("deployer/spawner_boxer", SpawnerBoxerPonders::deployerBoxingPonder);
    }

    public static void deployerBoxingPonder(SceneBuilder scene, SceneBuildingUtil util){
        scene.title("deployer_boxing", "When Deployer Meets Spawner...");
        scene.configureBasePlate(0, 0, 7);

        BlockPos deployerPos = util.grid.at(6, 1, 3);
        BlockPos spawnerPos = util.grid.at(3, 1, 3);
        Selection deployerSelection = util.select.position(deployerPos);

        scene.showBasePlate();
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.world.showSection(util.select.layer(1), Direction.DOWN);

        scene.idle(20);

        scene.overlay.showText(40)
                .placeNearTarget()
                .pointAt(util.vector.centerOf(deployerPos))
                .text("\"I'm eager with bloods...\"");

        scene.idle(50);

        scene.overlay.showText(20).placeNearTarget().pointAt(util.vector.centerOf(deployerPos)).text("\"Uhhhh!\"");
        scene.idle(25);
        scene.world.setKineticSpeed(deployerSelection, 256);
        scene.world.moveDeployer(deployerPos, 1, 10);
        scene.overlay.showText(40).placeNearTarget().pointAt(util.vector.centerOf(deployerPos)).text("\"I can't hold it anymore!\"");
        scene.idle(10);
        scene.world.moveDeployer(deployerPos, -1, 10);
        scene.overlay.showText(30).placeNearTarget().pointAt(util.vector.blockSurface(spawnerPos, Direction.WEST)).text("\"o_O;!\"");

        for (int i = 0; i < 4; i++) {
            scene.idle(10);
            scene.world.moveDeployer(deployerPos, i % 2 == 1 ? -1 : 1, 10);
        }
        var randomsource = new Random();
        for (int i = 0; i < 20; i++) {
            double particleX = spawnerPos.getX() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
            double particleY = spawnerPos.getY() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
            double particleZ = spawnerPos.getZ() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
            scene.effects.emitParticles(new Vec3(particleX, particleY, particleZ), EmitParticlesInstruction.Emitter.simple(ParticleTypes.SMOKE, Vec3.ZERO), 1, 1);
            scene.effects.emitParticles(new Vec3(particleX, particleY, particleZ), EmitParticlesInstruction.Emitter.simple(ParticleTypes.FLAME, Vec3.ZERO), 1, 1);
        }
        var entityElement = scene.world.createEntity(level -> {
            Zombie zombie = EntityType.ZOMBIE.create(level);
            Vec3 p = util.vector.topOf(spawnerPos);
            zombie.setPosRaw(p.x, p.y, p.z);
            zombie.setYRot(zombie.yRotO = 90.f);
            zombie.setYHeadRot(zombie.yHeadRotO = 90.0f);
            return zombie;
        });
        scene.idle(10);
        scene.world.moveDeployer(deployerPos, 1, 10);
        scene.idle(10);
        scene.world.moveDeployer(deployerPos, -1, 10);

        scene.overlay.showText(40)
                .placeNearTarget()
                .pointAt(util.vector.centerOf(deployerPos))
                .text("\"Battle... yes!\"");

        scene.overlay.showText(60)
                .independent(10).attachKeyFrame()
                .text("A working deployer imitating Left-click action can activate spawner, as if a player is standing nearby.");

        for (int i = 0; i < 8; i++) {
            scene.idle(10);
            scene.world.moveDeployer(deployerPos, i % 2 == 1 ? -1 : 1, 10);
        }

        scene.world.modifyEntity(entityElement, Entity::discard);
        scene.idle(10);
        scene.world.moveDeployer(deployerPos, 1, 10);
        scene.overlay.showText(40).placeNearTarget().attachKeyFrame().pointAt(util.vector.centerOf(deployerPos))
                        .text("\"Hey! No fleeing!\"");

        scene.idle(10);
        scene.world.moveDeployer(deployerPos, -1, 10);

        scene.overlay.showText(60).independent(10).text("However, mobs still despawn if player isn't within the mob spawning range.");

        for (int i = 0; i < 8; i++) {
            scene.idle(10);
            scene.world.moveDeployer(deployerPos, i % 2 == 1 ? -1 : 1, 10);
        }
        scene.overlay.showText(60).independent(10).text("Deployer on Contraption will not activate spawners. Deployer in server without Create: SpawnerBoxer installed will not activate spawners either.");
        for (int i = 0; i < 2; i++) {
            scene.idle(10);
            scene.world.moveDeployer(deployerPos, i % 2 == 1 ? -1 : 1, 10);
        }
        scene.markAsFinished();
        for (int i = 0; i < 50; i++) {
            scene.idle(10);
            scene.world.moveDeployer(deployerPos, i % 2 == 1 ? -1 : 1, 10);
        }
    }
}
