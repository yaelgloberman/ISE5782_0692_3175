package elements;

import primitives.Color;
import primitives.Double3;

/**
 * Ambient light for all 3D objects
 * Ambient light represents a fixed-intensity and fixed color light source
 * that affects all the object in the scene equally
 * the ambient light intensity in point is IP=Ka*Ia
 */
public class AmbientLight {

    private final Color intensity;

    /**
     * Constructor
     * @param Ia illumination color for light
     * @param Ka attenuation factor
     */
    public AmbientLight(Color Ia, Double3 Ka){
        intensity = Ia.scale(Ka);
    }

    /**
     * default constructor
     */
    public AmbientLight(){
        intensity = Color.BLACK;
    }

    /**
     * getter for intensity of ambient light
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
