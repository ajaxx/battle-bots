package org.battlebots.spring.config;

import org.battlebots.arena.ArenaFactory;
import org.battlebots.arena.DefaultSimulatorFactory;
import org.battlebots.arena.SimulatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimulatorConfig {
    /**
     * Returns the configured simulator factory.
     * @param arenaFactory the arena factory.
     * @return the configured simulator factory.
     */

    @Bean
    public SimulatorFactory getSimulatorFactory(final ArenaFactory arenaFactory) {
        return new DefaultSimulatorFactory(arenaFactory);
    }
}