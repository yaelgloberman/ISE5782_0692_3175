package lighting;

import org.w3c.dom.DOMImplementation;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionLight extends Light implements LightSource{

    private Vector direction;

    protected DirectionLight(Color intensity) {
        super(intensity);
    }

    public DirectionLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point p) {
        return Double.POSITIVE_INFINITY;
    }
}
