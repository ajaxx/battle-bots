package org.battlebots.objects;

import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Geometry;

public class Mine extends BasicAtom {
    public Mine() {
        super(Geometry.createCircle(10));
    }

    public Mine(int x, int y) {
        super(Geometry.createCircle(10));
        translate(x, y);
    }
}
