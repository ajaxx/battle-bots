package org.battlebots.arena;

import org.battlebots.objects.Atom;

import java.util.HashSet;
import java.util.Set;

public class Arena {
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
        atoms.add(atom);
    }

    /**
     * Removes an atom from the arena.
     * @param atom the atom to remove.
     */

    public void remove(final Atom atom) {
        atoms.remove(atom);
    }
}
