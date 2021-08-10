package org.battlebots.arena;

import org.battlebots.objects.Vehicle;
import org.battlebots.objects.Wall;
import org.dyn4j.geometry.Vector2;

public class DefaultArenaFactory implements ArenaFactory {
    public static final int DEFAULT_ARENA_WIDTH = 1000;
    public static final int DEFAULT_ARENA_HEIGHT = 500;
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

    public int getArenaWidth() {
        return arenaWidth;
    }

    public int getArenaHeight() {
        return arenaHeight;
    }

    /**
     * Creates a new arena.
     * @return the arena
     */
    @Override
    public Arena createArena() {
        // the omega race
        Arena arena = new Arena(arenaWidth, arenaHeight);
        arena.add(new Wall(150, 125, 700, 250));
        arena.add(Vehicle
                .createPlayerVehicle(850, 425)
                .setVelocity(Vector2.create(1, 0))
        );
        arena.add(Vehicle
                .createBotVehicle(50, 25)
                .setVelocity(Vector2.create(1, 0))
                .setAngularVelocity(0.2617994)
        );
        return arena;
    }
}
