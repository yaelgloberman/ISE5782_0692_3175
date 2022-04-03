package primitives;

import java.util.List;

import static primitives.Util.isZero;

public class Ray {
    final private Point p0;
    final private Vector dir;

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

    public Point getP0() {
        return p0;
    }

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

   public Point findClosestPoint(List<Point> pointList) {
        double minDistance = Double.MAX_VALUE;
        double pointDistance;

        if(pointList== null){
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
}