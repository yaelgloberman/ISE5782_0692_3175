package geometries;

import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * class for intersectable for more than one shape with a ray.
 */
public class Geometries extends Intersectable{
    List<Intersectable> _intersectableList;
    /**
     * constructor
     * @param emission
     */

    /**
     * constructor
     * @param intersectables
     */
    public Geometries(Intersectable... intersectables){
        _intersectableList=new LinkedList<>();
        Collections.addAll(_intersectableList,intersectables);
    }

    /**
     * add new intersectable point to the list
     * @param intersectables  number of Geometric objects that implement {@link Intersectable}
     */
    public void add(Intersectable...intersectables){
        Collections.addAll(_intersectableList,intersectables);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result=null;
        for ( var item: _intersectableList) {
            List<GeoPoint> itemLst=item.findGeoIntersections(ray);
            if(itemLst!=null){

                if(result==null){
                    result=new LinkedList<>();
                }
                result.addAll(itemLst);
            }
        }
        return result;
    }
}
