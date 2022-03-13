package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private Point p1 = new Point(1, 2, 3);

    /**
     * test method for {@link Point#subtract(Point)}
     */
    @Test
    void testSubtract() {
        assertEquals(new Vector(1, 1, 1)
                ,new Point(2, 3, 4).subtract(p1)
                , "ERROR: Point - Point does not work correctly");
    }

    @Test
    /**
     *  test method for {@link Point#add(Vector)}
     */
    void testAdd() {
        assertEquals(p1.add(new Vector(-1, -2, -3))
                ,new Point(0, 0, 0)
                ,"ERROR: Point + Vector does not work correctly");
    }

    @Test
    void testDistanceSquared() {
        Point point = new Point(0.5, 0, -100);
        assertEquals(0.25,point.distanceSquared(new Point(0,0,-100)),
                "Erorr! the distanceSquared is incorrect");
    }

    @Test
    void testDistance() {
        Point point = new Point(0.5, 0, -100);
        assertEquals(0.5, point.distance(new Point(0,0,-100)),
                "Erorr! the distance is incorrect");
    }
}