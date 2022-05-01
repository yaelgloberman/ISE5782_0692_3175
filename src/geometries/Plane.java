package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane extends Geometry implements FlatGeometry {
    final private Point _q0;
    final private Vector _normal;

    /**
     * Constructor of Plane from 3 points on its surface
     * the points are ordered from right to left
     * forming an arc in right direction
     *
     * @param p1 point p1
     * @param p2 point p2
     * @param p3 point p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        _q0 = p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);
        Vector N = U.crossProduct(V);
        //right-hand rule
        _normal = N.normalize();

    }

    /**
     * constructor of plane from a point and normal
     *
     * @param p0 point op0 the middle of the camera
     * @param normal vector for the normal (will bwe normalized automatically)
     */
    public Plane(Point p0, Vector normal) {
        _q0 = p0;
        _normal = normal.normalize();
    }


    /**
     * getter for _normal
     *
     * @return vector normal to the plane
     */
    public Vector getNormal() {
        return _normal;
    }

    /**
     * getter for _q0
     *
     * @return referenced point of the plane
     */
    public Point get_q0() {
        return _q0;
    }

    @Override
    public String toString() {
        return "Plane:" +
                "_q0=" + _q0 +
                ", _normal=" + _normal;
    }

    /**
     * getNormal implementation of Geometry interface
     *
     * @param point dummy point not use for flat geometries
     *              should be assigned null value
     * @return normal to the plane
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    /**
     * the func find the intersections of the ray with the plane
     * @param ray ray pointing towards the graphic object
     * @return list of intersection points
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        Point P0 = ray.getP0();
        Vector v = ray.getDirection();
        Vector n = _normal;

        if (_q0.equals(P0)) {
            return null;
        }

        //denominator
        double nv = alignZero(n.dotProduct(v));

        // ray is lying in the plane axis
        if (isZero(nv)) {
            return null;
        }

        Vector P0_Q0 = _q0.subtract(P0);

        //numerator
        double nP0Q0 = alignZero(n.dotProduct(P0_Q0));

        // ray parallel to plane
        if (isZero(nP0Q0)) {
            return null;
        }


        double t = alignZero(nP0Q0 / nv);

        if(alignZero(t-maxDistance)>0){
            return null;
        }

        //ray is opposite to the direction
        if (t < 0) {
            return null;
        }
        GeoPoint geopoint =new GeoPoint(this,ray.getPoint(t));
        return List.of(geopoint);
    }
}
