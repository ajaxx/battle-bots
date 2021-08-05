package org.battlebots.arena;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultSimulatorFactoryTest {
    @Test
    public void canCreateDefaultSimulatorFactory() {
        DefaultArenaFactory arenaFactory = new DefaultArenaFactory();
        DefaultSimulatorFactory simulatorFactory = new DefaultSimulatorFactory(arenaFactory);
        assertThat(simulatorFactory).isNotNull();
        assertThat(simulatorFactory.getArenaFactory()).isSameAs(arenaFactory);
    }

    @Test
    public void canCreateSimulator() {
        DefaultArenaFactory arenaFactory = new DefaultArenaFactory();
        DefaultSimulatorFactory simulatorFactory = new DefaultSimulatorFactory(arenaFactory);
        Simulator simulator = simulatorFactory.createSimulator();
        assertThat(simulator).isNotNull();
        assertThat(simulator.isRunning()).isFalse();
        assertThat(simulator.getArena()).isNotNull();
    }
}