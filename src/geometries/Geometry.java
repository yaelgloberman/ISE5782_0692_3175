package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * interface for all geometries
 */
public abstract class Geometry extends Intersectable{
    private Color emission; // the emission color of the geometry
    private Material material=new Material();

    /**
     * constructor
     * @param emission
     */
    protected Geometry(Color emission) {

        this.emission = emission;
    }

    /**
     * constructor
     */
    protected Geometry() {

        this.emission = Color.BLACK;
    }

    /**
     * getter
     * @return the material
     */
    public Material getMaterial() {

        return material;
    }

    /**
     * setter
     * @param material the new material
     * @return
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    /**
     * getter
     * @return the emission light
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * setter
     * @param emission the new emission
     * @return
     */
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
