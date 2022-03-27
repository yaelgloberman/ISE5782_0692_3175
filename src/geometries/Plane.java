package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane implements Geometry {
    final private Point _q0;
    final private Vector _normal;

    /**
     * Constructor of Plane from 3 points on its surface
     * the points are ordered from right to left
     * forming an arc in right direction
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        _q0 = p1;
        //TODO check direction of vectors
//        Vector U = p1.subtract(p2);
//        Vector V = p3.subtract(p2);

        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);

        Vector N = U.crossProduct(V);


        //right hand rule
        _normal = N.normalize();

    }

    /**
     * constructor of plane from a point and normal
     *
     * @param p0
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
     * @return referenced point of the palne
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

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0=ray.getP0();
        Vector v=ray.getDirection();
        Vector n=_normal;
        double nv=n.dotProduct(v);
        if(isZero(nv)){
            return null;
        }
        Vector P0_Q=p0.subtract(_q0);

        double t=alignZero( n.dotProduct(P0_Q)/nv);
        //if t<0 the ray point to the opposite direction
        //if t==0 the ray origin lay with the plane
        if(t>0){
            Point p=p0.add(v.scale(t));
            return List.of(p);
        }
        return null;
    }
}
