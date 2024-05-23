package io.github.cvrunmin.createspawnerboxer.forge;

import io.github.cvrunmin.createspawnerboxer.SpawnerBoxer;
import net.minecraftforge.fml.common.Mod;

@Mod(SpawnerBoxer.MOD_ID)
public class SpawnerBoxerForge {
    public SpawnerBoxerForge(){
        SpawnerBoxer.init();
    }
}
