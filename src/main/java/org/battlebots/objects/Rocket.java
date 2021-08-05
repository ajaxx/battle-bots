package org.battlebots.objects;

import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Vector2;

public class Rocket extends MovingAtom {
    public static Polygon createBasicPolygon() {
        return Geometry.createPolygon(
                new Vector2(0, 0),
                new Vector2(15, 0),
                new Vector2(20, 5),
                new Vector2(15, 10),
                new Vector2(0, 10)
        );
    }

    public Rocket() {
        super(createBasicPolygon());
    }

    public Rocket(double x, double y) {
        super(createBasicPolygon());
        translate(x, y);
    }
}
