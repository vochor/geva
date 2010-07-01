/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Individuals;

import Mapper.Symbol;
import Util.Enums;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author erikhemberg
 */
public class PhenotypeTest {

    public PhenotypeTest() {
    }

/**
 * Test of constructor, of calss Phenotype
 */
    @Test
    public void testPhenotype_Phenotype() {
        System.out.println("PhenotypeTest: Phenotype(Phenoytpe p)");
        Phenotype p = new Phenotype();
        p.add(new Symbol("a",Enums.SymbolType.TSymbol));
        p.add(new Symbol("b", Enums.SymbolType.TSymbol));
        Phenotype p2 = new Phenotype(p);
        assertEquals(p2, p);
        assertNotSame(p,p2);
    }

    /**
     * Test of getString method, of class Phenotype.
     */
    @Test
    public void testGetString() {
        System.out.println("PhenotypeTest: getString");
        
        //Empty
        Phenotype instance = new Phenotype();
        String expResult = "";
        String result = instance.getString();
        assertEquals(expResult, result);

        //Filled
        instance = new Phenotype();
        instance.add(new Symbol("a",Enums.SymbolType.TSymbol));
        instance.add(new Symbol("b", Enums.SymbolType.TSymbol));
        result = "a b ";
        expResult = instance.getString();
        assertEquals(true, result.equals(expResult));

                //Filled
        instance = new Phenotype();
        instance.add(new Symbol("a",Enums.SymbolType.TSymbol));
        instance.add(new Symbol("", Enums.SymbolType.TSymbol));
        instance.add(new Symbol("b", Enums.SymbolType.TSymbol));
        result = "a  b ";
        assertEquals(true, instance.getString().equals(result));
}

    /**
     * Test of getStringNoSpace method, of class Phenotype.
     */
    @Test
    public void testGetStringNoSpace() {
        System.out.println("PhenotypeTest: getStringNoSpace");
        Phenotype instance = new Phenotype();
        String result = instance.getStringNoSpace();
        //Filled
        instance = new Phenotype();
        instance.add(new Symbol("a",Enums.SymbolType.TSymbol));
        instance.add(new Symbol("b", Enums.SymbolType.TSymbol));
        result = "ab";
        String expResult = instance.getString();
        assertEquals(true, instance.getStringNoSpace().equals(result));
    }

}