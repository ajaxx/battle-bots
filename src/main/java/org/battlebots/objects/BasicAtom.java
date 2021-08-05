package org.battlebots.objects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.battlebots.util.BodySerializer;
import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Convex;

import java.util.UUID;

public class BasicAtom implements Atom {
    /** The identifier for the atom */
    private String id;
    /** Underlying atom shape */
    @JsonSerialize(using = BodySerializer.class)
    private Body body;

    /**
     * Constructor
     * @param body the body of the atom.
     */
    public BasicAtom(final Body body) {
        this.id = UUID.randomUUID().toString();
        this.body = body;
    }

    /**
     * Constructor that uses a single shape.
     * @param convexShape a single shape.
     */

    public BasicAtom(final Convex convexShape) {
        this.id = UUID.randomUUID().toString();
        this.body = new Body();
        this.body.addFixture(convexShape);
    }

    /**
     * Returns the atom identifier.
     * @param id the atom identifier.
     */

    public BasicAtom(String id) {
        this.id = id;
    }

    /**
     * Returns the identifier for the atom.
     * @return the identifier for the atom.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns the body for the atom.
     * @return the body for the atom.
     */
    public Body getBody() {
        return body;
    }

    /**
     * Translates the shape from one position to another.
     * @param dx the x-vector velocity
     * @param dy the y-vector velocity
     * @return
     */
    public BasicAtom translate(final double dx,
                               final double dy) {
        body.translate(dx, dy);
        return this;
    }
}
