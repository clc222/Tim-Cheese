package com.WolfChamp.timcheese.network;

import com.WolfChamp.timcheese.capabilities.RaceType;
import com.WolfChamp.timcheese.client.ClientCheseData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public record SyncChesePacket(
        RaceType race,
        int sight,
        int touch,
        int mind
) {

    public static void encode(SyncChesePacket msg, FriendlyByteBuf buf) {
        buf.writeEnum(msg.race);
        buf.writeInt(msg.sight);
        buf.writeInt(msg.touch);
        buf.writeInt(msg.mind);
    }

    public static SyncChesePacket decode(FriendlyByteBuf buf) {
        return new SyncChesePacket(
                buf.readEnum(RaceType.class),
                buf.readInt(),
                buf.readInt(),
                buf.readInt()
        );
    }

    public static void handle(SyncChesePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientCheseData.race  = msg.race();
            ClientCheseData.sight = msg.sight();
            ClientCheseData.touch = msg.touch();
            ClientCheseData.mind  = msg.mind();
        });
        ctx.get().setPacketHandled(true);
    }
}
