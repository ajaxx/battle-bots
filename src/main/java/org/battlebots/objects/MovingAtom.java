package org.battlebots.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.battlebots.events.MovementEvent;
import org.battlebots.listeners.MovementEventListener;
import org.battlebots.util.Vector2Serializer;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Vector2;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Models the basic mechanics of any atom that is capable of moving through the arena.
 * Essentially, the atom must have velocity and orientation.  Meaning, it's traveling
 * in a direction.
 */
abstract public class MovingAtom extends BasicAtom {
    @JsonIgnore
    private final CopyOnWriteArrayList<MovementEventListener> movementEventListeners = new CopyOnWriteArrayList<>();
    /** Velocity */
    @JsonSerialize(using = Vector2Serializer.class)
    private Vector2 velocity;
    /** Orientation */
    @JsonSerialize(using = Vector2Serializer.class)
    private Vector2 orientation;

    /**
     * Constructor for a atom.
     * @param shape atom shape.
     */
    public MovingAtom(final Convex shape) {
        super(shape);
        this.velocity = new Vector2(0, 0);
    }

    /**
     * Constructor for a atom.
     * @param body atom body.
     */
    public MovingAtom(final Body body) {
        super(body);
        this.velocity = new Vector2(0, 0);
    }

    /**
     * Returns the vector-based velocity of the atom.
     * @return the vector-based velocity of the atom.
     */
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity.
     * @param velocity
     * @return
     */
    public MovingAtom setVelocity(final Vector2 velocity) {
        this.velocity = velocity;
        return this;
    }

    /**
     * Returns the vector-based orientation of the atom.
     * @return the vector-based orientation of the atom.
     */

    public Vector2 getOrientation() {
        return orientation;
    }

    /**
     * Sets the atom orientation.
     * @param orientation
     * @return
     */
    public MovingAtom setOrientation(final Vector2 orientation) {
        this.orientation = orientation;
        return this;
    }

    /**
     * Occurs when the simulation executes a single tick.
     */
    public void onSimulationTick() {
        if (velocity.x != 0.0 || velocity.y != 0.0) {
            translate(velocity.x, velocity.y);
            MovementEvent movementEvent = new MovementEvent(this, getBody().getTransform().getTranslation());
            movementEventListeners.forEach(listener -> listener.onMovementEvent(movementEvent));
        }
    }

    /**
     * Adds a movement listener.
     * @param listener a movement listener.
     */

    public void addMovementListener(final MovementEventListener listener) {
        movementEventListeners.add(listener);
    }

    /**
     * Removes a movement listener.
     * @param listener a movement listener.
     */
    public void removeMovementListener(final MovementEventListener listener) {
        movementEventListeners.remove(listener);
    }
}
