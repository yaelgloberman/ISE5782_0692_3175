package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Triangle extends Polygon {
    /**
     * constructor that initialize the triangle
     * @param p1 the first point
     * @param p2 the second point
     * @param p3 the third point
     */
    public Triangle(Point p1,Point p2,Point p3){

        super(p1,p2,p3);
    }
    /**
     * the func find the intersections of the ray with the triangle
     * @param ray ray pointing towards the graphic object
     * @return list of intersection points
     */
    @Override
   public List<Point> findIntersections(Ray ray){
        //TODO
        List<Point> result=plane.findIntersections(ray);
        if(result==null){
            return null;
        }
        Point p0=ray.getP0();
        Vector v=ray.getDirection();

        Point p1=vertices.get(0);
        Point p2=vertices.get(1);
        Point p3=vertices.get(2);

        Vector v1= p0.subtract(p1); //(p0=>p1)
        Vector v2= p0.subtract(p2); //(p0=>p2)
        Vector v3= p0.subtract(p3); //(p0=>p3)

        Vector n1=v1.crossProduct(v2);
        Vector n2=v2.crossProduct(v3);
        Vector n3=v3.crossProduct(v1);

        double s1=v.dotProduct(n1);
        double s2=v.dotProduct(n2);
        double s3=v.dotProduct(n3);
        if((s1>0&&s2>0&&s3>0)||(s1<0&&s2<0&&s3<0)){
            return result;
        }

        return super.findIntersections(ray);
    }


}
