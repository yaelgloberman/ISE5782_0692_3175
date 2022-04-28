package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

    private Point position;
    private double KC = 1;
    private double KL = 0;
    private double KQ = 0;


    public PointLight(Color intensity, Point _position) {
        super(intensity);
        position = _position;
    }
    public PointLight setKC(double KC) {
        this.KC = KC;
        return this;
    }

    public PointLight setKL(double KL) {
        this.KL = KL;
        return this;
    }

    public PointLight setKQ(double KQ) {
        this.KQ = KQ;
        return this;
    }

    protected PointLight(Color intensity) {
        super(intensity);
    }

    @Override
    public Color getIntensity(Point p) {
        Color I0 = getIntensity();
        double d = p.distance(position);
        double d2 = p.distanceSquared(position);

        return I0.reduce(KC + KL * d + KQ * d2);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

}
