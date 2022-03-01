package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
    final private Point _q0;
    final private Vector _normal;

    /**
     * Constructor of Plane from 3 points on its surface
     * the points are ordered from right to left
     * forming an arc in right direction
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        _q0 = p1;
        _normal = null;
    }

    /**
     * constructor of plane from a point and normal
     * @param p0
     * @param normal vector for the normal (will bwe normalized automatically)
     */
    public Plane(Point p0, Vector normal) {
        _q0 = p0;
        _normal = normal.normalize();
    }


    /**
     * getNormal implementation of Geometry interface
     * @param point dummy point not use for flat geometries
     *              should be assigned null value
     * @return normal to the plane
     */
    @Override
    public Vector getNormal(Point point) {
        return _normal;
    }

    public Point get_q0() {
        return _q0;
    }

    @Override
    public String toString() {
        return "Plane:" +
                "_q0=" + _q0 +
                ", _normal=" + _normal;
    }

    public Vector getNormal() {
        return null;
    }
}
