package primitives;

import java.util.Objects;

public class Point {
     final Double3 _xyz;

    /**
     * constructor that initilize the xyz- get it ready
     * @param xyz
     */
    public Point(Double3 xyz) {
        _xyz = xyz;
    }

    /**
     * constructor that get 3 doubles and initilize new Double3
     * @param x
     * @param y
     * @param z
     */
    public Point(double x, double y, double z) {
        _xyz=new Double3(x,y,z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return _xyz.equals( point._xyz);
    }

    @Override
    public String toString() {
        return "Point " + _xyz ;
    }

    /**
     * Vector subtraction
     * @param pt2 point
     * @return new vector between the two points
     */
    public Vector subtract(Point pt2) {
        if (pt2.equals(this)) {
            throw new IllegalArgumentException("cannot create Vector to Point(0,0,0)");
        }
        return new Vector(this._xyz.subtract(pt2._xyz));
    }

    /**
     * add the vector to the point
     * @param v vector
     * @return new point
     */
    public Point add(Vector v) {
        return new Point(this._xyz.add(v._xyz));
    }

    /**
     * @param other
     * @return (x2 - x1)^2 + (y2-y1)^2 + (z2-z1)^2
     */
    public double distanceSquared(Point other) {
        final double x1 = _xyz._d1;
        final double y1 = _xyz._d2;
        final double z1 = _xyz._d3;

        final double x2 = other._xyz._d1;
        final double y2 = other._xyz._d2;
        final double z2 = other._xyz._d3;

        return ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)) + ((z2 - z1) * (z2 - z1));
    }

    /**
     * @param other
     * @return euclidean distance between 2  3D points using the Pythagorean theorem
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

}
