package com.WolfChamp.timcheese.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "timcheese",
        value = Dist.CLIENT,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class ModHud {

    @SubscribeEvent
    public static void register(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll(
                "chese_hud",
                ModHud::render
        );
    }

    private static void render(
            ForgeGui forgeGui,
            GuiGraphics gui,
            float partialTick,
            int width,
            int height
    ) {
        Minecraft mc = Minecraft.getInstance();

        int x = 10;
        int y = 10;

        gui.drawString(mc.font, "Race: " + ClientCheseData.race.name(), x, y, 0xFFFFFF);
        y += 14;

        gui.drawString(mc.font, "Sight: " + ClientCheseData.sight, x, y, 0x00FFFF);
        y += 10;

        gui.drawString(mc.font, "Touch: " + ClientCheseData.touch, x, y, 0xFF5555);
        y += 10;

        gui.drawString(mc.font, "Mind: " + ClientCheseData.mind, x, y, 0xAA55FF);
    }
}
