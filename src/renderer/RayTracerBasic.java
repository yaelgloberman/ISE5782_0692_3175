package renderer;

import geometries.FlatGeometry;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * class rayTracerBasic that extends from rayTracer
 */
public class RayTracerBasic extends RayTracer {
    private GeoPoint closestPoint;
    private static final double DELTA =0.1;

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
        List <GeoPoint> intersections ;
        if(!_bb) {
            intersections=scene.getGeometries().findGeoIntersections(ray);
        }else {
            intersections = scene.getGeometries().findGeoIntersections(ray);
        }
        if (intersections ==null || intersections.size()==0) {
            return null;
        }else {
            return ray.findClosestGeoPoint(intersections);
        }
    }
    /**
     * calculate the color of the point
     * @param geoPoint the point on the 3D model
     * @return the color in the point
     */

    private Color calcColor(GeoPoint geoPoint, Ray ray) {

        return scene.getAmbientLight().getIntensity()
                .add(calcLocalEffects(geoPoint, ray));


    }
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDirection().normalize();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(gp, lightSource,l, n, nl,nv)) {
                    Color iL = lightSource.getIntensity(gp.point);
                    color = color.add(
                            iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    private Double3 calcDiffusive(Material material, double nl) {
        return material.KD.scale(Math.abs(nl));
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalize();
        return material.KS.scale( Math.pow(Math.max(0, r.dotProduct(v.scale(-1d))), material.nShininess));

    }

    private boolean unshaded(GeoPoint gp, LightSource lightsource, Vector l, Vector n, double nl, double nv){
        Point point=gp.point;
        Vector lightDirection=l.scale(-1); //from point to light source
        Vector epsVector=n.scale(nv<0? DELTA:-DELTA);
        Point pointRay=point.add(epsVector);
        Ray lightRay=new Ray(pointRay,lightDirection);
        double maxDistance=lightsource.getDistance(point);
        List<GeoPoint> intersections =scene.getGeometries().findGeoIntersections(lightRay,maxDistance);

        return intersections==null;
    }
}
