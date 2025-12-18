package com.WolfChamp.timcheese.capabilities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "timcheese")
public class CapabilityEvents {

    @SubscribeEvent
    public static void attachCaps(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(RaceProvider.ID,  new RaceProvider());
            event.addCapability(CheseProvider.ID, new CheseProvider());
        }
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        // Called when player respawns / changes dimension (depending on Forge version & flags)
        Player oldP = event.getOriginal();
        Player newP = event.getEntity();

        oldP.reviveCaps();

        oldP.getCapability(ModCapabilities.RACE).ifPresent(oldRace ->
                newP.getCapability(ModCapabilities.RACE).ifPresent(newRace -> {
                    // Copy by reading NBT through providers is awkward; easiest is to store as data class:
                    if (oldRace instanceof RaceData o && newRace instanceof RaceData n) n.copyFrom(o);
                })
        );

        oldP.getCapability(ModCapabilities.CHESE).ifPresent(oldChese ->
                newP.getCapability(ModCapabilities.CHESE).ifPresent(newChese -> {
                    if (oldChese instanceof CheseData o && newChese instanceof CheseData n) n.copyFrom(o);
                })
        );

        oldP.invalidateCaps();
    }
}
