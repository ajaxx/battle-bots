package org.battlebots.objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.battlebots.util.BodySerializer;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.*;

import java.util.UUID;

public class BasicAtom implements Atom {
    /** The identifier for the atom */
    private String id;
    /** Underlying atom shape */
    @JsonSerialize(using = BodySerializer.class)
    private Body body;

    @JsonIgnore
    private double bboxX1;
    @JsonIgnore
    private double bboxX2;
    @JsonIgnore
    private double bboxY1;
    @JsonIgnore
    private double bboxY2;

    /**
     * Constructor
     * @param body the body of the atom.
     */
    public BasicAtom(final Body body) {
        this.id = UUID.randomUUID().toString();
        this.body = body;
        this.detectBoundingBox();
    }

    /**
     * Constructor that uses a single shape.
     * @param convexShape a single shape.
     */

    public BasicAtom(final Convex convexShape) {
        this.id = UUID.randomUUID().toString();
        this.body = new Body();
        this.body.addFixture(convexShape);
        this.detectBoundingBox();
    }

    private void detectBoundingBox() {
        for (BodyFixture fixture : body.getFixtures()) {
            Transform transform = new Transform();
            Convex shape = fixture.getShape();

            Vector2 minx1 = shape.getFarthestPoint(Vector2.create(-1.0, 0), transform);
            Vector2 minx2 = shape.getFarthestPoint(Vector2.create(1.0, 0), transform);
            Vector2 miny1 = shape.getFarthestPoint(Vector2.create(0.0, -1.0), transform);
            Vector2 miny2 = shape.getFarthestPoint(Vector2.create(0.0, 1.0), transform);

            if (minx1.x < bboxX1) {
                bboxX1 = minx1.x;
            }
            if (minx2.x > bboxX2) {
                bboxX2 = minx2.x;
            }
            if (miny1.y > bboxY1) {
                bboxY1 = miny1.y;
            }
            if (miny2.y > bboxY2) {
                bboxY2 = miny2.y;
            }
        }
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
     * @return the atom
     */
    public BasicAtom translate(final double dx,
                               final double dy) {
        body.translate(dx, dy);
        return this;
    }

    /**
     * Rotate the body.
     * @param theta the angle of rotation
     * @return the atom
     */
    public BasicAtom rotate(final double theta) {
        body.rotateAboutCenter(theta);
        return this;
    }

    @JsonIgnore
    public Vector2 getBoundingBoxCenter() {
        return new Vector2((bboxX1 + bboxX2)/2, (bboxY1 + bboxY2) / 2);
    }
}
