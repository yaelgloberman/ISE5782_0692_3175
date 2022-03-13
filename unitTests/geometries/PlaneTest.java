package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for plane class
 */
class PlaneTest {
    /**
     * Test method for {@link geometries.Plane#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to plane");
    }

    /**
     * test method for {@link Plane#Plane(Point, Point, Point)}
     */
    @Test
    void testConstructor(){
        // ============ Equivalence Partitions Tests ==============

        // TC01: when all if the given products are correct
        try {
            new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Plane");
        }
        // =============== Boundary Values Tests ==================

        // TC11: the 2 points are the same
        assertThrows(IllegalArgumentException.class,
                () -> {new Plane(new Point(1, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));},
                "Constructed a Plane with 2 od the same points");

        // TC12: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Plane(new Point(2, 1, 0), new Point(5, 0, 3), new Point(3.5, 0.5, 1.5)), //
                "Constructed a Plane that all of the given points are on the same line");
    }
}