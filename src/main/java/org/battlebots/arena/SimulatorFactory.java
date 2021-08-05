package org.battlebots.arena;

public interface SimulatorFactory {
    /**
     * Creates a new simulation.
     * @return a new simulation.
     */
    public Simulator createSimulator();
}
