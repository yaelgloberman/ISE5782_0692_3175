package renderer;

import geometries.Geometries;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

/**
 * class rayTracerBasic that extends from rayTracer
 */
public class RayTracerBasic extends RayTracer {
    /**
     * constructor
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * calculate a color of the pixel
     * @param ray
     * @return the color of the closest point to the intersection point
     */
    @Override
    public Color traceRay(Ray ray) {
        Geometries geometries = scene.getGeometries();
        List<Point> intersectionPoints =geometries.findIntersections(ray);
        Point closesPoint = ray.findClosestPoint(intersectionPoints);
        return calcColor(closesPoint);
    }

    /**
     * calculate the color of the point
     * @param point
     * @return
     */
    private Color calcColor(Point point) {
        if (point != null) {
            return scene.getAmbientLight().getIntensity();
        }
        else
            return scene.getBackground();//if the point is null its getting the color of the background
    }
}
