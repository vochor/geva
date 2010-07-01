package Mapper;

import Util.Constants;
import Util.Enums;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class ProductionTest {

    public ProductionTest() {
    }

    /**
     * Test constructors
     **/
    @Test
    public void testTestConstructors() {
        System.out.println("* ProductionTest: TestConstructors");
        //UNINITIALISED
        Production p = new Production();
        assertEquals(false, p.getRecursive());
        assertEquals(0, p.getMinimumDepth());

        //Size of production
        p = new Production(10);
        assertEquals(false, p.getRecursive());
        assertEquals((Integer.MAX_VALUE >> 1), p.getMinimumDepth());
        assertEquals(0, p.size());
    }

    /**
     * Test copy constructors
     **/
    @Test
    public void testTestCopyConstructor() {
        System.out.println("* ProductionTest: TestCopyConstructor");
        Production p = new Production();
        p = new Production(10);
        p.add(new Symbol("test", Enums.SymbolType.NTSymbol));
        Production copy = new Production(p);
        assertEquals(copy.getRecursive(), p.getRecursive());
        assertEquals(copy.getMinimumDepth(), p.getMinimumDepth());
        assertEquals(copy.size(), p.size());
        assertNotSame(copy, p);
        assertEquals(copy.get(0).getType(), p.get(0).getType());
        assertEquals(copy.get(0).getSymbolString(), p.get(0).getSymbolString());
        assertSame(copy.get(0), p.get(0));
    }

    /**
     * Test of getNTSymbols method, of class Production.
     */
    @Test
    public void testGetNTSymbols() {
        System.out.println("* ProductionTest: getNTSymbols");
//Default test
        Production instance = new Production();
        instance.add(new Symbol("test", Enums.SymbolType.NTSymbol));
        instance.add(new Symbol("test", Enums.SymbolType.TSymbol));
        instance.add(new Symbol("test", Enums.SymbolType.NTSymbol));
        instance.add(new Symbol(Constants.GE_CODON_VALUE_PARSING,Enums.SymbolType.NTSymbol));
        int expResult = 2;
        int result = instance.getNTSymbols();
        assertEquals(expResult, result);

        //Test clear
        instance.clear();
        assertEquals(0, instance.getNTSymbols());
    }

    /**
     * Test of toString method, of class Production.
     */
    @Test
    public void testToString() {
        System.out.println("* ProductionTest: toString");
        Production instance = new Production();
        instance.add(new Symbol("test", Enums.SymbolType.NTSymbol));
        instance.add(new Symbol("test", Enums.SymbolType.TSymbol));
        String expResult = "testtest";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}