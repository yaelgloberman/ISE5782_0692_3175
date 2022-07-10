package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class point light
 */
public class PointLight extends Light implements LightSource {

    private Point position; //the position of the light
    private double KC = 1;
    private double KL = 0;
    private double KQ = 0;

    /**
     * constructor
     * @param intensity the color of the light
     * @param _position the position of the light
     */
    public PointLight(Color intensity, Point _position) {
        super(intensity);
        position = _position;
    }

    /**
     * setter
     * @param KC
     * @return
     */
    public PointLight setKC(double KC) {
        this.KC = KC;
        return this;
    }

    /**
     * setter
     * @param KL
     * @return
     */
    public PointLight setKL(double KL) {
        this.KL = KL;
        return this;
    }

    /**
     * setter
     * @param KQ
     * @return
     */
    public PointLight setKQ(double KQ) {
        this.KQ = KQ;
        return this;
    }

    /**
     * constructor
     * @param intensity the color of the light
     */
    protected PointLight(Color intensity) {
        super(intensity);
    }

    /**
     * calculate a new color
     * @param p point
     * @return the new color
     */
    @Override
    public Color getIntensity(Point p) {
        Color I0 = getIntensity();
        double d = p.distance(position);
        double d2 = p.distanceSquared(position);

        return I0.reduce(KC + KL * d + KQ * d2);
    }

    /**
     * getter
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    /**
     * getter
     * @param p
     * @return
     */
    @Override
    public double getDistance(Point p) {
        return p.distance(this.position);
    }

}
