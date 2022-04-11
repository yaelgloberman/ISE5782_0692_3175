package renderer;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * class rayTracerBasic that extends from rayTracer
 */
public class RayTracerBasic extends RayTracer {
    private GeoPoint closestPoint;
private boolean _bb;//bounding box
    /**
     * constructor for ray tracer
     *
     * @param scene to be intersected
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
        GeoPoint closestPoint= findClosestGeoIntersection(ray);
        if(closestPoint==null){
            return scene.getBackground();
        }
        return calcColor(closestPoint,ray);
    }
    private GeoPoint findClosestGeoIntersection(Ray ray)
    {
        List <Point> intersections ;
        if(!_bb) {
            intersections=scene.getGeometries().findIntersections(ray);
        }else {
            intersections = scene.getGeometries().findIntersections(ray);
        }
        if (intersections ==null || intersections.size()==0) {
            return null;
        }else {
            return ray.findClosestPoint(intersections);
        }
    }
    /**
     * calculate the color of the point
     * @param geoPoint the point on the 3D model
     * @return the color in the point
     */
    private Color calcColor(GeoPoint geoPoint,Ray ray) {
        Color result= scene.getAmbientLight().getIntensity();
        result =result.add(geoPoint.geometry.getEmission());
        return result;

    }
}
