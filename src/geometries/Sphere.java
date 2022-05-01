package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

public class Sphere extends Geometry{

    final private Point _center;
    final private double _radius;

    public Sphere(Point _center, double _radius) {
        this._center = _center;
        this._radius = _radius;
    }
    /**
     * constructor to the Sphere
     * @param _center center of the sphere
     * @param _radius radius of the sphere
     */
    public Sphere(Color emission, Point _center, double _radius) {
        super(emission);
        this._center = _center;
        this._radius = _radius;
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
    /**
     * the func find the intersections of the ray with the sphere
     * @param ray ray pointing towards the graphic object
     * @return list of intersection points
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        Point P0 = ray.getP0();
        Vector v = ray.getDirection();

        if (P0.equals(_center)) {
            if(alignZero(_radius-maxDistance)>0){
                return null;
            }
            return List.of(new GeoPoint(this,_center.add(v.scale(_radius))));
        }

        Vector U = _center.subtract(P0);

        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        // no intersections : the ray direction is above the sphere
        if (d >= _radius) {
            return null;
        }

        double th = alignZero(Math.sqrt(_radius * _radius - d * d));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        if (t1 > 0 && t2 > 0) {
            Point P1 =ray.getPoint(t1);
            Point P2 =ray.getPoint(t2);
            return List.of(new GeoPoint(this,P1), new GeoPoint(this,P2));
        }
        if (t1 > 0) {
           //Point P1 = P0.add(v.scale(t1));
            Point P1 =ray.getPoint(t1);
            return List.of(new GeoPoint(this,P1));
        }
        if (t2 > 0) {
           // Point P2 = P0.add(v.scale(t2));
            Point P2 =ray.getPoint(t2);
            return List.of(new GeoPoint(this,P2));
        }
        return null;
    }
}
