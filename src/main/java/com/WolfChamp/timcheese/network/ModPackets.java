package com.WolfChamp.timcheese.network;

import com.WolfChamp.timcheese.capabilities.ModCapabilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPackets {

    private static final String PROTOCOL = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation("timcheese", "main"),
            () -> PROTOCOL,
            PROTOCOL::equals,
            PROTOCOL::equals
    );

    private static int id = 0;

    public static void register() {
        CHANNEL.registerMessage(
                id++,
                SyncChesePacket.class,
                SyncChesePacket::encode,
                SyncChesePacket::decode,
                SyncChesePacket::handle
        );
    }

    public static void sync(ServerPlayer player) {
        player.getCapability(ModCapabilities.RACE).ifPresent(race ->
                player.getCapability(ModCapabilities.CHESE).ifPresent(chese ->
                        CHANNEL.send(
                                PacketDistributor.PLAYER.with(() -> player),
                                new SyncChesePacket(
                                        race.getRace(),
                                        chese.getSight(),
                                        chese.getTouch(),
                                        chese.getMind()
                                )
                        )
                )
        );
    }
}
