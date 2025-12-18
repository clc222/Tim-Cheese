package com.WolfChamp.timcheese.capabilities;

import net.minecraft.nbt.CompoundTag;

public class CheseData implements IChese {
    private int sight = 0;
    private int touch = 0;
    private int mind  = 0;

    @Override public int getSight() { return sight; }
    @Override public int getTouch() { return touch; }
    @Override public int getMind()  { return mind;  }

    @Override public void setSight(int v) { sight = Math.max(0, v); }
    @Override public void setTouch(int v) { touch = Math.max(0, v); }
    @Override public void setMind(int v)  { mind  = Math.max(0, v); }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("sight", sight);
        tag.putInt("touch", touch);
        tag.putInt("mind", mind);
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        if (tag == null) return;
        sight = Math.max(0, tag.getInt("sight"));
        touch = Math.max(0, tag.getInt("touch"));
        mind  = Math.max(0, tag.getInt("mind"));
    }

    public void copyFrom(CheseData other) {
        this.sight = other.sight;
        this.touch = other.touch;
        this.mind  = other.mind;
    }
}
