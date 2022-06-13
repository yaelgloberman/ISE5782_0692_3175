package renderer;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Camera producing rays through a view plane
 */
public class Camera {


    private Vector vRight;      //vector pointing towards the right: x-axis
    private Point p0;           //camera eye
    private Vector vTo;         //vector pointing towards the scene
    private Vector vUp;         //vector pointing upwards the scene

    private double distance;     // camera distance from view plane
    private double width;        // view plane width
    private double height;       // view plane height

    private ImageWriter imageWriter = null;
    private RayTracer rayTracer = null;

    private Point centerPoint=new Point(0,0,200);

    /**
     * @param p0  origin point in 3D space
     * @param vUp vector go upwards
     * @param vTo vector vTo
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (!isZero(vUp.dotProduct(vTo))) {
            throw new IllegalArgumentException("vTo and vUp should orthogonal");
        }
        this.p0 = p0;
        //normalizing the positional vectors
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = this.vTo.crossProduct(this.vUp);
    }

    /**
     * set distance between the camera and its view plane
     *
     * @param distance the distance for the view plane
     * @return instance of camera for chaining
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * setting view plane size
     *
     * @param width  "physical" width
     * @param height "physical" height
     * @return instance of camera for chaining
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * constructing a ray through a pixel
     *
     * @param Nx represent number of columns in the view plane
     * @param Ny represent number of rows in the view plane
     * @param j  represent the index of the row in the view plane
     * @param i  represent the index of the column in the view plane
     * @return ray from the camera to pixel[i,j]
     */
    public Ray constructRay(int Nx, int Ny, int j, int i) {
        //image center
        Point Pc = p0.add(vTo.scale(distance));

        //ratio (pixel width & height)
        double Ry = height / Ny;
        double Rx = width / Nx;

        //Pixel[i,j] center
        Point Pij = Pc;

        //delta values for going to Pixel[i,j] from Pc
        double yI = -(i - (Ny - 1) / 2d) * Ry;
        double xJ = (j - (Nx - 1) / 2d) * Rx;

        //if xJ is not zero
        if (!isZero(xJ)) {
            Pij = Pij.add(vRight.scale(xJ));
        }

        if (!isZero(yI)) {
            Pij = Pij.add(vUp.scale(yI));
        }
        return new Ray(p0, Pij.subtract(p0));    //return ray from the camera to pixel[i,j]


    }

    /**
     * setter
     * @param imageWriter {@link ImageWriter}
     * @return the camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * setter
     * @param rayTracer
     * @return this camera
     */
    public Camera setRayTracer(RayTracer rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * create image
     */
    public void writeToImage() {
        if (imageWriter==null)
            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
        imageWriter.writeToImage();
    }

    /**
     * print the grid
     * @param interval
     * @param color which we want it to color
     */
    public void printGrid(int interval, Color color) {
        for (int j = 0; j < imageWriter.getNy(); j++) {
            for (int i = 0; i < imageWriter.getNy(); i++) {
                //grid 16 X 10
                if (j % interval == 0 || i % interval == 0) {
                    imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    /**
     * color every pixel and then we see the whole image
     */
    public void renderImage() {
        try {
            if (imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if (rayTracer == null) {
                throw new MissingResourceException("missing resource", RayTracer.class.getName(), "");
            }

            //rendering the image
            int nX = imageWriter.getNx();
            int nY = imageWriter.getNy();

            //foreach pixel (i,j)
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                   List<Ray>  ray=constructRays(nX, nY, j, i);// ray from the camera to the pixel
                    Color pixelColor = rayTracer.traceRay(ray); //get the color of the pixel
                    imageWriter.writePixel(j, i, pixelColor); //color the pixel
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }

    /**
     *
     * @param Nx
     * @param Ny
     * @param j
     * @param i
     * @return
     * improving the jagged edges - mini project 1
     */
    public List<Ray> constructRays(int Nx,int Ny, int j, int i){
        //Image center
        Point Pc = p0.add(vTo.scale(distance));

        //Ratio (pixel width & height)
        double Ry =height/ Ny;
        double Rx = width/Nx;

        //delta values for going to Pixel[i,j] from Pc
        double yI = -(i - (Ny -1)/2)* Ry;
        double xJ = (j - (Nx -1)/2)* Rx;

        if (! isZero(xJ) ) {
            Pc = Pc.add(vRight.scale(xJ));
        }

        if (! isZero(yI)) {
            Pc = Pc.add(vUp.scale(yI));
        }

        List<Ray> rays= new LinkedList<>();

         //puts the pixel center in the first place on the list.
        rays.add(new Ray(p0,Pc.subtract(p0)));

        // creating Ry*Rx rays for each pixel.
        Point newPoint=new Point(Pc.getX()- Rx/2,Pc.getY()+Rx/2,Pc.getZ());
        for (double t = newPoint.getY(); t >newPoint.getY()-Ry; t-=0.01) {
            for (double k = newPoint.getX(); k< newPoint.getX()+Rx; k+=0.01) {
                rays.add(new Ray(p0,new Point(k,t,Pc.getZ()).subtract(p0)));
            }
        }
        return rays;
    }
    /**
     * construct ray through a pixel in the view plane
     * nX and nY create the resolution
     *
     * @param Nx number of pixels in the width of the view plane
     * @param Ny number of pixels in the height of the view plane
     * @param j  index row in the view plane
     * @param i  index column in the view plane
     * @return ray that goes through the pixel (j, i)  Ray(p0, Vi,j)
     */
    public  Ray constructRayThroughPixel(int Nx,int Ny, int j, int i){
        Point pij=getCenterOfPixel(Nx,Ny,j,i);   //center point of the pixel

        //Vi,j =pi,j-p0, the direction of the ray to the pixel[j,i]
        Vector vij=pij.subtract(p0);
        return new Ray(p0,vij);

    }

    private Point getCenterOfPixel(int nX, int nY, int j, int i) {
        // calculate the ratio of the pixel by the height and by the width of the view plane
        // the ratio Ry = h/Ny, the height of the pixel
        double rY = alignZero(height / nY);
        // the ratio Rx = w/Nx, the width of the pixel
        double rX = alignZero(width / nX);

        // Xj = (j - (Nx -1)/2) * Rx
        double xJ = alignZero((j - ((nX - 1d) / 2d)) * rX);
        // Yi = -(i - (Ny - 1)/2) * Ry
        double yI = alignZero(-(i - ((nY - 1d) / 2d)) * rY);

        Point pIJ = centerPoint;

        if (!isZero(xJ)) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            pIJ = pIJ.add(vUp.scale(yI));
        }
        return pIJ;
    }

    /**
     * Creates a beam of rays into a square grid
     * @param nX Pixel length
     * @param nY Pixel width
     * @param j Position the pixel on the y-axis inside the grid
     * @param i Position the pixel on the x-axis inside the grid
     * @param numOfRays The root of the number of beams sent per pixel
     * @return List of beams of rays
     */

    public List<Ray> constructRays(int nX, int nY, int j, int i, int numOfRays) {
        if (numOfRays== 0) {
            throw new IllegalArgumentException("num Of Rays can not be 0");
        }
        if (numOfRays == 1) {
            return List.of(constructRayThroughPixel(nX, nY, j, i));
        }
        else {
            List<Ray> rays = new LinkedList<>();
            Point pIJ = getCenterOfPixel(nX, nY, j, i);

            double rY = alignZero(height / nY);
            // the ratio Rx = w/Nx, the width of the pixel
            double rX = alignZero(width / nX);

            double pY = alignZero(rY / numOfRays);
            double pX = alignZero(rX / numOfRays);
            Point PijTemP = pIJ;
            for (int p = 1; p < numOfRays; p++) {
                for (int m = 1; m < numOfRays; m++) {
                    PijTemP = pIJ.add(vRight.scale(pX * m)).add(vUp.scale(pY * p));
                    rays.add(new Ray(p0, PijTemP.subtract(p0).normalize()));
                }
            }


            return rays;
        }



    }
}

