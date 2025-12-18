package com.WolfChamp.timcheese.capabilities;

public interface IChese {
    int getSight();
    int getTouch();
    int getMind();

    void setSight(int v);
    void setTouch(int v);
    void setMind(int v);

    default void addSight(int v) { setSight(getSight() + v); }
    default void addTouch(int v) { setTouch(getTouch() + v); }
    default void addMind(int v)  { setMind(getMind() + v); }
}
