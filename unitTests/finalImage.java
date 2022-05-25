import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import renderer.ImageWriter;
import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.io.CharArrayWriter;

public class finalImage {
   // private Scene scene = new Scene.SceneBuilder("Test scene").build();
    @Test
    public void ourPicture(){
        Scene scene = new Scene.SceneBuilder("Test scene")//
                .setBackground(new Color(51,0,0)).build();

        Point A=new Point(-165,170,-300);
        Point B=new Point(-165,140,-300);
        Point C=new Point(-135,140,-300);
        Point D=new Point(-135,170,-300);
        Point E=new Point(-165,170,-200);
        Point F=new Point(-165,140,-200);
        Point G=new Point(-135,140,-200);
        Point H=new Point(-135,170,-200);
        Point J=new Point(-105,140,-300);
        Point K=new Point(-105,170,-300);
        Point L=new Point(-105,140,-200);
        Point M=new Point(-105,170,-200);
        Point N=new Point(-165,110,-300);
        Point O=new Point(-135,110,-300);
        Point P=new Point(-165,110,-200);
        Point Q=new Point(-135,110,-200);
        Point R=new Point(-150,90,-250);
        Point S=new Point(-90,155,-250);

        //Scene scene = new Scene.SceneBuilder("Test scene").build();//
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0,0,-1), new Vector(1,0,0)) //
                .setVPDistance(600).setVPSize(200, 200); //
        scene.getGeometries().add(
                new Sphere(new Point(-95,50,-300),10).setEmission(new Color(24,95,12))
                        .setMaterial(new Material().setKD(0.5).setKS(0.8).setKT(0.1).setKR(0.9).setNShininess(20)),
                new Sphere(new Point(-95,70,-300),10).setEmission(new Color(24,95,12))
                        .setMaterial(new Material().setKD(0.5).setKS(0.8).setKT(0.1).setKR(0.9).setNShininess(20)),
                new Sphere(new Point(-95,90,-300),10).setEmission(new Color(24,95,12))
                        .setMaterial(new Material().setKD(0.5).setKS(0.8).setKT(0.1).setKR(0.9).setNShininess(20)),
                new Sphere(new Point(-95,110,-300),10).setEmission(new Color(24,95,12))
                        .setMaterial(new Material().setKD(0.5).setKS(0.8).setKT(0.1).setKR(0.9).setNShininess(20)),
                new Sphere(new Point(-75,50,-300),10).setEmission(new Color(95,17,95))
                        .setMaterial(new Material().setKD(0.5).setKS(0.8).setKT(0.1).setKR(0.9).setNShininess(20)),
                new Sphere(new Point(-75,90,-300),10).setEmission(new Color(95,17,95))
                        .setMaterial(new Material().setKD(0.5).setKS(0.8).setKT(0.1).setKR(0.9).setNShininess(20)),
                new Sphere(new Point(-75,70,-300),10).setEmission(new Color(95,17,95))
                        .setMaterial(new Material().setKD(0.5).setKS(0.8).setKT(0.1).setKR(0.9).setNShininess(20)),
                new Sphere(new Point(-55,50,-300),10).setEmission(new Color(104,102,58))
                        .setMaterial(new Material().setKD(0.5).setKS(0.1).setKT(0.8).setNShininess(20)),
                new Sphere(new Point(-55,70,-300),10).setEmission(new Color(104,102,58))
                        .setMaterial(new Material().setKD(0.5).setKS(0.1).setKT(0.8).setNShininess(20)),
                new Sphere(new Point(-35,50,-300),10).setEmission(new Color(PINK))
                        .setMaterial(new Material().setKD(0.5).setKS(0.1).setKT(0.8).setNShininess(20)),
                new Polygon(A,B,C,D)
                        .setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKD(0.5).setKS(0.1).setKT(0).setKR(0).setNShininess(20)),
                new Polygon(E,F,G,H)
                        .setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKD(0.5).setKS(0.1).setKT(0).setKR(0).setNShininess(20)),
                new Polygon(A,D,H,E)
                        .setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKD(0.5).setKS(0.1).setKT(0).setKR(0).setNShininess(20)),
                new Polygon(G,H,D,C)
                        .setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKD(0.5).setKS(0.1).setKT(0).setKR(0).setNShininess(20)),
                new Polygon(G,F,B,C)
                        .setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKD(0.5).setKS(0.1).setKT(0).setKR(0).setNShininess(20)),
                new Polygon(E,F,B,A)
                        .setEmission(new Color(WHITE))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0).setNShininess(20)),
                new Polygon(L,M,K,J)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Polygon(G,C,J,L)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Polygon(M,H,D,K)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Polygon(G,H,M,L)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Polygon(C,D,K,J)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Polygon(N,O,Q,P)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Polygon(Q,G,C,O)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Polygon(P,F,B,N)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Polygon(N,B,C,O)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Polygon(P,F,G,Q)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Triangle(Q,O,R)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Triangle(N,O,R)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Triangle(N,P,R)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Triangle(P,Q,R)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Triangle(M,L,S)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Triangle(M,K,S)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Triangle(K,J,S)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Triangle(L,J,K)
                        .setEmission(new Color(51,204,255))
                        .setMaterial(new Material().setKD(0.5).setKS(1).setKT(0.5).setNShininess(20)),
                new Sphere(new Point(90, 130, -300),20).setEmission(new Color(ORANGE))   //light
                        .setMaterial(new Material().setKD(0.5).setKS(0.8).setKT(1)),
                new Triangle(new Point(94,130,-300),new Point(300,130,-300),new Point(300,130,-250))
                        .setEmission(new Color(102,0,153)),
                new Triangle(new Point(-105,200,700),new Point(-105,5,700),new Point(-105,5,-2500))
                        .setEmission(new Color(102,0,153))
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setKT(0.1).setKR(0.1).setNShininess(20)),
//                new Triangle(new Point(-105,-200,700),new Point(-105,-5,700),new Point(-105,-5,-2500))
//                        .setEmission(new Color(95,22,95))
//                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setKT(0.1).setKR(0.1).setNShininess(20)),
                new Plane(new Point(-1000,28,0),new Point(90,5,0),new Point(-105,33,-2034))
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material().setKD(0.5).setKS(0.5).setKR(0.25))
        );


        scene.lights.add(
               new PointLight(new Color(255,255,153), new Point(90, 150, -300)) //
                        .setKL(0.00001).setKQ(0.000005)
        );

        scene.lights.add(
                new SpotLight(new Color(255,204,0), new Point(90,150,-300), new Vector(15, 12, -19)) //
                        .setKL(0.00001).setKQ(0.000005)
        );
        camera.setImageWriter(new ImageWriter("ourPicture", 1000, 1000)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage();



    }
}
