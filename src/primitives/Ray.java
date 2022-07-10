package primitives;


import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Ray {
    final private Point p0;
    /**
     * delta value to move the point away from original point
     */
    final private Vector dir;
    private static final double DELTA = 0.1;

    /**
     * constructor to initialize the point and the direction vector-normalized
     *
     * @param p0  the point
     * @param dir the vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();//Normalizes the direction vector
    }

    /**
     * constructor to initialize the ray
     * @param p0 the point
     * @param n vector
     * @param dir the direction
     */
    public Ray(Point p0, Vector n, Vector dir) {
        this.dir = dir.normalize();
        double nv = alignZero(n.dotProduct(this.dir));
        if (nv < 0) {
            this.p0 = p0.add(n.scale(-DELTA));
        } else {
            this.p0 = p0.add(n.scale(DELTA));
        }

    }

    /**
     * getter
     * @return the point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter
     * @return the direction
     */
    public Vector getDirection() {
        return new Vector(dir.xyz);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * @param t-scale
     * @return p=p0+t*v
     */
    public Point getPoint(double t) {
        if (isZero(t)) {
            return p0;
        }
        return p0.add(dir.scale(t));
    }

    @Override
    public String toString() {
        return "Ray" +
                "_p0" + p0 +
                ", _dir=" + dir
                ;
    }

    /**
     * The function find the closest points to P0 of the ray
     * @param pointList
     * @return Point the closes point
     */
    public Point findClosestPoint(List<Point> pointList) {
        double minDistance = Double.MAX_VALUE;
        double pointDistance;

        if (pointList == null) {
            return null;
        }

        Point closestPoint = null;
        for (Point point : pointList) {
            pointDistance = point.distanceSquared(p0);
            if (pointDistance < minDistance) {
                minDistance = pointDistance;
                closestPoint = point;
            }
        }
        return closestPoint;
    }
    /**
    * The function find the closest points to P0 of the ray
    * @param pointList
    * @return Point3D the closes point
    */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> pointList) {
        double minDistance = Double.MAX_VALUE;
        double geoPointDistance;

        if (pointList == null) {
            return null;
        }

        GeoPoint closestPoint = null;
        for (GeoPoint geopoint : pointList) {
            geoPointDistance = geopoint.point.distanceSquared(p0);
            if (geoPointDistance < minDistance) {
                minDistance = geoPointDistance;
                closestPoint = geopoint;
            }
        }
        return closestPoint;
    }


}