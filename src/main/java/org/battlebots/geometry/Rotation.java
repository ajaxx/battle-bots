package org.battlebots.geometry;

public class Rotation {
    private double angle;
    private double cx;
    private double cy;

    public Rotation() {
    }

    public Rotation(double angle, double cx, double cy) {
        this.angle = angle;
        this.cx = cx;
        this.cy = cy;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getCx() {
        return cx;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }

    public double getCy() {
        return cy;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }
}
