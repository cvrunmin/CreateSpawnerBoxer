package io.github.cvrunmin.createspawnerboxer;

import java.util.*;
import java.util.function.Predicate;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import io.github.cvrunmin.createspawnerboxer.foundation.IDeployer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;

public class LevelBasedDeployerCache {
    private HashMap<SectionPos, HashMap<BlockPos, KineticBlockEntity>> deployersFromSectionPos = new HashMap<>();

    public List<KineticBlockEntity> getDeployers(BlockPos centerPos, int searchRadius, Predicate<KineticBlockEntity> predicate){
        SectionPos centerSectionPos = SectionPos.of(centerPos);
        SectionPos lowerLeft = SectionPos.of(centerPos.offset(-searchRadius, -searchRadius, -searchRadius));
        SectionPos upperRight = SectionPos.of(centerPos.offset(searchRadius, searchRadius, searchRadius));
        List<SectionPos> searchingPos = SectionPos.betweenClosedStream(lowerLeft.x(), lowerLeft.y(), lowerLeft.z(), upperRight.x(), upperRight.y(), upperRight.z())
                .sorted(Comparator.comparingInt(o -> o.distManhattan(centerSectionPos))).toList();
        double radiusSquared = Math.pow(searchRadius, 2);
        var result = new ArrayList<KineticBlockEntity>();
        for(SectionPos secPos : searchingPos){
            if(deployersFromSectionPos.containsKey(secPos)){
                for(var blockEntity : deployersFromSectionPos.get(secPos).values()){
                    if(blockEntity == null) continue;
                    double newDist = centerPos.distSqr(blockEntity.getBlockPos());
                    if(newDist > radiusSquared) continue;
                    if(!predicate.test(blockEntity)) continue;
                    result.add(blockEntity);
                }
            }
        }
        return result;
    }
    
    public KineticBlockEntity getNearestDeployer(BlockPos centerPos, int searchRadius){
        return getNearestDeployer(centerPos, searchRadius, (deployer) -> true);
    }

    public KineticBlockEntity getNearestDeployer(BlockPos centerPos, int searchRadius, Predicate<KineticBlockEntity> predicate){
        SectionPos centerSectionPos = SectionPos.of(centerPos);
        SectionPos lowerLeft = SectionPos.of(centerPos.offset(-searchRadius, -searchRadius, -searchRadius));
        SectionPos upperRight = SectionPos.of(centerPos.offset(searchRadius, searchRadius, searchRadius));
        List<SectionPos> searchingPos = SectionPos.betweenClosedStream(lowerLeft.x(), lowerLeft.y(), lowerLeft.z(), upperRight.x(), upperRight.y(), upperRight.z())
            .sorted(Comparator.comparingInt(o -> o.distManhattan(centerSectionPos))).toList();
        
        KineticBlockEntity lastWinner = null;
        double lastWinnerDist = Double.MAX_VALUE;
        int lastWinnerSectionDist = Integer.MAX_VALUE;
        for(SectionPos secPos : searchingPos){
            if(centerPos.distManhattan(secPos) > lastWinnerSectionDist){
                break; // no more candidate can be found
            }
            if(deployersFromSectionPos.containsKey(secPos)){
                for(var blockEntity : deployersFromSectionPos.get(secPos).values()){
                    if(blockEntity == null) continue;
                    double newDist = centerPos.distSqr(blockEntity.getBlockPos());
                    if(newDist > lastWinnerDist) continue;
                    if(!predicate.test(blockEntity)) continue;
                    lastWinner = blockEntity;
                    lastWinnerDist = newDist;
                    lastWinnerSectionDist = centerPos.distManhattan(secPos);
                }
            }

        }
        return lastWinner;
    }

    public void addDeployer(KineticBlockEntity entity){
        this.deployersFromSectionPos.computeIfAbsent(SectionPos.of(entity.getBlockPos()), idx -> new HashMap<>()).put(entity.getBlockPos(), entity);
    }

    public boolean removeDeployer(KineticBlockEntity entity){
        SectionPos secPos = SectionPos.of(entity.getBlockPos());
        if(this.deployersFromSectionPos.containsKey(secPos)){
            return this.deployersFromSectionPos.get(secPos).remove(entity.getBlockPos()) != null;
        }
        return false;
    }
}
