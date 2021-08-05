package org.battlebots.arena;

import org.springframework.stereotype.Component;

import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class Simulator {
    private final Arena arena;
    private boolean isRunning;
    private Lock lock;
    private TimerTask timerTask;

    /**
     * Constructor.
     * @param arena the arena
     */
    public Simulator(final Arena arena) {
        this.arena = arena;
        this.isRunning = false;
        this.lock = new ReentrantLock();
        this.timerTask = new TimerTask() {
            @Override
            public void run() {
                onGameTick();
            }
        };
        TimerSingleton.getInstance().scheduleAtFixedRate(timerTask, 100, 100);
    }

    /**
     * Returns the arena.
     * @return the arena.
     */
    public Arena getArena() {
        return arena;
    }

    /**
     * Occurs in response to a game tick.
     */
    public void onGameTick() {
        if (isRunning) {
            arena.onSimulationTick();
        }
    }

    public void start() {
        if (lock.tryLock()) {
            isRunning = true;
        }
    }

    public void stop() {
        if (lock.tryLock()) {
            isRunning = false;
        }
    }

    /**
     * Returns true if the simulation is running.
     * @return true if the simulation is running.
     */
    public boolean isRunning() {
        lock.lock();
        try {
            return isRunning;
        } finally {
            lock.unlock();
        }
    }
}
