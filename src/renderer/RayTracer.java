package renderer;

import primitives.Color;
import primitives.Ray;
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
}
