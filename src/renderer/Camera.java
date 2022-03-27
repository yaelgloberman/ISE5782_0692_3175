package renderer;

import primitives.*;

import static primitives.Util.isZero;

/**
 * Camera producing rays through a view plane
 */
public class Camera {


    private  Vector vRight;      //vector pointing towards the right: x axis
    private  Point p0;           //camera eye
    private  Vector vTo;         //vector pointing towards the scene
    private  Vector vUp;         //vector pointing upwards the scene

    private double distance;     // camera distance from view plane
    private double width;        // view plane width
    private double height;       // view plane height

    /**
     *
     * @param p0 origin point in 3D space
     * @param vUp vector
     * @param vTo
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (! isZero(vUp.dotProduct(vTo))){
           throw new IllegalArgumentException("vTo and vUp should orthogonal");
        }
        this.p0=p0;
        //normalizing the positional vectors
        this.vTo=vTo.normalize();
        this.vUp=vUp.normalize();

        this.vRight=this.vTo.crossProduct(this.vUp);
    }

    /**
     * set distance between the camera and its view plane
     * @param distance the distance for the view plane
     * @return instance of camera for chaining
     */
    public Camera setVPDistance(double distance) {
        this.distance=distance;
        return this;
    }

    /**
     * setting view plane size
     * @param width "physical" width
     * @param height "physical" height
     * @return instance of camera for chaining
     */
    public Camera setVPSize(double width,double height) {
        this.width=width;
        this.height=height;
        return this;
    }

    /**
     * constructing a ray through a pixel
     *
     * @param Nx represent number of columns in the view plane
     * @param Ny represent number of rows in the view plane
     * @param j represent the index of the row in the view plane
     * @param i represent the index of the column in the view plane
     * @return ray from the camera to pixel[i,j]
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        //image center
        Point Pc=p0.add(vTo.scale(distance));

        //ratio (pixel width & height)
        double Ry=height/Ny;
        double Rx=width/Nx;

        //Pixel[i,j] center
        Point Pij=Pc;

        //delta values for going to Pixel[i,j] from Pc
        double yI=-(i-(Ny -1)/2d)*Ry;
        double xJ=(j-(Nx -1)/2d)*Rx;

        //if xJ is not zero
        if(!isZero(xJ)){
            Pij=Pij.add(vRight.scale(xJ));
        }

        if(!isZero(yI)){
            Pij=Pij.add(vUp.scale(yI));
        }
        return new Ray(p0,Pij.subtract(p0));    //return ray from the camera to pixel[i,j]


    }
}
