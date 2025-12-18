package com.WolfChamp.timcheese.capabilities;

public interface IChese {
    int getSight();
    int getTouch();
    int getMind();

    void setSight(int v);
    void setTouch(int v);
    void setMind(int v);

    default void addSight(int amt) { setSight(getSight() + amt); }
    default void addTouch(int amt) { setTouch(getTouch() + amt); }
    default void addMind(int amt)  { setMind(getMind() + amt); }
}