package primitives;

/**
 * Vector in 3D
 */
public class Vector extends Point {
    /**
     * first constructor - get the xyz
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

    /**
     *
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }
    /**
     * dot product between two vectors (scalar product)
     *
     * @param other the right vector of U.V
     * @return scalre value of dot product
     * @link https://en.wikipedia.org/wiki/Dot_product
     */
    public double dotProduct(Vector other) {
        Double3 temp= this._xyz.product(other._xyz);
        return temp._d1+temp._d2+temp._d3;
    }

    /**
     * normalizing the current Vector
     *
     * @return new Vector normalized
     */
    public Vector normalize() {
        double length = this.length();

//      the following check is not necessary because there
//      cannot be a ZERO vector
//        //cannot divide by 0
        if (length == 0)
            throw new ArithmeticException("divide by Zero");
        return new Vector(new Double3(this._xyz._d1/length,this._xyz._d2/length,this._xyz._d3/length));
    }
    /**
     * creating a new Vector corresponding to the actual one
     * scaled by scaling factor
     *
     * @param scalar scaling factot
     */
    public Vector scale(double scalar) {
        if (Util.isZero(scalar)) {
            throw new IllegalArgumentException("scaling factor == 0");
        }
        return new Vector(this._xyz.scale(scalar));
    }
    /**
     * @param other Vector
     * @return new Vector (u+v)
     */
    public Vector add(Vector other) {
        return new Vector(this._xyz.add(other._xyz));
    }
    /**
     * Cross product (vectorial product)
     *
     * @param other second vector
     * @return new Vector resulting from cross product
     * @link https://en.wikipedia.org/wiki/Cross_product
     */
    public Vector crossProduct(Vector other) {
        double u1 = _xyz._d1;
        double u2 = _xyz._d2;
        double u3 = _xyz._d3;
        double v1 = other._xyz._d1;
        double v2 = other._xyz._d2;
        double v3 = other._xyz._d3;

        return new Vector(new Double3(
                u2 * v3 - u3 * v2,
                u3 * v1 - u1 * v3,
                u1 * v2 - u2 * v1
        ));
    }

}
