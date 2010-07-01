/*
 * Phenotype.java
 *
 * Created on 09 October 2006, 16:51
 *
 */

package Individuals;

import Mapper.Symbol;

import java.util.ArrayList;

/**
 * Phenotype extends ArrayList<Symbol>
 *
 * Phenotype holds the result of mapping from a genotype through a mapper.
 * The representation is a list of symbols, which must be all terminal if 
 * the individuals mapping was valid. There are two methods that are of particular
 * interest.
 *
 * getString() and getStringNoSpace()
 *
 *  get string will be used if you are evolving a program in a highlevel syntax
 *  where the language symbols must be space seperated. 
 *  The no space variant would be useful in other situations such as evolving non
 *  program code structures. Machine code, or solutions that have to undergo further 
 *  interpretation.   
 *
 */

public class Phenotype extends ArrayList<Symbol> {

    public Phenotype()
    {
        super();
    }

    /**
     * Copy constructor
     * @param p phenotype to copy
     */
    public Phenotype(Phenotype p) {
        super(p);
    }

    /**
     * Get a string reperesentation of the output(Phenotype)
     * @return string representation
     */
    public String getString() {
        Symbol currentSymbol;
        StringBuilder S = new StringBuilder();
        for (Object o : this) {
            currentSymbol = (Symbol)o;
            S.append(currentSymbol.getSymbolString());
            S.append(" ");//Add space
        }
        return S.toString();
    }

    /**
     * Get string representaiton without added whitespace
     * @return string representation
     */
    public String getStringNoSpace() {
        Symbol currentSymbol;
        StringBuilder S = new StringBuilder();
        for (Object o : this) {
            currentSymbol = (Symbol)o;
            S.append(currentSymbol.getSymbolString());
        }
        return S.toString();
    }

    @Override
    public String toString() {
        return this.getString();
    }

}
