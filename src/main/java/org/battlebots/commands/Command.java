package org.battlebots.commands;

import org.battlebots.objects.Atom;

public interface Command {
    /**
     * Apply a command to an atom.
     * @param atom the atom.
     */
    void apply(Atom atom);
}
