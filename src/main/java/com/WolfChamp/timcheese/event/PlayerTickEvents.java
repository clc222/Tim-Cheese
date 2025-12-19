package com.WolfChamp.timcheese.event;

import com.WolfChamp.timcheese.capabilities.CheseData;
import com.WolfChamp.timcheese.capabilities.ModCapabilities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "timcheese")
public class PlayerTickEvents {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.player instanceof ServerPlayer player)) return;

        if (player.onGround()) {
            player.getCapability(ModCapabilities.CHESE).ifPresent(chese -> {
                if (chese instanceof CheseData data) {
                    data.resetAirJumps();
                }
            });
        }
    }
}
