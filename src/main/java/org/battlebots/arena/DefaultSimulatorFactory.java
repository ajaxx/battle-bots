package org.battlebots.arena;

public class DefaultSimulatorFactory implements SimulatorFactory {
    private final ArenaFactory arenaFactory;

    /**
     * Constructor
     * @param arenaFactory an arena factory.
     */
    public DefaultSimulatorFactory(final ArenaFactory arenaFactory) {
        this.arenaFactory = arenaFactory;
    }

    public ArenaFactory getArenaFactory() {
        return arenaFactory;
    }

    /**
     * Creates a new simulation.
     * @return a new simulation.
     */
    public Simulator createSimulator() {
        return new Simulator(arenaFactory.createArena());
    }
}
