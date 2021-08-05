package org.battlebots.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.battlebots.objects.Atom;
import org.battlebots.util.Vector2Serializer;
import org.dyn4j.geometry.Vector2;

public class MovementEvent {
    private Atom atom;
    private Vector2 translation;

    /**
     * Constructs a new movement event.
     * @param atom the atom moving
     * @param translation the target translation.
     */
    public MovementEvent(final Atom atom,
                         final Vector2 translation) {
        this.atom = atom;
        this.translation = translation;
    }

    @JsonIgnore
    public Atom getAtom() {
        return atom;
    }

    @JsonProperty("atom")
    public String getAtomId() {
        return atom.getId();
    }

    @JsonSerialize(using = Vector2Serializer.class)
    public Vector2 getTranslation() {
        return translation;
    }
}
