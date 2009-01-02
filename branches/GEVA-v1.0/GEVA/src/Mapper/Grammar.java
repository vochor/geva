/*
 * Grammar.java
 *
 * Created on 09 October 2006, 11:41
 *
 */

package Mapper;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Abstract class for mapping using a Grammar
 * @author EHemberg
 */
public abstract class Grammar implements Mapper{

    // Variables
    private boolean validGrammar;
    private int startSymbol_index;
    ArrayList<Rule> rules;

    // Abstract methods
    public abstract boolean genotype2Phenotype();
    public abstract boolean phenotype2Genotype();
    public abstract void clear();

    // Constructors
    /** Default constructor */
    Grammar() {

        setValidGrammar(false);
        startSymbol_index=0;
        this.rules = new ArrayList<Rule>();
    }

    /** Copy constructor.
     * @param copy grammar to copy
     */
    Grammar(Grammar copy) {
        ArrayList<Rule> aLR = new ArrayList<Rule>();
        Iterator<Rule> ruleIt = copy.rules.iterator();
        Rule r;
        while(ruleIt.hasNext()) {
            r = new Rule(ruleIt.next());
            aLR.add(r);
        }
        this.rules = aLR;

        setValidGrammar(copy.getValidGrammar());
        startSymbol_index = copy.startSymbol_index;
    }

    //Get & Set
    /** Return the validity of the current grammar.
     * @return validity of grammar
     */
    boolean getValidGrammar() {
        return validGrammar;
    }

    /** Set the validity of the grammar.
     * @param newValidGrammar validity of grammar
     */
    void setValidGrammar(boolean newValidGrammar) {
        validGrammar = newValidGrammar;
    }

    /**
     * Get the start symbol of the grammar
     * @return start symbol
     */
    public Symbol getStartSymbol() {
        return rules.get(startSymbol_index).getLHS();
    }

    /** Change start symbol by index on ArrayList of rules.
     * @param index index of start symbol
     * @return if the setting of start symbol was successfull
     */
    @SuppressWarnings({"UnusedReturnValue", "SameParameterValue"})
    boolean setStartSymbol(int index) {
        if(index<rules.size()){// Check boundaries.
            startSymbol_index = index;
            genotype2Phenotype();// Update phenotype.
            return true;
        }
        return false;
    }

    /** Change start symbol by symbol.
     * @param newStartSymbol new start symbol
     * @return success of replacement
     */
    public boolean setStartSymbol(Symbol newStartSymbol){
        Rule rule;
        for (Rule rule1 : rules) {
            rule = rule1;
            if (rule.getLHS().equals(newStartSymbol)) { //Check that start symbol exists
                startSymbol_index = rules.indexOf(rule);
                genotype2Phenotype();// Update phenotype.
                return true;
            }
        }
        return false;
    }

    /** Change start symbol by string.
     * @param newStartSymbol new start symbol
     * @return success of replacement
     */
    public boolean setStartSymbol(String newStartSymbol){
        Rule rule;
        for (Rule rule1 : rules) {
            rule = rule1;
            if (rule.getLHS().getSymbolString().equals(newStartSymbol)) {
                startSymbol_index = rules.indexOf(rule);
                genotype2Phenotype();// Update phenotype.
                return true;
            }
        }
        return false;
    }

    /** Return pointer to current start rule.
     * @return start rule
     */
    public Rule getStartRule() {
        return rules.get(startSymbol_index);
    }

    /**
     * Get the rules in the grammar
     * @return rules in the grammar
     */
    public ArrayList<Rule> getRules() {
        return rules;
    }

    /**
     * Set rules in grammar
     * @param newRules rules to set
     */
    public void setRules(ArrayList<Rule> newRules) {
        this.rules = newRules;
    }

}
