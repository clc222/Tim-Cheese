package com.WolfChamp.timcheese.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class RaceProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static final ResourceLocation ID =
            new ResourceLocation("timcheese", "race");

    private final RaceData data = new RaceData();
    private final LazyOptional<IRace> opt = LazyOptional.of(() -> data);

    @Override
    public <T> LazyOptional<T> getCapability(
            net.minecraftforge.common.capabilities.Capability<T> cap,
            Direction side
    ) {
        return cap == ModCapabilities.RACE ? opt.cast() : LazyOptional.empty();
    }

    @Override public CompoundTag serializeNBT() { return data.serializeNBT(); }
    @Override public void deserializeNBT(CompoundTag nbt) { data.deserializeNBT(nbt); }

    public void invalidate() { opt.invalidate(); }
}
