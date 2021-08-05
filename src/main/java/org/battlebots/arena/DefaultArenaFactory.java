package org.battlebots.arena;

import org.battlebots.objects.Mine;
import org.battlebots.objects.Rocket;
import org.battlebots.objects.Vehicle;
import org.battlebots.objects.Wall;
import org.springframework.context.annotation.Bean;

public class DefaultArenaFactory implements ArenaFactory {
    public static final int DEFAULT_ARENA_WIDTH = 1000;
    public static final int DEFAULT_ARENA_HEIGHT = 1000;
    private final int arenaWidth;
    private final int arenaHeight;

    /**
     * Constructor.
     */
    public DefaultArenaFactory() {
        this(DEFAULT_ARENA_WIDTH, DEFAULT_ARENA_HEIGHT);
    }

    /**
     * Constructor.
     * @param arenaWidth the arena width
     * @param arenaHeight the arena height
     */

    public DefaultArenaFactory(final int arenaWidth,
                               final int arenaHeight) {
        this.arenaWidth = arenaWidth;
        this.arenaHeight = arenaHeight;
    }

    /**
     * Creates a new arena.
     * @return the arena
     */
    @Override
    public Arena createArena() {
        Arena arena = new Arena(arenaWidth, arenaHeight);
        arena.add(new Mine(20, 20));
        arena.add(new Mine(40, 40));
        arena.add(new Mine(60, 60));
        arena.add(new Mine(80, 80));
        arena.add(new Vehicle(400, 200));
        arena.add(new Wall(100, 100, 80, 10));
        arena.add(new Rocket(200, 200));
        return arena;
    }
}
