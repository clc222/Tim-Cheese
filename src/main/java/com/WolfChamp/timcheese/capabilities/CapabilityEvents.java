package com.WolfChamp.timcheese.capabilities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.server.level.ServerPlayer;
import com.WolfChamp.timcheese.network.ModPackets;

@Mod.EventBusSubscriber(modid = "timcheese")
public class CapabilityEvents {

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer sp) {
            ModPackets.sync(sp);
        }
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer sp) {
            ModPackets.sync(sp);
        }
    }

    @SubscribeEvent
    public static void attachCaps(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(RaceProvider.ID, new RaceProvider());
            event.addCapability(CheseProvider.ID, new CheseProvider());
        }
    }

    @SubscribeEvent
    public static void clonePlayer(PlayerEvent.Clone event) {
        Player oldP = event.getOriginal();
        Player newP = event.getEntity();

        oldP.reviveCaps();

        oldP.getCapability(ModCapabilities.RACE).ifPresent(o ->
                newP.getCapability(ModCapabilities.RACE).ifPresent(n ->
                        ((RaceData) n).copyFrom((RaceData) o)
                )
        );

        oldP.getCapability(ModCapabilities.CHESE).ifPresent(o ->
                newP.getCapability(ModCapabilities.CHESE).ifPresent(n ->
                        ((CheseData) n).copyFrom((CheseData) o)
                )
        );

        oldP.invalidateCaps();
    }
}
