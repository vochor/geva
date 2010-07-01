package Mapper;

import Util.Enums;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class SymbolTest {

    public SymbolTest() {
    }

    /**
     * Test constructors
     **/
    @Test
    public void testTestConstructors() {
        System.out.println("* SymbolTest: TestConstructors");

        //Symbol()
        Symbol s = new Symbol();
        assertEquals(Enums.SymbolType.TSymbol, s.getType());
        assertEquals("", s.getSymbolString());

        //Symbol(string,type)
        s = new Symbol("test", Enums.SymbolType.TSymbol);
        assertEquals(Enums.SymbolType.TSymbol, s.getType());
        assertEquals(true, s.getSymbolString().equals("test"));

    }

    /**
     * Test copy constructors
     **/
    @Test
    public void testTestCopyConstructor() {
        System.out.println("* SymbolTest: TestCopyConstructor");
        Symbol s = new Symbol("test", Enums.SymbolType.TSymbol);
        Symbol copy = new Symbol(s);
        assertEquals(copy.getType(), s.getType());
        assertEquals(copy.getSymbolString(), s.getSymbolString());
        assertNotSame(copy, s);
    }

    /**
     * Test of equals method, of class Symbol.
     */
    @Test
    public void testEquals_Symbol() {
        System.out.println("* SymbolTest: equals");
        //Unequal strings
        Symbol newSymbol = new Symbol("test2", Enums.SymbolType.NTSymbol);
        Symbol instance = new Symbol("test", Enums.SymbolType.NTSymbol);
        boolean expResult = false;
        boolean result = instance.equals(newSymbol);
        assertEquals(expResult, result);

        //Unequal type && string
        newSymbol = new Symbol("test", Enums.SymbolType.TSymbol);
        expResult = false;
        assertEquals(expResult, instance.equals(newSymbol));

        //Unequal type
        newSymbol = new Symbol("test2", Enums.SymbolType.TSymbol);
        expResult = false;
        assertEquals(expResult, instance.equals(newSymbol));

        //Equal
        newSymbol = new Symbol("test", Enums.SymbolType.NTSymbol);
        expResult = true;
        assertEquals(expResult, instance.equals(newSymbol));

        //Null
        newSymbol = null;
        expResult = false;
        try {
            result = instance.equals(newSymbol);
        } catch (NullPointerException e) {
            assertTrue(true);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Symbol.
     */
    @Test
    public void testEquals_String() {
        System.out.println("* SymbolTest: equals");
        Symbol instance = new Symbol("test2", Enums.SymbolType.TSymbol);
        assertEquals(false, instance.equals("test"));
        assertEquals(true, instance.equals("test2"));
    }

    /**
     * Test of clear method, of class Symbol.
     */
    @Test
    public void testClear() {
        System.out.println("* SymbolTest: clear");
        Symbol instance = new Symbol("test", Enums.SymbolType.TSymbol);
        instance.clear();
        assertEquals(null, instance.getSymbolString());
        assertEquals(null, instance.getType());
    }

    /**
     * Test of toString method, of class Symbol.
     */
    @Test
    public void testToString() {
        System.out.println("* SymbolTest: toString");
        Symbol instance = new Symbol("test", Enums.SymbolType.TSymbol);
        assertEquals("test", instance.toString());
    }
}