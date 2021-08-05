package org.battlebots.messages;

import org.battlebots.objects.Atom;

public class ListAtomsResponse {
    public Atom[] atoms;

    /**
     * Constructor.
     * @param atoms
     */
    public ListAtomsResponse(final Atom[] atoms) {
        this.atoms = atoms;
    }

    /**
     * Returns the atoms.
     * @return the atoms.
     */
    public Atom[] getAtoms() {
        return atoms;
    }
}
