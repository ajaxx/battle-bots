package org.battlebots.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.battlebots.geometry.Rotation;
import org.battlebots.objects.Atom;
import org.battlebots.util.Vector2Serializer;
import org.dyn4j.geometry.Vector2;

public class MovementEvent {
    private static final Vector2 DEFAULT_TRANSLATION = new Vector2(0.0, 0.0);

    private Atom atom;
    private Vector2 translation;
    private Rotation rotation;

    /**
     * Constructs a new movement event.
     * @param atom the atom moving
     */
    public MovementEvent(final Atom atom) {
        this.atom = atom;
        this.translation = DEFAULT_TRANSLATION;
        this.rotation = null;
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

    @JsonProperty("rotation")
    public Rotation getRotation() {
        return rotation;
    }

    public MovementEvent setTranslation(Vector2 translation) {
        this.translation = translation;
        return this;
    }

    public MovementEvent setRotation(Rotation rotation) {
        this.rotation = rotation;
        return this;
    }
}
