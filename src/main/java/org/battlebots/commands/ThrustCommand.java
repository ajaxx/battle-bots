package org.battlebots.commands;

import org.battlebots.objects.Atom;

/**
 * Command to issue a thrust request.
 */
public class ThrustCommand implements Command {
    /**
     * Applies a thrust command.
     * @param atom
     */
    public void apply(final Atom atom) {
        atom.applyThrust(1.0);
    }
}
