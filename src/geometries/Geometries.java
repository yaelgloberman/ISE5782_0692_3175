package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * class for intersectable for more than one shape with a ray.
 */
public class Geometries implements Intersectable{
    List<Intersectable> _intersectableList;
    /**
     * constructor
     */
    public Geometries(){
        _intersectableList=new LinkedList<>();
    }

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
     * @param intersectablesntersectables
     */
    public void add(Intersectable...intersectables){
        Collections.addAll(_intersectableList,intersectables);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result=null;
        for ( var item: _intersectableList) {
            List<Point> itemLst=item.findIntersections(ray);
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
