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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result=plane.findGeoIntersections(ray);
        if(result==null){
            return null;
        }
        Point p0=ray.getP0();
        Vector v=ray.getDirection();
        Vector v1=vertices.get(0).subtract(p0);//(p0=>p1)
        Vector v2=vertices.get(1).subtract(p0); //(p0=>p2)
        Vector v3= vertices.get(2).subtract(p0); //(p0=>p3)
        Vector[] crossVectors={v1.crossProduct(v2).normalize(),v2.crossProduct(v3).normalize(),v3.crossProduct(v1).normalize()};
        int numOfPositiveNumbers=0;
        for(Vector vector:crossVectors){
            double vn=v.dotProduct(vector);
            if(vn==0){
                return null;
                 }
            if(vn>0){
                numOfPositiveNumbers++;
            }
        }
                //if numOfPositiveNumbers is not 0 or 3 it is mean there is at least 1 number with odd sign
        if (numOfPositiveNumbers!=0 && numOfPositiveNumbers!=3){
            return null;
        }
        Point intersectPoint=result.get(0).point;
        return List.of((new GeoPoint(this,intersectPoint)));
    }


}
