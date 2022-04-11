package primitives;

import geometries.Intersectable;

public class Point {
    public static final Point ZERO = new Point(0d,0d,0d);

    final Double3 xyz;

    /**
     * constructor that initialize the xyz- get it ready
     * @param xyz point xyz
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * constructor that get 3 doubles and initialize new Double3
     * @param x value of X axis
     * @param y value of y axis
     * @param z value of z axis
     */
    public Point(double x, double y, double z) {
        xyz =new Double3(x,y,z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals( point.xyz);
    }

    @Override
    public String toString() {
        return "Point " + xyz;
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
        return new Vector(this.xyz.subtract(pt2.xyz));
    }

    /**
     * add the vector to the point
     * @param v vector
     * @return new point
     */
    public Point add(Vector v) {
        return new Point(this.xyz.add(v.xyz));
    }

    /**
     * @param other point
     * @return (x2 - x1)^2 + (y2-y1)^2 + (z2-z1)^2
     */
    public double distanceSquared(Point other) {
        final double x1 = xyz.d1;
        final double y1 = xyz.d2;
        final double z1 = xyz.d3;

        final double x2 = other.xyz.d1;
        final double y2 = other.xyz.d2;
        final double z2 = other.xyz.d3;

        return ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)) + ((z2 - z1) * (z2 - z1));
    }

    /**
     * @param other point
     * @return euclidean distance between 2  3D points using the Pythagorean theorem
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

    /**
     *
     * @return the value of x-axis
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     *
     * @return the value of y-axis
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     *
     * @return the value of x-axis
     */
    public double getZ() {
        return xyz.d3;
    }

}
