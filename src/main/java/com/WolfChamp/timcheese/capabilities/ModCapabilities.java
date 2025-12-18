package com.WolfChamp.timcheese.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ModCapabilities {
    public static final Capability<IRace>  RACE  =
            CapabilityManager.get(new CapabilityToken<>() {});
    public static final Capability<IChese> CHESE =
            CapabilityManager.get(new CapabilityToken<>() {});
}
