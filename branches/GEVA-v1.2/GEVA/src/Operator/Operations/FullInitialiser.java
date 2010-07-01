package Operator.Operations;

import Exceptions.InitializationException;
import Mapper.Production;
import Mapper.Rule;
import Mapper.Symbol;
import Util.Enums;
import Util.Structures.NimbleTree;
import java.util.ArrayList;
import java.util.Iterator;
import Mapper.GEGrammar;
import Util.Random.RandomNumberGenerator;

import java.util.Properties;

/**
 * Create a genotype by growing a tree to maxDepth for all leaves
 * @author erikhemberg
 */
public class FullInitialiser extends GrowInitialiser {

    /**
     * New instance
     * @param rng random number genrator
     * @param gegrammar grammatical evolution grammar (GEGrammar)
     * @param maxDepth max growth depth of tree
     */
    public FullInitialiser(RandomNumberGenerator rng, GEGrammar gegrammar, int maxDepth) {
        super(rng, gegrammar, maxDepth);
    }

    /**
     * New instance
     * @param rng random number generator
     * @param gegrammar grammatical evolution grammar (GEGrammar)
     * @param p properties
     */
    public FullInitialiser(RandomNumberGenerator rng, GEGrammar gegrammar, Properties p) {
        super(rng, gegrammar, p);
    }
    
    /**
     * Recursively builds a tree.
     * @param dt Tree to grow on
     * @return If the tree is valid
     **/
    @Override
    public boolean grow(NimbleTree<Symbol> dt) {
        Rule rule;
        Iterator<Production> prodIt;
        ArrayList<Integer> possibleRules = new ArrayList<Integer>();
        boolean recursiveRules;
        Production prod;
        int prodVal;
        boolean result;
        int newMaxDepth;
        Iterator<Symbol> symIt;
        Symbol symbol;
        
        try {
            if(dt.getCurrentNode().getData().getType() == Enums.SymbolType.TSymbol) {
                //Check if it is for ADF
		if(dt.getCurrentNode().getData().getSymbolString().contains("BRR")) {
		    this.extraCodons++;
		}
                return true;
            }
            if(dt.getCurrentLevel() > this.maxDepth) {
                System.out.println("Too deep:"+dt.getCurrentLevel()+">"+this.maxDepth);
                return false;
            }
            rule = grammar.findRule(dt.getCurrentNode().getData());
            if(rule!=null){
                prodIt = rule.iterator();
                possibleRules.clear();
                int ii = 0;
                recursiveRules = false;
                //		System.out.print(rule.getLHS().getSymbolString()+" minD:"+rule.getMinimumDepth()+" maxD:"+maxDepth+" cD:"+dt.getCurrentLevel());
                
                while(prodIt.hasNext()) {
                    prod = prodIt.next();
                    if((dt.getCurrentLevel()+prod.getMinimumDepth()) <= this.maxDepth) {
                        if(!recursiveRules && prod.getRecursive()) {
                            recursiveRules = true;
                            possibleRules.clear(); //Only recursive rules allowed? What about non-recursive rules with the proper length??
                        }
                        if(!recursiveRules || (recursiveRules && prod.getRecursive())) {
                            possibleRules.add(ii);
                        }
                    }
                    ii++;
                }
                //		System.out.print(" \n");
                if(possibleRules.isEmpty()) {
                    System.out.println("EmptyPossible rules:"+rule);
                    return false;
                } else {
                    prodVal = this.rng.nextInt(possibleRules.size());
                    int modVal = possibleRules.get(prodVal);
                    int tmp1 = this.rng.nextInt((Integer.MAX_VALUE-rule.size()));
                    int tmp;
                    int mod = tmp1%rule.size();
                    int diff;
                    if(mod>modVal) {
                        diff = mod - modVal;
                        tmp = tmp1 - diff;
                    } else {
                        diff = modVal - mod;
                        tmp = tmp1 + diff;
                    }
                    int newMod = tmp%rule.size();
                    if(newMod!=modVal) {
                        System.out.println("modVal:"+modVal+" tmp1:"+tmp1+" mod:"+mod+" tmp:"+tmp+" rule.size():"+rule.size()+" newMod:"+newMod);
                        
                    }
                    if(rule.size() > 1) {
                        this.chromosome.add(tmp); //correct choosing of production??
                        prod = rule.get(possibleRules.get(prodVal));
                    } else {
                        // only one rule does not use a codon
			//this.chromosome.add(tmp); //correct choosing of production??
                        prod = rule.get(0);
                    }
                }
                result = true;
                newMaxDepth = dt.getDepth();
                symIt = prod.iterator();
                while(symIt.hasNext() && result) {
                    symbol = symIt.next();
                    dt.addChild(symbol);
                    dt.setCurrentNode(dt.getCurrentNode().getEnd());
                    dt.setCurrentLevel(dt.getCurrentLevel() + 1);
                    result = grow(dt);
                    dt.setCurrentLevel(dt.getCurrentLevel() - 1);
                    if(newMaxDepth < dt.getDepth()) {
                        newMaxDepth = dt.getDepth();
                    }
                }
                chromosome.setValid(result);
                dt.setDepth(newMaxDepth);
                return result;
            } else {
		if(!checkGECodonValue(dt)) {
		    throw new InitializationException("Non-existent rule, maybe GECODON not yet impelemnted");
		}
                return true;
            }
        } catch(InitializationException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
