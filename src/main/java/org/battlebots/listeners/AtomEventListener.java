package org.battlebots.listeners;

import org.battlebots.events.AtomEvent;

public interface AtomEventListener {
    /**
     * Occurs when an atom is introduced, changes, or is destroyed.
     * @param atomEvent the details of the atom.
     */
    public void onAtomEvent(AtomEvent atomEvent);
}
