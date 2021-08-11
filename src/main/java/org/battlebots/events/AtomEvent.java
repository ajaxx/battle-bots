package org.battlebots.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.battlebots.geometry.Rotation;
import org.battlebots.objects.Atom;
import org.battlebots.util.Vector2Serializer;
import org.dyn4j.geometry.Vector2;

public class AtomEvent {
    private Atom atom;

    /**
     * Constructs a new atom event.
     * @param atom the atom
     */
    public AtomEvent(final Atom atom) {
        this.atom = atom;
    }

    @JsonProperty("atom")
    public Atom getAtom() {
        return atom;
    }
}
