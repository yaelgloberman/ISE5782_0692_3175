import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class ImagesTest {
    private Scene scene = new Scene.SceneBuilder("Test scene").build();

    @Test
    public void image1Test(){
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.getGeometries().add( //
                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(PINK)) //
                        .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setKT(0.3)));


        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKL(0.0004).setKQ(0.0000006));

        camera.setImageWriter(new ImageWriter("image1", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage();
    }
}
