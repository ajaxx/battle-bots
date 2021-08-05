package org.battlebots.arena;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Timer;
import java.util.TimerTask;

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
        Timer timer = Mockito.mock(Timer.class);
        TimerSingleton.setInstance(timer);

        DefaultArenaFactory arenaFactory = new DefaultArenaFactory();
        DefaultSimulatorFactory simulatorFactory = new DefaultSimulatorFactory(arenaFactory);
        Simulator simulator = simulatorFactory.createSimulator();
        assertThat(simulator).isNotNull();
        assertThat(simulator.isRunning()).isFalse();
        assertThat(simulator.getArena()).isNotNull();

        Mockito.verify(timer, Mockito.times(1)).scheduleAtFixedRate(
                Mockito.any(TimerTask.class),
                Mockito.eq(100L),
                Mockito.eq(100L));
    }
}