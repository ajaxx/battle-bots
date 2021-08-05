package org.battlebots.objects;

import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

public class Wall extends BasicAtom {
    public static Polygon createBasicPolygon(final double width,
                                             final double height) {
        return Geometry.createPolygon(
                new Vector2(0, 0),
                new Vector2(width, 0),
                new Vector2(width, height),
                new Vector2(0, height)
        );
    }

    /**
     * Constructor for a wall.
     * @param width the wall width.
     * @param height the wall height.
     */
    public Wall(final double width, final double height) {
        super(createBasicPolygon(width, height));
    }

    public Wall(final double x,
                final double y,
                final double width,
                final double height) {
        super(createBasicPolygon(width, height));
        translate(x, y);
    }
}
