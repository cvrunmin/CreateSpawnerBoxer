package io.github.cvrunmin.createspawnerboxer.forge;

import io.github.cvrunmin.createspawnerboxer.SpawnerBoxer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod(SpawnerBoxer.MOD_ID)
public class SpawnerBoxerForge {

    public SpawnerBoxerForge(){
        SpawnerBoxer.init();

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> SpawnerBoxer.setModExistenceChecker(new ModExistenceCheckerForge()));
    }
}
