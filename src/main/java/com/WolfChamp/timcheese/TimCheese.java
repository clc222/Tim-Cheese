package com.WolfChamp.timcheese;

import com.WolfChamp.timcheese.client.ModKeybinds;
import com.WolfChamp.timcheese.command.CheseCommand;
import com.WolfChamp.timcheese.command.RaceCommand;
import com.WolfChamp.timcheese.network.ModPackets;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TimCheese.MODID)
public class TimCheese {

    public static final String MODID = "timcheese";

    public TimCheese() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModPackets.register();
        modBus.register(ModKeybinds.class);
    }

    @Mod.EventBusSubscriber(modid = MODID)
    public static class ForgeEvents {

        @SubscribeEvent
        public static void registerCommands(RegisterCommandsEvent event) {
            RaceCommand.register(event.getDispatcher());
            CheseCommand.register(event.getDispatcher());
        }
    }
}
