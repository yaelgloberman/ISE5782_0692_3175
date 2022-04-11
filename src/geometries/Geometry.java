package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * interface for all geometries
 */
public abstract class Geometry extends Intersectable{
    private Color emission;

    protected Geometry(Color emission) {
        this.emission = emission;
    }
    protected Geometry() {
        this.emission = Color.BLACK;
    }

    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * returning the normal vector from the shape
     * @param point {@link Point} external to the shape
     * @return the normal vector{@link Vector} of the geometry
     */
    public abstract Vector getNormal(Point point);
}
