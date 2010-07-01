package Mapper;

import Util.Enums;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class RuleTest {

    private Rule r;

    public RuleTest() {
    }

    @Before
    public void setUp() {
        r = new Rule();
        Production p = new Production();
        Symbol lhs = new Symbol("lhs", Enums.SymbolType.NTSymbol);
        Symbol s1 = new Symbol("testT", Enums.SymbolType.TSymbol);
        Symbol s2 = new Symbol("testNT", Enums.SymbolType.NTSymbol);
        Production p2 = new Production();
        //This should be an empty symbol according to the constructor
        Symbol s3 = new Symbol();
        r.setLHS(lhs);
        p.add(s1);
        p.add(s2);
        p2.add(s3);
        r.add(p);
        r.add(p2);
        r.setRecursive(true);
        r.setMinimumDepth(2);
    }

    /**
     * Test CopyConstructor
     **/
    @Test
    public void testTestCopyConstructor() {
        System.out.println("* RuleTest: testTestCopyConstructor");
        Rule copy = new Rule(r);
        assertEquals(copy.getRecursive(), r.getRecursive());
        assertEquals(copy.getMinimumDepth(), r.getMinimumDepth());
        assertEquals(copy.getLHS(), r.getLHS());
        assertSame(copy.getLHS(), r.getLHS());
        for (int i = 0; i < r.size(); i++) {
            assertEquals(copy.get(i), r.get(i));
            assertSame(copy.get(i), r.get(i));
        }
    }

    /**
     * Test of setLHS method, of class Rule.
     */
    @Test
    public void testSetLHS() {
        //Test TSymbol
        System.out.println("* RuleTest: setLHS");
        Symbol s = new Symbol("testT", Enums.SymbolType.TSymbol);
        Rule instance = new Rule();
        try {
            instance.setLHS(s);
        } catch (AssertionError e) {
            assertTrue(true);
        }
        assertEquals(null, instance.getLHS());

        //Test NTSymbol
        s = new Symbol("testT", Enums.SymbolType.NTSymbol);
        instance.setLHS(s);
        assertEquals(true, s == instance.getLHS());

        Symbol s1 = new Symbol("testT", Enums.SymbolType.NTSymbol);
        assertEquals(true, s1.equals(instance.getLHS()));
    }

    /**
     * Test of toString method, of class Rule.
     */
    @Test
    public void testToString() {
        System.out.println("* RuleTest: toString");
        Rule instance = r;
        String expResult = "lhs::=testTtestNT|";
        String result = instance.toString();
        assertEquals(result, true, expResult.equals(result));
    }
}