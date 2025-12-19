package com.WolfChamp.timcheese.capabilities;

import net.minecraft.nbt.CompoundTag;

public class RaceData implements IRace {
    private RaceType race = RaceType.HUMAN;

    @Override
    public RaceType getRace() {
        return race;
    }

    @Override
    public void setRace(RaceType race) {
        this.race = (race == null) ? RaceType.HUMAN : race;
    }

    @Override
    public boolean hasWings() {
        return race == RaceType.AVIAN;
    }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("race", race.name());
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        if (tag == null) return;
        try {
            race = RaceType.valueOf(tag.getString("race"));
        } catch (Exception e) {
            race = RaceType.HUMAN;
        }
    }

    public void copyFrom(RaceData other) {
        this.race = other.race;
    }
}
