package Helpers;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class JUnitHelper {

    public static void checkArrays(int[] expResult, int[] result) {
   assertEquals(expResult.length, result.length);
        for (int i = 0; i < expResult.length; i++) {
            assertEquals(expResult[i], result[i]);
        }
    }

    public static void checkArrays(ArrayList expResult, ArrayList result) {
   assertEquals(expResult.size(), result.size());
        for (int i = 0; i < expResult.size(); i++) {
            assertEquals(expResult.get(i),result.get(i));
        }
    }

    private JUnitHelper() {
    }

    final public static void checkArrays(final double[] expResult, final double[] result) {
        assertEquals(expResult.length, result.length);
        for (int i = 0; i < expResult.length; i++) {
            assertEquals(expResult[i], result[i], 0.0000001);
        }
    }

    final public static void checkMatrix(final double[][] expResult, final double[][] result) {
        assertEquals(expResult.length, result.length);
        for (int i = 0; i < expResult.length; i++) {
            JUnitHelper.checkArrays(expResult[i], result[i]);
        }
    }

    final public static void checkList(final List<?> expected, final List<?> actual) {
        assertEquals(expected.size(), actual.size());
        for(int i=0; i<expected.size();i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}