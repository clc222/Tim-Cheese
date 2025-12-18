package com.WolfChamp.timcheese.capabilities;

import net.minecraft.nbt.CompoundTag;

public class RaceData implements IRace {
    private RaceType race = RaceType.AVIAN; // default; change if you want

    @Override
    public RaceType getRace() {
        return race;
    }

    @Override
    public void setRace(RaceType race) {
        this.race = (race == null) ? RaceType.AVIAN : race;
    }

    // ---- Serialization ----
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("race", race.name());
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        if (tag == null) return;
        String s = tag.getString("race");
        try {
            this.race = RaceType.valueOf(s);
        } catch (Exception ignored) {
            this.race = RaceType.AVIAN;
        }
    }

    public void copyFrom(RaceData other) {
        this.race = other.race;
    }
}