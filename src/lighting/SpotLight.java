package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight{
    private Vector direction;

    /**
     * constructor
     * @param intensity the color
     * @param position the position
     * @param direction the direction
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * constructor
     * @param intensity
     * @param direction
     */
    public SpotLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * constructor
     * @param intensity
     */
    protected SpotLight(Color intensity) {
        super(intensity);
    }

    /**
     * calculate the color
     * @param p point
     * @return
     */
    public Color getIntensity(Point p)
    {
        Color pointIntensity = super.getIntensity(p);
        double factor = Math.max(0, direction.dotProduct(getL(p)));
        return pointIntensity.scale(factor);
    }
}
