package com.WolfChamp.timcheese.capabilities;

import net.minecraft.nbt.CompoundTag;

public class CheseData implements IChese {

    // =====================
    // CORE CHese STATS
    // =====================
    private int sight;
    private int touch;
    private int mind;

    // =====================
    // FLIGHT / AIR JUMP DATA
    // =====================
    private int flightStamina = 100;
    private static final int MAX_FLIGHT_STAMINA = 100;

    private int airJumps = 2;
    private static final int MAX_AIR_JUMPS = 2;

    // =====================
    // GETTERS
    // =====================
    @Override public int getSight() { return sight; }
    @Override public int getTouch() { return touch; }
    @Override public int getMind()  { return mind; }

    @Override
    public int getFlightStamina() {
        return flightStamina;
    }

    public int getMaxFlightStamina() {
        return MAX_FLIGHT_STAMINA;
    }

    @Override
    public int getAirJumps() {
        return airJumps;
    }

    // =====================
    // SETTERS
    // =====================
    @Override public void setSight(int v) { sight = Math.max(0, v); }
    @Override public void setTouch(int v) { touch = Math.max(0, v); }
    @Override public void setMind(int v)  { mind  = Math.max(0, v); }

    @Override
    public void setFlightStamina(int v) {
        flightStamina = Math.max(0, Math.min(MAX_FLIGHT_STAMINA, v));
    }

    @Override
    public void setAirJumps(int v) {
        airJumps = Math.max(0, Math.min(MAX_AIR_JUMPS, v));
    }

    @Override
    public void useAirJump() {
        setAirJumps(airJumps - 1);
    }

    public void resetAirJumps() {
        airJumps = MAX_AIR_JUMPS;
    }

    // =====================
    // NBT
    // =====================
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putInt("sight", sight);
        tag.putInt("touch", touch);
        tag.putInt("mind", mind);

        tag.putInt("flightStamina", flightStamina);
        tag.putInt("airJumps", airJumps);

        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        if (tag == null) return;

        sight = Math.max(0, tag.getInt("sight"));
        touch = Math.max(0, tag.getInt("touch"));
        mind  = Math.max(0, tag.getInt("mind"));

        flightStamina = Math.max(0, tag.getInt("flightStamina"));
        airJumps = Math.max(0, tag.getInt("airJumps"));
    }

    // =====================
    // CLONE (RESPAWN / DIM CHANGE)
    // =====================
    public void copyFrom(CheseData other) {
        this.sight = other.sight;
        this.touch = other.touch;
        this.mind  = other.mind;

        this.flightStamina = other.flightStamina;
        this.airJumps = other.airJumps;
    }
}
