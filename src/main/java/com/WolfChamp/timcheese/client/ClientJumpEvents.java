package com.WolfChamp.timcheese.client;

import com.WolfChamp.timcheese.network.AirJumpPacket;
import com.WolfChamp.timcheese.network.ModPackets;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "timcheese", value = Dist.CLIENT)
public class ClientJumpEvents {

    @SubscribeEvent
    public static void onKey(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (event.getKey() == mc.options.keyJump.getKey().getValue()
                && event.getAction() == 1) {
            ModPackets.CHANNEL.sendToServer(new AirJumpPacket());
        }
    }
}
