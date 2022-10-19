package model;

public class Time {
    private static Time instance;
    private long ticks = 0;

    private Time() { }

    public static Time getInstance() {
        if (instance == null) {
            instance = new Time();
        }
        return instance;
    }

    public void tick() {
        ticks++;
    }

    public long getTicks() {
        return ticks;
    }
}
