package Operator.Operations.ContextSensitiveOperations;

import Operator.Operations.*;
import Individuals.GEChromosome;
import Individuals.GEIndividual;
import Individuals.Individual;
import Mapper.ContextualDerivationTree;
import Util.GenotypeHelper;
import Util.Random.RandomNumberGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This will find if the chromosome contains a expandable leaf node
 * and will mutate it depending on the mutation probability
 */
public class NodalMutation extends MutationOperation
{

    private CreationOperation creationOperation;
    
    public NodalMutation(RandomNumberGenerator rng, Properties p)
    {   super(rng, p);
    }

    public NodalMutation(double prob, RandomNumberGenerator rng)
    {   super(prob, rng);
    }

    public void setCreationOperation(CreationOperation creationOperation)
    {   this.creationOperation = creationOperation;
    }

    
    
    @Override
    public void doOperation(Individual operand)
    {
        ContextualDerivationTree tree = (ContextualDerivationTree) GenotypeHelper.buildDerivationTree(operand);
        GEChromosome chromosome = (GEChromosome)operand.getGenotype().get(0);     
            
        if(tree!=null) // this is to check that the individual is not invalid
        {
        // This vector contains the index values for all the leaf node codons
        ArrayList<Integer> nodeCodonList = new ArrayList(tree.getNodeCodonList());          
        //iterate through the leaf Node codons and mutate depending on probability
        for(int i =0;i< nodeCodonList.size();i++)
            {
            int codonIndex  = nodeCodonList.get(i);
            if(this.rng.nextBoolean(this.probability)) 
                {
                 chromosome.set(codonIndex, Math.abs(rng.nextInt()));
                }
            }
            ((GEIndividual)operand).invalidate();
            tree = null;  
        }
    }
    
    @Override
    public void doOperation(List<Individual> operands)
    {   for(Individual operand : operands)
            doOperation(operand);
    }
}
