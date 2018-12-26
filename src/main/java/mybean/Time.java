package mybean;

import java.util.concurrent.TimeUnit;

public class Time {
    /**
     * Time that elapsed from start.
     */
    public static String elapsedTime;
    public static long difference;
    /**
     * Time the Stopwatch started.
     */
    public static long start = 0;

    /**
     * Time the Stopwatch stopped.
     */
    public static long stop = 0;

    /**
     * Start ticking, resets the watch.
     */
    public static final void start() {
        if (start == 0) {
            start = System.currentTimeMillis();
            return;
        }
        start=System.currentTimeMillis()-difference;
    }

    /**
     * Stop ticking.
     */
    public static final void stop() {
//        if (stop == 0) {
//            start = System.currentTimeMillis();
//            return;
//        }
        stop = System.currentTimeMillis();
        elapsedTime();
    }

    /**
     * Calculates time elapsed.
     *
     * @return the time elapsed between start and stop as String in milliseconds.
     */
    public static void elapsedTime() {
        difference = (stop - start); // in ms
        elapsedTime = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(difference),
                TimeUnit.MILLISECONDS.toMinutes(difference) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(difference)),
                TimeUnit.MILLISECONDS.toSeconds(difference) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(difference)));
    }
}
