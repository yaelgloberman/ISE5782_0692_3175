package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);


    /***
     *  test constructor {@link Vector#Vector(double, double, double)}
     */
    @Test
    void testConstructorNotZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Vector(0, 0, 0.00000000000000000000034);
                },
                "Vector(0,0,0) should have thrown Exception");
    }

    @Test
    /**
     * methhod for testing {@link Vector#lengthSquared()}
     */
    void testLengthSquared() {
        // test length squared
        assertEquals(14.0000001,v1.lengthSquared(),0.000001,"ERROR: lengthSquared() wrong value");
    }

    /**
     * method for testing {@link Vector#length()}
     */
    @Test
    void testLength() {
        //test length
            assertEquals(5,new Vector(0, 3, 4).length(),"ERROR: length() wrong value");
    }

    /**
     * method for testing {@link Vector#dotProduct(Vector)}
     */
    @Test
    public void testDotProduct() {

        // ============ Equivalence Partitions Tests ==============
        //  Simple dotProduct test
        assertEquals(-28d, v1.dotProduct(v2), 0.00001, "dotProduct() wrong value");

        // =============== Boundary Values Tests ==================
        //  dotProduct for orthogonal vectors
        assertEquals(0d, v1.dotProduct(v3), 0.00001, "dotProduct() for orthogonal vectors is not zero");
    }

    /**
     * method for testing {@link Vector#normalize()}
     */
    @Test
    void testNormalized() {
        Vector v = new Vector(1, 2, 3);
        Vector n = v.normalize();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(1d, n.length(), 0.00001, "ERROR: the normalized vector is not a unit vector");
        assertThrows(IllegalArgumentException.class,// test that the vectors are co-lined
                () -> v.crossProduct(n),
                "ERROR: the normalized vector is not parallel to the original one");
        assertFalse(v.dotProduct(n)<0,"ERROR: the normalized vector is opposite to the original one");
    }


    @Test
    public void testScaleEP() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Vector(2, 4, 6),
                new Vector(1, 2, 3).scale(2),
                "Wrong vector scale");
    }

    @Test
    public void testScaleBVA() {
        // =============== Boundary Values Tests ==================
        // TC11: test scaling to 0
        assertThrows(IllegalArgumentException.class,
                () -> new Vector(1, 2, 3).scale(0d),
                "Scale by 0 must throw exception");
    }

    @Test
    public void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals(new Vector(1, 1, 1),
                new Vector(2, 3, 4).add(new Vector(-1, -2, -3)),
                "Wrong vector add");

        // =============== Boundary Values Tests ==================
        // TC11: test adding v + (-v)
		assertThrows( IllegalArgumentException.class,
				() -> new Vector(1, 2, 3).add(new Vector(-1, -2, -3)),
                "Add v plus -v must throw exception");
   }

    /**
     * method for testing{@link Vector#crossProduct(Vector)}
     */
    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001, "ERROR: crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "ERROR: crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)), "ERROR: crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        assertThrows(
                IllegalArgumentException.class,
                () -> v1.crossProduct(v2),
                "ERROR: crossProduct() for parallel vectors does not throw an exception"
        );
    }
}
