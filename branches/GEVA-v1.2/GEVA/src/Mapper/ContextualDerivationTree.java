/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Mapper;

import Individuals.GEChromosome;
import Util.Constants;
import java.util.ArrayList;

/**
 * This class is used for building a genotype to phenotype mapping
 * it also stores information on which codons affect structural or
 * nodal grammar rules, for a more selective mutation
 * @author jbyrne
 */
public class ContextualDerivationTree extends DerivationTree{

    private ArrayList<Integer> structCodonList;
    private ArrayList<Integer> nodeCodonList;
    private ArrayList<Symbol> terminalRules;
    private ArrayList<Symbol> nonTerminalRules;

    public ContextualDerivationTree(GEGrammar gram, GEChromosome gen)
    {
        super(gram, gen);
        this.terminalRules = gram.getTerminalRules();
        this.nonTerminalRules = gram.getNonTerminalRules();
        this.structCodonList = new ArrayList();
        this.nodeCodonList = new ArrayList();
    }

     /**
     * Copy Constructor
     * @param DerivationTree copy
     */
    public ContextualDerivationTree(ContextualDerivationTree copy) {
	super(copy);
    this.terminalRules = new ArrayList<Symbol>(copy.terminalRules);
    this.nonTerminalRules = new ArrayList<Symbol>(copy.nonTerminalRules);
    this.structCodonList = new ArrayList(copy.structCodonList);
    this.nodeCodonList = new ArrayList(copy.nodeCodonList);
    }

     /**
     * Grows the nodes of the tree in a recursive procedure. It also records
     * which nodes are structural or nodal
     * @param t start node
     * @return validity of growth
     **/
    @Override
    boolean growNode(DerivationNode t)
    {
        Symbol s = t.getData();
        boolean found = false;

        // Adding each codon to either the Nodal or Structural lists
        for(Symbol NTSymbol : nonTerminalRules)
        {
            if(s.equals(NTSymbol))
            {
                this.structCodonList.add(this.geneCnt);
                found = true;
            }
        }
        // this bit is to make sure it has more than one production
        // otherwise it mutates a codon that is used elsewhere
        Rule r = this.grammy.findRule(s);
        boolean usedCodon = false;

        if(r!=null && found == false)
        {
            //ugly as sin but this allows GECODONVALUES to be mutated
            if(r.size()>1 || r.get(0).get(0).getSymbolString().startsWith(Constants.GE_CODON_VALUE_PARSING))
            {
                usedCodon = true;
            }
        }

        if(usedCodon)
        {
            for(Symbol TSymbol : terminalRules)
            {
                if(s.equals(TSymbol))
                {        
                    this.nodeCodonList.add(this.geneCnt);
                }
            }
        }

        boolean result = super.growNode(t);
        return result;
    }

     /**
     * this vector contains the index of every structural codon.
     * @return a vector of index values
     */
    public ArrayList<Integer> getStructCodonList() {

        return structCodonList;
    }

     /**
     * this vector contains the index of every nodal codon.
     * @return a vector of index values
     */
    public ArrayList<Integer> getNodeCodonList() {

        return nodeCodonList;
    }


}
