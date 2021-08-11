package org.battlebots.arena;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.battlebots.events.AtomEvent;
import org.battlebots.listeners.AtomEventListener;
import org.battlebots.objects.Atom;
import org.battlebots.listeners.MovementEventListener;
import org.dyn4j.dynamics.PhysicsBody;
import org.dyn4j.dynamics.TimeStep;
import org.dyn4j.world.PhysicsWorld;
import org.dyn4j.world.World;
import org.dyn4j.world.listener.StepListener;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class Arena {
    private final CopyOnWriteArrayList<MovementEventListener> movementEventListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<AtomEventListener> atomEventListeners = new CopyOnWriteArrayList<>();

    private final World world;
    private final int width;
    private final int height;
    private final Set<Atom> atoms;

    /**
     * Constructor.
     * @param width the width of the arena.
     * @param height the height of the arena.
     */
    public Arena(
            final int width,
            final int height) {
        this.width = width;
        this.height = height;
        this.atoms = new HashSet<>();

        this.world = new World();
        this.world.addStepListener(new MyStepListener());
    }

    @JsonIgnore
    public World getWorld() {
        return world;
    }

    /**
     * Returns the width of the arena.
     * @return the width of the arena.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the arena.
     * @return the height of the arena.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Occurs when the simulation executes a single tick.
     */
    public void onSimulationTick() {
        world.update(1.0);
        atoms.forEach(atom -> atom.onSimulationTick());
    }

    /**
     * Returns all the atoms in the arena.
     * @return all the atoms in the arena.
     */
    public Atom[] getAtoms() {
        return atoms.toArray(new Atom[0]);
    }

    /**
     * Adds an atom to the arena.
     * @param atom the atom to add.
     */
    public void add(final Atom atom) {
        world.addBody(atom.getBody());

        atoms.add(atom);
        atom.addMovementListener(movementEvent ->
                movementEventListeners.forEach(listener -> listener.onMovementEvent(movementEvent)));

        AtomEvent atomEvent = new AtomEvent(atom);
        atomEventListeners.forEach(listener -> listener.onAtomEvent(atomEvent));
    }

    /**
     * Removes an atom from the arena.
     * @param atom the atom to remove.
     */

    public void remove(final Atom atom) {
        atoms.remove(atom);
    }

    public void addAtomListener(final AtomEventListener listener) {
        atomEventListeners.add(listener);
    }

    public void removeAtomListener(final AtomEventListener listener) {
        atomEventListeners.remove(listener);
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

    public class MyStepListener<T extends PhysicsBody> implements StepListener<T> {
        @Override
        public void begin(TimeStep step, PhysicsWorld<T, ?> world) {
        }

        @Override
        public void updatePerformed(TimeStep step, PhysicsWorld<T, ?> world) {
        }

        @Override
        public void postSolve(TimeStep step, PhysicsWorld<T, ?> world) {
        }

        @Override
        public void end(TimeStep step, PhysicsWorld<T, ?> world) {
        }
    }
}
