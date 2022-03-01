package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
