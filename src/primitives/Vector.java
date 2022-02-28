package primitives;

/**
 * Vector in 3D
 */
public class Vector extends Point {
    /**
     * TODO
     *
     * @param xyz
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (_xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector(0,0,0) is not valid in this project");
        }
    }

    /**
     * secondary Constructor for the Vector
     *
     * @param x value for x axis
     * @param y value for y axis
     * @param z value for z axis
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
    }

    @Override
    public String toString() {
        return "Vector " + _xyz;
    }

    /**
     * @return euclidean length squared of the vector
     */
    public double lengthSquared() {
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        return u1 * u1 + u2 * u2 + u3 * u3;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double dotProduct(Vector other) {
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;

        double v1 = other._xyz._d1;
        double v2 = other._xyz._d2;
        double v3 = other._xyz._d3;

        return (u1 * v1 + u2 * v2 + u3 * v3);
    }
}
