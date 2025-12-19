package com.WolfChamp.timcheese.network;

import com.WolfChamp.timcheese.capabilities.ModCapabilities;
import com.WolfChamp.timcheese.capabilities.RaceType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AirJumpPacket {

    public AirJumpPacket() {}

    public static void encode(AirJumpPacket msg, net.minecraft.network.FriendlyByteBuf buf) {}
    public static AirJumpPacket decode(net.minecraft.network.FriendlyByteBuf buf) {
        return new AirJumpPacket();
    }

    public static void handle(AirJumpPacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null) return;

            if (player.onGround()) return;

            player.getCapability(ModCapabilities.RACE).ifPresent(race ->
                    player.getCapability(ModCapabilities.CHESE).ifPresent(chese -> {

                        if (race.getRace() != RaceType.AVIAN) return;
                        if (chese.getAirJumps() <= 0) return;
                        if (chese.getFlightStamina() < 10) return;

                        chese.useAirJump();
                        chese.setFlightStamina(chese.getFlightStamina() - 10);

                        Vec3 motion = player.getDeltaMovement();
                        player.setDeltaMovement(
                                motion.x,
                                0.5,
                                motion.z
                        );
                        player.hasImpulse = true;
                    })
            );
        });
        context.setPacketHandled(true);
    }
}
