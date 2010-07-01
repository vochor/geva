/*
 * Production.java
 *
 * Created on 09 October 2006, 16:14
 */

package Mapper;

import Util.Constants;
import Util.Enums;

import java.util.ArrayList;

/**
 * Production extends an ArrayList<Symbol>. 
 * @author EHemberg
 */
public class Production extends ArrayList<Symbol>{

    // Variables
    private boolean recursive; // Recursive nature of production
    private int minimumDepth; // Minimum depth of parse tree for production to map to terminal symbol(s)
    
    /** Creates a new Production with newLength elements.
     * @param newLength initial length of production
     */
    public Production(int newLength){
        super(newLength);
        setRecursive(false);
        setMinimumDepth(Integer.MAX_VALUE>>1);
    }
    
    /** Creates a new Production*/
    public Production(){
        super();
    }
    
    /**Copy constructor; copy all symbols.
     * @param copy productions to copy
     */
    public Production(Production copy){
        super(copy);
        this.recursive = copy.recursive;
        this.minimumDepth = copy.minimumDepth;
    }
    
    /** Return the recursive nature of this production.
     * @return true if the production is recursive
     */
    public boolean getRecursive() {
        return recursive;
    }
    
    /** Update the recursive nature of this production.
     * @param newRecursive recursiveness of productino
     */
    public void setRecursive(boolean newRecursive){
        recursive = newRecursive;
    }
    
    /** Return the minimum mapping depth of this production. (Number of inputs until
     * output consists of only terminals)
     * @return minimum depth
     */
    public int getMinimumDepth() {
        return minimumDepth;
    }
    
    /** Update the minimum mapping depth of this production.
     * @param newMinimumDepth minimum depth
     */
    public void setMinimumDepth(int newMinimumDepth){
        minimumDepth = newMinimumDepth;
    }
    
    /**
     * Return the number of NTSymbols in the production
     * JByrne also added GE_CODONS to the terminal list.
     * @return number of Non-Terminal symbols in the production
     */
    public int getNTSymbols() {
        int cnt = 0;
        for (Symbol o : this) {
        if(o.getType() == Enums.SymbolType.NTSymbol && !(o.getSymbolString().startsWith(Constants.GE_CODON_VALUE_PARSING))) {
                cnt++;
            }
        }
        return cnt;
    }
    
    @Override
    @SuppressWarnings({"ForLoopReplaceableByForEach"})
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(int i = 0;i<this.size();i++) {
            s.append(this.get(i).getSymbolString());
        }
        return s.toString();
    }
}
