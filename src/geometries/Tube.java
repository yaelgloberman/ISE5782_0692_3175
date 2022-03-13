package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * the class tube implements Geometry
 */
public class Tube implements Geometry{
    final Ray _axisRay;
    final double _radius;

    /**
     * constructor to the Tube
     * @param _axisRay
     * @param _radius
     */
    public Tube(Ray _axisRay, double _radius) {
        this._axisRay = _axisRay;
        this._radius = _radius;
    }

    public Ray get_axisRay() {
        return _axisRay;
    }

    public double get_radius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Tube:" +
                "_axisRay=" + _axisRay +
                ", _radius=" + _radius ;
    }

    /**
     * get normal tube
     * @param point {@link Point} external to the shape
     * @return the normal of the tube
     */
    @Override
    public Vector getNormal(Point point) {

        Point P0 = _axisRay.getP0();
        Vector v = _axisRay.getDirection();

        Vector P0_P = point.subtract(P0);    //gets the vector between p and p0

        double t = alignZero(v.dotProduct(P0_P));

        if (isZero(t)) {                     //if the dot product of the vector v and p0_p is close to 0 return normalise vector
            return P0_P.normalize();
        }

        Point o = P0.add(v.scale(t));

        if (point.equals(o)) {
            throw new IllegalArgumentException("point cannot be on the tube axis");
        }

        Vector n = point.subtract(o).normalize();

        return n;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
