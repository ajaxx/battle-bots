package org.battlebots.arena;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultArenaFactoryTest {
    @Test
    public void canCreateDefaultArenaFactory() {
        DefaultArenaFactory arenaFactory = new DefaultArenaFactory();
        assertThat(arenaFactory).isNotNull();
        assertThat(arenaFactory.getArenaWidth()).isEqualTo(DefaultArenaFactory.DEFAULT_ARENA_WIDTH);
        assertThat(arenaFactory.getArenaHeight()).isEqualTo(DefaultArenaFactory.DEFAULT_ARENA_HEIGHT);
    }

    @Test
    public void canCreateDefaultArenaFactoryWithSize() {
        DefaultArenaFactory arenaFactory = new DefaultArenaFactory(10,20);
        assertThat(arenaFactory).isNotNull();
        assertThat(arenaFactory.getArenaWidth()).isEqualTo(10);
        assertThat(arenaFactory.getArenaHeight()).isEqualTo(20);
    }

    @Test
    public void canCreateArena() {
        DefaultArenaFactory arenaFactory = new DefaultArenaFactory();
        Arena arena = arenaFactory.createArena();
        assertThat(arena).isNotNull();
        assertThat(arena.getWidth()).isEqualTo(DefaultArenaFactory.DEFAULT_ARENA_WIDTH);
        assertThat(arena.getHeight()).isEqualTo(DefaultArenaFactory.DEFAULT_ARENA_HEIGHT);
    }
}