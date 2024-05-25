package io.github.cvrunmin.createspawnerboxer.forge;

import io.github.cvrunmin.createspawnerboxer.IModExistenceChecker;
import io.github.cvrunmin.createspawnerboxer.SpawnerBoxer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraftforge.network.ConnectionData;
import net.minecraftforge.network.NetworkHooks;

import java.util.UUID;

public class ModExistenceCheckerForge implements IModExistenceChecker {
    private UUID lastCheckedId;
    private boolean lastCheckExistence;

    public ModExistenceCheckerForge(){

    }

    public boolean exists(){
        var clientInstance = Minecraft.getInstance();
        ClientPacketListener clientPacketListener = clientInstance.getConnection();
        if (clientPacketListener == null) {
            return false;
        }
        var currentId = clientPacketListener.getId();
        if(lastCheckedId != currentId){
            ConnectionData data = NetworkHooks.getConnectionData(clientPacketListener.getConnection());
            if(data == null){
                lastCheckExistence = false;
            }
            else{
                lastCheckExistence = data.getModList().contains(SpawnerBoxer.MOD_ID);
            }
            lastCheckedId = currentId;
        }
        return lastCheckExistence;
    }
}
