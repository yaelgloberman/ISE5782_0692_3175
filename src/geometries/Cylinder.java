package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube{
    final private double _height;

    /**
     * constructor that uses the constructor of the Tube
     * @param _axisRay
     * @param _radius
     * @param _height
     */
    public Cylinder(Ray _axisRay, double _radius, double _height) {
        super(_axisRay, _radius);
        this._height = _height;
    }

    public double get_height() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder:" +
                "_height=" + _height +
                ", _axisRay=" + _axisRay +
                ", _radius=" + _radius ;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}