package org.battlebots.objects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.battlebots.util.BodySerializer;
import org.battlebots.util.Vector2Serializer;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Shape;
import org.dyn4j.geometry.Vector2;

/**
 * Models the basic mechanics of any atom that is capable of moving through the arena.
 * Essentially, the atom must have velocity and orientation.  Meaning, it's traveling
 * in a direction.
 */
abstract public class MovingAtom extends BasicAtom {
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
     * Returns the vector-based orientation of the atom.
     * @return the vector-based orientation of the atom.
     */

    public Vector2 getOrientation() {
        return orientation;
    }

    /**
     * Occurs when the simulation executes a single tick.
     */
    public void onSimulationTick() {
        translate(velocity.x, velocity.y);
    }
}
