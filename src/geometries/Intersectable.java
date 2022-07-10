package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * common interface for all graphic objects that intersect with a ray{@link Ray}
 */
public abstract class Intersectable {
    /**
     * internal class for certain geometry
     */
    public class GeoPoint{

        public Geometry geometry;
        public Point point; //the intersectable point

        /**
         * constructor
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry,Point point){
            this.geometry=geometry;
            this.point=point;


        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }
    }

    /**
     *
     * @param ray
     * @return the list of all intersections points
     */
    public final List<Point> findIntersections(Ray ray){
        List<GeoPoint> geoPointList=findGeoIntersections(ray);
        return geoPointList==null?null
                :geoPointList.stream().map(gp -> gp.point)
                .toList();
    }

    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray,Double.POSITIVE_INFINITY);
    }

    public final List<GeoPoint> findGeoIntersections(Ray ray,double maxDistance){

        return findGeoIntersectionsHelper(ray,maxDistance);
    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance);


}
