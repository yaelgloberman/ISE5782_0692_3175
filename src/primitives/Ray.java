package primitives;

import static primitives.Util.isZero;

public class Ray {
    final private Point _p0;
    final private Vector _dir;

    /**
     * constructor to initialize the point and the direction vector-normalized
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;
        if(!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return _p0.equals(ray._p0) && _dir.equals(ray._dir);
    }

    /**
     *
     * @param t-scale
     * @return p=p0+t*v
     */
    public Point getPoint(double t){
        if (isZero(t)){
            return  _p0;
        }
        return _p0.add(_dir.scale(t));
    }

    @Override
    public String toString() {
        return "Ray" +
                "_p0" + _p0 +
                ", _dir=" + _dir
                ;
    }
}