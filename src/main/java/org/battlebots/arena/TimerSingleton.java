package org.battlebots.arena;

import java.util.Timer;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The singleton pattern implemented to provide and expose a single timer for the
 * application.
 */

public class TimerSingleton {
    private static Lock singletonLock = new ReentrantLock();
    private static Timer timer;

    /**
     * Returns the singleton timer instance.
     * @return the singleton timer instance.
     */
    public static Timer getInstance() {
        singletonLock.lock();
        try {
            if (timer == null) {
                timer = new Timer(true);
            }

            return timer;
        } finally {
            singletonLock.unlock();
        }
    }
}
