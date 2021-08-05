package org.battlebots.messages;

import org.battlebots.objects.Atom;

public class InitialStateMessage {
    public int width;
    public int height;
    public Atom[] atoms;

    public InitialStateMessage(final int width, final int height, final Atom[] atoms) {
        this.width = width;
        this.height = height;
        this.atoms = atoms;
    }
}
