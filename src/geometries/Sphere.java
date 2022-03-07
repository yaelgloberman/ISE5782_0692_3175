package geometries;

import primitives.Double3;
import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry{

    final private Point _center;
    final private double _radius;

    /**
     * constructor to the Sphere
     * @param center
     * @param radius
     */
    public Sphere(Point center, double radius) {
        _center = center;
        _radius = radius;

    }

    public Point get_center() {
        return _center;
    }

    public double get_radius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Sphere:" +
                "_center=" + _center +
                ", _radius=" + _radius ;
    }

    @Override
    public Vector getNormal(Point point) {
        Vector N=point.subtract(_center);
        return N.normalize();
    }
}
