package lighting;

import primitives.Color;

abstract class Light {
    private Color intensity;

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
