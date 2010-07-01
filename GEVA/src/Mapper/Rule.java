/*
 * Rule.java
 *
 * Created on 09 October 2006, 19:25
 */

package Mapper;

import java.util.ArrayList;
import Util.Enums;
/**
 * Rule extends Arraylist<Production>.
 * @author EHemberg
 */
public class Rule extends ArrayList<Production> {
    
    //Variables
    private boolean recursive;  // Recursive nature of rule
    private int minimumDepth;	// Minimum depth of parse tree for production to map to terminal symbol(s)
    private Symbol lhs; //Left hand side symbol of the rule
    
    //Constructors
    /**Creates a new rule with newLength elements.
     * @param newLength initial length of rule
     */
    public Rule(int newLength){
        super(newLength);
        setRecursive(false);
        setMinimumDepth(Integer.MAX_VALUE>>1);
    }
    
    /**Creates a new rule*/
    public Rule(){
        super();
    }
    
    /** Copy constructor.
     * @param copy rule to copy
     */
    public Rule(Rule copy){
        super(copy);
        this.lhs = copy.lhs;
        this.recursive = copy.recursive;
        this.minimumDepth = copy.minimumDepth;
    }
    
    /** Return the recursive nature of this rule.
     * @return true if rule is recursive
     */
    public boolean getRecursive() {
        return recursive;
    }
    
    /** Update the recursive nature of this rule.
     * @param newRecursive set recursiveness
     */
    public void setRecursive(boolean newRecursive){
        recursive=newRecursive;
    }
    
    /** Return the minimum mapping depth of this rule.
     * @return minimum depth
     */
    public int getMinimumDepth() {
        return minimumDepth;
    }
    
    /** Update the minimum mapping depth of this Rule.
     * @param newMinimumDepth minimum depth
     */
    public void setMinimumDepth(int newMinimumDepth){
        minimumDepth=newMinimumDepth;
    }

    /**
     * Set the left hand side symbol of the rule
     * Must be a Non Terminal symbol. 
     * @param s left hand side symbol
     */
    public void setLHS(Symbol s) {
	assert (s.getType() == Enums.SymbolType.NTSymbol) : "Bad type: "+s.getType();
	if(s.getType() == Enums.SymbolType.NTSymbol) {
	    this.lhs = s;
	}
    }

    /**
     * Get the left hand symbol of the rule
     * @return left hand side symbol
     */
    public Symbol getLHS() {
        return this.lhs;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(lhs.getSymbolString());
        s.append("::=");
        for(int i=0;i<this.size();i++) {
            s.append(this.get(i));
            if(i<(this.size()-1)) {
                s.append("|");
            }
        }
        return s.toString();
    }
    
}
