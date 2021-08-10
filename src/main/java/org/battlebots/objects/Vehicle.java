package org.battlebots.objects;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.*;

public class Vehicle extends MovingAtom {
    public static final String CLASSIFICATION_PLAYER = "player";
    public static final String CLASSIFICATION_BOT = "bot";
    
    private String classification;

    /**
     * Constructs your basic vehicle.
     */
    public Vehicle(final Body body) {
        super(body);
    }

    /**
     * Constructs a vehicle at a location.
     * @param x x-vector of the location.
     * @param y y-vector of the location.
     */
    public Vehicle(final Body body,
                   final double x,
                   final double y) {
        super(body);
        translate(x, y);
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public static Body createStandardPlayerBody() {
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
                new Vector2(30,10),
                new Vector2(40, 15),
                new Vector2(30,20),
                new Vector2(0, 20)));
        return body;
    }

    public static Body createStandardEnemyBody() {
        Body body = new Body();
        body.addFixture(Geometry.createPolygon(
                new Vector2(0, 7),
                new Vector2(7, 0),
                new Vector2(17, 0),
                new Vector2(24, 7),
                new Vector2(24, 17),
                new Vector2(17, 24),
                new Vector2(7, 24),
                new Vector2(0, 17)
        ));

        Circle innerCircle = Geometry.createCircle(7);
        innerCircle.translate(12, 12);

        body.addFixture(innerCircle);
        return body;
    }

    public static Vehicle createPlayerVehicle(final double x, final double y) {
        Vehicle vehicle = new Vehicle(createStandardPlayerBody());
        vehicle.setClassification(CLASSIFICATION_PLAYER);
        vehicle.translate(x, y);
        return vehicle;
    }

    public static Vehicle createBotVehicle(final double x, final double y) {
        Vehicle vehicle = new Vehicle(createStandardEnemyBody());
        vehicle.setClassification(CLASSIFICATION_BOT);
        vehicle.translate(x, y);
        return vehicle;
    }
}
