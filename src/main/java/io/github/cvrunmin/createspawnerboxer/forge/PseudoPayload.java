package io.github.cvrunmin.createspawnerboxer.forge;

import io.github.cvrunmin.createspawnerboxer.SpawnerBoxer;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class PseudoPayload implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<PseudoPayload> TYPE = new Type<>(SpawnerBoxer.EXIST_CHECK_CHANNEL);

    public static final StreamCodec<ByteBuf, PseudoPayload> CODEC = StreamCodec.unit(new PseudoPayload());
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
