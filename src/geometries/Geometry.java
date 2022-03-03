package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * interface for all geometries
 */
public interface Geometry {
    /**
     *
     * @param point
     * @return the normal of the geometry
     */
    Vector getNormal(Point point);
}
