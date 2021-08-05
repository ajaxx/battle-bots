package org.battlebots.objects;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.battlebots.listeners.MovementEventListener;
import org.dyn4j.dynamics.Body;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface Atom {
    /**
     * Occurs when the atom collides with another atom.
     * @param atom the other atom.
     */
    default void onCollision(Atom atom) {
    }

    /**
     * Occurs when the simulation executes a single tick.
     */
    default void onSimulationTick() {
    }

    /**
     * Adds a movement listener.
     * @param listener a movement listener.
     */

    default void addMovementListener(final MovementEventListener listener) {
    }

    /**
     * Removes a movement listener.
     * @param listener a movement listener.
     */
    default void removeMovementListener(final MovementEventListener listener) {
    }

    /**
     * Returns the identifier for the atom.
     * @return the identifier for the atom.
     */
    String getId();

    /**
     * Returns the body for the atom.
     * @return the body for the atom.
     */
    Body getBody();
}
