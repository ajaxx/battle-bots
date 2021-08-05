package org.battlebots.objects;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.*;

public class Vehicle extends MovingAtom {
    public static Body createBasicBody() {
        Body body = new Body();
        body.addFixture(Geometry.createPolygon(
                new Vector2(0, 10),
                new Vector2(20, 0),
                new Vector2(15, 10)));
        body.addFixture(Geometry.createPolygon(
                new Vector2(0, 20),
                new Vector2(15, 20),
                new Vector2(20, 30)));
        body.addFixture(Geometry.createPolygon(
                new Vector2(0,10),
                new Vector2(40,10),
                new Vector2(50, 15),
                new Vector2(40,20),
                new Vector2(0, 20)));
        return body;
    }

    /**
     * Constructs your basic vehicle.
     */
    public Vehicle() {
        super(createBasicBody());
    }

    /**
     * Constructs a vehicle at a location.
     * @param x x-vector of the location.
     * @param y y-vector of the location.
     */
    public Vehicle(final double x, final double y) {
        super(createBasicBody());
        translate(x, y);
    }
}
