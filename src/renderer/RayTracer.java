package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.List;

/**
 * class used to trace for the rendering engine
 */
public abstract class RayTracer {
    /**
     * scene to be rendered
     */
    protected final Scene scene;

    /**
     * constructor for ray tracer
     * @param scene to be intersected
     */
    public RayTracer(Scene scene){
        this.scene = scene;
    }

   public abstract Color traceRay(Ray ray);

    public abstract Color traceRay(List<Ray> ray);
    public abstract Color AdaptiveSuperSamplingRec(Point centerP, double Width, double Height, double minWidth, double minHeight, Point cameraLoc, Vector Vright, Vector Vup, List<Point> prePoints);
}
