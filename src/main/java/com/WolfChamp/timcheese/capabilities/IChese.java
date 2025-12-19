package com.WolfChamp.timcheese.capabilities;

public interface IChese {

    // =====================
    // CORE STATS
    // =====================
    int getSight();
    void setSight(int v);

    int getTouch();
    void setTouch(int v);

    int getMind();
    void setMind(int v);

    // =====================
    // FLIGHT / AIR JUMPS
    // =====================
    int getFlightStamina();
    void setFlightStamina(int v);

    int getAirJumps();
    void setAirJumps(int v);

    default void useAirJump() {
        setAirJumps(getAirJumps() - 1);
    }

    // =====================
    // CONVENIENCE ADDERS
    // =====================
    default void addSight(int v) {
        setSight(getSight() + v);
    }

    default void addTouch(int v) {
        setTouch(getTouch() + v);
    }

    default void addMind(int v) {
        setMind(getMind() + v);
    }

}
