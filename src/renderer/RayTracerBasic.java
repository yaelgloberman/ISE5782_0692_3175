package renderer;

import geometries.FlatGeometry;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * class rayTracerBasic that extends from rayTracer
 * thanks to Shaili and Rivki about there help with this class
 */
public class RayTracerBasic extends RayTracer {
    private GeoPoint closestPoint;
    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10; //to stop the recursive function
    private static final double MIN_CALC_COLOR_K = 0.001; //to stop the recursive function


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
     *
     * @param ray
     * @return the color of the closest point to the intersection point
     */
    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.getGeometries().findGeoIntersections(ray);
        // if ray didn't intersect any geometrical object - the background color of the scene will be returned:
        if (intersections == null)
            return scene.getBackground();
        //The closest point to the beginning of the ray will be found using the new method we created: "findClosestGeoPoint".
        GeoPoint closestPoint = findClosestIntersection(ray);
        return calcColor(closestPoint, ray);
    }

    /**
     * calculate the color of the pixel
     * @param rays list of rays
     *  MP8
     * @return the color
     */
    public Color traceRay(List<Ray> rays)
    {
        Color finalColor=null;
        Color firstColor=null;
        Color colorTmp=new Color(0,0,0);
        for(var ray:rays)
        {
            List<GeoPoint> intersection = scene.getGeometries().findGeoIntersections(ray);
            if (intersection == null)
            {
                return scene.getBackground();
            }
            GeoPoint closestPoint = ray.findClosestGeoPoint(intersection);

            colorTmp=calcColor(closestPoint, ray) == null ? scene.getBackground() : calcColor(closestPoint, ray);
            if(finalColor==null)
            {
                firstColor=colorTmp;
                finalColor=new Color(0,0,0);
                for (int i = 0; i < 10; i++)
                    finalColor=finalColor.add(colorTmp);
            }

            if(!colorTmp.equals(firstColor))
                finalColor=finalColor.add(colorTmp);

        }
        if(finalColor.equals(firstColor))
            return firstColor;
        int size=rays.size()+10;
        return finalColor.reduce(size);
    }

    /**
     * build reflected ray
     *
     * @param p
     * @param v
     * @param n
     * @return
     */
    private Ray constructReflectedRay(Point p, Vector v, Vector n) {
        double vn = v.dotProduct(n);
        if (Util.isZero(vn)) {
            return null;
        }
        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(p, n, r);
    }


    /**
     * build refracted ray
     *
     * @param p
     * @param v
     * @param n
     * @return
     */
    private Ray constructRefractedRay(Point p, Vector v, Vector n) {
        return new Ray(p, n, v);
    }
    /**
     * calculate the local effects of the scene
     *
     * @param gp geo point intersection with the ray
     * @param ray
     * @param k   max level of color
     * @return the color of the local effect
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Color color = gp.geometry.getEmission();
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color.BLACK;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(lightSource, l, n, gp, nv);
                if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(
                            iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    /**
     * calc the diffusive
     * @param material
     * @param nl
     * @return
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.KD.scale(Math.abs(nl));
    }

    /**
     * calc the specular
     * @param material
     * @param n
     * @param l
     * @param nl
     * @param v
     * @return
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalize();
        return material.KS.scale(Math.pow(Math.max(0, r.dotProduct(v.scale(-1d))), material.nShininess));

    }

    /**
     * find the closest intersection point with the ray
     * @param ray
     * @return the closest point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null || intersections.size() == 0) {
            return null;
        } else {
            return ray.findClosestGeoPoint(intersections);
        }
    }

    /**
     * calculate the color
     * @param gp the geometry
     * @param ray from the camera
     * @return
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, new Double3(1.0)).add(scene.getAmbientLight().getIntensity());
    }
    /**
     * calculate the color of the scene
     *
     * @param intersection geo point intersections with the ray
     * @param ray
     * @param level        of the recursion
     * @param k            factor of the max value of the level
     * @return color of the geometry
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDirection(), level, k));
    }
    /**
     * Calculates the reflection and the refraction
     * at a given intersection point.
     *
     * @param gp    the intersection point
     * @param level the number of the recursive calls
     *              to calculate the next reflections and
     *              refractions
     * @param k     the effect's strength by the reflection and refraction
     * @return the color on the intersection point
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        Double3 kkr = material.KR.product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.KR, kkr);
        Double3 kkt = material.KT.product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.KT, kkt));
        return color;
    }

    /**
     * calc the global effects- reflection and refraction.
     * this func call "calcColor" in recursion to add more and more global effects.
     *
     * @param ray    the ray from the viewer
     * @param level of recursion- goes down each time till it gets to 1
     * @param kx     kR or kT factor
     * @param kkx    kR or kT factor multiplied by k - factor of reflection and refraction
     * @return the color of the effect
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.getBackground() : calcColor(gp, ray, level - 1, kkx)
        ).scale(kx);
    }


    /**
     * the func calculate the transparency and return it
     *
     * @param light    lightSource
     * @param l        from the light source to the point
     * @param n        normal
     * @param geopoint the point
     * @param nv
     * @return the transparency
     */
    private Double3 transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint, double nv) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay;

        if (nv < 0) {
            lightRay = new Ray(geopoint.point, n, lightDirection);
        } else
            lightRay = new Ray(geopoint.point, n.scale(-1), lightDirection);

        double lightDistance = light.getDistance(geopoint.point);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay, lightDistance);
        if (intersections == null) return Double3.ONE;

        Double3 ktr = Double3.ONE;//transparency initial

        for (GeoPoint gp : intersections) //move on all the geometries in the way
        {
            //if there are geometries between the point to the light- calculate the transparency
            //in order to know how much shadow there is on the point
            if (Util.alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0
                    && gp.geometry.getMaterial().KT != Double3.ZERO)//only objects without a transparency factor will cause shading
            {
                ktr = ktr.product(gp.geometry.getMaterial().KT);//add this transparency to ktr
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) //stop the loop- shadow "atum", black. very small transparency
                    return Double3.ZERO;
            }
        }
        return ktr;
    }


    /**
     * Checks the color of the pixel with the help of individual rays and averages between
     * them and only if necessary continues to send beams of rays in recursion
     * @param centerP center pixl
     * @param Width Length
     * @param Height width
     * @param minWidth min Width
     * @param minHeight min Height
     * @param cameraLoc Camera location
     * @param Vright Vector right
     * @param Vup vector up
     * @param prePoints pre Points
     * @return Pixel color
     * thanks to Rivki
     */
    @Override
    public Color AdaptiveSuperSamplingRec(Point centerP, double Width, double Height, double minWidth, double minHeight, Point cameraLoc, Vector Vright, Vector Vup, List<Point> prePoints) {
        if (Width < minWidth * 2 || Height < minHeight * 2) {
            return this.traceRay(new Ray(cameraLoc, centerP.subtract(cameraLoc))) ;
        }

        List<Point> nextCenterPList = new LinkedList<>();
        List<Point> cornersList = new LinkedList<>();
        List<primitives.Color> colorList = new LinkedList<>();
        Point tempCorner;
        Ray tempRay;
        for (int i = -1; i <= 1; i += 2){
            for (int j = -1; j <= 1; j += 2) {
                tempCorner = centerP.add(Vright.scale(i * Width / 2)).add(Vup.scale(j * Height / 2));
                cornersList.add(tempCorner);
                if (prePoints == null || !isInList(prePoints, tempCorner)) {
                    tempRay = new Ray(cameraLoc, tempCorner.subtract(cameraLoc));
                    nextCenterPList.add(centerP.add(Vright.scale(i * Width / 4)).add(Vup.scale(j * Height / 4)));
                    colorList.add(traceRay(tempRay));
                }
            }
        }


        if (nextCenterPList == null || nextCenterPList.size() == 0) {
            return primitives.Color.BLACK;
        }

        Color tempColor = primitives.Color.BLACK;
        for (Point center : nextCenterPList) {
            tempColor = tempColor.add(AdaptiveSuperSamplingRec(center, Width/2,  Height/2,  minWidth,  minHeight ,  cameraLoc, Vright, Vup, cornersList));
        }
        return tempColor.reduce(nextCenterPList.size());


    }

    /**
     * Find a point in the list
     * @param pointsList the list
     * @param point the point that we look for
     * @return true if the point in the list
     */
    private boolean isInList(List<Point> pointsList, Point point) {
        for (Point tempPoint : pointsList) {
            if(point.equals(tempPoint))
                return true;
        }
        return false;
    }


}
