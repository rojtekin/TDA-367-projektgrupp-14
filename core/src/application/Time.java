package application;

/**
 * A class responsible for keeping track of time. The time is measured in ticks.
 */
public class Time {
    private static Time instance;
    private long ticks = 0;

    private Time() { }

    /**
     * @return the one and only instance of the class
     */
    public static Time getInstance() {
        if (instance == null) {
            instance = new Time();
        }
        return instance;
    }

    /**
     * Increments the number of ticks.
     */
    public void tick() {
        ticks++;
    }

    /**
     * @return the current number of ticks
     */
    public long getTicks() {
        return ticks;
    }
}
