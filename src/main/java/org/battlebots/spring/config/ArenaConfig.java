package org.battlebots.spring.config;

import org.battlebots.arena.ArenaFactory;
import org.battlebots.arena.DefaultArenaFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArenaConfig {
    /**
     * Returns the configured arena factory.
     * @return the configured arena factory.
     */

    @Bean
    public ArenaFactory getArenaFactory() {
        return new DefaultArenaFactory();
    }
}