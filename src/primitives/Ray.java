package primitives;

public class Ray {
    final private Point _p0;
    final private Vector _dir;

    /**
     * constructor to initilize the point and the direction vector-normalized
     * @param p0 the point
     * @param dir the vector
     */
    public Ray(Point p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalize();//Normalizes the direction vector
    }

    public Point getP0() {
        return _p0;
    }

    public Vector getDirection() {
        return new Vector(_dir._xyz);
    }

    //public Point getPoint(double delta ){
      //  if (isZero(delta)){
        //    return _p0;
        //}
        //return _p0.add(_dir.scale(delta));
    //}




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;
        if(!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    @Override
    public String toString() {
        return "Ray" +
                "_p0" + _p0 +
                ", _dir=" + _dir
                ;
    }
}