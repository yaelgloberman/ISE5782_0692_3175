package primitives;

import java.util.Objects;

public class Point {
    final Double3 _xyz;

    public Point(Double3 xyz) {
        _xyz = xyz;
    }

    public Point(double x, double y, double z) {
        _xyz=new Double3(x,y,z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(_xyz, point._xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_xyz);
    }

    @Override
    public String toString() {
        return "Point " + _xyz ;
    }

}
