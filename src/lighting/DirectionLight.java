package lighting;

import org.w3c.dom.DOMImplementation;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class direction light
 */
public class DirectionLight extends Light implements LightSource{

    private Vector direction; //the direction of the light

    /**
     * constructor
     * @param intensity the color
     */
    protected DirectionLight(Color intensity) {
        super(intensity);
    }

    /**
     * constructor with 2 parameters
     * @param intensity the color
     * @param direction the direction
     */
    public DirectionLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * getter
     * @param p
     * @return
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    /**
     * getter
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point p) {
        return direction;
    }

    /**
     * getter
     * @param p
     * @return
     */
    @Override
    public double getDistance(Point p) {
        return Double.POSITIVE_INFINITY;
    }
}
