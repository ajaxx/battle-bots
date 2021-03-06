package org.battlebots.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.battlebots.commands.Command;
import org.battlebots.commands.ThrustCommand;
import org.battlebots.events.MovementEvent;
import org.battlebots.geometry.Rotation;
import org.battlebots.listeners.MovementEventListener;
import org.battlebots.util.Vector2Serializer;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Vector2;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Models the basic mechanics of any atom that is capable of moving through the arena.
 * Essentially, the atom must have velocity and orientation.  Meaning, it's traveling
 * in a direction.
 */
abstract public class MovingAtom extends BasicAtom {
    @JsonIgnore
    private final CopyOnWriteArrayList<MovementEventListener> movementEventListeners = new CopyOnWriteArrayList<>();
    @JsonIgnore
    private final Queue<Command> commandQueue = new LinkedList<Command>();

    /** Velocity */
    @JsonSerialize(using = Vector2Serializer.class)
    private Vector2 velocity;
    /** Angle (in radians) */
    private double angle;
    /** Angular velocity (in radians) */
    private double angularVelocity;

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
     * Returns the current angle.
     * @return the current angle.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Sets the current angle.
     * @param angle the new angle.
     */

    public MovingAtom setAngle(double angle) {
        this.angle = angle;
        return this;
    }

    /**
     * Returns the angular velocity.
     * @return the angular velocity.
     */

    public double getAngularVelocity() {
        return angularVelocity;
    }

    /**
     * Sets the angular velocity.
     * @param angularVelocity the angular velocity.
     */
    public MovingAtom setAngularVelocity(double angularVelocity) {
        this.angularVelocity = angularVelocity;
        return this;
    }

    public MovingAtom rotate(final double theta) {
        super.rotate(theta);
        angle = getBody().getTransform().getRotationAngle();
        return this;
    }

    private MovementEvent movementEvent;

    private MovementEvent getOrCreateMovementEvent() {
        if (movementEvent == null) {
            movementEvent = new MovementEvent(this);
        }
        return movementEvent;
    }

    /**
     * Occurs when the simulation executes a single tick.
     */
    public void onSimulationTick() {
        synchronized (commandQueue) {
            while(!commandQueue.isEmpty()) {
                Command command = commandQueue.poll();
                command.apply(this);
            }
        }

        applyTurn(angularVelocity);
        applyVelocity();

        if (movementEvent != null) {
            final MovementEvent finalizedMovementEvent = movementEvent;
            movementEventListeners.forEach(listener -> listener.onMovementEvent(finalizedMovementEvent));
        }
    }

    public void applyTurn(final double angularRotation) {
        if (angularRotation != 0.0) {
            rotate(angularRotation);
            Vector2 center = getBoundingBoxCenter();
            MovementEvent movementEvent = getOrCreateMovementEvent();
            movementEvent.setRotation(new Rotation(getAngle(), center.x, center.y));
        }
    }

    public void applyThrust(final double magnitude) {
        // thrust is always a vector adjustment based on current direction
        final double angleInRadians = getAngle();
        final double ax = magnitude * Math.cos(angleInRadians);
        final double ay = magnitude * Math.sin(angleInRadians);
        velocity.x += ax;
        velocity.y += ay;
    }

    public void applyVelocity() {
        if (velocity.x != 0.0 || velocity.y != 0.0) {
            translate(velocity.x, velocity.y);
            MovementEvent movementEvent = getOrCreateMovementEvent();
            movementEvent.setTranslation(getBody().getTransform().getTranslation());
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

    /**
     * Adds a command to the queue.
     * @param command the command to add.
     */
    public void queue(Command command) {
        commandQueue.add(command);
    }
}
