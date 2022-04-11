package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * common interface for all graphic objects that intersect with a ray{@link Ray}
 */
public abstract class Intersectable {

    public class GeoPoint{

        public final Geometry geometry;
        public final Point point;
        public GeoPoint(Geometry geometry,Point point){
            this.geometry=geometry;
            this.point=point;

        }
    }
    public final List<Point> findIntersections(Ray ray){
        List<GeoPoint> geoPointList=findGeoIntersections(ray);
        return geoPointList==null?null
                :geoPointList.stream().map(geoPoint -> geoPoint.point)
                .toList();
    }

    public final List<GeoPoint> findGeoIntersections(Ray ray){

        return findGeoIntersectionsHelper(ray);
    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);


}
