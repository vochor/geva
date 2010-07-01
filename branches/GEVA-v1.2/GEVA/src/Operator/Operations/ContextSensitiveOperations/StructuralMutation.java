package Operator.Operations.ContextSensitiveOperations;

import Operator.Operations.*;
import Individuals.GEChromosome;
import Individuals.GEIndividual;
import Individuals.Individual;
import Mapper.ContextualDerivationTree;
import Mapper.ContextualDerivationTree;
import Mapper.DerivationTree;
import Util.GenotypeHelper;
import Util.Random.RandomNumberGenerator;
/*
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//*/
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This will find if the chromosome contains a expandable structural node
 * and will mutate it depending on the mutation probability
 */
public class StructuralMutation extends MutationOperation
{

    private CreationOperation creationOperation;
    
    public StructuralMutation(RandomNumberGenerator rng, Properties p)
    {   super(rng, p);
    }

    public StructuralMutation(double prob, RandomNumberGenerator rng)
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
            // This vector contains the index values for all the structural codons
            ArrayList<Integer> structCodonList = new ArrayList(tree.getStructCodonList());
            //iterate through the structural codons and mutate depending on probability
            for(int i =0;i< structCodonList.size();i++)
            {               
                int codonIndex  =structCodonList.get(i);
                if(this.rng.nextBoolean(this.probability)) 
                {  
//                    if(tree!=null) // this is to check that the individual is not invalid
//                    {
//                    System.out.println("The old tree was:");
//                    System.out.println(tree.toString());
//                    System.out.println("The OLD codon list is:"+structCodonList.toString());
//                    System.out.println("The codon to mutate is:"+codonIndex);
//                    }

                    chromosome.set(codonIndex, Math.abs(rng.nextInt()));                   
                    tree = (ContextualDerivationTree) GenotypeHelper.buildDerivationTree(operand);
//                    if(tree!=null)
//                    {
//                    structCodonList = new ArrayList(tree.getStructCodonList());
//                    System.out.println("The NEW codon list is:"+structCodonList.toString());
//                    System.out.println("The new tree is:");
//                    System.out.println(tree.toString());
//                    }
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
