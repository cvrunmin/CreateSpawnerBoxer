package io.github.cvrunmin.createspawnerboxer.forge;

import io.github.cvrunmin.createspawnerboxer.IModExistenceChecker;
import io.github.cvrunmin.createspawnerboxer.SpawnerBoxer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.ConnectionProtocol;
import net.neoforged.neoforge.network.registration.NetworkRegistry;

import java.util.UUID;

public class ModExistenceCheckerForge implements IModExistenceChecker {
    private UUID lastCheckedId;
    private boolean lastCheckExistence;

    public ModExistenceCheckerForge(){

    }

    @SuppressWarnings("UnstableApiUsage")
    public boolean exists(){
        var clientInstance = Minecraft.getInstance();
        ClientPacketListener clientPacketListener = clientInstance.getConnection();
        if (clientPacketListener == null) {
            return false;
        }
        var currentId = clientPacketListener.getId();
        if(lastCheckedId != currentId){
            lastCheckExistence = NetworkRegistry.hasChannel(clientPacketListener.getConnection(), ConnectionProtocol.CONFIGURATION, SpawnerBoxer.EXIST_CHECK_CHANNEL);
            lastCheckedId = currentId;
            SpawnerBoxer.LOGGER.info("New Checking: The existence status is: {}", lastCheckExistence);
        }
        return lastCheckExistence;
    }
}
