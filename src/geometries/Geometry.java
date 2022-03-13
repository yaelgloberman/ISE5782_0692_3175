package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * interface for all geometries
 */
public interface Geometry extends Intersectable{
    /**
     * returning the normal vector from the shape
     * @param point {@link Point} external to the shape
     * @return the normal vector{@link Vector} of the geometry
     */
    Vector getNormal(Point point);
}
