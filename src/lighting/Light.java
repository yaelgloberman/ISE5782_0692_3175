package lighting;

import primitives.Color;
/*
base class of lights
 */
abstract class Light {
    private Color intensity;

    /**
     * constructor
     * @param intensity
     */
    protected Light(Color intensity) {

        this.intensity = intensity;
    }

    /**
     * get the color intensity
     * @return
     */
    public Color getIntensity() {

        return intensity;
    }


}
