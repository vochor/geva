/*
 * GEGrammar.java
 *
 * Created on 13 October 2006, 10:23
 *
 */

package Mapper;

import Exceptions.BadParameterException;
import Individuals.GEChromosome;
import Individuals.Phenotype;
import Mapper.DerivationNode;
import Parameter.ParameterI;

import java.io.File;
import java.util.Properties;

import Util.Constants;
import Util.Structures.TreeNode;
import java.util.Stack;

/**
 * Class for GEGrammar.
 * GEGrammar maps from a GEChromosome to a Phenotype
 * @author EHemberg
 */
public class GEGrammar extends ContextFreeGrammar implements ParameterI {
    
    private GEChromosome genotype;
    private Phenotype phenotype;
    private int maxWraps;
    private int usedWraps;
    private int usedCodons;
    private String name;
    int maxDerivationTreeDepth;
    int maxCurrentTreeDepth;
    
    /** Creates a new instance of GEGrammar */
    public GEGrammar() {
        super();
        this.maxWraps = 1;
    }

    /**
     * New instance
     * @param file file to read grammar from
     */
    public GEGrammar(String file) {
        super();
        this.readBNFFile(file);
        this.maxWraps = 1;
    }

    /**
     * New instance
     * @param p properties
     */
    public GEGrammar(Properties p) {
        super();
        this.setProperties(p);
    }

    /**
     * Copy constructor. Does not copy the genotype and phenotype
     * @param copy grammar to copy
     */
    public GEGrammar(GEGrammar copy) {
        super(copy);
        this.setMaxWraps(copy.getMaxWraps());
        this.maxWraps = copy.maxWraps;
        this.maxDerivationTreeDepth = copy.getMaxDerivationTreeDepth();
        //this.phenotype = new Phenotype(copy.phenotype);
        //this.genotype = new GEChromosome(copy.genotype);
    }

    /**
     * Set properties
     *
     * @param p object containing properties
     */
    public void setProperties(Properties p) {
       String file = null;
        String key;
        try {
            key = Constants.GRAMMAR_FILE;
            file = p.getProperty(key);
            if(file == null) {
                throw new BadParameterException(key);
            }
        } catch(BadParameterException e) {
            System.out.println(e+" No default grammar");
        }
        File f = new File(file);
        if(!f.exists()){ // try classloader
            this.readBNFFile(file);
        } else {
            this.readBNFFileFromFilesystem(file);
        }
        int value;
        try {
            key = Constants.MAX_WRAPS;
            value = Integer.parseInt(p.getProperty(key));
            if(value < 0) {
                throw new BadParameterException(key);
            }
        } catch(BadParameterException e) {
            value = 0;
            System.out.println(e+" default wraps:"+value);
        }
        this.maxWraps = value+1;
        
        this.maxDerivationTreeDepth = Integer.parseInt(p.getProperty(Constants.MAX_DERIVATION_TREE_DEPTH, Constants.DEFAULT_MAX_DERIVATION_TREE_DEPTH));
    }

    /**
     * Map input to output
     * @return validity of mapping
     */
    public boolean genotype2Phenotype() {
        return genotype2Phenotype(false);
    }

    /**
     * Map output to input
     * @return validity of mapping
     */
    public boolean phenotype2Genotype() {
        return true;
    }

    /**
     * Clear the grammar
     */
    public void clear() {
        //this.genotype.clear();
        //this.phenotype.clear();
        this.rules.clear();
        this.setStartSymbol(0);
        this.setValidGrammar(false);
    }
    
    /** Instanciates a derivation tree and calls buildDerivationTree() if
     * b is true else sets valid map to true 
    * @param b if tree should be built
     * @return validity of mapping
    */
    @SuppressWarnings({"UnusedAssignment"})
    public boolean genotype2Phenotype(boolean b) {
        boolean validMap;
        if(b) {
            this.phenotype.clear();
            DerivationTree dT = new DerivationTree(this, this.getGenotype());
            validMap = dT.buildDerivationTree();
            this.usedCodons = dT.getGeneCnt();
            this.usedWraps = dT.getWrapCount();
            this.maxCurrentTreeDepth = dT.getDepth();
            this.name = generateNameFromTree(dT);
            dT = null;//remove reference
        } else {
            validMap = true;
        }
        return validMap;
    }

    private String generateNameFromTree(DerivationTree tree)
    {   StringBuilder builder = new StringBuilder();
        Stack<DerivationNode> nodeStack = new Stack<DerivationNode>();
        nodeStack.push((DerivationNode)tree.getRoot());
        while(nodeStack.empty() == false)
        {   DerivationNode nodes = nodeStack.pop();
            if(nodes != null)
            {   if(nodes.getCodonIndex() != -1)
                    builder.append(nodes.getCodonPick());
                if(nodes.size() != 0)
                {   builder.append('[');
                    nodeStack.push(null);
                    for(int i = nodes.size(); i > 0; i--)
                        nodeStack.push((DerivationNode)nodes.get(i - 1));
                }
            }
            else
                builder.append(']');
        }
        return builder.toString();
    }

    /**
     *  Get the max depth of the derivation tree built by the grammar
     * @return max derivation tree depth
     */
    public int getMaxCurrentTreeDepth() {
        return maxCurrentTreeDepth;
    }

    /**
     * Set the max depth of the derriation tree that was built by this grammar
     * @param maxCurrentTreeDepth
     */
    public void setMaxCurrentTreeDepth(int maxCurrentTreeDepth) {
        this.maxCurrentTreeDepth = maxCurrentTreeDepth;
    }
    
    /**
     * Get max wraps allowed
     * @return max wraps
     */
    public int getMaxWraps(){
        return maxWraps;
    }

    /**
     * Set max wraps
     * @param i max wraps
     */
    public void setMaxWraps(int i){
        this.maxWraps = i;
    }

    /**
     * Set input
     * @param genotype input
     */
    public void setGenotype(GEChromosome genotype) {
        this.genotype = genotype;
    }

    /**
     * Set output
     * @param phenotype output
     */
    public void setPhenotype(Phenotype phenotype) {
        this.phenotype = phenotype;
    }

    public Phenotype getPhenotype() {
        return phenotype;
    }

    public GEChromosome getGenotype() {
        return this.genotype;
    }

    public void setPhenotype(Object p) {
        this.phenotype = (Phenotype)p;
        
    }
    
    public void setGenotype(Object g) {
        this.genotype = (GEChromosome)g;
        
    }

    /**
     * Get used inputs (codons in the genotype)
     * @return number of used inputs
     */
    public int getUsedCodons() {
        return this.usedCodons;
    }

    /**
     * Get used wraps (Number of times the input was reread from the start)
     * @return number of wraps
     */
    public int getUsedWraps() {
        return this.usedWraps;
    }

    public int getMaxDerivationTreeDepth() {
        return maxDerivationTreeDepth;
    }

    public void setMaxDerivationTreeDepth(int maxDerivationTreeDepth) {
        this.maxDerivationTreeDepth = maxDerivationTreeDepth;
    }

    /**
     * This method calculates an upper bound for the number of inputs needed 
     * to build a derivation tree according to the specified maxDerivationTreeDepth
     * and the max number of non terminals in a production of the grammars rules.
     * 
     * The upper bound is calculated as the sum of the max non terminals at each
     * depth of the tree. 
     * (length = Sum_i=0^D a^i, D=max depth, a = max non terminals in a production)
     * 
     * @return max number of inputs
     */
    public int getMaxChromosomeLengthByDepth() {
        int len = Integer.MAX_VALUE;
        if (this.maxDerivationTreeDepth < Integer.MAX_VALUE) {
            int maxNTProd = 0;
            // Get the maximum number of Non terminals in a production among the rules
            for (Rule r : rules) {
                for (Production p : r) {
                    if (maxNTProd < p.getNTSymbols()) {
                        maxNTProd = p.getNTSymbols();
                    }
                }
            }
            // Sum for each depth
            len = 0;
            for (int i = 0; i < this.maxDerivationTreeDepth; i++) {
                len += Math.pow(maxNTProd, i);
            }
        }
        return len;
    }
    
    public String getName()
    {   return name;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj == null || getClass() != obj.getClass())
            return false;
        final GEGrammar that = (GEGrammar)obj;
        if(name != that.name
        &&(name == null
        || name.equals(that.name) == false))
            return false;
        return true;
    }

    @Override
    public int hashCode()
    {   if(name != null)
            return name.hashCode();
        return 0;
    }

}

